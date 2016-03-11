/**
 * Copyright (c) 2016 TermMed SA
 * Organization
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/
 */

/**
 * Author: Alejandro Rodriguez
 */
package org.ihtsdo.control.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.ihtdso.fileprovider.CurrentFile;
import org.ihtsdo.control.model.ControlResultLine;
import org.ihtsdo.control.model.IControlPattern;
import org.ihtsdo.control.model.PatternConfig;
import org.ihtsdo.statistics.model.OutputInfoFactory;
import org.ihtsdo.statistics.runner.ProcessLogger;
import org.ihtsdo.utils.FileHelper;
import org.ihtsdo.utils.I_Constants;
import org.ihtsdo.utils.ResourceUtils;
import org.ihtsdo.utils.XmlMapUtil;

public class PatternExecutor {
	File configFile;
	private String resultOutputFolder;
	private HashSet<String> excludes;
	private HashSet<String> newConcepts;
	private HashSet<String> changedConcepts;
	private HashMap<Long,String> conceptTerms;
	private String releaseDate;
	private String previousReleaseDate;
	ProcessLogger logger ;

	public PatternExecutor( File configFile) {
		super();
		this.configFile = configFile;
		logger = new ProcessLogger();
	}


	public void execute() throws IOException, Exception {
		logger.logInfo("Starting patterns execution");
		XMLConfiguration xmlConfig;
		try {
			xmlConfig=new XMLConfiguration(configFile);
		} catch (ConfigurationException e) {
			logger.logInfo("ClassificationRunner - Error happened getting params configFile." + e.getMessage());
			throw e;
		}
		String runControls = xmlConfig.getString("patternExecutions.runControlPatterns");

		if (runControls==null || !runControls.toLowerCase().equals("true")){
			return;
		}
		resultOutputFolder=I_Constants.PATTERN_OUTPUT_FOLDER;
		File resultTmpFolder=new File(resultOutputFolder);
		if (!resultTmpFolder.exists()){
			resultTmpFolder.mkdirs();
		}else{
			FileHelper.emptyFolder(resultTmpFolder);
		}
		this.releaseDate = xmlConfig.getString("releaseDate");
		this.previousReleaseDate = xmlConfig.getString("previousReleaseDate");

		getNewConcepts();

		getChangedConcepts();

		excludes=new HashSet<String>();
		Object prop = xmlConfig.getProperty("patternExecutions.exclusions.patternId");
		if (prop!=null){
			if (prop instanceof Collection) {
				for (String loopProp : (Collection<String>)prop) {
					excludes.add(loopProp);
				}
			} else if (prop instanceof String) {
				excludes.add((String)prop);
			}
		}
		String relativePath="src/main/resources/";
		String path="org/ihtsdo/control/patterns";
		if( new File(relativePath).isDirectory() ){

			path=relativePath + path;

			logger.logInfo("Getting patterns from file system: " + path);
			executeFromFileSystem(path);

		}else{
			logger.logInfo("Getting patterns from resources: " + path);
			executeFromResources(path);

		}
	}

	private void getChangedConcepts() throws UnsupportedEncodingException, FileNotFoundException, Exception {
		changedConcepts=new HashSet<String>();
		if (CurrentFile.get().getChangedConceptFile()!=null){
			BufferedReader br=FileHelper.getReader(CurrentFile.get().getChangedConceptFile());
			br.readLine();
			String line;
			String[] spl;
			while ((line=br.readLine())!=null){
				spl=line.split(",",-1);
				changedConcepts.add(spl[1]);
			}
			br.close();
		}
	}

	private void getNewConcepts() throws UnsupportedEncodingException, FileNotFoundException, Exception {
		newConcepts=new HashSet<String>();
		if (CurrentFile.get().getNewConceptFile()!=null){
			BufferedReader br=FileHelper.getReader(CurrentFile.get().getNewConceptFile());
			br.readLine();
			String line;
			String[] spl;
			while ((line=br.readLine())!=null){
				spl=line.split(",",-1);
				newConcepts.add(spl[1]);
			}
			br.close();
		}
	}

	private void executeFromFileSystem(String path) throws IOException, Exception,
	SQLException {
		Collection<File>list = ResourceUtils.getFileSystemScripts(path);
		for(File file:list){

			if (file.getAbsolutePath().indexOf("/.")>-1 || file.getName().startsWith(".")){
				continue;
			}
			PatternConfig patternConfig=XmlMapUtil.getPatternConfigFromFileSystem(file);
			if (!excludes.contains(patternConfig.getPatternId())){
				processPattern(patternConfig);
			}
		}
	}

	private void processPattern(PatternConfig patternConfig) throws Exception {
		logger.logInfo("Executing pattern: " + patternConfig.getName() + " - id:" + patternConfig.getPatternId());
		IControlPattern controlPattern= (IControlPattern) Class.forName(patternConfig.getExecutionClass()).newInstance();

		controlPattern.setConfigFile(configFile);

		controlPattern.setNewConceptsList(newConcepts);
		controlPattern.setChangedConceptsList(changedConcepts);
		controlPattern.setCurrentEffTime(releaseDate);
		controlPattern.setPreviousEffTime(previousReleaseDate);
		controlPattern.setPatternId(patternConfig.getPatternId());
		controlPattern.setResultFile(new File(resultOutputFolder,patternConfig.getResultFileName()));
		controlPattern.setConceptTerms(CurrentFile.get().getConceptTerms());
		long start=logger.startTime();
		controlPattern.execute();

		String msg=logger.endTime(start);
		int posIni=msg.indexOf("ProcessingTime:")+16;
		patternConfig.setTimeTaken(msg.substring(posIni));
		List<ControlResultLine> sample = controlPattern.getSample();
		if (sample!=null){
			patternConfig.setResultSample(sample);
			patternConfig.setResultCount(controlPattern.getResultCount());
			OutputInfoFactory.get().getPatternProcess().getPatterns().add(patternConfig);
		}
		controlPattern=null;

	}


	private void executeFromResources(String path) throws IOException, Exception,
	SQLException {

		Collection<String>list = ResourceUtils.getResourceScripts(path);
		for(String file:list){
			if (file.indexOf("/.")>-1 || file.startsWith(".") || !file.endsWith(".xml")){
				continue;
			}
			String patternFile;
			if (file.indexOf("/")>-1){
				patternFile=file;
			}else{
				patternFile=path + "/" + file;
			}
			PatternConfig patternConfig=XmlMapUtil.getPatternConfigFromResource(patternFile);
			if (!excludes.contains(patternConfig.getPatternId())){
				processPattern(patternConfig);
			}
		}
	}

}
