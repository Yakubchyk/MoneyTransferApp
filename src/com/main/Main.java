package com.main;

import com.main.service.TransferService;

public class Main {
    public static void main(String[] args) {
        TransferService transferService = new TransferService();
        transferService.start();

    }
}
