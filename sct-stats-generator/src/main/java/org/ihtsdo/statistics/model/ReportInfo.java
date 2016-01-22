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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportInfo {
	String name;
	List<String> outputFiles;
	List<String> outputDetailFiles;
	String timeTaken;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
	public List<String> getOutputFiles() {
		if (outputFiles==null){
			outputFiles=new ArrayList<String>();
		}
		return outputFiles;
	}
	public void setOutputFiles(List<String> outputFiles) {
		this.outputFiles = outputFiles;
	}
	public List<String> getOutputDetailFiles() {
		if (outputDetailFiles==null){
			outputDetailFiles=new ArrayList<String>();
		}
		return outputDetailFiles;
	}
	public void setOutputDetailFiles(List<String> outputDetailFiles) {
		this.outputDetailFiles = outputDetailFiles;
	}
}
