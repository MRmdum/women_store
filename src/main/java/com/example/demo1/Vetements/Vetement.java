package com.example.demo1.Vetements;

public class Vetement extends Produit{

    int taille;

    public Vetement(String nom, double prix, int nbExamplaires,int taille) throws IllegalArgumentException{
        super(nom, prix, nbExamplaires);
        if(taille%2 ==0 && taille>=34 && taille <= 54) 
            this.taille = taille;
        else throw new IllegalArgumentException("Taille doit être paire et comprise entre 34/54");
    }

    public int Taille(){
        return this.taille;
    }
    void setTaille(int value){
        this.taille = value;
    }
    @Override
    public String toString() {       
        return super.toString()+String.format("Taille: %s\n", this.taille);
    }
    
}
