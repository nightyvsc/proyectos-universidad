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

public class PlanTuristico implements Serializable{
    static ArrayList <PlanTuristico> listaPlanTuristico = new ArrayList<>();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private transient Scanner s = new Scanner(System.in);
    private String codigo;
    private String nombre;
    private String descripcion;
    private String destino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String precio;

    public PlanTuristico(String codigo,String nombre,String descripcion,String destino,LocalDate fechaInicio,LocalDate fechaFin,String precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDestino(){
        return destino;
    }

    public void setDestino(String destino){
        this.destino = destino;
    }

    public LocalDate getFechaInicio(){
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio){
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin(){
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin){
        this.fechaFin = fechaFin;
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
            System.out.println("Eliga las opciones de la gestion de Planes turisticos");
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
                    Busqueda(listaPlanTuristico);
                    break;
                case 3:
                    Modificacion(listaPlanTuristico);
                    break;
                case 4:
                    Eliminar(listaPlanTuristico);
                    break;
                case 5:
                    break;
            
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }
        }while(opcion != 5);
    }

    public void Registro(){    
        try{
        System.out.println("Ingrese el codigo del plan turistico: ");
        codigo = s.nextLine();
        System.out.println("Ingrese el nombre del plan turistico: ");
        nombre = s.nextLine();
        System.out.println("Ingrese la descripcion del plan turistico");
        descripcion = s.nextLine();
        System.out.println("Ingrese el destino del plan turistico");
        destino = s.nextLine();
        System.out.println("Ingrese la fecha de inicio");
        String fechaInicioString = s.nextLine();
        fechaInicio = LocalDate.parse(fechaInicioString,dtf);
        System.out.println("Ingrese la fecha de fin");
        String fechaFinString = s.nextLine();
        fechaFin = LocalDate.parse(fechaFinString,dtf);
        System.out.println("Ingrese el precio del plan turistico");
        precio = s.nextLine();
        EscribirBinario();
        PlanTuristico nuevPlanTuristico = new PlanTuristico(codigo, nombre , descripcion, destino, fechaInicio, fechaFin, precio);
        listaPlanTuristico.add(nuevPlanTuristico);
        }
        catch(DateTimeParseException e){
            System.out.println("Uso el formato equivocado de fecha (use AAAA/MMM/DD)");
        }
        }


        public void Busqueda(ArrayList <PlanTuristico> listaPlanTuristico){
            boolean encontrado = false;
            try{
            System.out.println("Ingrese el nombre del plan turistico que desea buscar");
            String buscar = s.nextLine();
            for(PlanTuristico p : listaPlanTuristico){
                if(p.getNombre().contains(buscar)){
                    System.out.println(p.toString());
                    encontrado = true;
                }
            }
            if(!encontrado)
                System.out.println("No se encontro plan turistico con ese nombre");
            }
            catch(NullPointerException e){
                System.out.println("Primero haga un registro");
            }
        }
    
        public void Modificacion(ArrayList <PlanTuristico> listaPlanTuristico){
            try{
            System.out.println("Ingrese el nombre del plan turistico que desea modificar");
            String buscar = s.nextLine();
            int opcion = 0;
            for(PlanTuristico p : listaPlanTuristico){
                if(p.getNombre().contains(buscar)){
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
                                p.setNombre(cambioNombre);
                                break;
                            case 2:
                                System.out.println("Ingrese el codigo por el cual desea modificar el plan turistico");
                                String cambioCodigo = s.nextLine();
                                p.setCodigo(cambioCodigo);
                                break;
                            case 3:
                                System.out.println("Ingrese la descripcion por la cual desea modificar el plan turistico");
                                String cambioDescripcion = s.nextLine();
                                p.setDescripcion(cambioDescripcion);
                                break;
                            case 4:
                                System.out.println("Ingrese el destino por el cual desea modificar el plan turistico");
                                String cambioDestino = s.nextLine();
                                p.setDestino(cambioDestino);
                                break;
                            case 5:
                                try{
                                System.out.println("Ingrese la fecha inicio por la cual desea modificar el plan turistico");
                                String cambioFechaInicio = s.nextLine();
                                p.setFechaInicio(LocalDate.parse(cambioFechaInicio,dtf));
                                break;
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Ingrese el formato de fecha correcto");
                                }
                            case 6:
                                try{
                                System.out.println("Ingrese la fecha fin por la cual desea modificar el plan turistico");
                                String cambioFechaFin = s.nextLine();
                                p.setFechaFin(LocalDate.parse(cambioFechaFin,dtf));
                                break;
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Ingrese el formato de fecha correcto");
                                }
                            case 7:
                                System.out.println("Ingrese el precio por el cual desea modificar el plan turistico");
                                String cambioPrecio = s.nextLine();
                                p.setPrecio(cambioPrecio);
                                break;
                            case 8:
                                break;
                            
                            default:
                                System.out.println("Ingrese una opcion correcta");
                                break;
                        }
                        EscribirBinario();
                    }
                    while(opcion!=8);
                }
                else{
                    System.out.println("No se encontro plan turistico con ese nombre");
                }
            }
        }
        catch(NullPointerException e){
            System.out.println("Primero haga un registro");
        }
        }
    
        public void Eliminar(ArrayList <PlanTuristico> listaPlanTuristico){
            try {
            System.out.println("Ingrese el nombre del plan turistico que desea eliminar");
            String nombre = s.nextLine(); 
            boolean eliminado = false;
    
            Iterator<PlanTuristico> iterator = listaPlanTuristico.iterator();
            while (iterator.hasNext()) {
                PlanTuristico p = iterator.next();
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    iterator.remove(); 
                    eliminado = true;
                }
            }
    
            if (!eliminado) {
                System.out.println("Plan no encontrado");
            }
        } catch (NullPointerException e) {
            System.out.println("Primero haga un registro");
        }
    
        }
    
    public void EscribirBinario(){
        try{
            FileOutputStream fos = new FileOutputStream("PlanTuristico.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaPlanTuristico);
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
            fis = new FileInputStream("PlanTuristico.dat");
            ois = new ObjectInputStream(fis);

            listaPlanTuristico = (ArrayList <PlanTuristico>) ois.readObject();
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
        return "Nombre: " + getNombre() + "\nCodigo: " + getCodigo() + "\nDescripcion: " + getDescripcion() + "\nDestino: " + getDestino() + "\nFecha Inicio: " + getFechaInicio() + "\nFecha Fin: " + getFechaFin() + "\nPrecio: " + getPrecio();
    }


}
