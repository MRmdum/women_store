package com.example.demo1.Vetements;

public class Produit implements Solde,Comparable {
    String nom;
    double prix;
    int nbExamplaires;
    int numero;

    private static int totalCount =0;
    private static double recette =0;

    public Produit(String nom, double prix, int nbExamplaires) throws IllegalArgumentException{
        this.nom = nom;
        if(prix >0 && nbExamplaires>0){
            this.nbExamplaires = nbExamplaires;
            this.prix = prix;
        }
        else throw new IllegalArgumentException("Price and number of products must be positivs");

        totalCount++;
        numero = totalCount;

    }
    String Nom() {return this.nom;}
    public Double Prix() {return this.prix;}
    double Recette() {return Produit.recette;}
    int NbExamplaires() {return this.nbExamplaires;}
    int Numero() {return this.numero;}

    void setNom(String value) {this.nom = value;}
    void setPrix(double value) {this.prix = value;}
    void setRecette(double value) {Produit.recette = value;}
    void setNbExamplaires(int value) {this.nbExamplaires = value;}

    void vendre(int numberSold) throws IllegalArgumentException{
        if(numberSold > this.nbExamplaires){
            throw new IllegalArgumentException("Number sold can't greater than number of available products");
        }
        else{
            this.nbExamplaires -= numberSold;
            Produit.recette += this.prix*numberSold;
        }
    }

    void achat(int numbreAcheter){
        this.nbExamplaires += numbreAcheter;
    }

    public void remise(){
        switch (this.getClass().getSimpleName()) {
            case "Chaussure":
                this.prix *= 0.8;
                break;

            case "Accessoire":
                this.prix *= 0.5;
                break;

            case "Vetement":
                this.prix *= 0.7;
                break;
            default:
                break;
        }
    }

    public int compareTo(Produit a){
        if(this.prix > a.Prix()) return 1;
        else if(this.prix<a.Prix()) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        String retour = "Type: %s\n";
        retour += "Nom: %s\n";
        retour +="Number: %s\n";
        retour +="Price: %s\n";
        //retour +="RecetteTotal: %s\n";
        retour +="NbExemplaires: %s\n";

        retour = String.format(retour, this.getClass().getSimpleName(),
                                        this.nom,
                                        this.numero,
                                        String.format("%.2f",this.prix),
                                        //Produit.recette,
                                        this.nbExamplaires);
        return retour;
    }

}
