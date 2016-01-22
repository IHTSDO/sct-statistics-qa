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

import org.ihtsdo.control.model.PatternProcess;

public class OutputInfo {

	String executionId;
	String status;
	Config config;
	StatisticProcess statisticProcess;
	PatternProcess patternProcess;
	String timeTaken;
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
	public StatisticProcess getStatisticProcess() {
		if (statisticProcess==null){
			statisticProcess=new StatisticProcess();
		}
		return statisticProcess;
	}
	public void setStatisticProcess(StatisticProcess statisticProcess) {
		this.statisticProcess = statisticProcess;
	}
	public PatternProcess getPatternProcess() {
		if (patternProcess==null){
			patternProcess=new PatternProcess();
		}
		return patternProcess;
	}
	public void setPatternProcess(PatternProcess patternProcess) {
		this.patternProcess = patternProcess;
	}
	public String getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
	
}
