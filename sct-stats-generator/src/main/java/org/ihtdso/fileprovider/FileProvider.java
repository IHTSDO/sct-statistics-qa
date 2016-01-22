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
package org.ihtdso.fileprovider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import org.ihtsdo.utils.ConversionSnapshotDelta;
import org.ihtsdo.utils.FileHelper;
import org.ihtsdo.utils.I_Constants;
import org.ihtsdo.utils.TClosure;

public class FileProvider {


	private static final String TEMP_SORTING_FOLDER = "tmpWorking";
	private static final String TEMP_SORTED_FINAL_FOLDER = "tmpSortedFinal";
	private static final String SNAPSHOT_FOLDER = "snapshot";
	private static final String COMPLETED_FILES_FOLDER = "completedFiles";
	private String conceptFile ;
	private String descriptionFile ;
	private String relationshipFile ;
	private String attributeValueFile ;
	private String simpleMapFile ;
	private String associationFile ;
	private String languageFile ;
	private String refsetSimpleFile ;
	private String statedRelationshipFile ;
	private String textDefinitionFile ;
	/* Snapshot files */
	private String snapshotConceptFile ;
	private String snapshotDescriptionFile ;
	private String snapshotRelationshipFile ;
	private String snapshotAttributeValueFile ;
	private String snapshotSimpleMapFile ;
	private String snapshotAssociationFile ;
	private String snapshotLanguageFile ;
	private String snapshotRefsetSimpleFile ;
	private String snapshotStatedRelationshipFile ;
	private String snapshotTextDefinitionFile ;


	private File fullFolder;

	private File snapshotFolder;

	private File tempSortingFolder;

	private File tempSortedFinalfolder;

	private File completedFilesFolder;

	private String releaseDate;
	private String completeStatedRelationshipSnapshot;
	private String completeRelationshipSnapshot;
	private String completeDescriptionSnapshot;
	private HashSet<String> releaseDependenciesFullFolders;
	private File baseFolder;
	private String completeStatedRelationshipFull;
	private String completeRelationshipFull;
	private String completeDescriptionFull;
	private File newConceptFile;
	private File changedConceptFile;
	private String intermediatePrimitiveFile;
	private String completeConceptSnapshot;
	private String completeConceptFull;
	private String transitiveClosureStatedFile;
	private String transitiveClosureInferredFile;
	private HashMap<Long, String> conceptTerms;


	/**
	 * @return the conceptFile
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getConceptFile() throws IOException, Exception {
		if (conceptFile==null){
			conceptFile=FileHelper.getFile(fullFolder, "rf2-concepts", null, null, null, false, false);
		}
		return conceptFile;
	}

	/**
	 * @return the descriptionFile
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getDescriptionFile() throws IOException, Exception {
		if (descriptionFile==null){
			descriptionFile=FileHelper.getFile(fullFolder, "rf2-descriptions", null, null, null, false, false);
		}
		return descriptionFile;
	}

	/**
	 * @return the relationshipFile
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getRelationshipFile() throws IOException, Exception {
		if (relationshipFile==null){
			relationshipFile=FileHelper.getFile(fullFolder, "rf2-relationships", null, null, "stated", false, false);
		}
		return relationshipFile;
	}

	/**
	 * @return the attributeValueFile
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getAttributeValueFile() throws IOException, Exception {
		if (attributeValueFile==null){
			attributeValueFile=FileHelper.getFile(fullFolder, "rf2-attributevalue", null, null, null, false, false);
		}
		return attributeValueFile;
	}

	/**
	 * @return the simpleMapFile
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getSimpleMapFile() throws IOException, Exception {
		if (simpleMapFile==null){
			simpleMapFile=FileHelper.getFile(fullFolder, "rf2-simplemaps", null, null, null, false, false);
		}
		return simpleMapFile;
	}

	/**
	 * @return the associationFile
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getAssociationFile() throws IOException, Exception {
		if (associationFile==null){
			associationFile=FileHelper.getFile(fullFolder, "rf2-association", null, null, null, false, false);
		}
		return associationFile;
	}

	/**
	 * @return the languageFile
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getLanguageFile() throws IOException, Exception {
		if (languageFile==null){
			languageFile=FileHelper.getFile(fullFolder, "rf2-language", null, null, null, false, false);
		}
		return languageFile;
	}

	public String getRefsetSimpleFile() throws IOException, Exception {
		if (refsetSimpleFile==null){
			refsetSimpleFile=FileHelper.getFile(fullFolder, "rf2-simple", null, null, null, false, false);
		}
		return refsetSimpleFile;
	}

	public String getStatedRelationshipFile() throws IOException, Exception {
		if (statedRelationshipFile==null){
			statedRelationshipFile=FileHelper.getFile(fullFolder, "rf2-relationships", null, "stated", null, false, false);
		}
		return statedRelationshipFile;
	}

	public String getTextDefinitionFile() throws IOException, Exception {
		if (textDefinitionFile==null){
			textDefinitionFile=FileHelper.getFile(fullFolder, "rf2-textDefinition", null, null, null, false, false);
		}
		return textDefinitionFile;
	}



	public String getSnapshotConceptFile() throws IOException, Exception {
		if (snapshotConceptFile==null){
			snapshotConceptFile=FileHelper.getFile(snapshotFolder, "rf2-concepts", null, null, null, false, false);
			if (snapshotConceptFile==null){
				getConceptFile();
				snapshotConceptFile=new File(snapshotFolder,new File(conceptFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(conceptFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotConceptFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotConceptFile;
	}

	public String getSnapshotDescriptionFile() throws IOException, Exception {
		if (snapshotDescriptionFile==null){
			snapshotDescriptionFile=FileHelper.getFile(snapshotFolder, "rf2-descriptions", null, null, null, false, false);
			if (snapshotDescriptionFile==null){
				getDescriptionFile();
				snapshotDescriptionFile=new File(snapshotFolder,new File(descriptionFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(descriptionFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotDescriptionFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotDescriptionFile;
	}

	public String getSnapshotRelationshipFile() throws IOException, Exception {
		if (snapshotRelationshipFile==null){
			snapshotRelationshipFile=FileHelper.getFile(snapshotFolder, "rf2-relationships", null, null, "stated", false, false);
			if (snapshotRelationshipFile==null){
				getRelationshipFile();
				snapshotRelationshipFile=new File(snapshotFolder,new File(relationshipFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(relationshipFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotRelationshipFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotRelationshipFile;
	}

	public String getSnapshotAttributeValueFile() throws IOException, Exception {
		if (snapshotAttributeValueFile==null){
			snapshotAttributeValueFile=FileHelper.getFile(snapshotFolder, "rf2-attributevalue", null, null, null, false, false);
			if (snapshotAttributeValueFile==null){
				getAttributeValueFile();
				snapshotAttributeValueFile=new File(snapshotFolder,new File(attributeValueFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(attributeValueFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotAttributeValueFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotAttributeValueFile;
	}

	public String getSnapshotSimpleMapFile() throws IOException, Exception {
		if (snapshotSimpleMapFile==null){
			snapshotSimpleMapFile=FileHelper.getFile(snapshotFolder, "rf2-simplemaps", null, null, null, false, false);
			if (snapshotSimpleMapFile==null){
				getSimpleMapFile();
				snapshotSimpleMapFile=new File(snapshotFolder,new File(simpleMapFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(simpleMapFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotSimpleMapFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotSimpleMapFile;
	}

	public String getSnapshotAssociationFile() throws IOException, Exception {
		if (snapshotAssociationFile==null){
			snapshotAssociationFile=FileHelper.getFile(snapshotFolder, "rf2-association", null, null, null, false, false);
			if (snapshotAssociationFile==null){
				getAssociationFile();
				snapshotAssociationFile=new File(snapshotFolder,new File(associationFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(associationFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotAssociationFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotAssociationFile;
	}

	public String getSnapshotLanguageFile() throws IOException, Exception {
		if (snapshotLanguageFile==null){
			snapshotLanguageFile=FileHelper.getFile(snapshotFolder, "rf2-language", null, null, null, false, false);
			if (snapshotLanguageFile==null){
				getLanguageFile();
				snapshotLanguageFile=new File(snapshotFolder,new File(languageFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(languageFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotLanguageFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotLanguageFile;
	}

	public String getSnapshotRefsetSimpleFile() throws IOException, Exception {
		if (snapshotRefsetSimpleFile==null){
			snapshotRefsetSimpleFile=FileHelper.getFile(snapshotFolder, "rf2-simple", null, null, null, false, false);
			if (snapshotRefsetSimpleFile==null){
				getRefsetSimpleFile();
				snapshotRefsetSimpleFile=new File(snapshotFolder,new File(refsetSimpleFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(refsetSimpleFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotRefsetSimpleFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotRefsetSimpleFile;
	}

	public String getSnapshotStatedRelationshipFile() throws IOException, Exception {
		if (snapshotStatedRelationshipFile==null){
			snapshotStatedRelationshipFile=FileHelper.getFile(snapshotFolder, "rf2-relationships", null, "stated", null, false, false);
			if (snapshotStatedRelationshipFile==null){
				getStatedRelationshipFile();
				snapshotStatedRelationshipFile=new File(snapshotFolder,new File(statedRelationshipFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(statedRelationshipFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotStatedRelationshipFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotStatedRelationshipFile;
	}

	public String getSnapshotTextDefinitionFile() throws IOException, Exception {
		if (snapshotTextDefinitionFile==null){
			snapshotTextDefinitionFile=FileHelper.getFile(snapshotFolder, "rf2-textDefinition", null, null, null, false, false);
			if (snapshotTextDefinitionFile==null){
				getTextDefinitionFile();
				snapshotTextDefinitionFile=new File(snapshotFolder,new File(textDefinitionFile).getName().toLowerCase().replace("full","snapshot")).getAbsolutePath();
				ConversionSnapshotDelta.snapshotFile(new File(textDefinitionFile), tempSortingFolder, tempSortedFinalfolder, new File(snapshotTextDefinitionFile), releaseDate, new int[]{0,1}, 0, 1, null, null, null);
			}
		}
		return snapshotTextDefinitionFile;
	}

	public File getFullFolder() {
		return fullFolder;
	}

	public void setFullFolder(File fullFolder) {
		this.fullFolder = fullFolder;
	}

	public File getSnapshotFolder() {
		return snapshotFolder;
	}

	public void setSnapshotFolder(File snapshotFolder) {
		this.snapshotFolder = snapshotFolder;
	}

	public File getTempSortingFolder() {
		return tempSortingFolder;
	}

	public void setTempSortingFolder(File tempSortingFolder) {
		this.tempSortingFolder = tempSortingFolder;
	}

	public File getTempSortedFinalfolder() {
		return tempSortedFinalfolder;
	}

	public void setTempSortedFinalfolder(File tempSortedFinalfolder) {
		this.tempSortedFinalfolder = tempSortedFinalfolder;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public FileProvider(File sourceFullFolder,File baseFolder,HashSet<String>releaseDependenciesFullFolders, String releaseDate){
		setFullFolder(sourceFullFolder);
		setBaseFolder(baseFolder);
		setReleaseDate(releaseDate);
		setReleaseDependenciesFullFolders(releaseDependenciesFullFolders);
		createFolders();

	}

	private void setBaseFolder(File baseFolder) {
		this.baseFolder=baseFolder;
	}

	private void createFolders() {

		if (!baseFolder.exists()){
			baseFolder.mkdirs();
		}else{
			FileHelper.emptyFolder(baseFolder);
		}

		tempSortingFolder=new File(baseFolder , TEMP_SORTING_FOLDER);
		if (!tempSortingFolder.exists()){
			tempSortingFolder.mkdirs();
		}else{
			FileHelper.emptyFolder(tempSortingFolder);
		}
		tempSortedFinalfolder=new File(baseFolder , TEMP_SORTED_FINAL_FOLDER);
		if (!tempSortedFinalfolder.exists()){
			tempSortedFinalfolder.mkdirs();
		}else{
			FileHelper.emptyFolder(tempSortedFinalfolder);
		}	

		snapshotFolder=new File(baseFolder , SNAPSHOT_FOLDER);
		if (!snapshotFolder.exists()){
			snapshotFolder.mkdirs();
		}else{
			FileHelper.emptyFolder(snapshotFolder);
		}
		completedFilesFolder=new File(baseFolder , COMPLETED_FILES_FOLDER);
		if (!completedFilesFolder.exists()){
			completedFilesFolder.mkdirs();
		}else{
			FileHelper.emptyFolder(completedFilesFolder);
		}

	}

	public String getCompleteDescriptionSnapshot() throws IOException, Exception {
		if (completeDescriptionSnapshot==null){
			getCompleteDescriptionFull();
			if (completeDescriptionFull!=null){
				completeDescriptionSnapshot=completedFilesFolder.getAbsolutePath() + "/" + "sct2_completeDescriptionSnapshot.txt";
				ConversionSnapshotDelta.snapshotFile(new File(completeDescriptionFull), tempSortingFolder, tempSortedFinalfolder, new File(completeDescriptionSnapshot), releaseDate, new int[]{0,1}, 0, 1,null,null,null);
			}
		}
		return completeDescriptionSnapshot;
	}
	public String getCompleteDescriptionFull() throws IOException, Exception {
		if (completeDescriptionFull==null){
			if (releaseDependenciesFullFolders!=null){
				HashSet<String> tmpFiles=new HashSet<String>();
				for (String fullFolder:releaseDependenciesFullFolders){
					String tmpFile=FileHelper.getFile(new File(fullFolder), "rf2-descriptions", null, null, null, false, false);
					tmpFiles.add(tmpFile);
				}
				String descFull=getDescriptionFile();
				if (descFull!=null){
					tmpFiles.add(descFull);
				}
				completeDescriptionFull=completedFilesFolder.getAbsolutePath() + "/" + "sct2_completeDescriptionFull.txt";
				FileHelper.completeFileFromArray(tmpFiles,  completeDescriptionFull);
			}
		}
		return completeDescriptionFull;
	}

	public void setCompleteDescriptionSnapshot(
			String completeDescriptionSnapshot) {
		this.completeDescriptionSnapshot = completeDescriptionSnapshot;
	}

	public HashSet<String> getReleaseDependenciesFullFolders() {
		return releaseDependenciesFullFolders;
	}

	public void setReleaseDependenciesFullFolders(
			HashSet<String> releaseDependenciesFullFolders) {
		this.releaseDependenciesFullFolders = releaseDependenciesFullFolders;
	}

	public File getCompletedFilesFolder() {
		return completedFilesFolder;
	}

	public void setCompletedFilesFolder(File completedFilesFolder) {
		this.completedFilesFolder = completedFilesFolder;
	}

	public String getCompleteRelationshipSnapshot() throws IOException, Exception {
		if (completeRelationshipSnapshot==null){
			getCompleteRelationshipFull();
			if (completeRelationshipFull!=null){
				completeRelationshipSnapshot=completedFilesFolder.getAbsolutePath() + "/" + "sct2_completeRelationshipSnapshot.txt";
				ConversionSnapshotDelta.snapshotFile(new File(completeRelationshipFull), tempSortingFolder, tempSortedFinalfolder, new File(completeRelationshipSnapshot), releaseDate, new int[]{0,1}, 0, 1,null,null,null);
			}
		}
		return completeRelationshipSnapshot;
	}

	public String getCompleteRelationshipFull() throws IOException, Exception {
		if (completeRelationshipFull==null){
			if (releaseDependenciesFullFolders!=null){
				HashSet<String> tmpFiles=new HashSet<String>();
				for (String fullFolder:releaseDependenciesFullFolders){
					String tmpFile=FileHelper.getFile(new File(fullFolder), "rf2-relationships", null, null, "stated", false, false);
					tmpFiles.add(tmpFile);
				}
				String relsFull=getRelationshipFile();
				if (relsFull!=null){
					tmpFiles.add(relsFull);
				}
				completeRelationshipFull=completedFilesFolder.getAbsolutePath() + "/" + "sct2_completeRelationshipFull.txt";
				FileHelper.completeFileFromArray(tmpFiles,  completeRelationshipFull);
			}
		}
		return completeRelationshipFull;
	}

	public void setCompleteRelationshipSnapshot(
			String completeRelationshipSnapshot) {
		this.completeRelationshipSnapshot = completeRelationshipSnapshot;
	}

	public String getCompleteStatedRelationshipSnapshot() throws IOException, Exception {
		if (completeStatedRelationshipSnapshot==null){
			getCompleteStatedRelationshipFull();
			if (completeStatedRelationshipFull!=null){
				completeStatedRelationshipSnapshot=completedFilesFolder.getAbsolutePath() + "/" + "sct2_completeStatedRelationshipSnapshot.txt";
				ConversionSnapshotDelta.snapshotFile(new File(completeStatedRelationshipFull), tempSortingFolder, tempSortedFinalfolder, new File(completeStatedRelationshipSnapshot), releaseDate, new int[]{0,1}, 0, 1,null,null,null);
			}
		}
		return completeStatedRelationshipSnapshot;
	}

	public String getCompleteStatedRelationshipFull() throws IOException, Exception {
		if (completeStatedRelationshipFull==null){
			if (releaseDependenciesFullFolders!=null){
				HashSet<String> tmpFiles=new HashSet<String>();
				for (String fullFolder:releaseDependenciesFullFolders){
					String tmpFile=FileHelper.getFile(new File(fullFolder), "rf2-relationships", null, "stated", null, false, false);
					tmpFiles.add(tmpFile);
				}
				String relsFull=getStatedRelationshipFile();
				if (relsFull!=null){
					tmpFiles.add(relsFull);
				}
				completeStatedRelationshipFull=completedFilesFolder.getAbsolutePath() + "/" + "sct2_completeStatedRelationshipFull.txt";
				FileHelper.completeFileFromArray(tmpFiles,  completeStatedRelationshipFull);
			}
		}
		return completeStatedRelationshipFull;
	}

	public void setCompleteStatedRelationshipSnapshot(
			String completeStatedRelationshipSnapshot) {
		this.completeStatedRelationshipSnapshot = completeStatedRelationshipSnapshot;
	}

	public String getFullFileByPattern(String patternTag,
			String fileNameMustHave, String fileNameDoesntMustHave) throws IOException, Exception {
		if (patternTag.equals("rf2-concepts")){

			return getConceptFile();
		}
		if (patternTag.equals("rf2-descriptions")){

			return getDescriptionFile();
		}
		if (patternTag.equals("rf2-relationships")){
			if (fileNameMustHave!=null && fileNameMustHave.toLowerCase().equals("stated")){
				return getStatedRelationshipFile();
			}
			if (fileNameDoesntMustHave!=null && fileNameDoesntMustHave.toLowerCase().equals("stated")){
				return getRelationshipFile();
			}
		}
		if (patternTag.equals("rf2-attributevalue")){

			return getAttributeValueFile();
		}
		if (patternTag.equals("rf2-simplemaps")){

			return getSimpleMapFile();
		}
		if (patternTag.equals("rf2-association")){

			return getAssociationFile();
		}
		if (patternTag.equals("rf2-language")){

			return getLanguageFile();
		}
		if (patternTag.equals("rf2-simple")){

			return getRefsetSimpleFile();
		}
		if (patternTag.equals("rf2-textDefinition")){

			return getTextDefinitionFile();
		}

		return null;
	}

	public File getNewConceptFile() {
		return newConceptFile;
	}

	public void setNewConceptFile(File newConceptFile) {
		this.newConceptFile = newConceptFile;
	}

	public File getChangedConceptFile() {
		return changedConceptFile;
	}

	public void setChangedConceptFile(File changedConceptFile) {
		this.changedConceptFile = changedConceptFile;
	}

	public void setIntermediatePrimitiveFile(String intermediatePrimitiveFile) {
		this.intermediatePrimitiveFile=intermediatePrimitiveFile;
	}

	public String getIntermediatePrimitiveFile() {
		return intermediatePrimitiveFile;
	}

	public String getCompleteConceptSnapshot() throws IOException, Exception {
		if (completeConceptSnapshot==null){
			getCompleteConceptFull();
			if (completeConceptFull!=null){
				completeConceptSnapshot=completedFilesFolder.getAbsolutePath() + "/" + "sct2_completeConceptSnapshot.txt";
				ConversionSnapshotDelta.snapshotFile(new File(completeConceptFull), tempSortingFolder, tempSortedFinalfolder, new File(completeConceptSnapshot), releaseDate, new int[]{0,1}, 0, 1,null,null,null);
			}
		}
		return completeConceptSnapshot;
	}

	private String getCompleteConceptFull() throws IOException, Exception {
		if (completeConceptFull==null){
			if (releaseDependenciesFullFolders!=null){
				HashSet<String> tmpFiles=new HashSet<String>();
				for (String fullFolder:releaseDependenciesFullFolders){
					String tmpFile=FileHelper.getFile(new File(fullFolder), "rf2-concepts", null, null, null, false, false);
					tmpFiles.add(tmpFile);
				}
				String concFull=getConceptFile();
				if (concFull!=null){
					tmpFiles.add(concFull);
				}
				completeConceptFull=completedFilesFolder.getAbsolutePath() + "/" + "sct2_completeConceptFull.txt";
				FileHelper.completeFileFromArray(tmpFiles,  completeConceptFull);
			}
		}
		return completeConceptFull;		
	}

	public void setTransitiveClosureStatedFile(String transitiveClosureStatedFile) {
		this.transitiveClosureStatedFile=transitiveClosureStatedFile;
	}

	public String getTransitiveClosureStatedFile() throws IOException, Exception {
		if (transitiveClosureStatedFile==null){
			String statedRels;
			if (getReleaseDependenciesFullFolders()!=null){
				statedRels=getCompleteRelationshipSnapshot();
			}else{
				statedRels=getSnapshotRelationshipFile();
			}
			TClosure tClos=new TClosure(statedRels,4,5,7,2);
			transitiveClosureStatedFile = new File(completedFilesFolder,"t_closure_stated.txt").getAbsolutePath();
			tClos.toFile(transitiveClosureStatedFile);
			tClos=null;
		}
		return transitiveClosureStatedFile;
	}

	public void setTransitiveClosureInferredFile(String transitiveClosureInferredFile) {
		this.transitiveClosureInferredFile=transitiveClosureInferredFile;
	}

	public String getTransitiveClosureInferredFile() throws IOException, Exception {
		if (transitiveClosureInferredFile==null){
			String inferRels;
			if (getReleaseDependenciesFullFolders()!=null){
				inferRels=getCompleteRelationshipSnapshot();
			}else{
				inferRels=getSnapshotRelationshipFile();
			}
			TClosure tClos=new TClosure(inferRels,4,5,7,2);
			transitiveClosureInferredFile = new File(completedFilesFolder,"t_closure_inferred.txt").getAbsolutePath();
			tClos.toFile(transitiveClosureInferredFile);
			tClos=null;
		}
		return transitiveClosureInferredFile;
	}

	public HashMap<Long, String> getConceptTerms() throws IOException, Exception {
		if (conceptTerms==null){
			conceptTerms=new HashMap<Long, String>();

			getSnapshotDescriptionFile();
			if (snapshotDescriptionFile!=null){
				setTerms(snapshotDescriptionFile);
			}
			if (getReleaseDependenciesFullFolders()!=null){
				TreeMap<Integer,String> tmpFiles=new TreeMap<Integer,String>();
				int cont=0;
				for (String fullFolder:getReleaseDependenciesFullFolders()){
					String tmpFile=FileHelper.getFile(new File(fullFolder), "rf2-descriptions", null, null, null, false, false);
					HashSet<String>modules=FileHelper.getModules(tmpFile);

					if (modules.contains(I_Constants.CORE_MODULE)){
						if (modules.size()>1){
							tmpFiles.put(Integer.MAX_VALUE-cont, tmpFile);
						}else {
							tmpFiles.put(Integer.MAX_VALUE, tmpFile);
						}
					}else{
						tmpFiles.put(cont, tmpFile);
					}
					cont++;
				}
				for (Integer index:tmpFiles.keySet()){
					String file=tmpFiles.get(index);
					setTerms(file);
				}
			}

		}
		return conceptTerms;
	}

	private void setTerms(String file) throws UnsupportedEncodingException,
			FileNotFoundException, IOException, NumberFormatException {
//		System.out.println("desc file:" + file);
		BufferedReader br=FileHelper.getReader(file);
		br.readLine();
		String line;
		String[] spl;
		while ((line=br.readLine())!=null){
			spl=line.split("\t",-1);
			if (spl[2].equals("1") 
					&& spl[6].equals(I_Constants.FSN) && !conceptTerms.containsKey(Long.parseLong(spl[4]))){

				conceptTerms.put(Long.parseLong(spl[4]),spl[7]);
			}
		}
		br.close();
	}

}
