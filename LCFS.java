import java.io.Console;
import java.util.Stack;



public class LCFS extends Policy{

    private Stack<SimpleProcess> mainStack;

    

    public LCFS(){
        mainStack = new Stack<SimpleProcess>();
        System.out.println("Política LCFS(Last Come First Served)\n");
    }

    @Override
    public synchronized void add(SimpleProcess p){        
        mainStack.push(p);
        if(waitingForProcess>0){
           
            waitingForProcess--;
            notify();
        }        
    }

    @Override
    public void remove(SimpleProcess p){
        Stack<SimpleProcess> auxiliar= new Stack<SimpleProcess>();
        
        while(mainStack.peek() != p){ //mientras el elemento  p que deseamos remover no este hasta arriba de la pila
            auxiliar.push(mainStack.pop());
        }
        mainStack.pop(); //Ahora que ya sabemos que el elemento p se encuentra arriba lo removemos

        while(!auxiliar.isEmpty()){
            mainStack.push(auxiliar.pop());
        }

    }

    @Override
    public SimpleProcess next(){

        return mainStack.peek();
    }

    @Override
    public SimpleProcess serveNext(){
        if (mainStack.isEmpty()) {
            try {
                waitingForProcess++;
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SimpleProcess nextProcess;
        synchronized(this){
            if(!this.mainStack.isEmpty()){
                nextProcess= this.mainStack.pop();
            }else{
                return null;
            }

        }
       
        if(nextProcess.isFree){
            nextProcess.isFree=false;
            try{
                System.out.println("Inicio el proceso en la política LCFS con el Id:" + nextProcess.id + " Tipo: " + nextProcess.nombre);
                Thread.sleep((int)(nextProcess.time * 1000.0));                
                System.out.println("Termino de atenderse el proceso con Id:" + nextProcess.id +" Tipo: "+ nextProcess.nombre);
                System.out.println("Tiempo que tomo en atenderse el proceso fue de: " +  nextProcess.time);
                nextProcess.time = 0;
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
        return nextProcess;
        
    }

    @Override
    public boolean isEmpty(){
       return mainStack.isEmpty();
    }

    @Override
    public void finishPolicy() {
        notifyAll();
        
    }
    

}