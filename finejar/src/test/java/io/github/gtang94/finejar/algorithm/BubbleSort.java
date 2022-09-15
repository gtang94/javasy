package io.github.gtang94.finejar.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 步骤：
 * 1. 比较相邻两个元素，如果第一个比第二个大，则交换他们两个
 * 2. 对每一对相邻元素做相同工作，从开始第一对到结尾最后一对，这样最后的元素是最大的数
 * 3. 针对除最后一个的所有元素重复上面步骤
 * 4. 重复1-3步骤，直到排序完成
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] source = {3, 4, 1, 5, 2};

        for (int r : sort(source)) {
            System.err.print(r + " ");
        }
    }

    public static int[] sort(int[] source) {
        int[] arr = Arrays.copyOf(source, source.length);
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }
}
