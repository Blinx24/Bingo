/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo.set;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Antho
 */
public class MySet implements SetInterface{
    //Variables
    private Integer set[];          //Implementación mediante arrays
    private int cardinal, max;   //Número de elementos

    /**
     * Constructor
     * @param maxElems 
     */
    public MySet(Integer maxElems) {
        this.max = maxElems;
        this.set = (Integer[]) new Integer[maxElems];
        this.cardinal = 0;
    }

    //Getters y setters
    public Integer[] getSet() {
        return set;
    }

    public void setSet(Integer[] set) {
        this.set = set;
    }

    public int getCardinal() {
        return cardinal;
    }

    public void setCardinal(int cardinal) {
        this.cardinal = cardinal;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    //Métodos importados de la interfaz
    /**
     * Imprimimos el conjunto por pantalla
     * @return 
     */
    @Override
    public String toString(){
       String s = "{ ";
        for (int i = 0; i < cardinal; i++) {
            s += (set[i] + " ");
        }
        
        s = s + "}";
        return s;
    }
            
    /**
     * Añadimos un número a nuestro conjunto
     * @param num 
     */
    @Override
    public void addNumber(Integer num) {
        if(!containsNumber(num)){
            if(cardinal == max){
                //Creamos un conjunto auxiliar con mayor tamaño
                Integer[] B = (Integer[]) new Object[max*2];
                
                //Asignamos los valores del conjunto original al nuevo
                for(int i = 0; i < max; i++)
                    B[i] = set[i];  
                
                max = max*2;    //Insertamos el nuevo valor a max
                set = B;    //Asignamos al conjunto original el nuevo con mayor tamaño  
            }
            
            set[cardinal] = num;    //Guardamos el número en el array
            cardinal++; //Aumentamos la variable cardinal
        }
    }

    /**
     * Eliminamos un número de nuestro conjunto
     * @param number
     * @return 
     */
    @Override
    public Integer removeNumber(Integer number) {
        if(containsNumber(number)){
            //Índice auxiliar
            Integer pos = 0;
            //Variable para indicar si se ha encontrado
            Boolean found = false;
            
            //Hallamos la posición en la que se encuetra
            while(!found){
                //En caso de que coincidan los números
                if(Objects.equals(set[pos], number))
                    found = true;
                
                //Seguimos avanzando
                pos++;
            }
            
            //Desplazamos elementos para eliminar el número
            for (int i = pos-1; i < cardinal-1; i++) {
                set[i] = set[i+1]; 
            }
              
            //Disminuimos en uno el cardinal
            cardinal--;
            
            //Devolvemos el número introducido
            return number;
        }else{
            //Si no contiene el número introducido
            return -1;
        }
    }

    /**
     * Comprobamos si cierto número está en el conjunto
     * @param number
     * @return 
     */
    @Override
    public Boolean containsNumber(Integer number) {
        //Variables
        Boolean found = false;
        Integer i = 0;
        
        //Búsqueda elemento
        while(i < cardinal && !found){
            //Comprobación
            found = set[i].equals(number);
            
            //Aumentamos índice
            i++;
        }
        
        //Retorno
        return found;
    }

        @Override
    public Boolean containsSet(MySet b) {
        Integer numFound = 0;
        Integer i = 0;
        Integer j = 0;
        
        while(i < max && numFound != b.max){
            if(this.containsNumber(b.getSet()[j])){
                numFound ++;
                j++;
                i = 0;
            }
            i++;
        }
        
        return numFound == b.max;
    }
    
    /**
     * Devuelve el número de elementos que se encuentran dentro del conjunto
     * @return 
     */
    @Override
    public Integer size() {
        return cardinal;
    }

    /**
     * Comprobamos si el conjunto está vacío
     * @return 
     */
    @Override
    public Boolean isEmpty() {
        return (cardinal == 0);
    }

    /**
     * Reestablecemos el conjunto
     */
    @Override
    public void clear() {
        //Creamos un nuevo array
        Integer setB[];
        setB = (Integer[]) new Integer[max];
        
        //Asignamos al antiguo array el nuevo
        set = setB;
        
        //Reestablecemos la variable cardinal
        cardinal = 0;
    }
    
    /**
     * Hallamos un número aleatorio, lo eminimamos y lo devolvemos
     * @return
     * @throws NoSuchElementException 
     */
    @Override
    public Integer choose() throws NoSuchElementException{
        //Variables
        Random r = new Random();
        Integer num, i;
        
        //Si el conjunto está vacío
        if(cardinal == 0)
            throw new NoSuchElementException("Error en choose: "
                    + "conjunto vacío.");
        
        //Calculamos un índice aleatorio
        i = Math.abs(r.nextInt()%cardinal);
        
        //Hallamos a qué número corresponde dicho índice
        num = set[i];
        
        //Eliminamos ese número
        removeNumber(set[i]);
        
        //Lo devolvemos
        return num;
    }   
    
}
