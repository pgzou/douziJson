package com.douzi.algorithm;

import java.util.Arrays;

/**
 * quick sort
 * @author zouwei
 * @date 2019-03-24
 */

public class QuickSort {

    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }


    private static void sort(int[] arr, int lo, int hi) {
        if (lo >= hi) {return;}

        int position = partition(arr, lo, hi);

        sort(arr, lo, position - 1); // let arr[lo, partition-1] in order
        sort(arr, position + 1, hi); // let arr[partition+1, hi] in order
    }

    /**
     * make sure arr[lo, partition-1] < arr[partition] && arr[partition] < arr[partition+1, hi]
     * @return position
     */
    private static int partition(int[] arr, int lo, int hi) {
        int relative = arr[lo];
        int i = lo, j = hi + 1;

        while (true) {
            while (arr[++i] <= relative) {
                if (i == hi) {break;}
            }

            while (arr[--j] >= relative) {
                if (j == lo) {break;}
            }

            if (i >= j) {break;}

            swap(arr, i, j);
        }

        swap(arr, lo, j);

        return j;
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {18, 2, 3, 5, 9, 4, 3, 15, 84, 9, 147, 75, 36, 85, 12, 43, 14, 13};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
