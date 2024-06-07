package com.main.example.service;

import com.main.example.exception.FileProcessingException;
import com.main.example.exception.InvalidTransferException;
import com.main.example.model.Account;
import com.main.example.model.Transfer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileParserService {
    private static String currentDir = System.getProperty("user.dir");
    private static String input_Dir = currentDir + "/src/com/main/input";
    private static String output_File = currentDir + "/src/com/main/output/report.txt";
    private static String archive_Dir = currentDir + "/src/com/main/archive";
    private static String accounts_File = currentDir + "/src/com/main/resourses/accounts.txt";

    public static void parseFiles() throws FileProcessingException {
        File inputDir = new File(input_Dir);
        File[] files = inputDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            throw new FileProcessingException("No valid files found in the input directory.");
        }

        Map<String, Account> accounts = loadAccount();
        List<String> reportList = new ArrayList<>();

        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        String fromAccount = parts[0].trim();
                        String toAccount = parts[1].trim();
                        int amo;
                        try {
                            amo = Integer.parseInt(parts[2].trim());
                            processTransfer(new Transfer(fromAccount, toAccount, amo), accounts);
                            reportList.add(generateReportLine(file.getName(), fromAccount, toAccount, String.valueOf(amo), "successfully processed"));
                        } catch (NumberFormatException | InvalidTransferException e) {
                            reportList.add(generateReportLine(file.getName(), fromAccount, toAccount, parts[2].trim(), "error during processing, " + e.getMessage()));
                        }
                    }
                }
            } catch (IOException e) {
                throw new FileProcessingException("Error processing file: " + file.getName());
            }
            archiveFile(file);
        }

        saveAccount(accounts);
        saveReport(reportList);
    }


    private static void saveReport(List<String> reportLines) throws FileProcessingException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output_File, true))) {
            for (String line : reportLines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error saving report.");
        }
    }

    private static void archiveFile(File file) throws FileProcessingException {
        try {
            Files.move(file.toPath(), Paths.get(archive_Dir, file.getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileProcessingException("Error archiving file: " + file.getName());
        }
    }

    private static String generateReportLine(String fileName, String fromAccount, String toAccount, String
            amo, String status) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timestamp = sdf.format(new Date());
        return String.format("%s | %s | transfer from %s to %s %s | %s", timestamp, fileName, fromAccount, toAccount, amo, status);

    }

    private static void saveAccount(Map<String, Account> accounts) throws FileProcessingException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(accounts_File))) {
            for (Account account : accounts.values()) {
                bw.write(account.getAccountNumber() + " : " + account.getBalance());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error saving accounts.");
        }
    }

    private static Map<String, Account> loadAccount() throws FileProcessingException {
        Map<String, Account> accounts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(accounts_File))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String accountNumber = parts[0].trim();
                    int balance = Integer.parseInt(parts[1].trim());
                    accounts.put(accountNumber, new Account(accountNumber, balance));
                }
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error loading accounts.");
        }
        return accounts;
    }

    private static void processTransfer(Transfer transfer, Map<String, Account> accounts) throws
            InvalidTransferException {
        Account fromAccount = accounts.get(transfer.getFromAccount());
        Account toAccount = accounts.get(transfer.getToAccount());
        if (fromAccount == null || toAccount == null) {
            throw new InvalidTransferException("Invalid account number.");
        }
        if (transfer.getAmo() <= 0) {
            throw new InvalidTransferException("Invalid transfer amount.");
        }
        if (fromAccount.getBalance() < transfer.getAmo()) {
            throw new InvalidTransferException("Insufficient funds.");
        }
        fromAccount.setBalance(fromAccount.getBalance() - transfer.getAmo());
        toAccount.setBalance(toAccount.getBalance() + transfer.getAmo());
    }
}
