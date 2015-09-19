package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static main.Sorts.getRandomArray;


public class Counting implements Runnable{
    int AVERAGE, RANGE, INC;
    
    public Counting(int avg, int rng, int inc){
        AVERAGE = avg;
        RANGE   = rng;
        INC     = inc;
    }
    @Override
    public void run() {
        PrintWriter out;
        try {
            out = new PrintWriter("Count.dat");
        } catch (FileNotFoundException ex) {
            return;
        }
        long bBegin, bEnd; 
        long tBegin = System.currentTimeMillis();
        System.out.print("Count: ");
        for(int i = INC; i <= RANGE; i += INC){  //Max: 180000
            //Averaging Loop
            bBegin = System.currentTimeMillis();
            for(int j = 0; j < AVERAGE; j++){
                int[] ary = getRandomArray(i);
                ary = Sorts.countingSort(ary);
                System.out.print("+");
                //System.out.println("Bubbl: Size: " + i + " || Iteration: " + j);
            }
            bEnd = System.currentTimeMillis();
            //System.out.println(Sorts.outputBar("Counting", i, RANGE, i, bEnd-bBegin));
            out.println(i + "," + (bEnd-bBegin));
        }
        long tEnd = System.currentTimeMillis();
        System.out.println((tEnd - tBegin)/1000 + " seconds.");
        out.close();
    }
    
}
