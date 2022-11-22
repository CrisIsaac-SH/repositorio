public class InputOutput extends SimpleProcess{
    

    public InputOutput(int id){
        super(id, SimpleProcess.IOTime);
        this.nombre= "IO";
        System.out.println("Se agrego nuevo proceso: InputOutput.  Id:"+ id );

    }
    

}