package main;

import java.io.*;
import java.util.Arrays;
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
    
    public static int[][] getBigRandomArray(int range, int increment, int avg){
    	
    	int[][] result = new int [(range/increment)*avg][];
    	
    	int index = 0;
    	//Main loop
    	for(int i = 1; i <= range; i += increment){
    		//Repeat AVG times
    		for(int j = 0; j < avg; j++){
    			result[index] = getRandomArray(i);
    			index++;
    		}
    	}
    	
    	return result;
    }
        
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
    	
    	
    	System.out.println("---Creating Arrays---");
    	long createBegin = System.currentTimeMillis();
    	int[][] ary = getBigRandomArray(1000000, 5000, 2);
    	int[][] ary2 = new int[ary.length][];
    	dualAryCopy(ary2, ary);
    	long createEnd = System.currentTimeMillis();
    	System.out.println("Creating Arrays took " + (createEnd - createBegin)/1000.0 + " seconds");
    	
        
        System.out.println("---Running Single Thread Sorts---");
        long singleBegin = System.currentTimeMillis();
        for(int i = 0; i < ary.length; i++){
        	Thread thr = new Thread(new QuickSingleThread(ary[i]));
        	thr.start();
        	thr.join();
        	if(!sorted(ary[i])){
        		System.out.println("Single Thread Sort Failed! Position : " + i);
        		return;
        	}
        	System.out.println("Single: " + (i*100/ary.length) + "% : length: " + ary[i].length);
        }
        long singleEnd = System.currentTimeMillis();
        System.out.println("Single thread sorts took " + (singleEnd - singleBegin)/1000.0 + " seconds");
        
        
        System.out.println("---Running Multi Thread Sorts---");
        long multiBegin = System.currentTimeMillis();
        for(int i = 0; i < ary2.length; i++){
        	Thread thr = new Thread(new QuickMultiThreadMaster(ary2[i]));
        	thr.start();
        	thr.join();
        	if(!sorted(ary2[i])){
        		System.out.println("Single Thread Sort Failed! Position : " + i);
        		return;
        	}
        	System.out.println("Multi: " + (i*100/ary2.length) + "% : length: " + ary2[i].length);
        }
        long multiEnd = System.currentTimeMillis();
        
        System.out.println("Creating Arrays took " + (createEnd - createBegin)/1000.0 + " seconds");
        System.out.println("Single thread sorts took " + (singleEnd - singleBegin)/1000.0 + " seconds");
        System.out.println("Multi thread sorts took " + (multiEnd - multiBegin)/1000.0 + " seconds");
      
        
       
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
        System.out.print(":  " + input.length);
        System.out.println();
    }
    
    static public void print(int[][] input){
    	for(int i = 0; i < input.length; i++){
    		print(input[i]);
    	}
    }
    
    static public void dualAryCopy(int[][] target, int[][] source){
    	for(int i = 0; i < target.length; i++){
    		target[i] = Arrays.copyOf(source[i], source[i].length);
    	}
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
