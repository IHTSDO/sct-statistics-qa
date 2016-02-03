package org.ihtsdo.control.model;

public abstract class AControlPattern implements IControlPattern{

	public String getSemTag(String term){
		int pos1=term.lastIndexOf("(");
		int pos2=term.lastIndexOf(")");
		if ( pos1>-1 && pos2>pos1 ){
			return term.substring(pos1+1,pos2);
		}
		return "";
	}
}
