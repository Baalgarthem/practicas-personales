package libreria;

public class librosNuevos extends libros { //aplicando la herencia, esta clase hereda de la clase "libros"

  //primer constructor
    public librosNuevos(int a�o, String autor) {
    }
    //segundo constructor
    public librosNuevos(String nombreLibro, String autorLibro, int a�oLibro) {
        libros.nombreLibro = nombreLibro;
        libros.autorLibro = autorLibro;
        libros.a�oLibro = a�oLibro;
    }
    //tercer constructor, con esto hemos hecho una sobrecarga de constructores
    public librosNuevos(int a�oLibro) {
        librosNuevos.a�oLibro = a�oLibro;
    }
    
    @Override
        public void revisarLibro (int a�o, String autor){ //polimorfismo de metodo
        if (a�o>2000) {
            System.out.println("El libro es apto para a�adirse, lo a�adiremos a la librer�a");
                if (a�o>2013) {
                    System.out.println("El a�o del libro es mayor a 2013, muchas gracias por tu gran aporte");
            }
        }
        
    }
    
}
