/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.iterator;

import es.urjc.vo.queue.MyQueue;
import es.urjc.vo.queue.NodeQueue;

/**
 *
 * @author Antho
 * @param <E>
 */
public class MyQueueIterator<E> implements IteratorInterface<E> {

    //Variables
    NodeQueue queue;
    NodeQueue current;
    
    /**
     * Constructor básico
     * @param q 
     */
    public MyQueueIterator(MyQueue q) {
        this.queue = q.getFront();
        this.current = q.getFront();
    }

    //Getters y setters
    public NodeQueue getQueue() {
        return queue;
    }

    public void setQueue(NodeQueue queue) {
        this.queue = queue;
    }

    public NodeQueue getCurrent() {
        return current;
    }

    public void setCurrent(NodeQueue current) {
        this.current = current;
    }
    
    //Métodos de la interfaz
    /**
     * Calculamos el siguiente elemento
     * @return 
     */
    @Override
    public E next() {
        E element = null;
        element = (E) current.getInfo();
        current = current.getNext();
        return element;
    }

    /**
     * ¿Tiene siguiente?
     * @return 
     */
    @Override
    public boolean hasNext() {
        if(current == null)
            return false;
        
        return true;
    }
    
}
