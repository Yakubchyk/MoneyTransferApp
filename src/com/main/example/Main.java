package com.main.example;

import com.main.example.exception.FileProcessingException;
import com.main.example.service.FileParserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 1 to parse files, 2 to view report by date range:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        try {
            if (choice == 1) {
                FileParserService.parseFiles();
                System.out.println("Files parsed successfully.");
            } else if (choice == 2) {
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
    }
}
