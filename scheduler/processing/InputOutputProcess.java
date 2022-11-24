package scheduler.processing;
public class InputOutputProcess extends SimpleProcess{
    

    public InputOutputProcess(int id){
        super(id, SimpleProcess.IOTime);
        this.nombre= "IO";
        System.out.println("Se agrego nuevo proceso: InputOutput.  Id:"+ id );

    }
    

}