package com.accenture.automatizacion.datadrive;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.accenture.automatizacion.dto.Credenciales;

public class DataDrivenUsers {
	
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
//	public XSSFCol
	public XSSFCell cell = null;
	public XSSFFont font = null;
	public XSSFCellStyle style = null;


	public DataDrivenUsers() {
		// TODO Auto-generated constructor stub
	}
	
	public DataDrivenUsers(String exelfilepath) throws Exception {
		
		fis = new FileInputStream(exelfilepath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
		
	}
	
	

//	public Credenciales getCellData(int rowNum, String FinalStatus)
	public Credenciales getCellData(int rowNum)
    {
		int col_Num = -1;
        try
        {
        	Credenciales credencial = new Credenciales();
        	String sheetName = "Hoja1";
        	XSSFFont font = workbook.createFont();
        	XSSFCellStyle style = workbook.createCellStyle();
            sheet = workbook.getSheet(sheetName);
            String colName_user = "";
            String colName_pass = "";
            
            row = sheet.getRow(rowNum + 1);
            colName_user = row.getCell(0).getStringCellValue();
            colName_pass = row.getCell(1).getStringCellValue();
            
//         
//            if (FinalStatus.equals("Aceptado"))
//            {
//            	font.setColor(HSSFColor.GREEN.index);
//            }else if	(FinalStatus.equals("Rechazado"))
//            {
//            	font.setColor(HSSFColor.RED.index);
//            }
//            cell.setCellStyle(style);
//            cell.setCellValue(FinalStatus);
            
            	
            
            credencial.setUsername(colName_user);
            credencial.setPassword(colName_pass);
            return credencial;
//            if(cell.getCellTypeEnum() == CellType.STRING)
//                return cell.getStringCellValue();
            
        }
        catch(Exception e)
        {
        	e.printStackTrace();
			return null;
        }
    }
//	public void setStatus(int rowNum, String xlFilePath) throws Exception{
	public void setStatus(int rowNum, String xlFilePath, String FinalStatus) throws Exception{
		
		String sheetName = "Hoja1";
		
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum+1);
        cell = row.createCell(2);
        font = workbook.createFont();
    	style = workbook.createCellStyle();
    	
       font.setColor(HSSFColor.RED.index);
       if(FinalStatus.equals("Aceptado")) {
    	   font.setColor(HSSFColor.GREEN.index);
       }
       style.setFont(font);
        cell.setCellStyle(style);
        
        cell.setCellValue(FinalStatus); 
        cell.setCellValue(FinalStatus);
         
        FileOutputStream fos = new FileOutputStream(xlFilePath);
        workbook.write(fos);
        fos.close();
	}
	
	
	

}