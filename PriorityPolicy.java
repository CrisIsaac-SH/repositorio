//input/output
//aritmetico
//condicionales
//iterativos
import java.util.concurrent.*;

public class PriorityPolicy extends Policy{

    private ConcurrentLinkedQueue<SimpleProcess> priority1;
    private ConcurrentLinkedQueue<SimpleProcess> priority2;
    private ConcurrentLinkedQueue<SimpleProcess> priority3;
    private ConcurrentLinkedQueue<SimpleProcess> priority4;


    public PriorityPolicy(){
        
        priority1 = new ConcurrentLinkedQueue<SimpleProcess>();
        priority2 = new ConcurrentLinkedQueue<SimpleProcess>();
        priority3 = new ConcurrentLinkedQueue<SimpleProcess>();
        priority4 = new ConcurrentLinkedQueue<SimpleProcess>();

    }

    @Override
    public synchronized void add(SimpleProcess p) {
        
        if(p instanceof InputOutput){
            priority1.add(p);
        }
        else if(p instanceof Aritmetico){
            priority2.add(p);
        }
        else if(p instanceof Condicionales){
            priority3.add(p);
        }
        else if(p instanceof Iterativos){
            priority4.add(p);
        }
        else if (waitingForProcess > 0) {

            waitingForProcess--;
            notify();
        }
    }

    @Override
    public void remove(SimpleProcess p){
        if(p instanceof InputOutput){
            priority1.remove(p);
        }

        else if(p instanceof Aritmetico){
            priority2.remove(p);
        }
        else if(p instanceof Condicionales){
            priority3.remove(p);
        }
        else if(p instanceof Iterativos){
            priority4.remove(p);
        }
    }

    @Override
    public synchronized SimpleProcess next() {
        if(priority1.isEmpty()){
            return priority2.peek();
        }
        else if(priority2.isEmpty()){
            return priority3.peek();
        }
        else if(priority3.isEmpty()){
            return priority4.peek();
        }
        else{
            return null;
        }
    }

    @Override
    public SimpleProcess serveNext(){
        if(priority1.isEmpty()){
            try{
                synchronized (this) {
                    waitingForProcess++;
                    wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SimpleProcess nextProcess;
        synchronized (this) {
            if (!this.priority1.isEmpty()){
                nextProcess = priority2.remove();
            }
            else if(!this.priority2.isEmpty()){
                nextProcess = priority3.remove();
            }
            else if(!this.priority3.isEmpty()){
                nextProcess = priority4.remove();
            }
            else if(!this.priority4.isEmpty()){
                nextProcess = null;
            }
            else{
                return null;
            }
        }

        if(nextProcess.isFree){
            nextProcess.isFree = false;
            try {
                System.out.println("Se inicio el proceso con la politica PP con Id: "+ nextProcess.id + " Tipo de prioridad: "+ nextProcess.nombre);
                Thread.sleep((int) (nextProcess.time * 1000.0));
                synchronized (this) {
                    System.out.println(
                            "Termino de atenderse el proceso con Id:" + nextProcess.id + " Tipo: "
                                    + nextProcess.nombre);
                    System.out.println("Tiempo que tomo en atenderse el proceso fue de: " + nextProcess.time);
                    nextProcess.time = 0;
                    
                    nextProcess.time = 0;
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
        if(priority1.remove().isFree){
            return priority1.isEmpty();
        }
        else if(priority2.remove().isFree){
            return priority2.isEmpty();
        }
        else if(priority3.remove().isFree){
            return priority3.isEmpty();
        }
        else if(priority4.remove().isFree){
            return priority4.isEmpty();
        }
        else{
            return false;
        }
    }

    @Override
    public synchronized void finishPolicy() {
        notifyAll();

    }

}