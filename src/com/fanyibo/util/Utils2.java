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

        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int length = ratings.length;
        int[] candies = new int[length];
        candies[0] = 1;
        int i;
        for (i = 1; i < length; i++) {
            candies[i] = 1;
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1]) {
                candies[i] = candies[i - 1] - 1;
            }
            if (i < length - 1 && ratings[i] < ratings[i - 1] && ratings[i] <= ratings[i + 1]) {
                ReAdjustCandy(ratings, candies, i);
            }
            if (i == length - 1 && ratings[i] < ratings[i - 1]) {
                ReAdjustCandy(ratings, candies, i);
            }
        }

        int total = 0;
        for (i = 0; i < length; i++) {
            total += candies[i];
        }
        return total;
    }

    public static void ReAdjustCandy(int[] ratings,
                                     int[] candy,
                                     int startIndex) {
        int k = startIndex;
        int diff = 1 - candy[k];
        while (k > 0 && ratings[k - 1] > ratings[k]) {
            candy[k] = candy[k] + diff;
            k--;
        }
        if (diff > 0) {
            candy[k] += diff;
        }
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

        if (candidates == null || candidates.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(candidates);
        if (target < candidates[0]) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> set = getCombinationSum(candidates, target, 0);
        return new ArrayList<List<Integer>>(set);
    }

    public static Set<List<Integer>> getCombinationSum(int[] candidates,
                                                       int sum,
                                                       int start) {

        if (start >= candidates.length || candidates[start] > sum) {
            return new HashSet<List<Integer>>();
        }
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        if (candidates[start] == sum) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(candidates[start]);
            set.add(list);
        } else {
            int count = sum / candidates[start];
            for (int i = 0; i <= count; i++) {
                Set<List<Integer>> _set = getCombinationSum(candidates, sum - candidates[start] * i, start + 1);
                if (i == count && sum % candidates[start] == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    for (int j = 0; j < i; j++) {
                        list.add(candidates[start]);
                    }
                    set.add(list);
                } else {
                    for (List<Integer> _s : _set) {
                        List<Integer> list = new ArrayList<Integer>();
                        for (int j = 0; j < i; j++) {
                            list.add(candidates[start]);
                        }
                        list.addAll(_s);
                        set.add(list);
                    }
                }
            }
        }
        return set;
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

        if (num == null || num.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(num);
        if (target < num[0]) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> set = getUnqiueCombinationSum(num, target, 0);
        return new ArrayList<List<Integer>>(set);
    }

    public static Set<List<Integer>> getUnqiueCombinationSum(int[] candidates,
                                                             int sum,
                                                             int start) {

        if (start >= candidates.length || candidates[start] > sum) {
            return new HashSet<List<Integer>>();
        }
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        if (candidates[start] == sum) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(candidates[start]);
            set.add(list);
        } else {
            for (int i = 0; i <= 1; i++) {
                Set<List<Integer>> _set = getUnqiueCombinationSum(candidates, sum - candidates[start] * i, start + 1);
                for (List<Integer> _s : _set) {
                    List<Integer> list = new ArrayList<Integer>();
                    for (int j = 0; j < i; j++) {
                        list.add(candidates[start]);
                    }
                    list.addAll(_s);
                    set.add(list);
                }
            }
        }
        return set;
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


        if (n <= 0 || k <= 0) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 1; i <= n - k + 1; i++) {
            set.addAll(_combinations(n, i, k));
        }
        return new ArrayList<List<Integer>>(set);
    }

    public static List<List<Integer>> _combinations(int n,
                                                    int start,
                                                    int k) {

        if (start > n || k <= 0 || k > (n - start + 1)) {
            return new ArrayList<List<Integer>>();
        }
        if (k == 1) {
            List<List<Integer>> lists = new ArrayList<List<Integer>>();
            for (int i = start; i <= n; i++) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                lists.add(list);
            }
            return lists;
        } else {
            List<List<Integer>> lists = new ArrayList<List<Integer>>();
            for (int i = start + 1; i <= n - k + 2; i++) {
                List<List<Integer>> _lists = _combinations(n, i, k - 1);
                for (int j = 0; j < _lists.size(); j++) {
                    _lists.get(j).add(0, start);
                }
                lists.addAll(_lists);
            }
            return lists;
        }
    }

    /**
     * A linked list is given such that each node contains an additional random pointer which could point to any node
     * in
     * the list or null.
     * Return a deep copy of the list.
     */
    public static class RandomListNode {
        int            label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public static RandomListNode copyRandomList(RandomListNode head) {

        RandomListNode newHead = null;
        RandomListNode oriTemp1 = head;
        RandomListNode newTemp1 = null;

        HashMap<Integer, List<RandomListNode>> map = new HashMap<Integer, List<RandomListNode>>();

        while (oriTemp1 != null) {

            int label = oriTemp1.label;

            if (newHead == null) {
                newHead = new RandomListNode(label);
                newTemp1 = newHead;
            } else {
                RandomListNode temp = new RandomListNode(label);
                newTemp1.next = temp;
                newTemp1 = newTemp1.next;
            }
            List<RandomListNode> list = new ArrayList<RandomListNode>();
            list.add(oriTemp1);
            list.add(newTemp1);
            map.put(oriTemp1.hashCode(), list);
            oriTemp1 = oriTemp1.next;
        }

        for (Integer hasCode : map.keySet()) {
            List<RandomListNode> list = map.get(hasCode);
            RandomListNode original = list.get(0);
            RandomListNode newNode = list.get(1);
            if (original.random != null) {
                Integer oriRandomHashCode = original.random.hashCode();
                newNode.random = map.get(oriRandomHashCode).get(1);
            }
        }

        return newHead;
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

        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (S != null && S.length > 0) {
            Arrays.sort(S);
            for (int i = 0; i < S.length; i++) {
                List<Integer> list;
                int length = lists.size();
                for (int j = 0; j < length; j++) {
                    list = new ArrayList<Integer>(lists.get(j));
                    list.add(S[i]);
                    lists.add(list);
                }
                list = new ArrayList<Integer>();
                list.add(S[i]);
                lists.add(list);
            }
        }
        lists.add(new ArrayList<Integer>());
        return lists;
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

        if (matrix == null) {
            return;
        }
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (Integer x : rows) {
            for (int i = 0; i < matrix[x].length; i++) {
                matrix[x][i] = 0;
            }
        }
        for (Integer y : cols) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][y] = 0;
            }
        }
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

        List<String> list = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '/') {
                if (builder.length() > 0) {
                    list.add(builder.toString());
                    builder.delete(0, builder.length());
                }
            } else {
                builder.append(c);
            }
        }
        if (builder.length() > 0) {
            list.add(builder.toString());
            builder.delete(0, builder.length());
        }
        builder.delete(0, builder.length());
        Deque<String> queue = new ArrayDeque<String>();
        queue.addFirst("/");
        for (String elem : list) {
            if (elem.equals(".")) {
                // continue;
            } else if (elem.equals("..")) {
                if (!queue.isEmpty()) {
                    String removed = queue.removeLast();
                    if (removed.equals("/")) {
                        queue.addLast("/");
                    }
                } else {
                    queue.addLast("/");
                }
            } else {
                queue.addLast(elem);
            }
        }
        while (!queue.isEmpty()) {
            String elem = queue.removeFirst();
            builder.append(elem);
            if (!elem.equals("/") && !queue.isEmpty()) {
                builder.append("/");
            }
        }
        return builder.toString();
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

        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    paths[i][j] = 1;
                } else {
                    paths[i][j] = paths[i][j - 1] + paths[i - 1][j];
                }
            }
        }
        return paths[m - 1][n - 1];
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

        @Override public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static List<Interval> mergeIntervals(List<Interval> intervals) {

        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<Interval>();
        }

        List<Interval> result = new ArrayList<Interval>();

        for (int i = 0; i < intervals.size(); i++) {
            insertInterval(result, intervals.get(i));
        }
        return result;
    }

    public static void insertInterval(List<Interval> intervals,
                                      Interval newInterval) {
        int i = 0;
        while (i < intervals.size()) {

            int start = intervals.get(i).start;
            int end = intervals.get(i).end;

            if (start == newInterval.start && end == newInterval.end) {
                return;
            }

            if (newInterval.end < start) {
                intervals.add(i, newInterval);
                return;
            } else if (newInterval.start > end) {
                i++;
            } else {
                newInterval.start = Math.min(newInterval.start, start);
                newInterval.end = Math.max(newInterval.end, end);
                intervals.remove(i);
            }
        }
        intervals.add(newInterval);
    }

    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * For example:
     * A = [2,3,1,1,4], return true.
     * A = [3,2,1,0,4], return false.
     */
    public static boolean canJump(int[] A) {

        if (A == null || A.length <= 1) {
            return true;
        }
        //5,9,3,2,1,0,2,3,3,1,0,0
        int length = A.length;
        int last = length - 1;
        int[] records = new int[length];
        int current = 0;
        records[current] = 1;
        int longst = 0;
        while (current <= last && records[current] == 1) {

            for (int i = longst; i <= current + A[current]; i++) {
                if (i >= last) {
                    return true;
                } else {
                    records[i] = 1;
                    longst = i;
                }
            }
            current++;
        }
        return records[last] == 1;
    }

    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     * For example:
     * Given array A = [2,3,1,1,4]
     * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the
     * last index.)
     */
    public static int jump(int[] A) {

        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1 || A[0] >= A.length - 1) {
            return 1;
        }

        int length = A.length;
        int[] dist = new int[length];
        dist[0] = 0;
        for (int i = 0; i < length; i++) {
            int outbound = Math.min(length - 1, i + A[i]);
            for (int j = i + 1; j <= outbound; j++) {
                if (dist[j] == 0 || dist[j] > dist[i] + 1) {
                    dist[j] = dist[i] + 1;
                }
            }
        }
        return dist[length - 1];
    }

    /**
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     * For example:
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     */
    public static int titleToNumber(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();

        int total = 0;
        for (int i = 0; i < length; i++) {
            int val = (int) s.charAt(i) - 64;
            total = 26 * total + val;
        }
        return total;
    }

    /**
     * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a
     * BST.
     * Calling next() will return the next smallest number in the BST.
     * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the
     * tree.
     */
    public static class BSTIterator {

        private List<TreeNode> inorder = new ArrayList<TreeNode>();

        public BSTIterator(TreeNode root) {
            if (root != null) {
                inorderDFS(root);
            }
        }

        private void inorderDFS(TreeNode node) {
            if (node.left != null) {
                inorderDFS(node.left);
            }
            inorder.add(node);
            if (node.right != null) {
                inorderDFS(node.right);
            }
        }

        public boolean hasNext() {

            return !inorder.isEmpty();
        }

        public int next() {
            return inorder.remove(0).val;
        }
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
        } else if (s.length() <= 2) {
            int num = Integer.valueOf(s);
            if (num == 0 || (num < 10 && s.length() == 1)) {
                return 0;
            } else if (num <= 10 || num > 26) {
                return 1;
            } else {
                return 2;
            }
        }

        if (Integer.valueOf(s.substring(0, 1)) == 0) {
            return 0;
        }

        int num2 = Integer.valueOf(s.substring(0, 2));
        if (num2 == 10 || num2 > 26) {
            return numDecodings(s.substring(2));
        } else {
            return numDecodings(s.substring(1)) + numDecodings(s.substring(2));
        }
    }


    /**
     * Given a string S and a string T, count the number of distinct subsequences of T in S.
     * A subsequence of a string is a new string which is formed from the original string by deleting some (can be
     * none)
     * of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a
     * subsequence of "ABCDE" while "AEC" is not).
     * Here is an example:
     * S = "rabbbit", T = "rabbit"
     * Return 3.
     */
    public static int numDistinct(String S,
                                  String T) {

        if (S == null || S.length() == 0 || T == null || T.length() == 0 || T.length() > S.length()) {
            return 0;
        } else if (T.length() == S.length()) {
            return T.equals(S) ? 1 : 0;
        }
        int sLength = S.length();
        int tLength = T.length();

        HashMap<Character, List<Integer>> mapChar = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < tLength; i++) {
            mapChar.put(T.charAt(i), new ArrayList<Integer>());
        }
        for (int i = 0; i < sLength; i++) {
            char c = S.charAt(i);
            if (mapChar.containsKey(c)) {
                mapChar.get(c).add(i);
            }
        }
        HashMap<Integer, List<Integer>> mapIndex = new HashMap<Integer, List<Integer>>();
        int maxCol = 0;
        for (int i = 0; i < tLength; i++) {
            List<Integer> vals = mapChar.get(T.charAt(i));
            maxCol = maxCol < vals.size() ? vals.size() : maxCol;
            mapIndex.put(i, new ArrayList<Integer>(vals));
        }
        int[][] ways = new int[tLength][maxCol];
        for (int i = tLength - 1; i >= 0; i--) {
            List<Integer> list = mapIndex.get(i);
            if (i == tLength - 1) {
                for (int j = 0; j < list.size(); j++) {
                    ways[i][j] = 1;
                }
            } else {
                List<Integer> nextList = mapIndex.get(i + 1);
                for (int j = 0; j < list.size(); j++) {
                    for (int k = 0; k < nextList.size(); k++) {
                        if (list.get(j) < nextList.get(k)) {
                            ways[i][j] += ways[i + 1][k];
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int j = 0; j < ways[0].length; j++) {
            result += ways[0][j];
        }
        return result;
    }

    /**
     * Divide two integers without using multiplication, division and mod operator.
     * If it is overflow, return MAX_INT.
     */
    public static int divide(int dividend,
                             int divisor) {

        if (dividend == 0)
            return 0;
        if (divisor == 0)
            return Integer.MAX_VALUE;

        boolean isNegative = false;
        long left = dividend;
        long right = divisor;
        if (dividend < 0) {
            isNegative = true;
            left = -left;
        }
        if (divisor < 0) {
            isNegative = !isNegative;
            right = -right;
        }
        long result = 0;
        while (left >= right) {
            long temp = right;
            int i = 1;
            while (left >= temp) {
                left -= temp;
                result += i;
                temp <<= 1;
                i <<= 1;
            }
        }
        if (result > Integer.MAX_VALUE && !isNegative) {
            return Integer.MAX_VALUE;
        } else if (result - 1 > Integer.MAX_VALUE && isNegative) {
            return Integer.MAX_VALUE;
        }
        return isNegative ? (int) -result : (int) result;
    }


    /**
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * Find the minimum element.
     * The array may contain duplicates.
     */
    public static int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return Integer.MIN_VALUE;
        } else if (num.length == 1) {
            return num[0];
        }
        int min = num[0];
        int left = 0;
        int right = num.length - 1;
        while (left < right - 1) {
            int mid = (left + right) / 2;
            if (num[left] > num[mid]) {
                min = Math.min(min, num[mid]);
                right = mid - 1;
            } else if (num[left] < num[mid]) {
                min = Math.min(min, num[left]);
                left = mid + 1;
            } else {
                left++;
            }
        }
        min = Math.min(min, Math.min(num[left], num[right]));
        return min;
    }

    /**
     * Given an integer n, return the number of trailing zeroes in n!.
     * Note: Your solution should be in logarithmic time complexity.
     */
    public static int trailingZeroes(int n) {
        return 0;
    }

    /**
     * Given an integer, convert it to a roman numeral.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
    public static String intToRoman(int num) {
        return "";
    }

    /**
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     * For example,
     * Given:
     * s1 = "aabcc",
     * s2 = "dbbca",
     * When s3 = "aadbbcbcac", return true.
     * When s3 = "aadbbbaccc", return false.
     */
    public static boolean isInterleave(String s1,
                                       String s2,
                                       String s3) {
        return false;
    }


    /**
     * Given an unsorted integer array, find the first missing positive integer.
     * For example,
     * Given [1,2,0] return 3,
     * and [3,4,-1,1] return 2.
     * Your algorithm should run in O(n) time and uses constant space.
     */
    public static int firstMissingPositive(int[] A) {
        return 0;
    }

    /**
     * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each
     * operation is counted as 1 step.)
     * You have the following 3 operations permitted on a word:
     * a) Insert a character
     * b) Delete a character
     * c) Replace a character
     */
    public static int minDistance(String word1,
                                  String word2) {
        return 0;
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

        int[] num = {3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 6, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
        System.out.println(findMin(num));

        //        int[] A = new int[25000];
        //        for (int i = 0; i < A.length; i++) {
        //            A[i] = A.length - i;
        //        }
        //        int[] B = {2, 3, 1, 1, 4, 5, 3, 6};
        //        System.out.println(jump(A));
        //        System.out.println(jump(B));

        //        Interval i1 = new Interval(0, 0);
        //        Interval i2 = new Interval(4, 5);
        //        Interval i3 = new Interval(5, 6);
        //        Interval i4 = new Interval(5, 5);
        //        Interval i5 = new Interval(2, 3);
        //        Interval i6 = new Interval(5, 7);
        //        Interval i7 = new Interval(0, 0);
        //
        //
        //        List<Interval> intervals = new ArrayList<Interval>();
        //        intervals.add(i1);
        //        intervals.add(i2);
        //        intervals.add(i3);
        //        intervals.add(i4);
        //        intervals.add(i5);
        //        intervals.add(i6);
        //        intervals.add(i7);
        //
        //        List<Interval> result = mergeIntervals(intervals);
        //        for (Interval interval : result) {
        //            PRINT(interval);
        //        }


        //        List<List<Integer>> lists = combine(4, 3);
        //        for (List<Integer> list : lists) {
        //            PRINT(list);
        //        }

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
