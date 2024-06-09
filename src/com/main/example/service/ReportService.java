package com.main.example.service;

import com.main.example.exception.FileProcessingException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReportService {
    private static String currentDir = System.getProperty("user.dir");
    private static String output_File = currentDir + "/src/com/main/output/report.txt";

    public void vievReport() throws FileProcessingException {
        try (BufferedReader br = new BufferedReader(new FileReader(output_File))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            throw new FileProcessingException("Error reading report.");
        }
    }


}
