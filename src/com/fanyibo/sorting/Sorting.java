package com.fanyibo.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int start, int end) {

        int len = end - start + 1;
        if (len <= 1) {
            return;
        }
        int pos = partition(array, start, end);
        int left = pos;
        int right = pos;
        while (left >= start && array[left] == array[pos]) {
            left--;
        }
        while (right <= end && array[right] == array[pos]) {
            right++;
        }
        quickSort(array, start, left);
        quickSort(array, right, end);
    }

    private static int partition(int[] array, int start, int end) {

        int len = end - start + 1;
        if (len < 1) {
            return 0;
        } else if (len == 1) {
            return start;
        }
        int mid = (start + end) / 2;
        int pivot = array[mid];
        swap(array, mid, end);

        int i = start;
        for (int j = start; j < end; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, end);
        return i;
    }

    private static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static int[] mergeSort(int[] array) {
        return mergeSort(array, 0, array.length - 1);
    }

    private static int[] mergeSort(int[] array, int start, int end) {

        int len = end - start + 1;
        if (len <= 0) {
            return new int[0];
        } else if (len == 1) {
            int[] result = new int[1];
            result[0] = array[start];
            return result;
        }
        int mid = (start + end) / 2;
        return merge(mergeSort(array, start, mid), mergeSort(array, mid + 1, end));
    }

    private static int[] merge(int[] array1, int[] array2) {

        int size1 = array1.length;
        int size2 = array2.length;
        if (size1 == 0) {
            return array2;
        } else if (size2 == 0) {
            return array1;
        }
        int index = 0;
        int index1 = 0;
        int index2 = 0;
        int[] result = new int[size1 + size2];
        while (index1 < size1 && index2 < size2) {
            if (array1[index1] <= array2[index2]) {
                result[index] = array1[index1];
                index1++;
            } else {
                result[index] = array2[index2];
                index2++;
            }
            index++;
        }
        if (index1 == size1 && index2 < size2) {
            while (index2 < size2) {
                result[index++] = array2[index2++];
            }
        } else if (index1 < size1 && index2 == size2) {
            while (index1 < size1) {
                result[index++] = array1[index1++];
            }
        }
        return result;
    }

    public static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int x = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > x) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = x;
        }
    }

    public static void insertionSort(List<Integer> array) {

        for (int i = 1; i < array.size(); i++) {
            int x = array.get(i);
            int j = i;
            while (j > 0 && array.get(j - 1) > x) {
                array.remove(j);
                array.add(j, array.get(j - 1));
                j--;
            }
            array.remove(j);
            array.add(j, x);
        }
    }

    public static void selectionSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int index = i;
            int min = array[index];
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    index = j;
                }
            }
            if (i != index) {
                swap(array, i, index);
            }
        }
    }

    public static void bubbleSort(int[] array) {

        int n = array.length;
        boolean swapped;
        do {
            int newN = 0;
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                    newN = i + 1;
                }
            }
            n = newN;
        } while (swapped);
    }

    public static void bucketSort(int[] array) {

        if (array.length <= 1) {
            return;
        }
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        if (max == min) {
            return;
        }
        int len = (max - min) < array.length ? 1 : (max - min) / array.length;
        int size = (max - min) / len + 1;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>();
        for (int i = 0; i < size; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        for (int anArray : array) {
            buckets.get((anArray - min) / len).add(anArray);
        }
        for (int i = 0; i < size; i++) {
            if (!buckets.get(i).isEmpty()) {
                insertionSort(buckets.get(i));
            }
        }
        int index = 0;
        for (int i = 0; i < size; i++) {
            List<Integer> list = buckets.get(i);
            if (!list.isEmpty()) {
                for (Integer aList : list) {
                    array[index++] = aList;
                }
            }
        }
    }

    public static void radixSort(int[] array) {

        List<List<Integer>> radix = new ArrayList<List<Integer>>();
        for (int i = 0; i < 10; i++) {
            radix.add(new ArrayList<Integer>());
        }
        List<Integer> pos = new ArrayList<Integer>();
        List<Integer> neg = new ArrayList<Integer>();
        for (int anArray : array) {
            if (anArray >= 0) {
                pos.add(anArray);
            } else {
                neg.add(anArray);
            }
        }
        boolean allZero;
        int mod = 10;
        int dev = 1;
        do {
            allZero = true;
            for (int posNum : pos) {
                int num = (posNum % mod) / dev;
                radix.get(num).add(posNum);
                if (num != 0) {
                    allZero = false;
                }
            }
            mod *= 10;
            dev *= 10;
            pos.clear();
            for (List<Integer> aRadix : radix) {
                while (!aRadix.isEmpty()) {
                    pos.add(aRadix.remove(0));
                }
            }
        } while (!allZero);
        mod = 10;
        dev = 1;
        do {
            allZero = true;
            for (int negNum : neg) {
                int num = (-negNum % mod) / dev;
                radix.get(num).add(negNum);
                if (num != 0) {
                    allZero = false;
                }
            }
            mod *= 10;
            dev *= 10;
            neg.clear();
            for (List<Integer> aRadix : radix) {
                while (!aRadix.isEmpty()) {
                    neg.add(aRadix.remove(0));
                }
            }
        } while (!allZero);
        int index = 0;
        for (int i = neg.size() - 1; i >= 0; i--) {
            array[index++] = neg.get(i);
        }
        for (Integer po : pos) {
            array[index++] = po;
        }
    }

    public static void heapSort(int[] array) {

        if (array.length <= 1) {
            return;
        }
        heapify(array);
        for (int i = array.length - 1; i >= 0; i--) {
            siftdown(array, 0, i);
            swap(array, 0, i);
        }
    }

    private static void heapify(int[] array) {
        int len = array.length;
        int start = (len - 2) / 2;
        while (start >= 0) {
            siftdown(array, start, array.length - 1);
            start--;
        }
    }

    private static void siftdown(int[] array, int start, int end) {

        int root = start;
        int leftChild = 2 * root + 1;
        while (leftChild <= end) {
            int swap = root;
            if (array[swap] < array[leftChild]) {
                swap = leftChild;
            }
            if (leftChild + 1 <= end && array[swap] < array[leftChild + 1]) {
                swap = leftChild + 1;
            }
            if (swap == root) {
                return;
            } else {
                swap(array, root, swap);
                root = swap;
                leftChild = 2 * root + 1;
            }
        }
    }


    public static void main(String[] args) {

        {
            int[] A = {5, 5, 2, 5, 2, 1, 8, 9, 6, 5, 5};
            quickSort(A);
            PRINT(A);
        }
        {
            int[] A = {5, 4, 6, 2, 8, 1, 2, 9, 1, -10, 5};
            PRINT(mergeSort(A));
        }
        {
            int[] A = {5, 4, 6, 2, 8, 1, 2, 9, 1, -10, 5};
            insertionSort(A);
            PRINT(A);
        }
        {
            int[] A = {5, 4, 6, 2, 8, 1, 2, 9, 1, -10, 5};
            selectionSort(A);
            PRINT(A);
        }
        {
            int[] A = {5, 4, 6, 2, 8, 1, 2, 9, 1, -10, 5};
            bubbleSort(A);
            PRINT(A);
        }
        {
            int[] A = {5, 4, 6, 2, 8, 1, 2, 9, 1, -10, 5};
            bucketSort(A);
            PRINT(A);
        }
        {
            int[] A = {5, 4, 6, 2, -8, 1, 2, 9, 1, -10, 5};
            radixSort(A);
            PRINT(A);
        }
        {
            int[] A = {5, 4, 6, 2, -8, 1, 2, 9, 1, -10, 5};
            heapSort(A);
            PRINT(A);
        }

    }

    public static void PRINT(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
