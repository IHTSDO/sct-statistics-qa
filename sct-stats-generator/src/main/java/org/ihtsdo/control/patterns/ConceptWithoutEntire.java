package org.ihtsdo.control.patterns;

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

public class ConceptWithoutEntire extends AControlPattern {

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

		TestConcepts tc;
		String desc=PreviousFile.get().getSnapshotDescriptionFile();

		File completedFilesFolder=CurrentFile.get().getCompletedFilesFolder();
		tc=new TestConcepts(completedFilesFolder);
		String tClos_file=PreviousFile.get().getTransitiveClosureInferredFile();
		HashSet<Long> prev=tc.getConceptsWithEntireInFSN(desc,tClos_file);


		HashSet<Long>prevCps=tc.getCptsWithoutEntire(prev, tClos_file);

		desc=CurrentFile.get().getSnapshotDescriptionFile();
		tClos_file=CurrentFile.get().getTransitiveClosureInferredFile();
		HashSet<Long> curr=tc.getConceptsWithEntireInFSN(desc, tClos_file);


		HashSet<Long>currCps=tc.getCptsWithoutEntire(curr, tClos_file);


		getResults(prevCps, currCps);
	}



	private void getResults(HashSet<Long> prevCps, HashSet<Long> currCps
			) throws Exception {
		gson=new Gson(); 
		sep = System.getProperty("line.separator");


		sample=new ArrayList<ControlResultLine>();
		BufferedWriter bw = FileHelper.getWriter(resultFile);
		bw.append("[");
		boolean first=true;
		ControlResultLine crl=null;

		for (Long currCid:currCps){
			crl = new ControlResultLine();
			crl.setChanged(changedConcepts.contains(currCid));
			crl.setNew(newConcepts.contains(currCid));
			crl.setConceptId(currCid.toString());
			crl.setTerm(conceptTerms.get(currCid));
			crl.setSemtag(getSemTag(crl.getTerm()));
			crl.setCurrentEffectiveTime(currentEffTime);
			crl.setPreviousEffectiveTime(previousEffTime);
			crl.setForm("stated");

			crl.setPatternId(patternId);
			crl.setPreexisting(prevCps.contains(currCid));
			crl.setResultId(UUID.randomUUID().toString());
			crl.setCurrent(true);
			crl.setMatchDescription("concept without 'entire' in its FSN that has a parent (stated or inferred) which has 'entire' in the FSN.");
			if (first){
				first=false;
			}else{
				bw.append(",");
			}
			writeResultLine(bw, crl);


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
