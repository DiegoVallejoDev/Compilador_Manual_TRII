package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by Kaleb on 10/9/2016.
 */
public class Sintactico {

    Controller ctrl;
    int contadorNodos=1;
    int cordX=350, cordY=100;
    int hijosLibreria=0;
    int hijosVar=0;
    int hijosInicio=0;

    boolean encontradaLibreria=false,encontradaVar=false,encontradaInicio=false;

    public  static ArrayList<String> nombre_Nodo = new ArrayList<>(Arrays.asList());//Nombre del nodo
    public  static ArrayList<Integer> coordenada_X = new ArrayList<>(Arrays.asList());//Coordenada X
    public  static ArrayList<Integer> coodernada_Y = new ArrayList<>(Arrays.asList());//Coordenanda Y


    boolean programaEncontrado=false;
    public void detectarNodos_Arbol(){
        boolean verificaLibreria=false;
        boolean errorLibreria=false;
        boolean verificaVar=false;
        boolean verificaInicio=false;
        boolean errorVar=false;

        boolean libCompleta=false;
        boolean varCompleta=false;
        boolean inicioCompleta=false;

        boolean auxLibreria=false;
        boolean libreriaNOT=false;



        int hijos_Padre=0;
        int auxContador=0;

        for(int i=0;i<ctrl.lexico_Token.size();i++){
            auxContador=0;
            auxContador=i+1;

            if(ctrl.lexico_Token.get(i).equals("programa")){
                if(auxContador<ctrl.lexico_Token.size()){
                    if(ctrl.lexico_Token.get(i).matches("[a-zA-Z]+")){
                        programaEncontrado=true;

                        nombre_Nodo.add(ctrl.lexico_Token.get(i+1));//Agregamos la palabra reservada libreria
                        coordenada_X.add(100);
                        coodernada_Y.add(200);
                        i++;
                    }
                }
            }

            if(ctrl.lexico_Token.get(i).equals("libreria") && ctrl.lexico_Identificador.get(i).equals("Reservada") || verificaLibreria==true && libCompleta==false) {//Libreria

                encontradaLibreria=true;
                libreriaNOT=true;
                System.out.println("Entro libreria");

                if(auxLibreria==false) {
                    cordX = 170;
                    cordY = 200;
                    verificaLibreria = true;


                    nombre_Nodo.add(ctrl.lexico_Token.get(i));//Agregamos la palabra reservada libreria
                    coordenada_X.add(cordX);
                    coodernada_Y.add(cordY);
                    contadorNodos++;
                    hijos_Padre++;
                    hijosLibreria++;

                    if (hijos_Padre == 1) {
                        cordX = cordX + 90;
                    }

                /*
                if(ctrl.lexico_Token.get(i+1).matches("[<][A-Z][A-Za-z0-9]+[.][p][>]"))//Verificar siguiente parte
                    i++;
                else {
                    errorLibreria = true;
                    System.out.println("Error en libreria");
                }

                if(errorLibreria==true)
                    break;
                 */

                    i++;

                    nombre_Nodo.add(ctrl.lexico_Token.get(i));//Agregamos el nombre de la libreria <Hola.p>
                    coordenada_X.add(cordX);
                    coodernada_Y.add(cordY);
                    contadorNodos++;
                    hijosLibreria++;
                    cordX=cordX+80;
                    cordY=cordY+100;
                }

                if(auxLibreria==true) {
                    nombre_Nodo.add(ctrl.lexico_Token.get(i));//Agregamos lo que siga
                    coordenada_X.add(cordX);
                    coodernada_Y.add(cordY);
                    contadorNodos++;
                    hijosLibreria++;

                }
                auxLibreria=true;
                if (auxContador < ctrl.lexico_Token.size()) {
                    if (ctrl.lexico_Token.get(i + 1).equals("var")) {
                        verificaLibreria = false;
                        libCompleta = true;
                    }
                }

            }
            if(errorLibreria==true)//Terminar por error
                break;

            if(ctrl.lexico_Token.get(i).equals("var") && ctrl.lexico_Identificador.get(i).equals("Reservada") || verificaVar==true && varCompleta==false && libCompleta==true || libreriaNOT==false ) {//Var. Declaración

                System.out.println("Entró var");
                verificaVar=true;
                encontradaVar=true;

                if(hijosVar<1) {
                    i = i + 1;

                    cordX = 450;
                    cordY = 200;
                }
                if(ctrl.lexico_Token.get(i).equals("var"))
                    i++;

                nombre_Nodo.add(ctrl.lexico_Token.get(i));//Agregamos los elemento de var
                coordenada_X.add(cordX);
                coodernada_Y.add(cordY);
                contadorNodos++;
                cordY=cordY+100;
                hijosVar++;

                if(auxContador<ctrl.lexico_Token.size()) {
                    if (ctrl.lexico_Token.get(i + 1).equals("inicio")) {
                        verificaVar = false;
                        varCompleta = true;
                    }
                }
            }

            if(ctrl.lexico_Token.get(i).equals("inicio") && ctrl.lexico_Identificador.get(i).equals("Reservada") || verificaInicio==true && varCompleta==true && libCompleta==true) {//Var. Declaración

                verificaInicio=true;
                encontradaInicio=true;
                System.out.println("Entró inicio");

                if(hijosInicio<1) {
                    i++;

                    cordX = 550;
                    cordY = 200;
                }

                if(ctrl.lexico_Token.get(i).equals("inicio"))
                    i++;


                if(auxContador<ctrl.lexico_Token.size()) {
                    if (ctrl.lexico_Token.get(i + 1).equals(":=")) {
                        nombre_Nodo.add("<Asignacion>");//Agregamos los elemento de inicio
                        coordenada_X.add(cordX);
                        coodernada_Y.add(cordY);
                        contadorNodos++;
                        cordY = cordY + 100;
                        hijosInicio++;
                    }
                }


                nombre_Nodo.add(ctrl.lexico_Token.get(i));//Agregamos los elemento de inicio
                coordenada_X.add(cordX);
                coodernada_Y.add(cordY);
                contadorNodos++;
                cordY=cordY+100;
                hijosInicio++;
            }
        }
        System.out.println("Tamaño: "+nombre_Nodo.size());
        abrirArbol();
    }


    public void deteccionNodos_Tokens(){
        int listaLexico_Size=ctrl.lexico_Token.size();
        int auxSize_Lexico=0;
        for(int i=0;i<listaLexico_Size;i++){
            if(ctrl.lexico_Token.get(i).equals("programa"))
                System.out.println("");
        }
    }


    public void abrirArbol(){
        arbolSintactico arb = new arbolSintactico();
        arb.setArbol();
    }
}