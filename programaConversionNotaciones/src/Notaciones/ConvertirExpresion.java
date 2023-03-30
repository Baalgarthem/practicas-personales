package Notaciones;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertirExpresion {

    static final String NOTACION_INFIJA = "\\s*(\\d+\\s*[+\\-*/^]\\s*)+\\d+\\s*(\\(\\s*(\\d+\\s*[+\\-*/^]\\s*)+\\d+\\s*\\)\\s*(\\s*[+\\-*/^]\\s*\\(\\s*(\\d+\\s*[+\\-*/^]\\s*)+\\d+\\s*\\)\\s*)*)*";
    static final String NOTACION_PREFIJA = "\\s*[\\+\\-\\*/\\^]\\s*((\\s*(\\d+(\\.\\d+)?)\\s*)+|\\s*[\\+\\-\\*/\\^]\\s*((\\s*(\\d+(\\.\\d+)?)\\s*)+\\s*)+)+";
    static final String NOTACION_POSTFIJA = "\\d+(\\.\\d+)?(\\s+\\d+(\\.\\d+)?\\s+[\\+\\-\\*/\\^])*\\s*\\d+(\\.\\d+)?\\s*[\\+\\-\\*/\\^]?";

    public ConvertirExpresion(String expresionIngresada) {
    }

    public ConvertirExpresion() {
    }

    private static final Map<Character, Integer> precedencia = new HashMap<>();

    static {
        precedencia.put('^', 3);
        precedencia.put('*', 2);
        precedencia.put('/', 2);
        precedencia.put('+', 1);
        precedencia.put('-', 1);
    }

    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int jerarquiaOperadores(char operador) {
        switch (operador) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    public String detectarTipoExpresion(String expresion) {
        Map<String, String> expresionesRegulares = new HashMap<>();
        expresionesRegulares.put(NOTACION_INFIJA, "infija");
        expresionesRegulares.put(NOTACION_PREFIJA, "prefija");
        expresionesRegulares.put(NOTACION_POSTFIJA, "postfija");

        for (Map.Entry<String, String> entrada : expresionesRegulares.entrySet()) {
            if (expresion.matches(entrada.getKey())) {
                return entrada.getValue();
            }
        }

        return "Expresión no reconocida";
    }

public static String infijaAPrefija(String expresionInfija) {
    StringBuilder expresionPrefija = new StringBuilder();
    Stack<Character> pilaOperadores = new Stack<>();
    char[] elementos = expresionInfija.replaceAll("\\s+","").toCharArray();

    // Se recorre la expresión infija de derecha a izquierda
    for (int i = elementos.length - 1; i >= 0; i--) {
        char elemento = elementos[i];

        // Si el elemento es un número, se agrega a la expresión prefija
        if (Character.isDigit(elemento)) {
            expresionPrefija.insert(0, elemento);

            // Si el elemento anterior es un número, se agregaron varios dígitos
            while (i > 0 && Character.isDigit(elementos[i - 1])) {
                i--;
                expresionPrefija.insert(0, elementos[i]);
            }
            expresionPrefija.insert(0, ' ');
        } else if (elemento == ')' || elemento == '(') {
            if (elemento == ')') {
                pilaOperadores.push(elemento);
            } else {
                while (!pilaOperadores.isEmpty() && pilaOperadores.peek() != ')') {
                    expresionPrefija.insert(0, pilaOperadores.pop() + " ");
                }
                if (!pilaOperadores.isEmpty()) {
                    pilaOperadores.pop();
                }
            }
        } else if (precedencia.containsKey(elemento)) {
            while (!pilaOperadores.isEmpty() && precedencia.get(elemento) < precedencia.get(pilaOperadores.peek())) {
                expresionPrefija.insert(0, pilaOperadores.pop() + " ");
            }
            pilaOperadores.push(elemento);
        }
    }

    // Después de recorrer toda la expresión, se agregan los operadores restantes a la expresión prefija
    while (!pilaOperadores.isEmpty()) {
        expresionPrefija.insert(0, pilaOperadores.pop() + " ");
    }

    return expresionPrefija.toString().trim();
}


public static String infijaAPostfija(String expresionInfija) {
    StringBuilder expresionPostfija = new StringBuilder();
    Stack<Character> pilaOperadores = new Stack<>();
    String[] elementos = expresionInfija.replaceAll("\\s+", "").split("(?<=[\\d)])(?=[\\+\\-\\*/\\^\\(])|(?<=[\\+\\-\\*/\\^\\(])(?=[\\d\\)])");

    for (String elemento : elementos) {
        if (elemento.matches("\\d+(\\.\\d+)?")) { // Si el elemento es un número, se agrega a la expresión postfija
            expresionPostfija.append(elemento).append(" ");
        } else if (elemento.equals("(")) {
            pilaOperadores.push('(');
        } else if (elemento.equals(")")) {
            while (!pilaOperadores.isEmpty() && pilaOperadores.peek() != '(') {
                expresionPostfija.append(pilaOperadores.pop()).append(" ");
            }
            if (!pilaOperadores.isEmpty() && pilaOperadores.peek() == '(') {
                pilaOperadores.pop();
            }
        } else { // Si el elemento es un operador
            while (!pilaOperadores.isEmpty() && precedencia.get(pilaOperadores.peek()) >= precedencia.get(elemento.charAt(0))) {
                expresionPostfija.append(pilaOperadores.pop()).append(" ");
            }
            pilaOperadores.push(elemento.charAt(0));
        }
    }

    while (!pilaOperadores.isEmpty()) { // Después de recorrer toda la expresión, se agregan los operadores restantes a la expresión postfija
        expresionPostfija.append(pilaOperadores.pop()).append(" ");
    }

    return expresionPostfija.toString().trim();
}

    public static String convertirPrefijaAInfija(String prefija) {
        prefija = prefija.replaceAll("\\s+", ""); // ignora los espacios en blanco

        // Invertir la cadena de entrada y convertirla a una pila
        StringBuilder prefijaInvertida = new StringBuilder(prefija).reverse();
        Deque<String> pila = new ArrayDeque<>();

        // Recorrer la cadena de entrada invertida
        for (int i = 0; i < prefijaInvertida.length(); i++) {
            char caracter = prefijaInvertida.charAt(i);
            if (Character.isLetterOrDigit(caracter)) {
                pila.push(Character.toString(caracter));
            } else if (esOperador(caracter)) {
                String operando1 = pila.pop();
                String operando2 = pila.pop();
                String subexpresion = "(" + operando1 + caracter + operando2 + ")";
                pila.push(subexpresion);
            }
        }

        return pila.pop();
    }

    public static String convertirPrefijaAPostfija(String prefija) {
        prefija = prefija.replaceAll("\\s+", ""); // ignora los espacios en blanco

        // Invertir la cadena de entrada y convertirla a una pila
        StringBuilder prefijaInvertida = new StringBuilder(prefija).reverse();
        Deque<String> pila = new ArrayDeque<>();

        // Recorrer la cadena de entrada invertida
        for (int i = 0; i < prefijaInvertida.length(); i++) {
            char caracter = prefijaInvertida.charAt(i);
            if (Character.isLetterOrDigit(caracter)) {
                pila.push(Character.toString(caracter));
            } else if (esOperador(caracter)) {
                String operand2 = pila.pop();
                String operand1 = pila.pop();
                String postfijaSubexpresion = operand1 + operand2 + caracter;
                pila.push(postfijaSubexpresion);
            }
        }

        return pila.pop();
    }

    public static String convertirPostfijaAPrefija(String postfija) {
        postfija = postfija.replaceAll("\\s+", ""); // ignora los espacios en blanco

        // Convertir la cadena de entrada a una pila
        Deque<String> pila = new ArrayDeque<>();

        // Recorrer la cadena de entrada de derecha a izquierda
        for (int i = postfija.length() - 1; i >= 0; i--) {
            char caracter = postfija.charAt(i);
            if (Character.isLetterOrDigit(caracter)) {
                pila.push(Character.toString(caracter));
            } else if (esOperador(caracter)) {
                String operand1 = pila.pop();
                String operand2 = pila.pop();
                String operacion = operand1 + operand2 + caracter;
                pila.push(operacion);
            }
        }

        return pila.pop();
    }

    public static String convertirPostfijaAInfija(String postfija) {
        postfija = postfija.replaceAll("\\s+", ""); // ignorar espacios en blanco

        // Convertir la cadena de entrada a una pila
        Deque<String> pila = new ArrayDeque<>();

        // Recorrer la cadena de entrada
        for (int i = 0; i < postfija.length(); i++) {
            char caracter = postfija.charAt(i);
            if (Character.isLetterOrDigit(caracter)) {
                pila.push(String.valueOf(caracter));
            } else {
                String operando2 = pila.pop();
                String operando1 = pila.pop();
                String expresion = "(" + operando1 + caracter + operando2 + ")";
                pila.push(expresion);
            }
        }

        return pila.pop();
    }

    public String convertirExpresion(String expresion, String tipoDestino) {
        String resultado = "";
        String tipoExpresion = detectarTipoExpresion(expresion);

        switch (tipoExpresion) {
            case "infija":
                if (tipoDestino.equals("prefija")) {
                    resultado = infijaAPrefija(expresion);
                    System.out.println("La conversión a notación prefija es: " + resultado);
                } else if (tipoDestino.equals("postfija")) {
                    resultado = infijaAPostfija(expresion);
                    System.out.println("La conversión a notacion postfija es: " + resultado);
                } else {
                    System.out.println("Elija un destino válido");
                }
                break;
            case "prefija":
                if (tipoDestino.equals("infija")) {
                    resultado = convertirPrefijaAInfija(expresion);
                    System.out.println("La conversión a notacion infija es: " + resultado);
                } else if (tipoDestino.equals("postfija")) {
                    resultado = convertirPrefijaAPostfija(expresion);
                    System.out.println("La conversión a notacion postfija es: " + resultado);
                } else {
                    System.out.println("Elija un destino válido");
                }
                break;
            case "postfija":
                if (tipoDestino.equals("infija")) {
                    resultado = convertirPostfijaAInfija(expresion);
                    System.out.println("La conversión a notacion infija es: " + resultado);
                } else if (tipoDestino.equals("prefija")) {
                    resultado = convertirPostfijaAPrefija(expresion);
                    System.out.println("La conversión a notacion prefija es: " + resultado);
                } else {
                    System.out.println("Elija un destino válido");
                }
                break;
            case "Expresión no reconocida":
                System.exit(1);
                break;

            default:
                System.out.println("No se ha elegido una opción válida, escriba la opción deseada manualmente por favor");
                break;
        }

        return resultado;

    }

}
