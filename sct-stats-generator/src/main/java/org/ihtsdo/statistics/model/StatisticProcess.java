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

public class StatisticProcess {
	List<ReportInfo> reports;
	String timeTaken;
	
	public List<ReportInfo> getReports() {
		if (reports==null){
			reports=new ArrayList<ReportInfo>();
		}
		return reports;
	}
	public void setReports(List<ReportInfo> reports) {
		this.reports = reports;
	}
	public String getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}


}
