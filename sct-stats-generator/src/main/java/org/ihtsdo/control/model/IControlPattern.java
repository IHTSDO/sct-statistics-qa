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

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface IControlPattern {
	
	public void execute() throws Exception;
	
	public void setConfigFile(File configFile);
	
	public List<ControlResultLine>getSample();
	
	public void setResultFile(File resultFile);
	
	public void setNewConceptsList(HashSet<String> newConcepts);

	public void setChangedConceptsList(HashSet<String> changedConcepts);

	public void setCurrentEffTime(String releaseDate);

	public void setPreviousEffTime(String previousReleaseDate);

	public void setPatternId(String patternId);
	
	public int getResultCount();
	
	public void setConceptTerms(HashMap<Long,String> conceptTerms);
	
}
