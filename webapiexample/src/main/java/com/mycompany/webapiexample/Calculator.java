/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapiexample;

/**
 *
 * @author x08424179
 */
public class Calculator {
    double num1, num2;
    String result;
    
    //This is what get printed when you use Gson.toJson() method to convert this class
    public Calculator(double n1, double n2){
        num1 = n1;
        num2 = n2;
        
        result = "Number 1 + number 2:" + (num1+num2);        
    }
}
