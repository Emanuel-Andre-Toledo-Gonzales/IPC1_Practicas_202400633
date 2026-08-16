import java.util.Scanner;

public class SistemaEstacionamiento {

    //Tablero de 10x10 (vías + estacionamiento)
    public static String[][] tablero = new String[10][10];

    //Variables ha usar
    public static int vehiculos_ingresados = 0;
    public static double total_recaudado = 0.0;
    public static double tarifa = 10.0; //tarifa por vehiculo

    //Variables para las coordenadas de entrada y salida
    public static int entrada_fila, entrada_columna;
    public static int salida_fila, salida_columna;

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Inicializar el tablero
        inicializarTablero();

        int opcion = 0;

        while (opcion !=7) {

            System.out.println("===== SISTEMA DE ESTACIONAMIENTO =====");
            System.out.println("1. Ingresar vehículo.");
            System.out.println("2. Retirar vehículo.");
            System.out.println("3. Mostrar estacionamiento.");
            System.out.println("4. Buscar vehículo por placa.");
            System.out.println("5. Mostrar ruta más corta entre entrada y salida.");
            System.out.println("6. Mostrar ingresos.");
            System.out.println("7. Salir.");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1: //Módulo 1: Ingresar vehículo
                    
                    ingresarVehiculo();

                    break;

                case 2: //Módulo 2: Retirar vehículo

                    retirarVehiculo();

                    break;

                case 3: //Módulo 3: Mostrar estacionamiento

                    mostrarEstacionamiento();

                    break;

                case 4: //Módulo 4: Buscar vehiculo por placa

                    buscarVehiculo();

                    break;

                case 5: //Módulo 5: Mostrar ruta más corta entre entrada y salida

                    mostrarRuta();

                    break;

                case 6: //Módulo 6: Mostrar ingresos

                    ingresosEstacionamiento();

                    break;

                case 7: //Módulo 7: Salir del programa

                    System.out.println("Salio del programa correctamente...");

                    break;
            
                default:

                    System.out.println("Opción inválida. Vuelva a intentarlo");

                    break;
            }
        }
    }

    // Vías exteriores ("=") en los bordes y lugares libres ("L") en el interior
    public static void inicializarTablero() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    tablero[i][j] = "="; // Vías exteriores
                } else {
                    tablero[i][j] = "L"; // Lugares libres
                }
            }
        }
    }

    //Módulo 1.
    public static void ingresarVehiculo() {

        System.out.println("EN DESARROLLO...");

    }

    //Módulo 2.
    public static void retirarVehiculo() {

        System.out.println("EN DESARROLLO...");

    }

    /**
     * Módulo 3: Imprimir el tablero completo de 10x10 en la consola,
     * incluyendo guías de números para filas y columnas, así como los símbolos que representan las vías exteriores y los lugares libres.
     */
    public static void mostrarEstacionamiento() {
        System.out.println("\n   0 1 2 3 4 5 6 7 8 9"); //Encabezado de columnas
        for (int i = 0; i < 10; i++) {
            if (i > 0 && i < 9) {
                System.out.print(i + " "); //Encabezado de filas
            } else {
                System.out.print("  "); //Espacio para las vías exteriores
            }

            for (int j = 0; j < 10; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println(); //Salto de línea después de cada fila
        }
    }

    //Módulo 4.
    public static void buscarVehiculo() {
        
        System.out.println("EN DESARROLLO...");

    }

    //Módulo 5.
    public static void mostrarRuta() {

        System.out.println("EN DESARROLLO...");

    }

    //Módulo 6.
    public static void ingresosEstacionamiento() {

        System.out.println("EN DESARROLLO...");

    }
}