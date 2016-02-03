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
import org.ihtsdo.control.model.AControlPattern;
import org.ihtsdo.control.model.ControlResultLine;
import org.ihtsdo.control.model.IControlPattern;
import org.ihtsdo.control.model.MatchObjectRedundantRel;
import org.ihtsdo.control.model.RedundantRelDetailLine;
import org.ihtsdo.control.roletesting.RelationshipTests;
import org.ihtsdo.utils.FileHelper;

import com.google.gson.Gson;

public class RedundancyRole2Group extends AControlPattern {

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
		RelationshipTests rt=new RelationshipTests(resultTmpFolder.getAbsolutePath());

		String statedRels;
		String inferRels;
		String concFile;
		if (CurrentFile.get().getReleaseDependenciesFullFolders()!=null){
			statedRels=CurrentFile.get().getSnapshotStatedRelationshipFile();
			inferRels=CurrentFile.get().getCompleteRelationshipSnapshot();
			concFile=CurrentFile.get().getSnapshotConceptFile();
		}else{
			statedRels=CurrentFile.get().getSnapshotStatedRelationshipFile();
			inferRels=CurrentFile.get().getSnapshotRelationshipFile();
			concFile=CurrentFile.get().getSnapshotConceptFile();
		}
		String currFile="current_r2g_red.txt";
		rt.searchRedundance(statedRels
				,inferRels
				,concFile
				, conceptTerms
				,false
				,RelationshipTests.UNGROUPED2GROUPED
				,currFile);

		if (PreviousFile.get().getReleaseDependenciesFullFolders()!=null){
			statedRels=PreviousFile.get().getSnapshotStatedRelationshipFile();
			inferRels=PreviousFile.get().getCompleteRelationshipSnapshot();
		}else{
			statedRels=PreviousFile.get().getSnapshotStatedRelationshipFile();
			inferRels=PreviousFile.get().getSnapshotRelationshipFile();
		}
		String prevFile="previous_r2g_red.txt";
		rt.searchRedundance(statedRels
				,inferRels
				, null
				,(String)null
				,false
				,RelationshipTests.UNGROUPED2GROUPED
				,prevFile);

		rt=null;
		getResults(resultTmpFolder, prevFile,currFile);
		FileHelper.emptyFolder(resultTmpFolder);
	}

	private void getResults(File resultTmpFolder, String prevFile,
			String currFile) throws IOException {
		gson=new Gson(); 

		sep = System.getProperty("line.separator");

		BufferedReader br = FileHelper.getReader(new File(resultTmpFolder,prevFile));

		HashSet<String> prev=new HashSet<String>();
		br.readLine();
		String line;
		String[] spl;
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl.length>1){
				prev.add(spl[1]);
			}

		}
		br.close();
		sample=new ArrayList<ControlResultLine>();
		BufferedWriter bw = FileHelper.getWriter(resultFile);
		bw.append("[");
		boolean first=true;
		br=FileHelper.getReader(new File(resultTmpFolder,currFile));
		br.readLine();
		boolean firstLine=true;
		boolean emptyLine=true;
		boolean firstLine2=true;
		ControlResultLine crl=null;
		MatchObjectRedundantRel mobj=null;
		RedundantRelDetailLine detLine;
		String relId1="";
		String group2="";
		List<RedundantRelDetailLine>list=null;
		List<RedundantRelDetailLine>list2=null;
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl.length>1){
				if (emptyLine){
					if (firstLine){
						crl=new ControlResultLine();
						mobj=new MatchObjectRedundantRel();
						crl.setChanged(changedConcepts.contains(spl[1]));
						crl.setNew(newConcepts.contains(spl[1]));
						crl.setConceptId(spl[1]);
						crl.setTerm(conceptTerms.get(Long.parseLong(spl[1])));
						crl.setSemtag(getSemTag(crl.getTerm()));
						crl.setCurrentEffectiveTime(currentEffTime);
						crl.setPreviousEffectiveTime(previousEffTime);
						crl.setForm("stated");

						crl.setPatternId(patternId);
						crl.setPreexisting(prev.contains(spl[1]));
						crl.setResultId(UUID.randomUUID().toString());
						crl.setCurrent(true);
						list=new ArrayList<RedundantRelDetailLine>();
						detLine = new RedundantRelDetailLine(line);
						if (detLine.getGroup().equals("0")){
							relId1=detLine.getRelationshipId();
						}else{
							group2=detLine.getGroup();
						}
						list.add(detLine);
						mobj.setGroup1(list);
						firstLine=false;
					}else{
						detLine = new RedundantRelDetailLine(line);
						list.add(detLine);
						mobj.setGroup1(list);

					}

				}else{
					if (firstLine2){
						list2=new ArrayList<RedundantRelDetailLine>();
						detLine = new RedundantRelDetailLine(line);
						if (detLine.getGroup().equals("0")){
							relId1=detLine.getRelationshipId();
						}else{
							group2=detLine.getGroup();
						}
						list2.add(detLine);
						mobj.setGroup2(list2);
						firstLine2=false;
					}else{
						detLine = new RedundantRelDetailLine(line);
						list2.add(detLine);
						mobj.setGroup2(list2);
					}
				}
			}else if (spl[0].indexOf("--")>-1){

				crl.setMatchObject(mobj);
				crl.setMatchDescription("Redundancy between RelationshipId: " + relId1 + ", and group:#"  + group2);
				if (first){
					first=false;
				}else{
					bw.append(",");
				}
				writeResultLine(bw, crl);
				firstLine=true;
				firstLine2=true;
				emptyLine=true;
			}else if (spl[0].trim().equals("")){
				emptyLine=false;
				firstLine2=true;

			}
		}
		bw.append("]");
		bw.close();
		br.close();

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
