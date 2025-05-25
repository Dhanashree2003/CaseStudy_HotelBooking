package com.hexaware.cozyHeaven.hotelBooking.entity;



public class PaymentDetails {

    public enum PaymentMethod {
        CreditCard,
        DebitCard,
        UPI,
        NetBanking,
        Wallet
    }

    public enum PaymentStatus {
        Success,
        Failed,
        Pending
    }
}
