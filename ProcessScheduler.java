import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;


public class ProcessScheduler{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //String parar = br.readLine();
        double tiempoAr = Double.parseDouble(args[0]);
        double tiempoIO = Double.parseDouble(args[1]);
        double tiempoCon = Double.parseDouble(args[2]);
        double tiempoIter = Double.parseDouble(args[3]);

        SimpleProcess.AritmethicTime = tiempoAr;
        SimpleProcess.IOTime = tiempoIO;
        SimpleProcess.ConditionalTime = tiempoCon;
        SimpleProcess.IterativoTime = tiempoIter;

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
        ProcessGenerator generados = new ProcessGenerator(politica1);
        //Server servidor = new Server(politica1);
        Server servidor1 = new Server(politica1);
        //politica1.add(aritmetico);
        //politica1.add(inputOutput);
        //politica1.add(condiciones);
        //politica1.add(iterativos);

        

        Server servidor2 = new Server(politica1);
        //politica1.add(aritmetico1);
        //politica1.add(inputOutput2);
        //politica1.add(condiciones3);
        //politica1.add(iterativos4);

        Thread processT = new Thread(generados);


        Thread t = new Thread(servidor1);
        Thread t2 = new Thread(servidor2);
        t.start();             
        
        t2.start();
        processT.start();

        Thread.sleep(10);
        

        

        servidor1.run();
        servidor2.run();


        
       
        
    }
}