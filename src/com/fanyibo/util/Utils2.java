/*
 *    Copyright   2014
 *     Filename : Utils2
 *      Project : TestProject
 *   Created by : fanyibo on 12/27/14 9:37 PM
 */
package com.fanyibo.util;

import com.fanyibo.tree.TreeNode;

import java.util.*;

public final class Utils2 {
    /**
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique
     * triplets
     * in the array which gives the sum of zero.
     * Note:
     * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
     * The solution set must not contain duplicate triplets.
     * For example, given array S = {-1 0 1 2 -1 -4},
     * A solution set is:
     * (-1, 0, 1)
     * (-1, -1, 2)
     */
    public static List<List<Integer>> threeSum(int[] num,
                                               int sum) {
        if (num == null || num.length < 3) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(num);
        Set<List<Integer>> set = new HashSet<List<Integer>>();

        for (int i = 0; i < num.length; i++) {
            int start = i + 1;
            int end = num.length - 1;
            int target = sum - num[i];
            while (start < end) {
                if (num[start] + num[end] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(num[i]);
                    list.add(num[start]);
                    list.add(num[end]);
                    set.add(list);
                    start++;
                    end--;
                } else if (num[start] + num[end] < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return new ArrayList<List<Integer>>(set);
    }

    public static HashMap<List<Integer>, Integer> _combination(int[] num,
                                                               int start,
                                                               int count) {
        if (start < 0 || start >= num.length || count <= 0) {
            return null;
        }
        if (count == 1) {
            HashMap<List<Integer>, Integer> map = new HashMap<List<Integer>, Integer>();
            for (int i = start; i < num.length; i++) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(list, num[i]);
            }
            return map;
        } else {
            HashMap<List<Integer>, Integer> newMap = new HashMap<List<Integer>, Integer>();
            for (int i = start; i <= num.length - count; i++) {
                HashMap<List<Integer>, Integer> map = _combination(num, i + 1, count - 1);
                if (map == null) {
                    continue;
                }
                for (List<Integer> list : map.keySet()) {
                    int val = map.get(list);
                    List<Integer> newList = new ArrayList<Integer>();
                    newList.add(i);
                    newList.addAll(list);
                    newMap.put(newList, val + num[i]);
                }
            }
            return newMap;
        }
    }

    public static Set<List<Integer>> _threeSum(int[] num,
                                               int startIndex,
                                               int target,
                                               int count) {

        if (startIndex < 0 || startIndex > num.length - count || count <= 0) {
            return null;
        }

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        if (count == 1) {
            List<Integer> list = new ArrayList<Integer>();
            for (int i = startIndex; i < num.length; i++) {
                if (num[i] == target) {
                    list.add(num[i]);
                    result.add(new ArrayList<Integer>(list));
                    list.clear();
                }
            }
        } else {
            for (int i = startIndex + 1; i < num.length; i++) {
                Set<List<Integer>> subResult = _threeSum(num, i, target - num[i - 1], count - 1);
                if (subResult != null) {
                    for (List<Integer> list : subResult) {
                        list.add(0, num[i - 1]);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
     * Return the sum of the three integers. You may assume that each input would have exactly one solution.
     * For example, given array S = {-1 2 1 -4}, and target = 1.
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public static int threeSumClosest(int[] num,
                                      int target) {
        if (num == null || num.length < 3) {
            return 0;
        }
        Arrays.sort(num);

        int current = num[0] + num[1] + num[2];
        int result = current;
        int minDiff = Math.abs(current - target);
        int currDiff;
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1;
            int end = num.length - 1;
            while (start < end) {
                current = num[i] + num[start] + num[end];
                currDiff = Math.abs(current - target);
                if (currDiff < minDiff) {
                    result = current;
                    minDiff = currDiff;
                }
                if (current < target) {
                    start++;
                } else if (current > target) {
                    end--;
                } else {
                    return target;
                }
            }
        }
        return result;
    }


    /**
     * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find
     * all
     * unique quadruplets in the array which gives the sum of target.
     * Note:
     * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
     * The solution set must not contain duplicate quadruplets.
     * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
     * A solution set is:
     * (-1,  0, 0, 1)
     * (-2, -1, 1, 2)
     * (-2,  0, 0, 2)
     */
    public static List<List<Integer>> fourSum(int[] num,
                                              int target) {
        if (num == null || num.length < 3) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(num);
        Set<List<Integer>> set = new HashSet<List<Integer>>();

        for (int i = 0; i < num.length - 3; i++) {
            for (int j = i + 1; j < num.length - 2; j++) {
                int start = j + 1;
                int end = num.length - 1;
                int _target = target - num[i] - num[j];
                while (start < end) {
                    if (num[start] + num[end] == _target) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(num[i]);
                        list.add(num[j]);
                        list.add(num[start]);
                        list.add(num[end]);
                        set.add(list);
                        start++;
                        end--;
                    } else if (num[start] + num[end] < _target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return new ArrayList<List<Integer>>(set);
    }

    /**
     * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and
     * each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     */
    public static ListNode addTwoNumbers(ListNode l1,
                                         ListNode l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = null;
        ListNode temp = null;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        boolean addOne = false;

        while (temp1 != null || temp2 != null) {

            int val1 = temp1 == null ? 0 : temp1.val;
            int val2 = temp2 == null ? 0 : temp2.val;
            int sum = val1 + val2 + (addOne ? 1 : 0);
            if (head == null) {
                if (sum >= 10) {
                    head = new ListNode(sum - 10);
                    addOne = true;
                } else {
                    head = new ListNode(sum);
                    addOne = false;
                }
                temp = head;
            } else {
                if (sum >= 10) {
                    temp.next = new ListNode(sum - 10);
                    addOne = true;
                } else {
                    temp.next = new ListNode(sum);
                    addOne = false;
                }
                temp = temp.next;
            }

            temp1 = temp1 == null ? null : temp1.next;
            temp2 = temp2 == null ? null : temp2.next;
        }
        if (addOne) {
            temp.next = new ListNode(1);
        }
        return head;
    }

    /**
     * Given an array of strings, return all groups of strings that are anagrams.
     * Note: All inputs will be in lower-case.
     */
    public static List<String> anagrams(String[] strs) {

        if (strs != null && strs.length > 0) {

            HashMap<String, List<String>> map = new HashMap<String, List<String>>();

            for (String str : strs) {
                char[] c = str.toCharArray();
                Arrays.sort(c);
                String key = new StringBuilder().append(c).toString();
                if (!map.containsKey(key)) {
                    List<String> list = new ArrayList<String>();
                    list.add(str);
                    map.put(key, list);
                } else {
                    List<String> list = map.get(key);
                    list.add(str);
                }
            }
            List<String> result = new ArrayList<String>();
            for (String key : map.keySet()) {
                List<String> list = map.get(key);
                if (list.size() > 1) {
                    result.addAll(list);
                }
            }
            return result;
        }
        return new ArrayList<String>();
    }

    /**
     * There are N children standing in a line. Each child is assigned a rating value.
     * You are giving candies to these children subjected to the following requirements:
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * What is the minimum candies you must give?
     */
    public static int candy(int[] ratings) {
        return 0;
    }

    /**
     * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the
     * candidate numbers sums to T.
     * The same repeated number may be chosen from C unlimited number of times.
     * Note:
     * All numbers (including target) will be positive integers.
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set 2,3,6,7 and target 7,
     * A solution set is:
     * [7]
     * [2, 2, 3]
     */
    public static List<List<Integer>> combinationSum(int[] candidates,
                                                     int target) {
        return null;
    }

    /**
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the
     * candidate numbers sums to T.
     * Each number in C may only be used once in the combination.
     * Note:
     * All numbers (including target) will be positive integers.
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
     * A solution set is:
     * [1, 7]
     * [1, 2, 5]
     * [2, 6]
     * [1, 1, 6]
     */
    public static List<List<Integer>> combinationSum2(int[] num,
                                                      int target) {
        return null;
    }


    /**
     * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from
     * start
     * to end, such that:
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the dictionary
     * For example,
     * Given:
     * start = "hit"
     * end = "cog"
     * dict = ["hot","dot","dog","lot","log"]
     * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     * Note:
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     */
    public static int ladderLength(String start,
                                   String end,
                                   Set<String> dict) {
        return 0;
    }

    /**
     * Given a set of distinct integers, S, return all possible subsets.
     * Note:
     * Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * For example,
     * If S = [1,2,3], a solution is:
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     */
    public static List<List<Integer>> subsets(int[] S) {
        return null;
    }

    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * For example,
     * If n = 4 and k = 2, a solution is:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     */
    public static List<List<Integer>> combine(int n,
                                              int k) {
        return null;
    }

    /**
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     * click to show follow up.
     * Follow up:
     * Did you use extra space?
     * A straight forward solution using O(mn) space is probably a bad idea.
     * A simple improvement uses O(m + n) space, but still not the best solution.
     * Could you devise a constant space solution?
     */
    public static void setZeroes(int[][] matrix) {

    }

    /**
     * Given an absolute path for a file (Unix-style), simplify it.
     * For example,
     * path = "/home/", => "/home"
     * path = "/a/./b/../../c/", => "/c"
     * click to show corner cases.
     * Corner Cases:
     * Did you consider the case where path = "/../"?
     * In this case, you should return "/".
     * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
     * In this case, you should ignore redundant slashes and return "/home/foo".
     */
    public static String simplifyPath(String path) {
        return null;
    }

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
     * corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there?
     * Above is a 3 x 7 grid. How many possible unique paths are there?
     * Note: m and n will be at most 100.
     */
    public static int uniquePaths(int m,
                                  int n) {
        return 0;
    }

    /**
     * Given a collection of intervals, merge all overlapping intervals.
     * For example,
     * Given [1,3],[2,6],[8,10],[15,18],
     * return [1,6],[8,10],[15,18].
     */
    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s,
                 int e) {
            start = s;
            end = e;
        }

    }

    public static List<Interval> merge(List<Interval> intervals) {
        return null;
    }

    /**
     * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
     * For example,
     * Given n = 3, your program should return all 5 unique BST's shown below.
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     */
    public static List<TreeNode> generateTrees(int n) {
        return null;
    }


    public static void PRINT(Object obj) {
        System.out.println(obj.toString());
    }

    public static void main(String[] args) {

        String[] strs = {"", ""};
        List<String> list = anagrams(strs);
        System.out.println(list.toString());

        //        int[] num = {0, 1, 2, 3, 4, 5, 6};
        //        HashMap<List<Integer>, Integer> map = _combination(num, 0, 3);
        //        for (List<Integer> key : map.keySet()) {
        //            System.out.println(key.toString() + " => " + map.get(key));
        //        }
        //int[] num = {6, -18, -20, -7, -15, 9, 18, 10, 1, -20, -17, -19, -3, -5, -19, 10, 6, -11, 1, -17, -15, 6, 17, -18, -3, 16, 19, -20, -3, -17, -15, -3, 12, 1, -9, 4, 1, 12, -2, 14, 4, -4, 19, -20, 6, 0, -19, 18, 14, 1, -15, -5, 14, 12, -4, 0, -10, 6, 6, -6, 20, -8, -6, 5, 0, 3, 10, 7, -2, 17, 20, 12, 19, -13, -1, 10, -1, 14, 0, 7, -3, 10, 14, 14, 11, 0, -4, -15, -8, 3, 2, -5, 9, 10, 16, -4, -3, -9, -8, -14, 10, 6, 2, -12, -7, -16, -6, 10};
        //        int[] num = {1, 0, -1, 0, -2, 2};
        //        int target = 0;
        //        //System.out.println(threeSumClosest(num, target));
        //
        //        List<List<Integer>> lists = fourSum(num, target);
        //        if (lists == null) {
        //            PRINT("NULL");
        //        } else {
        //            for (List<Integer> list : lists) {
        //                PRINT(list);
        //            }
        //        }
    }
}
