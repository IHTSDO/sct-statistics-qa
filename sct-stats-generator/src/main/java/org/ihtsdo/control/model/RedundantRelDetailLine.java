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

public class RedundantRelDetailLine {
	public RedundantRelDetailLine(String relationshipId, String sourceId,
			String sourceTerm, String destinationId, String destinationTerm,
			String typeId, String typeTerm, String group,
			String definitionStatus, String statedAttributes,
			String allStatedParentsSD, String inferredDescendants) {
		super();
		this.relationshipId = relationshipId;
		this.sourceId = sourceId;
		this.sourceTerm = sourceTerm;
		this.destinationId = destinationId;
		this.destinationTerm = destinationTerm;
		this.typeId = typeId;
		this.typeTerm = typeTerm;
		this.group = group;
		this.definitionStatus = definitionStatus;
		this.statedAttributes = statedAttributes;
		this.allStatedParentsSD = allStatedParentsSD;
		this.inferredDescendants = inferredDescendants;
	}
	public RedundantRelDetailLine(String line){
		String[] spl=line.split("\t",-1);
		this.relationshipId = spl[0];
		this.sourceId = spl[1];
		this.sourceTerm = spl[2];
		this.destinationId = spl[3];
		this.destinationTerm = spl[4];
		this.typeId = spl[5];
		this.typeTerm = spl[6];
		this.group = spl[7];
		this.definitionStatus = spl[8];
		this.statedAttributes = spl[9];
		this.allStatedParentsSD = spl[10];
		this.inferredDescendants =spl[11];
	}
	String relationshipId;
	String sourceId;
	String sourceTerm;
	String destinationId;
	String destinationTerm;	
	String typeId;
	String typeTerm;	
	String group;
	String definitionStatus;
	String statedAttributes;
	String allStatedParentsSD;
	String inferredDescendants;
	public String getRelationshipId() {
		return relationshipId;
	}
	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceTerm() {
		return sourceTerm;
	}
	public void setSourceTerm(String sourceTerm) {
		this.sourceTerm = sourceTerm;
	}
	public String getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	public String getDestinationTerm() {
		return destinationTerm;
	}
	public void setDestinationTerm(String destinationTerm) {
		this.destinationTerm = destinationTerm;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeTerm() {
		return typeTerm;
	}
	public void setTypeTerm(String typeTerm) {
		this.typeTerm = typeTerm;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getDefinitionStatus() {
		return definitionStatus;
	}
	public void setDefinitionStatus(String definitionStatus) {
		this.definitionStatus = definitionStatus;
	}
	public String getStatedAttributes() {
		return statedAttributes;
	}
	public void setStatedAttributes(String statedAttributes) {
		this.statedAttributes = statedAttributes;
	}
	public String getAllStatedParentsSD() {
		return allStatedParentsSD;
	}
	public void setAllStatedParentsSD(String allStatedParentsSD) {
		this.allStatedParentsSD = allStatedParentsSD;
	}
	public String getInferredDescendants() {
		return inferredDescendants;
	}
	public void setInferredDescendants(String inferredDescendants) {
		this.inferredDescendants = inferredDescendants;
	}
}
