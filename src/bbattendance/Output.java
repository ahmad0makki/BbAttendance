/*
 * This Class for generate excel attendence sheet
 */
package bbattendance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.gembox.spreadsheet.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rami Dannah
 */
public class Output {
    //Generate output file
    public static void generate(Map<String, String> outSection, ArrayList<String> outSession) throws IOException {
        
        
//-------------------------------------------------------------------------------
//START:-
        int Row = 1;
        // Row
        int T = 0;
        
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
        
        ExcelFile workbook = new ExcelFile();
        ExcelWorksheet worksheet = workbook.addWorksheet("outfile");
        
        //addWorksheet("test")
        worksheet.getCell(0, 0).setValue("ID");
        worksheet.getCell(0, 1).setValue("Name");
        
        workbook.save("outfile.xlsx");
        
        for (String key : outSection.keySet()) {
            worksheet.getCell(Row, 0).setValue(key);
            worksheet.getCell(Row, 1).setValue(outSection.get(key));
            
            
            for (String i : outSession) {
                if (outSection.get(key).equals(i.substring(0, i.length()))) {
                   
                    worksheet.getCell(Row, 2).setValue("√");
                    Row++;
                    T++;
                    workbook.save("outfile.xlsx");
                }
            }
            if (T == 0) {
                worksheet.getCell(Row, 2).setValue("X");
                workbook.save("outfile.xlsx");
                Row++;
            }
            T = 0;
        }

    }
}