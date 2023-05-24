package libreria;

public class libros {

    String nombreLibro;
    String autorLibro;
    int añoLibro;

    //primer constructor
    public libros() {
    }

    //segundo constructor
    public libros(String nombreLibro, String autorLibro, int añoLibro) {
        this.nombreLibro = nombreLibro;
        this.autorLibro = autorLibro;
        this.añoLibro = añoLibro;
    }

    //tercer constructor, con esto hemos hecho una sobrecarga de constructores
    public libros(int añoLibro) {
        this.añoLibro = añoLibro;
    }

    //metodo principal de 'revisarLibro'
    public void revisarLibro(int año, String autor) {
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

    public int getAñoLibro() {
        return añoLibro;
    }

    public void setAñoLibro(int añoLibro) {
        this.añoLibro = añoLibro;
    }

}
