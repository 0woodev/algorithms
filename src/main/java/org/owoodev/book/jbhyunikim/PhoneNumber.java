package org.owoodev.book.jbhyunikim;

public class PhoneNumber {
    public final String phoneNumber;

    public PhoneNumber(String rawPhoneNumber) {
        this.phoneNumber = rawPhoneNumber.replaceAll("[^0-9]", "");
    }

    @Override
    public String toString() {
        return "PhoneNumber{phoneNumber='" + this.phoneNumber + "'}";
    }
}
