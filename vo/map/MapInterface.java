/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.map;

import es.urjc.vo.cardboard.Cardboard;

/**
 *
 * @author Antho
 */
public interface MapInterface {
    
    public Integer size();
    public Boolean isEmpty();
    public void put(String key, Cardboard cb);
    public Integer hash(String key, Integer max);
    
}
