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
package org.ihtsdo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;


public class TClosure {

	HashMap<Long,HashSet<Long>>parentHier;
	HashMap<Long,HashSet<Long>>childrenHier;
	private long ISARELATIONSHIPTYPEID=116680003l;
	private String ROOT_CONCEPT = "138875005";
	String rf2Rels;
	private HashSet<Long> hControl;
	private int sourceIndex;
	private int typeIndex;
	private int destinationIndex;
	private Integer activeIndex;
	public TClosure(String rf2Rels,int sourceIndex, int destinationIndex, int typeIndex,Integer activeIndex) throws FileNotFoundException, IOException{
		parentHier=new HashMap<Long,HashSet<Long>>();
		this.sourceIndex=sourceIndex;
		this.destinationIndex=destinationIndex;
		this.typeIndex=typeIndex;
		this.activeIndex=activeIndex;
		childrenHier=new HashMap<Long,HashSet<Long>>();
		this.rf2Rels=rf2Rels;
		loadIsas();
	}

	private void loadIsas() throws IOException, FileNotFoundException {
		System.out.println("Starting Isas Relationships from: " + rf2Rels);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rf2Rels), "UTF8"));
		try {
			String line = br.readLine();
			line = br.readLine(); // Skip header
			int count = 0;
			while (line != null) {
				if (line.isEmpty()) {
					continue;
				}
				String[] columns = line.split("\\t");
				if (Long.parseLong(columns[typeIndex])==ISARELATIONSHIPTYPEID
						&& !columns[sourceIndex].equals(ROOT_CONCEPT)){
					if (activeIndex!=null){
						if (columns[activeIndex].compareTo("1")==0){
							addRel(Long.parseLong(columns[destinationIndex]),Long.parseLong(columns[sourceIndex]));
						}
					}else{
						addRel(Long.parseLong(columns[destinationIndex]),Long.parseLong(columns[sourceIndex]));
					}
					count++;
					if (count % 100000 == 0) {
						System.out.print(".");
					}
				}
				line = br.readLine();
			}
			System.out.println(".");
			System.out.println("Parent isas Relationships loaded = " + parentHier.size());
			System.out.println("Children isas Relationships loaded = " + childrenHier.size());
		} finally {
			br.close();
		}		
	}
	public void addRel(Long parent, Long child){
		if (parent==child){
			System.out.println("same child and parent: " + child);
			return;
		}
		HashSet<Long> parentList=parentHier.get(child);
		if (parentList==null){
			parentList=new HashSet<Long>();
		}
		parentList.add(parent);
		parentHier.put(child, parentList);

		HashSet<Long> childrenList=childrenHier.get(parent);
		if (childrenList==null){
			childrenList=new HashSet<Long>();
		}
		childrenList.add(child);
		childrenHier.put(parent, childrenList);
	}

	public boolean isAncestorOf(Long ancestor,Long descendant){


		HashSet<Long>parent=parentHier.get(descendant);
		if (parent==null){
			return false;
		}
		if (parent.contains(ancestor)){
			return true;
		}
		for(Long par:parent){
			if (isAncestorOf(ancestor,par)){
				return true;
			}
		}
		return false;
	}

	public HashSet<Long> getParent(Long conceptId) {
		return parentHier.get(conceptId);
	}

	public HashSet<Long> getChildren(Long conceptId) {
		return childrenHier.get(conceptId);
	}


	public void toFile(String outputFile) throws IOException{
		BufferedWriter bw = getWriter(outputFile);
		addTClosureFileHeader(bw);
		writeHierarchy(bw);
		bw.close();
	}
	public void toFileFirstLevelHierarchy(String outputFile) throws IOException{
		BufferedWriter bw = getWriter(outputFile);
		addTClosureFileHeader(bw);
		writeFirstLevelHierarchy(bw);
		bw.close();
	}

	private void addTClosureFileHeader(BufferedWriter bw) throws IOException {
		bw.append("descendant");
		bw.append("\t");
		bw.append("ancestor");
		bw.append("\r\n");		
	}

	private BufferedWriter getWriter(String outFile) throws UnsupportedEncodingException, FileNotFoundException {

		FileOutputStream tfos = new FileOutputStream( outFile);
		OutputStreamWriter tfosw = new OutputStreamWriter(tfos,"UTF-8");
		return new BufferedWriter(tfosw);

	}

	private void writeHierarchy(BufferedWriter bw) throws IOException{

		for (Long child: parentHier.keySet()){
			hControl=new HashSet<Long>();
			writeParents(bw,child,child);
			hControl=null;
		}

	}
	private void writeFirstLevelHierarchy(BufferedWriter bw) throws IOException{

		for (Long child: childrenHier.get(Long.parseLong(ROOT_CONCEPT))){
			hControl=new HashSet<Long>();
			writeDescendants(bw,child,child);
			hControl=null;
		}

	}

	private void writeParents(BufferedWriter bw, Long child,Long descendant) throws IOException {

		HashSet<Long> parents=parentHier.get(child);
		if (parents==null){
			return;
		}

		for(Long par:parents){
			if (!hControl.contains(par)){
				hControl.add(par);
				bw.append(descendant.toString());
				bw.append("\t");
				bw.append(par.toString());
				bw.append("\r\n");
				writeParents(bw, par,descendant);
			}
		}		
	}
	private void writeDescendants(BufferedWriter bw, Long child,Long ancestor) throws IOException {

		HashSet<Long> children=childrenHier.get(child);
		if (children==null){
			return;
		}

		for(Long chi:children){
			if (!hControl.contains(chi)){
				hControl.add(chi);
				bw.append(chi.toString());
				bw.append("\t");
				bw.append(ancestor.toString());
				bw.append("\r\n");
				writeDescendants(bw, chi,ancestor);
			}
		}		
	}

	public boolean isIntermediate(long concept, HashSet<Long> sDconcepts) {
		for (Long desc:sDconcepts){
			if (isAncestorOf(concept,desc)){
				for (Long anc:sDconcepts){
					if (isAncestorOf(anc, concept)){
						return true;
					}
				}
			}
		}
		return false;
	}

	public Boolean isParent(Long parent, Long cid) {
		HashSet<Long> parents = parentHier.get(cid);
		if (parents!=null){
			return parents.contains(parent);
		}
		return null;
	}

}
