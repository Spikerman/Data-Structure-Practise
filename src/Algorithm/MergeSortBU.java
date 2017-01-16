package Algorithm;

import java.util.Random;

/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 17/1/16
 */
public class MergeSortBU {
    private static int[] aux;

    public static void sort(int[] arr) {
        int N = arr.length;
        aux = new int[N];
        for (int sz = 1; sz < N; sz = sz * 2)
            for (int low = 0; low < N - sz; low += sz + sz) {
                merge(arr, low, low + sz - 1, Math.min(low + sz + sz - 1, N - 1));
            }
    }

    private static void merge(int[] arr, int low, int middle, int high) {
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
