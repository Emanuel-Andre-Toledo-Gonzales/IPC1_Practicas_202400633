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

        if (vehiculos_ingresados == 64) {
            System.out.println("[ERROR]: El estacionamiento se encuentra lleno");
            return;
        }

        System.out.println("Ingresar la placa: ");//Formato [P###LLL]
        String placa = scanner.next();

        if(!placa.equals(placa.toUpperCase())){
            System.out.println("[ERROR] No se admiten minusculas. Intentelo de nuevo.");
        }
        /*
        Reglas del formato: 1. Tener 7 caracteres, 2. Empezar con P, 3. posición 1, 2, 3 numeros, 4. posicion 4, 5, 6 letras
         */

        if (!placa.matches("^P[0-9]{3}[A-Z]{3}$")){
            System.out.println("[ERROR] La placa debe tener el formato P###LLL. Intentelo de nuevo.");
            return;
        }

        for (int i=1; i<=8; i++) {
            for (int j=1; j<=8; j++) {
                if (tablero[i][j].equals(placa)) {
                    System.out.println("[ERROR] El vehiculo ya se encuentra dentro del estacionamiento. Intentelo de nuevo.");
                    return;
                }
            }
        }

        System.out.println("Fila en la que desea estacionar (1-8): ");
        int fila = scanner.nextInt();

        if (fila<1 || fila>8) {
            System.out.println("[ERROR] Fila fuera de rango. Intentelo de nuevo.");
            return;
        }

        System.out.println("Columna en la que desea estacionar (1-8): ");
        int columna = scanner.nextInt();

        if (columna<1 || columna>8) {
            System.out.println("[ERROR] Columna fuera de rango. Intentelo de nuevo.");
            return;
        }

        if(!tablero[fila][columna].equals("L")) {
            System.out.println("[ERROR] El espacio ya ocupado. Intentelo de nuevo.");
            return;
        }
        //Cobro
        System.out.println("La tarifa es de: Q" + tarifa);

        System.out.println("Ingrese el monto: ");
        double monto = scanner.nextDouble();

        if (monto<0) {
            System.out.println("[ERROR] No se admiten montos negativos. Intentelo de nuevo.");
            return;
        }

        if (monto>=0 && monto<10) {
            System.out.println("[ERROR] Monto insuficiente. Intentelo de nuevo.");
            return;
        }

        double cambio = 0;
        cambio = monto - tarifa;

        System.out.println("El cambio es de: " + cambio);

        vehiculos_ingresados = vehiculos_ingresados + 1;
        total_recaudado = total_recaudado + tarifa;

        tablero[fila][columna] = placa;
        System.out.println("¡Vehiculo registrado correctamente!");
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
        System.out.println("\n   0 1 2 3 4 5 6 7 8"); //Encabezado de columnas
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