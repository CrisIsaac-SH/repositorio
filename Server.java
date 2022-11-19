public class Server implements Runnable {

    private static int cont = 1;
    private Policy policy;
    private boolean active; // Esto se va a desactivar al presionar  Q o enter
    private int id;
    public static int numeroServer;
    public Server(Policy policy){
        this.policy= policy;
        this.active = true; 
        this.id = Server.numeroServer;
        Server.numeroServer++;
    }

    public void run(){
        SimpleProcess actualProcess;
        while (policy.isEmpty()==false){
            actualProcess=  policy.next();
            policy.serveNext();
            int total = cont++;
            if(actualProcess.isFinished()){
                //policy.remove(actualProcess);    
                System.out.println("Finalizo el proceso Id:" + actualProcess.id + " de servidor "+ this.id);
                System.out.println("\nTotal procesos: " + total + "\n"); 
            }else{
                System.out.println("Aun no se finalizo");
            }
        }

    }
    
}
