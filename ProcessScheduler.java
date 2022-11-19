import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;


public class ProcessScheduler{
    public static void main(String[] args) throws Exception{

        SimpleProcess.AritmethicTime = 1;
        SimpleProcess.IOTime = 2;
        SimpleProcess.ConditionalTime = 3;
        SimpleProcess.IterativoTime = 4;

        SimpleProcess aritmetico = new Aritmetico(1);
        SimpleProcess inputOutput = new InputOutput(2);
        SimpleProcess condiciones = new Condicionales(3);
        SimpleProcess iterativos = new Iterativos(4);
        ///////////////
        SimpleProcess aritmetico1 = new Aritmetico(5);
        SimpleProcess inputOutput2 = new InputOutput(6);
        SimpleProcess condiciones3 = new Condicionales(7);
        SimpleProcess iterativos4 = new Iterativos(8);

        ///////////////
        

        Policy politica1= new FCFS();
        //Server servidor = new Server(politica1);
        ProcessGenerator generador= new ProcessGenerator(politica1);
        Server servidor1 = new Server(politica1);
        // politica1.add(aritmetico);
        // politica1.add(inputOutput);
        // politica1.add(condiciones);
        // politica1.add(iterativos);

        

        Server servidor2 = new Server(politica1);
        // politica1.add(aritmetico1);
        // politica1.add(inputOutput2);
        // politica1.add(condiciones3);
        // politica1.add(iterativos4);
        
        Thread t = new Thread(servidor1);
        Thread t2 = new Thread(servidor2);
        Thread t3 = new Thread(generador);
        t3.start();
        Thread.sleep(100);
        t.start();             
        t2.start();
         //Thread.sleep(1);

        //servidor1.run();
        //servidor2.run();

       
        
    }
}