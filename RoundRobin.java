import java.io.Console;
import java.util.concurrent.*;

import javax.swing.plaf.synth.SynthCheckBoxMenuItemUI;

public class RoundRobin extends Policy {

    private ConcurrentLinkedQueue<SimpleProcess> roundQue;
    private int quatum;

    public RoundRobin(int quatum) {
        roundQue = new ConcurrentLinkedQueue<SimpleProcess>();
        this.quatum = quatum;
    }

    @Override
    public synchronized void add(SimpleProcess p) {

        roundQue.add(p);
        if (waitingForProcess > 0) {

            waitingForProcess--;
            notify();
        }
    }

    @Override
    public void remove(SimpleProcess p) {

        roundQue.remove(p);
    }

    @Override
    public synchronized SimpleProcess next() {
        return roundQue.peek();
    }

    @Override
    public SimpleProcess serveNext() {
        if (roundQue.isEmpty()) {
            try {
                synchronized (this) {
                    waitingForProcess++;
                    wait();
                }
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        SimpleProcess nextProcess;
        synchronized (this) {
            if (!this.roundQue.isEmpty()) {
                nextProcess = roundQue.remove();
            } else {
                return null;
            }
        }

        if (nextProcess.isFree) {
            nextProcess.isFree = false;

            try {
                System.out.println("Se inicio el proceso en la política FCFS con el Id:" + nextProcess.id + " Tipo: "
                        + nextProcess.nombre);
                Thread.sleep((int) (nextProcess.time * 1000.0));
                synchronized (this) {
                    System.out.println(
                            "Termino de atenderse el proceso con Id:" + nextProcess.id + " Tipo: "
                                    + nextProcess.nombre);
                    System.out.println("Tiempo que tomo en atenderse el proceso fue de: " + nextProcess.time);
                    nextProcess.time = quatum;
                }

            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
                return null;
            }

        }
        return nextProcess;

    }

    @Override
    public boolean isEmpty() {
        return roundQue.isEmpty();
    }

    @Override
    public synchronized void finishPolicy() {
        notifyAll();
    }

}