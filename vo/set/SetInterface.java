/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.set;

/**
 *
 * @author Antho
 */
public interface SetInterface {
    
    void addNumber(Integer num);
    Integer removeNumber(Integer number);
    Boolean containsNumber(Integer number);
    Boolean containsSet(MySet b);
    Integer size();
    Boolean isEmpty();
    void clear();
    Integer choose();
    
}
