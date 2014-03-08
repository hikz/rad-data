package com.munir.jxls.simple;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class XLSReader1 {

	private static String configFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/reading/config/sampleInput.xml";
	//private static String inputFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/reading/input/basictags_output2.xls";
	private static String inputFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/output/basictags_output2.xls";
	 
	public static void main(String[] args) throws Exception {
	
		new XLSReader1().read();

	}
	
	public void read() throws Exception {
		
		String templateFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/input/templates/basictags1.xls";
		String destFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/output/basictags_output2.xls";
		 List list = new ArrayList();
	        list.add(0);
	        list.add(60); 
	        list.add(70);
	        list.add(15);
	        
	        Map beans = new HashMap();
	        beans.put("xslNames", list);	        
	        
	        XLSTransformer transformer = new XLSTransformer(); 
	        transformer.transformXLS(templateFileName, beans, destFileName);
	        System.out.println("Your are done");
	        
	        System.out.println("------------------------------------");
		
		/*InputStream inputXML = new BufferedInputStream(new FileInputStream(configFileName));
        XLSReader mainReader =  ReaderBuilder.buildFromXML(inputXML);
        InputStream inputXLS = new BufferedInputStream(new FileInputStream(inputFileName));
       
        Department department = new Department();
        Map beans1 = new HashMap();
        beans1.put("department", department);
              
        
        System.out.println("Before reading : "+department.getDeptName());
       XLSReadStatus readStatus = mainReader.read( inputXLS, beans1);
       
       System.out.println("After reading : " + department.getDeptName());
       
       for(int i = 0; i< department.getEmployees().size(); i++){
    	   System.out.println(((Employee)department.getEmployees().get(i)).getEmpName());
       }*/
	        
	        
	        FileInputStream fis = new FileInputStream(inputFileName);
	        Workbook wb = new HSSFWorkbook(fis); //or new XSSFWorkbook("c:/temp/test.xls")
	        Sheet sheet = wb.getSheetAt(0);
	        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

	        // suppose your formula is in B3
	        CellReference cellReference = new CellReference("B8"); 
	        Row row = sheet.getRow(cellReference.getRow());
	        Cell cell = row.getCell(cellReference.getCol()); 

	        CellValue cellValue = evaluator.evaluate(cell);
	        
	        System.out.println("here u go----");
	        switch (cellValue.getCellType()) {
	            case Cell.CELL_TYPE_BOOLEAN:
	                System.out.println(cellValue.getBooleanValue());
	                break;
	            case Cell.CELL_TYPE_NUMERIC:
	                System.out.println(cellValue.getNumberValue());
	                break;
	            case Cell.CELL_TYPE_STRING:
	                System.out.println(cellValue.getStringValue());
	                break;
	            case Cell.CELL_TYPE_BLANK:
	                break;
	            case Cell.CELL_TYPE_ERROR:
	                break;

	            // CELL_TYPE_FORMULA will never happen
	            case Cell.CELL_TYPE_FORMULA: 
	                break;
	        }				

	}

}
