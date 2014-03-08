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

	 private static String templateFileName = "C:/Ramz_Trainingz/JXLS/JXLS_workspace/JXLSProject/src/main/resources/examples/template/basictags1.xls";
	 private static String destFileName = "C:/Ramz_Trainingz/JXLS/JXLS_workspace/JXLSProject/src/main/resources/build/basictags_output1.xls";

	    public static void main(String[] args) throws IOException, ParsePropertyException, InvalidFormatException {
	       
	        List list = new ArrayList();
	        list.add("MUNIR");
	        list.add("RAM");
	        
	        String javaName = "java-xls";
	        
	        Map beans = new HashMap();
	        beans.put("xslNames", list);
	        beans.put("xslName", javaName);
	        beans.put("input1",new Float(10.1));
	        beans.put("input2",new Float(10.2));
	        beans.put("input3",new Float(10.3));
	        
	        XLSTransformer transformer = new XLSTransformer();
	        transformer.transformXLS(templateFileName, beans, destFileName);
	      //  transformMultipleSheetsList(arg0, arg1, arg2, arg3, arg4, arg5)
	        System.out.println("Your are done");
	    }
}
