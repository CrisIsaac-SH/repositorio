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


        //Policy politica1= new FCFS();
        Policy politica2 = new LCFS();
//
        SimpleProcess aritmetico = new Aritmetico(1);
        SimpleProcess inputOutput = new InputOutput(2);
        SimpleProcess condiciones = new Condicionales(3);
        SimpleProcess iterativos = new Iterativos(4);
//
        //Server servidor = new Server(politica1);
        Server servidorr = new Server(politica2);

        politica2.add(aritmetico);
        politica2.add(inputOutput);
        politica2.add(condiciones);
        politica2.add(iterativos);

        servidorr.serve();
        
        


        System.out.println("tenemos que ejecutar\n");
        
    }
}