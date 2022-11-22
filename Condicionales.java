public class Condicionales extends SimpleProcess{
    
    
    public Condicionales(int id){
        super(id,SimpleProcess.ConditionalTime);
        this.nombre = "Condicional";
        System.out.println("Se agrego nuevo proceso: Condicional.  Id:"+ id );

    }
    
}