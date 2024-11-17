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

public class Hotel implements Serializable{
    ArrayList <Hotel> listaHotel = new ArrayList<>();
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private transient Scanner s = new Scanner(System.in);
    private String codigo;
    private String nombre;
    private String ubicacion;
    private String categoria;
    private String precioNoche;

    public Hotel(String codigo,String nombre,String ubicacion,String categoria,String precioNoche){
        this.codigo = codigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.precioNoche = precioNoche;
        listaHotel.add(this);
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

    public String getUbicacion(){
        return ubicacion;
    }

    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public String getPrecioNoche(){
        return precioNoche;
    }

    public void setPrecioNoche(String precioNoche){
        this.precioNoche = precioNoche;
    }

    public void menu(){
        int opcion = 0;
        LeerBinario();
        do{
            System.out.println("Eliga las opciones de la gestion de hoteles");
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
                    Busqueda(listaHotel);
                    break;
                case 3:
                    Modificacion(listaHotel);
                    break;
                case 4:
                    Eliminar(listaHotel);
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
        System.out.println("Ingrese el codigo del hotel: ");
        codigo = s.nextLine();
        System.out.println("Ingrese el nombre del hotel: ");
        nombre = s.nextLine();
        System.out.println("Ingrese la ubicacion del hotel");
        ubicacion = s.nextLine();
        System.out.println("Ingrese la categoria del hotel");
        categoria = s.nextLine();
        System.out.println("Ingrese el precio por noche del hotel");
        precioNoche = s.nextLine();
        EscribirBinario();
    }

        public void Busqueda(ArrayList <Hotel> listaHotel){
            boolean encontrado = false;
            try{
            System.out.println("Ingrese el nombre del hotel que desea buscar");
            String buscar = s.nextLine();
            for(Hotel p : listaHotel){
                if(p.getNombre().contains(buscar)){
                    System.out.println(p.toString());
                    encontrado = true;
                }
            }
            if(!encontrado)
                System.out.println("No se encontro hotel con ese nombre");
            }
            catch(NullPointerException e){
                System.out.println("Primero haga un registro");
            }
        }
    
        public void Modificacion(ArrayList <Hotel> listaHotel){
            try{
            System.out.println("Ingrese el nombre del hotel que desea modificar");
            String buscar = s.nextLine();
            int opcion = 0;
            for(Hotel p : listaHotel){
                if(p.getNombre().contains(buscar)){
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
                                p.setNombre(cambioNombre);
                                break;
                            case 2:
                                System.out.println("Ingrese el codigo por el cual desea modificar el hotel");
                                String cambioCodigo = s.nextLine();
                                p.setCodigo(cambioCodigo);
                                break;
                            case 3:
                                System.out.println("Ingrese la ubicacion por el cual desea modificar el hotel");
                                String cambioUbicacion = s.nextLine();
                                p.setUbicacion(cambioUbicacion);
                                break;
                            case 4:
                                System.out.println("Ingrese la categoria por el cual desea modificar el hotel");
                                String cambioHotel = s.nextLine();
                                p.setCategoria(cambioHotel);
                                break;
                            case 5:
                                System.out.println("Ingrese el precio por noche por el cual desea modificar el hotel");
                                String cambioPrecio = s.nextLine();
                                p.setPrecioNoche(cambioPrecio);
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Ingrese una opcion correcta");
                                break;
                        }   
                        EscribirBinario();
                    }
                    while(opcion!=6);
                }
                else{
                    System.out.println("No se encontro hotel con ese nombre");
                }
            }
        }
        catch(NullPointerException e){
            System.out.println("Primero haga un registro");
        }
        }
    
        public void Eliminar(ArrayList <Hotel> listaHotel){
            try {
            System.out.println("Ingrese el nombre del hotel que desea eliminar");
            String nombre = s.nextLine(); 
            boolean eliminado = false;
    
            Iterator<Hotel> iterator = listaHotel.iterator();
            while (iterator.hasNext()) {
                Hotel p = iterator.next();
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    iterator.remove(); 
                    eliminado = true;
                }
            }
    
            if (!eliminado) {
                System.out.println("Hotel no encontrado");
            }
        } catch (NullPointerException e) {
            System.out.println("Primero haga un registro");
        }
    
        }
    public void EscribirBinario(){
        try{
            FileOutputStream fos = new FileOutputStream("Hotel.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaHotel);
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
            fis = new FileInputStream("Hotel.dat");
            ois = new ObjectInputStream(fis);

            listaHotel = (ArrayList <Hotel>) ois.readObject();
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
        return "Nombre: " + getNombre() + "\nCodigo: " + getCodigo() + "\nCategoria: " + getCategoria() + "\nUbicacion: " + getUbicacion() + "\nPrecio noche:" + getPrecioNoche();
    }

}
