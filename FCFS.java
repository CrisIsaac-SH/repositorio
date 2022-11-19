import java.io.Console;
import java.util.concurrent.*;

public class FCFS extends Policy {

    private ConcurrentLinkedQueue<SimpleProcess> mainQue;

    

    public FCFS() {
        mainQue = new ConcurrentLinkedQueue<SimpleProcess>();

    }

    @Override
    public void add(SimpleProcess p) {

        mainQue.add(p);
    }

    @Override
    public void remove(SimpleProcess p) {

        mainQue.remove(p);
    }

    @Override
    public SimpleProcess next(){
        return mainQue.peek();
    }

    @Override
    public void serveNext() {
        SimpleProcess nextProcess = this.mainQue.remove();
        if (nextProcess.isFree) {
            nextProcess.isFree=false;
            try {
                System.out.println("Inicio proceso Id:" + nextProcess.id + " Tipo " +nextProcess.nombre);
                Thread.sleep(nextProcess.time * 1000);                
                System.out.println("Finalizo proceso Id:" + nextProcess.id + ". Tiempo que tomo---> "+  nextProcess.time);
                nextProcess.time = 0;
                



                // Thread.sleep( RounRobinTime.time*1000) esto seria para round robin
                // nextProcess.time= nextProcess.time- RoundRobinTime.time

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

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