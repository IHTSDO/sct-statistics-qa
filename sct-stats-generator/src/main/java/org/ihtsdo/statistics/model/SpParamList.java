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

import java.util.List;

public class SpParamList {
	List<StoredProcedureParamDescriptor>param;

	public List<StoredProcedureParamDescriptor> getParam() {
		return param;
	}

	public void setParam(List<StoredProcedureParamDescriptor> param) {
		this.param = param;
	}
}
