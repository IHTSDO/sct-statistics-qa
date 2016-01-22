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

import java.util.Comparator;

public class ArrayComparator implements Comparator<String[]> {

	private int[] indexes;
	private boolean reverse;

	public ArrayComparator(int[] indexes, boolean reverse) {
		this.indexes = indexes;
		this.reverse = reverse;
	}

	public int compare(String[] o1, String[] o2) {
		for (int i = 0; i < indexes.length; i++) {
			if (o1[indexes[i]].compareTo(o2[indexes[i]]) < 0) {
				return reverse ? 1 : -1;
			} else if (o1[indexes[i]].compareTo(o2[indexes[i]]) > 0) {
				return reverse ? -1 : 1;
			}
		}
		return 0;
	}
}
