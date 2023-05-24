package Notaciones;

import java.awt.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertirExpresion {

    static final String OPERADORES = "[\\+\\-\\*/\\^]";
    static final String NUMERO = "\\d+(\\.\\d+)?";
    static final String NOTACION_INFIJA = "\\s*(\\d+\\s*[+\\-*/^]\\s*)+\\d+\\s*(\\(\\s*(\\d+\\s*[+\\-*/^]\\s*)+\\d+\\s*\\)\\s*(\\s*[+\\-*/^]\\s*\\(\\s*(\\d+\\s*[+\\-*/^]\\s*)+\\d+\\s*\\)\\s*)*)*";
    static final String NOTACION_PREFIJA = "\\s*" + OPERADORES + "\\s*((\\s*" + NUMERO + "\\s*)+|\\s*" + OPERADORES + "\\s*((\\s*" + NUMERO + "\\s*)+\\s*)+)+";
static final String NOTACION_POSTFIJA = "(\\s*\\d+(\\.\\d+)?\\s+)+((\\s*[\\+\\-\\*/\\^]\\s+\\d+(\\.\\d+)?\\s+)+)+[\\+\\-\\*/\\^]?\\s*|\\d+(\\.\\d+)?(\\s+\\d+(\\.\\d+)?(\\s+[\\+\\-\\*/\\^]\\s+)?)*\\s*[\\+\\-\\*/\\^]?\\s*";

    public ConvertirExpresion(String expresionIngresada) {
    }

    public ConvertirExpresion() {
    }

    private static final Map<Character, Integer> precedenciaOperadores = new HashMap<>();

    static {
        precedenciaOperadores.put('^', 3);
        precedenciaOperadores.put('*', 2);
        precedenciaOperadores.put('/', 2);
        precedenciaOperadores.put('+', 1);
        precedenciaOperadores.put('-', 1);
    }

    private static final Map<String, String> expresionesRegulares = new HashMap<>();

    static {
        expresionesRegulares.put(NOTACION_INFIJA, "infija");
        expresionesRegulares.put(NOTACION_PREFIJA, "prefija");
        expresionesRegulares.put(NOTACION_POSTFIJA, "postfija");
    }

    public String detectarTipoExpresion(String expresion) {
        for (Map.Entry<String, String> entrada : expresionesRegulares.entrySet()) {
            if (expresion.matches(entrada.getKey())) {
                return entrada.getValue();
            }
        }

        return "Expresi�n no reconocida";
    }

    public static String infijaAPrefija(String expresionInfija) {
        StringBuilder expresionPrefija = new StringBuilder();
        Stack<Character> pilaOperadores = new Stack<>();
        char[] elementos = expresionInfija.replaceAll("\\s+", "").toCharArray();

        // Se recorre la expresi�n infija de derecha a izquierda
        for (int i = elementos.length - 1; i >= 0; i--) {
            char elemento = elementos[i];

            // Si el elemento es un n�mero, se agrega a la expresi�n prefija
            if (Character.isDigit(elemento)) {
                expresionPrefija.insert(0, elemento);

                // Si el elemento anterior es un n�mero, se agregaron varios d�gitos
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
            } else if (precedenciaOperadores.containsKey(elemento)) {
                while (!pilaOperadores.isEmpty() && precedenciaOperadores.get(elemento) < precedenciaOperadores.get(pilaOperadores.peek())) {
                    expresionPrefija.insert(0, pilaOperadores.pop() + " ");
                }
                pilaOperadores.push(elemento);
            }
        }

        // Despu�s de recorrer toda la expresi�n, se agregan los operadores restantes a la expresi�n prefija
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
            if (elemento.matches("\\d+(\\.\\d+)?")) { // Si el elemento es un n�mero, se agrega a la expresi�n postfija
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
                while (!pilaOperadores.isEmpty() && precedenciaOperadores.get(pilaOperadores.peek()) >= precedenciaOperadores.get(elemento.charAt(0))) {
                    expresionPostfija.append(pilaOperadores.pop()).append(" ");
                }
                pilaOperadores.push(elemento.charAt(0));
            }
        }

        while (!pilaOperadores.isEmpty()) { // Despu�s de recorrer toda la expresi�n, se agregan los operadores restantes a la expresi�n postfija
            expresionPostfija.append(pilaOperadores.pop()).append(" ");
        }

        return expresionPostfija.toString().trim();
    }

    public static String prefijaAInfija(String expresionPrefija) {
        Stack<String> pilaOperandos = new Stack<>();
        char[] elementos = expresionPrefija.trim().toCharArray();

        // Se recorre la expresi�n prefija de derecha a izquierda
        for (int i = elementos.length - 1; i >= 0; i--) {
            char elemento = elementos[i];

            // Si el elemento es un n�mero, se agrega a la pila de operandos
            if (Character.isDigit(elemento)) {
                StringBuilder sb = new StringBuilder();
                sb.append(elemento);

                // Si el elemento anterior es un n�mero, se agregaron varios d�gitos
                while (i > 0 && Character.isDigit(elementos[i - 1])) {
                    i--;
                    sb.insert(0, elementos[i]);
                }

                pilaOperandos.push(sb.toString());
            } else if (precedenciaOperadores.containsKey(elemento)) {
                // Se sacan los dos operandos superiores de la pila
                String operando1 = pilaOperandos.pop();
                String operando2 = pilaOperandos.pop();

                // Se construye la subexpresi�n infija y se agrega a la pila de operandos
                String subexpresionInfija = "(" + operando1 + " " + elemento + " " + operando2 + ")";
                pilaOperandos.push(subexpresionInfija);
            }
        }

        // Al final, la pila de operandos debe contener una sola expresi�n infija
        if (pilaOperandos.size() == 1) {
            return pilaOperandos.pop();
        } else {
            return "Expresi�n no v�lida";
        }
    }

    public static String prefijaAPostfija(String expresionPrefija) {
        StringBuilder expresionPostfija = new StringBuilder();
        Stack<Character> pilaOperadores = new Stack<>();
        char[] elementos = expresionPrefija.replaceAll("\\s+", "").toCharArray();

        // Se recorre la expresi�n prefija de derecha a izquierda
        for (int i = elementos.length - 1; i >= 0; i--) {
            char elemento = elementos[i];

            // Si el elemento es un n�mero, se agrega a la expresi�n postfija
            if (Character.isDigit(elemento)) {
                expresionPostfija.insert(0, elemento);

                // Si el elemento anterior es un n�mero, se agregaron varios d�gitos
                while (i > 0 && Character.isDigit(elementos[i - 1])) {
                    i--;
                    expresionPostfija.insert(0, elementos[i]);
                }
                expresionPostfija.insert(0, ' ');
            } else if (precedenciaOperadores.containsKey(elemento)) {
                while (!pilaOperadores.isEmpty() && precedenciaOperadores.get(elemento) < precedenciaOperadores.get(pilaOperadores.peek())) {
                    expresionPostfija.insert(0, pilaOperadores.pop() + " ");
                }
                pilaOperadores.push(elemento);
            }
        }

        // Despu�s de recorrer toda la expresi�n, se agregan los operadores restantes a la expresi�n postfija
        while (!pilaOperadores.isEmpty()) {
            expresionPostfija.insert(0, pilaOperadores.pop() + " ");
        }

        return expresionPostfija.toString().trim();
    }

 public static String postfijaAPrefija(String expresionPostfija) throws IllegalArgumentException {
    Stack<String> pilaOperandos = new Stack<>();
    String[] elementos = expresionPostfija.split("\\s+");

    for (String elemento : elementos) {
        // Si el elemento es un operando, se agrega a la pila
        if (elemento.matches("\\d+")) {
            pilaOperandos.push(elemento);
        } else if (precedenciaOperadores.containsKey(elemento.charAt(0))) {
            // Si el elemento es un operador, se sacan los dos operandos de la pila
            if (pilaOperandos.size() < 2) {
                throw new IllegalArgumentException("Expresi�n no v�lida: faltan operandos");
            }
            String operando2 = pilaOperandos.pop();
            String operando1 = pilaOperandos.pop();
            // Se construye la expresi�n prefija con los dos operandos y el operador
            String expresionPrefija = String.format("%s %s %s", elemento, operando1, operando2);
            // Se agrega la expresi�n prefija a la pila de operandos
            pilaOperandos.push(expresionPrefija);
        } else {
            throw new IllegalArgumentException("Expresi�n no v�lida: " + elemento + " no es un operando ni un operador v�lido");
        }
    }

    // Al final, la pila de operandos debe contener la expresi�n prefija completa
    if (pilaOperandos.size() != 1) {
        throw new IllegalArgumentException("Expresi�n no v�lida: sobran operandos");
    }
    return pilaOperandos.peek();
}

    
    public static String postfijaAInfija(String expresionPostfija) throws IllegalArgumentException {
        Stack<String> pilaOperandos = new Stack<>();
        String[] elementos = expresionPostfija.split("\\s+");

        for (String elemento : elementos) {
            // Si el elemento es un operando, se agrega a la pila
            if (elemento.matches("\\d+")) {
                pilaOperandos.push(elemento);
            } else if (precedenciaOperadores.containsKey(elemento.charAt(0))) {
                // Si el elemento es un operador, se sacan los dos operandos de la pila
                if (pilaOperandos.size() < 2) {
                    throw new IllegalArgumentException("Expresi�n no v�lida: faltan operandos");
                }
                String operando2 = pilaOperandos.pop();
                String operando1 = pilaOperandos.pop();
                // Se construye la expresi�n infija con los dos operandos y el operador
                String expresionInfija = String.format("(%s %s %s)", operando1, elemento, operando2);
                // Se agrega la expresi�n infija a la pila de operandos
                pilaOperandos.push(expresionInfija);
            } else {
                throw new IllegalArgumentException("Expresi�n no v�lida: " + elemento + " no es un operando ni un operador v�lido");
            }
        }

        // Al final, la pila de operandos debe contener la expresi�n infija completa
        if (pilaOperandos.size() != 1) {
            throw new IllegalArgumentException("Expresi�n no v�lida: sobran operandos");
        }
        return pilaOperandos.peek();
    }

    public String convertirExpresion(String expresion, String tipoDestino) {
        String resultado = "";
        String tipoExpresion = detectarTipoExpresion(expresion);

        switch (tipoExpresion) {
            case "infija":
                if (tipoDestino.equals("prefija")) {
                    resultado = infijaAPrefija(expresion);
                    System.out.println("La conversi�n a notaci�n prefija es: " + resultado);
                } else if (tipoDestino.equals("postfija")) {
                    resultado = infijaAPostfija(expresion);
                    System.out.println("La conversi�n a notacion postfija es: " + resultado);
                } else {
                    System.out.println("Elija un destino v�lido");
                }
                break;
            case "prefija":
                if (tipoDestino.equals("infija")) {
                    resultado = prefijaAInfija(expresion);
                    System.out.println("La conversi�n a notacion infija es: " + resultado);
                } else if (tipoDestino.equals("postfija")) {
                    resultado = prefijaAPostfija(expresion);
                    System.out.println("La conversi�n a notacion postfija es: " + resultado);
                } else {
                    System.out.println("Elija un destino v�lido");
                }
                break;
            case "postfija":
                if (tipoDestino.equals("infija")) {
                    resultado = postfijaAInfija(expresion);
                    System.out.println("La conversi�n a notacion infija es: " + resultado);
                } else if (tipoDestino.equals("prefija")) {
                    
                    resultado = postfijaAPrefija(expresion);
                    System.out.println("La conversi�n a notacion prefija es: " + resultado);
                } else {
                    System.out.println("Elija un destino v�lido");
                }
                break;
            default:
                System.out.println("No se ha elegido una opci�n v�lida, escriba la opci�n deseada manualmente por favor");
                break;
        }

        return resultado;

    }
public static boolean esOperando(String elemento) {
        return elemento.matches("\\d+(\\.\\d+)?");
    }

    public static boolean esOperador(String elemento) {
        return elemento.matches("[\\+\\-\\*/\\^]");
    }
}
