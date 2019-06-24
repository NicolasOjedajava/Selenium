
package ejercicioonreay.nicolaojeda.Class;
import ejercicioonreay.nicolaojeda.Interface.GetDescripcion;

public class Moto extends Vehiculo implements GetDescripcion{

    public Moto() {

    }
    
    int cilindrada;

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String getDescripcion() {
        
        String desc;
        
        desc ="Marca: " + this.marca.trim() + 
              " // Modelo: " + this.modelo.trim() +
              " // Cilindrada: " + String.valueOf(this.cilindrada).trim()+ "c" + 
              " // Precio: $" + String.valueOf(this.precio).trim() + ";";

        return desc;
    }
    
}
