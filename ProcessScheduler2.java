import java.io.*;
//import java.util.Random;

public class ProcessScheduler2{
    public static void main(String[] args) throws Exception{

        double tiempo1 = 0;
        double tiempo2 = 0;

        if(args[0].equals("-fcfs")){

            if(args[1].substring(3,4).equals("-")){
                tiempo1 = Double.parseDouble(args[1].substring(0,3));
                tiempo2 = Integer.parseInt(args[1].substring(4,5));
            }
            else if(args[1].substring(1,2).equals("-")){
                tiempo1 = Integer.parseInt(args[1].substring(0,1));
                tiempo2 = Double.parseDouble(args[1].substring(2,5));
            }
            /*System.out.println(tiempo1);
            System.out.println(tiempo2);*/
            double random = Math.random()* (tiempo2 + tiempo1);
            System.out.println(random);
            
            double aritmetico = Double.parseDouble(args[2]);
            //System.out.println(aritmetico);
            double intOut = Double.parseDouble(args[3]);
            //System.out.println(intOut);
            double condicional = Double.parseDouble(args[4]);
            //System.out.println(condicional);
            double iterativo = Double.parseDouble(args[5]);
            //System.out.println(iterativo);

        }
        else if(args[0].equals("-lcfs")){

            if(args[1].substring(3,4).equals("-")){
                tiempo1 = Double.parseDouble(args[1].substring(0,3));
                tiempo2 = Integer.parseInt(args[1].substring(4,5));
            }
            else if(args[1].substring(1,2).equals("-")){
                tiempo1 = Integer.parseInt(args[1].substring(0,1));
                tiempo2 = Double.parseDouble(args[1].substring(2,5));
            }

            double random = Math.random()* (tiempo2 + tiempo1);
            
            double aritmetico = Double.parseDouble(args[2]);
            double intOut = Double.parseDouble(args[3]);
            double condicional = Double.parseDouble(args[4]);
            double iterativo = Double.parseDouble(args[5]);

        }
        else if(args[0].equals("-rr")){

            if(args[1].substring(3,4).equals("-")){
                tiempo1 = Double.parseDouble(args[1].substring(0,3));
                tiempo2 = Integer.parseInt(args[1].substring(4,5));
            }
            else if(args[1].substring(1,2).equals("-")){
                tiempo1 = Integer.parseInt(args[1].substring(0,1));
                tiempo2 = Double.parseDouble(args[1].substring(2,5));
            }

            double random = Math.random()* (tiempo2 + tiempo1);
            
            double aritmetico = Double.parseDouble(args[2]);
            double intOut = Double.parseDouble(args[3]);
            double condicional = Double.parseDouble(args[4]);
            double iterativo = Double.parseDouble(args[5]);

        }
        else if(args[0].equals("-pp")){

            if(args[1].substring(3,4).equals("-")){
                tiempo1 = Double.parseDouble(args[1].substring(0,3));
                tiempo2 = Integer.parseInt(args[1].substring(4,5));
            }
            else if(args[1].substring(1,2).equals("-")){
                tiempo1 = Integer.parseInt(args[1].substring(0,1));
                tiempo2 = Double.parseDouble(args[1].substring(2,5));
            }

            double random = Math.random()* (tiempo2 + tiempo1);
            
            double aritmetico = Double.parseDouble(args[2]);
            double intOut = Double.parseDouble(args[3]);
            double condicional = Double.parseDouble(args[4]);
            double iterativo = Double.parseDouble(args[5]);
        }

    }
}