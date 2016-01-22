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
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class OutputFileTableMap {

	private static final long serialVersionUID = 4L;

	private String file;
	private String reportHeader;
	private ArrayList<SelectTableMap> select;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getReportHeader() {
		return reportHeader;
	}
	public void setReportHeader(String reportHeader) {
		this.reportHeader = reportHeader;
	}
	public ArrayList<SelectTableMap> getSelect() {
		return select;
	}
	public void setSelect(ArrayList<SelectTableMap> select) {
		this.select = select;
	}
}
