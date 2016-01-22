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
package org.ihtdso.fileprovider;

import java.io.File;
import java.util.HashSet;

public class PreviousFile {

	private static FileProvider previousFile;
	
	public static FileProvider get() throws Exception{
		if (previousFile==null){
			throw new Exception("Previous File Provider was not initialized.");
		}
		return previousFile;
	}
	public static void init(File sourceFullFolder,File baseFolder,HashSet<String> releaseDependenciesFullFolders,  String releaseDate){
		
		previousFile=new FileProvider( sourceFullFolder, baseFolder, releaseDependenciesFullFolders, releaseDate);
	}
}
