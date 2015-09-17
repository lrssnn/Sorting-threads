package main;

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Niklas
 */
public class Sorts {
    
    public static boolean sorted(int[] ary){
        for(int i = 0; i < ary.length-1; i++){
            if(ary[i] > ary[i+1]){
            	//System.out.println(i + " " + (i+1));
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

    	int maxLength 	= 1000000;
    	int increment 	= 50000;
    	int average		= 1;

    	long total = System.currentTimeMillis();    	
    		
    	Thread thr = new Thread(new BubbleThread(average, maxLength, increment));
    	thr.run();
    	thr.join();
    	
    	thr = new Thread(new CombThread(average, maxLength, increment));
    	thr.run();
    	thr.join();
    	
    	thr = new Thread(new SelectThread(average, maxLength, increment));
    	thr.run();
    	thr.join();
    	
    	thr = new Thread(new MergeThread(average, maxLength, increment));
    	thr.run();
    	thr.join();
    	
    	thr = new Thread(new QuickThread(average, maxLength, increment));
    	thr.run();
    	thr.join();
    	
    	thr = new Thread(new Counting(average, maxLength, increment));
    	thr.run();
    	thr.join();
    	
    	long end = System.currentTimeMillis();
    	System.out.println("Test time: " + (end - total));
    }
    
    private static int[] readAry(Scanner read, int currentSize) {
		int[] ary = new int[currentSize];
		for(int i = 0; i < currentSize; i++){
			ary[i] = read.nextInt();
		}
		return ary;
	}

	private static void print(PrintWriter file, int[] ary) {
    	for(int i = 0; i < ary.length; i++){
    		file.print(ary[i] + " ");
    	}
    	file.println("");
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
//        for(int i = 0; i< input.length; i++){
//            System.out.print(input[i]);
//            System.out.print(" ");
//            
//        }
        System.out.print(":  " + input.length + ": " + sorted(input));
        System.out.println();
    }
    
    static public void print(int[][] input){
    	for(int i = 0; i < input.length; i++){
    		print(input[i]);
    	}
    }
    
    static public void dualAryCopy(int[][] target, int[][] source){
    	//For each Array
    	for(int i = 0; i < target.length; i++){
    		//Create a target array of the correct length
    		target[i] = new int[source[i].length];
    		//For each element
    		for(int j = 0; j < target[i].length; j++){
    			target[i][j] = source[i][j];
    		}
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
        
        while(gap > 1 || swaps == true){
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
    
    public static String percentage(int top, int bottom){
    	return String.format("%-2.2f", (top*100.0)/bottom) + "%";
    }
    
    public static String percentageBar(int top, int bottom){
    	final int SCALE = 1;
    	String output = "|";
    	int percentage = (top*100*SCALE)/bottom;
    	
    	int i;
    	for(i = 0; i < percentage; i++){
    		output += "+";
    	}
    	
    	for(int j = i; j < 100*SCALE; j++){
    		output += "-";
    	}
    	
    	output += "|";
    	
    	return output;
    }
    
    public static String outputBar(String title, int numGenerated, int numTests, int currentSize, long time){
    	String output = title + " " +
    					percentage(numGenerated, numTests) + 
    					"\t: length: " + 
    					currentSize + 
    					percentageBar(numGenerated, numTests) +
    					"Time: " + time/1000.0;
    	return output;
    }
    
    public static int[] countingSort(int[] array){
    	int MAX = 1800000;
    	int[] count = new int[MAX];
    	int[] output = new int[array.length];
    	
    	
    	//Count
    	for(int i : array){
    		count[i]++;
    	}
    	
    	int total = 0;
    	int oldCount;
    	//Convert to numBefore
    	for(int i = 0; i < MAX; i++){
    		oldCount = count[i];
    		count[i] = total;
    		total+= oldCount;
    	}
    	
    	//Copy to output array
    	for(int i : array){
    		output[count[i]] = i;
    		count[i] += 1;
    	}
    	
    	return output;
    }
}
