/*
 *    Copyright   2014
 *     Filename : Utils
 *      Project : TestProject
 *   Created by : fanyibo on 12/9/14 10:42 PM
 */
package com.fanyibo.util;

import com.fanyibo.tree.AVLTree;
import com.fanyibo.tree.TreeNode;

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
        int a = length / (2 * nRows - 2);
        int left = length % (2 * nRows - 2);
        int cols = a * (nRows - 1) + 1 + (left > nRows ? (left - nRows) : 0);

        int index = 0;
        int rowIndex = nRows - 2;
        char array[][] = new char[nRows][cols];
        for (int i = 0; i < cols; i++) {

            if (i == 0 || i % (nRows - 1) == 0) {
                for (int j = 0; j < nRows; j++) {
                    if (index < length) {
                        array[j][i] = s.charAt(index++);
                    }
                }
            } else {
                if (index < length) {
                    if (rowIndex >= 1) {
                        array[rowIndex--][i] = s.charAt(index++);
                    }
                    if (rowIndex == 0) {
                        rowIndex = nRows - 2;
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = array[i][j];
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

        if (numerator == 0) {
            return "0";
        }

        long left = (long) numerator;
        long right = (long) denominator;

        if (right == 1) {
            return String.valueOf(left);
        } else if (right == -1) {
            return String.valueOf(-left);
        }

        boolean negativeL = false;
        boolean negativeR = false;


        if (left < 0) {
            negativeL = true;
            left = -left;
        }
        if (right < 0) {
            negativeR = true;
            right = -right;
        }

        StringBuilder builder = new StringBuilder();
        boolean appendDot = false;

        HashMap<Long, Integer> numeratorIndexMap = new HashMap<Long, Integer>();

        while (true) {
            if (left == right) {
                builder.append('1');
                break;
            } else if (left < right) {
                left = 10 * left;
                if (!appendDot) {
                    builder.append('.');
                    appendDot = true;
                }
            }
            if (appendDot) {

                if (numeratorIndexMap.containsKey(left)) {
                    builder.insert(numeratorIndexMap.get(left), "(");
                    builder.append(')');
                    break;
                }
                numeratorIndexMap.put(left, builder.length());
            }
            int i = (int) (left / right);
            left = left % right;
            builder.append(i);
            if (left == 0) {
                break;
            }
        }
        if (builder.charAt(0) == '.') {
            builder.insert(0, '0');
        }

        return negativeL == negativeR ? builder.toString() : "-" + builder.toString();
    }

    public static String convertToTitle(int n) {

        if (n < 27) {
            return String.valueOf((char) (n + 64));
        }

        StringBuilder builder = new StringBuilder();
        int left = n % 26;
        n = n / 26;
        if (left >= 0) {

            builder.append((char) (((left == 0) ? 26 : left) + 64));
            if (left == 0) {
                n--;
            }
        }

        return convertToTitle(n) + builder.reverse().toString();
    }

    /**
     * Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
     * horizontally or vertically neighboring. The same letter cell may not be used more than once.
     * For example,
     * Given board =
     * [
     * ["ABCE"],
     * ["SFCS"],
     * ["ADEE"]
     * ]
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     */

    public static boolean exist(char[][] board, String word) {

        if (word == null || word.length() == 0 || board == null || board.length == 0) {
            return false;
        }

        int row = board.length;
        int col = board[0].length;
        int visited[][] = new int[row][col];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (visited[i][j] != 1 && board[i][j] == word.charAt(0)) {
                    visited[i][j] = 1;
                    if (search(board, visited, word, 0, i, j, 1)) {
                        return true;
                    }
                    visited[i][j] = 0;
                }
            }
        }
        return false;
    }


    private static boolean search(char[][] board, int[][] visited, String word, int direction, int row, int col,
                                  int index) {

        if (index >= word.length()) {
            return true;
        }
        if (direction != 3 && col + 1 < board[0].length && visited[row][col + 1] != 1 && board[row][col + 1] == word
                .charAt(index)) {
            visited[row][col + 1] = 1;
            if (search(board, visited, word, 4, row, col + 1, index + 1)) {
                return true;
            }
            visited[row][col + 1] = 0;
        }
        if (direction != 1 && row + 1 < board.length && visited[row + 1][col] != 1 && board[row + 1][col] == word
                .charAt(index)) {
            visited[row + 1][col] = 1;
            if (search(board, visited, word, 2, row + 1, col, index + 1)) {
                return true;
            }
            visited[row + 1][col] = 0;
        }
        if (direction != 4 && col - 1 >= 0 && visited[row][col - 1] != 1 && board[row][col - 1] == word.charAt(index)) {
            visited[row][col - 1] = 1;
            if (search(board, visited, word, 3, row, col - 1, index + 1)) {
                return true;
            }
            visited[row][col - 1] = 0;
        }
        if (direction != 2 && row - 1 >= 0 && visited[row - 1][col] != 1 && board[row - 1][col] == word.charAt(index)) {
            visited[row - 1][col] = 1;
            if (search(board, visited, word, 1, row - 1, col, index + 1)) {
                return true;
            }
            visited[row - 1][col] = 0;
        }
        return false;
    }


    public static int singleNumberII_36(int A[]) {
        int n = A.length;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int c = 0;
            int d = 1 << i;
            for (int j = 0; j < n; j++) {
                if ((A[j] & d) > 0) {
                    c++;
                }
            }

            if (c % 3 != 0) {
                ans |= d;
            }
        }
        return ans;
    }


    /**
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
     * station (i+1). You begin the journey with an empty tank at one of the gas stations.
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) {
            return 0;
        }

        int sizeG = gas.length;
        int max = gas[0];
        int maxIndex = 0;
        for (int i = 0; i < sizeG; i++) {
            if (max < gas[i]) {
                max = gas[i];
                maxIndex = i;
            }
        }
        int start = maxIndex;

        for (int i = 0; i < sizeG; i++) {

            int current = start;
            int tank = gas[current];
            int next = (current == sizeG - 1) ? 0 : (current + 1);
            while (next != start) {

                if (tank - cost[current] < 0) {
                    break;
                }
                tank = tank - cost[current] + gas[next];
                current = next;
                next = (current == sizeG - 1) ? 0 : (current + 1);
            }
            if (next == start && tank >= cost[current]) {
                return start;
            } else {
                start = (start == sizeG - 1) ? 0 : (start + 1);
            }
        }
        return -1;
    }

    /************************************************************************************************************/

    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
     * For example, given the array [2,3,-2,4],
     * the contiguous subarray [2,3] has the largest product = 6.
     */
    public static int maxProduct(int[] A) {
        int res = A[0];
        int pos = Math.max(0, A[0]);
        int neg = Math.min(0, A[0]);
        for (int i = 1; i < A.length; ++i) {
            if (A[i] == 0) {
                pos = 0;
                neg = 0;
            } else if (A[i] > 0) {
                pos = Math.max(1, pos) * A[i];
                neg = neg * A[i];
            } else {
                int pos_old = pos;
                pos = neg * A[i];
                neg = Math.min(A[i], A[i] * pos_old);
            }
            res = Math.max(res, pos);
        }
        return res;
    }

    /**
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on
     * the row below.
     * For example, given the following triangle
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     * Note:
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the
     * triangle.
     */
    public static int minimumTotal(List<List<Integer>> triangle) {

        int row = triangle.size();
        if (row == 0) {
            return 0;
        } else if (row == 1) {
            return triangle.get(0).get(0);
        }
        int col = triangle.get(row - 1).size();
        int value[][] = new int[row][col];
        for (int j = 0; j < triangle.get(row - 1).size(); j++) {
            value[row - 1][j] = triangle.get(row - 1).get(j);
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                value[i][j] = triangle.get(i).get(j) + Math.min(value[i + 1][j], value[i + 1][j + 1]);
            }
        }
        return value[0][0];
    }

    public static int minimumTotal(List<List<Integer>> triangle, int rowIndex, int colIndex) {

        if (triangle.get(rowIndex).size() == 0 || rowIndex < 0 || rowIndex >= triangle
                .size() || colIndex < 0 || colIndex >= triangle.get(rowIndex).size()) {
            return 0;
        }
        if (rowIndex == triangle.size() - 1) {
            return triangle.get(rowIndex).get(colIndex);
        }
        return triangle.get(rowIndex).get(colIndex) + Math.min(minimumTotal(triangle, rowIndex + 1, colIndex),
                minimumTotal(triangle, rowIndex + 1, colIndex + 1));
    }

    /**
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
     * should be set to NULL.
     * Initially, all next pointers are set to NULL.
     * Note:
     * You may only use constant extra space.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has
     * two children).
     * For example,
     * Given the following perfect binary tree,
     * 1
     * /  \
     * 2    3
     * / \  / \
     * 4  5  6  7
     * After calling your function, the tree should look like:
     * 1 -> NULL
     * /  \
     * 2 -> 3 -> NULL
     * / \  / \
     * 4->5->6->7 -> NULL
     */
    public static class TreeLinkNode {
        int          val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public static void connect(TreeLinkNode root) {

        if (root == null) {
            return;
        }

        List<TreeLinkNode> list = new ArrayList<TreeLinkNode>();
        boolean setLevelStart = false;
        TreeLinkNode nextLevelStart = root.left;
        list.add(root);
        while (!list.isEmpty()) {

            TreeLinkNode temp = list.remove(0);
            if (setLevelStart) {
                nextLevelStart = temp.left;
            }
            if (temp.left != null) {
                list.add(temp.left);
            }
            if (temp.right != null) {
                list.add(temp.right);
            }
            if (!list.isEmpty() && list.get(0) != nextLevelStart) {
                temp.next = list.get(0);
                setLevelStart = false;
            } else {
                temp.next = null;
                setLevelStart = true;
            }
        }
    }


    /**
     * Given a binary tree, flatten it to a linked list in-place.
     * For example,
     * Given
     * 1
     * / \
     * 2   5
     * / \   \
     * 3   4   6
     * The flattened tree should look like:
     * 1
     * \
     * 2
     * \
     * 3
     * \
     * 4
     * \
     * 5
     * \
     * 6
     * click to show hints.
     * Hints:
     * If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order
     * traversal.
     */
    public static void flatten(TreeNode root) {

        List<TreeNode> list = new ArrayList<TreeNode>();
        dfs(root, list);

        for (int i = 0; i < list.size() - 1; ++i) {
            list.get(i).left = null;
            list.get(i).right = list.get(i + 1);
        }
    }

    public static void dfs(TreeNode node, List<TreeNode> list) {

        if (node == null) {
            return;
        }
        list.add(node);
        dfs(node.left, list);
        dfs(node.right, list);
    }

    /**
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     * For example:
     * Given the below binary tree and sum = 22,
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * return
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     */
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {

        if (root == null) {
            return null;
        }

        List<List<Integer>> total = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        dfs1(root, 0, sum, list, total);
        return total;
    }

    public static void dfs1(TreeNode node, int current, int sum, List<Integer> list, List<List<Integer>> total) {

        list.add(node.val);
        current += node.val;
        if (node.left != null) {
            dfs1(node.left, current, sum, list, total);
        }
        if (node.right != null) {
            dfs1(node.right, current, sum, list, total);
        }
        if (node.left == null && node.right == null && current == sum) {
            total.add(new ArrayList<Integer>(list));
        }
        list.remove(list.size() - 1);
        current -= node.val;
    }


    /**
     * Given a list, rotate the list to the right by k places, where k is non-negative.
     * For example:
     * Given 1->2->3->4->5->NULL and k = 2,
     * return 4->5->1->2->3->NULL.
     * {1,2,3}, 4 Expected:	{3,1,2}
     */
    public static ListNode rotateRight(ListNode head, int n) {

        if (head == null) {
            return null;
        }
        ListNode newHead = head;
        ListNode last = head;

        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            last = temp;
            temp = temp.next;
            size++;
        }
        n = n % size;
        if (n != 0) {
            int i = 0;
            temp = head;
            while (i != size - n - 1) {
                temp = temp.next;
                i++;
            }
            newHead = temp.next;
            temp.next = null;
            last.next = head;
        }
        return newHead;
    }

    /**
     * Given a collection of integers that might contain duplicates, S, return all possible subsets.
     * Note:
     * Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * For example,
     * If S = [1,2,2], a solution is:
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     */
    public static int[] mergeSort(int[] num, int start, int end) {

        if (end < start) {
            return new int[]{};
        }
        int size = end - start + 1;

        if (size == 1) {
            int[] result = new int[size];
            result[0] = num[start];
            return result;
        } else if (size == 2) {
            int[] result = new int[size];
            result[0] = num[start] <= num[end] ? num[start] : num[end];
            result[1] = num[start] <= num[end] ? num[end] : num[start];
            return result;
        } else {
            int half = size / 2;
            return merge1(mergeSort(num, start, start + half), mergeSort(num, start + half + 1, end));
        }
    }

    public static int[] merge1(int[] left, int[] right) {

        if ((left == null && right == null) || (left.length == 0 && right.length == 0)) {
            return new int[]{};
        }
        if (left == null || left.length == 0) {
            return right;
        }
        if (right == null || right.length == 0) {
            return left;
        }
        int sizeL = left.length;
        int sizeR = right.length;
        int size = sizeL + sizeR;
        int result[] = new int[size];
        int index = 0;
        int index1 = 0;
        int index2 = 0;

        while (index1 < sizeL && index2 < sizeR) {
            if (left[index1] <= right[index2]) {
                result[index++] = left[index1];
                index1++;
            } else {
                result[index++] = right[index2];
                index2++;
            }
        }

        if (index1 == sizeL && index2 != sizeR) {
            for (int i = index2; i < sizeR; i++) {
                result[index++] = right[i];
            }
        } else if (index1 != sizeL && index2 == sizeR) {
            for (int i = index1; i < sizeL; i++) {
                result[index++] = left[i];
            }
        }

        return result;
    }

    public static Set<List<Integer>> getAllCombination(int[] num, int index) {

        Set<List<Integer>> set = new HashSet<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        list.add(num[index]);
        set.add(list);
        if (index < num.length - 1) {
            Set<List<Integer>> rest = getAllCombination(num, index + 1);
            for (List<Integer> elem : rest) {
                set.add(new ArrayList<Integer>(elem));
                elem.add(0, num[index]);
                set.add(new ArrayList<Integer>(elem));
            }
        }
        return set;
    }


    public static List<List<Integer>> subsetsWithDup(int[] num) {


        if (num == null || num.length == 0) {
            return new ArrayList<List<Integer>>();

        }
        int array[] = mergeSort(num, 0, num.length - 1);

        Set<List<Integer>> bigSet = new HashSet<List<Integer>>();
        for (int i = 0; i < array.length; i++) {
            Set<List<Integer>> set = getAllCombination(array, i);
            for (List<Integer> elem : set) {
                bigSet.add(elem);
            }
        }
        bigSet.add(new ArrayList<Integer>());
        return new ArrayList<List<Integer>>(bigSet);
    }


    /**
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public static TreeNode sortedListToBST(ListNode head) {

        AVLTree avlTree = new AVLTree();
        ListNode temp = head;
        while (temp != null) {
            avlTree.insert(temp.val);
            temp = temp.next;
        }
        return avlTree.root;
    }

    /**
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     * Note:
     * You may assume that duplicates do not exist in the tree.
     */
    public static TreeNode buildTree1(int[] inorder, int[] postorder) {

        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }
        int inLength = inorder.length;
        int poLength = postorder.length;

        int rootVal = postorder[poLength - 1];
        int i = 0;
        for (i = 0; i < inLength; i++) {
            if (inorder[i] == rootVal) {
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeFromInPostOrders(inorder, 0, i - 1, postorder, 0, i - 1);
        root.right = buildTreeFromInPostOrders(inorder, i + 1, inLength - 1, postorder, i, poLength - 2);
        return root;
    }

    public static TreeNode buildTreeFromInPostOrders(int[] inorder, int inStart, int inEnd, int[] postorder,
                                                     int poStart, int poEnd) {

        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }

        if (inEnd == inStart) {
            return new TreeNode(inorder[inStart]);
        } else if (inEnd < inStart) {
            return null;
        }
        if (poEnd == poStart) {
            return new TreeNode(postorder[poStart]);
        } else if (poEnd < poStart) {
            return null;
        }
        int rootVal = postorder[poEnd];
        int i = 0;
        for (i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeFromInPostOrders(inorder, inStart, i - 1, postorder, poStart, poStart + i - 1 - inStart);
        root.right = buildTreeFromInPostOrders(inorder, i + 1, inEnd, postorder, poStart + i - inStart, poEnd - 1);

        return root;
    }

    /**
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     */
    public static TreeNode buildTree2(int[] preorder, int[] inorder) {

        if (inorder == null || inorder.length == 0 || preorder == null || preorder.length == 0) {
            return null;
        }
        int inLength = inorder.length;
        int preLength = preorder.length;

        int rootVal = preorder[0];
        int i = 0;
        for (i = 0; i < inLength; i++) {
            if (inorder[i] == rootVal) {
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeFromPreInOrders(preorder, 1, i, inorder, 0, i - 1);
        root.right = buildTreeFromPreInOrders(preorder, i + 1, preLength - 1, inorder, i + 1, inLength - 1);
        return root;
    }

    public static TreeNode buildTreeFromPreInOrders(int[] preorder, int preStart, int preEnd, int[] inorder,
                                                    int inStart, int inEnd) {

        if (inorder == null || inorder.length == 0 || preorder == null || preorder.length == 0) {
            return null;
        }

        if (preEnd == preStart) {
            return new TreeNode(preorder[preStart]);
        } else if (preEnd < preStart) {
            return null;
        }
        if (inEnd == inStart) {
            return new TreeNode(inorder[inStart]);
        } else if (inEnd < inStart) {
            return null;
        }
        int rootVal = preorder[preStart];
        int i = 0;
        for (i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeFromPreInOrders(preorder, preStart + 1, preStart + i - inStart, inorder, inStart, i - 1);
        root.right = buildTreeFromPreInOrders(preorder, preStart + i - inStart + 1, preEnd, inorder, i + 1, inEnd);

        return root;
    }

    /**
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right,
     * then right to left for the next level and alternate between).
     * For example:
     * Given binary tree {3,9,20,#,#,15,7},
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its zigzag level order traversal as:
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        List<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(root);
        boolean markNewLevel = true;
        TreeNode levelMark = null;
        int level = 0;
        while (!queue.isEmpty()) {

            TreeNode temp = queue.remove(0);
            if (level % 2 == 0) {
                tempList.add(temp.val);
            } else {
                tempList.add(0, temp.val);
            }
            if (markNewLevel) {
                if (temp.left != null) {
                    levelMark = temp.left;
                    markNewLevel = false;
                } else if (temp.right != null) {
                    levelMark = temp.right;
                    markNewLevel = false;
                }
            }
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            if (!queue.isEmpty() && queue.get(0) == levelMark) {
                resultList.add(new ArrayList<Integer>(tempList));
                tempList.clear();
                markNewLevel = true;
                level++;
            } else if (queue.isEmpty()) {
                resultList.add(new ArrayList<Integer>(tempList));
                tempList.clear();
            }
        }

        return resultList;
    }

    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * For example:
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,3,2].
     * Note: Recursive solution is trivial, could you do it iteratively?
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> resultList = new ArrayList<Integer>();
        TreeNode node = root;
        Stack<TreeNode> parentStack = new Stack<TreeNode>();
        while (!parentStack.isEmpty() || node != null) {
            if (node != null) {
                parentStack.push(node);
                node = node.left;
            } else {
                node = parentStack.pop();
                resultList.add(node.val);
                node = node.right;
            }
        }
        return resultList;
    }

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     */
    public static boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }
        List<Integer> list = new ArrayList<Integer>();
        dfsBST(root, list);
        int temp = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (temp >= list.get(i)) {
                return false;
            }
            temp = list.get(i);
        }
        return true;
    }

    public static void dfsBST(TreeNode node, List<Integer> inorder) {

        if (node == null) {
            return;
        }
        dfsBST(node.left, inorder);
        inorder.add(node.val);
        dfsBST(node.right, inorder);
    }

    /**
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * For example:
     * Given "25525511135",
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     */
    public static List<String> restoreIpAddresses(String s) {

        if (s == null || s.length() < 4 || s.length() > 12) {
            return null;
        }
        List<String> result = new ArrayList<String>();

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 3; ++i) {
            int first = Integer.valueOf(s.substring(0, i));
            if (first > 255) {
                continue;
            }
            builder.append(first);
            builder.append(".");

            for (int j = 1; j <= 3; ++j) {
                if (i + j >= s.length()) {
                    continue;
                }
                int second = Integer.valueOf(s.substring(i, i + j));
                if (second > 255) {
                    continue;
                }
                builder.append(second);
                builder.append(".");

                for (int k = 1; k <= 3; k++) {
                    if (i + j + k >= s.length()) {
                        continue;
                    }
                    int third = Integer.valueOf(s.substring(i + j, i + j + k));
                    if (third > 255) {
                        continue;
                    }
                    builder.append(third);
                    builder.append(".");

                    for (int l = 1; l <= 3; l++) {
                        if (i + j + k + l != s.length()) {
                            continue;
                        }
                        int forth = Integer.valueOf(s.substring(i + j + k, i + j + k + l));
                        if (forth > 255) {
                            continue;
                        }
                        builder.append(forth);
                        if (builder.length() == s.length() + 3) {
                            result.add(builder.toString());
                        }
                        builder.delete(builder.length() - String.valueOf(forth).length(), builder.length());
                    }
                    builder.delete(builder.length() - String.valueOf(third).length() - 1, builder.length());
                }
                builder.delete(builder.length() - String.valueOf(second).length() - 1, builder.length());
            }
            builder.delete(0, builder.length());
        }

        return result;
    }


    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given an encoded message containing digits, determine the total number of ways to decode it.
     * For example,
     * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
     * The number of ways decoding "12" is 2.
     */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return Integer.valueOf(s) == 0 ? 0 : 1;
        }
        if (s.length() == 2) {

            int num = Integer.valueOf(s);
            if (num == 10 || num > 26) {
                return 1;
            } else {
                return 2;
            }
        }

        int num1 = Integer.valueOf(s.substring(0, 1));
        int num2 = Integer.valueOf(s.substring(0, 2));
        if (num1 == 0) {
            return 0;
        }
        if (num2 == 10 || num2 > 26) {
            return numDecodings(s.substring(2));
        } else {
            return numDecodings(s.substring(2)) + numDecodings(s.substring(1));
        }
    }

    /**
     * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * For example,
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     */
    public static void solve(char[][] board) {

        if (board == null || board.length == 0) {
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        if (rows < 3 || cols < 3) {
            return;
        }

        List<Integer> indexX = new ArrayList<Integer>();
        List<Integer> indexY = new ArrayList<Integer>();

        int[][] marks = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            if (board[i][0] != 'X' && marks[i][0] != 1) {
                indexX.add(i);
                indexY.add(0);
            }
            if (board[i][cols - 1] != 'X' && marks[i][cols - 1] != 1) {
                indexX.add(i);
                indexY.add(cols - 1);
            }
        }
        for (int j = 1; j < cols - 1; j++) {
            if (board[0][j] != 'X' && marks[0][j] != 1) {
                indexX.add(0);
                indexY.add(j);
            }
            if (board[rows - 1][j] != 'X' && marks[rows - 1][j] != 1) {
                indexX.add(rows - 1);
                indexY.add(j);
            }
        }


        while (!indexX.isEmpty()) {

            int x = indexX.remove(0);
            int y = indexY.remove(0);

            marks[x][y] = 1;

            int leftX = x;
            int leftY = y - 1;
            int upX = x - 1;
            int upY = y;
            int rightX = x;
            int rightY = y + 1;
            int downX = x + 1;
            int downY = y;

            if (rightY < cols && marks[rightX][rightY] != 1 && board[rightX][rightY] != 'X') {
                indexX.add(rightX);
                indexY.add(rightY);
            }
            if (downX < rows && marks[downX][downY] != 1 && board[downX][downY] != 'X') {
                indexX.add(downX);
                indexY.add(downY);
            }
            if (upX >= 0 && marks[upX][upY] != 1 && board[upX][upY] != 'X') {
                indexX.add(upX);
                indexY.add(upY);
            }
            if (leftY >= 0 && marks[leftX][leftY] != 1 && board[leftX][leftY] != 'X') {
                indexX.add(leftX);
                indexY.add(leftY);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (marks[i][j] != 1) {
                    board[i][j] = 'X';
                }
            }
        }

    }


    /**
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray
     * code. A gray code sequence must begin with 0.
     * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     * Note:
     * For a given n, a gray code sequence is not uniquely defined.
     * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
     * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
     */
    public static List<Integer> grayCode(int n) {

        List<Integer> result = new ArrayList<Integer>();
        if (n >= 0) {
            for (int i = 0; i <= n; i++) {
                if (i == 0) {
                    result.add(0);
                } else {
                    int head = (int) Math.pow(2, (i - 1));
                    int size = result.size();
                    for (int j = 0; j < size; j++) {
                        result.add(size, (head + result.get(j)));
                    }
                }
            }
        }
        return result;
    }


    /**
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
     * original list.
     * For example,
     * Given 1->2->3->3->4->4->5, return 1->2->5.
     * Given 1->1->1->2->3, return 2->3.
     * 1 1 2 3 4 4 5 6
     */
    public static ListNode deleteDuplicates1(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        HashMap<Integer, Integer> dupNum = new HashMap<Integer, Integer>();
        ListNode temp = head;
        while (temp != null) {

            if (temp.next != null && temp.val == temp.next.val) {
                dupNum.put(temp.val, temp.val);
            }
            temp = temp.next;
        }

        ListNode newHead = null;
        ListNode tempHead = null;
        temp = head;
        while (temp != null) {

            if (dupNum.containsKey(temp.val) == false) {
                if (newHead == null) {
                    newHead = temp;
                    tempHead = newHead;
                } else {
                    tempHead.next = temp;
                    tempHead = temp;
                }
                temp = temp.next;
            } else {
                if (tempHead != null) {
                    temp = temp.next;
                    tempHead.next = temp;
                } else {
                    temp = temp.next;
                }
            }
        }

        return newHead;
    }

    /**
     * Follow up for "Search in Rotated Sorted Array":
     * What if duplicates are allowed?
     * Would this affect the run-time complexity? How and why?
     * Write a function to determine if a given target is in the array.
     */
    public static int _search(int[] A, int target, int left, int right) {

//        if (left > right || left < 0 || right >= A.length) {
//            return -1;
//        }
//        if (right - left == 1) {
//            return A[left] == target ? left : (A[right] == target ? right : -1);
//        }
//        if (left == right) {
//            return A[left] == target ? left : -1;
//        }
//        int half = ((right - left) / 2) + left;
//
//        if (A[left] < A[half]) {
//
//            if (A[left] <= target && A[half] >= target) {
//                return _search(A, target, left, half);
//            } else {
//                return _search(A, target, half + 1, right);
//            }
//
//        } else {
//
//            if (A[half] <= target && A[right] >= target) {
//                return _search(A, target, half, right);
//            } else {
//                return _search(A, target, left, half - 1);
//            }
//        }

        if (left > right || left < 0 || right >= A.length) {
            return -1;
        }
        if (right - left == 1) {
            return A[left] == target ? left : (A[right] == target ? right : -1);
        }
        if (left == right) {
            return A[left] == target ? left : -1;
        }

        int half = ((right - left) / 2) + left;
        if (A[left] < A[half] && A[left] <= target && A[half] >= target) {
            return _search(A, target, left, half);
        } else {
            for (int i = half + 1; i < right; i++) {
                if (A[i] == target) {
                    return i;
                }
            }
            return -1;
        }
    }

    public static int search1(int[] A, int target) {

        if (A == null || A.length == 0) {
            return -1;
        }

        return _search(A, target, 0, A.length - 1);
    }

    public static boolean search2(int[] A, int target) {

        int start = 0;
        int end = A.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (A[mid] == target) {
                return true;
            }
            if (A[start] < A[mid]) {
                if (target >= A[start] && target < A[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (A[start] > A[mid]) {
                if (target > A[mid] && target <= A[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                start++;
            }
        }
        return false;
    }

    /**
     * Follow up for "Remove Duplicates":
     * What if duplicates are allowed at most twice?
     * For example,
     * Given sorted array A = [1,1,1,2,2,3],
     * Your function should return length = 5, and A is now [1,1,2,2,3].
     */
    public static int removeDuplicates2(int[] A) {

        if (A == null || A.length <= 2) {
            return A.length;
        }

        HashMap<Integer, Integer> dupNum = new HashMap<Integer, Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == A[i + 1]) {
                if (dupNum.containsKey(A[i])) {
                    int count = dupNum.get(A[i]);
                    if (count < 2) {
                        dupNum.put(A[i], count + 1);
                    }
                } else {
                    dupNum.put(A[i], 2);
                    list.add(A[i]);
                }
            } else {
                if (!dupNum.containsKey(A[i])) {
                    dupNum.put(A[i], 1);
                    list.add(A[i]);
                }
                if (i == A.length - 2) {
                    dupNum.put(A[i + 1], 1);
                    list.add(A[i + 1]);
                }
            }
        }
        int index = 0;
        for (Integer key : list) {
            int count = dupNum.get(key);
            for (int i = 0; i < count; i++) {
                A[index + i] = key;
            }
            index += count;
        }
        return index;
    }


    public static void main(String[] args) {


        int[] A = {1, 3, 1, 1, 1, 1};

        System.out.println(search2(A, 1));

//        TreeNode n1 = new TreeNode(10);
//        TreeNode n2 = new TreeNode(5);
//        TreeNode n3 = new TreeNode(12);
//        TreeNode n4 = new TreeNode(2);
//        TreeNode n5 = new TreeNode(11);
//        TreeNode n6 = new TreeNode(15);
//        TreeNode n7 = new TreeNode(1);
//        TreeNode n8 = new TreeNode(14);
//        TreeNode n9 = new TreeNode(14);
//        TreeNode n10 = new TreeNode(18);
//        TreeNode n11 = new TreeNode(3);
//
//        n1.left = n2;
//        n1.right = n3;
//
//        n2.left = n4;
//
//        n3.left = n5;
//        n3.right = n6;
//
//        n4.left = n7;
//        n4.right = n8;
//
//        n6.left = n9;
//        n6.right = n10;
//
//        n8.left = n11;
//
        //       System.out.println(numDecodings("101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010"));
//        List<String> ips = restoreIpAddresses("010010");
//        for (String ip : ips) {
//            System.out.println(ip);
//        }
    }
}

