/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.queue;

import es.urjc.vo.cardboard.Cardboard;

/**
 *
 * @author herna
 */
public class NodeQueue {
    //Atributos
    Cardboard info;
    NodeQueue next;  
    
    /**
     * Constructor básico
     * @param elem 
     */
    public NodeQueue(Cardboard elem) {
        this.info = elem;
        this.next = null;
    }
    
    //Getters y setters
    public Cardboard getInfo() {
        return info;
    }

    public void setInfo(Cardboard info) {
        this.info = info;
    }

    public NodeQueue getNext() {
        return next;
    }

    public void setNext(NodeQueue next) {
        this.next = next;
    }
      
}


