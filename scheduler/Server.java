package scheduler;
import scheduler.processing.SimpleProcess;
import scheduler.scheduling.Policy;

public class Server implements Runnable {

    public static int cont = 1;
    public Policy policy;
    public boolean active; // Esto se va a desactivar al presionar Q o enter
    public int id;
    public static int numeroServer = 1;

    public Server(Policy policy) {
        this.policy = policy;
        this.active = true;
        this.id = Server.numeroServer;
        Server.numeroServer++;
    }

    public void run() {
        SimpleProcess actualProcess;

        while (ProcessGenerator.generating || !policy.isEmpty()) {
            
                actualProcess = policy.serveNext();
                if (actualProcess != null) {
                    if (actualProcess.isFinished()) {
                        int total = cont++;
                        // policy.remove(actualProcess);
                        System.out.println("Finalizo el proceso y cola actual con Id:" + actualProcess.id + " del Procesador " + this.id);
                        System.out.println("\nTotal procesos atendidos: " + total + "\n");
                    } else {
                        System.out.println("Aun no se finalizo proceso Id: " + actualProcess.id);
                    }
                }
            
        }

    }

}
