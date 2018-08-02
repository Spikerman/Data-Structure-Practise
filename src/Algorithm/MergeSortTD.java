package Algorithm;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 17/1/15
 */

public class MergeSortTD {

    private static int[] aux;

    private static void sort(int[] arr, int low, int high) {
        if (high <= low)
            return;
        int mid = (low + high) / 2;
        sort(arr, low, mid);
        sort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    public static void sort(int[] arr) {
        aux = new int[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    private static void merge(int[] arr, int low, int middle, int high) {
        //todo 注意循环的边界范围,low,high,<=
        for (int i = low; i <= high; i++) {
            aux[i] = arr[i];
        }
        int m = low;
        int n = middle + 1;
        for (int i = low; i <= high; i++) {
            if (m > middle)
                arr[i] = aux[n++];
            else if (n > high)
                arr[i] = aux[m++];
            else if (aux[m] < aux[n])
                arr[i] = aux[m++];
            else
                arr[i] = aux[n++];
        }
    }

    public static void main(String args[]) {
        int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}



