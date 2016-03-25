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
package org.ihtsdo.control.patterns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.ihtdso.fileprovider.CurrentFile;
import org.ihtdso.fileprovider.PreviousFile;
import org.ihtsdo.control.concept.TestConcepts;
import org.ihtsdo.control.model.AControlPattern;
import org.ihtsdo.control.model.ControlResultLine;
import org.ihtsdo.control.model.IControlPattern;
import org.ihtsdo.utils.FileHelper;

import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Class IntermediatePrimitiveGainedSubtypes.
 */
public class IntermediatePrimitiveGainedSubtypes extends AControlPattern {

	/** The result file. */
	private File resultFile;
	
	/** The new concepts. */
	private HashSet<String> newConcepts;
	
	/** The changed concepts. */
	private HashSet<String> changedConcepts;
	
	/** The current eff time. */
	private String currentEffTime;
	
	/** The previous eff time. */
	private String previousEffTime;
	
	/** The pattern id. */
	private String patternId;

	/** The gson. */
	private Gson gson;
	
	/** The sep. */
	private String sep;
	
	/** The sample. */
	private List<ControlResultLine> sample;
	
	/** The result count. */
	private int resultCount;
	
	/** The concept terms. */
	private HashMap<Long, String> conceptTerms;

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#execute()
	 */
	public void execute() throws Exception {

		resultCount=0;

		File resultTmpFolder=new File(resultFile.getParentFile() + "/tmp");
		if (!resultTmpFolder.exists()){
			resultTmpFolder.mkdirs();
		}else{
			FileHelper.emptyFolder(resultTmpFolder);
		}

		String concFile;
		String completeConcFile=null;
		String currFile=null;
		String prevFile=null;
		if (CurrentFile.get().getIntermediatePrimitiveFile()==null){
			File completedFilesFolder=CurrentFile.get().getCompletedFilesFolder();
			TestConcepts tc=new TestConcepts(completedFilesFolder);

			if (CurrentFile.get().getReleaseDependenciesFullFolders()!=null){
				completeConcFile=CurrentFile.get().getCompleteConceptSnapshot();
			}
			concFile=CurrentFile.get().getSnapshotConceptFile();
			currFile="current_interm_prim.txt";
			
			String tClos_file=CurrentFile.get().getTransitiveClosureStatedFile();
			
			tc.getIntermediatePrimitive( concFile,completeConcFile, currFile, tClos_file);

			currFile=new File(completedFilesFolder,currFile).getAbsolutePath();
			CurrentFile.get().setIntermediatePrimitiveFile(currFile);

			tc=null;
		}else{
			currFile=CurrentFile.get().getIntermediatePrimitiveFile();
		}
		if (PreviousFile.get().getIntermediatePrimitiveFile()==null){
			File completedFilesFolder=PreviousFile.get().getCompletedFilesFolder();
			completeConcFile=null;
			TestConcepts tc=new TestConcepts(completedFilesFolder);

			if (PreviousFile.get().getReleaseDependenciesFullFolders()!=null){
				completeConcFile=PreviousFile.get().getCompleteConceptSnapshot();
			}
			concFile=PreviousFile.get().getSnapshotConceptFile();
			prevFile="previous_interm_prim.txt";

			String tClos_file=PreviousFile.get().getTransitiveClosureStatedFile();

			tc.getIntermediatePrimitive(concFile, completeConcFile, prevFile, tClos_file);
			prevFile=new File(completedFilesFolder,prevFile).getAbsolutePath();
			PreviousFile.get().setIntermediatePrimitiveFile(prevFile);
			tc=null;
		}else{
			prevFile=PreviousFile.get().getIntermediatePrimitiveFile();
		}
		getResults(resultTmpFolder, prevFile,currFile);
		FileHelper.emptyFolder(resultTmpFolder);
	}



	/**
	 * Gets the results.
	 *
	 * @param resultTmpFolder the result tmp folder
	 * @param prevFile the prev file
	 * @param currFile the curr file
	 * @return the results
	 * @throws Exception the exception
	 */
	private void getResults(File resultTmpFolder, String prevFile,
			String currFile) throws Exception {
		gson=new Gson(); 

		sep = System.getProperty("line.separator");

		BufferedReader br = FileHelper.getReader(new File(prevFile));

		HashSet<String> prev=new HashSet<String>();
		br.readLine();
		String line;
		String[] spl;
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			prev.add(spl[0]);
		}
		br.close();

		br = FileHelper.getReader(new File(currFile));
		HashSet<String> curr=new HashSet<String>();
		br.readLine();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			curr.add(spl[0]);
		}
		br.close();

		String tClos_file=PreviousFile.get().getTransitiveClosureInferredFile();
		br=FileHelper.getReader(tClos_file);
		br.readLine();
		HashMap<Long,Integer>prevSubTypes=new HashMap<Long,Integer>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (prev.contains(spl[1]) && curr.contains(spl[1])){
				Long cid=Long.parseLong(spl[1]);
				Integer count=prevSubTypes.get(cid);
				if (count==null){
					count=1;
				}else{
					count++;
				}
				prevSubTypes.put(cid, count);
			}
		}
		br.close();

		tClos_file=CurrentFile.get().getTransitiveClosureInferredFile();
		br=FileHelper.getReader(tClos_file);
		br.readLine();
		HashMap<Long,Integer>currSubTypes=new HashMap<Long,Integer>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (prev.contains(spl[1]) && curr.contains(spl[1])){
				Long cid=Long.parseLong(spl[1]);
				Integer count=currSubTypes.get(cid);
				if (count==null){
					count=1;
				}else{
					count++;
				}
				currSubTypes.put(cid, count);
			}
		}
		br.close();

		sample=new ArrayList<ControlResultLine>();
		BufferedWriter bw = FileHelper.getWriter(resultFile);
		bw.append("[");
		boolean first=true;
		ControlResultLine crl=null;

		for (Long currCid:currSubTypes.keySet()){
			Integer prevCount=prevSubTypes.get(currCid);
			Integer currCount=currSubTypes.get(currCid);
			if (prevCount==null || prevCount.intValue()<currCount.intValue()){
				crl=new ControlResultLine();
				crl.setChanged(changedConcepts.contains(currCid.toString()));
				crl.setNew(newConcepts.contains(currCid.toString()));
				crl.setConceptId(currCid.toString());
				crl.setTerm(conceptTerms.get(currCid));
				crl.setSemtag(getSemTag(crl.getTerm()));
				crl.setCurrentEffectiveTime(currentEffTime);
				crl.setPreviousEffectiveTime(previousEffTime);
				crl.setForm("stated");

				crl.setPatternId(patternId);
				crl.setPreexisting(prev.contains(currCid.toString()));
				crl.setResultId(UUID.randomUUID().toString());
				crl.setCurrent(true);
				crl.setMatchDescription("Intermediate primitive have gained subtype.");
				if (first){
					first=false;
				}else{
					bw.append(",");
				}
				writeResultLine(bw, crl);

			}
		}
		bw.append("]");
		bw.close();

	}

	/**
	 * Write result line.
	 *
	 * @param bw the bw
	 * @param crl the crl
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void writeResultLine(BufferedWriter bw, ControlResultLine crl) throws IOException {
		bw.append(gson.toJson(crl).toString());
		bw.append(sep);
		if (sample.size()<10){
			sample.add(crl);
		}
		resultCount++;
	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#setConfigFile(java.io.File)
	 */
	public void setConfigFile(File configFile) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#getSample()
	 */
	public List<ControlResultLine> getSample() {
		return sample;
	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#setResultFile(java.io.File)
	 */
	public void setResultFile(File resultFile) {
		this.resultFile=resultFile;

	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#setNewConceptsList(java.util.HashSet)
	 */
	public void setNewConceptsList(HashSet<String> newConcepts) {
		this.newConcepts=newConcepts;		
	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#setChangedConceptsList(java.util.HashSet)
	 */
	public void setChangedConceptsList(HashSet<String> changedConcepts) {
		this.changedConcepts=changedConcepts;
	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#setCurrentEffTime(java.lang.String)
	 */
	public void setCurrentEffTime(String releaseDate) {
		this.currentEffTime=releaseDate;
	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#setPreviousEffTime(java.lang.String)
	 */
	public void setPreviousEffTime(String previousReleaseDate) {
		this.previousEffTime=previousReleaseDate;
	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#setPatternId(java.lang.String)
	 */
	public void setPatternId(String patternId) {
		this.patternId=patternId;
	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#getResultCount()
	 */
	public int getResultCount() {
		return resultCount;
	}

	/* (non-Javadoc)
	 * @see org.ihtsdo.control.model.IControlPattern#setConceptTerms(java.util.HashMap)
	 */
	public void setConceptTerms(HashMap<Long, String> conceptTerms) {
		this.conceptTerms=conceptTerms;
	}
}
