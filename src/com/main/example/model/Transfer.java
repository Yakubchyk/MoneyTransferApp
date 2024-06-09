package com.main.example.model;

public class Transfer {
    private String fromAccount;
    private String toAccount;
    private double amo;

    public Transfer(String fromAccount, String toAccount, double amo) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amo = amo;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public double getAmo() {
        return amo;
    }
}
