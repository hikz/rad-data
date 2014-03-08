package com.munir.jxls.simple;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;

public class MultipleSheetTest {

	 private static String templateFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/multiple_sheets/basictags_template.xls";
	 private static String destFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/multiple_sheets/basictags_output.xls";

	 
	public static void main(String[] args) throws Exception { 
		
		  //Multiple Sheets code
	       List sheetNames = new ArrayList();
	       sheetNames.add("xcalc");
	       sheetNames.add("data");
	       List maps = new ArrayList();
	       
	       for(int i=0; i<2;i++){
	    	   Map map = new HashMap();
		       map.put("sheet"+i,"test"+i);   
		       maps.add(map);
	       }
	              
	       XLSTransformer transformer = new XLSTransformer();
	       FileInputStream is = new FileInputStream(new File(templateFileName));
	       Workbook resultWorkbook = transformer.transformMultipleSheetsList(is,maps, sheetNames, "map", new HashMap(), 0);
	       
	       System.out.println("Done"); 
     
	}

}
