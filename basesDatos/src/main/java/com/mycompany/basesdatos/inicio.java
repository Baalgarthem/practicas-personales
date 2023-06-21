/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.basesdatos;

import java.sql.Connection;

/**
 *
 * @author Alush
 */
public class Inicio {

   public static void main(String[] args) {
        Conexion conexion = new Conexion();
        
        try (Connection pruebaConexion = conexion.get_connection()){
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
