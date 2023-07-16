/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package src;

import java.text.DecimalFormat;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author alinabila
 */
@WebService(serviceName = "HealthCalculatorWS")
public class HealthCalculatorWS {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    double heightw, weightw;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getName")
    public String getName(@WebParam(name = "name") String name) {
        return name;
    }

    @WebMethod(operationName = "getIc")
    public String getIc(@WebParam(name = "ic") String ic) {
        return ic;
    }
//
    @WebMethod(operationName = "getHeight")
    public double getHeight(@WebParam(name = "height") double height) {
        return height;
    }
//
    @WebMethod(operationName = "getWeight")
    public double getWeight(@WebParam(name = "weight") double weight) {
        return weight;
    }
//
    @WebMethod(operationName = "getGender")
    public String getGender(@WebParam(name = "gender") String gender) {
        String newGender = gender.toLowerCase();
        return newGender;
    }
//
    @WebMethod(operationName = "getAge")
    public int getAge(@WebParam(name = "ic") String ic) {
        String newIC = getIc(ic);
        int age = 22 - Integer.valueOf(newIC.substring(0, 2));
        return age;
    }
//
    @WebMethod(operationName = "bmi")
    public String bmi(@WebParam(name = "height") double h, @WebParam(name = "weight") double w) {
        String bmi = df.format(w / (h * h));
        return bmi;
    }
//
    @WebMethod(operationName = "getBodyFat")
    public String getBodyFat(@WebParam(name = "height") double h, @WebParam(name = "weight") double w, @WebParam(name = "ic") String ic) {
        String bmi = bmi(h, w);
        int age = getAge(ic);
        String bf = df.format((120 * Double.parseDouble(bmi)) + (0.23 * age) - 16.2);
        return bf;
    }

    @WebMethod(operationName = "getCaloryBurn")
    public String getCaloryBurn(@WebParam(name = "time") String time, @WebParam(name = "height") double h, @WebParam(name = "weight") double w, @WebParam(name = "ic") String ic, @WebParam(name = "gender") String g) {
        double height = getHeight(h);
        double weight = getWeight(w);
        int age = getAge(ic);
        String gender = getGender(g);
        String newBMR = null;
        double caloryBurned = 0;
        if (gender.equals("male")) {
            caloryBurned = (13.75 * weight) + (5 * height) - (6.76 * age) + 66;
        } else if (gender.equals("female")) {
            caloryBurned = (9.56 * weight) + (1.85 * height) - (4.68 * age) + 655;
        }
        switch (time.toLowerCase()) {
            case "no exercise":
                newBMR = df.format(caloryBurned * 1.2);
                break;
            case "1-3 days":
                newBMR = df.format(caloryBurned * 1.375);
                break;
            case "3-5 days":
                newBMR = df.format(caloryBurned * 1.55);
                break;
            case "most days":
                newBMR = df.format(caloryBurned * 1.725);
                break;
            case "every day":
                newBMR = df.format(caloryBurned * 1.9);
                break;
            default:
                break;
        }

        return newBMR;

    }

}
