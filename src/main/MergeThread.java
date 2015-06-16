package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static main.Sorts.getRandomArray;
import static main.Sorts.mergeSort;


public class MergeThread implements Runnable{
    int AVERAGE, RANGE, INC;
    
    public MergeThread(int avg, int rng, int inc){
        AVERAGE = avg;
        RANGE   = rng;
        INC     = inc;
    }
    
    @Override
    public void run() {
        PrintWriter out;
        try {
            out = new PrintWriter("Merge.dat");
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
                ary = mergeSort(ary);
                //System.out.println("Bubbl: Size: " + i + " || Iteration: " + j);
            }
            bEnd = System.currentTimeMillis();
            System.out.println("Merge:  ||" + 
                    Math.round((double)i/(((double)RANGE/(double)INC )* (double)AVERAGE))/100.0 + 
                    "%      ||" + i + "         ||" + (bEnd-bBegin)/1000.0 + " seconds");
            out.println(i + "," + (bEnd-bBegin));
            out.println(i + "," + (bEnd-bBegin));
        }
        long tEnd = System.currentTimeMillis();
        System.out.println("Merge Finished in " + (tEnd - tBegin)/1000 + " seconds.");
        out.close();
    }
    
}
