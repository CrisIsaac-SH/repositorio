import java.io.Console;
import java.util.concurrent.*;

import javax.swing.plaf.synth.SynthCheckBoxMenuItemUI;

public class RoundRobin extends Policy {

    private ConcurrentLinkedQueue<SimpleProcess> roundQue;

    public RoundRobin() {
        roundQue = new ConcurrentLinkedQueue<SimpleProcess>();
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
    public SimpleProcess serveNext(){
        if(roundQue.isEmpty()){
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
        SimpleProcess nexProcess
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