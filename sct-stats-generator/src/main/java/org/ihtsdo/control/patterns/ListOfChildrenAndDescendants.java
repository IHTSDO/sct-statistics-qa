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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.ihtdso.fileprovider.CurrentFile;
import org.ihtsdo.control.model.AControlPattern;
import org.ihtsdo.control.model.ControlResultLine;
import org.ihtsdo.utils.FileHelper;
import org.ihtsdo.utils.I_Constants;

public class ListOfChildrenAndDescendants extends AControlPattern {

	private File resultFile;

	private HashMap<Long, String> conceptTerms;
	private enum Indexes{STATED_CHILDREN,STATED_DESCENDANTS,INFERRED_CHILDREN,INFERRED_DESCENDANTS};

	public void execute() throws Exception {

		String file=CurrentFile.get().getSnapshotConceptFile();
		
		BufferedReader br=FileHelper.getReader(file);
		br.readLine();
		String line;
		String[] spl;
		HashMap<Long,int[]>SDconcepts=new HashMap<Long,int[]>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") && spl[4].equals("900000000000073002" )){
				SDconcepts.put(Long.parseLong(spl[0]),new int[4]);
			}
		}
		br.close();
		String rels;
		if (CurrentFile.get().getReleaseDependenciesFullFolders()!=null){
			rels=CurrentFile.get().getCompleteStatedRelationshipSnapshot();
		}else{
			rels=CurrentFile.get().getSnapshotStatedRelationshipFile();
		}
		br=FileHelper.getReader(rels);
		br.readLine();

		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") 
					&& spl[7].compareTo(I_Constants.ISA)==0){
				Long tgt=Long.parseLong(spl[5]);
				if (SDconcepts.containsKey(tgt)){
					int[] values=SDconcepts.get(tgt);
					values[Indexes.STATED_CHILDREN.ordinal()]++;
					SDconcepts.put(tgt,values);
				}
			}
		}
		br.close();
		
		if (CurrentFile.get().getReleaseDependenciesFullFolders()!=null){
			rels=CurrentFile.get().getCompleteRelationshipSnapshot();
		}else{
			rels=CurrentFile.get().getSnapshotRelationshipFile();
		}
		br=FileHelper.getReader(rels);
		br.readLine();

		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") 
					&& spl[7].compareTo(I_Constants.ISA)==0){
				Long tgt=Long.parseLong(spl[5]);
				if (SDconcepts.containsKey(tgt)){
					int[] values=SDconcepts.get(tgt);
					values[Indexes.INFERRED_CHILDREN.ordinal()]++;
					SDconcepts.put(tgt,values);
				}
			}
		}
		br.close();
		
		String tclos_file;
		tclos_file=CurrentFile.get().getTransitiveClosureStatedFile();
		br=FileHelper.getReader(tclos_file);
		br.readLine();

		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
				Long tgt=Long.parseLong(spl[1]);
				if (SDconcepts.containsKey(tgt)){
					int[] values=SDconcepts.get(tgt);
					values[Indexes.STATED_DESCENDANTS.ordinal()]++;
					SDconcepts.put(tgt,values);
				}
		}
		br.close();
		tclos_file=CurrentFile.get().getTransitiveClosureInferredFile();
		br=FileHelper.getReader(tclos_file);
		br.readLine();

		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
				Long tgt=Long.parseLong(spl[1]);
				if (SDconcepts.containsKey(tgt)){
					int[] values=SDconcepts.get(tgt);
					values[Indexes.INFERRED_DESCENDANTS.ordinal()]++;
					SDconcepts.put(tgt,values);
				}
		}
		br.close();
		
		String outputFile=I_Constants.STATS_OUTPUT_FOLDER + "/" + resultFile.getName();
		BufferedWriter bw = FileHelper.getWriter(outputFile);
		bw.append("id");
		bw.append("\t");
		bw.append("term");
		bw.append("\t");
		bw.append("statedChildren");
		bw.append("\t");
		bw.append("statedDescendants");
		bw.append("\t");
		bw.append("inferredChildren");
		bw.append("\t");
		bw.append("inferredDescendants");
		bw.append("\r\n");
		for (Long cid:SDconcepts.keySet()){
			int[] values=SDconcepts.get(cid);
			bw.append(cid.toString());
			bw.append("\t");
			bw.append(conceptTerms.get(cid));
			bw.append("\t");
			bw.append(String.valueOf(values[Indexes.STATED_CHILDREN.ordinal()]));
			bw.append("\t");
			bw.append(String.valueOf(values[Indexes.STATED_DESCENDANTS.ordinal()]));
			bw.append("\t");
			bw.append(String.valueOf(values[Indexes.INFERRED_CHILDREN.ordinal()]));
			bw.append("\t");
			bw.append(String.valueOf(values[Indexes.INFERRED_DESCENDANTS.ordinal()]));
			bw.append("\r\n");
			
		}
		bw.close();

	}


	public void setConfigFile(File configFile) {

	}

	public List<ControlResultLine> getSample() {
		return null;
	}

	public void setResultFile(File resultFile) {
		this.resultFile=resultFile;

	}

	public void setNewConceptsList(HashSet<String> newConcepts) {
	}

	public void setChangedConceptsList(HashSet<String> changedConcepts) {
	}

	public void setCurrentEffTime(String releaseDate) {
	}

	public void setPreviousEffTime(String previousReleaseDate) {
	}

	public void setPatternId(String patternId) {
	}


	public int getResultCount() {
		return 0;
	}

	public void setConceptTerms(HashMap<Long, String> conceptTerms) {
		this.conceptTerms=conceptTerms;
	}

}
