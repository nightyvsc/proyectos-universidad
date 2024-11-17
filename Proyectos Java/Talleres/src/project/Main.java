/*Integrantes Juan Manuel Diaz, Nicol Vargas 
Grupo: 9
Fecha: 10/11/2024
Taller: Proyecto Final Java*/

package project;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){

        Cliente cliente = new Cliente(null, null, null, null);
        Hotel hotel = new Hotel(null, null, null, null, null);
        Reserva reserva = new Reserva(null, null, null, null, null,null,null);
        PlanTuristico planTuristico = new PlanTuristico(null, null, null, null, null, null, null);
        Vuelo vuelo = new Vuelo(null, null, null, null, null, null, null);
        Aerolinea aerolinea = new Aerolinea(null, null);

        int opcion = 0;
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("Bienvenido al sistema de gestión de agencia de viajes");
            System.out.println("Elija una opción:");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Planes Turísticos");
            System.out.println("3. Gestión de Vuelos");
            System.out.println("4. Gestión de Aerolíneas");
            System.out.println("5. Gestión de Hoteles");
            System.out.println("6. Gestión de Reservas");
            System.out.println("7. Salir");
            opcion = s.nextInt();
            switch (opcion) {
                case 1:
                    cliente.menu();                    
                    break;
                case 2:
                    planTuristico.menu();
                    break;
                case 3:
                    vuelo.menu();
                    break;
                case 4:
                    aerolinea.menu();
                    break;
                case 5:
                    hotel.menu();
                    break;
                case 6:
                    reserva.menu();
                    break;
                case 7: 
                    break;
                default:
                    break;
            }
        }
        while(opcion!=7);
    }
    
}



