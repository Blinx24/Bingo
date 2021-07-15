/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.iterator;

import es.urjc.vo.list.MyLinkedList;
import es.urjc.vo.list.Node;


/**
 *
 * @author Antho
 * @param <E>
 */
public class MyLinkedIterator<E> implements IteratorInterface<E>{

    //Variables
    Node list;
    Node current;

    /**
     * Constructor básico
     * @param list 
     */
    public MyLinkedIterator(MyLinkedList list) {
        this.list = list.getLinkedList();
        this.current = list.getLinkedList();
    }
    
    //Métodos de la interfaz
    /**
     * Obtenemos el elemento siguiente
     * @return 
     */
    @Override
    public E next() {
        //Vaciamos el elemento actual
        E element = null;
        
        //Hallamos el que nos corresponde
        element = (E) current.getInfo();
        
        //Calculamos el siguiente
        current = current.getNext();
        
        //Devolvemos el elemento
        return element;
    }

    /**
     * ¿Tiene siguiente la lista?
     * @return 
     */
    @Override
    public boolean hasNext() {
        //Si el nodo actual está vacío
        if(current == null)
            return false;
        
        return true;
    }

    //Getters y setters
    public Node getList() {
        return list;
    }

    public void setList(Node list) {
        this.list = list;
    }

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }
}
