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
    private int numBtn;
    
    
    public Message(TypesMessage type){
        setTypeMessage(type);
    }

    public Message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}