package Algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 17/1/17
 */
public class QuickSort {
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low + 1;
        int j = high;
        do {
            while (i <= j && arr[i] <= pivot) i++;
            while (i <= j && arr[j] > pivot) j--;
            if (i < j) swap(arr, i, j);
        } while (i < j);
        swap(arr, low, j);
        return j;
    }

    private static void sort(int[] arr, int low, int high) {
        if (high <= low)
            return;
        int j = partition(arr, low, high);
        sort(arr, low, j - 1);
        sort(arr, j + 1, high);
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String args[]) {
        int[] arr = new int[10];
        Arrays.fill(arr, 1);
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
