package com.example.ape;

import java.io.*;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelExcute {
    static String path = "C:/Users/Admin/Desktop/Task_Auto_Test.xlsx" ;
    static int startRow = 2168;
    static int endRow = 2170;
    final int colcondit = 2;
    final int colexcpet = 1;
    static final String name = "test2";
    String write;
    public static void ghitext(ArrayList<Case> inputcase, String name){
        String text = "";
        for (Case e:
             inputcase) {
            text+= e.print()+"\n";
            }
        try {

            FileWriter writer = new FileWriter("C:/Users/Admin/Desktop/"+name+".txt");   // Tạo đối tượng FileWriter để ghi dữ liệu ra file
            writer.write(text);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }
    public static String fotmatCondition(String text){
        String text2 = "";
        if (text.contains("\n")) {
            String[] lines = text.split("\\r?\\n");
            StringBuilder resultBuilder = new StringBuilder();
            for (String line : lines) {
                int index = line.indexOf(":");
                if (index != -1&&index<=5) {
                    String result = line.substring(index + 1).trim().replace("\n", ",");
                    resultBuilder.append(result).append(",");
                    text2+=result;
                }
                else {
                    String result = line.replace("\n", ",");
                    resultBuilder.append(result).append(",");
                    text2+=result;
                }
            }
            if (resultBuilder.length() > 0) {
                resultBuilder.deleteCharAt(resultBuilder.length() - 1);
            }
            String result = resultBuilder.toString();
            text2+=result;
        }
        else {
            int index = text.indexOf(":");
            if (index != -1&&index<=5) {
                text2 +=text.substring(0, index);
            }
            else text2+=(text);
        }
        return text2;
    }
    public static String fotmatExcept(String text){
        String[] lines = text.split("\\r?\\n");
        StringBuilder resultBuilder = new StringBuilder();
        for (String line : lines) {
            String[] parts = line.split("-");
            for (String part : parts) {
                String result = part.trim().replace("\n", ",");
                resultBuilder.append(result).append(",");
            }
        }
        if (resultBuilder.length() > 0) {
            resultBuilder.deleteCharAt(resultBuilder.length() - 1);
        }

        String result = resultBuilder.toString();
        if(result.startsWith(","))
        {
            result.substring(1);
        }
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
            try {
                FileInputStream file = new FileInputStream(new File(path));
                Workbook workbook = WorkbookFactory.create(file);
                Sheet sheet = workbook.getSheetAt(0);
                ArrayList<Case> e = new ArrayList<>();
                for (int i = startRow; i <= endRow; i++) {
                    Case target = new Case();
                    Row row = sheet.getRow(i - 1);
                    target.setIpnut(startRow);
                    Cell cell = row.getCell(2);
                    String cellContent = cell.getStringCellValue();
                    target.setCondition(fotmatCondition(cellContent));
                    Cell cell2 = row.getCell(4);
                    String cellContent2 = cell2.getStringCellValue();
                    target.setExpect(fotmatExcept(cellContent2));
                    e.add(target);
                }
                file.close();
                ghitext(e,name);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}