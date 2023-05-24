package libreria;

public class librosViejos extends libros{ //aplicando la herencia, esta clase hereda de la clase "libros"

  //primer constructor
    public librosViejos(int año, String autor) {
    }
    //segundo constructor
    public librosViejos(String nombreLibro, String autorLibro, int añoLibro) {
       libros.nombreLibro = nombreLibro;
        libros.autorLibro = autorLibro;
        libros.añoLibro = añoLibro;
    }
    //tercer constructor, con esto hemos hecho una sobrecarga de constructores
    public librosViejos(int añoLibro) {
       super.añoLibro = añoLibro;
    }
    
    @Override
        public void revisarLibro (int año, String autor){ //polimorfismo del metodo
        if (año<2000) {
            System.out.println("El libro es viejo.");
            System.out.println("No se añadira a la libreria");
        }
    }
    
}
