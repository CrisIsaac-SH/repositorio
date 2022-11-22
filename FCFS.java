import java.io.Console;
import java.util.EmptyStackException;
import java.util.concurrent.*;

public class FCFS extends Policy {

    private ConcurrentLinkedQueue<SimpleProcess> mainQue;

    public FCFS() {

        mainQue = new ConcurrentLinkedQueue<SimpleProcess>();

    }

    @Override
    public synchronized void add(SimpleProcess p) {

        mainQue.add(p);
        if (waitingForProcess > 0) {

            waitingForProcess--;
            notify();
        }
    }

    @Override
    public void remove(SimpleProcess p) {

        mainQue.remove(p);
    }

    @Override
    public synchronized SimpleProcess next() {
        return mainQue.peek();
    }

    //synchronized static void manejo(SimpleProcess p){
      //  SimpleProcess manejo = this.mainQue.serveNext();
    //}

    @Override
    public SimpleProcess serveNext() {
        SimpleProcess nextProcess = this.mainQue.remove();
        
        if (nextProcess.isFree) {
            nextProcess.isFree=false;
            try {
                
                System.out.println("Ingreso el proceso a la política FCFS con el Id:" + nextProcess.id + " Tipo: " + nextProcess.nombre);
                Thread.sleep((int)(nextProcess.time * 1000.0));                
                System.out.println("Termino de atenderse el proceso con Id:" + nextProcess.id +" Tipo: "+ nextProcess.nombre);
                System.out.println("Tiempo que tomo en atenderse el proceso fue de: " +  nextProcess.time);
                nextProcess.time = 0;

                return nextProcess;
                



                // Thread.sleep( RounRobinTime.time*1000) esto seria para round robin
                // nextProcess.time= nextProcess.time- RoundRobinTime.time

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new NoSuchElementException();
                return null;
            }
        }

        return nextProcess;

        //return null;
    }

    @Override
    public boolean isEmpty() {
       return  mainQue.isEmpty();
    }

    // no creo que sea necesario sacar de la cola creo
    /**
     * public void sacarCola(int size){
     * dequeue(this.contador);
     * }
     */

    /**
     * public void getTotalProcesos(){
     * 
     * }
     */

}