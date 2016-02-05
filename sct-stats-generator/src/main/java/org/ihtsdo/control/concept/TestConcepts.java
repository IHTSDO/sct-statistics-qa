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
package org.ihtsdo.control.concept;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import org.ihtsdo.utils.FileHelper;
import org.ihtsdo.utils.I_Constants;
import org.ihtsdo.utils.TClosure;

public class TestConcepts {

	private File outputFolder;

	public TestConcepts(File outputFolder) {
		this.outputFolder=outputFolder;
	}

	public void getIntermediatePrimitive( String concFile,
			String completeConcFile, String currFile, String tClos_file) throws FileNotFoundException, IOException,
			UnsupportedEncodingException, NumberFormatException {

		if (completeConcFile==null){
			completeConcFile=concFile;
		}
		BufferedReader br = FileHelper.getReader(completeConcFile);

		br.readLine();
		String[] spl;
		String line;

		HashSet<Long>SDconcepts=new HashSet<Long>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") && spl[4].equals("900000000000073002" )){
				SDconcepts.add(Long.parseLong(spl[0]));
			}
		}
		br.close();

		br = FileHelper.getReader(concFile);

		br.readLine();

		HashSet<Long>Pconcepts=new HashSet<Long>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") && spl[4].equals("900000000000074008" )){
				Pconcepts.add(Long.parseLong(spl[0]));
			}
		}
		br.close();
		
		br = FileHelper.getReader(tClos_file);
		br.readLine();
		Long desc;
		Long asc;
		HashSet<Long> checkAsDesc = new HashSet<Long>();
		HashSet<Long> checkAsPar = new HashSet<Long>();
		HashSet<Long> intermPrim=new HashSet<Long>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			desc=Long.parseLong(spl[0]);
			asc=Long.parseLong(spl[1]);
			if (Pconcepts.contains(desc) && SDconcepts.contains(asc)){
				if (checkAsDesc.contains(desc)){
					intermPrim.add(desc);
				}else{
					checkAsPar.add(desc);
				}
			}else if(Pconcepts.contains(asc) && SDconcepts.contains(desc)){
				if (checkAsPar.contains(asc)){
					intermPrim.add(asc);
				}else{
					checkAsDesc.add(asc);
				}
			}
		}
		br.close();
		Pconcepts=null;
		SDconcepts=null;
		checkAsDesc=null;
		checkAsPar=null;
		
		BufferedWriter bw = FileHelper.getWriter(new File(outputFolder,currFile));
		bw.append("id");
		bw.append("\r\n");
		
		for (Long key:intermPrim){

			bw.append(key.toString());
			bw.append("\r\n");
		}
		
		bw.close();
		
		tClos_file=null;

	}

	public void getConceptGainedPrimitiveIntermediateParent(String concFile,
			String currFile, TClosure prevTClos, TClosure tClos,
			String gainPrimFile) throws IOException {

		BufferedReader br=FileHelper.getReader(currFile);
		br.readLine();
		String line;
		String[] spl;
		HashSet<Long> curr=new HashSet<Long>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			curr.add(Long.parseLong(spl[0]));
		}
		br.close();

		BufferedWriter bw = FileHelper.getWriter(new File(outputFolder,gainPrimFile));
		bw.append("id");
		bw.append("\r\n");

		br=FileHelper.getReader(concFile);
		br.readLine();
		while ((line=br.readLine())!=null){

			spl=line.split("\t",-1);
			Long cid=Long.parseLong(spl[0]);
			for (Long intPrim: curr){
				Boolean isParent=tClos.isParent(intPrim,cid);
				if (isParent!=null && isParent){
					isParent=prevTClos.isParent(intPrim,cid);
					if (isParent!=null && isParent==false){
						bw.append(cid.toString());
						bw.append("\r\n");
						
					}
				}
			}
		}
		br.close();
		bw.close();
	}

	public void getSDConceptsWithoutStatedAtt(String rels,
			String concept, String outputFile) throws NumberFormatException, IOException {


		BufferedReader br=FileHelper.getReader(rels);
		br.readLine();
		String line;
		String[] spl;
		HashSet<Long> curr=new HashSet<Long>();
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("0") || spl[7].equals(I_Constants.ISA)){
				continue;
			}
			curr.add(Long.parseLong(spl[4]));
		}
		br.close();

		BufferedWriter bw = FileHelper.getWriter(new File(outputFolder,outputFile));
		bw.append("id");
		bw.append("\r\n");

		br=FileHelper.getReader(concept);
		br.readLine();
		while ((line=br.readLine())!=null){

			spl=line.split("\t",-1);
			if (spl[4].equals(I_Constants.SUFFICIENTLY_DEFINED) && !curr.contains(Long.parseLong(spl[0]))){
				bw.append(spl[0]);
				bw.append("\r\n");
			}
		}
		bw.close();
		br.close();
	}

	public HashSet<Long> getConceptsWithEntireInFSN(String descriptionFile, String tClos_file) throws IOException {
		HashSet<Long> cpts=new HashSet<Long>();
		HashSet<Long> bscpts=new HashSet<Long>();
		HashSet<Long> excpts=new HashSet<Long>();

		BufferedReader br=FileHelper.getReader(tClos_file);
		br.readLine();
		String line;
		String[] spl;
		Long desc;
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			desc=Long.parseLong(spl[0]);
			
			if (spl[1].equals("91723000") ){
				bscpts.add(desc);
			}else if (spl[1].equals("4421005") ){
				excpts.add(desc);
			}
		}
		br.close();
		br=FileHelper.getReader(descriptionFile);
		br.readLine();
		Long cid;
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			cid=Long.parseLong(spl[4]);
			if (bscpts.contains(cid) && !excpts.contains(cid) && spl[2].equals("1") && spl[6].equals("900000000000003001") && spl[7].toLowerCase().contains("entire")){
				cpts.add(Long.parseLong(spl[4]));
			}
		}
		br.close();
		br=null;
		bscpts=null;
		excpts=null;
		return cpts;
	}

	public HashSet<Long> getCptsWithoutEntire(HashSet<Long> entireCpt,
			String tClos_file) throws IOException {
		HashSet<Long> cpts=new HashSet<Long>();
		
		BufferedReader br=FileHelper.getReader(tClos_file);
		br.readLine();
		String line;
		String[] spl;
		Long desc;
		Long asc;
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			desc=Long.parseLong(spl[0]);
			asc=Long.parseLong(spl[1]);
			
			if (entireCpt.contains(asc) & !entireCpt.contains(desc)){
				cpts.add(desc);
			}
		}
		br.close();
		br=null;
		return cpts;
	}

}
