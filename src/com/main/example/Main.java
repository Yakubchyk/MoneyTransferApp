package com.main.example;

import com.main.example.service.TransferService;

public class Main {
    public static void main(String[] args) {
        TransferService transferService = new TransferService();
        transferService.start();

    }
}
