package Algorithm;

import java.util.Random;

/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 17/1/14
 */
public class InsertionSort {
    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                } else {
                    break;
                }
            }
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
