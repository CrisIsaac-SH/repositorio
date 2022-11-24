package scheduler.scheduling;

//input/output
//aritmetico
//condicionales
//iterativos
import java.util.concurrent.*;

import scheduler.processing.ArithmeticProcess;
import scheduler.processing.ConditionalProcess;
import scheduler.processing.InputOutputProcess;
import scheduler.processing.LoopProcess;
import scheduler.processing.SimpleProcess;

public class PriorityPolicy extends Policy {

    public ConcurrentLinkedQueue<SimpleProcess> priority1;
    public ConcurrentLinkedQueue<SimpleProcess> priority2;
    public ConcurrentLinkedQueue<SimpleProcess> priority3;
    public ConcurrentLinkedQueue<SimpleProcess> priority4;

    public PriorityPolicy() {

        priority1 = new ConcurrentLinkedQueue<SimpleProcess>();
        priority2 = new ConcurrentLinkedQueue<SimpleProcess>();
        priority3 = new ConcurrentLinkedQueue<SimpleProcess>();
        priority4 = new ConcurrentLinkedQueue<SimpleProcess>();

    }

    @Override
    public synchronized void add(SimpleProcess p) {

        if (p instanceof InputOutputProcess) {
            priority1.add(p);
        } else if (p instanceof ArithmeticProcess) {
            priority2.add(p);
        } else if (p instanceof ConditionalProcess) {
            priority3.add(p);
        } else if (p instanceof LoopProcess) {
            priority4.add(p);
        } 
        
        if (waitingForProcess > 0) {
            waitingForProcess--;
            notify();
        }
    }

    @Override
    public void remove(SimpleProcess p) {
        if (p instanceof InputOutputProcess) {
            priority1.remove(p);
        } else if (p instanceof ArithmeticProcess) {
            priority2.remove(p);
        } else if (p instanceof ConditionalProcess) {
            priority3.remove(p);
        } else if (p instanceof LoopProcess) {
            priority4.remove(p);
        }
    }

    @Override
    public synchronized SimpleProcess next() {
        if (!this.priority1.isEmpty()) {
            return priority1.peek();
        } else if (!this.priority2.isEmpty()) {
            return priority2.peek();
        } else if (!this.priority3.isEmpty()) {
            return priority3.peek();
        } else if (!this.priority4.isEmpty()) {
            return priority4.peek();
        } else {
            return null;
        }
    }

    @Override
    public SimpleProcess serveNext() {

        if (priority1.isEmpty()) {
            if (priority2.isEmpty()){
                if (priority3.isEmpty()){
                    if (priority4.isEmpty()){
                        try {
                            synchronized (this) {
                                waitingForProcess++;
                                wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }   
        }
        SimpleProcess nextProcess;
        synchronized (this) {
            if (!this.priority1.isEmpty()) {
                nextProcess = priority1.remove();
            } else if (!this.priority2.isEmpty()) {
                nextProcess = priority2.remove();
            } else if (!this.priority3.isEmpty()) {
                nextProcess = priority3.remove();
            } else if (!this.priority4.isEmpty()) {
                nextProcess = priority4.remove();
            } else {
                return null;
            }
        }

        if (nextProcess.isFree) {
            nextProcess.isFree = false;
            try {
                System.out.println("Se inicio el proceso con la politica PP con Id: " + nextProcess.id
                        + " Tipo de prioridad: " + nextProcess.nombre);
                Thread.sleep((int) (nextProcess.time * 1000.0));
                synchronized (this) {
                    System.out.println(
                            "Termino de atenderse el proceso con Id:" + nextProcess.id + " Tipo: "
                                    + nextProcess.nombre);
                    System.out.println("Tiempo que tomo en atenderse el proceso fue de: " + nextProcess.time);
                    nextProcess.time = 0;
                }

            } catch (InterruptedException e) {                
                e.printStackTrace();
                return null;
            }
        }
        return nextProcess;
    }

    @Override
    public boolean isEmpty() {
        if(priority1.isEmpty()){
            if(priority2.isEmpty()){
                if(priority3.isEmpty()){
                    if(priority4.isEmpty()){
                        return true;
                    }
                    return priority4.isEmpty();
                }
                return priority3.isEmpty();
            }
            return priority2.isEmpty();
        }
        return priority1.isEmpty();
    }

    @Override
    public synchronized void finishPolicy() {
        notifyAll();

    }

}