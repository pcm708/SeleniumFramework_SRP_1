package com.qa.automation.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This is the utility class for data read write
 *
 * @author QAIT
 *
 */
public class DataReadWrite {

    /**
     * construtor of this class
     */
    private static  Sheet ExcelWSheet;
    private static  XSSFSheet ExcelSMsheet;

	private static XSSFWorkbook ExcelWBook;
	    
    public DataReadWrite() {
    }

    /**
     * writeDataToFile
     *
     * @param Property
     * @param Data
     * @return true if written else return false
     */
    public static boolean writeDataToFile(String Property, String Data) {
        PrintWriter pw = null;
        boolean result = false;
        System.out.println("Data=" + Data);
        try {
            System.out.println("Start of try block");
            URL url = ResourceLoader.getResourceUrl("courseData");
            System.out.println(url);
            //String dataFolderPath = URLDecoder.decode(url.getPath(), "UTF-8");
            String path = "./src/test/resources/testdata";
            String dataFolderPath = URLDecoder.decode(path, "UTF-8");
            System.out.println(dataFolderPath);
            String outFilePath = dataFolderPath + File.separator + "courseData.tmp";
            System.out.println(outFilePath);
            pw = new PrintWriter(new BufferedWriter(new FileWriter(outFilePath, true)));
            pw.println(Property + "=" + Data);

            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }

        }

        return result;
    }

    /**
     * readDataFromFile
     *
     * @param Property
     * @return text
     */
    public static String readDataFromFile(String Property) {
        try {
            Properties prop = ResourceLoader.loadProperties("./src/test/resources/testdata/courseData.tmp");
            return prop.getProperty(Property);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getProperty(String Property) {
        try {
            Properties prop = ResourceLoader.loadProperties("./src/test/resources/testdata/courseData.tmp");
            return prop.getProperty(Property);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * 
     * This method will get the properties pulled from a file located relative to the base dir
     * 
     * @param propFile complete or relative (to base dir) file location of the properties file 
     * @param Property property name for which value has to be fetched 
     * @return String value of the property
     */
    public static String getProperty(String propFile, String Property) {
        try {
            Properties prop = ResourceLoader.loadProperties(propFile);
            return prop.getProperty(Property);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }    

    /**
     * readXmlFromFile
     *
     * @param fileName
     * @return text
     */
    public static String readXmlFromFile(String fileName) {

        File file = new File(fileName);
        StringBuilder contents = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text).append(
                        System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (contents.toString());
    }
    
    public static List<String> readDataFromCSVFile(String filePath){
        Scanner scanner = null;
		try {
			scanner = new Scanner(new File(filePath));
			//scanner = new Scanner(new File(System.getProperty("user.dir") + "/src/test/resources/testdata/StudentStoreBackend/Orders.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Scanner dataScanner = null;
        int index = 0;
        List<String> empList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            dataScanner = new Scanner(scanner.nextLine());
            dataScanner.useDelimiter(",");

            while (dataScanner.hasNext()) {
                String data = dataScanner.next();
                empList.add(data);
                index++;
            }
            System.out.println("empList is--->"+ empList);
        }

        scanner.close();
        return empList;
    }
    
    //Added by sujata on 15-07-2019 to read data from excel file
    @SuppressWarnings("deprecation")
	public List getExcelData(String path,String sheetName) throws Exception {
    	System.out.println("file path is  "+ path);
    	System.out.println("sheetName is " + sheetName);
    	List list=new ArrayList<>();
    	List headerList=new ArrayList<>();

    	try {
            FileInputStream ExcelFile = new FileInputStream(path);
            XSSFWorkbook  workbook = new XSSFWorkbook(path); 
            ExcelSMsheet = workbook.getSheet(sheetName);
            int index=0,rowItr=0;
  		   	 for(Row r:ExcelSMsheet){ 
  		   		// System.out.println("rownum is====> " + r.getRowNum());
  		   		 if(r.getRowNum()==1){
	  		   		Cell c=r.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c!=null){
	                		c.setCellType(Cell.CELL_TYPE_STRING);
		       				String isbn = c.getStringCellValue();
			       			list.add(isbn);
	                }
	                
	                Cell cell=r.getCell(3, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(cell!=null){
			       			int year = (int) cell.getNumericCellValue();
			       			list.add(year);
	                }
  		   		 }if(r.getRowNum()==4){
	  		   		Cell c=r.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c!=null){
	                		c.setCellType(Cell.CELL_TYPE_STRING);
		       				String folderName = c.getStringCellValue();
			       			list.add(folderName);
	                }
	                
	                Cell c1=r.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c1!=null){
	                		c1.setCellType(Cell.CELL_TYPE_STRING);
		       				String assetName = c1.getStringCellValue();
			       			list.add(assetName);
	                }
	                
	                Cell c2=r.getCell(2, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c2!=null){
	                		c2.setCellType(Cell.CELL_TYPE_STRING);
		       				String assetType = c2.getStringCellValue();
			       			list.add(assetType);
	                }
	                
	                Cell c3=r.getCell(3, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c3!=null){
	                		c3.setCellType(Cell.CELL_TYPE_STRING);
		       				String assetSubType = c3.getStringCellValue();
			       			list.add(assetSubType);
	                }
	                
	                
	                Cell c4=r.getCell(9, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c4!=null){
	                		c4.setCellType(Cell.CELL_TYPE_STRING);
		       				String pfn = c4.getStringCellValue();
			       			list.add(pfn);
	                }
	                
	                Cell c5=r.getCell(10, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c5!=null){
	                		c5.setCellType(Cell.CELL_TYPE_STRING);
		       				String luminaKey = c5.getStringCellValue();
			       			list.add(luminaKey);
	                }
	                
  		   		 }if(r.getRowNum()==3){
	  		   		Cell c=r.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c!=null){
	                		c.setCellType(Cell.CELL_TYPE_STRING);
	                		headerList.add(c.getStringCellValue());
	                }
	                
	                Cell c1=r.getCell(1, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c1!=null){
	                		c1.setCellType(Cell.CELL_TYPE_STRING);
	                		headerList.add(c1.getStringCellValue());
	                }
	                
	                Cell c2=r.getCell(2, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c2!=null){
	                		c2.setCellType(Cell.CELL_TYPE_STRING);
	                		headerList.add(c2.getStringCellValue());
	                }
	                
	                Cell c3=r.getCell(3, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c3!=null){
	                		c3.setCellType(Cell.CELL_TYPE_STRING);
	                		headerList.add(c3.getStringCellValue());
	                }
	                 
	                Cell c4=r.getCell(4, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c4!=null){
	                		c4.setCellType(Cell.CELL_TYPE_STRING);
	                		headerList.add(c4.getStringCellValue());
	                }
	                
	                Cell c5=r.getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c5!=null){
	                		c5.setCellType(Cell.CELL_TYPE_STRING);
	                		headerList.add(c5.getStringCellValue());
	                } 
	                Cell c6=r.getCell(6, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	                if(c6!=null){
	                		c6.setCellType(Cell.CELL_TYPE_STRING);
	                		headerList.add(c6.getStringCellValue());
	                }
	                
  		   		 }
	       	}    
  	    } 
        catch (FileNotFoundException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
		return list;
    }
    
    @SuppressWarnings("deprecation")
	public List getExcelDataHeader(String path,String sheetName,int rowNum) throws Exception {
    	List headerList=new ArrayList<>();

    	try {
            FileInputStream ExcelFile = new FileInputStream(path);
            XSSFWorkbook  workbook = new XSSFWorkbook(path); 
            ExcelSMsheet = workbook.getSheet(sheetName);
            for (int i = 0; i < ExcelSMsheet.getRow(rowNum).getLastCellNum(); i++) {
  		   		Cell c=ExcelSMsheet.getRow(rowNum).getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK); 
	  		   	if(c!=null){
	  		   		if(c.getCellType()== Cell.CELL_TYPE_STRING){
		  		   		c.setCellType(Cell.CELL_TYPE_STRING);
		        		headerList.add(c.getStringCellValue());
	  		   		}else if(c.getCellType()== Cell.CELL_TYPE_NUMERIC){
		  		   		c.setCellType(Cell.CELL_TYPE_NUMERIC);
		        		headerList.add(c.getNumericCellValue());
	  		   		}else if(c.getCellType()== Cell.CELL_TYPE_BOOLEAN){
		  		   		c.setCellType(Cell.CELL_TYPE_BOOLEAN);
		        		headerList.add(c.getBooleanCellValue());
	  		   		}else if(c.getCellType()== Cell.CELL_TYPE_FORMULA){
		  		   		c.setCellType(Cell.CELL_TYPE_FORMULA);
		        		headerList.add(c.getCellFormula());
	  		   		}else if(c.getCellType()== Cell.CELL_TYPE_ERROR){
		  		   		c.setCellType(Cell.CELL_TYPE_ERROR);
		        		headerList.add(c.getErrorCellValue());
	  		   		}else if(c.getCellType()== Cell.CELL_TYPE_BLANK){
		  		   		c.setCellType(Cell.CELL_TYPE_BLANK);
		        		headerList.add(c.getStringCellValue());
	  		   		}
	  		   		
	        		
	  		   	}
			}
              
  	    } 
        catch (FileNotFoundException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
		return headerList;
    }
    
    public List<String> getTableArray(String path,int startRow,int endRow) throws Exception {
    	List<String> dataList=new ArrayList<String>();
		   	try {
            FileInputStream ios = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(ios);
            XSSFSheet sheet = workbook.getSheetAt(0);
            //for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            for (int rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
            	  Row row = sheet.getRow(rowIndex);
            	  if (row != null) {
            	    Cell cell = row.getCell(0);
            	    if (cell != null) {
            	      dataList.add(cell.getStringCellValue());
            	    }
            	  }
            	}
              
            System.out.println(dataList);
           	       	       
        } 
        catch (FileNotFoundException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e1){
            System.out.println("Could not read the Excel sheet");
            e1.printStackTrace();
        }
		return dataList;
    }
}
