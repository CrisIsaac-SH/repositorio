package scheduler.processing;
public class LoopProcess extends SimpleProcess{
 

    public LoopProcess(int id){
        super(id, SimpleProcess.IterativoTime);
        this.nombre= "Iterativo";
        System.out.println("Se agrego nuevo proceso: Iterativo.  Id:"+ id );
    }

    


}