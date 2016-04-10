import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

//import statements
public class ReadExcel 
{
	private DBManager db;
	
	public ReadExcel(DBManager db){this.db = db;}
	
	public void readSome(String filename, String rowName, int firstRowIndex){
		try {
    	    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(new File("datas/"+filename+".xls")));
    	    HSSFWorkbook wb = new HSSFWorkbook(fs);
    	    HSSFSheet sheet = wb.getSheetAt(0);
    	    HSSFRow row;
    	    HSSFCell cell;
    	    int memorize = -1;

    	    int rows; 
    	    rows = sheet.getPhysicalNumberOfRows();

    	    int cols = 0; 
    	    int tmp = 0;
    	    
    	    ArrayList<String> colNames = new ArrayList<>();
    	    
    	    for(int i = 0; i < 10 || i < rows; i++) {
    	        row = sheet.getRow(i);
    	        if(row != null) {
    	            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
    	            if(tmp > cols) cols = tmp;
    	        }
    	    }

    	    for(int r = 0; r < rows; r++) {
    	        row = sheet.getRow(r);
    	        if(row != null) {
    	            for(int c = 0; c < cols; c++) {
    	                cell = row.getCell((int)c);
    	                if(cell != null) {
    	                	if(row.getRowNum() == firstRowIndex){
    	                		colNames.add(cell.getStringCellValue());
    	                	}
    	                    if(cell.toString().equals(rowName)) memorize = row.getRowNum();
    	                    else if(memorize == row.getRowNum()){
    	                    	
    	                    }
    	                }
    	            }
    	        }
    	    }
    	    wb.close();
    	    db.createTable(filename, colNames);
    	} catch(Exception ioe) {
    	    ioe.printStackTrace();
    	}
	}
	
	public void readAll(String filename, int firstRowIndex){
		try {
    	    POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(new File("datas/"+filename+".xls")));
    	    HSSFWorkbook wb = new HSSFWorkbook(fs);
    	    HSSFSheet sheet = wb.getSheetAt(0);
    	    HSSFRow row;
    	    HSSFCell cell;

    	    int rows; 
    	    rows = sheet.getPhysicalNumberOfRows();

    	    int cols = 0; 
    	    int tmp = 0;
    	    
    	    ArrayList<String> colNames = new ArrayList<>();
    	    
    	    for(int i = 0; i < 10 || i < rows; i++) {
    	        row = sheet.getRow(i);
    	        if(row != null) {
    	            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
    	            if(tmp > cols) cols = tmp;
    	        }
    	    }

    	    for(int r = 0; r < rows; r++) {
    	        row = sheet.getRow(r);
    	        if(row != null) {
    	            for(int c = 0; c < cols; c++) {
    	                cell = row.getCell((int)c);
    	                if(cell != null) {
    	                	if(row.getRowNum() == firstRowIndex){
    	                		 colNames.add(cell.getStringCellValue());
    	                	}
    	                    else{
    	                    	
    	                    }
    	                }
    	            }
    	        }
    	    }
    	    wb.close();
    	    db.createTable(filename, colNames);
    	} catch(Exception ioe) {
    	    ioe.printStackTrace();
    	}
	}
}