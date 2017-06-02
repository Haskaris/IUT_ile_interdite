/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author reyneu
 */
public class Message {
    private TypesMessage type;
    
    public Message(TypesMessage type){
        setTypeMessage(type);
    }
    
    public void setTypeMessage(TypesMessage type){
        this.type = type;
    }
    
    public TypesMessage getTypeMessage(){
        return type;
    }
}