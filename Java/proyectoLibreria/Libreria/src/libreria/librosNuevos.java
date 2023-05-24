package libreria;

public class librosNuevos extends libros { //aplicando la herencia, esta clase hereda de la clase "libros"

  //primer constructor
    public librosNuevos(int año, String autor) {
    }
    //segundo constructor
    public librosNuevos(String nombreLibro, String autorLibro, int añoLibro) {
        super.nombreLibro = nombreLibro;
        super.autorLibro = autorLibro;
        super.añoLibro = añoLibro;
    }
    //tercer constructor, con esto hemos hecho una sobrecarga de constructores
    public librosNuevos(int añoLibro) {
        this.añoLibro = añoLibro;
    }
    
    @Override
        public void revisarLibro (int año, String autor){ //polimorfismo de metodo
        if (año>2000) {
            System.out.println("El libro es apto para añadirse, lo añadiremos a la librería");
                if (año>2013) {
                    System.out.println("El año del libro es mayor a 2013, muchas gracias por tu gran aporte");
            }
        }
        
    }
    
}
