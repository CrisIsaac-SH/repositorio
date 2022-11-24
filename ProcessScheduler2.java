import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;
import java.util.regex.Pattern;

public class ProcessScheduler2 {
    public static void main(String[] args) throws Exception {

        double str1 = Double.parseDouble(args[1].substring(0, 3));
        double str2 = Double.parseDouble(args[1].substring(4, 7));

        if (args[0].equals("-fcfs")) {

            SimpleProcess.AritmethicTime = Double.parseDouble(args[2]);
            SimpleProcess.IOTime = Double.parseDouble(args[3]);
            SimpleProcess.ConditionalTime = Double.parseDouble(args[4]);
            SimpleProcess.IterativoTime = Double.parseDouble(args[5]);

            Policy politica1 = new FCFS();
            ProcessGenerator generador = new ProcessGenerator(politica1, str1, str2);
            Server servidor1 = new Server(politica1);
            Thread t = new Thread(servidor1);
            Thread t3 = new Thread(generador);
            t3.start();
            t.start();

        }

        else if (args[0].equals("-lcfs")) {
            SimpleProcess.AritmethicTime = Double.parseDouble(args[2]);
            SimpleProcess.IOTime = Double.parseDouble(args[3]);
            SimpleProcess.ConditionalTime = Double.parseDouble(args[4]);
            SimpleProcess.IterativoTime = Double.parseDouble(args[5]);

            Policy politica1 = new LCFS();
            ProcessGenerator generador = new ProcessGenerator(politica1, str1, str2);
            Server servidor1 = new Server(politica1);
            Thread t = new Thread(servidor1);
            Thread t3 = new Thread(generador);
            t3.start();
            // Thread.sleep(100);

            // double random = Math.random() * (tiempo2 + tiempo1);
            // long l = Math.round(random);
            // Thread.sleep(l);
            t.start();
        }

        else if (args[0].equals("-rr")) {
            SimpleProcess.AritmethicTime = Double.parseDouble(args[3]);
            SimpleProcess.IOTime = Double.parseDouble(args[4]);
            SimpleProcess.ConditionalTime = Double.parseDouble(args[5]);
            SimpleProcess.IterativoTime = Double.parseDouble(args[6]);
            double quantum = Double.parseDouble(args[7]);

            Policy politica1 = new RoundRobin(quantum);
            ProcessGenerator generador = new ProcessGenerator(politica1, str1, str2);
            Server servidor1 = new Server(politica1);
            Thread t = new Thread(servidor1);
            Thread t3 = new Thread(generador);
            t3.start();
            // Thread.sleep(100);

            // double random = Math.random() * (tiempo2 + tiempo1);
            // long l = Math.round(random);
            // Thread.sleep(l);
            t.start();
        }

        else if (args[0].equals("-pp")) {
            SimpleProcess.AritmethicTime = Double.parseDouble(args[2]);
            SimpleProcess.IOTime = Double.parseDouble(args[3]);
            SimpleProcess.ConditionalTime = Double.parseDouble(args[4]);
            SimpleProcess.IterativoTime = Double.parseDouble(args[5]);

            Policy politica1 = new PriorityPolicy();
            ProcessGenerator generador = new ProcessGenerator(politica1, str1, str2);
            Server servidor1 = new Server(politica1);
            Thread t = new Thread(servidor1);
            Thread t3 = new Thread(generador);
            t3.start();
            Thread.sleep(100);
            t.start();
        }

        else if (args[0].equals("-dual") && args[1].equals("-fcfs")) {
            /**
             * if (args[2].substring(3, 4).equals("-")) {
             * tiempo1 = Double.parseDouble(args[2].substring(0, 3));
             * tiempo2 = Integer.parseInt(args[2].substring(4, 5));
             * } else if (args[2].substring(1, 2).equals("-")) {
             * tiempo1 = Integer.parseInt(args[2].substring(0, 1));
             * tiempo2 = Double.parseDouble(args[2].substring(2, 5));
             * }
             */
            SimpleProcess.AritmethicTime = Double.parseDouble(args[3]);
            SimpleProcess.IOTime = Double.parseDouble(args[4]);
            SimpleProcess.ConditionalTime = Double.parseDouble(args[5]);
            SimpleProcess.IterativoTime = Double.parseDouble(args[6]);
            Policy politica1 = new FCFS();
            ProcessGenerator generador = new ProcessGenerator(politica1, str1, str2);

            Server servidor1 = new Server(politica1);
            Server servidor2 = new Server(politica1);

            Thread t = new Thread(servidor1);
            Thread t2 = new Thread(servidor2);
            Thread t3 = new Thread(generador);
            t3.start();
            Thread.sleep(100);
            t.start();
            t2.start();
        }

        else if (args[0].equals("-dual") && args[1].equals("-lcfs")) {
            SimpleProcess.AritmethicTime = Double.parseDouble(args[3]);
            SimpleProcess.IOTime = Double.parseDouble(args[4]);
            SimpleProcess.ConditionalTime = Double.parseDouble(args[5]);
            SimpleProcess.IterativoTime = Double.parseDouble(args[6]);

            Policy politica1 = new LCFS();
            ProcessGenerator generador = new ProcessGenerator(politica1, str1, str2);

            Server servidor1 = new Server(politica1);
            Server servidor2 = new Server(politica1);

            Thread t = new Thread(servidor1);
            Thread t2 = new Thread(servidor2);
            Thread t3 = new Thread(generador);
            t3.start();
            Thread.sleep(100);
            t.start();
            t2.start();

        } else if (args[0].equals("-dual") && args[1].equals("-rr")) {
            SimpleProcess.AritmethicTime = Double.parseDouble(args[3]);
            SimpleProcess.IOTime = Double.parseDouble(args[4]);
            SimpleProcess.ConditionalTime = Double.parseDouble(args[5]);
            SimpleProcess.IterativoTime = Double.parseDouble(args[6]);
            double quantum = Double.parseDouble(args[7]);

            Policy politica1 = new RoundRobin(quantum);
            ProcessGenerator generador = new ProcessGenerator(politica1, str1, str2);

            Server servidor1 = new Server(politica1);
            Server servidor2 = new Server(politica1);

            Thread t = new Thread(servidor1);
            Thread t2 = new Thread(servidor2);
            Thread t3 = new Thread(generador);
            t3.start();
            Thread.sleep(100);
            t.start();
            t2.start();
        } else if (args[0].equals("-dual") && args[1].equals("-pp")) {
            SimpleProcess.AritmethicTime = Double.parseDouble(args[3]);
            SimpleProcess.IOTime = Double.parseDouble(args[4]);
            SimpleProcess.ConditionalTime = Double.parseDouble(args[5]);
            SimpleProcess.IterativoTime = Double.parseDouble(args[6]);

            Policy politica1 = new PriorityPolicy();
            ProcessGenerator generador = new ProcessGenerator(politica1, str1, str2);

            Server servidor1 = new Server(politica1);
            Server servidor2 = new Server(politica1);

            Thread t = new Thread(servidor1);
            Thread t2 = new Thread(servidor2);
            Thread t3 = new Thread(generador);
            t3.start();
            Thread.sleep(100);
            t.start();
            t2.start();
        }
    }

}
