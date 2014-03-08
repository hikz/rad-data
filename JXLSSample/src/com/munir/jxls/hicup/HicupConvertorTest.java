package com.munir.jxls.hicup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.munir.jxls.simple.Department;
import com.munir.jxls.simple.Employee;

public class HicupConvertorTest {

	

	public static void main(String[] args) throws IOException, ParsePropertyException, InvalidFormatException, Exception {
	      
		//readInput();
		writeOutput();
		readForOptimization1();
	}

	private static HicupFinalInput readInput() throws Exception {
		 
		String configFileName = "C:/Ramz_Trainingz/JXLS/HICUP_PROJECT/FILES_21_Jan/FILES/input_config/hicup_sampleInput.xml";
		String inputFileName = "C:/Ramz_Trainingz/JXLS/HICUP_PROJECT/FILES_21_Jan/FILES/input/hicup_reader_input.xls";
		
		InputStream inputXML = new BufferedInputStream(new FileInputStream(configFileName));
        XLSReader mainReader =  ReaderBuilder.buildFromXML( inputXML );
        InputStream inputXLS = new BufferedInputStream(new FileInputStream(inputFileName));
       
        HicupFinalInput hicupFinalInput = new HicupFinalInput();
        Map beans = new HashMap();
        beans.put("hicupFinalInput", hicupFinalInput);              
        
        System.out.println("Before reading : "+hicupFinalInput.getHicupInputList().size());
        XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
        System.out.println("After reading : "+hicupFinalInput.getHicupInputList().size());
       
       
       for(int i = 0; i< hicupFinalInput.getHicupInputList().size(); i++){
    	   System.out.println(((HicupInput)hicupFinalInput.getHicupInputList().get(i)).getCounts());
       }
       
       return hicupFinalInput;
	}
	
	private static void writeOutput() throws Exception {
		String templateFileName = "C:/Ramz_Trainingz/JXLS/HICUP_PROJECT/FILES_21_Jan/FILES/output_templates/hicup9.xls";
		String destFileName = "C:/Ramz_Trainingz/JXLS/HICUP_PROJECT/FILES_21_Jan/FILES/output/hicupOutput.xls";
	       
	    HicupFinalInput hicupFinalInput = readInput();	      
	        
	       Map beans = new HashMap();
	       beans.put("hicupFinalInput",hicupFinalInput);
	       XLSTransformer transformer = new XLSTransformer();
	       transformer.transformXLS(templateFileName, beans, destFileName);	     
	       System.out.println("Your are done");
	}

	
	
	private static HicupOutput readForOptimization() throws Exception {
		
		String outputConfigFileName = "C:/Ramz_Trainingz/JXLS/HICUP_PROJECT/FILES_21_Jan/FILES/output_config/hicup_outputConfig.xml";
		String outputFileName = "C:/Ramz_Trainingz/JXLS/HICUP_PROJECT/FILES_21_Jan/FILES/output/hicupOutput.xls";
		
		InputStream outputConfigXML = new BufferedInputStream(new FileInputStream(outputConfigFileName));
        XLSReader mainReader =  ReaderBuilder.buildFromXML( outputConfigXML );
        InputStream inputXLS = new BufferedInputStream(new FileInputStream(outputFileName));
        
        HicupOutput hicupOutput = new HicupOutput();
        Map beans = new HashMap();
        beans.put("hicupOutput", hicupOutput);              
        
        System.out.println("Before reading : "+hicupOutput.getOnset());
        XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
        System.out.println("After reading : ");
        System.out.println("Onset - "+hicupOutput.getOnset());
        System.out.println("wl - "+hicupOutput.getWl());
        System.out.println("pwr - "+hicupOutput.getPwr());
        System.out.println("viewFactor - "+hicupOutput.getViewFactor());
        System.out.println("Asymptote - "+hicupOutput.getAsymptote());
        System.out.println("funnel - "+hicupOutput.getFunnel());
        System.out.println("FitCriteria - "+hicupOutput.getFitCriteria());
        
		return hicupOutput;
	}
	
	private static HicupOutput readForOptimization1() throws Exception {
		
		String outputFileName = "C:/Ramz_Trainingz/JXLS/HICUP_PROJECT/FILES_21_Jan/FILES/output/hicupOutput.xls";
		
		FileInputStream fis = new FileInputStream(outputFileName);
        Workbook wb = new HSSFWorkbook(fis); //or new XSSFWorkbook("c:/temp/test.xls")
        Sheet sheet = wb.getSheetAt(0);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        // suppose your formula is in B3
        CellReference cellReference = new CellReference("K58"); 
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol()); 

        CellValue cellValue = evaluator.evaluate(cell);
        
        System.out.println("here u go---fit criteria-");
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
        
        return new HicupOutput();
	}
}
