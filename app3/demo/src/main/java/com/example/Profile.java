package com.example;

public class Profile {

    public String nom , prenom , pseudo ;

    public Profile(String nom , String prenom  , String pseudo)
    {
        this.nom    = nom    ;
        this.prenom = prenom ;
        this.pseudo = pseudo ;
    }

    @Override
    public String toString()
    {
        return "Pseudo =" + this.pseudo;
    }

    
}
