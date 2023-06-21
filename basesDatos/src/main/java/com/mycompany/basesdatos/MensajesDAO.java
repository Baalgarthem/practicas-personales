/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basesdatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        Conexion db_connect = new Conexion();
        PreparedStatement declaracion =null;
        ResultSet resultado = null;
        try (Connection conexion = db_connect.get_connection()){
            String query = "SELECT * FROM mensajes";
            declaracion = conexion.prepareStatement(query);
            resultado = declaracion.executeQuery();
            
            while (resultado.next()) {
                System.out.println("ID: "+resultado.getInt("id_mensaje"));
                System.out.println("Mensaje: "+resultado.getString("mensaje"));
                System.out.println("Autor: "+resultado.getString("autor_mensaje"));
                System.out.println("Fecha: "+resultado.getString("fecha_mensaje"));
                System.out.println("----");
            }
        } catch (SQLException e) {
            System.out.println("No se han podido leer los mensajes");
            System.out.println(e);
        }
    }

    public static void borrarMensaje(int id_mensaje) {
    }

    public static void editarMensaje(Mensajes mensaje) {
    }
}
