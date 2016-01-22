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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.ihtsdo.control.model.PatternConfig;
import org.ihtsdo.statistics.model.Config;
import org.ihtsdo.statistics.model.ReportConfig;


public class XmlMapUtil {


	public static ReportConfig getReportConfigFromResource(String fileName) {

		ReportConfig config = null;
		try {

			JAXBContext context = JAXBContext.newInstance(ReportConfig.class);
			Unmarshaller u = context.createUnmarshaller();
			System.out.println("************ report " + fileName);
			InputStream is = XmlMapUtil.class.getResourceAsStream(fileName);

			config = (ReportConfig) u.unmarshal(is);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}

	public static ReportConfig getReportConfigFromFileSystem(String fileName) {

		ReportConfig config = null;
		try {

			JAXBContext context = JAXBContext.newInstance(ReportConfig.class);
			Unmarshaller u = context.createUnmarshaller();
			
			FileInputStream rfis = new FileInputStream(fileName);
			InputStreamReader is = new InputStreamReader(rfis,"UTF-8");

			config = (ReportConfig) u.unmarshal(is);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}

	public static PatternConfig getPatternConfigFromResource(String fileName) {

		PatternConfig config = null;
		try {

			JAXBContext context = JAXBContext.newInstance(PatternConfig.class);
			Unmarshaller u = context.createUnmarshaller();
			if (fileName.indexOf("/")>-1 && !fileName.startsWith("/")){
				fileName="/" + fileName;
			}
			System.out.println("************ pattern " + fileName);
			
			InputStream is = XmlMapUtil.class.getResourceAsStream(fileName);

			config = (PatternConfig) u.unmarshal(is);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}

	public static PatternConfig getPatternConfigFromFileSystem(File file) {

		PatternConfig config = null;
		try {

			JAXBContext context = JAXBContext.newInstance(PatternConfig.class);
			Unmarshaller u = context.createUnmarshaller();

			FileInputStream rfis = new FileInputStream(file);
			InputStreamReader is = new InputStreamReader(rfis,"UTF-8");

			config = (PatternConfig) u.unmarshal(is);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}

	public static Config getConfigFromFileSystem(File file) {

		Config config = null;
		try {

			JAXBContext context = JAXBContext.newInstance(Config.class);
			Unmarshaller u = context.createUnmarshaller();

			FileInputStream rfis = new FileInputStream(file);
			InputStreamReader is = new InputStreamReader(rfis,"UTF-8");

			config = (Config) u.unmarshal(is);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config;
	}
}