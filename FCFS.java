import java.io.Console;
import java.util.concurrent.*;

import javax.swing.plaf.synth.SynthCheckBoxMenuItemUI;

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

    @Override
    public SimpleProcess serveNext() {
        if (mainQue.isEmpty()) {
            try {
                synchronized (this) {
                    waitingForProcess++;
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SimpleProcess nextProcess;// = this.mainQue.remove();
        synchronized (this) {
            if (!this.mainQue.isEmpty())
                nextProcess = mainQue.remove();
            else
                return null;
        }

        if (nextProcess.isFree) {
            nextProcess.isFree = false;
            try {

                System.out.println("Se inicio el proceso en la política FCFS con el Id:" + nextProcess.id + " Tipo: "
                        + nextProcess.nombre);
                Thread.sleep((int) (nextProcess.time * 1000.0));
                System.out.println(
                        "Termino de atenderse el proceso con Id:" + nextProcess.id + " Tipo: " + nextProcess.nombre);
                System.out.println("Tiempo que tomo en atenderse el proceso fue de: " + nextProcess.time);
                nextProcess.time = 0;

                return nextProcess;

            } catch (InterruptedException e) {

                e.printStackTrace();
                return null;
            }
        }

        return nextProcess;

        // return null;
    }

    @Override
    public boolean isEmpty() {
        return mainQue.isEmpty();
    }

    @Override
    public synchronized void finishPolicy() {
        notifyAll();

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