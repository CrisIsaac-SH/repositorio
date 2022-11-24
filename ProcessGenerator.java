import java.util.*;

public class ProcessGenerator implements Runnable {

    private Policy policy;
    private static int contad = 0;
    public static boolean generating = true;
    private double tiempoIni;
    private double timepoFin;

    public ProcessGenerator(Policy policy, double tiempoIni, double timepoFin) {
        this.policy = policy;
        this.tiempoIni = tiempoIni;
        this.timepoFin = timepoFin;

    }

    public void run() { // f - i + 1 + i
        int tiempoGenerator = (int) (Math.random() * ((timepoFin - tiempoIni) + 1) + tiempoIni);

        while (generating) {
            contad++;
            SimpleProcess newRandomProcess = null;

            int numero = (int) (Math.random() * (4 - 1 + 1) + 1);
            switch (numero) {
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

            try {

                Thread.sleep(tiempoGenerator * 1000); /// TODO este tiempo debe ser RANDOM

            } catch (InterruptedException e) {
                // this part is executed when an exception (in this example
                // InterruptedException) occurs
                System.out.println("No se pudo crear proceso");
            }

        }
        ProcessGenerator.generating = false;
        policy.finishPolicy();

    }

}