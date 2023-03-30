package Notaciones;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertirExpresion {

// Expresi�n regular para notaci�n infija
    static final String NOTACION_INFIJA = "\\d+(\\s*[\\+\\-\\*/\\^]\\s*\\d+)*(\\s*\\([^()]+\\)\\s*(\\s*[\\+\\-\\*/\\^]\\s*\\([^()]+\\)\\s*)*)*";

// Expresi�n regular para notaci�n prefija
    static final String NOTACION_PREFIJA = "\\s*[\\+\\-\\*/\\^]\\s*((\\s*(\\d+(\\.\\d+)?)\\s*)+|\\s*[\\+\\-\\*/\\^]\\s*((\\s*(\\d+(\\.\\d+)?)\\s*)+\\s*)+)+";

// Expresi�n regular para notaci�n postfija
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

    public String detectarTipoExpresion(String expresion) {
        if (expresion.matches(NOTACION_INFIJA)) {
            return "infija";
        } else if (expresion.matches(NOTACION_PREFIJA)) {
            return "prefija";
        } else if (expresion.matches(NOTACION_POSTFIJA)) {
            return "postfija";
        } else {
            return "Expresi�n no reconocida";
        }
    }

    public String infijaAPrefija(String expresionInfija) {
        StringBuilder expresionPrefija = new StringBuilder();
        Stack<Character> pilaOperadores = new Stack<>();

        // Se recorre la expresi�n infija de derecha a izquierda
        for (int i = expresionInfija.length() - 1; i >= 0; i--) {
            char c = expresionInfija.charAt(i);

            // Si el caracter es un espacio, se ignora
            if (Character.isWhitespace(c)) {
                continue;
            }

            // Si el caracter es un n�mero, se agrega a la expresi�n prefija
            if (Character.isDigit(c)) {
                expresionPrefija.insert(0, c);

                // Si el caracter anterior es un espacio, se agregaron varios d�gitos
                while (i > 0 && Character.isDigit(expresionInfija.charAt(i - 1))) {
                    i--;
                    expresionPrefija.insert(0, expresionInfija.charAt(i));
                }
                expresionPrefija.insert(0, ' ');
            } // Si el caracter es un operador
            else if (esOperador(c)) {
                while (!pilaOperadores.isEmpty() && precedencia.get(c) < precedencia.get(pilaOperadores.peek())) {
                    expresionPrefija.insert(0, pilaOperadores.pop() + " ");
                }
                pilaOperadores.push(c);
            } // Si el caracter es un par�ntesis de cierre
            else if (c == ')') {
                pilaOperadores.push(c);
            } // Si el caracter es un par�ntesis de apertura
            else if (c == '(') {
                while (!pilaOperadores.isEmpty() && pilaOperadores.peek() != ')') {
                    expresionPrefija.insert(0, pilaOperadores.pop() + " ");
                }
                pilaOperadores.pop();
            }
        }

        // Se vac�a la pila de operadores restantes
        while (!pilaOperadores.isEmpty()) {
            expresionPrefija.insert(0, pilaOperadores.pop() + " ");
        }

        return expresionPrefija.toString().trim();
    }

    public static String infijaAPostfija(String infija) {
        Stack<Character> pilaOperadores = new Stack<>();
        String postfija = "";
        String[] elementos = infija.split(NOTACION_INFIJA);

        for (String elemento : elementos) {
            if (elemento.matches("\\d+(\\.\\d+)?")) {
                postfija += elemento + " ";
            } else if (elemento.equals("(")) {
                pilaOperadores.push('(');
            } else if (elemento.equals(")")) {
                while (!pilaOperadores.isEmpty() && pilaOperadores.peek() != '(') {
                    postfija += pilaOperadores.pop() + " ";
                }
                pilaOperadores.pop();
            } else {
                while (!pilaOperadores.isEmpty() && jerarquiaOperadores(pilaOperadores.peek()) >= jerarquiaOperadores(elemento.charAt(0))) {
                    postfija += pilaOperadores.pop() + " ";
                }
                pilaOperadores.push(elemento.charAt(0));
            }
        }

        while (!pilaOperadores.isEmpty()) {
            postfija += pilaOperadores.pop() + " ";
        }

        return postfija.trim();
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

    public static String convertirInfijaAPostfija(String infija) {
        infija = infija.replaceAll("\\s+", ""); // ignora los espacios en blanco

        // Convertir la cadena de entrada a una pila
        Deque<Character> pila = new ArrayDeque<>();
        StringBuilder postfija = new StringBuilder();

        // Recorrer la cadena de entrada
        for (int i = 0; i < infija.length(); i++) {
            char caracter = infija.charAt(i);
            if (Character.isLetterOrDigit(caracter)) {
                postfija.append(caracter);
            } else if (caracter == '(') {
                pila.push(caracter);
            } else if (esOperador(caracter)) {
                while (!pila.isEmpty() && pila.peek() != '(' && precedencia.get(pila.peek()) >= precedencia.get(caracter)) {
                    postfija.append(pila.pop());
                }
                pila.push(caracter);
            } else if (caracter == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    postfija.append(pila.pop());
                }
                pila.pop(); // quita el par�ntesis izquierdo
            }
        }

        while (!pila.isEmpty()) {
            postfija.append(pila.pop());
        }

        return postfija.toString();
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
                    System.out.println("La conversi�n a notaci�n prefija es: " + resultado);
                } else if (tipoDestino.equals("postfija")) {
                    resultado = convertirInfijaAPostfija(expresion);
                    System.out.println("La conversi�n a notacion postfija es: " + resultado);
                } else {
                    System.out.println("Tipo de destino no v�lido");
                }
                break;
            case "prefija":
                System.out.println("�Desea convertir la expresion prefija a infija o postfija?");
                if (tipoDestino.equals("infija")) {
                    resultado = convertirPrefijaAInfija(expresion);
                    System.out.println("La conversi�n a notacion infija es: " + resultado);
                } else if (tipoDestino.equals("postfija")) {
                    resultado = convertirPrefijaAPostfija(expresion);
                    System.out.println("La conversi�n a notacion postfija es: " + resultado);
                } else {
                    System.out.println("Tipo de destino no valido");
                }
                break;
            case "postfija":
                System.out.println("�Desea convertir la expresion postfija a infija o prefija?");
                if (tipoDestino.equals("infija")) {
                    resultado = convertirPostfijaAInfija(expresion);
                    System.out.println("La conversi�n a notacion infija es: " + resultado);
                } else if (tipoDestino.equals("prefija")) {
                    resultado = convertirPostfijaAPrefija(expresion);
                    System.out.println("La conversi�n a notacion prefija es: " + resultado);
                } else {
                    System.out.println("Tipo de destino no valido");
                }
                break;
            default:
                System.out.println("Tipo de expresi�n no valido");
                break;
        }

        return resultado;

    }

}
