package programaconversionnotaciones;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Gramatica {
    private final Set<String> operadores;
    private final String expresionInfija;
    private String[] elementos;

    public Gramatica(String expresionInfija, String[] operadores) {
        this.expresionInfija = expresionInfija;
        this.operadores = new HashSet<>(Arrays.asList(operadores));
    }

    public void analizarExpresion() {
        this.elementos = expresionInfija.split(" ");
        for (String elemento : elementos) {
            if (!elemento.matches("\\d+") && !operadores.contains(elemento)) {
                throw new IllegalArgumentException("El elemento " + elemento + " no es un operador ni un número.");
            }
        }
    }

    public String[] getElementos() {
        return elementos;
    }

    public Set<String> getOperadores() {
        return operadores;
    }
}
