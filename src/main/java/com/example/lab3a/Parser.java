package com.example.lab3a;

public class Parser {
    public static double stringParseDouble(String str) throws NumberFormatException{
        try{
            return Double.parseDouble(str.replaceAll(",", "."));
        }catch (NumberFormatException exception){
            throw new NumberFormatException();
        }
    }
}
