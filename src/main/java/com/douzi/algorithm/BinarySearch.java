package com.douzi.algorithm;

/**
 * @author zouwei
 * @date 2019-03-29
 */

public class BinarySearch {


    /**
     * find arr[i] == target and return i, if not exists return -1
     * @param arr a ordered array
     * @param target target value
     */
    public static int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2; // prevent int overflow
            if (arr[mid] > target) {
                hi = mid - 1;
            } else if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**
     * find first arr[i] > target and return i, if not exists return -1
     * @param arr a ordered array
     * @param target target value
     */
    public static int binarySearch2(int[] arr, int target) {
        if (arr[arr.length - 1] <= target) {return -1;}

        int lo = 0, hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2; // prevent int overflow
            if (arr[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return hi + 1;
    }


    public static void main(String[] args) {
        int [] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 18, 19, 25, 32, 35};

        assert binarySearch(arr, 8) == 7;
        assert binarySearch(arr, 15) == -1;

        assert binarySearch2(arr, 10) == 10;
        assert binarySearch2(arr, 20) == 14;
        assert binarySearch2(arr, 40) == -1;

        System.out.println("all pass, good!");
    }

}
