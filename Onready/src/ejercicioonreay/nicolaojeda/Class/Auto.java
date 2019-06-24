
package ejercicioonreay.nicolaojeda.Class;
import ejercicioonreay.nicolaojeda.Interface.GetDescripcion;


public class Auto extends Vehiculo implements GetDescripcion{
    
    public Auto() {

    }
    
    int puertas;
    
    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    @Override
    public String getDescripcion() {
       
        String desc;
        
        desc ="Marca: " + marca.trim() + 
              " // Modelo: " + modelo.trim() +
              " // Puertas: " + String.valueOf(puertas).trim() + 
              " // Precio: $" + String.valueOf(precio).trim() + ";";

        return desc;
    }

    
    
}
