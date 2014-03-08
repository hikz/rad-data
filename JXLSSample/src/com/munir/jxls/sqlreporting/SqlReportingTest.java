package com.munir.jxls.sqlreporting;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jxls.report.ReportManager;
import net.sf.jxls.report.ReportManagerImpl;
import net.sf.jxls.transformer.XLSTransformer;


public class SqlReportingTest {

	
	
    public static void main(String[] args) throws Exception,  ClassNotFoundException, SQLException {
    	//new SqlReportingTest().reportingBasic();
    	new SqlReportingTest().reportingDependentQueryExample();
    }
    
    public void reportingBasic()  throws Exception,  ClassNotFoundException, SQLException {
    	
    	String templateFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/sql_reporting/templates/report_basic_template.xls";
        String destFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/sql_reporting/reports/report_basic_output.xls";

    	DatabaseHelper dbHelper = new DatabaseHelper();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xls_reporting","root","admin");
        //This will create the department and employee tables in the above xls_reporting database.
        //dbHelper.initDatabase(conn);
        Map beans = new HashMap();
        ReportManager reportManager = new ReportManagerImpl( conn, beans);
        beans.put("rm", reportManager);
        
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templateFileName, beans, destFileName);
        System.out.println("Done");
    }
    
    
    public void reportingDependentQueryExample()  throws Exception,  ClassNotFoundException, SQLException {
    	
    	String templateFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/sql_reporting/templates/report_basic_params_template.xls";
        String destFileName = "C:/Ramz_Trainingz/JXLS/Code_and_samples/sql_reporting/reports/report_basic_params_output.xls";

    	DatabaseHelper dbHelper = new DatabaseHelper();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xls_reporting","root","admin");
        //This will create the department and employee tables in the above xls_reporting database.
        //dbHelper.initDatabase(conn);
        Map beans = new HashMap();
        ReportManager reportManager = new ReportManagerImpl( conn, beans);
        beans.put("rm", reportManager);
        beans.put("ageParam", 27);
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templateFileName, beans, destFileName);
        System.out.println("Done");
    }

}
