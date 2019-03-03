package sortingAlgorithms;

import java.util.Random;

public class Test {

    public static void main(String args[]){
        int NUM_ITERS = 1;
        int numListsToTest = 100000;
        Random randomGenerator = new Random();
        Integer[][] a = new Integer[NUM_ITERS][numListsToTest];
        for (int i = 0;i < NUM_ITERS;i++) {
            for (int j = 0;j < numListsToTest;j++){
                a[i][j] = randomGenerator.nextInt();
            }
        }
//        Node[][] a = new Node[NUM_ITERS][numListsToTest];
//        for (int i = 0;i < NUM_ITERS;i++){
//            for (int j = 0;j < numListsToTest;j++){
//                a[i][j] = new Node(randomGenerator.nextInt());
//                if (j > 0){
//                    a[i][j-1].setNext(a[i][j]);
//                }
//            }
//        }
//        for (int i = 0;i < NUM_ITERS;i++){
//            SortingInterface listSorter = new SortingAlgorithms();
//            listSorter.mergeSortLL(a[i][0], false);
//        }
        SortingInterface listSorter = new SortingAlgorithms();
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        for(int i = 0; i < NUM_ITERS; i++) {
            listSorter.insertionSort(a[i], 0, numListsToTest-1, false);
//            listSorter.shakerSort(a[i], 0, numListsToTest-1, false);
//            listSorter.randomizedQuickSort(a[i], 0, numListsToTest-1, false);
//            listSorter.hybridSort(a[i], 0, numListsToTest-1, false);
//            listSorter.mergeSortLL(a[i][0], false);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime);
        double runningTime = ((double) (endTime - startTime));
        System.out.println(runningTime);
    }
}