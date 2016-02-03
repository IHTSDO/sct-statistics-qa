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
package org.ihtsdo.statistics.db.importer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.ihtdso.fileprovider.CurrentFile;
import org.ihtsdo.statistics.db.setup.DbSetup;
import org.ihtsdo.statistics.model.ReportConfig;
import org.ihtsdo.utils.ConversionSnapshotDelta;
import org.ihtsdo.utils.FileHelper;
import org.ihtsdo.utils.FileTest;
import org.ihtsdo.utils.I_Constants;
import org.ihtsdo.utils.ResourceUtils;
import org.ihtsdo.utils.TClosure;


public class ImportManager {
	boolean rootDesc;
	boolean concepts;
	boolean descriptions;
	boolean descriptions_pre;
	boolean relationships;
	boolean statedRels;
	boolean textDefins;
	boolean attributeValues;
	boolean associations;
	boolean same_associations;
	boolean simpleRefset;
	boolean simpleMaps;
	boolean language;
	boolean tClosureInferred;
	boolean tClosureStated;
	boolean concepts_pre;
	boolean relationships_pre;
	boolean statedRels_pre;
	boolean tClosureStated_pre;
	public static HashMap<String,String> params;
	public enum TABLE{
		CONCEPTS("s_concepts", "rf2-concepts",null,null,null,false,"id,active,definitionStatusId",null,null),
		CONCEPTS_PREVIOUS("s_concepts_pre", "rf2-concepts",null,null,null,true,"id,active,definitionStatusId",null,null),
		DESCRIPTIONS("s_descriptions", "rf2-descriptions",null,null,null,false,"effectiveTime,active,conceptId,typeId,term",null,null),
		DESCRIPTIONS_PREVIOUS("s_descriptions_pre", "rf2-descriptions",null,null,null,true,"effectiveTime,active,conceptId,typeId,term",null,null),
		RELATIONSHIPS("s_relationships","rf2-relationships",null,null,"stated",false,"sourceId,effectiveTime,active",null,null),
		RELATIONSHIPS_PREVIOUS("s_relationships_pre",null,null,null,"stated",true,null,null,null),
		STATEDRELS("s_statedrels", "rf2-relationships",null,"stated",null,false,"sourceId,effectiveTime,active",null,null),
		STATEDROOTDESC("s_statedrootdesc", "rf2-statedrootdesc",null,"stated",null,false,"sourceId,term",new Integer[]{2,5,7},new String[]{"1","138875005","116680003"}),
		STATEDRELS_PREVIOUS("s_statedrels_pre", null,null,"stated",null,true,null,null,null),
		TEXTDEFINS("s_textdefin",null,null,null,null,false,null,null,null),
		ATTRIBUTEVALUES("s_attributevalues","rf2-attributevalue",null,null,null,false,"refsetId,referencedComponentId,valueId",new Integer[]{2,4},new String[]{"1","900000000000489007"}),
		ASSOCIATIONS("s_associations",null,null,null,null,false,null,null,null),
		SAME_AS_ASSOCIATIONS("s_same_associations","rf2-association",null,null,null,false,"referencedComponentId,targetComponentId",new Integer[]{2,4},new String[]{"1","900000000000527005"}),
		SIMPLEREFSET("s_simplerefsets",null,null,null,null,false,null,null,null),
		SIMPLEMAPS("s_simplemaps",null,null,null,null,false,null,null,null),
		LANGUAGE("s_languages",null,null,null,null,false,null,null,null),
		TCLOSURESTATED("s_tclosure_stated","transitive-closure",null,"stated","pre",false,null,null,null),
		TCLOSURESTATED_PREVIOUS("s_tclosure_stated_pre","transitive-closure",null,"stated",null,true,null,null,null),
		TCLOSUREINFERRED("s_tclosure_inferred",null,null,null,null,false,null,null,null);

		private String tableName;
		private String patternTag;
		private String defaultFolder;
		private String fileNameMustHave;
		private String fileNameDoesntMustHave;
		private boolean isPrevious;
		private String fields;
		private Integer[] fieldFilter;
		private String[] fieldFilterValue;

		TABLE (String tableName,
				String patternTag,
				String defaultFolder, 
				String fileNameMustHave,
				String fileNameDoesntMustHave,
				boolean isPrevious,
				String fields,
				Integer[] fieldFilter,
				String[] fieldFilterValue){
			this.tableName=tableName;
			this.patternTag=patternTag;
			this.defaultFolder=defaultFolder;
			this.fileNameMustHave=fileNameMustHave;
			this.fileNameDoesntMustHave=fileNameDoesntMustHave;
			this.isPrevious=isPrevious;
			this.fields=fields;
			this.fieldFilter=fieldFilter;
			this.fieldFilterValue=fieldFilterValue;
		}

		public String getTableName() {
			return tableName;
		}

		public String getPatternTag() {
			return patternTag;
		}

		public void setPatternTag(String patternTag) {
			this.patternTag = patternTag;
		}

		public String getDefaultFolder() {
			return defaultFolder;
		}

		public void setDefaultFolder(String defaultFolder) {
			this.defaultFolder = defaultFolder;
		}

		public String getFileNameMustHave() {
			return fileNameMustHave;
		}

		public void setFileNameMustHave(String fileNameMustHave) {
			this.fileNameMustHave = fileNameMustHave;
		}

		public String getFileNameDoesntMustHave() {
			return fileNameDoesntMustHave;
		}

		public void setFileNameDoesntMustHave(String fileNameDoesntMustHave) {
			this.fileNameDoesntMustHave = fileNameDoesntMustHave;
		}

		public boolean isPrevious() {
			return isPrevious;
		}

		public void setPrevious(boolean isPrevious) {
			this.isPrevious = isPrevious;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getFields() {
			return fields;
		}

		public void setFields(String fields) {
			this.fields = fields;
		}

		public Integer[] getFieldFilter() {
			return fieldFilter;
		}

		public void setFieldFilter(Integer[] fieldFilter) {
			this.fieldFilter = fieldFilter;
		}

		public String[] getFieldFilterValue() {
			return fieldFilterValue;
		}

		public void setFieldFilterValue(String[] fieldFilterValue) {
			this.fieldFilterValue = fieldFilterValue;
		}
	};

	Importer importer;
	File dataFolder;
	private File configFile;
	private String releaseDate;
	private String previousReleaseDate;
	private File reducedSnapshotFolder;
	private File previousReducedSnapshotFolder;
	private Connection connection;
	private boolean changedPreviousDate;
	private boolean changedDate;
	private File sortFolderTmp;
	private File sortedFolderTmp;
	private boolean releaseDependencies;

	private static Logger logger;
	public ImportManager(Connection con, File file, boolean changedDate, boolean changedPreviousDate) {
		logger = Logger.getLogger("org.ihtsdo.statistics.db.importer.ImportManager");
		this.connection=con;
		this.configFile=file;
		this.changedDate=changedDate;
		this.changedPreviousDate=changedPreviousDate;
	}

	private void createFolders() throws Exception {

		dataFolder=new File(I_Constants.REPO_FOLDER);
		if (!dataFolder.exists()){
			dataFolder.mkdirs();
		}
		sortFolderTmp=CurrentFile.get().getTempSortingFolder();
		sortedFolderTmp=CurrentFile.get().getTempSortedFinalfolder();

	}

	public void execute() throws IOException, Exception {
		logger.info("Starting import manager process");

		createFolders();
		importer=new Importer();
		if(!existingTables()){
			DbSetup dbSetup=new DbSetup(connection);
			dbSetup.execute();
			dbSetup=null;
		}
		XMLConfiguration xmlConfig;
		try {
			xmlConfig=new XMLConfiguration(configFile);
		} catch (ConfigurationException e) {
			logger.error("ClassificationRunner - Error happened getting params configFile." + e.getMessage());
			throw e;
		}

		releaseDependencies=CurrentFile.get().getReleaseDependenciesFullFolders()!=null;
		this.releaseDate = xmlConfig.getString("releaseDate");
		this.previousReleaseDate = xmlConfig.getString("previousReleaseDate");

		this.reducedSnapshotFolder = new File("reducedSnapshotFolder");
		if (!reducedSnapshotFolder.exists()){
			reducedSnapshotFolder.mkdirs();
		}
		this.previousReducedSnapshotFolder = new File("previousReducedSnapshotFolder");
		if (!previousReducedSnapshotFolder.exists()){
			previousReducedSnapshotFolder.mkdirs();
		}


		List<HierarchicalConfiguration> fields = xmlConfig
				.configurationsAt("reports.reportDescriptor");

		if (fields!=null){
			for (HierarchicalConfiguration sub : fields) {

				String report=sub.getString("filename");
				String value=sub.getString("execute");

				if (value.toLowerCase().equals("true")){
					logger.info("Getting config for report " + report);
					ReportConfig reportCfg=ResourceUtils.getReportConfig(report);

					for (TABLE table:reportCfg.getInputFile()){
						switch (table){
						case STATEDROOTDESC:
							if (rootDesc){
								continue;
							}
							rootDesc=true;
							break;
						case CONCEPTS:
							if (concepts){
								continue;
							}
							concepts=true;
							break;

						case DESCRIPTIONS:
							if (descriptions){
								continue;
							}
							descriptions=true;
							break;
						case DESCRIPTIONS_PREVIOUS:
							if (descriptions_pre){
								continue;
							}
							descriptions_pre=true;
							break;

						case RELATIONSHIPS:
							if (relationships){
								continue;
							}
							relationships=true;
							break;


						case STATEDRELS :
							if (statedRels){
								continue;
							}
							statedRels=true;
							break;


						case TCLOSUREINFERRED :
							if (tClosureInferred){
								continue;
							}
							tClosureInferred=true;
							break;

						case TCLOSURESTATED :
							if (tClosureStated){
								continue;
							}
							tClosureStated=true;
							break;

						case CONCEPTS_PREVIOUS :
							if (concepts_pre){
								continue;
							}
							concepts_pre=true;
							break;
						case RELATIONSHIPS_PREVIOUS :
							if (relationships_pre){
								continue;
							}
							relationships_pre=true;
							break;
						case STATEDRELS_PREVIOUS :
							if (statedRels_pre){
								continue;
							}
							statedRels_pre=true;
							break;

						case TCLOSURESTATED_PREVIOUS :
							if (tClosureStated_pre){
								continue;
							}
							tClosureStated_pre=true;
							break;
						case SAME_AS_ASSOCIATIONS :
							if (same_associations){
								continue;
							}
							same_associations=true;
							break;
						}
						ImportRf2Table(table);
					}
					System.out.println(report + " " + value);
				}
			}
		}
		logger.info("Updating date to " + releaseDate);
		saveNewDate(I_Constants.RELEASE_DATE, releaseDate);

		logger.info("Updating previous date to " + previousReleaseDate);
		saveNewDate(I_Constants.PREVIOUS_RELEASE_DATE, previousReleaseDate);

		fields = xmlConfig
				.configurationsAt("sp_params.param");

		params=new HashMap<String,String>();
		if (fields!=null){
			for (HierarchicalConfiguration sub : fields) {
				String paramName=sub.getString("name");
				String value=sub.getString("value");
				params.put(paramName, value);
			}
		}
		logger.info("End of import manager process");
	}


	private boolean existingTables() {
		for(TABLE table:TABLE.values()){
			if (table.getPatternTag()!=null && !importer.tableExists(table, connection)){
				logger.info("Table " + table.getTableName() + " doesn't exist in db. Setup will create it.");

				return false;
			}
		}
		return true;

	}


	/**
	 * @param table
	 * @throws IOException
	 * @throws Exception
	 */
	void ImportRf2Table( TABLE table) throws IOException, Exception{
		String newChk;
		String oldChk;
		File targetFolder;
		boolean recreate=false;
		String snapshotDate;
		if (table.isPrevious()){
			if (changedPreviousDate){
				recreate=true;
			}
			targetFolder=previousReducedSnapshotFolder;
			snapshotDate=previousReleaseDate;
		}else{
			if (changedDate){
				recreate=true;
			}
			targetFolder=reducedSnapshotFolder;
			snapshotDate=releaseDate;
		}
		String txt ="";
		logger.info("Evaluation if table " + table.getTableName() + " must be laoded.");
		if (!recreate){

			txt = FileHelper.getFile( targetFolder, table.getPatternTag(),table.getDefaultFolder(),table.getFileNameMustHave(),table.getFileNameDoesntMustHave(),table.isPrevious(),true);
			if (txt==null){
				txt=getSnapshot(table,targetFolder,snapshotDate);
				oldChk="";
			}else{
				oldChk= getOldCheck(table);
			}
			newChk=FileTest.getMD5Checksum(txt);
			if ( !newChk.equals(oldChk)){
				logger.info("Different checksum for file " + txt);

				importer.loadFileToDatabase(new File(txt), table, connection, null);
				saveNewCheck(table,newChk);
			}else{
				logger.info("Same data detected for table " + table.getTableName() + ". Skipping import process for it");
			}
		}else{
			logger.info("Regenerating table " + table.getTableName() );

			txt=getSnapshot(table,targetFolder,snapshotDate);
			newChk=FileTest.getMD5Checksum(txt);
			importer.loadFileToDatabase(new File(txt), table, connection, null);
			saveNewCheck(table,newChk);
		}
	}

	private void saveNewCheck(TABLE table, String newChk) throws IOException {
		if (!dataFolder.exists()){
			dataFolder.mkdirs();
		}
		File file=new File(dataFolder, table.name() + ".dat");
		BufferedWriter bw = getWriter(file.getAbsolutePath());
		bw.append(newChk);
		bw.append("\r\n");
		bw.close();
		bw=null;
	}
	private void saveNewDate(String fileName, String date) throws IOException {
		if (!dataFolder.exists()){
			dataFolder.mkdirs();
		}
		File file=new File(dataFolder, fileName + ".dat");
		BufferedWriter bw = getWriter(file.getAbsolutePath());
		bw.append(date);
		bw.append("\r\n");
		bw.close();
		bw=null;
	}
	private BufferedWriter getWriter(String outFile) throws UnsupportedEncodingException, FileNotFoundException {

		FileOutputStream tfos = new FileOutputStream( outFile);
		OutputStreamWriter tfosw = new OutputStreamWriter(tfos,"UTF-8");
		return new BufferedWriter(tfosw);

	}
	private String getSnapshot(TABLE table,File targetFolder,String snapshotDate) throws Exception {
		logger.info("Getting snapshot for " + table.getTableName() );
		String[] outputFields=null;
		if (table.getPatternTag().equals("transitive-closure")){
			String txtTClos=table.getTableName() + ".txt";
			String isasFile=null;
			String isasFullFile;
			if (releaseDependencies){
				isasFullFile=CurrentFile.get().getCompleteStatedRelationshipFull();
			}else{

				isasFullFile=CurrentFile.get().getStatedRelationshipFile();
			}
			File isasTmpSnapshot=new File(targetFolder,"tmp_" + ((table.getFileNameMustHave()==null)? "":table.getFileNameMustHave()) + "relationships" + (table.isPrevious()?"_pre":"") + ".txt");
			File isasSnapshot=new File(targetFolder,((table.getFileNameMustHave()==null)? "":table.getFileNameMustHave()) + "relationships" + (table.isPrevious()?"_pre":"") + ".txt");
			logger.info("Getting snapshot from file " + isasFullFile);

			outputFields=new String[4];
			for (int i=0;i<outputFields.length;i++){
				outputFields[0]="sourceId";
				outputFields[1]="destinationId";
				outputFields[2]="relationshipGroup";
				outputFields[3]="typeId";
			}
			ConversionSnapshotDelta.snapshotFile(new File(isasFullFile), sortFolderTmp, sortedFolderTmp, isasTmpSnapshot, snapshotDate, new int[]{0,1}, 0, 1,null,null,null);
			ConversionSnapshotDelta.snapshotFile(isasTmpSnapshot, sortFolderTmp, sortedFolderTmp, isasSnapshot, snapshotDate, new int[]{0,1}, 0, 1,new Integer[]{2},new String[]{"1"},outputFields);
			isasFile=isasSnapshot.getAbsolutePath();
			TClosure tcf=new TClosure(isasFile,0,1,3,null);

			logger.info("Creating transitive closure from file " + isasFile);
			File tmpFile=new File(isasFile);
			txtTClos= tmpFile.getParent() + "/" + txtTClos;
			tcf.toFileFirstLevelHierarchy(txtTClos);
			
//			isasSnapshot.delete();
			isasTmpSnapshot.delete();
			tcf=null;
			return txtTClos;
		} 
		String txt ="";
		if (table.getPatternTag().equals("rf2-statedrootdesc")){
			logger.info("Getting Top Level");
			if (!releaseDependencies){
				txt=CurrentFile.get().getStatedRelationshipFile();
			}else{
				txt = CurrentFile.get().getCompleteStatedRelationshipFull();
			}
		}else{
			txt = CurrentFile.get().getFullFileByPattern(table.getPatternTag(),table.getFileNameMustHave(),table.getFileNameDoesntMustHave());
		}
		if (txt==null || txt.equals("")){
			throw new Exception("Full source file not found for pattern:" + table.getPatternTag() );
		}
		File txtFile=new File(targetFolder,table.getTableName() + ".txt");
		logger.info("Getting snapshot from file " + txt);
		if (table.getFields()!=null){
			outputFields=table.getFields().split(",");
			for (int i=0;i<outputFields.length;i++){
				outputFields[i]=outputFields[i].trim();
			}
		}
		if (table.getFieldFilter()!=null){
			File fileTxt=new File(txt);
			File txtTmpFile=new File(fileTxt.getParentFile(),"tmp_"+ fileTxt.getName());
			ConversionSnapshotDelta.snapshotFile(fileTxt, sortFolderTmp, sortedFolderTmp, txtTmpFile, snapshotDate, new int[]{0,1}, 0, 1, null, null, null);
			ConversionSnapshotDelta.snapshotFile(txtTmpFile, sortFolderTmp, sortedFolderTmp, txtFile, snapshotDate, new int[]{0,1}, 0, 1,table.getFieldFilter(),table.getFieldFilterValue(),outputFields);
			txtTmpFile.delete();
		}else{
			ConversionSnapshotDelta.snapshotFile(new File(txt), sortFolderTmp, sortedFolderTmp, txtFile, snapshotDate, new int[]{0,1}, 0, 1,null,null,outputFields);
		}
		if (table.getPatternTag().equals("rf2-statedrootdesc")){
			logger.info("Getting Top Level terms");
			getDescriptionsForTopLevel(txtFile);
		}
		return txtFile.getAbsolutePath();
	}

	private void getDescriptionsForTopLevel(File txtFile) throws Exception {
		String descFile;		
		if (releaseDependencies){
			descFile=CurrentFile.get().getCompleteDescriptionSnapshot();
		}else{
			descFile= CurrentFile.get().getSnapshotDescriptionFile();
		}
		BufferedReader br=FileHelper.getReader(txtFile);
		String header =br.readLine();
		String line;
		String[] spl;
		HashMap<String,String> mapTopLevel=new HashMap<String,String>();
		while((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			mapTopLevel.put(spl[0], "");
		}
		br.close();
		br=FileHelper.getReader(descFile);
		br.readLine();
		while((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") 
					&& mapTopLevel.containsKey(spl[4])){
				String tmp=mapTopLevel.get(spl[4]);
				if (tmp.equals("") ){
					mapTopLevel.put(spl[4], spl[7]);
				}else if(spl[6].equals("900000000000003001")){
					mapTopLevel.put(spl[4], spl[7]);
				}
			}
		}

		br.close();
		BufferedWriter bw = FileHelper.getWriter(txtFile);
		bw.append(header);
		bw.append("\r\n");
		for (String key:mapTopLevel.keySet()){
			bw.append(key);
			bw.append("\t");
			bw.append(mapTopLevel.get(key));
			bw.append("\r\n");

		}
		bw.close();
	}

	private String getOldCheck(TABLE table) throws IOException {

		if (!dataFolder.exists()){
			dataFolder.mkdirs();
		}
		File file=new File(dataFolder, table.name() + ".dat");
		if (file.exists()){
			FileInputStream rfis = new FileInputStream(file);
			InputStreamReader risr = new InputStreamReader(rfis,"UTF-8");
			BufferedReader rbr = new BufferedReader(risr);
			String ret=rbr.readLine();
			rbr.close();
			rbr=null;
			return ret;
		}
		return "";
	}

}
