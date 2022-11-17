public class Server {


    private Policy policy;
    private boolean active; // Esto se va a desactivar al presionar  Q o enter
    public Server(Policy policy){
        this.policy= policy;
        this.active = true; 
    }

    public void serve(){
        SimpleProcess actualProcess;
        while (policy.isEmpty()==false){
            actualProcess=  policy.next();
            policy.serveNext();
            if(actualProcess.isFinished()){
                policy.remove(actualProcess);    
                System.out.println(" finalizo el proceso");
            }else{
                System.out.println("Aun no se finalizo");
            }
        }

    }
    
}
