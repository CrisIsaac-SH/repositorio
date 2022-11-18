import java.io.Console;
import java.util.Stack;
import java.util.Queue;

public class LCFS extends Policy{

    private Stack<SimpleProcess> mainStack;

    public LCFS(){
        mainStack = new Stack<SimpleProcess>();
    }

    @Override
    public void add(SimpleProcess p){

        mainStack.push(p);
    }

    @Override    
    public void remove(SimpleProcess p){

        //mainStack.pop();
    }

    @Override
    public SimpleProcess next(){

        return mainStack.peek();
    }

    @Override
    public void serveNext(){
        SimpleProcess nextProcess = this.next();
        if(nextProcess.isFree){
            try{
                Thread.sleep(nextProcess.time * 1000);
                System.out.println("Atendiendo al proceso Id:" + nextProcess.id + " Tipo " +nextProcess.nombre);
                System.out.println("Tiempo que tomo " +  nextProcess.time);
                nextProcess.time = 0;
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Override
    public boolean isEmpty(){
       return mainStack.isEmpty();
    }


    

}