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

import java.util.List;

public class MatchObjectRedundantRel {
	List<RedundantRelDetailLine>  Group1;
	List<RedundantRelDetailLine>  Group2;
	public List<RedundantRelDetailLine> getGroup1() {
		return Group1;
	}
	public void setGroup1(List<RedundantRelDetailLine> group1) {
		Group1 = group1;
	}
	public List<RedundantRelDetailLine> getGroup2() {
		return Group2;
	}
	public void setGroup2(List<RedundantRelDetailLine> group2) {
		Group2 = group2;
	}
}
