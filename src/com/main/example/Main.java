package com.main.example;

import com.main.example.exception.FileProcessingException;
import com.main.example.service.FileParserService;
import com.main.example.service.TransferServise;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransferServise transferServise = new TransferServise();
        transferServise.start();
    }
}
