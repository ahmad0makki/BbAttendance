/*
 * This Class for generate excel attendence sheet
 */
package bbattendance;

import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.SpreadsheetInfo;
import com.gembox.spreadsheet.*;
 
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.gembox.spreadsheet.*;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Ahmed Makki ID:1740809
 */
public class Output {
    int Row = 0;
    // Row = الصفوف
    int T = 0;
    
    


    public void outSection(Map<String,String>outSection) throws IOException{
            SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");//تصريح من المكتبة
        ExcelFile workbook = new ExcelFile();
        ExcelWorksheet worksheet = workbook.addWorksheet("outfile");//addWorksheet("test"); إسم الملف
        worksheet.getCell(0 , 0).setValue("ID");
        worksheet.getCell(0 , 1).setValue("Name");
        workbook.save("outfile.xlsx");
        Row=1;
            for (String key : outSection.keySet()){
                worksheet.getCell(Row , 0).setValue(key);
                worksheet.getCell(Row , 1).setValue(outSection.get(key));
                workbook.save("outfile.xlsx");
                Row++; 
               }
              // يكتب إسم الطالب و الرقم الجامعي
              Row=0;
    }
    public void outSession(ArrayList<String>outSession) throws IOException{
        int ans=0;
    int r=1,r1=1,r2=0,r3=0,r4=0,r5=0,r6=0,r7=0,r8=0;
    SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
        ExcelFile workbook = ExcelFile.load("outfile.xlsx");
        ArrayList<String> testSection = new ArrayList<String>();
        ArrayList<String> testSection2 = new ArrayList<String>();
         ArrayList<String> ID = new ArrayList<String>();
        // Iterate through all worksheets in an Excel workbook
        for (ExcelWorksheet worksheet : workbook.getWorksheets()) {
            
                for (ExcelRow row : worksheet.getRows()) {
                for (ExcelCell cell : row.getAllocatedCells()) {
                    r2++;
                    if (cell.getValueType() != CellValueType.NULL){
                       if(r==2){testSection.add(String.format("%1$s", cell.getValue(), cell.getValueType()));
                       r3=r+worksheet.calculateMaxUsedColumns();}
                       else if(r3==r){
                    testSection.add(String.format("%1$s", cell.getValue(), cell.getValueType()));
                       r3=r+worksheet.calculateMaxUsedColumns();}
                    
                    else if(r==r1*worksheet.calculateMaxUsedColumns()){
                        testSection2.add(String.format("%1$s", cell.getValue(), cell.getValueType()));
                        r1++;}
                    else{
                        if(r8==0){
                             ID.add(String.format("%1$s", cell.getValue(), cell.getValueType()));
                        }
                        
                        
                        testSection2.add(String.format("%1$s", cell.getValue(), cell.getValueType()));
                    r4++;
                    }
                        }
                    r8=0;
                    r++;
                    
                  
                    }
                    
                  
                }

        }
        r3=1;
        
      //----------------------------------------------------------------
            ExcelFile workbook2 = new ExcelFile();
   ExcelWorksheet worksheet2 = workbook2.addWorksheet("outfile");
               
    for (String key : testSection){
              worksheet2.getCell(Row, 1).setValue(key);
    Row++;}Row=0;
   int ii=0,jj=0;
   
       for (String key2 : ID){
   worksheet2.getCell(ii , 0).setValue(key2);
ii++;
       }
       workbook2.save("outfile.xlsx");
      ii=0; 
      int n=worksheet2.calculateMaxUsedColumns();
   for (String key2 : testSection2){
       
       if(n!=2){
       if(jj==r3*(n-1)){r3++;
       worksheet2.getCell(ii , jj).setValue(key2);
       ii++;
       jj=0;}
       else if(jj == 1){
       jj++;
   }else{worksheet2.getCell(ii , jj).setValue(key2);jj++;}}
       else{}}
   
   workbook2.save("outfile.xlsx");

          for (String key4 : testSection){
              if(r5!=0){
                //  worksheet2.getCell(Row,(worksheet2.calculateMaxUsedColumns())-).setValue(key);
               for (String i : outSession) {
                   if(Row==0){worksheet2.getCell(Row,n).setValue(".");
                   workbook2.save("outfile.xlsx");
                   Row++;} 
                   else if(key4.equals(i.substring(0,i.length()))){
                   
                  
                    worksheet2.getCell(Row,n).setValue("√");
                    Row++;
                    T++;
                    workbook2.save("outfile.xlsx");}}
                
               
                if(T==0){
               worksheet2.getCell(Row,n).setValue("X");
               workbook2.save("outfile.xlsx");
               Row++; 
               }
              T=0;
        }r5++;} 
    }
}

