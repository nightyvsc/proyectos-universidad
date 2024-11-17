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
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Aerolinea implements Serializable{
    ArrayList <Aerolinea> listaAerolinea = new ArrayList<>();
    
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private transient Scanner s = new Scanner(System.in);
    private String codigo;
    private String nombre;

    public Aerolinea(String codigo,String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
        listaAerolinea.add(this);
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

    public void menu(){
        int opcion = 0;
        LeerBinario();
        do{
            System.out.println("Eliga las opciones de la gestion de aerolineas");
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
                    Busqueda(listaAerolinea);
                    break;
                case 3:
                    Modificacion(listaAerolinea);
                    break;
                case 4:
                    Eliminar(listaAerolinea);
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
        System.out.println("Ingrese el codigo de la aerolinea: ");
        codigo = s.nextLine();
        System.out.println("Ingrese el nombre de la aerolinea: ");
        nombre = s.nextLine();
        new Aerolinea(codigo, nombre);
        EscribirBinario();
        
    }

        public void Busqueda(ArrayList <Aerolinea> listaAerolineas){
            boolean encontrado = false;
            try{
            System.out.println("Ingrese el nombre de la aerolinea que desea buscar");
            String buscar = s.nextLine();

            for(Aerolinea p : listaAerolineas){
                if(p.getNombre().contains(buscar)){
                    System.out.println(p.toString());
                    encontrado = true;
                }
            }
            if(!encontrado)
                System.out.println("No se encontro aerolinea con ese codigo");
            }
            catch(NullPointerException e){
                System.out.println("Primero haga un registro");
            }

        }
    
        public void Modificacion(ArrayList <Aerolinea> listaAerolineas){
            try{
            System.out.println("Ingrese el nombre de la aerolinea que desea modificar");
            String buscar = s.nextLine();
            int opcion = 0;
            for(Aerolinea p : listaAerolineas){
                if(p.getNombre().contains(buscar)){
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
                                p.setNombre(cambioNombre);
                                break;
                            case 2:
                                System.out.println("Ingrese el codigo por el cual desea modificar la aerolinea");
                                String cambioCodigo = s.nextLine();
                                p.setCodigo(cambioCodigo);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Ingrese una opcion correcta");
                                break;
                        }
                        EscribirBinario();
                    }
                    while(opcion!=3);
                }
                else{
                    System.out.println("No se encontro aerolinea con ese nombre");
                }
            }
        }
        catch(NullPointerException e){
            System.out.println("Primero haga un registro");
        }
        }
    
        public void Eliminar(ArrayList <Aerolinea> listaAerolinea){
            try {
            System.out.println("Ingrese el nombre de la aerolinea que desea eliminar");
            String nombre = s.nextLine(); 
            boolean eliminado = false;
    
            Iterator<Aerolinea> iterator = listaAerolinea.iterator();
            while (iterator.hasNext()) {
                Aerolinea p = iterator.next();
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    iterator.remove(); 
                    eliminado = true;
                }
            }
    
            if (!eliminado) {
                System.out.println("Aerolinea no encontrado");
            }
        } catch (NullPointerException e) {
            System.out.println("Primero haga un registro");
        }
    
        }
    public void EscribirBinario(){
        try{
            FileOutputStream fos = new FileOutputStream("Aerolinea.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaAerolinea);
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
            fis = new FileInputStream("Aerolinea.dat");
            ois = new ObjectInputStream(fis);

            listaAerolinea = (ArrayList <Aerolinea>) ois.readObject();
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
        return "Nombre: " + getNombre() + "\nCodigo: " + getCodigo();
    }
    


}
