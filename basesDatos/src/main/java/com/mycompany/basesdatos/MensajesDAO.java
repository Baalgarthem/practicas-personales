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
    public static void crearMensaje (Mensajes mensaje){
    Conexion db_connect = new Conexion();
        
        try (Connection iniciarConexion = db_connect.get_connection()){
             
             PreparedStatement declaracion  = null;
             
             try {
                String query = "INSERT INTO mensajes (mensaje, autor_mensaje) VALUES (?,?)";
                ps=
            } catch (Exception e) {
            }
             
        } catch (SQLException e) {
            //System.out.println(e);
        }
    }
    
    
    
    public static void leerMensaje (){}
    public static void borrarMensaje (int id_mensaje){}
    public static void editarMensaje (Mensajes mensaje){}
}
