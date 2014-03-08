package com.munir.jxls.hicup;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

public class SampleTest {

	public static void main(String[] args) throws Exception {
		
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
        

	}

}
