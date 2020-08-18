package loanCalculator;

import java.io.Serializable;

public class Loan implements Serializable {

    private double AIR;
    private double NOY;
    private double LA;

    public Loan(double AIR, double NOY, double LA){
        this.AIR=AIR;
        this.NOY=NOY;
        this.LA=LA;
    }

    public double getAIR() {
        return AIR;
    }

    public double getNOY() {
        return NOY;
    }

    public double getLA() {
        return LA;
    }

    public double getMonthlyPayment(){
        return LA*((AIR/100))/12/(1-Math.pow((1+(AIR/100)/12),-NOY*12));
    }

    public double getTotalPayment(){
        return (getMonthlyPayment()*12)*3;
    }
}
