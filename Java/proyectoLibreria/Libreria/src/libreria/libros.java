package libreria;

public class libros {

    String nombreLibro;
    String autorLibro;
    int a�oLibro;

    //primer constructor
    public libros() {
    }

    //segundo constructor
    public libros(String nombreLibro, String autorLibro, int a�oLibro) {
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.a�oLibro = a�oLibro;
    }

    //tercer constructor, con esto hemos hecho una sobrecarga de constructores
    public libros(int a�oLibro) {
        this.a�oLibro = a�oLibro;
    }

    //metodo principal de 'revisarLibro'
    public void revisarLibro(int a�o, String autor) {
        System.out.println("El libro '" + this.nombreLibro + "' del autor '" + this.autorLibro + "' ha sido revisado");

    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
    }

    public int getA�oLibro() {
        return a�oLibro;
    }

    public void setA�oLibro(int a�oLibro) {
        this.a�oLibro = a�oLibro;
    }

}
