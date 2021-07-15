/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.iterator;

/**
 *
 * @author Antho
 * @param <E>
 */
public interface IteratorInterface<E>{
    
    public E next();
    public boolean hasNext();
}
