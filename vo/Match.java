/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.urjc.vo;

import es.urjc.vo.cardboard.Cardboard;
import es.urjc.vo.iterator.MyLinkedIterator;
import es.urjc.vo.iterator.MyMapIterator;
import es.urjc.vo.iterator.MyQueueIterator;
import es.urjc.vo.list.MyLinkedList;
import es.urjc.vo.map.MyMap;
import es.urjc.vo.set.MySet;
import es.urjc.vo.queue.MyQueue;
import java.util.Scanner;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Antho
 */
public class Match {
    //Variables 
    private Integer numPlayers, numAdversaries, //Número de jugadores y adversarios
            round = 0;  //Número de rondas
    private Boolean lineRewards,    //¿Se puede cantar línea?
            line = false,   //Alguien ha cantado línea
            sangLine = false,   //Se ha terminado de cantar línea
            configured = false, //El juego se ha configurado
            bingo = false;  //Alguien ha cantado bingo
    private MySet rotatingDrum, //Bombo
            table;  //Mesa
    private MyMap players, //Mapa de los jugadores - original
            editablePlayers;    //Mapa de los jugadores - editado durante partida
    private MyLinkedList adversaries,   //Lista de adversarios - original
            editableAdversaries;    //Lista de adversarios - editado durante partida
    private MyQueue lineWinners = new MyQueue(),    //Cola de cartones que han cantado línea
            bingoWinners = new MyQueue();   //Cola de cartones que han cantado bingo 

    /**
     * Constructor básico para acceder a esta clase
     */
    public Match() {
    }

    //Getters y setters
    public Integer getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Integer getNumAdversaries() {
        return numAdversaries;
    }

    public void setNumAdversaries(Integer numAdversaries) {
        this.numAdversaries = numAdversaries;
    }

    public Boolean getLineRewards() {
        return lineRewards;
    }

    public void setLineRewards(Boolean lineRewards) {
        this.lineRewards = lineRewards;
    }

    public MySet getRotatingDrum() {
        return rotatingDrum;
    }

    public void setRotatingDrum(MySet rotatingDrum) {
        this.rotatingDrum = rotatingDrum;
    }

    public MySet getTable() {
        return table;
    }

    public void setTable(MySet table) {
        this.table = table;
    }

    public Boolean getConfigured() {
        return configured;
    }

    public void setConfigured(Boolean configured) {
        this.configured = configured;
    }

    public MyMap getPlayers() {
        return players;
    }

    public void setPlayers(MyMap players) {
        this.players = players;
    }

    public MyLinkedList getAdversaries() {
        return adversaries;
    }

    public void setAdversaries(MyLinkedList adversaries) {
        this.adversaries = adversaries;
    }

    public Boolean getBingo() {
        return bingo;
    }

    public void setBingo(Boolean bingo) {
        this.bingo = bingo;
    }

    public MyMap getEditablePlayers() {
        return editablePlayers;
    }

    public void setEditablePlayers(MyMap editablePlayers) {
        this.editablePlayers = editablePlayers;
    }

    public MyLinkedList getEditableAdversaries() {
        return editableAdversaries;
    }

    public void setEditableAdversaries(MyLinkedList editableAdversaries) {
        this.editableAdversaries = editableAdversaries;
    }
    
    //Métodos propios
    /**
     * Método para llamar al menú inicial
     */
    public void menu(){
        //Variables
        Integer op;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Bienvenido al Bingo.");
        System.out.println("Juego realizado por: Lucas Hernández Luelmo, "
                + "Ignacio Calles Quintero y Antonio Rafael Ruano "
                + "Rodríguez");
        //Menú
        do{
            System.out.println("**********************");
            System.out.println("********* MENÚ *********");
            System.out.println("1. Nueva Partida.");
            System.out.println("2. Configuración.");
            System.out.println("3. Salir.");
            
            //Elección de opción
            System.out.print("- Inserte una opción: ");
            op = input.nextInt();
            
            //Validación
            while(op < 1 || op > 3){
                System.out.println("<> Opción no correcta.");
                System.out.print("- Introduce de nuevo la opción: ");
                op = input.nextInt();
            }
            
            //Acceso a opciones
            switch(op){
                case 1:
                    if(configured){
                    System.out.println("*******************************");
                    startMatch();
                    System.out.println("*******************************");
                    playMatch();
                    }else{
                        System.out.println("<> ¡Debes configurar primero tu partida!");
                    }
                    break;
                case 2:
                    configuration();
                    break;
                case 3:
                    System.out.println("¡Adiós!");
                    break;
            }
        }while(op != 3);
    }
    
    /**
     * Prepara la partida para su inicio
     */
    public void startMatch(){
        //Inicio
        System.out.println("********* NUEVA PARTIDA *********");
        System.out.println("Preparando su juego...");
        pauseExecution();
            
        //Bombo
        System.out.println("Preparando el bombo...");
        createRotatingDrum();
        pauseExecution();
        System.out.println("¡Bombo creado!");
            
        //Jugadores
        System.out.println("Creando jugadores...");
        createPlayers();
        pauseExecution();
        System.out.println("¡Jugadores creados!");
        
        //Mostrar jugadores
        System.out.println("Mostrando cartones de los jugadores...");
        showCardboards();
        pauseExecution();
            
        //Adversarios
        System.out.println("Creando adversarios...");
        createAdversaries();
        pauseExecution();
        System.out.println("¡Adversarios creados!");
            
        //Mesa de juego
        System.out.println("Creando la mesa de juego...");
        createTable();
        pauseExecution();
        System.out.println("¡Mesa creada!");
    }
    
    //Métodos para iniciar nuestra partida
    /**
     * Creamos el bombo de la partida
     */
    public void createRotatingDrum(){
        //Creamos el bombo con los 90 huecos
        MySet rd = new MySet(90);
        
        //Llenamos los huecos con números del 1 - 90
        for (int i = 1; i < 91; i++) {
            rd.addNumber(i);    
        }
        
        //Devolvemos el bombo
        rotatingDrum = rd;
    }
    
    /**
     * Crea tantos cartones como jugadores haya en la partida
     */
    public void createPlayers(){ 
        //Creamos unos mapas auxiliares
        MyMap m = new MyMap(numPlayers);
        MyMap m2 = new MyMap(numPlayers);
        
        /*Creamos una instancia de la clase Random para generar 
        los números aleatorios*/
        Random r = new Random();
        
        //Bucle para insertar los cartones de los jugadores en la lista
        for (int i = 0; i < numPlayers; i++) {
            //Creamos unos cartones auxiliares
            Cardboard c = new Cardboard();
            Cardboard c2 = new Cardboard();
            
            //Creación y relleno de la primera línea
            MySet l1 = new MySet(5);
            MySet l12 = new MySet(5); 
            for (int j = 0; j < 5; j++) {
                //Cálculo del entero aleatorio
                Integer n = r.nextInt(30) + 1;
                
                //Comprobamos si el número es repetido
                while(l1.containsNumber(n)){
                    n = r.nextInt(30) + 1;
                }
                
                //Añadimos el entero al conjunto
                l1.addNumber(n);
                l12.addNumber(n);
            }
            
            //Creación y relleno de la segunda línea
            MySet l2 = new MySet(5);
            MySet l22 = new MySet(5);
            for (int j = 0; j < 5; j++) {
                //Cálculo del entero aleatorio
                Integer n = r.nextInt(30) + 31;
                
                //Comprobamos si el número es repetido
                while(l2.containsNumber(n)){
                    n = r.nextInt(30) + 31;
                }
                
                //Añadimos el entero al conjunto
                l2.addNumber(n);
                l22.addNumber(n);
            }
            
            //Creación y relleno de la primera línea
            MySet l3 = new MySet(5);
            MySet l32 = new MySet(5);
            for (int j = 0; j < 5; j++) {
                //Cálculo del entero aleatorio
                Integer n = r.nextInt(30) + 61;
                
                //Comprobamos si el número es repetido
                while(l3.containsNumber(n)){
                    n = r.nextInt(30) + 61;
                }
                
                //Añadimos el entero al conjunto
                l3.addNumber(n);
                l32.addNumber(n);
            }
            
            //Asignamos a los cartones
            c.getRandomCode();
            c.setLine1(l1);
            c.setLine2(l2);
            c.setLine3(l3);
            
            c2.setCode(c.getCode());
            c2.setLine1(l12);
            c2.setLine2(l22);
            c2.setLine3(l32);
            
            //Introducimos el cartón a los mapas auxiliares
            m.put(c.getCode(), c);
            m2.put(c2.getCode(), c2);
        }
        
        //Establecemos los mapas auxiliares como el mapa de jugadores
        setPlayers(m);
        setEditablePlayers(m2);
    }
    
    /**
     * Muestra todos los cartones de los jugadores
     */
    public void showCardboards(){
        MyMapIterator it = players.createIterator(players);
        Integer i = 0;
        Cardboard aux = new Cardboard();
        
        //Recorremos el mapa e imprimimos los cartones
        while(it.hasNext()){
            aux = (Cardboard) it.next();
            System.out.println("**** Jugador " + i + " ******");
            System.out.print("Clave: ");
            System.out.println(aux.getCode());
            System.out.println(aux.getLine1().toString());
            System.out.println(aux.getLine2().toString());
            System.out.println(aux.getLine3().toString());
            System.out.println("*****************************");
            
            //Pasamos al siguiente jugador
            i++;
        }
    }
    
    /**
     * Crea tantos cartones como adversarios haya en la partida
     */
    public void createAdversaries(){ 
        //Creamos unas listas auxiliares
        MyLinkedList list = new MyLinkedList();
        MyLinkedList list2 = new MyLinkedList();
        
        /*Creamos una instancia de la clase Random para generar 
        los números aleatorios*/
        Random r = new Random();
        
        //Bucle para insertar los cartones de los rivales en la lista
        for (int i = 0; i < numAdversaries; i++) {
            //Creamos unos cartones auxiliares
            Cardboard c = new Cardboard();
            Cardboard c2 = new Cardboard();
                        
            //Creamos la primera línea de enteros del cartón
            MySet l1 = new MySet(5);
            MySet l12 = new MySet(5);
            for (int j = 0; j < 5; j++) {
                //Cálculo del entero aleatorio
                Integer n = r.nextInt(30) + 1;
                
                //Creación y relleno de la primera línea
                while(l1.containsNumber(n)){
                    n = r.nextInt(30) + 1;
                }
                
                //Añadimos el entero al conjunto
                l1.addNumber(n);
                l12.addNumber(n);
            }
            
            //Creación y relleno de la segunda línea
            MySet l2 = new MySet(5);
            MySet l22 = new MySet(5);
            for (int j = 0; j < 5; j++) {
                //Cálculo del entero aleatorio
                Integer n = r.nextInt(30) + 31;
                
                //Comprobamos si el número es repetido
                while(l2.containsNumber(n)){
                    n = r.nextInt(30) + 31;
                }
                
                //Añadimos el entero al conjunto
                l2.addNumber(n);
                l22.addNumber(n);
            }
            
            //Creación y relleno de la tercera línea
            MySet l3 = new MySet(5);
            MySet l32 = new MySet(5);
            for (int j = 0; j < 5; j++) {
                //Cálculo del entero aleatorio
                Integer n = r.nextInt(30) + 61;
                
                //Comprobamos si el número es repetido
                while(l3.containsNumber(n)){
                    n = r.nextInt(30) + 61;
                }
                
                //Añadimos el entero al conjunto
                l3.addNumber(n);
                l32.addNumber(n);
            }
            
            //Asignamos a los cartones
            c.getRandomCode();
            c.setLine1(l1);
            c.setLine2(l2);
            c.setLine3(l3);
            
            c2.setCode(c.getCode());
            c2.setLine1(l12);
            c2.setLine2(l22);
            c2.setLine3(l32);
            
            //Introducimos el cartón a las listas auxiliares
            list.addFinal(c);
            list2.addFinal(c2);
        }
        
        //Establecemos la lista auxiliar como la lista de rivales
        setAdversaries(list);
        setEditableAdversaries(list2);
    }
    
    /**
     * Crea la mesa del juego
     */
    public void createTable(){
        //Creamos un conjunto vacío
        MySet t = new MySet(90);
        
        //Lo asignamos a la mesa
        setTable(t);   
    }
    
    //Métodos para el desarrollo de la partida
    /**
     * Método del desarrollo de la partida
     */
    public void playMatch(){
        //Variables
        Integer ball;
       
        //Introducción
        System.out.println("Iniciando partida...");
        pauseExecution();
        
        System.out.println("************************");
        System.out.println("********** BINGO *********");
        
        //Métodos de la partida
        do{
            //Conseguimos la bola del bombo
            ball = getBall();
            
            //Actualizamos la mesa
            updateTable(ball);
            
            //Comprobamos la bola en los jugadores
            checkPlayers(ball);

            //Comprobamos la bola en los adversarios
            checkAdversaries(ball);
            
            //Imprimimos los cartones que cantaron bingo
            singLine(line);
        }while(!bingo && !rotatingDrum.isEmpty());
        
        //Método final
        singBingo();
    }
    
    /**
     * Obtiene una bola del bombo
     * @return 
     */
    public Integer getBall(){
        //Variables
        Scanner input = new Scanner(System.in);
        String key;
        Integer b;
        
        //Pasamos a la ronda siguiente
        round++;
        
        //Pulsación de tecla para continuar
        System.out.println("********** RONDA: " + round + " **************");
        System.out.print("- Introduzca la tecla c para sacar una bola del "
                + "bombo: ");
        key = input.nextLine();
        
        //Comprobación de la cadena introducida
        while(!key.equalsIgnoreCase("c")){
            System.out.print("- Esa tecla no es correcta. Vuelva introducir la"
                    + " tecla (c):");
            key = input.nextLine();
        }
        
        //Elegimos una bola del bombo
        b = rotatingDrum.choose();
        System.out.println("<> La bola es: " + b + ".");
        
        //Devolvemos el valor de la bola
        return b;   
    }
    
    /**
     * Método para ir comprobando los jugadores
     * @param bl 
     */
    public void checkPlayers(Integer bl){
        //Variables
        String key, keyP;
        Scanner input = new Scanner(System.in);
        MyMapIterator it = editablePlayers.createIterator(editablePlayers);
        Integer i = 0;
        
        while(it.hasNext()){
            //Imprimimos el cartón del jugador
            System.out.println("**** Jugador " + i + " ******");
            System.out.println(it.getMap().getMap()[i].getLine1().toString());
            System.out.println(it.getMap().getMap()[i].getLine2().toString());
            System.out.println(it.getMap().getMap()[i].getLine3().toString());
            System.out.println("*****************************");
            
            //Menú de opciones
            do{
                System.out.println("Menú de opciones:");
                System.out.println("> Tachar número (t).");
                System.out.println("> Cantar línea (l).");
                System.out.println("> Cantar bingo (b).");
                System.out.println("> Pasar turno (cualquier otra letra).");
                System.out.print("- Inserte una opción: ");
                key = input.nextLine();
                
                switch(key){
                    case "t":
                        //Si la bola está en la primera línea
                        if(it.getMap().getMap()[i].getLine1().containsNumber(bl)){
                            //Borramos el número
                            editablePlayers.getMap()[i].getLine1().removeNumber(bl);
                            
                            System.out.println("----------");
                            System.out.println("<> Tachaste " + bl + ".");
                            //Sumamos 1 al número de tachados
                            it.getMap().getMap()[i].setBlankNumber(it.getMap().getMap()[i].getBlankNumber() + 1);
                            System.out.println("----------");
                            
                        //Si la bola está en la segunda línea
                        }else if (it.getMap().getMap()[i].getLine2().containsNumber(bl)){
                            //Borramos el número
                            editablePlayers.getMap()[i].getLine2().removeNumber(bl);
                            
                            System.out.println("----------");
                            System.out.println("<> Tachaste " + bl + ".");
                            //Sumamos 1 al número de tachados
                            it.getMap().getMap()[i].setBlankNumber(it.getMap().getMap()[i].getBlankNumber() + 1);
                            System.out.println("----------");
                        
                        //Si la bola está en la tercera línea
                        }else if (it.getMap().getMap()[i].getLine3().containsNumber(bl)){
                            //Borramos el número
                            editablePlayers.getMap()[i].getLine3().removeNumber(bl);
                            
                            System.out.println("----------");
                            System.out.println("<> Tachaste " + bl + ".");
                            //Sumamos 1 al número de tachados
                            it.getMap().getMap()[i].setBlankNumber(it.getMap().getMap()[i].getBlankNumber() + 1);
                            System.out.println("----------");
                        
                        //Si no tienes el número
                        }else{
                            System.out.println("----------");
                            System.out.println("<> No tienes el número " 
                                    + bl + ".");
                            System.out.println("----------");
                        }
                        
                        //Comprobamos si hay alguna línea vacía y avisamos
                        if(lineRewards){
                            if(!sangLine){
                                if(it.getMap().getMap()[i].getLine1().isEmpty()
                                        || it.getMap().getMap()[i].getLine2().isEmpty() 
                                        || it.getMap().getMap()[i].getLine3().isEmpty()){
                                    System.out.println("----------");
                                    System.out.println("<> ¡Puedes cantar línea!");
                                    System.out.println("----------");
                                }
                            }
                        }
                            
                        //Comprobamos si tiene bingo y avisamos
                        if(it.getMap().getMap()[i].getLine1().isEmpty()
                                && it.getMap().getMap()[i].getLine2().isEmpty()
                                && it.getMap().getMap()[i].getLine3().isEmpty()){
                            System.out.println("----------");
                            System.out.println("<> ¡Puedes cantar bingo!");
                            System.out.println("----------");
                        }
                        break;
                    case "l":
                        //Si está activa la opción
                        if(lineRewards){
                            //Si alguien cantó línea
                            if(!sangLine){
                                System.out.print("- Introduce tu clave: ");
                                keyP = input.nextLine();
                                
                                //Validación clave
                                while(!keyP.equals(it.getMap().getMap()[i].getCode())){
                                    System.out.print("- Clave incorrecta. "
                                            + "Introdúcela de nuevo: ");
                                    keyP = input.nextLine();
                                }
                                
                                System.out.println("----------");
                                System.out.println("<> Comprobando si tienes"
                                        + " línea...");
                                System.out.println("----------");
                                
                                //Variables auxiliares
                                MyMapIterator itL = players.createIterator(players);
                                Integer j = 0;
                                
                                //Búsqueda del jugador en el mapa
                                while(itL.hasNext() && !keyP.equals(itL.getMap().getMap()[j].getCode())){
                                    j++;
                                    itL.next();
                                }
                                
                                //Si alguna línea está vacía
                                if(table.containsSet(itL.getMap().getMap()[j].getLine1())
                                        || table.containsSet(itL.getMap().getMap()[j].getLine2())
                                        || table.containsSet(itL.getMap().getMap()[j].getLine3())){
                                    
                                    System.out.println("----------");
                                    System.out.println("<> Un jugador cantó línea.");
                                    System.out.println("----------");
                                    
                                    //Añadir el cartón a la cola de ganadores
                                    lineWinners.add(itL.getMap().getMap()[j]);
                                    
                                    //Modificamos la variable line
                                    if(!line)
                                        line = true;
                                }else{
                                    System.out.println("<> ¡No tienes línea!");
                                    System.out.println("----------");
                                }
                            }else{
                                System.out.println("----------");
                                System.out.println("<> La partida va para bingo ahora.");
                                System.out.println("----------");
                            }
                        }else{
                            System.out.println("----------");
                            System.out.println("<> Esta opción está inactiva.");
                            System.out.println("----------");
                        }
                        break;
                    case "b":
                        System.out.print("- Introduce tu clave: ");
                        keyP = input.nextLine();
                        
                        //Validación de clave
                        while(!keyP.equals(it.getMap().getMap()[i].getCode())){
                            System.out.print("- Clave incorrecta. "
                                    + "Introdúcela de nuevo: ");
                            keyP = input.nextLine();
                        }
                        
                        System.out.println("----------");
                        System.out.println("<> Comprobando si tienes bingo...");
                        System.out.println("----------");
                        
                        //Variables auxiliares
                        MyMapIterator itB = players.createIterator(players);
                        Integer j = 0;
                        
                        //Búsqueda del cartón del jugador
                        while(itB.hasNext() && !keyP.equals(itB.getMap().getMap()[j].getCode())){
                            j++;
                            itB.next();
                        }
                        
                        //Si están todas las líneas vacías
                        if(table.containsSet(itB.getMap().getMap()[j].getLine1())
                                && table.containsSet(itB.getMap().getMap()[j].getLine2())
                                && table.containsSet(itB.getMap().getMap()[j].getLine3())){
                                    
                            System.out.println("----------");
                            System.out.println("<> Un jugador cantó bingo.");
                            System.out.println("----------");
                            
                            //Añadir el cartón a la cola
                            bingoWinners.add(itB.getMap().getMap()[j]);
                            
                            //Asignamos la cantidad de tachados al cartón original
                            itB.getMap().getMap()[j].setBlankNumber(it.getMap().getMap()[i].getBlankNumber());
                            
                            //Modificamos la variable bingo
                            if(!bingo)
                                bingo = true;
                        }else {
                            System.out.println("<> ¡No tienes bingo!");
                            System.out.println("----------");
                        }
                        break;
                    default:
                        System.out.println("<> Pasando al siguiente jugador...");
                        pauseExecution();
                        break;
                }
            }while(key.equalsIgnoreCase("t") || key.equalsIgnoreCase("l") 
                    || key.equalsIgnoreCase("b"));
            
            //Pasamos al siguiente jugador
            i++;
            it.next();
        }
    }
    
    /**
     * Método para actualizar la mesa
     * @param bl 
     */
    public void updateTable(Integer bl){
        //Metemos la bola en la mesa
        table.addNumber(bl);
    }
    
    /**
     * Método para ir comprobando los adversarios
     * @param bl 
     */
    public void checkAdversaries(Integer bl){
        //Variables
        MyLinkedIterator it = editableAdversaries.createIterator(editableAdversaries);
        Integer i = 0;
        
        while(it.hasNext()){          
            //Comprobamos si el rival puede tachar
            if(it.getCurrent().getInfo().getLine1().containsNumber(bl)){
                //Eliminamos la bola
                editableAdversaries.getLinkedList().getInfo().getLine1().removeNumber(bl);
                //Sumamos 1 al número de tachados
                it.getCurrent().getInfo().setBlankNumber(it.getList().getInfo().getBlankNumber() + 1);
                            
            }else if (it.getCurrent().getInfo().getLine2().containsNumber(bl)){
                //Eliminamos la bola
                editableAdversaries.getLinkedList().getInfo().getLine2().removeNumber(bl);
                //Sumamos 1 al número de tachados
                it.getCurrent().getInfo().setBlankNumber(it.getList().getInfo().getBlankNumber() + 1);
                            
            }else if (it.getCurrent().getInfo().getLine3().containsNumber(bl)){
                //Eliminamos la bola
                editableAdversaries.getLinkedList().getInfo().getLine3().removeNumber(bl);
                //Sumamos 1 al número de tachados
                it.getCurrent().getInfo().setBlankNumber(it.getList().getInfo().getBlankNumber() + 1);
            }
                        
            //Comprobamos si el rival puede hacer línea
            if(lineRewards){
                //Si se ha terminado de cantar línea
                if(!sangLine){
                    //Comprobamos si hay alguna línea vacía
                    if(it.getCurrent().getInfo().getLine1().isEmpty() 
                            || it.getCurrent().getInfo().getLine2().isEmpty() 
                            || it.getCurrent().getInfo().getLine3().isEmpty()){
                    
                        System.out.println("----------");
                        System.out.println("Un rival cantó línea.");
                        System.out.println("----------");
                        
                        //Variables auxiliares
                        MyLinkedIterator itL = adversaries.createIterator(adversaries);
                        Integer j = 0;
                
                        while(itL.hasNext() && !Objects.equals(i, j)){
                            j++;
                            itL.next();
                        }
                
                        //Añadir el cartón a la cola
                        lineWinners.add(itL.getCurrent().getInfo());
                        
                        //Modificamos la variable line
                        if(!line)
                            line = true;
                    }
                }
            }
        
            //Comprobamos si el rival puede haber bingo.
            if(it.getCurrent().getInfo().getLine1().isEmpty() 
                && it.getCurrent().getInfo().getLine2().isEmpty() 
                && it.getCurrent().getInfo().getLine3().isEmpty()){
            
                //Variables auxiliares
                MyLinkedIterator itB = adversaries.createIterator(adversaries);
                Integer j = 0;
                
                while(itB.hasNext() && !Objects.equals(i, j)){
                    j++;
                    itB.next();
                }
                
                System.out.println("----------");
                System.out.println("Un rival cantó bingo.");
                System.out.println("----------");
                
                //Añadir el cartón a la cola
                bingoWinners.add(itB.getCurrent().getInfo());
                
                //Asignamos la cantidad de tachados al cartón original
                itB.getCurrent().getInfo().setBlankNumber(it.getCurrent().getInfo().getBlankNumber());
                
                //Modificamos la variable bingo
                if(!bingo)
                    bingo = true;
            }

            //Pasamos al siguiente rival
            i++;
            it.next();
        }
    }
    
    //Métodos para premios
    /**
     * Método para imprimir por pantalla los cartones que han cantado línea
     * @param sl
     */
    public void singLine(Boolean sl){
        if(!sangLine){
            if(line){
                //Variables
                MyQueueIterator it = lineWinners.createIterator(lineWinners);
                
                //Método para imprimir por pantalla los cartones
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("Estos son los cartones que han cantado "
                        + "línea:");
                
                while(it.hasNext()){
                    System.out.println("-------------------------------------");
                    System.out.println("Cartón con id: " + it.getCurrent().getInfo().getCode());
                    System.out.println("> Líneas:");
                    System.out.println(it.getCurrent().getInfo().getLine1());
                    System.out.println(it.getCurrent().getInfo().getLine2());
                    System.out.println(it.getCurrent().getInfo().getLine3());
                    System.out.println("-------------------------------------");
                    
                    it.next();
                }
                
                System.out.println("Ahora, ¡la partida sigue para bingo!");
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                
                //Modificamos sangLine
                sangLine = true;
            }
        }
    }
    
    /**
     * Método para imprimir por pantalla los cartones que han cantado bingo
     * Final del juego
     */
    public void singBingo(){
        if(bingo){
            //Variables
                MyQueueIterator it = bingoWinners.createIterator(bingoWinners);
                
                //Método para imprimir por pantalla los cartones
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println("Estos son los cartones que han cantado "
                        + "bingo:");
                
                while(it.hasNext()){
                    System.out.println("-------------------------------------");
                    System.out.println("Cartón con id: " + it.getCurrent().getInfo().getCode());
                    System.out.println("> Líneas:");
                    System.out.println(it.getCurrent().getInfo().getLine1());
                    System.out.println(it.getCurrent().getInfo().getLine2());
                    System.out.println(it.getCurrent().getInfo().getLine3());
                    System.out.println("-------------------------------------");
                    
                    it.next();
                }
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                
                System.out.println("¡SE ACABÓ LA PARTIDA, GRACIAS POR JUGAR!");
        }
    }
    /**
     * Configura la partida del bingo
     */
    public void configuration(){
        //Variables
        Integer numPl, numAdv, lineReward;
        Scanner input = new Scanner(System.in);
        
        System.out.println("********* CONFIGURACIÓN *********");
        //Selección de jugadores
        System.out.print("- Seleccione el número de jugadores (1-4): ");
        numPl = input.nextInt();
        
        //Validación
        while(numPl < 1 || numPl > 5){
            System.out.println("<> Opción incorrecta.");
            System.out.print("- Vuelva a introducir el número de jugadores: ");
            numPl = input.nextInt();
        }
        
        //Asignación
        setNumPlayers(numPl);
        
        //Selección de oponentes
        System.out.print("- Seleccione el número de rivales (1-100): ");
        numAdv = input.nextInt();
        
        //Validación
        while(numAdv < 1 || numAdv > 100){
            System.out.println("<> Opción incorrecta.");
            System.out.print("- Vuelva a introducir el número de rivales: ");
            numAdv = input.nextInt();
        }
        
        //Asignación
        setNumAdversaries(numAdv);
        
        //Selección de premio por línea
        System.out.print("- Seleccione si quiere premio por línea "
                + "(0 - No; o 1 - Sí): ");
        lineReward = input.nextInt();
        
        //Validación
        while(lineReward < 0 || lineReward > 1){
            System.out.println("<> Opción incorrecta.");
            System.out.print("- Vuelva a introducir el valor: ");
            lineReward = input.nextInt();
        }
     
        //Asignación
        if(lineReward == 0){
            setLineRewards(false);
        }else if(lineReward == 1){
            setLineRewards(true);
        }
        
        //Permiso de inicio de partida
        configured = true;
    }
    
    //Otros métodos
    /**
     * Método extra que suspende el programa durante unos ms
     */
    public void pauseExecution(){
        try {
            //El programa se duerme durante unos ms
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }   
}
