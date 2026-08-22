import java.util.Scanner;
import java.util.Random;

public class SistemaEstacionamiento {

    //Tablero de 10x10 (vías + estacionamiento)
    public static String[][] tablero = new String[10][10];

    //Variables ha usar
    public static int vehiculos_ingresados = 0;
    public static int vehiculos_cobrados = 0;
    public static double total_recaudado = 0.0;
    public static double tarifa = 10.0; //tarifa por vehiculo

    //Variables para las coordenadas de entrada y salida
    public static int entrada_fila, entrada_columna;
    public static int salida_fila, salida_columna;

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Inicializar el tablero
        inicializarTablero();

        generarEntradaSalida();

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
            System.out.println("");
            

            if (scanner.hasNextInt()) {

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
                        System.out.println("");
                        break;
                
                    default:
                        System.out.println("Opción inválida. Vuelva a intentarlo");
                        System.out.println("");
                        break;
                }
            } else {
                System.out.println("[ERROR] No se permiten letras. Intentelo de nuevo.");
                System.out.println("");
                scanner.next();
            }
            
        }
        System.out.println("");
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

    /**
     * Módulo 1: Ingresar vehiculo, pedir la placa como verificar su estructura, también hacer el cobro de la tarifa y entregar cambio.
     */
    public static void ingresarVehiculo() {

        if (vehiculos_ingresados == 64) {
            System.out.println("[ERROR]: El estacionamiento se encuentra lleno");
            System.out.println("");
            return;
        }

        System.out.println("Ingresar la placa: ");//Formato [P###LLL]
        String placa = scanner.next();

        if(!placa.equals(placa.toUpperCase())){
            System.out.println("[ERROR] No se admiten minusculas. Intentelo de nuevo.");
            System.out.println("");
            return;
        }
        /*
        Reglas del formato: 1. Tener 7 caracteres, 2. Empezar con P, 3. posición 1, 2, 3 numeros, 4. posicion 4, 5, 6 letras
         */

        if (!placa.matches("^P[0-9]{3}[A-Z]{3}$")){
            System.out.println("[ERROR] La placa debe tener el formato P###LLL. Intentelo de nuevo.");
            System.out.println("");
            return;
        }

        for (int i=1; i<=8; i++) {
            for (int j=1; j<=8; j++) {
                if (tablero[i][j].equals(placa)) {
                    System.out.println("[ERROR] El vehiculo ya se encuentra dentro del estacionamiento. Intentelo de nuevo.");
                    System.out.println("");
                    return;
                }
            }
        }

        System.out.println("Fila en la que desea estacionar (1-8): ");
        int fila = scanner.nextInt();

        if (fila<1 || fila>8) {
            System.out.println("[ERROR] Fila fuera de rango. Intentelo de nuevo.");
            System.out.println("");
            return;
        }

        System.out.println("Columna en la que desea estacionar (1-8): ");
        int columna = scanner.nextInt();

        if (columna<1 || columna>8) {
            System.out.println("[ERROR] Columna fuera de rango. Intentelo de nuevo.");
            System.out.println("");
            return;
        }

        if(!tablero[fila][columna].equals("L")) {
            System.out.println("[ERROR] El espacio ya está ocupado. Intentelo de nuevo.");
            System.out.println("");
            return;
        }
        //Cobro
        System.out.println("La tarifa es de: Q" + tarifa);

        System.out.println("Ingrese el monto: ");
        double monto = scanner.nextDouble();

        if (monto<0) {
            System.out.println("[ERROR] No se admiten montos negativos. Intentelo de nuevo.");
            System.out.println("");
            return;
        }

        if (monto>=0 && monto<10) {
            System.out.println("[ERROR] Monto insuficiente. Intentelo de nuevo.");
            System.out.println("");
            return;
        }

        double cambio = 0;
        cambio = monto - tarifa;

        System.out.println("El cambio es de: Q" + cambio);

        vehiculos_ingresados = vehiculos_ingresados + 1;
        vehiculos_cobrados = vehiculos_cobrados + 1;
        total_recaudado = total_recaudado + tarifa;

        tablero[fila][columna] = placa;
        System.out.println("¡Vehiculo registrado correctamente!");
        System.out.println("");
    }

    /**
     * Módulo 2: Retirar vehiculo por placa. Pedir confirmación antes de eliminar el vehículo
     */
    public static void retirarVehiculo() {
        System.out.println("Ingresar la placa: ");//Formato [P###LLL]
        String placa = scanner.next();

        if(!placa.equals(placa.toUpperCase())){
            System.out.println("[ERROR] No se admiten minusculas. Intentelo de nuevo.");
            System.out.println("");
            return;
        }
        /*
        Reglas del formato: 1. Tener 7 caracteres, 2. Empezar con P, 3. posición 1, 2, 3 numeros, 4. posicion 4, 5, 6 letras
         */

        if (!placa.matches("^P[0-9]{3}[A-Z]{3}$")){
            System.out.println("[ERROR] La placa debe tener el formato P###LLL. Intentelo de nuevo.");
            System.out.println("");
            return;
        }

        int filaEncontrada = -1;
        int columnaEncontrada = -1;

        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++) {
                if (tablero[i][j].equals(placa)) { //Si el tablero encuentra una fila y columna que sea igual a la placa los valores se cambiaran
                    filaEncontrada = i;
                    columnaEncontrada = j;
                    break;
                }
            }
        }
        if (filaEncontrada == -1) {
            System.out.println("[ERROR] La placa no existe en el estacionamiento. Intentelo de nuevo.");
            System.out.println("");
            return;
        }
            System.out.println("El vehículo que encuentra en la fila " + filaEncontrada + " y columna " + columnaEncontrada + " será eliminado.");

        System.out.println("¿Esta seguro que desea eliminar la placa? (S/N)");
        String confirmacion = scanner.next();

            if (!confirmacion.equalsIgnoreCase("S") && !confirmacion.equalsIgnoreCase("N")) {
                System.out.println("[ERROR] Signo inválido. Intentelo de nuevo.");
                System.out.println("");
                return;
            }

            if (confirmacion.equalsIgnoreCase("S")) {
                tablero[filaEncontrada][columnaEncontrada] = "L";

                vehiculos_ingresados = vehiculos_ingresados - 1;
                System.out.println("¡Vehículo retirado exitosamente!");
            } else {
                System.out.println("-----Operación cancelada-----");
            }
        System.out.println("");
    }

    /**
     * Módulo 3: Imprimir el tablero completo de 10x10 en la consola, incluyendo guías de números para filas y columnas, así como los símbolos que representan las vías exteriores y los lugares libres.
     */
    public static void mostrarEstacionamiento() {
        System.out.println("\n    1 2 3 4 5 6 7 8"); //Encabezado de columnas
        for (int i = 0; i < 10; i++) {
            if (i > 0 && i < 9) {
                System.out.print(i + " "); //Encabezado de filas
            } else {
                System.out.print("  "); //Espacio para las vías exteriores
            }

            for (int j = 0; j < 10; j++) {

                if (!tablero[i][j].equals("=") && !tablero[i][j].equals("L") && !tablero[i][j].equals("E") && !tablero[i][j].equals("S")) {
                    System.out.print("A ");
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println(); //Salto de línea después de cada fila
        }

        int espacio_libre = 64 - vehiculos_ingresados;
        System.out.println("");
        System.out.println("La cantidad de espacios ocupados es de: " + vehiculos_ingresados);
        System.out.println("La cantidad de espacios libres es de: " + espacio_libre);
        System.out.println("");
    }

    /**
     * Módulo 4: Buscar vehículo por placa y mostrar la fila y columna en la que se encuentra.
     */
    public static void buscarVehiculo() {
        System.out.println("Ingresar la placa: ");//Formato [P###LLL]
        String placa = scanner.next();

        if(!placa.equals(placa.toUpperCase())){
            System.out.println("[ERROR] No se admiten minusculas. Intentelo de nuevo.");
            System.out.println("");
            return;
        }
        /*
        Reglas del formato: 1. Tener 7 caracteres, 2. Empezar con P, 3. posición 1, 2, 3 numeros, 4. posicion 4, 5, 6 letras
         */

        if (!placa.matches("^P[0-9]{3}[A-Z]{3}$")){
            System.out.println("[ERROR] La placa debe tener el formato P###LLL. Intentelo de nuevo.");
            System.out.println("");
            return;
        }

        int filaEncontrada = -1;
        int columnaEncontrada = -1;

        for(int i=1; i<=8; i++){
            for(int j=1; j<=8; j++) {
                if (tablero[i][j].equals(placa)) { //Si el tablero encuentra una fila y columna que sea igual a la placa los valores se cambiaran
                    filaEncontrada = i;
                    columnaEncontrada = j;
                    break;
                }
            }
        }
        if (filaEncontrada == -1) {
            System.out.println("[ERROR] La placa no existe en el estacionamiento. Intentelo de nuevo.");
            System.out.println("");
            return;
        }
        System.out.println("El vehículo se encuentra en la fila " + filaEncontrada + " y columna " + columnaEncontrada);
        System.out.println("");
    }

    /**
     * Módulo 5: Mostrar la ruta más corta entre la entrada y la salida del estacionamiento.
     */
    public static void mostrarRuta() {

        System.out.println("EN DESARROLLO...");
        System.out.println("");
    }

    /**
     * Módulo 6: Mostrar todos los ingresos, los vehículos cobrados y el total recaudado durante toda la ejecución del programa.
     */
    public static void ingresosEstacionamiento() {

        total_recaudado = tarifa * vehiculos_cobrados;

        System.out.println("===== INGRESOS =====");
        System.out.println("");
        System.out.println("Vehiculos cobrados: " + vehiculos_cobrados);
        System.out.println("Tarifa por vehículo: Q10.00");
        System.out.println("El total de ingresos es de: Q" + total_recaudado);
        System.out.println("");
    }
    /**
     * Módulo para generar la entrada y salida de forma aleatoria, se asegurá que no queden en el mismo lugar y que no esten en las esquinas.
     */
    public static void generarEntradaSalida() {
        Random random = new Random();

        //Generación aleatoria de la entrada.
        //Se decide una calle al azar: 1-Arriba, 2-Abajo, 3-Izquierda, 4-Derecha.
        int entrada = random.nextInt(4) + 1;
        int posicionEntrada = random.nextInt(8) + 1; //Entre 1 y 8 para evitar las esquinas (0 y 9)

        if (entrada == 1) {
            entrada_fila = 0;
            entrada_columna = posicionEntrada;
        } else if (entrada == 2) {
            entrada_fila = 9;
            entrada_columna = posicionEntrada;
        } else if (entrada == 3) {
            entrada_fila = posicionEntrada;
            entrada_columna = 0;
        } else {
            entrada_fila = posicionEntrada;
            entrada_columna = 9;
        }

        //Generación aleatoria de la salida.
        //Se usa el bucle dowhile para que se repita el ciclo si la entrada y la salida coincide en la misma posición.
        do {
            int salida = random.nextInt(4) + 1; //+ 1 para pasar de las posiciones 0, 1, 2, 3 a 1, 2, 3, 4.
            int posicionSalida = random.nextInt(8) + 1;

            if (salida == 1) {
                salida_fila = 0;
                salida_columna = posicionSalida;
            } else if (salida == 2) {
                salida_fila = 9;
                salida_columna = posicionSalida;
            } else if (salida == 3) {
                salida_fila = posicionSalida;
            } else {
                salida_fila = posicionSalida;
                salida_columna = 9;
            }
        } while (entrada_fila == salida_fila && entrada_columna == salida_columna);

        tablero[entrada_fila][entrada_columna] = "E";
        tablero[salida_fila][salida_columna] = "S";
        System.out.println("");
    }
}