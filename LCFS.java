import java.io.Console;
import java.util.Stack;


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
    public void serveNext(){
        SimpleProcess nextProcess = this.next();
        if(nextProcess.isFree){
            nextProcess.isFree=false;
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