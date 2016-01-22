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
package org.ihtsdo.statistics.db.setup;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.ihtsdo.utils.FileHelper;
import org.ihtsdo.utils.ResourceUtils;
import org.ihtsdo.utils.SQLStatementExecutor;


public class DbSetup {


	private Connection con;

	public DbSetup(Connection con) {
		super();
		this.con = con;
	}

	public void execute() throws Exception{
		String relativePath="src/main/resources/";
		if( new File(relativePath).isDirectory() ){

			String path=relativePath + "org/ihtsdo/statistics/db/setup/table";
			setFromFileSystem(path);
			path=relativePath + "org/ihtsdo/statistics/db/setup/storedprocedure";
			setFromFileSystem(path);
			path=relativePath + "org/ihtsdo/statistics/db/setup/index";
			setFromFileSystem(path);

		}else{
//			String path="table/";
			String path="org/ihtsdo/statistics/db/setup/table";
			setFromResources(path);
			path="org/ihtsdo/statistics/db/setup/storedprocedure";
			setFromResources(path);
			path="org/ihtsdo/statistics/db/setup/index";
			setFromResources(path);
		}

	}


	public void recreatePath(String resourcePath) throws Exception{
		String relativePath="src/main/resources/";
		if( new File(relativePath).isDirectory() ){

			String path=relativePath + resourcePath;
			setFromFileSystem(path);

		}else{
			setFromResources(resourcePath);
		}

	}
	private void setFromFileSystem(String path) throws IOException, Exception,
			SQLException {
		Collection<File>list = ResourceUtils.getFileSystemScripts(path);
		SQLStatementExecutor exec=new SQLStatementExecutor(con);
		for(File file:list){

			if (file.getAbsolutePath().indexOf("/.")>-1 || file.getName().startsWith(".")){
				continue;
			}
			String script=FileHelper.getTxtFileContent(file);
			exec.executeStatement(script, null);
		}
	}

	private void setFromResources(String path) throws IOException, Exception,
			SQLException {

		Collection<String>list = ResourceUtils.getResourceScripts(path);
		SQLStatementExecutor exec=new SQLStatementExecutor(con);
		for(String file:list){
			if (file.indexOf("/.")>-1 || file.startsWith(".")){
				continue;
			}
			String script;
			if (file.indexOf("/")>-1){
				script=FileHelper.getTxtResourceContent(file);
			}else{
				script=FileHelper.getTxtResourceContent(path + "/" + file);
			}
			exec.executeStatement(script, null);
		}
	}

}
