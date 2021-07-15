/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.list;

import es.urjc.vo.cardboard.Cardboard;

/**
 *
 * @author ignaciocallesquintero
 */
public class Node {
    //Variables
    Cardboard info;
    Node next;

    /**
     * Constructor básico
     * @param elem 
     */
    public Node(Cardboard elem) {
        this.info = elem;
        this.next = null;
    }

    //Getters y setters
    public Cardboard getInfo() {
        return info;
    }

    public Node getNext() {
        return next;
    }
    
}
