package Notaciones;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese una expresi�n: ");
        String expresion = scanner.nextLine();

        ConvertirExpresion accesoMetodos = new ConvertirExpresion(expresion);

        String tipoExpresion = accesoMetodos.detectarTipoExpresion(expresion);
        System.out.println("El tipo de la expresi�n ingresada es: " + tipoExpresion);
        if (tipoExpresion.equals("Expresi�n no reconocida")) {
            System.out.println("El programa se cerr� debido a que no se reconoci� correctamente la expresi�n");
            System.exit(1);
        }
        
         System.out.println("ESCRIBA MANUALMENTE EL DESTINO DE CONVERSI�N > 'infija, prefija, postfija'");
        String expresionDeSALIDA = scanner.nextLine();
        
        String resultado = accesoMetodos.convertirExpresion(expresion, expresionDeSALIDA);
    }
}
