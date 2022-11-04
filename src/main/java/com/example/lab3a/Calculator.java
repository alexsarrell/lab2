package com.example.lab3a;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.stream.IntStream;

public class Calculator {
    public static ObservableList<MclarenDto> calculateAreaMclaren(double from, double to, double step, double eps){
        ObservableList<MclarenDto> results = FXCollections.observableArrayList();
        for(double i = from; i < to; i += step){
            MclarenDto mclarenDTO = new MclarenDto();
            mclarenDTO.setX(i);
            mclarenDTO.setValue(calculateMclaren(i, eps));
            mclarenDTO.setStandardValue(Math.sqrt(1 + i));
            results.add(mclarenDTO);
        }
        return results;
    }
    public static double calculateMclaren(double value, double eps){
        double previousValue = 0;
        double startValue = value;
        int degree = 0;
        while(Math.abs(value - previousValue) > eps){
            previousValue = value;
            value += getDecimal(startValue, degree);
            degree++;
        }
        return value - startValue;
    }
    private static double getDecimal(double value, int degree){
        System.out.println(Math.pow(-1, degree) * factorial(2 * degree) * Math.pow(value, degree) / ((1 - 2 * degree) * Math.pow(factorial(degree), 2) * Math.pow(4, degree)));
        return Math.pow(-1, degree) * factorial(2 * degree) * Math.pow(value, degree) / ((1 - 2 * degree) * Math.pow(factorial(degree), 2) * Math.pow(4, degree));
    }
    private static int factorial(int val){
        int res = 1;
        for(int i = 1; i <= val; i++){
            res = res * i;
        }
        return res;
    }
}
