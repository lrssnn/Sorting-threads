package main;

import static main.Sorts.print;
import static main.Sorts.quickSort;


public class QuickMultiThreadMaster implements Runnable{
    
    int[] ary;
    
    public QuickMultiThreadMaster(int[] target){
        ary = target;
    }
    
    @Override
    public synchronized void run(){
        //print(ary);
        fill(ary, 0, quickSort(ary), 0);
        //print(ary);
        notifyAll();
        //ary[0] = 0;
    }    
    
     public static int[] quickSort(int[] input){
        //Base Case
        if(input.length <= 1) return input;
        
        //Choose Pivot
        int pivot = input[(int)input.length/2];
        
        //Partition
        int[] less = new int[input.length], equal = new int[input.length], greater = new int[input.length];
        int iLess = 0, iEq = 0, iGreat = 0;
        for(int i = 0; i < input.length; i++){
            if(input[i] < pivot){
                less[iLess] = input[i];
                iLess++;
            } else if(input[i] == pivot){
                equal[iEq] = input[i];
                iEq++;
            } else {
                greater[iGreat] = input[i];
                iGreat++;
            }
        }
        
        if(iEq == input.length){
            return equal;
        }
        less = truncate(less, input.length - iLess);
        equal = truncate(equal, input.length - iEq);
        greater = truncate(greater, input.length - iGreat);
        
        //Sort
        less = quickSort(less);
        equal = quickSort(equal);
        greater = quickSort(greater);
        
        //Reconstruct
        int[] output = new int[input.length];
        output = fill(output, 0, less, 0);
        output = fill(output, less.length, equal, 0);
        output = fill(output, less.length + equal.length, greater, 0);
        
        return output;
    }

    private static int[] truncate(int[] array, int numRemoved) {
        int[] output = new int[array.length - numRemoved];
        for(int i = 0; i < output.length; i++){
            output[i] = array[i];
        }
        return output;
    }
    
    private static int[] fill(int[] target, int targetStart, int[] source, int sourceStart) {
        int sourceInd = sourceStart;
        int targetInd = targetStart;
        for(int i = 0; i < (source.length - sourceStart); i++){
            target[targetInd] = source[sourceInd];
            targetInd++;
            sourceInd++;
        }
        return target;
    }
}
