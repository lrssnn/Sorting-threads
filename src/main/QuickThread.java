package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static main.Sorts.getRandomArray;
import static main.Sorts.quickSort;


public class QuickThread implements Runnable{
    int AVERAGE, RANGE, INC;
    
    public QuickThread(int avg, int rng, int inc){
        AVERAGE = avg;
        RANGE   = rng;
        INC     = inc;
    }
    @Override
    public void run() {
        PrintWriter out;
        try {
            out = new PrintWriter("Quick.dat");
        } catch (FileNotFoundException ex) {
            return;
        }
        long bBegin, bEnd; 
        long tBegin = System.currentTimeMillis();
        for(int i = RANGE; i <= RANGE; i += INC){  //Max: 180000
            //Averaging Loop
            bBegin = System.currentTimeMillis();
            for(int j = 0; j < AVERAGE; j++){
                int[] ary = getRandomArray(i);
                ary = quickSort(ary);
                //System.out.println("Bubbl: Size: " + i + " || Iteration: " + j);
            }
            bEnd = System.currentTimeMillis();
            System.out.println("Quick:  ||" + 
                    Math.round((double)i/(((double)RANGE/(double)INC )* (double)AVERAGE))/100.0 + 
                    "%      ||" + i + "         ||" + (bEnd-bBegin)/1000.0 + " seconds");
            out.println(i + "," + (bEnd-bBegin));
            out.println(i + "," + (bEnd-bBegin));
        }
        long tEnd = System.currentTimeMillis();
        System.out.println("Quick Finished in " + (tEnd - tBegin)/1000 + " seconds.");
        out.close();
    }
    
}
