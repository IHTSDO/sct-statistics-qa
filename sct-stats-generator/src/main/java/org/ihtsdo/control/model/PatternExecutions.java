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
package org.ihtsdo.control.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PatternExecutions {
	String runControlPatterns;
	PatternExclusions exclusions;
	public String getRunControlPatterns() {
		return runControlPatterns;
	}
	public void setRunControlPatterns(String runControlPatterns) {
		this.runControlPatterns = runControlPatterns;
	}
	public PatternExclusions getExclusions() {
		return exclusions;
	}
	public void setExclusions(PatternExclusions exclusions) {
		this.exclusions = exclusions;
	}
}
