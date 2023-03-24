package programaconversionnotaciones;

import java.util.Stack;

public class conversion {
    
    public static String infijaAPostfija(String expresionInfija) throws IllegalArgumentException {
        try {
            String[] elementos = expresionInfija.split(" ");
            Stack<String> pila = new Stack<>();
            StringBuilder salida = new StringBuilder();
            for (String elemento : elementos) {
                if (elemento.matches("\\d+")) {
                    salida.append(elemento).append(" ");
                } else if (elemento.equals("(")) {
                    pila.push(elemento);
                } else if (elemento.equals(")")) {
                    while (!pila.peek().equals("(")) {
                        salida.append(pila.pop()).append(" ");
                    }
                    pila.pop();
                } else {
                    while (!pila.isEmpty() && precedencia(elemento) <= precedencia(pila.peek())) {
                        salida.append(pila.pop()).append(" ");
                    }
                    pila.push(elemento);
                }
            }
            while (!pila.isEmpty()) {
                salida.append(pila.pop()).append(" ");
            }
            return salida.toString().trim();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al convertir la expresión infija a postfija.");
        }
    }

    public static String infijaAPrefija(String expresionInfija) throws IllegalArgumentException {
        try {
            String[] elementos = new StringBuilder(expresionInfija).reverse().toString().split(" ");
            Stack<String> pila = new Stack<>();
            StringBuilder salida = new StringBuilder();
            for (String elemento : elementos) {
                if (elemento.matches("\\d+")) {
                    salida.insert(0, elemento + " ");
                } else if (elemento.equals(")")) {
                    pila.push(elemento);
                } else if (elemento.equals("(")) {
                    while (!pila.peek().equals(")")) {
                        salida.insert(0, pila.pop() + " ");
                    }
                    pila.pop();
                } else {
                    while (!pila.isEmpty() && precedencia(elemento) < precedencia(pila.peek())) {
                        salida.insert(0, pila.pop() + " ");
                    }
                    pila.push(elemento);
                }
            }
            while (!pila.isEmpty()) {
                salida.insert(0, pila.pop() + " ");
            }
            return salida.toString().trim();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al convertir la expresión infija a prefija.");
        }
    }

    public static String postfijaAInfija(String expresionPostfija) throws IllegalArgumentException {
        try {
            String[] elementos = expresionPostfija.split(" ");
            Stack<String> pila = new Stack<>();
            for (String elemento : elementos) {
                if (elemento.matches("\\d+")) {
                    pila.push(elemento);
                } else {
                    String op2 = pila.pop();
                    String op1 = pila.pop();
                    String nuevaExpresion = "(" + op1 + " " + elemento + " " + op2 + ")";
                    pila.push(nuevaExpresion);
                }
            }
            return pila.pop();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al convertir la expresión postfija a infija.");
        }
    }
    
  public static String prefijaAInfija(String expresionPrefija) {
        String[] elementos = new StringBuilder(expresionPrefija).reverse().toString().split(" ");
        Stack<String> pila = new Stack<>();
        for (String elemento : elementos) {
            if (elemento.matches("\\d+")) {
                pila.push(elemento);
            } else {
                String op1 = pila.pop();
                String op2 = pila.pop();
                String nuevaExpresion = "(" + op1 + " " + elemento + " " + op2 + ")";
                pila.push(nuevaExpresion);
            }
        }
        return pila.pop();
    }

    private static int precedencia(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}
