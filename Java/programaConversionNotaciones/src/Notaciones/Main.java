package Notaciones;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese una expresión: ");
        String expresion = scanner.nextLine();

        ConvertirExpresion accesoMetodos = new ConvertirExpresion(expresion);

        String tipoExpresion = accesoMetodos.detectarTipoExpresion(expresion);
        System.out.println("El tipo de la expresión ingresada es: " + tipoExpresion);
        if (tipoExpresion.equals("Expresión no reconocida")) {
            System.out.println("El programa se cerró debido a que no se reconoció correctamente la expresión");
            System.exit(1);
        }
        
         System.out.println("ESCRIBA MANUALMENTE EL DESTINO DE CONVERSIÓN > 'infija, prefija, postfija'");
        String expresionDeSALIDA = scanner.nextLine();
        
        String resultado = accesoMetodos.convertirExpresion(expresion, expresionDeSALIDA);
    }
}
