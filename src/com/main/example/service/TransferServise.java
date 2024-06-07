package com.main.example.service;

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
                    fileParserService.parseFiles();
                    System.out.println("Files parsed successfully.");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else if (ch == 2) {
                try {
                    reportService.vievReport();

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            } else if (ch == 3) {
                System.out.println("Exit");
                break;

            } else {
                System.out.println("Invalid choice. Enter 1 to parse files or 2 to view report: ");
            }
        }
    }
}

