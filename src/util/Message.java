/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.cartesOrange.CarteDosOrange;

/**
 *
 * @author reyneu
 */
public class Message {
    private TypesMessage type;
    private int numBtn;
    private CarteDosOrange carteADonner;
    private String str;
    
    
    public Message(TypesMessage type){
        setTypeMessage(type);
    }
    
    public void setTypeMessage(TypesMessage type){
        this.type = type;
    }
    
    public TypesMessage getTypeMessage(){
        return type;
    }

    public void setNumBtn(int numBtn) {
        this.numBtn = numBtn;
    }
    public int getNumBtn() {
        return numBtn;
    }
    
    public void setCarte(CarteDosOrange carte){
        this.carteADonner = carte;
    }
    
    public CarteDosOrange getCarte(){
        return carteADonner;
    }
    
    public void setString(String str){
         this.str = str;
    }
    
    public String getString(){
        return str;
    }
     
}