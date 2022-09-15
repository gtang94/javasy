package io.github.gtang94.finejar.algorithm;

import java.util.Arrays;

/**
 * 快速排序
 *
 * 步骤：
 * 1. 从数列中挑出一个元素作为基准
 * 2. 重新排序数列，使得比基准小的元素放在基准前面，比基准大的元素放到基准后面
 * 3. 递归的把小于基准的子数列和大于基准的子数列排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] source = {3, 4, 1, 5, 2};
        for (int r : sort(source)) {
            System.err.print(r + " ");
        }
    }

    private static int[] sort(int[] source) {
        int[] arr = Arrays.copyOf(source, source.length);
        return quickSort(arr, 0, arr.length - 1);
    }

    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
