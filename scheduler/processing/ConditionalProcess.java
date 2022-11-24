package scheduler.processing;
public class ConditionalProcess extends SimpleProcess{
    
    
    public ConditionalProcess(int id){
        super(id,SimpleProcess.ConditionalTime);
        this.nombre = "Condicional";
        System.out.println("Se agrego nuevo proceso: Condicional.  Id:"+ id );

    }
    
}