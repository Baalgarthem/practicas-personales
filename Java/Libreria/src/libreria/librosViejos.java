package libreria;

public class librosViejos extends libros{ //aplicando la herencia, esta clase hereda de la clase "libros"

  //primer constructor
    public librosViejos(int a�o, String autor) {
    }
    //segundo constructor
    public librosViejos(String nombreLibro, String autorLibro, int a�oLibro) {
       libros.nombreLibro = nombreLibro;
        libros.autorLibro = autorLibro;
        libros.a�oLibro = a�oLibro;
    }
    //tercer constructor, con esto hemos hecho una sobrecarga de constructores
    public librosViejos(int a�oLibro) {
       super.a�oLibro = a�oLibro;
    }
    
    @Override
        public void revisarLibro (int a�o, String autor){ //polimorfismo del metodo
        if (a�o<2000) {
            System.out.println("El libro es viejo.");
            System.out.println("No se a�adira a la libreria");
        }
    }
    
}
