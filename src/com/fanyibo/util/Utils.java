/*
 *    Copyright   2014
 *     Filename : Utils
 *      Project : TestProject
 *   Created by : fanyibo on 12/9/14 10:42 PM
 */
package com.fanyibo.util;

import java.util.Arrays;

public class Utils {


    public static boolean isDigit(char c) {


        int asInt = (int) c;
        if (asInt < 48 || asInt > 57) {
            return false;
        }
        return true;
    }

    /**
     * implement atoi to convert a string to an integer.
     * Hint: Carefully consider all possible input cases.
     * If you want a challenge, please do not see below and ask yourself
     * what are the possible input cases.
     * Notes: It is intended for this problem to be specified vaguely
     * (ie, no given input specs). You are responsible to gather all
     * the input requirements up front.
     * spoilers alert... click to show requirements for atoi.
     * Requirements for atoi:
     * The function first discards as many whitespace characters as necessary until the first non-whitespace
     * character is found. Then, starting from this character, takes an optional initial plus or minus sign followed
     * by as many numerical digits as possible, and interprets them as a numerical value.
     * The string can contain additional characters after those that form the integral number, which are ignored and
     * have no effect on the behavior of this function.
     * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such
     * sequence exists because either str is empty or it contains only whitespace characters, no conversion is
     * performed.
     * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range
     * of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
     * https://oj.leetcode.com/problems/string-to-integer-atoi/
     */
    public static int atoi(String str) {

        if (str == null || str.isEmpty()) {
            return 0;
        }
        int result = 0;
        boolean meetSpace = false;
        boolean meetValid = false;
        boolean isNegative = false;
        boolean symbol = false;
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                if (meetValid) {
                    break;
                }
                if (!meetSpace) {
                    meetSpace = true;
                } else if (meetSpace && !meetValid) {

                } else {
                    break;
                }
                continue;
            }
            meetValid = true;
            int add = 0;
            if (c == '-') {
                if (symbol) {
                    break;
                }
                isNegative = true;
                symbol = true;
            } else if (c == '+') {
                if (symbol) {
                    break;
                }
                symbol = true;
            } else if (c == '0') {
                add = 0;
            } else if (c == '1') {
                add = 1;
            } else if (c == '2') {
                add = 2;
            } else if (c == '3') {
                add = 3;
            } else if (c == '4') {
                add = 4;
            } else if (c == '5') {
                add = 5;
            } else if (c == '6') {
                add = 6;
            } else if (c == '7') {
                add = 7;
            } else if (c == '8') {
                add = 8;
            } else if (c == '9') {
                add = 9;
            } else {
                return result;
            }

            if (result < (Integer.MIN_VALUE + add) / 10) {
                return Integer.MIN_VALUE;
            } else if (result > (Integer.MAX_VALUE - add) / 10) {
                return Integer.MAX_VALUE;
            }
            result = isNegative ? result * 10 - add : result * 10 + add;
        }
        return result;
    }

    /**
     * One transaction
     */
    public static int maxProfit_0(int[] prices) {

        int length = prices.length;
        if (length < 2) {
            return 0;
        } else if (length == 2) {
            int gap = prices[1] - prices[0];
            return gap < 0 ? 0 : gap;
        }
        int max_diff = prices[1] - prices[0];
        int min_elem = prices[0];
        for (int i = 1; i < length; i++) {
            int gap = prices[i] - min_elem;
            if (gap > max_diff) {
                max_diff = gap;
            }
            if (prices[i] < min_elem) {
                min_elem = prices[i];
            }
        }
        return max_diff < 0 ? 0 : max_diff;
    }

    public static int maxProfit_0(int[] prices, int start, int end) {

        int length = end - start + 1;
        if (length < 2) {
            return 0;
        } else if (length == 2) {
            int gap = prices[end] - prices[start];
            return gap < 0 ? 0 : gap;
        }
        int max_diff = prices[end] - prices[start];
        int min_elem = prices[start];
        for (int i = start; i <= end; i++) {
            int gap = prices[i] - min_elem;
            if (gap > max_diff) {
                max_diff = gap;
            }
            if (prices[i] < min_elem) {
                min_elem = prices[i];
            }
        }
        return max_diff < 0 ? 0 : max_diff;
    }

    private static int maxSubSum(int[] diffs, int start, int end) {

        int max = 0;
        int cur = 0;
        for (int i = start; i <= end; i++) {

            if (cur > 0) {
                cur += diffs[i];
            } else {
                cur = diffs[i];
            }

            // Update max sum, if needed
            if (cur > max) {
                max = cur;
            }
        }
        return max > 0 ? max : 0;
    }

    /**
     * As many transactions as possible
     */
    public static int maxProfit_1(int[] prices) {

        int length = prices.length;
        if (length < 2) {
            return 0;
        } else if (length == 2) {
            int gap = prices[1] - prices[0];
            return gap < 0 ? 0 : gap;
        }

        // Initialize diff, current sum and max sum
        int diff = prices[1] - prices[0];
        int curr_sum = diff;
        int max_sum = curr_sum;
        int sec_sum = 0;

        for (int i = 1; i < length - 1; i++) {
            diff = prices[i + 1] - prices[i];
            if (curr_sum > 0) {
                curr_sum += diff;
            } else {
                curr_sum = diff;
            }
            if (curr_sum > max_sum) {
                max_sum = curr_sum;
            } else {
                if (curr_sum > sec_sum) {
                    sec_sum = curr_sum;
                }
            }
        }

        return max_sum < 0 ? 0 : max_sum;
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete at most two transactions.
     * Note:
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     */
    public static int maxProfit_2(int[] prices) {

        //        int length = prices.length;
        //        int[] profits = new int[length];
        //        int maxSingleProfit = 0;
        //
        //        for (int j = 1; j < length; j++) {
        //            int maxColumn = 0;
        //            for (int i = 0; i < j - 1; i++) {
        //                int profit = prices[j] - prices[i];
        //                maxSingleProfit = Math.max(profit, maxSingleProfit);
        //                maxColumn = Math.max(maxColumn, profit);
        //            }
        //            profits[j] = maxColumn;
        //        }
        //        int maxProfit = 0;
        //        for (int i = 0; i < length; i++) {
        //            maxProfit = Math.max(singleProfits[0][i], maxProfit);
        //        }
        //
        //        return maxProfit;
        return 0;
    }

    public static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     * For example, the following two linked lists:
     * A:          a1 → a2
     * ↘
     * c1 → c2 → c3
     * ↗
     * B:     b1 → b2 → b3
     * begin to intersect at node c1.
     * Notes:
     * If the two linked lists have no intersection at all, return null.
     * The linked lists must retain their original structure after the function returns.
     * You may assume there are no cycles anywhere in the entire linked structure.
     * Your code should preferably run in O(n) time and use only O(1) memory.
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        /*
        Solution #1:
         */
        //        int iA = 0;
        //        int iB = 0;
        //
        //        ListNode tmp = headA;
        //        while (tmp != null) {
        //            tmp = tmp.next;
        //            iA++;
        //        }
        //
        //        tmp = headB;
        //        while (tmp != null) {
        //            tmp = tmp.next;
        //            iB++;
        //        }
        //
        //        int diff = iA - iB;
        //        ListNode longHead  = headA;
        //        ListNode shortHead = headB;
        //        if (diff < 0) {
        //            diff = 0 - diff;
        //            shortHead = longHead;
        //            longHead = headB;
        //        }
        //
        //        while (diff > 0) {
        //            longHead = longHead.next;
        //            --diff;
        //        }
        //
        //        ListNode tmp1 = longHead;
        //        ListNode tmp2 = shortHead;
        //        while (tmp1 != null && tmp2 != null && tmp1.val != tmp2.val) {
        //            tmp1 = tmp1.next;
        //            tmp2 = tmp2.next;
        //        }
        //        return tmp1;

        /*
        Solution #2
         */
        if (headA == null || headB == null) {
            return null;
        }

        ListNode end = headA;
        while (end != null) {
            if (end.next == null) {
                end.next = headA;
                break;
            } else {
                end = end.next;
            }
        }

        ListNode tmp1 = headA;
        ListNode tmp2 = headB;
        while (tmp2 != null && tmp1 != null && tmp1.val != tmp2.val) {
            tmp1 = tmp1.next;
            if (tmp2.val == end.val) {
                tmp2 = headB;
            } else {
                tmp2 = tmp2.next;
            }
        }
        return tmp1;
    }


    public static void printlist(ListNode head) {
        ListNode tmp = head;
        while (tmp != null) {
            System.out.print(tmp.val + " -> ");
            tmp = tmp.next;
        }
        System.out.print("null");
    }

    public static ListNode reverse(ListNode head) {

        ListNode tmp = head;
        ListNode tmp1 = head.next;
        ListNode tmp2 = head.next;
        tmp.next = null;

        while (tmp1 != null) {
            tmp2 = tmp1.next;
            tmp1.next = tmp;
            tmp = tmp1;
            tmp1 = tmp2;
        }

        return tmp;
    }


    /**
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     */

    public static class MinStack {

        private final int initial_capability = 1000;

        private int[] stack = new int[initial_capability];

        private int capability = initial_capability;
        private int size       = 0;
        private int min;

        public void push(int x) {
            if (size == 0) {
                min = x;
            }

            if (size + 1 > capability) {
                capability = capability << 1;
                stack = Arrays.copyOf(stack, capability);
            }
            stack[size++] = x;
            min = x < min ? x : min;
        }

        public void pop() {
            if (size > 0) {
                int _top = stack[size - 1];
                stack[size - 1] = '\0';
                size--;
                if (_top == min) {
                    findMin();
                }
            }
        }

        public int top() {
            int topV = 0;
            if (size > 0) {
                topV = stack[size - 1];
            }
            return topV;
        }

        public int getMin() {
            return min;
        }

        private void findMin() {
            if (size == 0) {
                min = 0;
                return;
            }
            min = stack[0];
            for (int i = 0; i < size; i++) {
                min = min<stack[i] ? min : stack[i];
            }
        }

        public void print() {

            for (int i = 0; i < size; i++) {
                System.out.println(stack[i]);
            }
        }
    }

    public static void main(String[] args) {

        MinStack stack = new MinStack();
        stack.push(-1);
        System.out.println(stack.top());
        System.out.println(stack.getMin());



        //        ListNode n1 = new ListNode(1);
        //        ListNode n2 = new ListNode(2);
        //        ListNode n3 = new ListNode(3);
        //        ListNode n4 = new ListNode(4);
        //        ListNode n5 = new ListNode(5);
        //        ListNode n6 = new ListNode(6);
        //        ListNode n7 = new ListNode(7);
        //
        //        n1.next = n2;
        //        n2.next = n3;
        //        n3.next = n6;
        //        n6.next = n7;
        //
        //        n4.next = n5;
        //        n5.next = n6;
        //        n6.next = n7;
        //
        //        n7.next = null;
        //
        //
        //        ListNode tmp = getIntersectionNode(n1, n4);
        //        System.out.println(tmp.val);
        //ListNode tmp = reverse(n1);
        //printlist(tmp);


        //        Random random = new Random(new Date().getTime());
        //        int size = 99999;
        //        int prices[] = new int[size];
        //        for (int i = 0; i < size; ++i) {
        //            prices[i] = random.nextInt();
        //        }
        //        long timeA = System.currentTimeMillis();
        //        System.out.println(maxProfit_1(prices));
        //        long timeB = System.currentTimeMillis();
        //        System.out.println("Elapsed time: " + (timeB - timeA));
    }
}

