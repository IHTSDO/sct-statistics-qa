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
package org.ihtsdo.sampling;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ControlResultLine {

	String resultId;
	String previousEffectiveTime;
	String currentEffectiveTime;
	String executionTime;
	String patternId;
	String conceptId;
	String term;
	String semtag;
	String matchDescription;
	Object matchObject;
	String form;
	boolean preexisting;
	boolean current;
	boolean changed;
	boolean isNew;
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public String getPreviousEffectiveTime() {
		return previousEffectiveTime;
	}
	public void setPreviousEffectiveTime(String previousEffectiveTime) {
		this.previousEffectiveTime = previousEffectiveTime;
	}
	public String getCurrentEffectiveTime() {
		return currentEffectiveTime;
	}
	public void setCurrentEffectiveTime(String currentEffectiveTime) {
		this.currentEffectiveTime = currentEffectiveTime;
	}
	public String getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}
	public String getPatternId() {
		return patternId;
	}
	public void setPatternId(String patternId) {
		this.patternId = patternId;
	}
	public String getConceptId() {
		return conceptId;
	}
	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}
	public String getMatchDescription() {
		return matchDescription;
	}
	public void setMatchDescription(String matchDescription) {
		this.matchDescription = matchDescription;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public boolean isPreexisting() {
		return preexisting;
	}
	public void setPreexisting(boolean preexisting) {
		this.preexisting = preexisting;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
	public boolean isChanged() {
		return changed;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public Object getMatchObject() {
		return matchObject;
	}
	public void setMatchObject(Object matchObject) {
		this.matchObject = matchObject;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getSemtag() {
		return semtag;
	}
	public void setSemtag(String semtag) {
		this.semtag = semtag;
	}
	
}
