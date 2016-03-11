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

public class SufficientlyDefinedWithCanonicalChangesAndDoesntLostOrGainedDescendant extends AControlPattern {

	private File resultFile;
	private HashSet<String> newConcepts;
	private HashSet<String> changedConcepts;
	private String currentEffTime;
	private String previousEffTime;
	private String patternId;

	private Gson gson;
	private String sep;
	private List<ControlResultLine> sample;
	private int resultCount;
	private HashMap<Long, String> conceptTerms;

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
		HashMap<Long,Integer>prevSubTypes=new HashMap<Long,Integer>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (curr.contains(spl[1])){
				Long cid=Long.parseLong(spl[1]);
				Long descId=Long.parseLong(spl[0]);
				if (retired.contains(descId)){
					continue;
				}
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
		retired=null;


		tClos_file=CurrentFile.get().getTransitiveClosureInferredFile();
		br=FileHelper.getReader(tClos_file);
		br.readLine();
		HashMap<Long,Integer>currSubTypes=new HashMap<Long,Integer>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (curr.contains(spl[1])){
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
		gson=new Gson(); 

		sep = System.getProperty("line.separator");

		sample=new ArrayList<ControlResultLine>();
		BufferedWriter bw = FileHelper.getWriter(resultFile);
		bw.append("[");
		boolean first=true;
		ControlResultLine crl=null;

		for (String currCid:curr){
			Integer prevCount=prevSubTypes.get(Long.parseLong(currCid));
			Integer currCount=currSubTypes.get(Long.parseLong(currCid));
			if (prevCount==null){
				prevCount=0;
			}
			if (currCount==null){
				currCount=0;
			}
			int diff=currCount-prevCount;
			if (diff==0){
				
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
				crl.setMatchDescription("Sufficiently defined concept with canonical changes does not have lost or gained inferred subtypes.");
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

	private void writeResultLine(BufferedWriter bw, ControlResultLine crl) throws IOException {
		bw.append(gson.toJson(crl).toString());
		bw.append(sep);
		if (sample.size()<10){
			sample.add(crl);
		}
		resultCount++;
	}

	public void setConfigFile(File configFile) {

	}

	public List<ControlResultLine> getSample() {
		return sample;
	}

	public void setResultFile(File resultFile) {
		this.resultFile=resultFile;

	}

	public void setNewConceptsList(HashSet<String> newConcepts) {
		this.newConcepts=newConcepts;		
	}

	public void setChangedConceptsList(HashSet<String> changedConcepts) {
		this.changedConcepts=changedConcepts;
	}

	public void setCurrentEffTime(String releaseDate) {
		this.currentEffTime=releaseDate;
	}

	public void setPreviousEffTime(String previousReleaseDate) {
		this.previousEffTime=previousReleaseDate;
	}

	public void setPatternId(String patternId) {
		this.patternId=patternId;
	}


	public int getResultCount() {
		return resultCount;
	}

	public void setConceptTerms(HashMap<Long, String> conceptTerms) {
		this.conceptTerms=conceptTerms;
	}

}
