package com.munir.jxls.simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class NewTest {

	 private static String templateFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/input/templates/basictags1.xls";
	 private static String destFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/output/basictags_output2.xls";

	    public static void main(String[] args) throws IOException, ParsePropertyException, InvalidFormatException {
	       
	        List list = new ArrayList();
	        list.add(0);
	        list.add(60); 
	        list.add(45);
	        
	        Map beans = new HashMap();
	        beans.put("xslNames", list);	        
	        
	        XLSTransformer transformer = new XLSTransformer(); 
	        transformer.transformXLS(templateFileName, beans, destFileName);
	        System.out.println("Your are done");
	    }
}
