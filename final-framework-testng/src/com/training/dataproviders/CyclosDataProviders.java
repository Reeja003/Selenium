package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.CyclosBean;
import com.training.dao.CyclosDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class CyclosDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<CyclosBean> list = new CyclosDAO().getLoanDetails(); ; 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(CyclosBean temp : list){
			Object[]  obj = new Object[3]; 
			obj[0] = temp.getMemberName(); 
			obj[1] = temp.getAmount(); 
			obj[2]=temp.getDescription();
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	
	
	
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:/Reeja/Selenium/Project/TestData/CyclosTestData.xlsx"; 
		String sheetName="CYCTD_076";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetName); 
		
	}
	
	@DataProvider(name = "excel-inputs2")
	public Object[][] getExcelData2(){
		String fileName ="C:/Reeja/Selenium/Project/TestData/CyclosTestData.xlsx"; 
		String sheetName="CYCTD_079";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetName); 
		
	}
	
	@DataProvider(name = "excel-inputs3")
	public Object[][] getExcelData3(){
		String fileName ="C:/Reeja/Selenium/Project/TestData/CyclosTestData.xlsx"; 
		String sheetName="CYCTD_080";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetName); 
		
	}
	
	
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
		//return new ReadExcel().getExcelData("C:/Reeja/Selenium/Project/TestData/CyclosTestData.xls;", "Cyclos1");
	}
}
