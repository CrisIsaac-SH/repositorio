package scheduler.scheduling;
import java.io.Console;
import java.util.concurrent.*;

import javax.swing.plaf.synth.SynthCheckBoxMenuItemUI;

import scheduler.processing.SimpleProcess;

public class RoundRobin extends Policy {

    public ConcurrentLinkedQueue<SimpleProcess> roundQue;
    public double quatum;

    public RoundRobin(Double quatum) {
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
                System.out.println("Se inicio el proceso en la política RR con el Id:" + nextProcess.id + " Tipo: "
                        + nextProcess.nombre);
                if (nextProcess.time > this.quatum)
                    Thread.sleep((int) (this.quatum * 1000.0));
                else
                    Thread.sleep((int) (nextProcess.time * 1000));
                synchronized (this) {
                    nextProcess.time = nextProcess.time - this.quatum;
                    if (nextProcess.isFinished()) {

                        System.out.println("Termino de atenderse el proceso con Id:" + nextProcess.id + " Tipo: "
                                + nextProcess.nombre +
                                "\nTiempo que tomo en atenderse el proceso fue de: " + nextProcess.original);

                    } else {
                        System.out.println("Se atendio parcialmente el proceso con Id:" + nextProcess.id
                                + "\nTiempo restante: " + nextProcess.time);
                        this.roundQue.add(nextProcess);
                        nextProcess.isFree = true;
                    }
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