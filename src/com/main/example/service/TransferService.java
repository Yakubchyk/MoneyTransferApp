package com.main.example.service;

import com.main.example.exception.FileProcessingException;

import java.util.List;
import java.util.Scanner;

public class TransferService {
    private final FileParserService fileParserService;
    private final ReportService reportService;

    public TransferService() {
        this.fileParserService = new FileParserService();
        this.reportService = new ReportService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to parse files, 2 to view report, 3 to EXIT:");

        while (true) {
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            try {
                if (choice == 1) {
                    fileParserService.parseFiles();
                    System.out.println("Files parsed successfully.");
                } else if (choice == 2) {
                    System.out.println("Enter start date (dd-MM-yyyy HH:mm:ss):");
                    String startDate = scanner.nextLine();
                    System.out.println("Enter end date (dd-MM-yyyy HH:mm:ss):");
                    String endDate = scanner.nextLine();
                    List<String> report = fileParserService.getReportByDateRange(startDate, endDate);
                    System.out.println("Report from " + startDate + " to " + endDate + ":");
                    if (report.isEmpty()) {
                        System.out.println("No records found.");
                    } else {
                        report.forEach(System.out::println);
                    }
                } else if (choice == 3) {
                    System.out.println("Exit");
                    break;
                } else {
                    System.out.println("Invalid choice. Enter 1 to parse files, 2 to view report, 3 to EXIT:");
                }
            } catch (FileProcessingException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
