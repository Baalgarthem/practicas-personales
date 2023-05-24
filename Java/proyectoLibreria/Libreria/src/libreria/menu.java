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

                    libros libroa�adido = new libros(nombreLibro, autorLibro, a�o);
                    librosA�adidos[contadorLibros] = libroa�adido;
                    contadorLibros++;

                    libroa�adido.revisarLibro(a�o, autorLibro);
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
            for (int i = 0; i < contadorLibros; i++) {
                libros libro = librosA�adidos[i];
                System.out.println("Libro: '" + libro.getNombreLibro() + "' - Autor: " + libro.getAutorLibro());
            }
        } else {
            System.out.println("No se han a�adido libros.");
        }
    }
}
