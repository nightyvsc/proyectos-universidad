/*Integrantes Juan Manuel Diaz, Nicol Vargas 
Grupo: 9
Fecha: 10/11/2024
Taller: Proyecto Final Java*/

package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Reserva implements Serializable{
    ArrayList <Reserva> listaReservas = new ArrayList<>();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private transient Scanner s = new Scanner(System.in);
    private static final DateTimeFormatter htf = DateTimeFormatter.ofPattern("HH:mm");
    private Cliente cliente;
    private PlanTuristico planTuristico;
    private Vuelo vuelo;
    private Aerolinea aerolinea;
    private Hotel hotel;
    private LocalDate fechaReserva;
    private String codigoReserva;

    public Reserva(Cliente cliente,PlanTuristico planTuristico,Vuelo vuelo,Aerolinea aerolinea,Hotel hotel,LocalDate fechaReserva,String codigoReserva){
        this.cliente = cliente;
        this.planTuristico = planTuristico;
        this.vuelo = vuelo;
        this.aerolinea = aerolinea;
        this.hotel = hotel;
        this.fechaReserva = fechaReserva;
        this.codigoReserva = codigoReserva;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public PlanTuristico getPlanTuristico(){
        return planTuristico;
    }

    public void setPlanTuristico(PlanTuristico planTuristico){
        this.planTuristico = planTuristico;
    }

    public Vuelo getVuelo(){
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo){
        this.vuelo = vuelo;
    }

    public Aerolinea getAerolinea(){
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
    }

    public Hotel getHotel(){
        return hotel;
    }

    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }
    
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getCodigo(){
        return codigoReserva;
    }

    public void setCodigo(String codigoReserva){
        this.codigoReserva = codigoReserva;
    }

    public void menu(){
        int opcion = 0;
        LeerBinario();
        do{
            System.out.println("Eliga las opciones de la gestion de reservas");
            System.out.println("1. Creacion");
            System.out.println("2. Modificacion");
            System.out.println("3. Cancelacion");
            System.out.println("4. Generar informe");
            System.out.println("5. Salir");
            opcion = s.nextInt();
            s.nextLine();
            switch (opcion) {
                case 1:
                    Creacion();
                    break;
                case 2:
                    Modificacion();
                    break;
                case 3:
                    Cancelacion();
                    break;
                case 4:
                    Informe();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;     
            }
        }while(opcion != 5);
    }

public void Creacion() {
    fechaReserva = LocalDate.now();
    System.out.println("Ingrese el codigo de reserva");
    codigoReserva = s.nextLine();
    
    // Declarar variables para los objetos
    
    try {
        // Creación de Cliente
        System.out.println("Ingrese el codigo del cliente");
        String codigoCliente = s.nextLine();
        System.out.println("Ingrese el nombre del cliente");
        String nombreCliente = s.nextLine();
        System.out.println("Ingrese la fecha de nacimiento del cliente (formato AAAA/MM/DD)");
        String fechaNacimientoString = s.nextLine();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoString, dtf);
        System.out.println("Ingrese la información de contacto del cliente");
        String infoContacto = s.nextLine();
        cliente = new Cliente(codigoCliente, nombreCliente, fechaNacimiento, infoContacto);
    } catch (DateTimeParseException e) {
        System.out.println("Uso el formato equivocado de fecha (use AAAA/MM/DD)");
    }
    
    try {
        // Creación de PlanTuristico
        System.out.println("Ingrese el codigo del plan turistico");
        String codigoPlan = s.nextLine();
        System.out.println("Ingrese el nombre del plan turistico");
        String nombrePlan = s.nextLine();
        System.out.println("Ingrese la descripcion del plan turistico");
        String descripcion = s.nextLine();
        System.out.println("Ingrese el destino del plan turistico");
        String destinoPlan = s.nextLine();
        System.out.println("Ingrese la fecha de inicio (AAAA/MM/DD)");
        String fechaInicioString = s.nextLine();
        LocalDate fechaInicio = LocalDate.parse(fechaInicioString, dtf);
        System.out.println("Ingrese la fecha de fin (AAAA/MM/DD)");
        String fechaFinString = s.nextLine();
        LocalDate fechaFin = LocalDate.parse(fechaFinString, dtf);
        System.out.println("Ingrese el precio del plan turistico");
        String precioPlan = s.nextLine();
        planTuristico = new PlanTuristico(codigoPlan, nombrePlan, descripcion, destinoPlan, fechaInicio, fechaFin, precioPlan);
    } catch (DateTimeParseException e) {
        System.out.println("Uso el formato equivocado de fecha (use AAAA/MM/DD)");
    }
    
    try {
        // Creación de Aerolinea y Vuelo
        System.out.println("Ingrese el codigo de la aerolinea");
        String codigoAerolinea = s.nextLine();
        System.out.println("Ingrese el nombre de la aerolinea");
        String nombreAerolinea = s.nextLine();
        aerolinea = new Aerolinea(codigoAerolinea, nombreAerolinea);

        System.out.println("Ingrese el código del vuelo");
        String codigoVuelo = s.nextLine();
        System.out.println("Ingrese el origen del vuelo");
        String origen = s.nextLine();
        System.out.println("Ingrese el destino del vuelo");
        String destinoVuelo = s.nextLine();
        System.out.println("Ingrese la fecha del vuelo (AAAA/MM/DD)");
        String fechaVueloString = s.nextLine();
        LocalDate fechaVuelo = LocalDate.parse(fechaVueloString, dtf);
        System.out.println("Ingrese la hora de salida (HH:MM)");
        String horaVueloString = s.nextLine();
        LocalTime horaVuelo = LocalTime.parse(horaVueloString, htf);
        System.out.println("Ingrese el precio del vuelo");
        String precioVuelo = s.nextLine();
        vuelo = new Vuelo(codigoVuelo, aerolinea, origen, destinoVuelo, fechaVuelo, horaVuelo, precioVuelo);
    } catch (DateTimeParseException e) {
        System.out.println("Formato de fecha u hora incorrecto.");
        return;
    }

    // Creación de Hotel
    System.out.println("Ingrese el codigo del hotel");
    String codigoHotel = s.nextLine();
    System.out.println("Ingrese el nombre del hotel");
    String nombreHotel = s.nextLine();
    System.out.println("Ingrese la ubicacion del hotel");
    String ubicacionHotel = s.nextLine();
    System.out.println("Ingrese la categoria del hotel");
    String categoriaHotel = s.nextLine();
    System.out.println("Ingrese el precio por noche del hotel");
    String precioNoche = s.nextLine();
    hotel = new Hotel(codigoHotel, nombreHotel, ubicacionHotel, categoriaHotel, precioNoche);

    // Creación de la reserva
    Reserva nuevaReserva = new Reserva(cliente, planTuristico, vuelo, aerolinea, hotel, fechaReserva, codigoReserva);
    listaReservas.add(nuevaReserva);
    EscribirBinario();
}


    public void Modificacion(){
        try{
        System.out.println("Ingrese el codigo de la reserva que desea modificar");
        String codigoBusqueda = s.nextLine();
        int opcion = 0;
        for(Reserva r : listaReservas){
            if(r.getCodigo().equalsIgnoreCase(codigoBusqueda)){
                do{
                    System.out.println("Ingrese el objeto que desea modificar:");
                    System.out.println("1. Cliente");
                    System.out.println("2. Plan Turistico");
                    System.out.println("3. Vuelo");
                    System.out.println("4. Aerolinea");
                    System.out.println("5. Hotel");
                    System.out.println("6. Salir");
                    opcion = s.nextInt();
                    s.nextLine();
                    switch (opcion) {
                        case 1:
                            do{
                            System.out.println("Ingrese la opcion del atributo que quiera modificar: ");
                            System.out.println("1. Nombre");
                            System.out.println("2. Codigo");
                            System.out.println("3. Fecha de Nacimiento");
                            System.out.println("4. Informacion de contacto");
                            System.out.println("5. Salir");
                            opcion = s.nextInt();
                            s.nextLine();
                            switch (opcion) {
                                case 1:
                                    System.out.println("Ingrese el nombre por el cual desea modificar");
                                    String nombreString = s.nextLine();
                                    r.getCliente().setNombre(nombreString);
                                    break;
                                case 2:
                                    System.out.println("Ingrese el codigo por el cual desea modificar");
                                    String codigoString = s.nextLine();
                                    r.getCliente().setCodigo(codigoString);
                                    break;
                                case 3:
                                    System.out.println("Ingrese la fecha de nacimiento por la cual desea modificar (Formato AAAA/MM/DD)");
                                    try{
                                        String fechaString = s.nextLine();
                                        LocalDate fecha = LocalDate.parse(fechaString,dtf);
                                        r.getCliente().setFechaNacimiento(fecha);
                                        break;
                                    }
                                    catch(DateTimeParseException e){
                                        System.out.println("Ingrese el formato correcto");
                                    }
                                case 4:
                                    System.out.println("Ingrese la informacion de contacto por la cual desea modificar");
                                    String info = s.nextLine();
                                    r.getCliente().setInfoContacto(info);
                                    break;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Ingrese una opcion correcta");
                                    break;
                            }
                            }while(opcion!=5);
                        case 2:
                        do{
                            System.out.println("Ingrese la opcion del atributo que quiera modificar: ");
                            System.out.println("1. Nombre");
                            System.out.println("2. Codigo");
                            System.out.println("3. Descripcion");
                            System.out.println("4. Destino");
                            System.out.println("5. Fecha de inicio");
                            System.out.println("6. Fecha de fin");
                            System.out.println("7. Precio");
                            System.out.println("8. Salir");
                            opcion = s.nextInt();
                            s.nextLine();
                            switch (opcion) {
                                case 1:
                                    System.out.println("Ingrese el nombre por el cual desea modificar el plan turistico");
                                    String cambioNombre = s.nextLine();
                                    r.getPlanTuristico().setNombre(cambioNombre);
                                    break;
                                case 2:
                                    System.out.println("Ingrese el codigo por el cual desea modificar el plan turistico");
                                    String cambioCodigo = s.nextLine();
                                    r.getPlanTuristico().setCodigo(cambioCodigo);
                                    break;
                                case 3:
                                    System.out.println("Ingrese la descripcion por la cual desea modificar el plan turistico");
                                    String cambioDescripcion = s.nextLine();
                                    r.getPlanTuristico().setDescripcion(cambioDescripcion);
                                    break;
                                case 4:
                                    System.out.println("Ingrese el destino por el cual desea modificar el plan turistico");
                                    String cambioDestino = s.nextLine();
                                    r.getPlanTuristico().setDestino(cambioDestino);
                                    break;
                                case 5:
                                    try{
                                    System.out.println("Ingrese la fecha inicio por la cual desea modificar el plan turistico");
                                    String cambioFechaInicio = s.nextLine();
                                    r.getPlanTuristico().setFechaInicio(LocalDate.parse(cambioFechaInicio,dtf));
                                    break;
                                    }
                                    catch(DateTimeParseException e){
                                        System.out.println("Ingrese el formato de fecha correcto");
                                    }
                                case 6:
                                    try{
                                    System.out.println("Ingrese la fecha fin por la cual desea modificar el plan turistico");
                                    String cambioFechaFin = s.nextLine();
                                    r.getPlanTuristico().setFechaFin(LocalDate.parse(cambioFechaFin,dtf));
                                    break;
                                    }
                                    catch(DateTimeParseException e){
                                        System.out.println("Ingrese el formato de fecha correcto");
                                    }
                                case 7:
                                    System.out.println("Ingrese el precio por el cual desea modificar el plan turistico");
                                    String cambioPrecio = s.nextLine();
                                    r.getPlanTuristico().setPrecio(cambioPrecio);
                                    break;
                                case 8:
                                    break;
                                default:
                                    System.out.println("Ingrese una opcion correcta");
                                    break;
                            }
        
                        }
                        while(opcion!=8);
                    case 3:
                    do{
                        System.out.println("Ingrese la opcion del atributo que quiera modificar: ");
                        System.out.println("1. Codigo");
                        System.out.println("2. Aerolinea");
                        System.out.println("3. Origen");
                        System.out.println("4. Destino");
                        System.out.println("5. Fecha");
                        System.out.println("6. Hora de salida");
                        System.out.println("7. Precio");
                        System.out.println("8. Salir");
                        opcion = s.nextInt();
                        s.nextLine();
                        switch (opcion) {
                            case 1:
                                System.out.println("Ingrese el codigo por el cual desea modificar el vuelo");
                                String cambioCodigo = s.nextLine();
                                r.getVuelo().setCodigo(cambioCodigo);
                                break;
                            case 2:
                                System.out.println("Ingrese el nombre de la aerolinea el cual desea modificar");
                                String cambioNombre = s.nextLine();
                                System.out.println("Ingrese el codigo de la aerolinea el cual desea modificar");
                                String cambioCodigA = s.nextLine();
                                r.getVuelo().setAerolinea(new Aerolinea(cambioCodigA, cambioNombre));
                                break;
                            case 3:
                                System.out.println("Ingrese el origen por el cual desea modificar el vuelo");
                                String cambioOrigen = s.nextLine();
                                r.getVuelo().setOrigen(cambioOrigen);
                                break;
                            case 4:
                                System.out.println("Ingrese el destino por el cual desea modificar el vuelo");
                                String cambioDestino = s.nextLine();
                                r.getVuelo().setDestino(cambioDestino);
                                break;
                            case 5:
                            try{
                                System.out.println("Ingrese la fecha por el cual desea modificar el vuelo (AAAA/MM/DD)");
                                String cambioVueloString = s.nextLine();
                                LocalDate cambioVuelo = LocalDate.parse(cambioVueloString,dtf);
                                r.getVuelo().setFecha(cambioVuelo);
                                break;
                            }
                            catch(DateTimeParseException e){
                                System.out.println("Ingrese el formato de fecha correcto");
                            }
                            case 6:
                                try{
                                System.out.println("Ingrese la hora de salida por el cual desea modificar el vuelo (HH:MM)");
                                String cambioHoraString = s.nextLine();
                                LocalTime cambioHora = LocalTime.parse(cambioHoraString,htf);
                                r.getVuelo().setHora(cambioHora);
                                break;
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Ingrese el formato de hora correcto");
                                }
                            case 7:
                                System.out.println("Ingrese el precio por el cual desea modificar el vuelo");
                                String cambioPrecio = s.nextLine();
                                r.getVuelo().setPrecio(cambioPrecio);
                                break;
                            case 8:
                                break;
                            default:
                                System.out.println("Ingrese una opcion correcta");
                                break;
                        }
    
                    }
                    while(opcion!=8);

                    case 4:
                    do{
                        System.out.println("Ingrese la opcion del atributo que quiera modificar: ");
                        System.out.println("1. Nombre");
                        System.out.println("2. Codigo");
                        System.out.println("3. Salir");
                        opcion = s.nextInt();
                        s.nextLine();
                        switch (opcion) {
                            case 1:
                                System.out.println("Ingrese el nombre por el cual desea modificar la aerolinea");
                                String cambioNombre = s.nextLine();
                                r.getAerolinea().setNombre(cambioNombre);
                                break;
                            case 2:
                                System.out.println("Ingrese el codigo por el cual desea modificar la aerolinea");
                                String cambioCodigo = s.nextLine();
                                r.getAerolinea().setCodigo(cambioCodigo);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Ingrese una opcion correcta");
                                break;
                        }
    
                    }
                    while(opcion!=3);

                    case 5:
                    do{
                        System.out.println("Ingrese la opcion del atributo que quiera modificar: ");
                        System.out.println("1. Nombre");
                        System.out.println("2. Codigo");
                        System.out.println("3. Ubicacion");
                        System.out.println("4. Categoria");
                        System.out.println("5. Precio por noche");
                        System.out.println("6. Salir");
                        opcion = s.nextInt();
                        s.nextLine();
                        switch (opcion) {
                            case 1:
                                System.out.println("Ingrese el nombre por el cual desea modificar el hotel");
                                String cambioNombre = s.nextLine();
                                r.getHotel().setNombre(cambioNombre);
                                break;
                            case 2:
                                System.out.println("Ingrese el codigo por el cual desea modificar el hotel");
                                String cambioCodigo = s.nextLine();
                                r.getHotel().setCodigo(cambioCodigo);
                                break;
                            case 3:
                                System.out.println("Ingrese la ubicacion por el cual desea modificar el hotel");
                                String cambioUbicacion = s.nextLine();
                                r.getHotel().setUbicacion(cambioUbicacion);
                                break;
                            case 4:
                                System.out.println("Ingrese la categoria por el cual desea modificar el hotel");
                                String cambioHotel = s.nextLine();
                                r.getHotel().setCategoria(cambioHotel);
                                break;
                            case 5:
                                System.out.println("Ingrese el precio por noche por el cual desea modificar el hotel");
                                String cambioPrecio = s.nextLine();
                                r.getHotel().setPrecioNoche(cambioPrecio);
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Ingrese una opcion correcta");
                                break;
                        }
    
                    }while(opcion!=6);

                    case 6:
                        break;
       
                    default:
                        break;
                    }
                    EscribirBinario();
                }while(opcion!=6);

                
            }
        }
        System.out.println("No se encontro cliente con ese nombre");
    }
    catch(NullPointerException e){
        System.out.println("Primero haga un registro");
    }
    }

    public void Cancelacion() {
        try {
            System.out.println("Ingrese el codigo de la reserva que desea cancelar");
            String clienteM = s.nextLine();
            boolean encontrar = false;
    
            Iterator<Reserva> iterator = listaReservas.iterator();
            while (iterator.hasNext()) {
                Reserva r = iterator.next();
                if (r.getCodigo().equalsIgnoreCase(clienteM)) {
                    iterator.remove(); 
                    encontrar = true;
                }
            }
    
            if (!encontrar) {
                System.out.println("No se encontró ninguna reserva con ese código.");
            }
        } catch (NullPointerException e) {
            System.out.println("Primero haga un registro.");
        }
    }
    

    public void Informe(){

        try{
        FileWriter fw = new FileWriter("reservas.txt");

        for (Reserva reserva : listaReservas) {

            String linea = reserva.toString();
    
            fw.write(linea);
        }

        fw.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(NullPointerException e){
            System.out.println("Realize un registro primero");
        }
    
    }

        public void EscribirBinario(){
        try{
            FileOutputStream fos = new FileOutputStream("Reservas.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaReservas);
        }
        catch(FileNotFoundException e){
            System.out.println("El archivo no se pudo crear");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void LeerBinario(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        try{
            fis = new FileInputStream("Reservas.dat");
            ois = new ObjectInputStream(fis);

            listaReservas = (ArrayList <Reserva>) ois.readObject();
        }

        catch(FileNotFoundException e){
            e.getStackTrace();
        }
        catch(IOException e){
            e.getStackTrace();
        }

        catch(ClassNotFoundException e){
            e.getStackTrace();
        }

        finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
    
            }
        }
    }

    public String toString(){
        return "Cliente:\n " + getCliente().toString() + "\nPlan turistico:\n " +  getPlanTuristico().toString() + "\nVuelo:\n " + "Codigo: " + getVuelo().getCodigo() +  "\nOrigen: " + getVuelo().getOrigen() + "\nDestino: " + getVuelo().getDestino() + "\nFecha: " + getVuelo().getFecha() + "\nHora: " + getVuelo().getHora() + "\nPrecio: " + getVuelo().getPrecio() + "\nAerolinea:\n " + getAerolinea().toString() + "\nHotel:\n " + getHotel().toString() + "\nFecha de reserva:\n " + getFechaReserva() + "\nCodigo de la reserva:\n " + getCodigo();
    }
 }
        
    

  
