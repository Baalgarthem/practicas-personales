/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basesdatos;

import java.util.Scanner;

/**
 *
 * @author Alush
 */
public class MensajesService {

    public static void crearMensaje() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu mensaje");
        String mensaje = sc.nextLine();
        
        System.out.println("Escribe tu nombre");
        String nombre = sc.nextLine();
        
        Mensajes registro = new Mensajes ();
        registro.setMensaje(mensaje);
        registro.setAutor_mensaje(nombre);
        MensajesDAO.crearMensaje(registro);
    }

    public static void listarMensajes() {
    }

    public static void borrarMensaje() {
    }

    public static void editarMensaje() {
    }
}
