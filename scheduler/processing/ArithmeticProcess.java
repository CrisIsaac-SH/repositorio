package scheduler.processing;
public class ArithmeticProcess extends SimpleProcess{
    
    
    public ArithmeticProcess(int id){
        
        super(id, SimpleProcess.AritmethicTime);
        System.out.println("Se agrego nuevo proceso: Aritmetico.  Id:"+ id + ". Tiempo necesario " + this.time);
        this.nombre= "Aritmetico";
       
    
    }

    

}