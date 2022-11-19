public class Server implements Runnable {

    int cont = 1;
    private Policy policy;
    private boolean active; // Esto se va a desactivar al presionar  Q o enter
    private int id;
    public static int numeroServer;
    public Server(Policy policy){
        this.policy= policy;
        this.active = true; 
        this.id = Server.numeroServer;
        this.id++;
    }

    public void run(){
        SimpleProcess actualProcess;
        while (policy.isEmpty()==false){
            actualProcess=  policy.next();
            policy.serveNext();
            int total = cont++;
            if(actualProcess.isFinished()){
                actualProcess.isFree=false;
                policy.remove(actualProcess);    
                System.out.println(" finalizo el proceso de servidor "+ this.id);
                System.out.println("\nTotal procesos: " + total + "\n"); 
            }else{
                System.out.println("Aun no se finalizo");
            }
        }

    }
    
}
