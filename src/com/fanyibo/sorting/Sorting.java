package com.fanyibo.sorting;

import com.fanyibo.util.ListNode;

public class Sorting {

    //    public static ListNode quickSort(ListNode head) {
    //
    //    }
    //
    //    public static ListNode mergeSort(ListNode head) {
    //
    //    }
    //
    //    public static ListNode heapSort(ListNode head) {
    //
    //    }
    //
    public static ListNode insertionSort(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head;
        ListNode temp1 = head.next;
        ListNode father1 = head;
        while (temp1 != null) {

            ListNode father2 = null;
            ListNode temp2 = newHead;
            while (temp2.val < temp1.val) {
                father2 = temp2;
                temp2 = temp2.next;
            }
            if (temp2.val > temp1.val || (temp2 != temp1 && temp2.val == temp1.val)) {
                father1.next = temp1.next;
                if (father2 == null) {
                    newHead = temp1;
                } else {
                    father2.next = temp1;
                }
                temp1.next = temp2;
                temp1 = father1.next;
            } else {
                father1 = temp1;
                temp1 = temp1.next;
            }
        }
        return newHead;
    }
    //
    //    public static ListNode selectionSort(ListNode head) {
    //
    //    }
    //
    //    public static ListNode bubbleSort(ListNode head) {
    //
    //    }
    //
    //    public static ListNode binarytreeSort(ListNode head) {
    //
    //    }
    //
    //    public static ListNode introSort(ListNode head) {
    //
    //    }
    //
    //    public static ListNode timSort(ListNode head) {
    //
    //    }
    //
    //    public static ListNode cubeSort(ListNode head) {
    //
    //    }


}
