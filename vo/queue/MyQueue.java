/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.queue;

import es.urjc.vo.cardboard.Cardboard;
import es.urjc.vo.iterator.MyQueueIterator;

/**
 *
 * @author herna
 */
public class MyQueue{
    //Atributos 
    private NodeQueue end;
    private NodeQueue front;
    private int size;

    /**
     * Constructor básico
     */
    public MyQueue() {
        this.end = null;
        this.front = null;
        this.size = 0;
    }

    //Getters y setters
    public NodeQueue getEnd() {
        return end;
    }

    public void setEnd(NodeQueue end) {
        this.end = end;
    }

    public NodeQueue getFront() {
        return front;
    }

    public void setFront(NodeQueue front) {
        this.front = front;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    //Creación iterator
    public MyQueueIterator createIterator(MyQueue q) {
        return new MyQueueIterator(q);
    }

    /**
     * Añadimos un carton a nuestra cola
     * @param a
     */
    public void add (Cardboard a){
        //Creamos un nuevo nodo
        NodeQueue newNode = new NodeQueue(a);
        
        //Si estaba vació en un principio
        if(isEmpty()){
            //El principio y final apuntan al nuevo nodo
            front = newNode;
            end = newNode;
        }
        else {
            //El siguiente al final es el nuevo nodo y final apunta a ese nodo
            end.next = newNode;
            end = newNode;
        }
        
        //Aumentamos size
        size++;
    }

    /**
     * Eliminamos un número de nuestro conjunto
     * @param a
     * @return 
     */
    public Cardboard remove (Cardboard a) {
        //Comprobamos si la cola está vacía
        if (isEmpty()) {
            return null;
        }
        else {
            //Sacamos el primer elemento
            a = front.info;
            
            //Frente apunta al siguiente
            front = front.next;
        }    
        
        //Disminuimos size
        size--;
        
        //Devolvemos el elemento
        return a;
    }
    
    /**
     * Reestablecemos la cola
     */
    public void clear(){
        end = null;
        front = null;
    }
    
    /**
     * Devuelve el número de elementos que se encuentran dentro de la cola
     * @return 
     */
    public Integer size() {
        return size;
    }

    /**
     * Comprobamos si la cola está vacía
     * @return 
     */
    public boolean isEmpty(){ 
        return (front == null);
    }

}
