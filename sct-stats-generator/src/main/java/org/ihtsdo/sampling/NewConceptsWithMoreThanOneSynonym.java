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

import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * Created by alo on 2/15/16.
 */
public class NewConceptsWithMoreThanOneSynonym {
    
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Starting...");
        File newConceptsFile = new File("/Users/alo/Downloads/newConceptsDetailed.csv");
        BufferedReader br = new BufferedReader(new FileReader(newConceptsFile));
        String line;
        Set<String> newConcepts = new HashSet<String>();
        int count = 0;
        while ((line = br.readLine()) != null) {
            // process the line.
            List<String> columns = Arrays.asList(line.split(","));
            newConcepts.add(columns.get(1));
            //System.out.println(columns.get(1));
        }
        System.out.println("New concepts loaded");

        File langFile = new File("/Users/alo/Downloads/SnomedCT_RF2Release_INT_20160131-1/Snapshot/Refset/Language/der2_cRefset_LanguageSnapshot-en_INT_20160131.txt");
        BufferedReader br3 = new BufferedReader(new FileReader(langFile));
        String line3;
        Set<String> allLanguageDescriptions = new HashSet<String>();
        int count3 = 0;
        br.readLine();
        while ((line = br3.readLine()) != null) {
            // process the line.
            List<String> columns = Arrays.asList(line.split("\t", -1));
            if (columns.size() >= 2 && columns.get(2).equals("1") &&
                    columns.get(4).equals("900000000000509007")) {
                allLanguageDescriptions.add(columns.get(5));
            }
        }
        System.out.println("All language refset lines");

        File descsFile = new File("/Users/alo/Downloads/SnomedCT_RF2Release_INT_20160131-1/Snapshot/Terminology/sct2_Description_Snapshot-en_INT_20160131.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(descsFile));
        String line2;
        int count2 = 0;
        Map<String,Integer> descsCounts = new HashMap<String,Integer>();
        while ((line2 = br2.readLine()) != null) {
            // process the line.
            count2++;
            if (count2 % 10000 == 0) {
                //System.out.println(count2);
            }
            List<String> columns = Arrays.asList(line2.split("\t", -1));
            if (columns.size() >= 6) {
                if (columns.get(4).equals("714539008")) {
                    System.out.println("Found...");
                }
                if (columns.get(2).equals("1") && columns.get(6).equals("900000000000013009") &&
                        allLanguageDescriptions.contains(columns.get(0))) {
                    if (descsCounts.containsKey(columns.get(4))) {
                        int newDescsCount = descsCounts.get(columns.get(4)) + 1;
                        descsCounts.put(columns.get(4),newDescsCount);
                    } else {
                        descsCounts.put(columns.get(4),1);
                    }
                }
            }
        }
        System.out.println("Description counts loaded");

        int count3x = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("NewConceptsWithMoreThanOneSynonym.csv")));;
        for (String loopConcept : newConcepts) {
            if (descsCounts.containsKey(loopConcept)) {
                if (descsCounts.get(loopConcept) > 1) {
                    writer.write(loopConcept);
                    writer.newLine();
                    count3x++;
                }
            } else {
                System.out.println("Missing concept " + loopConcept);
            }

        }
        writer.close();
        System.out.println("Done, found " + count3x + " concepts");
    }
}
