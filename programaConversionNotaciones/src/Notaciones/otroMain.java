/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Notaciones;

/**
 *
 * @author Alush
 */
public class otroMain {

    public static void main(String[] args) {

        String infija = "3 + 2";
        String eleccion = "postfija";
        ConvertirExpresion metodos = new ConvertirExpresion();
        String tipoDetectado = metodos.detectarTipoExpresion(infija);
        System.out.println("Tu expresión es: " + tipoDetectado);

        metodos.convertirExpresion(infija, eleccion);

        String conversion = metodos.convertirExpresion(infija, eleccion);
        System.out.println("Notación Infija: " + infija);
        System.out.println("Notación Postfija: " + conversion);
    }
}
