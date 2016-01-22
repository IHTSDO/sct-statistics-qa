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
import org.ihtsdo.control.model.ControlResultLine;
import org.ihtsdo.control.model.IControlPattern;
import org.ihtsdo.utils.FileHelper;
import org.ihtsdo.utils.TClosure;

import com.google.gson.Gson;

public class ConceptsGainIntermediatePrimitiveParent implements IControlPattern {

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

		String statedRels;
		String concFile;
		String completeConcFile=null;
		String currFile=null;
		TestConcepts tc;
		File completedFilesFolder=CurrentFile.get().getCompletedFilesFolder();
		if (CurrentFile.get().getReleaseDependenciesFullFolders()!=null){
			statedRels=CurrentFile.get().getCompleteStatedRelationshipSnapshot();
			completeConcFile=CurrentFile.get().getCompleteConceptSnapshot();
		}else{
			statedRels=CurrentFile.get().getSnapshotStatedRelationshipFile();
		}
		tc=new TestConcepts(completedFilesFolder);
		String tClos_file=CurrentFile.get().getTransitiveClosureStatedFile();
		TClosure tClos=new TClosure(statedRels,4,5,7,2);
		if (tClos_file==null){
			tClos_file=new File(completedFilesFolder,"t_closure_stated.txt").getAbsolutePath();
			tClos.toFile(tClos_file);
			CurrentFile.get().setTransitiveClosureStatedFile(tClos_file);
		}
		concFile=CurrentFile.get().getSnapshotConceptFile();
		if (CurrentFile.get().getIntermediatePrimitiveFile()==null){
			
			currFile="current_interm_prim.txt";

			tc.getIntermediatePrimitive( concFile,completeConcFile, currFile, tClos_file);

			currFile=new File(completedFilesFolder,currFile).getAbsolutePath();
			CurrentFile.get().setIntermediatePrimitiveFile(currFile);

			tc=null;
		}else{
			currFile=CurrentFile.get().getIntermediatePrimitiveFile();
		}
		tc=new TestConcepts(completedFilesFolder);
		
		if (PreviousFile.get().getReleaseDependenciesFullFolders()!=null){
			statedRels=PreviousFile.get().getCompleteStatedRelationshipSnapshot();
		}else{
			statedRels=PreviousFile.get().getSnapshotStatedRelationshipFile();
		}
		String gainPrimFile="concept_gain_prim.txt";

		TClosure prevTClos=new TClosure(statedRels,4,5,7,2);

		tc.getConceptGainedPrimitiveIntermediateParent(concFile,currFile,prevTClos,tClos,gainPrimFile);
		tClos=null;
		prevTClos=null;
		gainPrimFile=new File(completedFilesFolder,gainPrimFile).getAbsolutePath();
		tc=null;
		
		getResults(resultTmpFolder, gainPrimFile);
		FileHelper.emptyFolder(resultTmpFolder);
	}



	private void getResults(File resultTmpFolder, String gainPrimFile
			) throws Exception {
		gson=new Gson(); 
		sep = System.getProperty("line.separator");


		BufferedReader br = FileHelper.getReader(new File(gainPrimFile));
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
		if (PreviousFile.get().getTransitiveClosureInferredFile()==null){
			String prevInferredRels=null;
			if (PreviousFile.get().getReleaseDependenciesFullFolders()!=null){
				prevInferredRels=PreviousFile.get().getCompleteRelationshipSnapshot();
			}else{
				prevInferredRels=PreviousFile.get().getSnapshotRelationshipFile();
			}
			TClosure tClos=new TClosure(prevInferredRels,4,5,7,2);
			tClos_file=new File(PreviousFile.get().getCompletedFilesFolder(),"t_closure_inferred.txt").getAbsolutePath();
			tClos.toFile(tClos_file);
			tClos=null;
			PreviousFile.get().setTransitiveClosureInferredFile(tClos_file);
		}else{
			tClos_file=PreviousFile.get().getTransitiveClosureInferredFile();
		}
		 br = FileHelper.getReader(tClos_file);
		br.readLine();
		HashMap<Long,Integer>prevSubTypes=new HashMap<Long,Integer>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (curr.contains(spl[1])){
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


		if (CurrentFile.get().getTransitiveClosureInferredFile()==null){
			String currInferredRels=null;
			if (CurrentFile.get().getReleaseDependenciesFullFolders()!=null){
				currInferredRels=CurrentFile.get().getCompleteRelationshipSnapshot();
			}else{
				currInferredRels=CurrentFile.get().getSnapshotRelationshipFile();
			}
			TClosure tClos=new TClosure(currInferredRels,4,5,7,2);
			tClos_file=new File(CurrentFile.get().getCompletedFilesFolder(),"t_closure_inferred.txt").getAbsolutePath();
			tClos.toFile(tClos_file);
			tClos=null;
			CurrentFile.get().setTransitiveClosureInferredFile(tClos_file);
		}else{
			tClos_file=CurrentFile.get().getTransitiveClosureInferredFile();
		}
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

		sample=new ArrayList<ControlResultLine>();
		BufferedWriter bw = FileHelper.getWriter(resultFile);
		bw.append("[");
		boolean first=true;
		ControlResultLine crl=null;

		for (String currCid:curr){
			Integer prevCount=prevSubTypes.get(Long.parseLong(currCid));
			Integer currCount=currSubTypes.get(Long.parseLong(currCid));
			if ((prevCount!=null && currCount==null)
					|| (prevCount==null && currCount!=null)
					|| (prevCount!=null && currCount!=null && prevCount.intValue()!=currCount.intValue())){
				crl=new ControlResultLine();
				crl.setChanged(changedConcepts.contains(currCid));
				crl.setNew(newConcepts.contains(currCid));
				crl.setConceptId(currCid);
				crl.setTerm(conceptTerms.get(Long.parseLong(currCid)));
				crl.setCurrentEffectiveTime(currentEffTime);
				crl.setPreviousEffectiveTime(previousEffTime);
				crl.setForm("stated");

				crl.setPatternId(patternId);
				crl.setPreexisting(false);
				crl.setResultId(UUID.randomUUID().toString());
				crl.setCurrent(true);
				crl.setMatchDescription("Concepts gained intermediate primitive stated parent have lost or gained inferred subtypes.");
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
