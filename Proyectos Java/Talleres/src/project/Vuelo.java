/*Integrantes Juan Manuel Diaz, Nicol Vargas 
Grupo: 9
Fecha: 10/11/2024
Taller: Proyecto Final Java*/

package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate; 
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalTime;

public class Vuelo implements Serializable{
    ArrayList <Vuelo> listaVuelo = new ArrayList<>();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private transient Scanner s = new Scanner(System.in);
    private static final DateTimeFormatter htf = DateTimeFormatter.ofPattern("HH:mm");
    private String codigo;
    private Aerolinea aerolinea;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private LocalTime hora;
    private String precio;

    public Vuelo(String codigo,Aerolinea aerolinea,String origen,String destino,LocalDate fecha, LocalTime hora,String precio){
        this.codigo = codigo;
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        listaVuelo.add(this);
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public Aerolinea getAerolinea(){
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea){
        this.aerolinea = aerolinea;
    }

    public String getOrigen(){
        return origen;
    }

    public void setOrigen(String origen){
        this.origen = origen;
    }

    public String getDestino(){
        return destino;
    }

    public void setDestino(String destino){
        this.destino = destino;
    }

    public LocalDate getFecha(){
        return fecha;
    }

    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }

    public LocalTime getHora(){
        return hora;
    }

    public void setHora(LocalTime hora){
        this.hora = hora;
    }

    public String getPrecio(){
        return precio;
    }

    public void setPrecio(String precio){
        this.precio = precio;
    }

    public void menu(){
        int opcion = 0;
        LeerBinario();
        do{
            System.out.println("Eliga las opciones de la gestion de vuelos");
            System.out.println("1. Registro");
            System.out.println("2. Busqueda");
            System.out.println("3. Modificacion");
            System.out.println("4. Eliminacion");
            System.out.println("5. Salir");
            opcion = s.nextInt();
            s.nextLine();
            switch (opcion) {
                case 1:
                    Registro();
                    break;
                case 2:
                    Busqueda(listaVuelo);
                    break;
                case 3:
                    Modificacion(listaVuelo);
                    break;
                case 4:
                    Eliminar(listaVuelo);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }
        }while(opcion != 5);
    }

    public void Registro() {
        System.out.println("Ingrese el código del vuelo: ");
        codigo = s.nextLine();
    
        System.out.print("Ingrese el codigo de la aerolinea: ");
        String codigoAerolinea = s.nextLine();
        System.out.print("Ingrese el nombre de la aerolinea ");
        String nombreAerolinea = s.nextLine();
        aerolinea = new Aerolinea(codigoAerolinea, nombreAerolinea); 
    
        System.out.print("Ingrese el origen del vuelo: ");
        origen = s.nextLine();
        
        System.out.print("Ingrese el destino del vuelo: ");
        destino = s.nextLine();
    
        System.out.print("Ingrese la fecha del vuelo (formato AAAA/MM/DD): ");
        String fechaString = s.nextLine();
        try {
            fecha = LocalDate.parse(fechaString, dtf);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }
    
        System.out.print("Ingrese la hora de salida (formato HH:MM): ");
        String horaString = s.nextLine();
        try {
            hora = LocalTime.parse(horaString,htf);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de hora incorrecto.");
            return;
        }
    
        System.out.print("Ingrese el precio del vuelo: ");
        precio = s.nextLine();
        EscribirBinario(); 
    }

        public void Busqueda(ArrayList <Vuelo> listaVuelos){
            boolean encontrado = false;
            try{
            System.out.println("Ingrese el codigo del vuelo que desea buscar");
            String buscar = s.nextLine();
            for(Vuelo p : listaVuelos){
                if(p.getCodigo().contains(buscar)){
                    System.out.println(p.toString());
                    encontrado = true;
                }
            }
            if(!encontrado) 
                System.out.println("No se encontro vuelo con ese codigo");
            }
            catch(NullPointerException e){
                System.out.println("Primero haga un registro");
            }
        }
    
        public void Modificacion(ArrayList <Vuelo> listaVuelos){
            try{
            System.out.println("Ingrese el codigo del vuelo que desea modificar");
            String buscar = s.nextLine();
            int opcion = 0;
            for(Vuelo p : listaVuelos){
                if(p.getCodigo().contains(buscar)){
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
                                p.setCodigo(cambioCodigo);
                                break;
                            case 2:
                                System.out.println("Ingrese el nombre de la aerolinea el cual desea modificar");
                                String cambioNombre = s.nextLine();
                                System.out.println("Ingrese el codigo de la aerolinea el cual desea modificar");
                                String cambioCodigA = s.nextLine();
                                p.setAerolinea(new Aerolinea(cambioCodigA, cambioNombre));
                                break;
                            case 3:
                                System.out.println("Ingrese el origen por el cual desea modificar el vuelo");
                                String cambioOrigen = s.nextLine();
                                p.setOrigen(cambioOrigen);
                                break;
                            case 4:
                                System.out.println("Ingrese el destino por el cual desea modificar el vuelo");
                                String cambioDestino = s.nextLine();
                                p.setDestino(cambioDestino);
                                break;
                            case 5:
                            try{
                                System.out.println("Ingrese la fecha por el cual desea modificar el vuelo (AAAA/MM/DD)");
                                String cambioVueloString = s.nextLine();
                                LocalDate cambioVuelo = LocalDate.parse(cambioVueloString,dtf);
                                p.setFecha(cambioVuelo);
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
                                p.setHora(cambioHora);
                                break;
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Ingrese el formato de hora correcto");
                                }
                            case 7:
                                System.out.println("Ingrese el precio por el cual desea modificar el vuelo");
                                String cambioPrecio = s.nextLine();
                                p.setPrecio(cambioPrecio);
                                break;
                            case 8:
                                break;
                            default:
                                System.out.println("Ingrese una opcion correcta");
                                break;
                        }
    
                    }
                    while(opcion!=8);
                }
                else{
                    System.out.println("No se encontro cliente con ese nombre");
                }
            }
        }
        catch(NullPointerException e){
            System.out.println("Primero haga un registro");
        }
        }
    
    public void Eliminar(ArrayList <Vuelo> listaVuelo){
           try {
            System.out.println("Ingrese el codigo del vuelo que desea eliminar");
            String codigo = s.nextLine(); 
            boolean eliminado = false;
    
            Iterator<Vuelo> iterator = listaVuelo.iterator();
            while (iterator.hasNext()) {
                Vuelo p = iterator.next();
                if (p.getCodigo().equalsIgnoreCase(codigo)) {
                    iterator.remove(); 
                    eliminado = true;
                }
            }
    
            if (!eliminado) {
                System.out.println("Vuelo no encontrado");
            }
        } catch (NullPointerException e) {
            System.out.println("Primero haga un registro");
        }
    
        }
    public void EscribirBinario(){
        try{
            FileOutputStream fos = new FileOutputStream("Vuelo.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaVuelo);
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
            fis = new FileInputStream("Vuelo.dat");
            ois = new ObjectInputStream(fis);

            listaVuelo = (ArrayList <Vuelo>) ois.readObject();
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
        return "Codigo: " + getCodigo() + "\nAerolinea:\n " + getAerolinea().toString() + "\nOrigen: " + getOrigen() + "\nDestino: " + getDestino() + "\nFecha: " + getFecha() + "\nHora: " + getHora() + "\nPrecio: " + getPrecio();
    }
    



    
}
