import java.util.*;


public class ProcessGenerator implements Runnable{

    private static int ids;
    private Policy policy;
    private static int contad = 0;
    public static boolean generating=true;

    public ProcessGenerator(Policy policy){
        this.policy = policy;
        

    }

    public void run(){
        int contador=0;
        while(generating){
            contad++;
            SimpleProcess newRandomProcess=null;

            int numero = (int)(Math.random()*(4-1+1)+1);
            switch(numero){
                case 1:
                    newRandomProcess = new Aritmetico(contad);
                break;
    
                case 2:
                    newRandomProcess = new InputOutput(contad);
                break;
    
                case 3:
                    newRandomProcess = new Condicionales(contad); 
                break;
    
                case 4:
                    newRandomProcess = new Iterativos(contad);
                break;
            }
            this.policy.add(newRandomProcess);
            
            try{
                Thread.sleep(1000);
                
            } 
            catch(InterruptedException e){
     // this part is executed when an exception (in this example InterruptedException) occurs 
                System.out.println("No se pudo crear los procesos");
            }
            contador++;
        }
        ProcessGenerator.generating=false;

    }
    
}