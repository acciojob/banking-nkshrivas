package com.driver;

import java.util.*;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance);
        setTradeLicenseId(tradeLicenseId);
        setMinBalance(5000);
        if(getMinBalance()>balance){
            throw new Exception("Insufficient Balance");
        }


    }

    public void validateLicenseId() throws Exception {

        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if (tradeLicenseId == null || tradeLicenseId.length() == 0) {
            throw new Exception("Invalid Trade License ID");
        }





        if (!canCreateValidTradeLicenseId(tradeLicenseId)) {
            throw new Exception("Valid License can not be generated");
        }
        else if(!isValidTradeLicenseId(tradeLicenseId)){
            generateValidTradeLicenseId(tradeLicenseId);
        }


    }

    private boolean canCreateValidTradeLicenseId(String tradeLicenseId) {

        HashMap<Character,Integer> hm = new HashMap<>();
        int n=tradeLicenseId.length();

        for(int i=0;i<tradeLicenseId.length();i++){
            char ch=tradeLicenseId.charAt(i);
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }

        for(Integer value:hm.values()){
            if(n%2==0 && value>n/2+1){
                return false;
            }
            else if(n%2==1 && value>n/2+2){
                return false;
            }
        }
        return true;
    }

    private  boolean isValidTradeLicenseId(String tradeLicenseId) {
        char prevChar = tradeLicenseId.charAt(0);
        for (int i = 1; i < tradeLicenseId.length(); i++) {
            char currChar = tradeLicenseId.charAt(i);
            if (prevChar == currChar) {
                return false;
            }
            prevChar = currChar;
        }
        return true;
    }
    private  String generateValidTradeLicenseId(String tradeLicenseId) {
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < tradeLicenseId.length(); i++) {
            charList.add(tradeLicenseId.charAt(i));
        }

        Collections.shuffle(charList);
        StringBuilder sb = new StringBuilder();
        for (char c : charList) {
            sb.append(c);
        }
        String shuffledId = sb.toString();
        if (isValidTradeLicenseId(shuffledId)) {
            return shuffledId;
        } else {
            return generateValidTradeLicenseId(shuffledId);
        }
    }


}
