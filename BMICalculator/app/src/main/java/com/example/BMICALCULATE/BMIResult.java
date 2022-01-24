package com.example.BMICALCULATE;

public class BMIResult {
    public double weight, height, index;
    public int id;
    public String indexValue;

    public BMIResult(){}

    public BMIResult(double weight, double height, double index){
        this.weight = weight;
        this.height = height;
        this.index = index;
        this.indexValue = getValue(index);
    }

    public String getValue(double index){
        String val;
        if(index <= 18.4)
            val = "Underweight\nMALNUTRITION RISK";
        else if(index <= 25)
            val = "Normal weight\nLOW RISK";
        else if(index <= 30)
            val = "Overweight\nENCHANCED RISK";
        else if(index <= 35)
            val = "Moderately obese\nMEDIUM RISK";
        else if(index <= 40)
            val = "Severely obese\nHIGH RISK";
        else
            val = "very severely obese\nVERY HIGH RISK";
        return val;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setWeight(double w){
        weight = w;
    }

    public void setHeight(double h){
        height = h;
    }

    public void setIndex(double id){
        index = id;
    }

    public void setIndexValue(String idVal){
        indexValue = idVal;
    }

    public int getID(){
        return id;
    }


    public double getWeight(){
        return weight;
    }

    public double getHeight(){
        return height;
    }

    public double getIndex(){
        return index;
    }

    public String getIndexValue(){
        return indexValue;
    }
}
