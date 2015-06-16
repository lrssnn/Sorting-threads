package main;

import java.io.*;
/**
 *
 * @author Niklas
 */
public class Sorts {
    
    public static boolean sorted(int[] ary){
        for(int i = 0; i < ary.length-1; i++){
            if(ary[i] > ary[i+1]){
                return false;
            }
        }
        return true;
    }
    
    public static synchronized void main(String[] args) throws FileNotFoundException, InterruptedException {
    	System.out.println("---Creating Arrays---");
        int[] ary  = getRandomArray(9999999);
        int[] ary2 = getRandomArray(999999);
        
        System.out.println("---Creating Threads---");
        Thread thr = new Thread(new QuickMultiThreadMaster(ary));
        Thread thr2 = new Thread(new QuickSingleThread(ary2));
        
        System.out.println("---Running Sorts---");
        thr.start();
        thr2.start();
        
        System.out.println("---Waiting---");
        thr.join();
        thr2.join();


        System.out.println("1:" + sorted(ary));
        System.out.println("2:" + sorted(ary2));
        
        //int AVERAGE = 1, RANGE = 9000000, INC = 20000;
        /*Thread bub = new Thread(new BubbleThread(AVERAGE, RANGE, INC));
        Thread mrg = new Thread(new MergeThread (AVERAGE, RANGE, INC));
        Thread qck = new Thread(new QuickThread (AVERAGE, RANGE, INC));
       
        Thread slc = new Thread(new SelectThread(AVERAGE, RANGE, INC));
        
        bub.start();
        mrg.start();*/
        //qck.start();
        //Thread cmb = new Thread(new CombThread  (AVERAGE, RANGE, INC));
        //cmb.start();
        //slc.start();
        
        //Thread bub = new Thread(new VerbBubbleThread(2000000));
        //bub.start();
        
        //thr3.start();
        //System.out.println("Something else");
        
        /*long totalb = System.currentTimeMillis();
        int AVERAGE = 200, RANGE = 10000, INC = 20;
        
        PrintWriter mergeOut = new PrintWriter("Merge.dat");
        long totale, mBegin, mEnd;    
        for(int i = 1; i < RANGE; i += INC){  //Max: 180000
            //Averaging Loop
            mBegin = System.currentTimeMillis();
            for(int j = 0; j < AVERAGE; j++){
                int[] ary = getRandomArray(i);
                ary = mergeSort(ary);
                System.out.println("Merge: Size: " + i + " || Iteration: " + j);
            }
            mEnd = System.currentTimeMillis();
            System.out.println("Sort: " + AVERAGE + " " + i + " sized arrays took " + (mEnd - mBegin)/1000.0 + " seconds");
            mergeOut.println(i + "," + (mEnd-mBegin));
        }
        mergeOut.close();
        
        PrintWriter quickOut = new PrintWriter("Quick.dat");
        long qBegin, qEnd;    
        for(int i = 1; i < RANGE; i += INC){  //Max: 180000
            //Averaging Loop
            qBegin = System.currentTimeMillis();
            for(int j = 0; j < AVERAGE; j++){
                int[] ary = getRandomArray(i);
                ary = quickSort(ary);
                System.out.println("Quick: Size: " + i + " || Iteration: " + j);
            }
            qEnd = System.currentTimeMillis();
            System.out.println("Sort: " + AVERAGE + " " + i + " sized arrays took " + (qEnd - qBegin)/1000.0 + " seconds");
            quickOut.println(i + "," + (qEnd-qBegin));
        }
        quickOut.close();
        
        PrintWriter combOut = new PrintWriter("Comb.dat");
        long cBegin, cEnd;    
        for(int i = 1; i < RANGE; i += INC){  //Max: 180000
            //Averaging Loop
            cBegin = System.currentTimeMillis();
            for(int j = 0; j < AVERAGE; j++){
                int[] ary = getRandomArray(i);
                ary = combSort(ary);
                System.out.println("combs: Size: " + i + " || Iteration: " + j);
            }
            cEnd = System.currentTimeMillis();
            System.out.println("Sort: " + AVERAGE + " " + i + " sized arrays took " + (cEnd - cBegin)/1000.0 + " seconds");
            combOut.println(i + "," + (cEnd-cBegin));
        }
        combOut.close();
        
         PrintWriter selectOut = new PrintWriter("Select.dat");
        long sBegin, sEnd;    
        for(int i = 1; i < RANGE; i += INC){  //Max: 180000
            //Averaging Loop
            sBegin = System.currentTimeMillis();
            for(int j = 0; j < AVERAGE; j++){
                int[] ary = getRandomArray(i);
                ary = selectSort(ary);
                System.out.println("Selec: Size: " + i + " || Iteration: " + j);
            }
            sEnd = System.currentTimeMillis();
            System.out.println("Sort: " + AVERAGE + " " + i + " sized arrays took " + (sEnd - sBegin)/1000.0 + " seconds");
            selectOut.println(i + "," + (sEnd-sBegin));
        }
        selectOut.close();
        
        PrintWriter bubbleOut = new PrintWriter("Bubble.dat");
        long bBegin, bEnd;    
        for(int i = 1; i < RANGE; i += INC){  //Max: 180000
            //Averaging Loop
            bBegin = System.currentTimeMillis();
            for(int j = 0; j < AVERAGE; j++){
                int[] ary = getRandomArray(i);
                ary = bubbleSort(ary);
                System.out.println("Bubbl: Size: " + i + " || Iteration: " + j);
            }
            bEnd = System.currentTimeMillis();
            System.out.println("Sort: " + AVERAGE + " " + i + " sized arrays took " + (bEnd - bBegin)/1000.0 + " seconds");
            bubbleOut.println(i + "," + (bEnd-bBegin));
        }
        bubbleOut.close();
        
        totale = System.currentTimeMillis();
        System.out.println("Test took " + (totale-totalb) + " milliseconds (" + ((totale-totalb)/60000.0) + " minutes).");
                */
    }
    
    static public int[] mergeSort(int[] input){
        //Base Case
        if(input.length == 1){
            return input;
        }
        
        //Split into two arrays
        int[] a = new int[input.length/2];
        int[] b = new int[(int)Math.ceil(input.length/2.0)];
        int i;
        for(i = 0; i<input.length/2; i++){
            a[i] = input [i];
        }
        for(int j=0; i<input.length; j++){
            b[j] = input[i];
            i++;
        }
 
        a = mergeSort(a);
        b = mergeSort(b);
        
        //Merge
        //print(a);
        //print(b);
        input = merge(a,b);
        //System.out.print("Merge:");
        //print(input);
        return input;
    }
    
    static public void print(int[] input){
        for(int i = 0; i< input.length; i++){
            System.out.print(input[i]);
            System.out.print(" ");
            
        }
        System.out.println();
    }

    private static int[] merge(int[] a, int[] b) {
        int[] output = new int[a.length + b.length];
        int indA= 0, indB = 0;
        for(int i = 0; i < a.length + b.length; i++){
            if(a[indA] < b[indB]){
                output[i] = a[indA];
                indA++;
                if(indA == a.length){
                    output = fill(output, i+1, b, indB);
                    return output;
                }
            } else {
                output[i] = b[indB];
                indB++;
                if(indB == b.length){
                    output = fill(output, i+1, a, indA);
                    return output;
                }
            }
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
    
    public static int[] getRandomArray(int length){
        int[] output = new int[length];
        for(int i = 0; i < length; i++){
            output[i] = (int) (Math.floor(Math.random() * (1800000 - 0)));
        }
        return output;
    }
    
    public static int[] insertionSort(int[] input){
        int[] output = new int[input.length];
        for(int i = 0; i < input.length; i++){
            insert(output, input[i]);
        }
        return output;
    }

    private static void insert(int[] array, int number) {
        for(int i = 0; i < array.length; i++){
            if(number <= array[i]){
                
            }
        }
    }
    
    public static int[] selectSort(int[] input){
        for(int i = 0; i < input.length; i++){
            int currentLowest = i;
            for(int j = i; j < input.length; j++){
                if(input[j] < input[currentLowest]){
                    currentLowest = j;
                }
            }
            input = swap(input, i, currentLowest);
        }
        return input;
    }

    private static int[] swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
        return input;
    }
    
    public static int[] bubbleSort(int[] input){
        boolean swaps = false;
        int j = input.length;
        do{
            swaps = false;
            for(int i = 1; i < j; i++){
                if(input[i-1] > input[i]){
                    input = swap(input, i-1, i);
                    swaps = true;
                }
            }
            j--;
        } while(swaps);
        return input;
    }
    
    public static int[] combSort(int[] input){
        int gap = input.length;
        double shrink = 1.3;
        boolean swaps = true;
        
        while(gap > 1 && swaps == true){
            //Update the gap
            gap = (int)(gap/shrink);
            if(gap < 1){
                gap = 1;
            }
            
            //Do the sort
            int i = 0;
            swaps = false;
            while(i+gap < input.length){
                if(input[i] > input[i+gap]){
                    input = swap(input, i, i+gap);
                    swaps = true;
                }
                i++;
            }
        }
        return input;
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
}
