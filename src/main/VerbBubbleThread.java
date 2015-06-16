package main;

import static main.Sorts.getRandomArray;


public class VerbBubbleThread implements Runnable{
    long SWAPS, COMPS, ITRS;
    int SIZE;
    
    public VerbBubbleThread(int size){
        SIZE = size;
        SWAPS = 0;
        COMPS = 0;
        ITRS = 0;
    }
    @Override
    public void run() {
        int[] ary = getRandomArray(SIZE);
        
        long tBegin = System.currentTimeMillis();
        ary = bubbleSort(ary);
        long bEnd = System.currentTimeMillis();
        
        System.out.println("Time: " + (bEnd-tBegin)/1000.0 + " seconds");
        System.out.println("Swaps: " + SWAPS);
        System.out.println("Comparisons: " + COMPS);
        }
    
    private int[] swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
        return input;
    }
    
    public int[] bubbleSort(int[] input){
        boolean swaps = false;
        int j = input.length;
        do{
            swaps = false;
            for(int i = 1; i < j; i++){
                COMPS++;
                if(input[i-1] > input[i]){
                    input = swap(input, i-1, i);
                    SWAPS++;
                    swaps = true;
                }
                COMPS++;
            }
            j--;
            ITRS++;
            status();
        } while(swaps);
        return input;
    }
    
    public void status(){
        System.out.println(ITRS + " || " + COMPS + " comps || " + SWAPS + " swaps");
    }
}
