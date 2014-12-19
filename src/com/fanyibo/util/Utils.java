/*
 *    Copyright   2014
 *     Filename : Utils
 *      Project : TestProject
 *   Created by : fanyibo on 12/9/14 10:42 PM
 */
package com.fanyibo.util;

import java.util.*;

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

        if (head == null) {
            System.out.println("null");
            return;
        }
        StringBuilder builder = new StringBuilder();
        ListNode tmp = head;
        while (tmp != null) {
            builder.append(tmp.val);
            if (tmp.next != null) {
                builder.append(" -> ");
            }
            tmp = tmp.next;
        }
        System.out.println(builder.toString());
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
                min = min < stack[i] ? min : stack[i];
            }
        }

        public void print() {

            for (int i = 0; i < size; i++) {
                System.out.println(stack[i]);
            }
        }
    }


    /**
     * Given an index k, return the kth row of the Pascal's triangle.
     * For example, given k = 3,
     * Return [1,3,3,1].
     * Note:
     * Could you optimize your algorithm to use only O(k) extra space?
     * 0 0 0 1
     * 0 0 1 1
     * 0 1 2 1
     * 1 3 3 1
     */
    public static List<Integer> getRow(int rowIndex) {

        int size = rowIndex + 1;
        Integer[] array = new Integer[size];
        array[size - 1] = 1;
        for (int i = size - 2; i >= 0; i--) {
            array[i] = 0;
        }

        for (int i = 1; i <= rowIndex; i++) {
            int old = -1;
            for (int j = size - 2; j >= size - i - 1; j--) {
                if (j == size - 2) {
                    old = array[j];
                    array[j] += (array[j + 1]);
                } else {
                    int temp = array[j];
                    array[j] += old;
                    old = temp;
                }
            }
        }

        return Arrays.asList(array);
    }

    /**
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the
     * values along the path equals the given sum.
     * For example:
     * Given the below binary tree and sum = 22,
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     */

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }

        int val = root.val;
        sum -= val;

        if (sum == 0 && root.left == null && root.right == null) {
            return true;
        } else {
            return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
        }
    }

    /**
     * Given a binary tree, find its minimum depth.
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf
     * node.
     */
    public static int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else if (root.left == null && root.right != null) {
            return 1 + minDepth(root.right);
        } else if (root.left != null && root.right == null) {
            return 1 + minDepth(root.left);
        } else {
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        }
    }

    public static int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else if (root.left == null && root.right != null) {
            return 1 + maxDepth(root.right);
        } else if (root.left != null && root.right == null) {
            return 1 + maxDepth(root.left);
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    public static final class MaxMinDepth {

        int max = 0;
        int min = 0;

        public MaxMinDepth(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public static MaxMinDepth maxminDepth(TreeNode root) {

        if (root == null) {
            return new MaxMinDepth(0, 0);
        } else if (root.left == null && root.right == null) {
            return new MaxMinDepth(1, 1);
        } else if (root.left == null && root.right != null) {
            MaxMinDepth temp = maxminDepth(root.right);
            return new MaxMinDepth(1 + temp.max, 1);
        } else if (root.left != null && root.right == null) {
            MaxMinDepth temp = maxminDepth(root.left);
            return new MaxMinDepth(1 + temp.max, 1);
        } else {
            MaxMinDepth tempL = maxminDepth(root.left);
            MaxMinDepth tempR = maxminDepth(root.right);
            return new MaxMinDepth(1 + Math.max(tempL.max, tempR.max), 1 + Math.min(tempL.min, tempR.min));
        }
    }

    /**
     * Given a binary tree, determine if it is height-balanced.
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
     * subtrees of every node never differ by more than 1.
     */
    public static boolean isBalanced(TreeNode root) {

        if (root == null) {
            return true;
        }

        int leftL = maxDepth(root.left);
        int leftR = maxDepth(root.right);
        if (Math.abs(leftL - leftR) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    /**
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right,
     * level by level from leaf to root).
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its bottom-up level order traversal as:
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root != null) {

            LinkedList<TreeNode> bfsList1 = new LinkedList<TreeNode>();
            LinkedList<TreeNode> bfsList2 = new LinkedList<TreeNode>();

            bfsList1.push(root);

            LinkedList<TreeNode> freeList = bfsList2;
            LinkedList<TreeNode> busyList = bfsList1;

            while (!busyList.isEmpty() || !freeList.isEmpty()) {
                List<Integer> list = new ArrayList<Integer>();
                while (!busyList.isEmpty()) {

                    TreeNode temp = busyList.removeFirst();
                    list.add(temp.val);
                    if (temp.left != null) {
                        freeList.addLast(temp.left);
                    }
                    if (temp.right != null) {
                        freeList.addLast(temp.right);
                    }
                }
                result.add(list);

                LinkedList<TreeNode> tmp = freeList;
                freeList = busyList;
                busyList = tmp;
            }

            Collections.reverse(result);
        }
        return result;
    }


    /**
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * For example, this binary tree is symmetric:
     * 1
     * /   \
     * 2     2
     * / \   / \
     * 3   4 4   3
     * But the following is not:
     * 1
     * / \
     * 2   2
     * \   \
     * 3   3
     * Note:
     * Bonus points if you could solve it both recursively and iteratively.
     * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
     */

    private static boolean isListSymmetric(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        int size = list.size();
        int index1;
        int index2;
        if (size % 2 == 1) {
            index1 = ((size - 1) / 2) - 1;
            index2 = index1 + 1;
        } else {
            index1 = (size / 2) - 1;
            index2 = index1 + 1;
        }

        while (index1 >= 0 && index2 <= size - 1) {

            Integer left = list.get(index1);
            Integer right = list.get(index2);

            if ((left == null && right == null) || (left != null && right != null && left.equals(right))) {
                index1--;
                index2++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isSymmetric(TreeNode root) {

        if (root != null) {

            LinkedList<TreeNode> bfsList1 = new LinkedList<TreeNode>();
            LinkedList<TreeNode> bfsList2 = new LinkedList<TreeNode>();

            bfsList1.push(root.left);
            bfsList2.push(root.right);

            while (!bfsList1.isEmpty() && !bfsList2.isEmpty()) {

                TreeNode tempL = bfsList1.removeFirst();
                TreeNode tempR = bfsList2.removeFirst();

                if (tempL == null && tempR == null) {
                    continue;
                }
                if ((tempL != null && tempR == null) || (tempL == null && tempR != null)) {
                    return false;
                }

                if (tempL.val != tempR.val) {
                    return false;
                }

                bfsList1.addLast(tempL.left);
                bfsList1.addLast(tempL.right);

                bfsList2.addLast(tempR.right);
                bfsList2.addLast(tempR.left);
            }
        }
        return true;
    }

    /**
     * Given an input string, reverse the string word by word.
     * For example,
     * Given s = "the sky is blue",
     * return "blue is sky the".
     */
    public static String reverseWords(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }

        List<String> result = new ArrayList<String>();

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (c == ' ' && str.length() == 0) {
                continue;
            }
            if (c == ' ') {
                result.add(str.toString());
                str.delete(0, str.length());
            } else {
                str.append(c);
            }
        }
        if (str.length() > 0) {
            result.add(str.toString());
            str.delete(0, str.length());
        }

        int size = result.size();
        if (size > 0) {
            for (int i = size - 1; i > 0; i--) {
                str.append(result.get(i));
                str.append(" ");
            }
            str.append(result.get(0));
        }
        return str.toString();
    }

    /**
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     * You must do this in-place without altering the nodes' values.
     * For example,
     * Given {1,2,3,4}, reorder it to {1,4,2,3}.
     */
    public static void reorderList(ListNode head) {

        if (head == null) {
            return;
        }

        List<ListNode> list = new ArrayList<ListNode>();

        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        int size = list.size();

        if (size <= 1) {
            return;
        }

        int half = 0;
        int left = 0;
        if (size % 2 == 0) {
            half = (size / 2) - 1;
        } else {
            half = ((size - 1) / 2) - 1;
            left = half + 1;
        }


        for (int i = 0; i <= half; i++) {
            list.get(i).next = list.get(size - 1 - i);
            list.get(size - 1 - i).next = (i == size - 2 - i) ? null : list.get(i + 1);
        }
        if (left != 0) {
            list.get(left).next = null;
        }

    }

    /**
     * A peak element is an element that is greater than its neighbors.
     * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     * You may imagine that num[-1] = num[n] = -∞.
     * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
     * click to show spoilers.
     */
    public static int findPeakElement(int[] num) {

        List<Integer> indexs = findPeakElementIndex(num);
        if (indexs.size() == 0) {
            return 0;
        }
        return indexs.get(0);
    }

    public static List<Integer> findPeakElementIndex(int[] num) {

        List<Integer> indexs = new ArrayList<Integer>();

        int size = num.length;
        if (size == 0) {
            return indexs;
        }
        if (size == 1) {
            indexs.add(0);
            return indexs;
        }

        for (int i = 1; i < size; i++) {
            if (i == 1 && num[0] > num[1]) {
                indexs.add(0);
            } else if (i == size - 1) {
                if (num[i] > num[i - 1]) {
                    indexs.add(i);
                }
            } else {
                if (num[i] > num[i + 1] && num[i] > num[i - 1]) {
                    indexs.add(i);
                }
            }
        }
        return indexs;
    }

    /**
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * Follow up:
     * Can you solve it without using extra space?
     */
    public static ListNode detectCycle(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode temp1 = head;
        ListNode temp2 = head;

        while (temp1 != null && temp2 != null) {

            temp1 = temp1.next;
            if (temp2.next == null) {
                return null;
            } else {
                temp2 = temp2.next.next;
            }
            if (temp1 == temp2) {
                // have cycle
                temp1 = head;
                while (temp1 != temp2) {
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return temp1;
            }
        }
        return null;
    }

    /**
     * Compare two version numbers version1 and version2.
     * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
     * You may assume that the version strings are non-empty and contain only digits and the . character.
     * The . character does not represent a decimal point and is used to separate number sequences.
     * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level
     * revision of the second first-level revision.
     * Here is an example of version numbers ordering:
     * 0.1 < 1.1 < 1.2 < 13.37
     */
    public static List<String> splitVersionString(String version) {

        List<String> strs = new ArrayList<String>();
        StringBuilder strBuilder = new StringBuilder();
        int length = version.length();

        for (int i = 0; i < length; i++) {
            char c = version.charAt(i);
            if (c != '.') {
                strBuilder.append(c);
            } else {
                strs.add(strBuilder.toString());
                strBuilder.delete(0, length);
            }
        }
        strs.add(strBuilder.toString());
        strBuilder.delete(0, length);
        return strs;
    }


    public static int compareVersion(String version1, String version2) {

        List<String> list1 = splitVersionString(version1);
        List<String> list2 = splitVersionString(version2);

        int size1 = list1.size();
        int size2 = list2.size();

        int minSize = size1 < size2 ? size1 : size2;

        for (int i = 0; i < minSize; i++) {

            String str1 = list1.get(i);
            String str2 = list2.get(i);

            int a = Integer.parseInt(str1);
            int b = Integer.parseInt(str2);

            if (a < b) {
                return -1;
            } else if (a == b) {
                continue;
            } else {
                return 1;
            }
        }
        if (size1 < size2) {
            for (int i = size1; i < size2; i++) {
                String str = list2.get(i);
                int a = Integer.parseInt(str);
                if (a == 0) {
                    continue;
                } else {
                    return -1;
                }
            }
            return 0;
        } else if (size1 == size2) {
            return 0;
        } else {
            for (int i = size2; i < size1; i++) {
                String str = list1.get(i);
                int a = Integer.parseInt(str);
                if (a == 0) {
                    continue;
                } else {
                    return 1;
                }
            }
            return 0;
        }
    }

    /**
     * Given two binary trees, write a function to check if they are equal or not.
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null) {
            return q == null;
        }
        if (q == null) {
            return false;
        }

        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(p);
        stack2.push(q);
        while (!stack1.isEmpty() && !stack2.isEmpty()) {

            TreeNode temp1 = stack1.pop();
            TreeNode temp2 = stack2.pop();

            if (temp1 == null) {
                if (temp2 != null) {
                    return false;
                }
            } else if (temp2 == null) {
                return false;
            } else if (temp1.val == temp2.val) {
                stack1.push(temp1.left);
                stack1.push(temp1.right);
                stack2.push(temp2.left);
                stack2.push(temp2.right);
            } else {
                return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    /**
     * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
     * For example,
     * Given n = 3, there are a total of 5 unique BST's.
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     */
    public static int numTrees(int n) {

        if (n < 2) {
            return 1;
        }
        int[] nums = new int[n + 1];

        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                nums[i] += nums[j] * nums[i - j - 1];
            }

        }
        return nums[n];
    }

    /**
     * Given two sorted integer arrays A and B, merge B into A as one sorted array.
     * Note:
     * You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements
     * from B. The number of elements initialized in A and B are m and n respectively.
     */
    public static void merge(int A[], int m, int B[], int n) {


        int C[] = new int[m + n];

        int ia = 0;
        int ib = 0;
        for (int i = 0; i < m + n; i++) {

            if (ia >= m) {
                C[i] = B[ib++];
                continue;
            }
            if (ib >= n) {
                C[i] = A[ia++];
                continue;
            }

            int a = A[ia];
            int b = B[ib];

            if (a < b) {
                C[i] = a;
                ia++;
            } else if (a == b) {
                C[i++] = a;
                C[i] = b;
                ia++;
                ib++;
            } else {
                C[i] = b;
                ib++;
            }

        }

        for (int i = 0; i < m + n; i++) {
            A[i] = C[i];
        }
    }


    /**
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     * For example,
     * Given 1->1->2, return 1->2.
     * Given 1->1->2->3->3, return 1->2->3.
     */
    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode temp1 = head;
        ListNode temp2 = temp1.next;
        while (temp2 != null) {
            if (temp1.val == temp2.val) {
                temp1.next = temp2.next;
                temp2 = temp1.next;
            } else {
                temp1 = temp2;
                temp2 = temp2.next;
            }
        }
        return head;
    }

    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     */
    public static int climbStairs(int n) {

        int count[] = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            count[i] = count[i - 2] + count[i - 1];
        }
        return count[n];
    }

    /**
     * Given a non-negative number represented as an array of digits, plus one to the number.
     * The digits are stored such that the most significant digit is at the head of the list.
     */
    public static int[] plusOne(int[] digits) {

        int iDot = 0;
        for (iDot = 0; iDot < digits.length; iDot++) {
            if (digits[iDot] == '.') {
                break;
            }
        }
        if (iDot >= digits.length) {
            iDot = digits.length;
        }
        boolean markOne = false;
        for (int i = iDot - 1; i >= 0; i--) {

            int temp;
            if (i == iDot - 1) {
                temp = digits[i] + 1;
            } else {
                temp = markOne ? digits[i] + 1 : digits[i];
            }

            if (temp < 10) {
                digits[i] = temp;
                return digits;
            } else {
                digits[i] = temp - 10;
                markOne = true;
            }
        }
        if (markOne) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        }
        return digits;
    }

    /**
     * Given two binary strings, return their sum (also a binary string).
     * For example,
     * a = "11"
     * b = "1"
     * Return "100".
     */
    public static String addBinary(String a, String b) {

        String longStr, shortStr;
        int length = a.length() > b.length() ? a.length() : b.length();
        if (length == a.length()) {
            longStr = a;
            shortStr = b;
        } else {
            longStr = b;
            shortStr = a;
        }

        StringBuilder result = new StringBuilder();

        boolean markOne = false;
        for (int i = shortStr.length() - 1; i >= 0; i--) {

            char c1 = longStr.charAt(i + longStr.length() - shortStr.length());
            char c2 = shortStr.charAt(i);

            int i1 = (c1 == '1') ? 1 : 0;
            int i2 = (c2 == '1') ? 1 : 0;

            if (markOne) {
                i1 += (i2 + 1);
            } else {
                i1 += i2;
            }
            if (i1 == 0 || i1 == 1) {
                markOne = false;
            } else {
                markOne = true;
                i1 = i1 - 2;
            }
            result.append(i1);

        }
        for (int i = longStr.length() - shortStr.length() - 1; i >= 0; i--) {

            char c1 = longStr.charAt(i);
            int i1 = (c1 == '1') ? 1 : 0;

            if (markOne) {
                i1 += 1;
            }
            if (i1 == 0 || i1 == 1) {
                markOne = false;
            } else {
                markOne = true;
                i1 = i1 - 2;
            }
            result.append(i1);
        }
        if (markOne) {
            result.append(1);
        }

        return result.reverse().toString();
    }


    /**
     * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together
     * the nodes of the first two lists.
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = l1.val <= l2.val ? l1 : l2;

        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode temp3;

        while (temp1 != null && temp2 != null) {

            if (temp1.val < temp2.val) {

                temp3 = temp1;
                while (temp3 != null && temp3.val <= temp2.val) {
                    temp1 = temp3;
                    temp3 = temp3.next;
                }
                temp1.next = temp2;
                temp1 = temp3;

            } else {

                temp3 = temp2;
                while (temp3 != null && temp3.val <= temp1.val) {
                    temp2 = temp3;
                    temp3 = temp3.next;
                }
                temp2.next = temp1;
                temp2 = temp3;

            }
        }

        return head;
    }

    /**
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of
     * last word in the string.
     * If the last word does not exist, return 0.
     * Note: A word is defined as a character sequence consists of non-space characters only.
     * For example,
     * Given s = "Hello World",
     * return 5.
     */
    public static int lengthOfLastWord(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        List<Integer> lengths = new ArrayList<Integer>();
        int length = 0;
        boolean meetWord = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (meetWord) {
                    lengths.add(length);
                    length = 0;
                    meetWord = false;
                }
            } else {
                length++;
                meetWord = true;
            }
        }
        if (meetWord) {
            lengths.add(length);
        }
        if (lengths.size() == 0) {
            return 0;
        }
        return lengths.get(lengths.size() - 1);
    }


    /**
     * The count-and-say sequence is the sequence of integers beginning as follows:
     * 1, 11, 21, 1211, 111221, ...
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     * Given an integer n, generate the nth sequence.
     * Note: The sequence of integers will be represented as a string.
     */
    public static String countAndSay(int n) {

        StringBuilder builder = new StringBuilder();
        String str = "1";
        for (int i = 1; i < n; i++) {
            int length = str.length();
            int count = 0;
            char lastC = '\0';
            for (int j = 0; j < length; j++) {
                char currC = str.charAt(j);
                if (lastC != '\0' && currC == lastC) {
                    count++;
                } else if (lastC != '\0' && currC != lastC) {
                    builder.append(count);
                    builder.append(lastC);
                    lastC = currC;
                    count = 1;
                } else {
                    lastC = currC;
                    count++;
                }
            }
            if (lastC != '\0') {
                builder.append(count);
                builder.append(lastC);
            }
            str = builder.toString();
            builder.delete(0, builder.length());
        }
        return str;
    }

    /**
     * Implement strStr().
     * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Update (2014-11-02):
     * The signature of the function had been updated to return the index instead of the pointer. If you still see
     * your function signature returns a char * or String, please click the reload button to reset your code
     * definition.
     */
    public static int strStr(String haystack, String needle) {

        int mainLength = haystack.length();
        int targetLength = needle.length();

        if (targetLength == 0) {
            return 0;
        }
        if (mainLength < targetLength) {
            return -1;
        }

        int mainIndex = 0;
        int targetIndex = 0;
        for (mainIndex = 0; mainIndex <= mainLength - targetLength; mainIndex++) {
            for (targetIndex = 0; targetIndex < targetLength; targetIndex++) {
                if (haystack.charAt(mainIndex + targetIndex) != needle.charAt(targetIndex)) {
                    break;
                }
            }
            if (targetIndex == targetLength) {
                return mainIndex;
            }
        }
        return -1;
    }

    /**
     * Given an array and a value, remove all instances of that value in place and return the new length.
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     */
    public static int removeElement(int[] A, int elem) {

        if (A == null) {
            return 0;
        }
        int length = A.length;
        if (length == 0) {
            return 0;
        }

        int index1 = 0;
        int index2 = length - 1;
        while (index1 != index2) {

            if (A[index1] == elem) {
                while (index2 > index1 && A[index2] == elem) {
                    index2--;
                }
                if (index2 == index1) {
                    break;
                }
                int temp = A[index2];
                A[index2] = A[index1];
                A[index1] = temp;
            }
            index1++;
        }
        if (A[index1] != elem) {
            index1++;
        }
        if (index1 > 0) {
            return index1;
        } else if (index1 == 0 && A[0] == elem) {
            return 0;
        }
        return A.length;
    }


    /**
     * Sort a linked list in O(n log n) time using constant space complexity.
     */
    // TODO sorting
    public static ListNode sortList(ListNode head) {

        return null;
    }

    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     * Some examples:
     * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     */
    public static int evalRPN(String[] tokens) {

        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int result = 0;

        Stack<Integer> stack = new Stack<Integer>();
        for (String token : tokens) {
            if (token.equals("+")) {
                right = stack.pop();
                left = stack.pop();
                result = left + right;
                stack.push(result);
            } else if (token.equals("-")) {
                right = stack.pop();
                left = stack.pop();
                result = left - right;
                stack.push(result);
            } else if (token.equals("*")) {
                right = stack.pop();
                left = stack.pop();
                result = left * right;
                stack.push(result);
            } else if (token.equals("/")) {
                right = stack.pop();
                left = stack.pop();
                result = left / right;
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }


    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,2,3].
     * Note: Recursive solution is trivial, could you do it iteratively?
     */
    public static List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }


    /**
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated
     * sequence of one or more dictionary words.
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * Return true because "leetcode" can be segmented as "leet code".
     */
    private static HashMap<String, Boolean> results = new HashMap<String, Boolean>();

    public static boolean wordBreak(String s, Set<String> dict) {

        if (dict == null || dict.size() == 0 || s == null || s.length() == 0) {
            return false;
        }

        for (String word : dict) {

            Boolean value = results.get(word);
            if (value != null && value == false) {
                continue;
            }

            int i;
            for (i = 0; i <= s.length() - word.length(); i++) {

                int j;
                for (j = 0; j < word.length(); j++) {
                    if (s.charAt(i + j) != word.charAt(j)) {
                        break;
                    }
                }
                if (j == word.length()) {

                    if (i == 0) {
                        if (i + word.length() >= s.length()) {
                            return true;
                        } else if (wordBreak(s.substring(i + word.length()), dict)) {
                            return true;
                        }
                    } else {
                        if (wordBreak(s.substring(0, i), dict)) {
                            if (i + word.length() >= s.length()) {
                                return true;
                            } else if (wordBreak(s.substring(i + word.length()), dict)) {
                                return true;
                            }
                        }
                    }
                }
            }
            results.put(word, false);
        }
        return false;
    }


    /**
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new
     * length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * For example,
     * Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     */
    public static int removeDuplicates(int[] A) {

        if (A == null) {
            return 0;
        }
        int length = A.length;
        if (length < 2) {
            return length;
        }

        int index1 = 0;
        int index2 = 1;
        int size = length;
        while (index1 < size && index2 < length) {

            while (index2 < length && A[index2] == A[index1]) {
                index2++;
                size--;
            }
            if (index2 < length && index2 - index1 > 1) {
                int temp = A[index1 + 1];
                A[index1 + 1] = A[index2];
                A[index2] = temp;
            }
            index1++;
            index2++;
        }
        return size;
    }

    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
     * valid.
     * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     */
    public static boolean isValid(String s) {

        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                char p = stack.peek();
                if (c == '}' && p == '{') {
                    stack.pop();
                } else if (c == ']' && p == '[') {
                    stack.pop();
                } else if (c == ')' && p == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * Given a roman numeral, convert it to an integer.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
    private static final HashMap<Character, Integer> ROMAN_NUM_DICT = new HashMap<Character, Integer>();

    static {
        ROMAN_NUM_DICT.put('I', 1);
        ROMAN_NUM_DICT.put('V', 5);
        ROMAN_NUM_DICT.put('X', 10);
        ROMAN_NUM_DICT.put('L', 50);
        ROMAN_NUM_DICT.put('C', 100);
        ROMAN_NUM_DICT.put('D', 500);
        ROMAN_NUM_DICT.put('M', 1000);
    }

    public static int romanToInt(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return ROMAN_NUM_DICT.get(s.charAt(0));
        }
        int result = 0;
        int i = 0;
        while (i < s.length() - 1) {//MDCXCV
            char c1 = s.charAt(i);
            char c2 = s.charAt(i + 1);

            int i1 = ROMAN_NUM_DICT.get(c1);
            int i2 = ROMAN_NUM_DICT.get(c2);

            if (i1 >= i2) {
                result += i1;
                if (i == s.length() - 2) {
                    result += i2;
                }
                i++;
            } else {
                result += (i2 - i1);
                i += 2;
                if (i == s.length() - 1) {
                    result += ROMAN_NUM_DICT.get(s.charAt(i));
                }
            }
        }
        return result;
    }

    /**
     * Determine whether an integer is a palindrome. Do this without extra space.
     * click to show spoilers.
     * Some hints:
     * Could negative integers be palindromes? (ie, -1)
     * If you are thinking of converting the integer to string, note the restriction of using extra space.
     * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know
     * that
     * the reversed integer might overflow. How would you handle such case?
     * There is a more generic way of solving this problem.
     * 12321
     */
    public static boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }
        int last = x - ((x / 10) * 10);
        int ten = 10;
        int left = x / ten;
        int size = 1;
        while (left > 10) {
            size++;
            ten = (int) Math.pow(10, size);
            left = x / ten;
        }
        int first = (x - (x % (int) Math.pow(10, size))) / ((int) Math.pow(10, size));
        if (first == last) {
            if (size <= 2) {
                return true;
            } else {

                int newX = (x % (int) Math.pow(10, size)) / 10;
                size = size - 2;
                if (newX == 0) {
                    return true;
                } else if (newX / (int) Math.pow(10, size) > 0) {
                    return isPalindrome(newX);
                } else {
                    int zeroBefore = size;
                    ten = (int) Math.pow(10, zeroBefore);
                    while (newX / ten == 0) {
                        zeroBefore--;
                        ten /= 10;
                    }
                    zeroBefore = size - zeroBefore;
                    int zeroAfter = 0;
                    ten = 10;
                    left = newX % ten;
                    while (left == 0) {
                        zeroAfter++;
                        ten *= 10;
                        left = newX % ten;
                    }
                    if (zeroAfter != zeroBefore) {
                        return false;
                    } else {
                        newX = newX / (ten / 10);
                        return isPalindrome(newX);
                    }
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Reverse digits of an integer.
     * Example1: x = 123, return 321
     * Example2: x = -123, return -321
     * click to show spoilers.
     * Have you thought about this?
     * Here are some good questions to ask before coding. Bonus points for you if you have already thought through
     * this!
     * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
     * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse
     * of 1000000003 overflows. How should you handle such cases?
     * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     * Update (2014-11-10):
     * Test cases had been added to test the overflow behavior.
     */
    public static int reverse(int x) {

        if (x == Integer.MIN_VALUE) {
            return 0;
        }
        boolean negative = false;
        if (x < 0) {
            negative = true;
            x = -x;
        }
        StringBuilder builder = new StringBuilder();
        boolean meetUnZero = false;
        while (x > 0) {
            int last = x - (x / 10) * 10;

            if (!meetUnZero) {
                if (last > 0) {
                    meetUnZero = true;
                    builder.append(last);
                }
            } else {
                builder.append(last);
            }

            x = (x - last) / 10;
        }
        int size = builder.length();
        if (size > 10) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < size; i++) {
            int digit = (int) builder.charAt(i) - 48;
            if (i == 9) {
                if ((result > 214748364) ||
                        (result == 214748364 && digit > 7 && !negative) ||
                        (result == 214748364 && digit > 8 && negative)) {
                    return 0;
                }
            }
            result = 10 * result + digit;
        }
        return negative ? -result : result;
    }

    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want
     * to display this pattern in a fixed font for better legibility)
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     */
    public static String convert(String s, int nRows) {

        if (nRows == 1) {
            return s;
        }

        int length = s.length();
        int half = (int)((double)nRows) / 2;

        int a = (int) Math.ceil(0.25 * (1.0 + (((double) length) / half)));
        int b = 2 * a - 1;

        int index = 0;
        char matrix[][] = new char[nRows][Math.max(a, b)];
        for (int i = 0; i < Math.max(a, b); i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < nRows; j++) {
                    if (index < length) {
                        matrix[j][i] = s.charAt(index);
                        index++;
                    }
                }
            } else {
                for (int j = 1; j < nRows; j += 2) {
                    if (index < length) {
                        matrix[j][i] = s.charAt(index);
                        index++;
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < Math.max(a, b); j++) {
                char c = matrix[i][j];
                if (c != '\0') {
                    builder.append(c);
                }
            }
        }

        return builder.toString();
    }

    /**
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string
     * format.
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     * For example,
     * Given numerator = 1, denominator = 2, return "0.5".
     * Given numerator = 2, denominator = 1, return "2".
     * Given numerator = 2, denominator = 3, return "0.(6)".
     */
    public static String fractionToDecimal(int numerator, int denominator) {

        return "";
    }

    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
     * For example, given the array [2,3,-2,4],
     * the contiguous subarray [2,3] has the largest product = 6.
     */
    public static int maxProduct(int[] A) {

        return 0;
    }


    public static void main(String[] args) {

        System.out.println(convert("ABC", 2));
        //System.out.println(isPalindrome(120030021));
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(Integer.MIN_VALUE);
        //System.out.println(isValid("{[]{([][][][()([(])])}}"));
        //        int A[] = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
        //
        //        int size = removeDuplicates(A);
        //        for (int i = 0; i < size; i++) {
        //            System.out.print(A[i] + " ");
        //        }
        //        System.out.println();

        //        TreeNode t1 = new TreeNode(1);
        //        TreeNode t2 = new TreeNode(2);
        //        TreeNode t3 = new TreeNode(2);
        //        TreeNode t4 = new TreeNode(3);
        //        TreeNode t5 = new TreeNode(4);
        //        TreeNode t6 = new TreeNode(4);
        //        TreeNode t7 = new TreeNode(3);
        //
        //        t1.left = t2;
        //        t1.right = t3;
        //
        //        t2.left = t4;
        //        t2.right = t5;
        //
        //        t3.left = t6;
        //        t3.right = t7;
        //
        //        System.out.println(preorderTraversal(t1));


        //        ListNode n1 = new ListNode(9);
        //        ListNode n2 = new ListNode(1);
        //        ListNode n3 = new ListNode(1);
        //        ListNode n4 = new ListNode(1);
        //        ListNode n5 = new ListNode(1);
        //
        //        n1.next = n2;
        //        n2.next = n3;
        //        n3.next = n4;
        //        n4.next = n5;
        //
        //        printlist(n1);
        //        ListNode head = Sorting.insertionSort(n1);
        //        printlist(head);


        //        int A[] = {2};
        //        int A[] = {3,3,3,3,3,3,3,3};
        //
        //        System.out.println(removeElement(A, 6));
        //        for (int i = 0; i < A.length; i++) {
        //            System.out.print(A[i] + " ");
        //        }
        //        System.out.println();


        //System.out.println(strStr("", ""));

        //        ListNode n11 = new ListNode(-4);
        //        ListNode n12 = new ListNode(-2);
        //        ListNode n13 = new ListNode(0);
        //        ListNode n14 = new ListNode(1);
        //        ListNode n15 = new ListNode(4);
        //
        //        n11.next = n12;
        //        n12.next = n13;
        //        n13.next = n14;
        //        n14.next = n15;
        //
        //        ListNode n21 = new ListNode(-9);
        //        ListNode n22 = new ListNode(-8);
        //        ListNode n23 = new ListNode(-6);
        //        ListNode n24 = new ListNode(-6);
        //        ListNode n25 = new ListNode(-5);
        //        ListNode n26 = new ListNode(-1);
        //        ListNode n27 = new ListNode(1);
        //        ListNode n28 = new ListNode(4);
        //        ListNode n29 = new ListNode(9);
        //
        //        n21.next = n22;
        //        n22.next = n23;
        //        n23.next = n24;
        //        n24.next = n25;
        //        n25.next = n26;
        //        n26.next = n27;
        //        n27.next = n28;
        //        n28.next = n29;
        //        ListNode n11 = new ListNode(1);
        //        ListNode n12 = new ListNode(3);
        //        ListNode n13 = new ListNode(4);
        //
        //        n11.next = n12;
        //        n12.next = n13;
        //
        //        ListNode n21 = new ListNode(0);
        //
        //        ListNode head = mergeTwoLists(n11, n21);
        //        printlist(head);


        //System.out.println(addBinary("1011", "101"));
        //        int A[] = {9, 9, 9, 9, 9};
        //
        //        A = plusOne(A);
        //        for (int i = 0; i < A.length; i++) {
        //            System.out.print(A[i] + " ");
        //        }
        //        System.out.println();

        //System.out.println(numTrees(3));

        //        ListNode n1 = new ListNode(1);
        //        ListNode n2 = new ListNode(2);
        //        ListNode n3 = new ListNode(3);
        //        ListNode n4 = new ListNode(4);
        //        ListNode n5 = new ListNode(5);
        //        ListNode n6 = new ListNode(6);
        //        ListNode n7 = new ListNode(7);
        //        ListNode n8 = new ListNode(8);
        //        ListNode n9 = new ListNode(9);
        //
        //        n1.next = n2;
        //        n2.next = n3;
        //        n3.next = n4;
        //        n4.next = n5;
        //        n5.next = n6;
        //        n6.next = n7;
        //        n7.next = n8;
        //        n8.next = n9;
        //        n9.next = n4;
        //
        //        System.out.println(detectCycle(n1).val);

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
        //        n3.next = n4;
        //        n4.next = n5;
        //        n5.next = n6;
        //        n6.next = n7;
        //
        //        reorderList(n1);
        //        ListNode temp = n1;
        //        while (temp != null) {
        //            System.out.print(temp.val + " -> ");
        //            temp = temp.next;
        //        }
        //        System.out.println();

        //System.out.println(reverseWords("     "));
        //        {
        //            TreeNode t1 = new TreeNode(1);
        //            TreeNode t2 = new TreeNode(2);
        //            TreeNode t3 = new TreeNode(2);
        //            TreeNode t4 = new TreeNode(3);
        //            TreeNode t5 = new TreeNode(4);
        //            TreeNode t6 = new TreeNode(4);
        //            TreeNode t7 = new TreeNode(3);
        //
        //            t1.left = t2;
        //            t1.right = t3;
        //
        //            t2.left = t4;
        //            t2.right = t5;
        //
        //            t3.left = t6;
        //            t3.right = t7;
        //
        //            System.out.println(isSymmetric(t1));
        //        }
        //        {
        //            TreeNode t1 = new TreeNode(1);
        //            TreeNode t2 = new TreeNode(2);
        //            TreeNode t3 = new TreeNode(2);
        //            TreeNode t4 = new TreeNode(3);
        //            TreeNode t5 = new TreeNode(3);
        //
        //            t1.left = t2;
        //            t1.right = t3;
        //            t2.right = t4;
        //            t3.right = t5;
        //
        //            System.out.println(isSymmetric(t1));
        //        }

        //System.out.println(getRow(100));

        //        MinStack stack = new MinStack();
        //        stack.push(-1);
        //        System.out.println(stack.top());
        //        System.out.println(stack.getMin());
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

