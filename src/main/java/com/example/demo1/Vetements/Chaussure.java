package com.example.demo1.Vetements;

public class Chaussure extends Produit {
    int pointure;
    public Chaussure(String nom,double prix, int nbExamplaires,int pointure)throws IllegalArgumentException{
        super(nom, prix, nbExamplaires);
        if(pointure>=36 && pointure <= 50) 
            this.pointure = pointure;
        else throw new IllegalArgumentException("pointure doit être comprise entre 36/50");

    }
    
    public int Pointure(){
        return this.pointure;
    }

    void setTaille(int value){
        this.pointure = value;
    }

    @Override
    public String toString() {
        return super.toString()+String.format("Pointure: %s", this.pointure);
    }
}
