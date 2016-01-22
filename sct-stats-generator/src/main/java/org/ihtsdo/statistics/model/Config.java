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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.ihtsdo.control.model.PatternExecutions;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {
	String releaseFullFolder;
	String releaseDate;
	String previousReleaseDate;
	ReportList reports;
	SpParamList sp_params;
	String createDetailReports;
	ReleaseDependencies releaseDependencies;
	PatternExecutions patternExecutions;
	public String getReleaseFullFolder() {
		return releaseFullFolder;
	}
	public void setReleaseFullFolder(String releaseFullFolder) {
		this.releaseFullFolder = releaseFullFolder;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getPreviousReleaseDate() {
		return previousReleaseDate;
	}
	public void setPreviousReleaseDate(String previousReleaseDate) {
		this.previousReleaseDate = previousReleaseDate;
	}
	public String getCreateDetailReports() {
		return createDetailReports;
	}
	public void setCreateDetailReports(String createDetailReports) {
		this.createDetailReports = createDetailReports;
	}
	public ReleaseDependencies getReleaseDependencies() {
		return releaseDependencies;
	}
	public void setReleaseDependencies(ReleaseDependencies releaseDependencies) {
		this.releaseDependencies = releaseDependencies;
	}
	public PatternExecutions getPatternExecutions() {
		return patternExecutions;
	}
	public void setPatternExecutions(PatternExecutions patternExecutions) {
		this.patternExecutions = patternExecutions;
	}
	public ReportList getReports() {
		return reports;
	}
	public void setReports(ReportList reports) {
		this.reports = reports;
	}
	public SpParamList getSp_params() {
		return sp_params;
	}
	public void setSp_params(SpParamList sp_params) {
		this.sp_params = sp_params;
	}
	
}
