/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.cardboard;

import es.urjc.vo.set.MySet;
import java.security.SecureRandom;
import java.math.BigInteger;

/**
 *
 * @author ignaciocallesquintero
 */
public class Cardboard {
    //Variables
    private String code;    //Código identificador
    private MySet line1;    //Líneas
    private MySet line2;
    private MySet line3;
    private Integer blankNumber;    //Número de tachados

    /**
     * Constructor básico
     */
    public Cardboard() {
        this.code = "";
        this.line1 = null;
        this.line2 = null;
        this.line3 = null;
        this.blankNumber = 0;
    }

    //Getters y setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MySet getLine1() {
        return line1;
    }

    public void setLine1(MySet line1) {
        this.line1 = line1;
    }

    public MySet getLine2() {
        return line2;
    }

    public void setLine2(MySet line2) {
        this.line2 = line2;
    }

    public MySet getLine3() {
        return line3;
    }

    public void setLine3(MySet line3) {
        this.line3 = line3;
    }

    public Integer getBlankNumber() {
        return blankNumber;
    }

    public void setBlankNumber(Integer blankNumber) {
        this.blankNumber = blankNumber;
    }

    /**
     * Obtenemos una clave aleatoria y la asignamos a la clave del cartón
     */
    public void getRandomCode(){
        SecureRandom r = new SecureRandom();
        String text = new BigInteger(25, r).toString(32);
        setCode(text);   
    }
    
}
