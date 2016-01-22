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
package org.ihtsdo.statistics.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.ihtsdo.statistics.db.importer.ImportManager.TABLE;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportConfig implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String name;
	private ArrayList<TABLE> inputFile;
	private StoredProcedure storedProcedure;
	private ArrayList<OutputFileTableMap> outputFile;
	private ArrayList<OutputDetailFile> outputDetailFile;
	
	public ArrayList<TABLE> getInputFile() {
		return inputFile;
	}
	public void setInputFile(ArrayList<TABLE> inputFile) {
		this.inputFile = inputFile;
	}
	public StoredProcedure getStoredProcedure() {
		return storedProcedure;
	}
	public void setStoredProcedure(StoredProcedure storedProcedure) {
		this.storedProcedure = storedProcedure;
	}
	public ArrayList<OutputFileTableMap> getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(ArrayList<OutputFileTableMap> outputFile) {
		this.outputFile = outputFile;
	}
	public ArrayList<OutputDetailFile> getOutputDetailFile() {
		return outputDetailFile;
	}
	public void setOutputDetailFile(ArrayList<OutputDetailFile> outputDetailFile) {
		this.outputDetailFile = outputDetailFile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
