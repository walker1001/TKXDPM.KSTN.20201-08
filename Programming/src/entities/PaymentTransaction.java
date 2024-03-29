package entities;

import dataaccesslayer.PaymentTransactionDAO;

public class PaymentTransaction {
    private String rentalCode;
    private String cardCode;
    private String owner;
    private String transactionContent;
    private int amount;
    private String time;
    private String day;


    public PaymentTransaction(String rentalCode, String cardCode, String owner, String transactionContent, int amount, String time, String day) {
        this.rentalCode = rentalCode;
        this.cardCode = cardCode;
        this.owner = owner;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.time = time;
        this.day = day;
    }

    public void setRentalCode(String rentalCode) {
        this.rentalCode = rentalCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

//    public void setBalance(int balance) {
//        this.balance = balance;
//    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRentalCode() {
        return rentalCode;
    }

    public String getCardCode() {
        return cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public int getAmount() {
        return amount;
    }

//    public int getBalance() {
//        return balance;
//    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }
    // undone
    public void savePaymentTransaction(){
        PaymentTransactionDAO.save(rentalCode, cardCode, owner, transactionContent, amount, time, day);
    }
}
