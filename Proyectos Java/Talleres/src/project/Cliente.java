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
import java.time.LocalDate; 
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Cliente implements Serializable{
    static ArrayList <Cliente> listaClientes = new ArrayList<>();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private transient Scanner s = new Scanner(System.in);
    private String codigo;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String infoContacto;

    public Cliente(String codigo,String nombre,LocalDate fechaNacimiento,String infoContacto){
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.infoContacto = infoContacto;
        listaClientes.add(this);
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

    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getInfoContacto(){
        return infoContacto;
    }

    public void setInfoContacto(String infoContacto){
        this.infoContacto = infoContacto;
    }

    public void menu(){
        int opcion = 0;
        LeerBinario();
        do{
            System.out.println("Eliga las opciones de la gestion de clientes");
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
                    Busqueda(listaClientes);
                    break;
                case 3:
                    Modificacion(listaClientes);
                    break;
                case 4:
                    Eliminar(listaClientes);
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
    System.out.println("Ingrese el codigo del cliente: ");
    codigo = s.nextLine();
    System.out.println("Ingrese el nombre del cliente: ");
    nombre = s.nextLine();
    System.out.println("Ingrese la fecha de nacimiento del cliente(formato AAAA/MM/DD): ");
    String fechaNacimientoString = s.nextLine();
    fechaNacimiento = LocalDate.parse(fechaNacimientoString,dtf);
    System.out.println("Ingrese el la informacion de contacto del cliente: ");
    infoContacto = s.nextLine(); 
    EscribirBinario();
    }
    catch(DateTimeParseException e){
        System.out.println("Uso el formato equivocado de fecha (use AAAA/MMM/DD)");
    }
    
    }

    public void Busqueda(ArrayList <Cliente> listaClientes){
        boolean encontrado = false;
        try{
        System.out.println("Ingrese el nombre del cliente que desea buscar");
        String buscar = s.nextLine();
        for(Cliente p : listaClientes){
            if(p.getNombre().contains(buscar)){
                System.out.println(p.toString());
                encontrado = true;
            }
        }
        if(!encontrado)
            System.out.println("No se encontro cliente con ese nombre");
        }
        catch(NullPointerException e){
            System.out.println("Primero haga un registro");
        }
    }

    public void Modificacion(ArrayList <Cliente> listaClientes){
        try{
        System.out.println("Ingrese el nombre del cliente que desea modificar");
        String buscar = s.nextLine();
        int opcion = 0;
        for(Cliente p : listaClientes){
            if(p.getNombre().contains(buscar)){
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
                            System.out.println("Ingrese el nombre por el cual desea modificar al cliente");
                            String cambioNombre = s.nextLine();
                            p.setNombre(cambioNombre);
                            
                            break;
                        case 2:
                            System.out.println("Ingrese el codigo por el cual desea modificar al cliente");
                            String cambioCodigo = s.nextLine();
                            p.setCodigo(cambioCodigo);
                            break;
                        case 3:
                            try{
                            System.out.println("Ingrese la fecha de nacimiento por el cual desea modificar al cliente (AAAA/MM/DD)");
                            String cambioFecha = s.nextLine();
                            p.setFechaNacimiento(LocalDate.parse(cambioFecha,dtf));
                            break;
                            }
                            catch(DateTimeParseException e){
                                System.out.println("Ingrese el formato de fecha correcto");
                            }
                        case 4:
                            System.out.println("Ingrese la informacion de contacto por el cual desea modificar al cliente");
                            String cambioInfo = s.nextLine();
                            p.setInfoContacto(cambioInfo);
                            break;
                        case 5:
                            break;
                        
                        default:
                            System.out.println("Ingrese una opcion correcta");
                            break;
                    }
                    EscribirBinario();

                }
                while(opcion!=5);
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

    public void Eliminar(ArrayList<Cliente> listaClientes) {
        try {
            System.out.println("Ingrese el nombre del cliente que desea eliminar");
            String nombre = s.nextLine(); 
            boolean eliminado = false;
    
            Iterator<Cliente> iterator = listaClientes.iterator();
            while (iterator.hasNext()) {
                Cliente p = iterator.next();
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    iterator.remove(); 
                    eliminado = true;
                }
            }
    
            if (!eliminado) {
                System.out.println("Cliente no encontrado");
            }
        } catch (NullPointerException e) {
            System.out.println("Primero haga un registro");
        }
    }

    public void EscribirBinario(){
        try{
            FileOutputStream fos = new FileOutputStream("Cliente.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaClientes);
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
            fis = new FileInputStream("Cliente.dat");
            ois = new ObjectInputStream(fis);

            listaClientes = (ArrayList <Cliente>) ois.readObject();
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
        return "Nombre: " + getNombre() + "\nCodigo: " + getCodigo() + "\nFecha de nacimiento: " + getFechaNacimiento() + "\nInformacion de contacto: " + getInfoContacto();
    }



    


}
