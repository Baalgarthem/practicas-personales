/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basesdatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Alush
 */
public class MensajesDAO {

    public static void crearMensaje(Mensajes mensaje) {
        Conexion db_connect = new Conexion();

        try (Connection conexion = db_connect.get_connection()) {
              PreparedStatement declaracion =null;
              System.out.println("Hasta aqui todo bien");
            try {
               String query="insert into mensajes(mensaje,autor_mensaje) values (?,?)";
                declaracion = conexion.prepareStatement(query);
                declaracion.setString(1, mensaje.getMensaje());
                declaracion.setString(2, mensaje.getAutor_mensaje());
                declaracion.executeUpdate();
                System.out.println("Mensaje creado exitosamente");
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void leerMensaje() {
    }

    public static void borrarMensaje(int id_mensaje) {
    }

    public static void editarMensaje(Mensajes mensaje) {
    }
}
