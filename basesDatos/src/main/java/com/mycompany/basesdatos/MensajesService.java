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
        MensajesDAO.leerMensaje();
    }

    public static void borrarMensaje() {
         Scanner sc = new Scanner(System.in);
        System.out.println("Indica el ID del mensaje a borrar");
        Integer id_mensaje = Integer.parseInt(sc.nextLine());
        MensajesDAO.borrarMensaje(id_mensaje);
    }

    public static void editarMensaje() {
        Scanner sc = new Scanner (System.in);
        System.out.println("Escribe tu nuevo mensaje: ");
        String nuevoMensaje = sc.nextLine();
        
        System.out.println("Escribe el ID del mensaje a actualizar: ");
        Integer id_mensaje = Integer.parseInt(sc.nextLine());
        Mensajes actualizacion = new Mensajes ();
        actualizacion.setId_mensaje(id_mensaje);
        actualizacion.setMensaje(nuevoMensaje);
        MensajesDAO.editarMensaje(actualizacion);
    }
}
