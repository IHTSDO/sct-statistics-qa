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

public class OutputInfoFactory {
	private static OutputInfo outputInfo;
	
	public static OutputInfo get(){
		if (outputInfo==null){
			outputInfo=new OutputInfo();
		}
		return outputInfo;
	}
}
