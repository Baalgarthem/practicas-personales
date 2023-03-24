package programaconversionnotaciones;

/**
 * @author Arturo RAMIREZ
 */
import java.util.Scanner;

public class ProgramaConversionNotaciones {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la expresion infija: ");
        String expresionInfija = sc.nextLine();
        
        System.out.println("Seleccione la conversión a realizar: ");
        System.out.println("1. Infija a postfija");
        System.out.println("2. Infija a prefija");
        System.out.println("3. Postfija a infija");
        System.out.println("4. Prefija a infija");
        int opcion = sc.nextInt();
        
        String resultado;
        switch (opcion) {
            case 1:
                resultado = conversion.infijaAPostfija(expresionInfija);
                System.out.println("Expresión postfija: " + resultado);
                break;
            case 2:
                resultado = conversion.infijaAPrefija(expresionInfija);
                System.out.println("Expresión prefija: " + resultado);
                break;
            case 3:
                resultado = conversion.postfijaAInfija(expresionInfija);
                System.out.println("Expresión infija: " + resultado);
                break;
            case 4:
                resultado = conversion.prefijaAInfija(expresionInfija);
                System.out.println("Expresión infija: " + resultado);
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }
}
