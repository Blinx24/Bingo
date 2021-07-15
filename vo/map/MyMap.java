/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.map;

import es.urjc.vo.cardboard.Cardboard;
import es.urjc.vo.iterator.MyMapIterator;

/**
 *
 * @author Antho
 */
public class MyMap implements MapInterface{
    //Variables
    private Cardboard map[];
    private Integer size, maxElems;

    /**
     * Constructor básico
     * @param max 
     */
    public MyMap(Integer max) {
        this.map = (Cardboard[]) new Cardboard[max];
        this.size = 0;
        this.maxElems = max;
    }

    //Getters y setters
    public Cardboard[] getMap() {
        return map;
    }

    public void setMap(Cardboard[] map) {
        this.map = map;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getMaxElems() {
        return maxElems;
    }

    public void setMaxElems(Integer maxElems) {
        this.maxElems = maxElems;
    }

    public MyMapIterator createIterator(MyMap map) {
        return new MyMapIterator(map);
    }
    
    //Métodos de la interfaz
    /**
     * Obtenemos el número de elementos en el array
     * @return 
     */
    @Override
    public Integer size() {
        return size;
    }

    /**
     * Comprobamos si está vacío
     * @return 
     */
    @Override
    public Boolean isEmpty() {
        return (size==0);
    }

    /**
     * Colocamos un elemento según su clave
     * @param key
     * @param cb 
     */
    @Override
    public void put(String key, Cardboard cb) {
        //Calculamos la posición correspondiente
        Integer position = hash(key,maxElems);
        
        //Si no hay hueco disponible (colisión)
        while(map[position]!= null) {
            //Si salimos de los límites
            if(position >= maxElems-1)
                //Vuelta al principio
                position = 0;
            
            position++;               
        } 
        
        //Asignamos el cartón a su posición
        map[position] = cb;
        
        //Aumentamos size
        size++;
    }

    /**
     * Calculamos la posición del elemento en el array
     * @param key
     * @param max
     * @return 
     */
    @Override
    public Integer hash(String key, Integer max) {
        //Variables
        Integer hashValue = 0;
        
        //Bucle para calcular el entero correspondiente a la clave
        for (int i = 0; i < key.length(); i++) {
            hashValue = 37 * hashValue + key.charAt(i);
        }
        
        //Cálculo de la posición
        hashValue %= max;
        
        //Si sale negativo
        if(hashValue < 0)
            hashValue += max;
        
        //Devolvemos la posición
        return hashValue;
    }
}
