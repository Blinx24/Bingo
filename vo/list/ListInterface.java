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
public interface ListInterface {
    
    void addFirst(Cardboard element );
    void addFinal(Cardboard element);
    void clear();
    Boolean isEmpty();

}

