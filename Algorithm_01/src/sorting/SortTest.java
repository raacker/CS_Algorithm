package sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SortTest
{
    static final String insert = "hw02_00_201203393_insertion.txt";
    static final String binary = "hw02_00_201203393_binary_insertion.txt";
    static final String merge = "hw02_00_201203393_merge.txt";
    static final String three = "hw02_00_201203393_3way_merge.txt";

	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new FileInputStream(args[0]));
		
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		while(sc.hasNext())
		{
			StringTokenizer tok = new StringTokenizer(sc.nextLine(), ",");
			while(tok.hasMoreTokens())
			{
				testArray.add(Integer.valueOf(tok.nextToken()));
			}
		}
		sc.close();

		Integer[] classResult = new Integer[testArray.size()];
        classResult = testArray.toArray(classResult);
        int[] result = Arrays.stream(classResult).mapToInt(Integer::intValue).toArray();
        /*
        time("Insertion Sort", insert, new Sort(){

            public int[] sortWithLength(int[] arr, int length){
                int[] result = Sorting.insertionSort(arr, arr.length);
                return result;
            }
            public int[] sortWithRange(int[] arr, int start, int end){
                return null;
            }
        }, true, result.clone());

        time("Binary Insertion Sort", binary, new Sort(){

            public int[] sortWithLength(int[] arr, int length){
                int[] result = Sorting.insertionSortInBinarySearch(arr, arr.length);
                return result;
            }
            public int[] sortWithRange(int[] arr, int start, int end){
                return null;
            }
        }, true, result.clone());
        */
        time("Merge Sort", merge, new Sort(){

            public int[] sortWithLength(int[] arr, int length){
                return null;
            }
            public int[] sortWithRange(int[] arr, int start, int end){
                Sorting.mergeCallCount = 0;
                int[] result = Sorting.mergeSort(arr, 0, arr.length-1);
                return result;
            }
        }, false, result.clone());

        time("Three Way Merge Sort", three, new Sort(){

            public int[] sortWithLength(int[] arr, int length){
                return null;
            }
            public int[] sortWithRange(int[] arr, int start, int end){
                Sorting.mergeCallCount = 0;
                int[] result = Sorting.mergeSortInThreeWay(arr, 0, arr.length-1);
                return result;
            }
        }, false, result.clone());

	}

	private static void time(String name, String fileName, Sort function, boolean isLength, int[] intArray) {
        long start = System.nanoTime();
        int[] result;
        if (isLength)
            result = function.sortWithLength(intArray, intArray.length);
        else
            result = function.sortWithRange(intArray, 0, intArray.length-1);
        long end = System.nanoTime();

        store(result, fileName);

        double ms = (end - start) / 1000000000d;
        System.out.printf("%-25s takes \t%15f sec.\n",name, ms);
    }

    private static void store(int[] intArray, String fileName) {
        BufferedWriter output;
        try{
            output = new BufferedWriter(new FileWriter(fileName));
            for(int i = 0; i < intArray.length-1; i++) {
                output.write(i + ", ");
            }
            output.write(intArray[intArray.length-1] + "");
            if(fileName.equals(merge) || fileName.equals(three))
                output.write("     *Merge Function Call : " + Sorting.mergeCallCount);
            output.close();
        }
        catch(IOException e) {
            throw new RuntimeException();
        }
    }

}