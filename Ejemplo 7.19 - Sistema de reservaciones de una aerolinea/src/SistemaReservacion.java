import java.util.Arrays;
import java.util.Scanner;

public class SistemaReservacion {

    private static boolean isPrimeraClase = true;
    private static boolean isClaseEconomica = true;

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        boolean[] asientos = new boolean[10]; //array de 10 espacios (10 asientos)
        int opcion;

        Arrays.fill(asientos, false); //se llena el array de valores "false"

        System.out.println("Bienvenido a Aerolineas JAVA");

        //menu de opciones de sistema de reservacion
        do {
            System.out.println();
            System.out.println("1. Primera Clase");
            System.out.println("2. Clase Economica");
            System.out.println("3. Salir");

            System.out.print("Introduce un numero: ");
            opcion = entrada.nextInt();

            //depende la opcion que elige el usuario hace algo
            if (opcion == 1){
                primeraClase(asientos);
            } else if (opcion == 2){
                economica(asientos);
            }else if (opcion == 3) {
                System.out.println("Gracias por usar nuestros servicios");
            }else {
                System.out.println("Elija una opción correcta");
            }

        } while ( (isPrimeraClase || isClaseEconomica) && opcion != 3 );

    }

    public static void reservarAsiento(boolean[] asientos, int asiento, String clase) {
        asientos[asiento] = true;
        if (clase.equals("primera")) {
            System.out.printf("%nPase de abordar%nUsted saco un boleto para %s%nAsiento %02d reservado%n", "'Primera Clase'", asiento + 1);
        } else if (clase.equals("economica")) {
            System.out.printf("%nPase de abordar%nUsted saco un boleto para %s%nAsiento %02d reservado%n", "'Clase Economica'", asiento + 1);
        }
    }
    //validacion para ver si primera clase esta lleno. lleno false / asiento disponible true.
    public static boolean estaDisponible(boolean[] asientos, String clase){
        if(clase.equals("primera")){
            for(int i = 0; i < 5; i++){
                if(!asientos[i]){
                    return true;
                }
            }
        }
        else if(clase.equals("economica")){
            for(int i = 5; i < asientos.length; i++){
                if(!asientos[i]){
                    return true;
                }
            }
        }

        return false;
    }

    //reservacion de primera clase
    public static void primeraClase(boolean[] asientos){
        Scanner scanner = new Scanner(System.in);
        boolean reservaHecha = false;
        if (estaDisponible(asientos, "primera")) {
            for (int i = 0; i < 5; i++) {
                if (!asientos[i]){
                    reservarAsiento(asientos, i, "primera");
                    reservaHecha = true;
                }
                if (reservaHecha){
                    break;
                }
            }
        } else {
            isPrimeraClase = false;
            System.out.println("El asiento en primera clase no esta disponible");
            System.out.println("Quiere un asiento en económica? (s/n)");
            String respuesta = scanner.nextLine();
            if (respuesta.equals("s")) {
                economica(asientos);
            }
        }
    }

    private static void economica(boolean[] asientos) {
        boolean reservaHecha = false;
        Scanner scanner = new Scanner(System.in);
        if (estaDisponible(asientos, "economica")) {
            for (int i = 5; i < asientos.length; i++) {
                if (!asientos[i]){
                    reservarAsiento(asientos, i , "economica");
                    reservaHecha = true;
                }
                if (reservaHecha){
                    break;
                }
            }
        } else {
            System.out.println("El asiento en económica no esta disponible");
            isClaseEconomica = false;
            if (isPrimeraClase) {
                System.out.println("Quiere un asiento en primera clase? (s/n)");
                String respuesta = scanner.nextLine();
                if (respuesta.equals("s")) {
                    primeraClase(asientos);
                }
            }else {
                System.out.println("Disculpe! Ya no hay mas asientos disponibles. El proximo vuelo sale en 3hs.");
            }
        }
    }
}