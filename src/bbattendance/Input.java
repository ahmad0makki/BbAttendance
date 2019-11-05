package bbattendance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class Input {
    
  //get Section Fix Method
  public static Map<String, String> inSectionFix(String path) throws IOException {
    Map<String, String> map = new HashMap<String, String>();
    
    Path path2 = Paths.get(path);
    
    List<String> allLines = Files.readAllLines(path2, StandardCharsets.UTF_16);
    
    int j = 0;
    for(String s : allLines) {
      if(j == 0) j = 1;
      else {
        
        
        String Last_Name = "";
        String First_Name = "";
        String Username = "";
        
        Boolean itIsOkeay = true;
        
        int mod = 1;
        for(int i=0; i<s.length();i++){
          char c = s.charAt(i);
          
          if(mod == 4);
          else if(Character.getType(c) == 12) {
            if(itIsOkeay) {itIsOkeay = false; Last_Name = Last_Name + c;}
          }
          else if(Character.getType(c) == 15) {
            if(mod == 1) mod = 2;
            else if(mod == 2 && !First_Name.equals("")) mod = 3;
            else if(mod == 3 && !Username.equals("")) mod = 4;
          }    
          else if(mod == 1) Last_Name = Last_Name + c;
          else if(mod == 2) First_Name = First_Name + c;
          else if(mod == 3) Username = Username + c;
          
        }
        
        String Full_Name = First_Name+" "+Last_Name;
        
        map.put(Username+"", Full_Name);
        
      }
      
    }
    return map;
   
  }
 
  // get Section Method
  @SuppressWarnings("resource")
  public static Map<String, String> inSection(String path) throws IOException {
    
    Map<String, String> map = new HashMap<String, String>();
    
    File file = new File(path);
    FileInputStream fis = null;
    
    try {
      fis = new FileInputStream(file);
    }
    catch (FileNotFoundException e) {
      System.out.println("\nError file not found, Exit form Program.");
      System.exit(0);
    }
    
    if(!path.contains(".xls")) {
      System.out.println("\nError file Format, Exit form Program.");
      System.exit(0);
    }
    
    // Finds the workbook instance for XLS file
    HSSFWorkbook myWorkBook = new HSSFWorkbook (fis);
    
    // Return first sheet from the XLS workbook
    HSSFSheet mySheet = myWorkBook.getSheetAt(0);
    
    // Get iterator to all the rows in current sheet
    Iterator<Row> rowIterator = mySheet.iterator();
    
    int j = 0;
    
    // Traversing over each row of XLS file
    while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        
        if(j == 0) j = 1;
        else {
          
          String First_Name = null;
          String Last_Name = null;
          int Username = 0;
          
          int i = 1;
          
          // For each row, iterate through each columns
          Iterator<Cell> cellIterator = row.cellIterator();
          
          while (cellIterator.hasNext()) {
              
              Cell cell = cellIterator.next();
              
              if(i == 1) {
                Last_Name = cell.getStringCellValue();
              }
              else if(i == 2) {
                First_Name = cell.getStringCellValue();
              }
              else if(i == 3) {
                Username = (int) cell.getNumericCellValue();
              }
              i++;
              
          }
          
          String Full_Name = First_Name+" "+Last_Name;
          
          map.put(Username+"", Full_Name);
        }

    }
    return map;
  }
  
  
  @SuppressWarnings("unused")
  public static ArrayList<String> inSession(String path, double timeInMinets) throws IOException {
    ArrayList<String> list = new ArrayList<String>();
    
    File file = new File(path);
    FileInputStream fis = null;
    
    try {
      fis = new FileInputStream(file);
    }
    catch (FileNotFoundException e) {
      System.out.println("\nError file not found, Exit form Program.");
      System.exit(0);
    }
    
    if(!path.contains(".csv")) {
      System.out.println("\nError file Format, Exit form Program.");
      System.exit(0);
    }
    
    // create a reader
    Reader reader =  new InputStreamReader(new FileInputStream(path), "utf-8");
    
    // read csv file
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
    int j = 0;
    for (CSVRecord record : records) {
      if(j == 0) j = 1;
      else {
        
        String Name = record.get(0);
        String Role = record.get(2);
        String Total_time = record.get(6);
        
        String[] parts = Total_time.split(":");
        
        int Hours = Integer.valueOf(parts[0]);
        int Minets = Integer.valueOf(parts[1]);
        
        for(;;) {
          if(Hours != 0) {
            Hours = Hours - 1;
            Minets = Minets + 60;
          }else break;
        }
        
        if(Role.equals("Participant")){
          if(Minets >= timeInMinets) list.add(Name);
        }
         
      }
        
    }
    return list;
    
  }
}
