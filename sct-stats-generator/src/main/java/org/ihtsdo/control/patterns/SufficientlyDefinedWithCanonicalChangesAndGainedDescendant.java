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
import org.ihtsdo.utils.FileHelper;

import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Class SufficientlyDefinedWithCanonicalChangesAndGainedDescendant.
 */
public class SufficientlyDefinedWithCanonicalChangesAndGainedDescendant extends AControlPattern {

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


		File completedFilesFolder=CurrentFile.get().getCompletedFilesFolder();
		TestConcepts tc=new TestConcepts(completedFilesFolder);

		String canonicalChangesFile=tc.getCanonicalChangesOnSDConceptsFile();

		tc=null;

		BufferedReader br = FileHelper.getReader(new File(canonicalChangesFile));
		HashSet<String> curr=new HashSet<String>();
		br.readLine();
		String[] spl;
		String line;
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			curr.add(spl[0]);
		}
		br.close();
		String tClos_file=null;

		tClos_file=PreviousFile.get().getTransitiveClosureInferredFile();

		br = FileHelper.getReader(CurrentFile.get().getSnapshotConceptFile());
		HashSet<Long> retired=new HashSet<Long>();
		br.readLine();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].compareTo("0")==0){
				retired.add(Long.parseLong(spl[0]));
			}
		}
		br.close();

		br = FileHelper.getReader(tClos_file);
		br.readLine();
		HashMap<Long,HashSet<Long>>prevSubTypes=new HashMap<Long,HashSet<Long>>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (curr.contains(spl[1])){
				Long cid=Long.parseLong(spl[1]);
				Long descId=Long.parseLong(spl[0]);
				if (retired.contains(descId)){
					continue;
				}
				HashSet<Long> list=prevSubTypes.get(cid);
				if (list==null){
					list=new HashSet<Long>();
				}
				list.add(descId);
				prevSubTypes.put(cid, list);
			}
		}
		br.close();
		retired=null;


		tClos_file=CurrentFile.get().getTransitiveClosureInferredFile();
		br=FileHelper.getReader(tClos_file);
		br.readLine();
		HashMap<Long,HashSet<Long>>currSubTypes=new HashMap<Long,HashSet<Long>>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (curr.contains(spl[1])){
				Long cid=Long.parseLong(spl[1]);
				Long descId=Long.parseLong(spl[0]);
				HashSet<Long> list=currSubTypes.get(cid);
				if (list==null){
					list=new HashSet<Long>();
				}
				list.add(descId);
				currSubTypes.put(cid, list);
			}
		}
		br.close();
		gson=new Gson(); 

		sep = System.getProperty("line.separator");

		sample=new ArrayList<ControlResultLine>();
		BufferedWriter bw = FileHelper.getWriter(resultFile);
		bw.append("[");
		boolean first=true;
		ControlResultLine crl=null;

		for (String currCid:curr){
			HashSet<Long> prevList=prevSubTypes.get(Long.parseLong(currCid));
			HashSet<Long> currList=currSubTypes.get(Long.parseLong(currCid));
			int diff=0;
			if (prevList==null && currList!=null){
				diff=currList.size();
			}else if (currList==null){
				continue;
			}else{
				diff=getDiff(prevList, currList);
			}
			if (diff!=0){
				String strDiff= formatDiff(diff);
				crl=new ControlResultLine();
				crl.setChanged(changedConcepts.contains(currCid));
				crl.setNew(newConcepts.contains(currCid));
				crl.setConceptId(currCid);
				crl.setTerm(conceptTerms.get(Long.parseLong(currCid)));
				crl.setSemtag(getSemTag(crl.getTerm()));
				crl.setCurrentEffectiveTime(currentEffTime);
				crl.setPreviousEffectiveTime(previousEffTime);
				crl.setForm("canonical");

				crl.setPatternId(patternId);
				crl.setPreexisting(false);
				crl.setResultId(UUID.randomUUID().toString());
				crl.setCurrent(true);
				crl.setMatchDescription("(" + strDiff + ")Sufficiently defined concept with long canonical form changes that has gained inferred descendants.");
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
	 * Gets the diff.
	 *
	 * @param prevList the prev list
	 * @param currList the curr list
	 * @return the diff
	 */
	private int getDiff(HashSet<Long> prevList, HashSet<Long> currList) {
		int diff=0;
		for (Long descId:currList){
			if (!prevList.contains(descId)){
				diff++;
			}
		}
		return diff;
	}

	/**
	 * Format diff.
	 *
	 * @param diff the diff
	 * @return the string
	 */
	private String formatDiff(int diff) {
		
		String ret="000000" + String.valueOf(diff);
		ret= ret.substring(ret.length()-6);
		return ret;
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
