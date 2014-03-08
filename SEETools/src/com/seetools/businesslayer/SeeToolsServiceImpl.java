package com.seetools.businesslayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.seetools.businesslayer.hipconv.process.HipconverterFinalInput;
import com.seetools.businesslayer.hipconv.process.HipconverterFinalOutput;
import com.seetools.businesslayer.hipconv.process.HipconverterInput;
import com.seetools.businesslayer.hipconv.process.HipconverterOutput;

public class SeeToolsServiceImpl {

	public HipconverterFinalOutput processFileUpload(Part inputFile) throws Exception {
		writeOutput(inputFile.getInputStream());
        return readOutput(); 
	}
	
	private static HipconverterFinalInput readInput(InputStream inputStream, int numberOfBits) throws Exception {
		 
		String configFileName = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/input_config/hipconverter_sampleInput.xml";
		//String inputFileName  = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/input/hipconverter_reader_input.xls"; 
		
		InputStream inputXML = new BufferedInputStream(new FileInputStream(configFileName));
        XLSReader mainReader =  ReaderBuilder.buildFromXML( inputXML );
        InputStream inputXLS = new BufferedInputStream(inputStream);
       
        HipconverterFinalInput hipconverterFinalInput = new HipconverterFinalInput();
        Map<String,HipconverterFinalInput> beans = new HashMap<String, HipconverterFinalInput>();
        beans.put("hipconverterFinalInput", hipconverterFinalInput);  
        
        System.out.println("Before reading : "+ hipconverterFinalInput.getHipconverterInputList().size());
        XLSReadStatus readStatus = mainReader.read( inputXLS, beans);
        System.out.println("After reading : "+hipconverterFinalInput.getHipconverterInputList().size());
       
        
       for(HipconverterInput hipconverterInput : hipconverterFinalInput.getHipconverterInputList()){
    	   System.out.println(hipconverterInput.getLet() + "/" + hipconverterInput.getXsec() + "/" + hipconverterInput.getEnergy());
       }
       //Populate Energy fields for input
       double defaultEnergy = 10;
       
       for(HipconverterInput hipconverterInput : hipconverterFinalInput.getHipconverterInputList()){
    	   hipconverterInput.setEnergy(defaultEnergy);
    	   defaultEnergy= defaultEnergy+10;
       }
       hipconverterFinalInput.setNumberOfBits(numberOfBits);
       return hipconverterFinalInput;
	}
	
	private void writeOutput(InputStream inputStream) throws Exception {		
	       
		String templateFileName = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output_templates/hipconverterTemplate.xls";
		String destFileName     = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output/hipconverterOutput.xls";
	       HipconverterFinalInput hipconverterFinalInput = readInput(inputStream, 8);
	       int numberOfBits = 8; 
	       hipconverterFinalInput.setNumberOfBits(numberOfBits);
	       
	       Map<String,HipconverterFinalInput> beans = new HashMap<String, HipconverterFinalInput>();
	       beans.put("hipconverterFinalInput",hipconverterFinalInput);
	       XLSTransformer transformer = new XLSTransformer();
	       transformer.transformXLS(templateFileName, beans, destFileName);	     
	       System.out.println("Your are done");
	}
	
	private HipconverterFinalOutput readOutput() throws Exception {
		 
	   String outputFileName  = "C:/Ramz_Trainingz/JXLS/HIPCONVERTOR_PROJECT/FILES/output/hipconverterOutput.xls";        
	   	
	   FileInputStream fis = new FileInputStream(outputFileName);
       Workbook wb = new HSSFWorkbook(fis);
       Sheet sheet = wb.getSheetAt(0);
       FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
       
       HipconverterFinalOutput hipconverterFinalOutput = new HipconverterFinalOutput();
       List<HipconverterOutput> hipconverterOutputs = new ArrayList<HipconverterOutput>();
       System.out.println("here u go-fit criteria-");
       for(int i=8; i<23;i++)
       {
		       HipconverterOutput hipconverterOutput = new HipconverterOutput();
		       
		       //Reading energy values
		       CellReference energyCellReference = new CellReference("K"+i); 
		       Row energyRow = sheet.getRow(energyCellReference.getRow());
		       Cell energyCell = energyRow.getCell(energyCellReference.getCol()); 
		       CellValue energyCellValue = evaluator.evaluate(energyCell);
		       
		       hipconverterOutput.setEnergy(energyCellValue.getNumberValue());
               
		       //Reading proton cross section values
         	   CellReference protonXSecCellReference = new CellReference("L"+i); 
               Row protonXSecRow = sheet.getRow(protonXSecCellReference.getRow());
               Cell protonXSecCell = protonXSecRow.getCell(protonXSecCellReference.getCol()); 

               CellValue protonXSecCellValue = evaluator.evaluate(protonXSecCell);
               
               hipconverterOutput.setProtonCrossSection(protonXSecCellValue.getNumberValue());
               hipconverterOutputs.add(hipconverterOutput);
       }
       
       hipconverterFinalOutput.setHipconverterOutputList(hipconverterOutputs);
       for(HipconverterOutput output : hipconverterFinalOutput.getHipconverterOutputList()){
    	   System.out.println(output.getProtonCrossSection() + "--" + output.getEnergy());
       }
       return hipconverterFinalOutput;
	
	}
    
	
}
