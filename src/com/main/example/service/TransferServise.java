package com.main.example.service;

import com.main.example.exception.FileProcessingException;

import java.util.List;
import java.util.Scanner;

public class TransferServise {
    private final FileParserService fileParserService;
    private final ReportService reportService;

    public TransferServise() {
        this.fileParserService = new FileParserService();
        this.reportService = new ReportService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to parse files or 2 to view report, 3 to EXIT- : ");
        while (scanner.hasNext()) {
            int ch = scanner.nextInt();
            if (ch == 1) {

                try {
                    if (ch == 1) {
                        FileParserService.parseFiles();
                        System.out.println("Files parsed successfully.");
                    } else if (ch == 2) {
                        System.out.println("Enter start date (dd-MM-yyyy HH:mm:ss):");
                        String startDate = scanner.nextLine();
                        System.out.println("Enter end date (dd-MM-yyyy HH:mm:ss):");
                        String endDate = scanner.nextLine();
                        List<String> report = FileParserService.getReportByDateRange(startDate, endDate);
                        System.out.println("Report from " + startDate + " to " + endDate + ":");
                        if (report.isEmpty()) {
                            System.out.println("No records found.");
                        } else {
                            report.forEach(System.out::println);
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } catch (FileProcessingException e) {
                    System.err.println("Error: " + e.getMessage());
                }
//                try {
//                    fileParserService.parseFiles();
//                    System.out.println("Files parsed successfully.");
//                } catch (Exception e) {
//                    System.err.println(e.getMessage());
//                }
//            } else if (ch == 2) {
//                try {
//                    reportService.vievReport();
//
//                } catch (Exception e) {
//                    System.err.println(e.getMessage());
//                }
            } else if (ch == 3) {
                System.out.println("Exit");
                break;

            } else {
                System.out.println("Invalid choice. Enter 1 to parse files or 2 to view report: ");
            }
        }
    }
}



