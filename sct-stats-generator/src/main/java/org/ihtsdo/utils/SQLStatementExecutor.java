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
package org.ihtsdo.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.ihtsdo.statistics.model.SpParam;
import org.ihtsdo.statistics.model.StoredProcedure;


public class SQLStatementExecutor {

	private Connection con;
	private ResultSet resultSet;
	public SQLStatementExecutor(Connection con) throws Exception {
		this.con = con;
	}
	
	public boolean executeStatement(String statement, Integer queryTimeOut) throws SQLException {
		
		resultSet=null;
		if (statement != null && statement.length() > 0) {
			Statement st = con.createStatement();
			
			if (queryTimeOut != null ) {			
				st.setQueryTimeout(queryTimeOut);
			}
			
			st.executeUpdate(statement);
			resultSet  = st.getResultSet();
			if (resultSet == null) {
				st.close();
			}
			
			return true;
		}
		
		return false;
	}
	public boolean executeQuery(String query, Integer queryTimeOut) throws SQLException {
		resultSet=null;
		if (query != null && query.length() > 0) {
			Statement st = con.createStatement();
			
			if (queryTimeOut != null ) {			
				st.setQueryTimeout(queryTimeOut);
			}
			
			st.execute(query);
			resultSet  = st.getResultSet();
			if (resultSet == null) {
				st.close();
			}
			
			return true;
		}
		
		return false;
	}
	public boolean executeStoredProcedure(StoredProcedure sProcedure,HashMap<String,String> params, Integer queryTimeOut) throws SQLException{
		resultSet=null;
		if (sProcedure != null ) {
			CallableStatement st = con.prepareCall("call " + sProcedure.getName());
			if (sProcedure.getParam()!=null){
				for(SpParam param:sProcedure.getParam()){
					if (param.getSQLType().compareTo("INT")==0){
						st.setInt(param.getOrder(), Integer.parseInt(params.get(param.getName())));
					}
					// TODO Add other types if necessary
				}
			}
			
			if (queryTimeOut != null ) {			
				st.setQueryTimeout(queryTimeOut);
			}
			
			st.execute();
			if (st.getMoreResults()){
				resultSet  = st.getResultSet();
			}
			if (resultSet == null) {
				st.close();
			}
			
			return true;
		}
		
		return false;
		
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
}
