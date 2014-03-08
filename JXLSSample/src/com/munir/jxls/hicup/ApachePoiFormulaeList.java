package com.munir.jxls.hicup;

import java.util.Collection;

import org.apache.poi.ss.formula.WorkbookEvaluator;

public class ApachePoiFormulaeList {

	public static void main(String[] args) {
		
		Collection<String> unsupportedFuncs = WorkbookEvaluator.getNotSupportedFunctionNames();
		System.err.println(unsupportedFuncs);
		
		

	}

}
