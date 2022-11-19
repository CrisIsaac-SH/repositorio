public class Server implements Runnable {

    private static int cont = 1;
    private Policy policy;
    private boolean active; // Esto se va a desactivar al presionar Q o enter
    private int id;
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
            if (!policy.isEmpty()) {
                actualProcess = policy.serveNext();
                if (actualProcess.isFinished()) {
                    int total = cont++;
                    // policy.remove(actualProcess);
                    System.out.println("Finalizo el proceso Id:" + actualProcess.id + " de servidor " + this.id);
                    System.out.println("\nTotal procesos: " + total + "\n");
                } else {
                    System.out.println("Aun no se finalizo");
                }
            }
        }

    }

}
