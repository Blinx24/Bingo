/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.main;

import es.urjc.vo.*;

/**
 *
 * @author Antho
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Instancias de la clase principal de la clase
        Match m = new Match();
        
        //Accedemos al menú
        m.menu();
    }
    
}
