/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.iterator;

import es.urjc.vo.map.MyMap;

/**
 *
 * @author Antho
 * @param <E>
 */
public class MyMapIterator<E> implements IteratorInterface<E> {

    //Variables
    MyMap map;
    Integer pos;

    /**
     * Constructor del iterador
     * @param map 
     */
    public MyMapIterator(MyMap map) {
        this.map = map;
        this.pos = 0;
    }

    //Getters y setters
    public MyMap getMap() {
        return map;
    }

    public void setMap(MyMap map) {
        this.map = map;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
    
    //Métodos de la interfaz
    /**
     * Cálculo del siguiente elemento
     * @return 
     */
    @Override
    public E next() {
        //Hallamos el elemento
        E elem = (E) map.getMap()[pos];
        
        //Pasamos a la siguiente posición
        pos++;
        
        //Devolvemos el elemento
        return elem;
    }

    /**
     * ¿Tiene siguiente?
     * @return 
     */
    @Override
    public boolean hasNext() {
        //Si encontramos un elemento vacío o superamos el límite
        if(map.getMap().length <= pos || map.getMap()[pos] == null)
            return false;
        
        return true;
    }
    
}
