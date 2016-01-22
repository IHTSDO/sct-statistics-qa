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
import org.ihtsdo.control.model.ControlResultLine;
import org.ihtsdo.control.model.IControlPattern;
import org.ihtsdo.utils.FileHelper;
import org.ihtsdo.utils.I_Constants;

import com.google.gson.Gson;

public class RelationshipTypeTargetPairsNotInferredView implements IControlPattern {

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

		String inferRels=PreviousFile.get().getSnapshotRelationshipFile();
		String statedRels=PreviousFile.get().getSnapshotStatedRelationshipFile();
		BufferedReader br = FileHelper.getReader(inferRels);
		
		br.readLine();
		String line;
		String[] spl;
		HashSet<String> roles=new HashSet<String>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") && spl[7].compareTo(I_Constants.ISA)!=0){
				String key=spl[4]+ "-" + spl[7] + ":" + spl[5];
				roles.add(key);
			}
		}
		br.close();

		HashSet<String>preEx=new HashSet<String>();
		br = FileHelper.getReader(statedRels);
		
		br.readLine();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") && spl[7].compareTo(I_Constants.ISA)!=0){
				String key=spl[4]+ "-" + spl[7] + ":" + spl[5];
				if (!roles.contains(key)){
					preEx.add(key);
				}
			}
		}
		br.close();
		
		inferRels=CurrentFile.get().getSnapshotRelationshipFile();
		statedRels=CurrentFile.get().getSnapshotStatedRelationshipFile();
		
		br = FileHelper.getReader(inferRels);
		
		br.readLine();
		roles=new HashSet<String>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") && spl[7].compareTo(I_Constants.ISA)!=0){
				String key=spl[4]+ "-" + spl[7] + ":" + spl[5];
				roles.add(key);
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
		
		br = FileHelper.getReader(statedRels);
		
		br.readLine();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") && spl[7].compareTo(I_Constants.ISA)!=0){
				String key=spl[4]+ "-" + spl[7] + ":" + spl[5];
				if (!roles.contains(key)){
					String pair=key.substring(key.indexOf("-")+1);
					crl=new ControlResultLine();
					crl.setChanged(changedConcepts.contains(spl[4]));
					crl.setNew(newConcepts.contains(spl[4]));
					crl.setConceptId(spl[4]);
					crl.setTerm(conceptTerms.get(Long.parseLong(spl[4])));
					crl.setCurrentEffectiveTime(currentEffTime);
					crl.setPreviousEffectiveTime(previousEffTime);
					crl.setForm("inferred");

					crl.setPatternId(patternId);
					crl.setPreexisting(preEx.contains(key));
					crl.setResultId(UUID.randomUUID().toString());
					crl.setCurrent(true);
					crl.setMatchDescription("RelationshipType-target pairs that are stated but not inferred, pair:" + pair);
					if (first){
						first=false;
					}else{
						bw.append(",");
					}
					writeResultLine(bw, crl);
					
				}
			}
		}
		br.close();
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
		// TODO Auto-generated method stub

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
