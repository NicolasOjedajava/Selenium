
package ejercicioonreay.nicolasojeda.Main;

import ejercicioonreay.nicolasojeda.Class.Auto;
import ejercicioonreay.nicolasojeda.Class.Moto;
import java.util.ArrayList;


public class EjercicioOnreayNicolaOjeda {

    public static void main(String[] args) {
        
        //Variables
        Auto autoMasCaro = new Auto();
        Auto autoMasBar = new Auto();
        Moto motoMasCara = new Moto();
        Moto motoMasBar = new Moto();

        ArrayList <Object> listaDeVehiculos = new ArrayList<>();
        ArrayList <Object> VehiculosConY = new ArrayList<>();
        //Cargo lista de Vehiculos
        listaDeVehiculos = CargarLista();
        
        
        for(Object v:listaDeVehiculos){
            
            //Pregunto si es un auto o una moto, para ver cual
            //es la descripcion que tengo que mostrar.

            if (v instanceof Auto){
                Auto auto = (Auto) v;
                System.out.println(auto.getDescripcion());
                
                if ((auto.getPrecio() > autoMasCaro.getPrecio())){
                    autoMasCaro = auto;
                } 
                
                if (autoMasBar.getPrecio() == 0){
                    autoMasBar = auto;
                }
                
                if (auto.getPrecio() < autoMasBar.getPrecio()){
                    autoMasBar = auto;
                } 
                
                if (auto.getModelo().contains("Y")){
                    VehiculosConY.add(auto);
                }
            }
            
            if (v instanceof Moto){
                Moto moto = (Moto) v;
                System.out.println(moto.getDescripcion());
                
                if (moto.getPrecio() > motoMasCara.getPrecio()){
                    motoMasCara = moto;
                }
                 
                if (motoMasBar.getPrecio() == 0){
                    motoMasBar = moto;
                }
                                
                if (moto.getPrecio() < motoMasBar.getPrecio()){
                    motoMasBar = moto;
                   
                }
                
                if (moto.getModelo().contains("Y")){
                    VehiculosConY.add(moto);
                }
            }

        }
        
        System.out.println("=============================");
        
        //Vehiculo mas Caro
        if (autoMasCaro.getPrecio() > motoMasCara.getPrecio()){
        
            System.out.println("Vehículo más caro: " + autoMasCaro.getMarca() +
                                " " + autoMasCaro.getModelo());
        } else {
            System.out.println("Vehículo más caro: " + motoMasCara.getMarca() +
                                " " + motoMasCara.getModelo());
        }
        
        //Vehiculo mas Barato
        if (autoMasBar.getPrecio() < motoMasBar.getPrecio()){
        
            System.out.println("Vehículo más barato: " + autoMasBar.getMarca() +
                                " " + autoMasBar.getModelo());
        } else {
            System.out.println("Vehículo más barato: " + motoMasBar.getMarca() +
                                " " + motoMasBar.getModelo());
        }
        
        for(Object i: VehiculosConY){
            
            if (i instanceof Auto){
                Auto vehiculo = (Auto) i;
                
                System.out.println("Vehículo que contiene " + 
                "en el modelo la letra ‘Y’:" +
                vehiculo.getMarca().trim() + " " +
                vehiculo.getModelo().trim() + " " +
                vehiculo.getPrecio());
                
            }else{
                Moto vehiculo = (Moto) i;
                
                System.out.println("Vehículo que contiene " + 
                "en el modelo la letra ‘Y’: " +
                vehiculo.getMarca().trim() + " " +
                vehiculo.getModelo().trim() + " " +
                vehiculo.getPrecio());
            }
        }
        
        System.out.println("=============================");

    }
    
    //Carga la lista de Vehiculos
    public static ArrayList CargarLista(){
        
        ArrayList listaDeVehiculos = new ArrayList();
        
        Auto auto1 = new Auto();
        auto1.setMarca("Peugeot");
        auto1.setModelo("206");
        auto1.setPuertas(4);
        auto1.setPrecio(200000.00);
        listaDeVehiculos.add(auto1);

        
        Moto moto1 = new Moto();
        moto1.setMarca("Honda");
        moto1.setModelo("Titan");
        moto1.setCilindrada(125);
        moto1.setPrecio(60000.00);
        listaDeVehiculos.add(moto1);
        
        Auto auto2 = new Auto();
        auto2.setMarca("Peugeot");
        auto2.setModelo("208");
        auto2.setPuertas(5);
        auto2.setPrecio(250000.00);
        listaDeVehiculos.add(auto2);
        
        Moto moto2 = new Moto();
        moto2.setMarca("Yamaha");
        moto2.setModelo("YBR");
        moto2.setCilindrada(160);
        moto2.setPrecio(80500.50);      
        listaDeVehiculos.add(moto2);
        
        return listaDeVehiculos;
    }
    
}
