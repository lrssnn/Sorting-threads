package main;

import java.io.FileNotFoundException;

class Sorts {
    
    private static boolean sorted(int[] ary){
        for(int i = 0; i < ary.length-1; i++){
            if(ary[i] > ary[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
    	
    	int maxLength 	= 5000000;
    	int increment 	= 1000;
    	int average		= 1;

        int numTests = (maxLength/increment)*average;

        long start = System.currentTimeMillis();
    	long begin = System.currentTimeMillis();

//        for (int i = increment; i < maxLength; i += increment) {
//            for(int j = 0; j < average; j++){
//                int[] ary = getRandomArray(i);
//                bubbleSort(ary);
//                System.out.print("\r" +
//                        statusBar(  "Bubble",
//                                    (i - 1)*average + j,
//                                    maxLength));
//            }
//        }

        long end = System.currentTimeMillis();
        //System.out.print(" " + (begin - end)/1000.0 + " seconds.\n");

        begin = System.currentTimeMillis();

//        for (int i = increment; i < maxLength; i += increment) {
//            for(int j = 0; j < average; j++){
//                int[] ary = getRandomArray(i);
//                selectSort(ary);
//                System.out.print("\r" +
//                        statusBar(  "Select",
//                                (i - 1)*average + j,
//                                maxLength));
//            }
//        }

        end = System.currentTimeMillis();
//        System.out.print(" " + (begin - end)/1000.0 + " seconds.\n");

        begin = System.currentTimeMillis();

        for (int i = increment; i < maxLength; i += increment) {
            for(int j = 0; j < average; j++){
                int[] ary = getRandomArray(i);
                mergeSort(ary);
                System.out.print("\r" +
                        statusBar(  "Merge",
                                (i - 1)*average + j,
                                maxLength));
            }
        }

        end = System.currentTimeMillis();
        System.out.print(" " + (begin - end)/1000.0 + " seconds.\n");

        begin = System.currentTimeMillis();

        for (int i = increment; i < maxLength; i += increment) {
            for(int j = 0; j < average; j++){
                int[] ary = getRandomArray(i);
                quickSort(ary);
                System.out.print("\r" +
                        statusBar(  "Quick",
                                (i - 1)*average + j,
                                maxLength));
            }
        }

        end = System.currentTimeMillis();
        System.out.print(" " + (begin - end)/1000.0 + " seconds.\n");

        begin = System.currentTimeMillis();

        for (int i = increment; i < maxLength; i += increment) {
            for(int j = 0; j < average; j++){
                int[] ary = getRandomArray(i);
                combSort(ary);
                System.out.print("\r" +
                        statusBar(  "Comb",
                                (i - 1)*average + j,
                                maxLength));
            }
        }

        end = System.currentTimeMillis();
        System.out.print(" " + (begin - end)/1000.0 + " seconds.\n");

        begin = System.currentTimeMillis();

        for (int i = increment; i < maxLength; i += increment) {
            for(int j = 0; j < average; j++){
                int[] ary = getRandomArray(i);
                countingSort(ary);
                System.out.print("\r" +
                        statusBar(  "Count",
                                (i - 1)*average + j,
                                maxLength));
            }
        }

        end = System.currentTimeMillis();
        System.out.print(" " + (begin - end)/1000.0 + " seconds.\n");

    	long finish = System.currentTimeMillis();
    	System.out.println("Test time: " + (finish - start));
    }

    private static void verify(String sortName, int[] ary) {
        if(sorted(ary)){
            System.out.println(sortName + " sorted successfully");
        } else {
            System.out.println(sortName + " not sorted.");
        }
    }

    private static void mergeSort(int[] input){
        //Base Case
        if(input.length == 1){
            return;
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
 
        mergeSort(a);
        mergeSort(b);

        merge(input, a,b);
    }

    private static void merge(int[] out, int[] a, int[] b) {
        int indA= 0, indB = 0;
        for(int i = 0; i < a.length + b.length; i++){
            if(a[indA] < b[indB]){
                out[i] = a[indA];
                indA++;
                if(indA == a.length){
                    System.arraycopy(b, indB, out, i+1, b.length-indB);
                    return;
                }
            } else {
                out[i] = b[indB];
                indB++;
                if(indB == b.length){
                    System.arraycopy(a, indA, out, i+1, a.length-indA);
                    return;
                }
            }
        }
    }

    private static int[] getRandomArray(int length){
        int[] output = new int[length];
        for(int i = 0; i < length; i++){
            output[i] = (int) (Math.floor(Math.random() * (1800000)));
        }
        return output;
    }

    private static void selectSort(int[] input){
        for(int i = 0; i < input.length; i++){
            int currentLowest = i;
            for(int j = i; j < input.length; j++){
                if(input[j] < input[currentLowest]){
                    currentLowest = j;
                }
            }
            swap(input, i, currentLowest);
        }
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
    
    private static void bubbleSort(int[] input){
        boolean swaps;
        int j = input.length;
        do{
            swaps = false;
            for(int i = 1; i < j; i++){
                if(input[i-1] > input[i]){
                    swap(input, i-1, i);
                    swaps = true;
                }
            }
            j--;
        } while(swaps);
    }
    
    private static void combSort(int[] input){
        int gap = input.length;
        double shrink = 1.3;
        boolean swaps = true;
        
        while(gap > 1 || swaps){
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
                    swap(input, i, i+gap);
                    swaps = true;
                }
                i++;
            }
        }
    }
    
    private static void quickSort(int[] input){
        //Base Case
        if(input.length <= 1) return;
        
        //Choose Pivot
        int pivot = input[input.length /2];
        
        //Partition
        int[] less = new int[input.length], equal = new int[input.length], greater = new int[input.length];
        int iLess = 0, iEq = 0, iGreat = 0;
        for (int anInput : input) {
            if (anInput < pivot) {
                less[iLess] = anInput;
                iLess++;
            } else if (anInput == pivot) {
                equal[iEq] = anInput;
                iEq++;
            } else {
                greater[iGreat] = anInput;
                iGreat++;
            }
        }
        
        if(iEq == input.length){
            return;
        }
        less = truncate(less, input.length - iLess);
        equal = truncate(equal, input.length - iEq);
        greater = truncate(greater, input.length - iGreat);
        
        //Sort
        quickSort(less);
        quickSort(equal);
        quickSort(greater);
        
        //Reconstruct
        System.arraycopy(less, 0, input, 0, less.length);
        System.arraycopy(equal, 0, input, less.length, equal.length);
        System.arraycopy(greater, 0, input, less.length + equal.length, greater.length);
        }

    private static int[] truncate(int[] array, int numRemoved) {
        int[] output = new int[array.length - numRemoved];
        System.arraycopy(array, 0, output, 0, output.length);
        return output;
    }

    private static void countingSort(int[] array){
        int MAX = max(array)+1;
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

        System.arraycopy(output, 0, array, 0, array.length);
    }

    private static int max(int[] array){
        int max = array[0];
        for(int num: array){
            if(num > max){
                max = num;
            }
        }
        return max;
    }

    private static String statusBar(String title, int test, int numTests){
        String bar = progBar(test, numTests);
        return title + "\t |  " + test + "\t / " + numTests + "\t " + bar;
    }

    private static String progBar(int num, int max){
        final int BAR_LENGTH = 80;
        int percent = (int)(num*100/(float)max);

        StringBuilder bar = new StringBuilder(BAR_LENGTH + 3);
        bar.append('|');

        for(int i = 0; i < BAR_LENGTH; i++){
            bar.append( i*100/BAR_LENGTH <= percent ? '#' : '-');
        }

        bar.append('|');
        bar.append(percent).append("%");
        return bar.toString();
    }
}
