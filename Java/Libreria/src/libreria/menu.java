package libreria;

import java.util.Scanner;

public class menu {

    public static void iniciarMenu() {

        Scanner entradaTecladoDelUsuario = new Scanner(System.in);
        libros[] librosAñadidos = new libros[10]; // Tamaño inicial del arreglo (puedes ajustarlo según tus necesidades)
        int contadorLibros = 0; // Contador para realizar el seguimiento del número de libros añadidos
        var respuesta = true;

        do {
            System.out.println("¿Desea añadir un nuevo libro?\n1. Si.\n2. No.");
            System.out.print("Respuesta : ");
            String opcionElegida = entradaTecladoDelUsuario.nextLine();

            switch (opcionElegida) {
                case "1" -> {
                    System.out.println("Elegiste la opción 1.");
                    System.out.println("Ingresa el nombre del autor: ");
                    String autorLibro = entradaTecladoDelUsuario.nextLine();
                    System.out.println("Ingresa el nombre del libro:");
                    String nombreLibro = entradaTecladoDelUsuario.nextLine();
                    System.out.println("Ingresa el año del libro:");
                    int año = Integer.parseInt(entradaTecladoDelUsuario.nextLine());

                    libros revision1 = new libros(nombreLibro, autorLibro, año);
                    librosNuevos revision2 = new librosNuevos(nombreLibro, autorLibro, año);
                    librosViejos revision3 = new librosViejos(nombreLibro, autorLibro, año);
                    librosAñadidos[contadorLibros] = revision1; //uso de arreglo para almacenar libros
                    contadorLibros++;

                    revision1.revisarLibro(año, autorLibro);
                    revision2.revisarLibro(año, autorLibro);
                    revision3.revisarLibro(año, autorLibro);
                    
                }
                case "2" -> {
                    System.out.println("Elegiste la opción 2");
                    System.out.println("Programa terminado...");
                    respuesta = false;
                }
                default ->
                    throw new AssertionError();
            }
        } while (respuesta);

        if (contadorLibros > 0) { 
            System.out.println("Los libros que ingresaste fueron:");
            for (int i = 0; i < contadorLibros; i++) { //Impresion del arreglo recorriendo en un for
                libros libro = librosAñadidos[i];
                System.out.println("Libro: '" + libro.getNombreLibro() + "' - Autor: " + libro.getAutorLibro());
            }
        } else {
            System.out.println("No se han añadido libros.");
        }
    }
}
