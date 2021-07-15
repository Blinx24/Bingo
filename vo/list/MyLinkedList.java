/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.list;

import es.urjc.vo.cardboard.Cardboard;
import es.urjc.vo.iterator.MyLinkedIterator;


/**
 *
 * @author ignaciocallesquintero
 */
public class MyLinkedList implements ListInterface{
    
    //Variables
    Node linkedList;

    /**
     * Constructor básico
     */
    public MyLinkedList(){
        linkedList = null;    
    }

    /**
     * Creación iterador
     * @param list
     * @return 
     */
    public MyLinkedIterator createIterator(MyLinkedList list) {
        return new MyLinkedIterator(list);
    }

    //Getters y setters
    public Node getLinkedList() {
        return linkedList;
    }
    
    //Métodos de la interfaz
    /**
     * Añadir un cartón al principio de nuestra lista
     * @param cb 
     */
    @Override
    public void addFirst(Cardboard cb) {
        //Creamos un nuevo nodo cuya información es un cartón
        Node newNode = new Node(cb);
        
        //El nuevo nodo apunta al nodo que apuntaba la lista
        newNode.next = linkedList;
        
        //La lista apunta al nuevo nodo
        linkedList = newNode;     
    }

    /**
     * Añade un cartón al final de nuestra lista
     * @param cb 
     */
    @Override
    public void addFinal(Cardboard cb) {
        //Creamos un nuevo nodo que contiene nuestro cartón
        Node newNode = new Node(cb);
        
        //Si la lista está vacía
        if (isEmpty()){
            //La lista apunta al nuevo nodo
            linkedList = newNode;    
        }else{  
            //Establecemos un nodo auxiliar para buscar el final de la lista
            Node currentNode = linkedList;
            
            //Busca cuál es el último nodo de nuestra lista
            while (currentNode.next != null){   
                //Siguiente nodo
                currentNode = currentNode.next;
            }
            
            //El último nodo apunta al nuevo nodo insertado
            currentNode.next = newNode;
        }
    }

    /**
     * Eliminamos la lista
     */
    @Override
    public void clear() {
        linkedList = null;
    }

    /**
     * Determina si la lista está vacía
     * @return 
     */
    @Override
    public Boolean isEmpty() {
        return (linkedList == null);
    }
    
}

    

        

