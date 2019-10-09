/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingwithhsqldb;

/**
 *
 * @author pedago
 */
public class ProductEntity {
    
    private int ID;
    private String name;
    private int Price;

public ProductEntity(int id,String nom,int prix){
    this.ID=id;
    this.name=nom;
    this.Price=prix;
}

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return Price;
    }
    
}
