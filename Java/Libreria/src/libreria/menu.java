package libreria;

import java.util.Scanner;

public class menu {

    public static void iniciarMenu() {

        Scanner entradaTecladoDelUsuario = new Scanner(System.in);
        libros[] librosA�adidos = new libros[10]; // Tama�o inicial del arreglo (puedes ajustarlo seg�n tus necesidades)
        int contadorLibros = 0; // Contador para realizar el seguimiento del n�mero de libros a�adidos
        var respuesta = true;

        do {
            System.out.println("�Desea a�adir un nuevo libro?\n1. Si.\n2. No.");
            System.out.print("Respuesta : ");
            String opcionElegida = entradaTecladoDelUsuario.nextLine();

            switch (opcionElegida) {
                case "1" -> {
                    System.out.println("Elegiste la opci�n 1.");
                    System.out.println("Ingresa el nombre del autor: ");
                    String autorLibro = entradaTecladoDelUsuario.nextLine();
                    System.out.println("Ingresa el nombre del libro:");
                    String nombreLibro = entradaTecladoDelUsuario.nextLine();
                    System.out.println("Ingresa el a�o del libro:");
                    int a�o = Integer.parseInt(entradaTecladoDelUsuario.nextLine());

                    libros revision1 = new libros(nombreLibro, autorLibro, a�o);
                    librosNuevos revision2 = new librosNuevos(nombreLibro, autorLibro, a�o);
                    librosViejos revision3 = new librosViejos(nombreLibro, autorLibro, a�o);
                    librosA�adidos[contadorLibros] = revision1; //uso de arreglo para almacenar libros
                    contadorLibros++;

                    revision1.revisarLibro(a�o, autorLibro);
                    revision2.revisarLibro(a�o, autorLibro);
                    revision3.revisarLibro(a�o, autorLibro);
                    
                }
                case "2" -> {
                    System.out.println("Elegiste la opci�n 2");
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
                libros libro = librosA�adidos[i];
                System.out.println("Libro: '" + libro.getNombreLibro() + "' - Autor: " + libro.getAutorLibro());
            }
        } else {
            System.out.println("No se han a�adido libros.");
        }
    }
}
