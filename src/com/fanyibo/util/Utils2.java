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

        @Override
        public String toString() {
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

        if (dividend == 0) {
            return 0;
        }
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }

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

        int k = 0;
        long powerFive = (long) Math.pow(5, k);
        while (powerFive <= n) {
            powerFive *= 5;
            k++;
        }
        int result = 0;
        for (int i = 1; i <= k; i++) {
            result += Math.floor(n / Math.pow(5, i));
        }
        return result;
    }

    /**
     * Given an integer, convert it to a roman numeral.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
    private static final HashMap<Integer, String> BASE_ROMAN_NUM_DICT = new HashMap<Integer, String>();

    static {
        BASE_ROMAN_NUM_DICT.put(1, "I");
        BASE_ROMAN_NUM_DICT.put(5, "V");
        BASE_ROMAN_NUM_DICT.put(10, "X");
        BASE_ROMAN_NUM_DICT.put(50, "L");
        BASE_ROMAN_NUM_DICT.put(100, "C");
        BASE_ROMAN_NUM_DICT.put(500, "D");
        BASE_ROMAN_NUM_DICT.put(1000, "M");
    }

    private static final HashMap<Integer, String> SPEC_ROMAN_NUM_DICT = new HashMap<Integer, String>();

    static {
        SPEC_ROMAN_NUM_DICT.put(4, "IV");
        SPEC_ROMAN_NUM_DICT.put(9, "IX");
        SPEC_ROMAN_NUM_DICT.put(40, "XL");
        SPEC_ROMAN_NUM_DICT.put(90, "XC");
        SPEC_ROMAN_NUM_DICT.put(400, "CD");
        SPEC_ROMAN_NUM_DICT.put(900, "CM");
    }

    private static final int[] intRoman = {1000, 500, 100, 50, 10, 5, 1};

    public static String intToRoman(int num) {

        StringBuilder builder = new StringBuilder();
        String strNum = String.valueOf(num);
        int length = strNum.length();
        for (int i = 0; i < length; i++) {
            char digit = strNum.charAt(i);
            int numZero = length - i - 1;
            if (digit == '0') {
                continue;
            }
            int iDigit = ((int) digit - 48) * (int) Math.pow(10, numZero);
            if ((digit == '4' || digit == '9') && (numZero < 3)) {
                builder.append(SPEC_ROMAN_NUM_DICT.get(iDigit));
            } else {
                for (int j = 0; j < intRoman.length; j++) {
                    int times = iDigit / intRoman[j];
                    for (int k = 0; k < times; k++) {
                        builder.append(BASE_ROMAN_NUM_DICT.get(intRoman[j]));
                    }
                    iDigit = iDigit - intRoman[j] * times;
                }
            }
        }
        return builder.toString();
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

        int length1 = s1.length();
        int length2 = s2.length();
        int length3 = s3.length();
        if (length1 + length2 != length3) {
            return false;
        }

        boolean[][] interLeave = new boolean[length1 + 1][length2 + 1];
        interLeave[0][0] = true;
        for (int i = 1; i <= length1; i++) {
            interLeave[i][0] = interLeave[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i <= length2; i++) {
            interLeave[0][i] = interLeave[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                interLeave[i][j] = (interLeave[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1)))
                        || (interLeave[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1)));
            }
        }
        return interLeave[length1][length2];
    }


    /**
     * Given an unsorted integer array, find the first missing positive integer.
     * For example,
     * Given [1,2,0] return 3,
     * and [3,4,-1,1] return 2.
     * Your algorithm should run in O(n) time and uses constant space.
     */
    public static int firstMissingPositive(int[] A) {

        for (int i = 0; i < A.length; i++) {
            int target = A[i];
            while (target != i + 1) {
                if (target > 0 && target <= A.length && target != A[target - 1]) {
                    A[i] = A[target - 1];
                    A[target - 1] = target;
                    target = A[i];
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }
        return A.length + 1;
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

        int length1 = word1.length();
        int length2 = word2.length();
        int[][] distance = new int[length1 + 1][length2 + 1];

        for (int i = 0; i <= length2; i++) {
            distance[0][i] = i;
        }
        for (int i = 0; i <= length1; i++) {
            distance[i][0] = i;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    int case1 = distance[i][j - 1] + 1;
                    int case2 = distance[i - 1][j - 1] + 1;
                    int case3 = distance[i - 1][j] + 1;
                    int min = Math.min(case1, case2);
                    distance[i][j] = Math.min(min, case3);
                }
            }
        }
        return distance[length1][length2];
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
    public static boolean isNeighbor(String word1,
                                     String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }

    public static int ladderLength(String start,
                                   String end,
                                   Set<String> dict) {

        if (start.equals(end)) {
            return 0;
        }
        if (isNeighbor(start, end)) {
            return 2;
        }

        Set<String> starts = new HashSet<String>();
        Set<String> ends = new HashSet<String>();
        HashMap<String, Boolean> visited1 = new HashMap<String, Boolean>();
        HashMap<String, Boolean> visited2 = new HashMap<String, Boolean>();
        Iterator<String> it = dict.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if (str.equals(start) || str.equals(end)) {
                it.remove();
                continue;
            }
            visited1.put(str, false);
            visited2.put(str, false);
            boolean isStartNeighbor = isNeighbor(start, str);
            boolean isEndNeighbor = isNeighbor(end, str);
            if (isStartNeighbor && isEndNeighbor) {
                return 3;
            } else if (isStartNeighbor) {
                starts.add(str);
            } else if (isEndNeighbor) {
                ends.add(str);
            }
        }
        if (starts.isEmpty() || ends.isEmpty()) {
            return 0;
        }

        List<String> queue1 = new ArrayList<String>(ends);
        List<String> queue2 = new ArrayList<String>(starts);

        visited1.put(queue1.get(0), true);
        visited2.put(queue2.get(0), true);

        String newLevelStart1 = queue1.get(0);
        String newLevelStart2 = queue2.get(0);

        boolean markNewLevel1 = false;
        boolean markNewLevel2 = false;

        int step1 = 1;
        int step2 = 1;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            String node1 = queue1.remove(0);
            String node2 = queue2.remove(0);
            if (node1.equals(newLevelStart1)) {
                markNewLevel1 = true;
                step1++;
            }
            if (node2.equals(newLevelStart2)) {
                markNewLevel2 = true;
                step2++;
            }

            boolean v1 = visited1.get(node2);
            boolean v2 = visited2.get(node1);
            if (v1 && v2) {
                return step1 + step2 - 1;
            } else if (v1 || v2) {
                return step1 + step2;
            }

            it = dict.iterator();
            while (it.hasNext()) {
                String str = it.next();
                if (!visited1.get(str) && isNeighbor(str, node1)) {
                    queue1.add(str);
                    if (markNewLevel1) {
                        newLevelStart1 = str;
                        markNewLevel1 = false;
                    }
                    visited1.put(str, true);
                }
                if (!visited2.get(str) && isNeighbor(str, node2)) {
                    queue2.add(str);
                    if (markNewLevel2) {
                        newLevelStart2 = str;
                        markNewLevel2 = false;
                    }
                    visited2.put(str, true);
                }
                if (visited1.get(str) && visited2.get(str)) {
                    it.remove();
                }
            }
        }
        return 0;
    }


    /**
     * Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to
     * end, such that:
     * Only one letter can be changed at a time
     * Each intermediate word must exist in the dictionary
     * For example,
     * Given:
     * start = "hit"
     * end = "cog"
     * dict = ["hot","dot","dog","lot","log"]
     * Return
     * [
     * ["hit","hot","dot","dog","cog"],
     * ["hit","hot","lot","log","cog"]
     * ]
     * Note:
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     */
    public static class LadderNode {
        public String val;
        public Set<LadderNode> next = new HashSet<LadderNode>();

        public LadderNode(String node) {
            this.val = node;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            LadderNode that = (LadderNode) o;

            if (val != null ? !val.equals(that.val) : that.val != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = val != null ? val.hashCode() : 0;
            return result;
        }
    }

    public static List<List<String>> findLadders(String start,
                                                 String end,
                                                 Set<String> dict) {

        LadderNode root = new LadderNode(end);
        LadderNode leaf = new LadderNode(start);

        Set<LadderNode> starts = new HashSet<LadderNode>();
        Set<LadderNode> ends = new HashSet<LadderNode>();

        HashMap<String, LadderNode> map = new HashMap<String, LadderNode>();

        Set<List<String>> result = new HashSet<List<String>>();

        Iterator<String> it = dict.iterator();
        while (it.hasNext()) {
            String str = it.next();
            LadderNode node = new LadderNode(str);
            map.put(str, node);

            boolean isStartNeighbor = isNeighbor(start, str);
            boolean isEndNeighbor = isNeighbor(end, str);

            if (isStartNeighbor) {
                ends.add(node);
            }
            if (isEndNeighbor) {
                starts.add(node);
            }
        }
        if (starts.isEmpty() || ends.isEmpty()) {
            return new ArrayList<List<String>>(result);
        }

        List<LadderNode> queue = new ArrayList<LadderNode>(ends);
        LadderNode newLevelStart = queue.get(0);
        boolean markNewLevel = false;
        boolean meetStart = false;
        int step = 1;
        while (!queue.isEmpty()) {
            LadderNode node = queue.remove(0);
            if (node.equals(newLevelStart)) {
                markNewLevel = true;
                step++;
            }
            if (starts.contains(node)) {
                meetStart = true;
                break;
            }
            it = dict.iterator();
            while (it.hasNext()) {
                String str = it.next();
                if (isNeighbor(str, node.val)) {
                    it.remove();
                    queue.add(map.get(str));
                    if (markNewLevel) {
                        newLevelStart = map.get(str);
                        markNewLevel = false;
                    }
                }
            }
        }
        return new ArrayList<List<String>>(result);
    }

    /**
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * For example,
     * Given [100, 4, 200, 1, 3, 2],
     * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
     * Your algorithm should run in O(n) complexity.
     */
    public static int calcLongestConsecutive(HashMap<Integer, Boolean> visited,
                                             HashMap<Integer, Integer> lengths,
                                             int current) {
        if (visited.containsKey(current)) {
            if (!visited.get(current)) {
                int distBefore = calcLongestConsecutive(visited, lengths, current - 1);
                lengths.put(current, distBefore + 1);
                visited.put(current, true);
                return lengths.get(current);
            } else {
                return lengths.get(current);
            }
        }
        return 0;
    }

    public static int longestConsecutive(int[] num) {

        HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
        HashMap<Integer, Integer> lengths = new HashMap<Integer, Integer>();
        for (int i = 0; i < num.length; i++) {
            visited.put(num[i], false);
            lengths.put(num[i], 1);
        }
        List<Integer> keys = new ArrayList<Integer>(lengths.keySet());
        for (Integer key : keys) {
            if (visited.get(key) == false) {
                calcLongestConsecutive(visited, lengths, key);
            }
        }
        int max = 0;
        for (Integer key : keys) {
            int temp = lengths.get(key);
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    /**
     * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is
     * 1000, and there exists one unique longest palindromic substring.
     */
    public static String longestPalindrome(String s) {

        if (s == null || s.length() == 1) {
            return s;
        }

        int length = s.length();
        boolean[][] D = new boolean[length][length];
        D[length - 1][length - 1] = true;
        int max = 0;
        int start = 0; // inclusive
        int end = 0; // inclusive
        for (int i = length - 1; i >= 0; i--) {
            for (int j = length - 1; j >= i; j--) {
                if (j == i) {
                    D[i][j] = true;
                } else if (j - i <= 2) {
                    D[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    D[i][j] = s.charAt(i) == s.charAt(j) ? D[i + 1][j - 1] : false;
                }
                if (D[i][j] == true && j - i > max) {
                    max = j - i;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * Given a string, find the length of the longest substring without repeating characters. For example, the longest
     * substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest
     * substring is "b", with the length of 1.
     */
    public static int lengthOfLongestSubstring(String s) {

        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }
        int length = s.length();
        int max = 0;
        int current = 0;
        int[] hash = new int[256];
        for (int i = 0; i < 256; i++) {
            hash[i] = -1;
        }
        for (int i = 0; i < length; i++) {
            int c = s.charAt(i);
            if (hash[c] != -1) {
                i = hash[c];
                max = Math.max(max, current);
                current = 0;
                for (int j = 0; j < 256; j++) {
                    hash[j] = -1;
                }
            } else {
                hash[c] = i;
                current++;
            }
        }
        return Math.max(max, current);
    }

    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
     * parentheses substring.
     * For "(()", the longest valid parentheses substring is "()", which has length = 2.
     * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     */
    public static int longestValidParentheses(String s) {

        //        if (s == null || s.length() == 0) {
        //            return 0;
        //        }
        //        int length = s.length();
        //        int max = 0;
        //        int[] valid = new int[length];
        //        valid[length - 1] = 0;
        //        for (int i = length - 2; i >= 0; i--) {
        //            if (s.charAt(i) == ')') {
        //                valid[i] = 0;
        //            } else {
        //                int j = i + 1 + valid[i + 1];
        //                if (j < length && s.charAt(j) == ')') {
        //                    valid[i] = valid[i + 1] + 2;
        //                    if (j + 1 < length) {
        //                        valid[i] += valid[j + 1];
        //                    }
        //                } else {
        //                    valid[i] = 0;
        //                }
        //            }
        //            max = Math.max(valid[i], max);
        //        }
        //        return max;

        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        Stack<Integer> stack = new Stack<Integer>();

        int max = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
                if (stack.isEmpty()) {
                    max = Math.max(max, i + 1);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            } else {
                stack.push(i);
            }
        }
        return max;

        //        boolean[][] valid = new boolean[length][length];
        //        valid[length - 1][length - 1] = false;
        //        int max = 0;
        //        for (int i = length - 1; i >= 0; i--) {
        //            for (int j = i + 1; j < length; j++) {
        //                int size = j - i + 1;
        //                if (size % 2 != 0) {
        //                    valid[i][j] = false;
        //                } else {
        //                    if (size == 2) {
        //                        valid[i][j] = s.charAt(i) == '(' && s.charAt(j) == ')';
        //                    } else {
        //                        valid[i][j] = s.charAt(i) == '('
        //                                && s.charAt(j) == ')'
        //                                && (valid[i][j - 2] || valid[i + 2][j] || valid[i + 1][j - 1]);
        //                    }
        //                }
        //                if (valid[i][j] && size > max) {
        //                    max = size;
        //                }
        //            }
        //        }
        //        return max;
    }

    /**
     * The set [1,2,3,…,n] contains a total of n! unique permutations.
     * By listing and labeling all of the permutations in order,
     * We get the following sequence (ie, for n = 3):
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     * Note: Given n will be between 1 and 9 inclusive.
     */
    public static String getPermutation(int n,
                                        int k) {

        return null;
    }

    /**
     * Given a collection of numbers, return all possible permutations.
     * For example,
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    public static List<List<Integer>> permute(int[] num) {
        return null;
    }

    /**
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     * For example,
     * [1,1,2] have the following unique permutations:
     * [1,1,2], [1,2,1], and [2,1,1].
     */
    public static List<List<Integer>> permuteUnique(int[] num) {
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

        System.out.println(longestValidParentheses("()()()(((())()()()))))(())()())))))(())((())))))(()()))"));
        System.out.println(longestValidParentheses("))())))())(((())))()"));
        System.out.println(longestValidParentheses("((())))()())))(((()()(())))((()(())()((()))())())())()())))))))(((()(())(()))(()()(()()((()))()(())(()(())))))()(())(()()(((()(()()))))((()()))))))()((()())()()))((())()((((()))()()()((()())))())((())))))))(()()((((((()))(((((((()()))((())()(()())()()()(()())(()())(())))()()))))()(((())(())(()())()))()(()))(())((()))))(())))()))((()((()(())(()()()()()))(())())()))))()(()(((())))()()()(((()((()))(()((((((())((()))(()(())(()))(())())))()()))))())(()((()()())()))((((()(()))()()))(()())))((()))(()((((()(())(())()((()))(()))())))(((()(())))((())()(()(((())))())())()()()())((()()))))))(()))(())()(((()))()()((()))(()))(((()))))))))(()(())())(()((())(()()))((())))(()())((((())))(()(()))())(((()(()((()(())((())())(()))(())))()()(())((()()))((()()((()()())())()))())()))())()))())(()(()))(()))()(())))((((())()())()()())((()())(()())(()()))()(())(())))))()()()((()(())(((()(())()()))(()()((()(((()))))))))(((()((()()((()(((((())((()((()((((((((())()))())((())((()((()(()((())(((()(()))())))))))))))))()((()(())())))()(()))(((()))())()(((()))))((()(())(()())(((()(((()((((())()))))(())((()(((((()((()(()()()()((()((((((((((((())()(()))()()(()())()(()(((()((()(()()()())))((())()))())()()))())(((()(())))))()()()(((())))((()(()(((())(())(()((((()(((()(())(((((())()))())())()()(()())((((()(())))((()())))))))))()(()(())))))))()))()())))((())(()()()()()()()(())(()())))))())((()()))))()))))((())((()(((()))))(((()()))()(()((()()())()))(((()(()((())(()(()(()()))()((()(())))()((())))))(())()(())()))((())(((((()))()())(())))((((()((())())(())))(())))))((())())())((((()((())))()()((()()()))()())())(()())(((()))()()))))(()(())(()))()())(()())(()))(((((((()(()))())()())()())((()(((((()())(((())))()())))(()(()(())()((())()))(())))())()))((((()))())((()))(())))))(()))))))(()))))(())))())()()())()()(())()()(((((()))(((()()))()(()((((()(()(()(())))())))())(()()())()(()))())(()()))(()()((()()))))))(())((()()))(())))())())(())((((()))))()))()))()()()))))((((()((())(()))(()()))(())()())(()())))(()(()(())((()())()((())(()))()))()))))((())))(())(()))()()()()()))((())(((()(())))(((((((()(()))(()))())()((()))(()(())((()((()((())))()()((())))))((((())()())(()()(((()()((()))()()((())))(((()())((((()(())())))())()()()(())()))))))()()((()))())(()(((()()))((())))())())())((((()(((()(())())()())((()((()(()((())()(()))()((())))()(()))))(((()))())())(()((()))))()()(((((()))())))(()(()(())((((())())))((()()())(((((((()(()(()))(())))))()))(()(((((())()))((()()()()((()))()(()()()()))(()))))())())()))()(()()(((())((()))(()())))((()()(((())())))))))(())))((()(()(((())((((()))))(()()()))))(((((((())(()(()))(()(())((())(()(()(()(()())(())()(())(()()(()(()))())(())()()(((()())(())(()(((()()(())()((((()()))())(((()(((((()())()(())))()))))(()(()()(()(()()(((()))()))((()())))()(()(())))))))())((((()()))(()))))()((()))(()))())()))()))))(()(())()()()))(((((()))()())())(()())())))()())))))()()()())))))(())(((()))((())((()()))))()((((()(()(()))))(()(())(((())(()()(((()(())()())(()()(()(()())))()())))(((()()((()())()()((()))()))(((()((((()(((()(((()(()())((()))))()(()())(())()(()(((())((()))(())()(())()(()(())()))())()))()())(()))))()))))((()()()((()(()()(())))())(())()(()()))))))))()((()))((((())))())))((()()()(()(()((((()((()))()()((())((())(()))))(())())(((()()(()))))))(()()))()))((()(()(())()))(((())()))(())(()((((()((()()()))()()))(()()(())())((((((())(())((()())()(()())))()))())(()()(()(()()()(()()()()))(()(()()())())((()()()(((()((()())()()((()()(()((()())()())()((()))(()((()())))))))(())((((())(((((())(((())(()))(((()((()()())()((()(()))()()()(()((((())))(())())))((())))(()(((((()()()((())((((((((()()((((())))())())())))))))(((()())(((()))())))()))((())())())))))))))(()()(((())))))(())()()))((())()))(()(()))((()(()((((()(()(((()))))()))(()(()))())())()()(((())())(((()))))(((()())))()(()())()())()))())())(()()(((()()))(())(((()((())((((())))))((()))))(()((()(())))()(())((()(())((()(()())())))()))))(())())(()())()()()((())))((()()))()()()((((()())))))()))))()))())()((()(())()()(())(((()((()))(()(()()))(()))()))))))))))))(()()))(((())((()(((()()()(()())((((()(()()()))())))())(()())))(()((((()))((()()())(((()))()())(()(()((()(()))))(())()()((()())((()(()(()))((()((()())(((()(((((()()()))(()()(()(((()(()())()()()))((()(()())))())(()(()))(())()())))()()()))()())(()(((((()))()()((((()()()()))()()(()((()))(()))))))))))()))()(()((((((())(()))()((())))(((((())))))(()))))()()(()()()(((((()))()())()((((()()))()(())())))(((()((())))))))))(()()()((()))(()())((())))()()((()())))()()(()))))))))()(((()(()))()())((((((())))(((()(()())())))(())())())()()((((()(()(((())(()()(((((()))(()(())()))))))()))()())))()()(()))(((()))()())))((())(((()()))((((((())))(((())()()(()((()))())(()((()()(((())())()))()()())())(()()((((((((())))()(((())(()))))()()())()(())))(((((()())(((())()()))))()((())())(())()(()(()()((()))()(()(((()))))()()())(())()()()(((()((()()()(()())())(())()(((((()())(())()((((()()()))()((())()((()(()(((()(()))()())())()())()(()()(()(((()))()(())(()())(())((())()((()()())(()))))()(()()))))((())()()((()((()()(()((()()())(())))))())))()))))(((((()(()())(()))((()))()(()())())())))()(()()(()((())))))()()))((())((((()))))())((()))())((())((()(()((()))()()()))()((((((())((((((()((((((((()))(()(((()(((((((((())(())())()())))))))())())))()))(()))))()(()))(())))()()()((()()))(())(()))(()()(()())))()(()()()()())))(())((((()))(((((())(()(((((())((()((((()))(((((()))(()())()))))())()))()(()()))((((()))()())(())))()((())))(((((()()((()()((()))))()((())())()))(((((((((()((())((((())()())))(())())()))())))())()))()(()()(()))(()()()((())((()))())))()(()())()(((((((()))))(()()()((()(())))())()))((())())(()(()(())()()())))))(()()()))())()))()())(((())(())))()(())())))()((()(()(())()()()((()))()))((())((((()((((((()()()))()))())())))))()(())(()())))()(((()())(((())))((())()()))((())())()()(()()())()))())(((()((()(((()())(()(())(((())))()))))))())()((((((()))))))()(()))()))())))())((((())(())()(())()))()))((((((((()()(())())((((())))((((()(())()()(())()))())()))(((()((()))()(((((()()()))))(()(()())))(((()(((()(())())((((())(()((()((()(()()(()()))()))()()))()))))))())()(())())))(((())))((()))((()(())())((())))((()))()))(((()))))(()())()())())()())))())))(())))(())())()((()())()()))((()()())(((((()())))())))()()()((((((())())()((())()))(()))()(()())()())(())()())((()((())(())()()()()((())(()())()()((())))()(((()(()(((((()(())))()(()))()(()))()))())()))()())()(()))))()()())(((())((()((())(()(()))()((()))))))(())(()(())())()()((())((())((((())))))(()()())(()()())(())())(((()()(())(())))()()))(())))))())(())()(((())())))((()(((())))(()((())()))()))((()()())()(((((())((((())))(())()()((()()(()()))(()((()))((())())))))))()())))())())((()(()()()()()))))()))((())(((((()())(()))((())))((()(())))))))))(((())(()(())(()(())((()((()))()((())())()())()((()(())())()(((()()((()(()())))))())((())(((()())(((()(()((())((()(((())(()()((((((()))())))())(()(()(()()())())((()))((())(())(())())))()(()())()))())(())((((())()((())))))(()()((())(((((()))()()))()()())(()(((()))())()()()))(((()()))(()(()((())(())))()()((((()()))()(())()())()()()()()(()()))(((())(((()()()((((((((((()()()(((()))))))())))()(((((((()((((((((()))()(((())())())())((((((()()))(()))()))))(())()())))())(()))(((())()()()((()()((())(()))((()(()())))()(()((()((())()()()()(())()()((())())())()()))()()))))((()()((())(((())(())())))((())())())(()))))())))))()((()(()(()))))()))((((())((())())(()))))()((()))(())((()()))()()((())(())())))(())))))()()(()())((()(())(((()((())))()())))()))()))))(())()(()))((()()()()))(())))(()()(())(((()(((()()))()((()))())()))(()(()))())))))))()((()(()))(((())())(())(()))(())(()((()))))))(()())(()()()(((()(((((()))((()))))(()))(())())(((()(()())(()()()()))())(()((()(()))()))())))(((()(()))))()))(()()((())())(()()())((()))(())))()()))(())))())))(()((())((()))((()))))())()()()((((((())((())()))(()))(())(())())))()())()((())))((()()())(()))(((()))())())))(())(())())()())()))((((()()()()))(())))((((())))(())(()((((()))())()))))))()()()))())))()((())))))((())())))()()(()()(()(()()()())((((())))(())(((())(()(()((((())(()()()(()(()((())(())(()())((((()((()((((((()((((())(((()())()((((()((())()(()(()(()((()()()(()(()())(((()(())(()(())(())(()))()((((((((()()(())))(()()(((())()(()(((((()())((()(())()())(((()(()(())()((((((((())()((()()((((())(((()(()((()((((()((((()(()(())())()(((()()))))))(()(((())()(((()())))((()))))(()()))))(()))))))((())())((())))()()()(()((()))()))()))()()()()()))(((((((()((()))((())()(()(()))()((((((((((((()(())))))(()())))()(()()(()(()()))))(((((()()((())()))())()()()))(())())()())()()((()()(()(()()(()))))))()()))(()()((()))))()((()()()())))(((()(((()()(())(())(()(((())(()((()(()))(()()((())()(()()())()))))))(()()((())((()())))(())(()))()(()))(()))()))()(())()(()())))(()))(()()(((()))))())))))((())())))))()()()))(()))((()())())()()))(((())((()((())()(()))()((()))()(())))))))()()())())))(()()(())(()))(())))))()(()))(()()))))))))((((()()()()()))(()))((()((())))(()())(((()()()(())))))()))()())())(()()()))))))((()())))((())))(())()((()))()(()())())))))(((()()(()(())()())(((((()))((()(())(())))))))()()))))))((()((((()()))()))(()()))(()()(())))))(((()()))(()())))(())()((()((()(((()()()()((()())())(()(((((((()((((())(()((((()()(())))))(()())))))(()())))())(())))((()(()))(()())(((())))((((())))))((()))()(((((((())())())((())))))))(()))))))))()((()()())((())))(())))((()(((()(())(())))()()()()))(())(()(())()())(())))()())((()((()((()()((())())))(()((())()()))()))((()()(()))(((((((()))((())((())((())()()((((((((()())()()()))(()()(((()(())()))()))()))()()(()(((((()))))((())(((()))()((((((((()()()()(()))()(()))()(())))))))()((((()()((()(())()((()))((()()(()()))))))))))(())(()))()()((((())))()((())(()((()((()(())()))()()((((()))))()))())))))())(((()()))()))()(()))(()))()((()((((((())())))()))()((())(()(())))))))()))(()()()())())()))((()))(()((()((()())))))((((())()()())(())())()((()((()())()())()(()))))((()())))()))()()))))()((())))())(()))()))(()((())(()))))()()))(()()((((()()))(((()()())(()(()(((()))())))((((()())()()()(())()()()((()))))((()()(()()))()())))(((((()(())())))))(()))))())))(())())()))))((()))))))(((())(((())()(((())))(()))()())(((()(()(((()))))()(()()(())())))))())())()()((())))()(())((()))((())(()())(()()()(()()())((())())))))((()(()())()()))))(()()(()()()(()()))((((((()))(()())(())(())())((())(()(()))((()()(()))))()))()(())))())))())(()((())))((())(()()()(()))((()((((((()())()()))))()))((()((())()))()((((()()()(((())())))()()()())())())(()())()))()(())(())()))())((()(((((()))(())(((((()))(()())(()(()(())()((()(()(()))()(()))))()(((())(()((((((()))(()(((()()())()())((()())))((()((()())()((((((())()))(()))))(()()()()))())())((((((((())((((()()()))(())()()))(()(()()(()(()))))(()))(()()()(())()()(())(()))())((()()((()(()))()))))())((()())())(((((()(()()))((()()()))()()(()(()(((()())(((((((((()))()())(()(()(())(((((()()))))(()())(()())())(())))))((()))())(((()((((()))()))(()(()(((()()(()(((()()))(())))((((()(()()))))((((()()())()))())()))))((())((((((())()()))()))()(((()((())(()((((()())())((((((((((()(((((()())()()(()))))))()((((())())(((()()((((()()())()(((((())))()))(())())(((()(((())()))()()()()(())))(()(((()(()))())))()(((()()()()()(()(((()(((()))()(())(())()()))()((()))))))()(((((()(()()((())(())((())))()(()())(())))())((())(()(()()(((())((()()(()()((()))))())))()(()))()))))))())())))((((((()())))(())(()))()()))()(())))))))((()))(()()()()))()())()()()()))()()())))))))((())(())))(()))(())((()())))(()(((()))((((())())))(())((())())))))(((()())(())()(())))((()()()((()(()))()))(())))((()(()()((()()()))((()))((()))()))(()())()()(((((((((()(()))()())()((())((((((((()(())()(((()((())()((((((((()))())))(()((()((())())())((()()())))(()(()((((((()))))((((())))((()()(())()())()()())(())(((()(()()(())(((())((((())()()(()()(((())()(())((()(()(((()(()())))()))((()()())(())))))(()(()))()))()(()))))(()((()))()())(())(())(()))((()())()))())()())((((()))))())())(((()))(((()()((()((())(())()()))))(((()((((()(((((((((())()()()(())((()(()()(()()(()()(())()())(((((()))))()(())(((()((((()())(()(((()))))()))())((((()()((()(()))))((())(())(()(()))()()(((()))(((((((((()())))((())()(()(((((((()))))))()()(())(((()(()())()()))((()()))((((()(())())))((()())))))()))))))))()()(((())())((()))())((())((()()))())((((((((())((()((())())))))()()))))))()()(())))))()))()()(((()))))(())((((()()()()))((()((((()()(())(((((()())()))))))())())()((((((((((()))()))((()))(())())(()(()(())((()()(()((())(())((((())(()()(()((()((()(((((()(()()((((())(())())(()()())()())((()(())()(())()))))"));
        //        int[] num = {9, 53, 55, 56, -3, -4, -2, 10, 20, 35, 51, 54, -1, -5, 58, -6, 0, 1, 57, 59, 60, 61};
        //        System.out.println(longestConsecutive(num));
        //        {
        //            String start = "hit";
        //            String end = "cog";
        //            String[] strs = {"hot", "hit", "cog", "dot", "dog"};
        //            Set<String> dict = new HashSet<String>(Arrays.asList(strs));
        //            System.out.println("5 => " + ladderLength(start, end, dict));
        //        }
        //        {
        //            String start = "red";
        //            String end = "tax";
        //            String[] strs = {"ted", "tex", "red", "tax", "tad", "den", "rex", "pee"};
        //            Set<String> dict = new HashSet<String>(Arrays.asList(strs));
        //            System.out.println("4 => " + ladderLength(start, end, dict));
        //        }
        //        {
        //            String start = "raining";
        //            String end = "cellini";
        //            String[] strs = {"heaping", "conning", "nipping", "wadding", "pulling", "lunging", "figging", "donning", "jamming", "coating", "foaling", "ousting", "dowsing", "busting", "penning", "lapping", "yanking", "sapping", "tasking", "rigging", "ranking", "larking", "farming", "dunging", "nutting", "gouging", "barfing", "fasting", "belting", "boiling", "boating", "dipping", "kilning", "barking", "furling", "calving", "veiling", "decking", "ricking", "salting", "lucking", "sending", "taiping", "marking", "martina", "warping", "bulking", "seaming", "topping", "larding", "jilting", "besting", "weeding", "nesting", "baiting", "jibbing", "pelting", "bushing", "garbing", "banting", "keeping", "venting", "rapping", "binning", "mulling", "smiting", "hatting", "tapping", "writing", "footing", "carding", "ratting", "bagging", "sitting", "dousing", "pinking", "testing", "passing", "gelling", "gassing", "ranging", "hefting", "vamping", "wetting", "paining", "rolling", "sinking", "yakking", "shaking", "nabbing", "licking", "sparing", "hamming", "celling", "halving", "matting", "landing", "kooking", "pinning", "hagging", "narking", "soaping", "winding", "dealing", "earring", "cunning", "moating", "skiting", "jutting", "fueling", "hooping", "guiling", "mapping", "hailing", "gutting", "firming", "bunting", "mealing", "rending", "jobbing", "pauling", "foiling", "peeking", "rollins", "lansing", "felling", "whiting", "vealing", "resting", "saltine", "earning", "purging", "mullins", "pausing", "colling", "banning", "wasting", "sealing", "gigging", "scaring", "pocking", "massing", "curring", "storing", "dinging", "handing", "pitting", "faining", "cupping", "staring", "riffing", "gowning", "hipping", "vanning", "darting", "maiming", "damping", "deaning", "bellini", "kipling", "marting", "hawking", "fending", "kicking", "beading", "curving", "wending", "yelling", "foaming", "rifting", "surging", "gaining", "stoking", "panging", "winking", "nursing", "oinking", "looking", "tolling", "bailing", "tanking", "hacking", "warming", "cooping", "wanting", "rotting", "kinking", "bugging", "purling", "wincing", "joining", "belling", "wilting", "tensing", "fellini", "wilding", "binding", "bugling", "sagging", "nagging", "binging", "tatting", "cellini", "silting", "belying", "ripping", "crating", "slaking", "killing", "hurting", "running", "harming", "banding", "rinking", "staying", "touting", "hasting", "melting", "nibbing", "talking", "ganging", "bonging", "rilling", "damning", "pooling", "porting", "sinning", "collins", "barbing", "bunking", "smiling", "hanging", "tending", "bulging", "ginning", "coiling", "lolling", "molting", "letting", "mending", "hinging", "gunning", "melding", "dilling", "shaving", "harping", "basting", "cobbing", "carting", "leading", "styling", "fowling", "goading", "yowling", "zipping", "wagging", "gaoling", "panning", "valving", "peeling", "titling", "sailing", "harding", "parring", "haloing", "quiting", "punting", "reeling", "batting", "signing", "pegging", "bogging", "mashing", "rankine", "seeding", "sassing", "wafting", "winging", "framing", "rooting", "longing", "sabling", "bulbing", "whiling", "balking", "canting", "dashing", "dueling", "renting", "booting", "whaling", "vatting", "veining", "fencing", "yucking", "slaving", "welling", "sunning", "lulling", "purring", "dawning", "sensing", "meaning", "wording", "hogging", "parsing", "falling", "yelping", "dinning", "vetting", "hulling", "reading", "lapsing", "tooling", "hoaxing", "roiling", "forming", "ramming", "gelding", "felting", "popping", "walling", "costing", "welding", "washing", "filling", "lasting", "couping", "cabling", "getting", "winning", "carping", "martins", "bilking", "burning", "jelling", "sicking", "tinting", "ceiling", "totting", "balding", "kenning", "tinging", "hugging", "westing", "burring", "pasting", "pecking", "parking", "slaying", "pigging", "heating", "manning", "bucking", "bussing", "gagging", "goaling", "rowling", "netting", "funking", "pitying", "jarring", "tasting", "putting", "beating", "funding", "mauling", "balling", "molding", "shining", "perkins", "dialing", "panting", "looping", "welting", "relying", "dulling", "dumping", "tanning", "warring", "gatling", "staging", "finding", "farting", "petting", "picking", "swaying", "toiling", "jambing", "bawling", "minting", "wedding", "hulking", "keeling", "nanking", "railing", "heading", "cutting", "gosling", "vesting", "sighing", "mucking", "copping", "polling", "raising", "fooling", "hooting", "titting", "calming", "seating", "rifling", "soiling", "dubbing", "jesting", "posting", "sacking", "corking", "yipping", "lathing", "bopping", "setting", "coaxing", "poshing", "fawning", "heeling", "warning", "napping", "vending", "mooting", "hurling", "supping", "nanjing", "pipping", "tagging", "mopping", "souping", "palming", "gulling", "kirking", "gilding", "docking", "wefting", "dusting", "sharing", "darling", "bowling", "lauding", "bidding", "hopping", "honking", "hunting", "pepping", "busying", "damming", "patting", "hitting", "gusting", "jigging", "gabbing", "hosting", "sidling", "telling", "rusting", "daubing", "reining", "memling", "healing", "gashing", "betting", "lilting", "hashing", "salving", "firring", "gabling", "ducking", "waiving", "skating", "worming", "waiting", "burying", "booking", "corning", "suiting", "hooking", "gonging", "listing", "hulaing", "sulking", "digging", "fouling", "zincing", "cocking", "packing", "scaling", "pooping", "zinging", "banging", "bolling", "punning", "palling", "sipping", "bunging", "minding", "choking", "yapping", "nicking", "warding", "gorging", "canning", "culling", "lending", "spaying", "lashing", "pupping", "fanning", "banking", "pinging", "roaming", "sopping", "fonding", "searing", "fucking", "rooking", "tooting", "raining", "billing", "pulsing", "curbing", "cashing", "calking", "harking", "tarring", "tacking", "whining", "tarting", "pauline", "rasping", "howling", "helling", "curling", "pucking", "hauling", "coaling", "lopping", "mailing", "wailing", "lugging", "ticking", "staving", "snaking", "selling", "masking", "jabbing", "mewling", "heaving", "soaring", "fagging", "cording", "begging", "ridging", "jetting", "backing", "dotting", "lacking", "parting", "jotting", "dunning", "tinning", "stiling", "stating", "zapping", "hearing", "fitting", "barging", "galling", "wigging", "feeding", "tenting", "looting", "cabbing", "cursing", "dunking", "dabbing", "ragging", "bedding", "witting", "pouting", "burping", "slating", "tamping", "basking", "failing", "papping", "narcing", "lancing", "furlong", "tabling", "dolling", "tailing", "pawning", "collies", "lamming", "coifing", "bolting", "sucking", "rafting", "morning", "ranting", "tabbing", "rinding", "bandung", "bashing", "bending", "ducting", "casting", "camping", "flaming", "hinting", "sanding", "carving", "lagging", "helping", "keening", "jolting", "temping", "junking", "manging", "dimming", "ringing", "tipping", "spiking", "malling", "pursing", "soaking", "willing", "fulling", "causing", "jacking", "furring", "singing", "halting", "tucking", "ruining", "denting", "calling", "barring", "fopping", "yawning", "tilling", "nilling", "downing", "cooling", "martini", "budging", "lapwing", "mincing", "rinsing", "cowling", "marring", "coining", "sibling", "potting", "tauting", "bulling", "lurking", "sorting", "poohing", "bathing", "spicing", "nailing", "spiting", "racking", "lusting", "rutting", "gulping", "tilting", "pairing", "peaking", "capping", "gobbing", "finking"};
        //            Set<String> dict = new HashSet<String>(Arrays.asList(strs));
        //            System.out.println("? => " + ladderLength(start, end, dict));
        //        }
        //        {
        //            String start = "charge";
        //            String end = "comedo";
        //            String[] strs = {"shanny", "shinny", "whinny", "whiney", "shiver", "sharer", "scarer", "scaler", "render", "fluxes", "teases", "starks", "clinks", "messrs", "crewed", "donner", "blurts", "bettye", "powell", "castes", "hackee", "hackle", "heckle", "deckle", "decile", "defile", "define", "refine", "repine", "rapine", "ravine", "raving", "roving", "chased", "roping", "coping", "coming", "homing", "pointy", "hominy", "homily", "homely", "comely", "comedy", "comedo", "vagues", "crocus", "spiked", "bobbed", "dourer", "smells", "feared", "wooden", "stings", "loafer", "pleads", "gaiter", "meeter", "denser", "bather", "deaves", "wetted", "pleats", "cadger", "curbed", "grover", "hinged", "budget", "gables", "larked", "flunks", "fibbed", "bricks", "bowell", "yonder", "grimes", "clewed", "triads", "legion", "lacier", "ridden", "bogied", "camper", "damien", "spokes", "flecks", "goosed", "snorer", "choked", "choler", "leakey", "vagued", "flumes", "scanty", "bugger", "tablet", "nilled", "julies", "roomed", "ridges", "snared", "singes", "slicks", "toiled", "verged", "shitty", "clicks", "farmed", "stunts", "dowsed", "brisks", "skunks", "linens", "hammer", "naiver", "duster", "elates", "kooked", "whacky", "mather", "loomed", "soured", "mosses", "keeled", "drains", "drafty", "cricks", "glower", "brayed", "jester", "mender", "burros", "arises", "barker", "father", "creaks", "prayed", "bulges", "heaped", "called", "volley", "girted", "forded", "huffed", "bergen", "grated", "douses", "jagger", "grovel", "lashes", "creeds", "bonier", "snacks", "powder", "curled", "milker", "posers", "ribbed", "tracts", "stoked", "russel", "bummer", "cusses", "gouged", "nailed", "lobbed", "novels", "stands", "caches", "swanks", "jutted", "zinged", "wigged", "lunges", "divers", "cranny", "pinter", "guides", "tigers", "traces", "berber", "purges", "hoaxer", "either", "bribed", "camped", "funked", "creaky", "noises", "paused", "splits", "morrow", "faults", "ladies", "dinged", "smoker", "calved", "deters", "kicker", "wisher", "ballad", "filled", "fobbed", "tucker", "steams", "rubber", "staled", "chived", "warred", "draped", "curfew", "chafed", "washer", "tombed", "basket", "limned", "rapped", "swills", "gashed", "loaner", "settee", "layers", "bootee", "rioted", "prance", "sharps", "wigner", "ranted", "hanker", "leaden", "groped", "dalian", "robbed", "peeled", "larder", "spoofs", "pushed", "hallie", "maiden", "waller", "pashas", "grains", "pinked", "lodged", "zipper", "sneers", "bootie", "drives", "former", "deepen", "carboy", "snouts", "fained", "wilmer", "trance", "bugles", "chimps", "deeper", "bolder", "cupped", "mauser", "pagers", "proven", "teaser", "plucky", "curved", "shoots", "barged", "mantes", "reefer", "coater", "clotho", "wanner", "likens", "swamis", "troyes", "breton", "fences", "pastas", "quirky", "boiler", "canoes", "looted", "caries", "stride", "adorns", "dwells", "hatred", "cloths", "rotted", "spooks", "canyon", "lances", "denied", "beefed", "diaper", "wiener", "rifled", "leader", "ousted", "sprays", "ridged", "mousey", "darken", "guiled", "gasses", "suited", "drools", "bloody", "murals", "lassie", "babied", "fitter", "lessee", "chiles", "wrongs", "malian", "leaves", "redder", "funnel", "broths", "gushes", "grants", "doyens", "simmer", "locked", "spoors", "berger", "landed", "mosley", "scorns", "whiten", "hurled", "routed", "careen", "chorus", "chasms", "hopped", "cadged", "kicked", "slewed", "shrewd", "mauled", "saucer", "jested", "shriek", "giblet", "gnarls", "foaled", "roughs", "copses", "sacked", "blends", "slurps", "cashew", "grades", "cramps", "radius", "tamped", "truths", "cleans", "creams", "manner", "crimps", "hauled", "cheery", "shells", "asters", "scalps", "quotas", "clears", "clover", "weeder", "homers", "pelted", "hugged", "marked", "moaned", "steely", "jagged", "glades", "goshes", "masked", "ringer", "eloped", "vortex", "gender", "spotty", "harken", "hasten", "smiths", "mulled", "specks", "smiles", "vainer", "patted", "harden", "nicked", "dooley", "begged", "belief", "bushel", "rivers", "sealed", "neuter", "legged", "garter", "freaks", "server", "crimea", "tossed", "wilted", "cheers", "slides", "cowley", "snotty", "willed", "bowled", "tortes", "pranks", "yelped", "slaved", "silver", "swords", "miners", "fairer", "trills", "salted", "copsed", "crusts", "hogged", "seemed", "revert", "gusted", "pixies", "tamika", "franks", "crowed", "rocked", "fisher", "sheers", "pushes", "drifts", "scouts", "sables", "sallie", "shiner", "coupes", "napped", "drowse", "traced", "scenes", "brakes", "steele", "beater", "buries", "turned", "luther", "bowers", "lofted", "blazer", "serves", "cagney", "hansel", "talker", "warmed", "flirts", "braced", "yukked", "milken", "forged", "dodder", "strafe", "blurbs", "snorts", "jetted", "picket", "pistil", "valved", "pewter", "crawls", "strews", "railed", "clunks", "smiled", "dealer", "cussed", "hocked", "spited", "cowers", "strobe", "donned", "brawls", "minxes", "philby", "gavels", "renter", "losses", "packet", "defied", "hazier", "twines", "balled", "gaoled", "esther", "narrow", "soused", "crispy", "souped", "corned", "cooley", "rioter", "talley", "keaton", "rocker", "spades", "billie", "mattel", "billet", "horton", "navels", "sander", "stoker", "winded", "wilder", "cloyed", "blazed", "itched", "docked", "greene", "boozed", "ticket", "temped", "capons", "bravos", "rinded", "brandi", "massed", "sobbed", "shapes", "yippee", "script", "lesion", "mallet", "seabed", "medals", "series", "phases", "grower", "vertex", "dented", "tushed", "barron", "toffee", "bushes", "mouser", "zenger", "quaked", "marley", "surfed", "harmed", "mormon", "flints", "shamed", "forgot", "jailor", "boater", "sparer", "shards", "master", "pistol", "tooted", "banned", "drover", "spices", "gobbed", "corals", "chucks", "kitten", "whales", "nickel", "scrape", "hosted", "hences", "morays", "stomps", "marcel", "hummed", "wonder", "stoves", "distil", "coffer", "quaker", "curler", "nurses", "cabbed", "jigger", "grails", "manges", "larger", "zipped", "rovers", "stints", "nudges", "marlin", "exuded", "storey", "pester", "longer", "creeps", "meaner", "wallop", "dewier", "rivera", "drones", "valued", "bugled", "swards", "cortes", "charts", "benson", "wreaks", "glares", "levels", "smithy", "slater", "suites", "paired", "fetter", "rutted", "levied", "menses", "wither", "woolly", "weeded", "planed", "censer", "tested", "pulled", "hitter", "slicer", "tartar", "chunky", "whirrs", "mewled", "astern", "walden", "hilton", "cached", "geller", "dolled", "chores", "sorter", "soothe", "reused", "clumps", "fueled", "hurler", "helled", "packed", "ripped", "tanned", "binder", "flames", "teased", "punker", "jerked", "cannon", "joists", "whited", "sagged", "heaven", "hansen", "grayer", "turfed", "cranks", "stater", "bunted", "horsey", "shakes", "brands", "faints", "barber", "gorged", "creamy", "mowers", "scrams", "gashes", "knacks", "aeries", "sticks", "altars", "hostel", "pumped", "reeves", "litter", "hoaxed", "mushed", "guided", "ripper", "bought", "gelled", "ranker", "jennie", "blares", "saloon", "bomber", "mollie", "scoops", "coolie", "hollis", "shrunk", "tattle", "sensed", "gasket", "dodoes", "mapped", "strips", "dodges", "sailed", "talked", "sorted", "lodges", "livest", "pastel", "ladles", "graded", "thrice", "thales", "sagger", "mellon", "ganged", "maroon", "fluked", "raised", "nannie", "dearer", "lither", "triked", "dorset", "clamps", "lonnie", "spates", "larded", "condor", "sinker", "narced", "quaver", "atones", "farted", "elopes", "winger", "mottle", "loaned", "smears", "joanne", "boozes", "waster", "digger", "swoops", "smokey", "nation", "drivel", "ceased", "miffed", "faiths", "pisses", "frames", "fooled", "milled", "dither", "crazed", "darryl", "mulder", "posses", "sumter", "weasel", "pedals", "brawny", "charge", "welted", "spanks", "sallow", "joined", "shaker", "blocks", "mattie", "swirls", "driver", "belles", "chomps", "blower", "roared", "ratted", "hailed", "taunts", "steamy", "parrot", "deafer", "chewed", "spaces", "cuffed", "molded", "winked", "runnel", "hollow", "fluted", "bedded", "crepes", "stakes", "vested", "parley", "burton", "loiter", "massey", "carnap", "closed", "bailed", "milder", "heists", "morale", "putter", "snyder", "damion", "conned", "little", "pooped", "ticced", "cocked", "halves", "wishes", "francs", "goblet", "carlin", "pecked", "julius", "raster", "shocks", "dawned", "loosen", "swears", "buried", "peters", "treats", "noshed", "hedges", "trumps", "rabies", "ronnie", "forces", "ticked", "bodies", "proved", "dadoes", "halved", "warner", "divest", "thumbs", "fettle", "ponies", "testis", "ranked", "clouts", "slates", "tauted", "stools", "dodged", "chancy", "trawls", "things", "sorrow", "levies", "glides", "battle", "sauced", "doomed", "seller", "strove", "ballet", "bumper", "gooses", "foiled", "plowed", "glints", "chanel", "petals", "darted", "seared", "trunks", "hatter", "yokels", "vanned", "tweedy", "rubles", "crones", "nettie", "roofed", "dusted", "dicker", "fakers", "rusted", "bedder", "darrin", "bigger", "baylor", "crocks", "niches", "tented", "cashed", "splats", "quoted", "soloed", "tessie", "stiles", "bearer", "hissed", "soiled", "adored", "bowery", "snakes", "wagers", "rafter", "crests", "plaids", "cordon", "listed", "lawson", "scared", "brazos", "horded", "greens", "marred", "mushes", "hooper", "halter", "ration", "calked", "erodes", "plumed", "mummer", "pinged", "curios", "slated", "ranter", "pillow", "frills", "whaled", "bathos", "madden", "totted", "reamed", "bellow", "golfer", "seaman", "barred", "merger", "hipped", "silken", "hastes", "strays", "slinks", "hooted", "convex", "singed", "leased", "bummed", "leaner", "molted", "naught", "caters", "tidied", "forges", "sealer", "gulled", "plumps", "racket", "fitted", "rafted", "drapes", "nasser", "tamara", "winced", "juliet", "ledger", "bettie", "howell", "reeved", "spiced", "thebes", "apices", "dorsey", "welled", "feeler", "warded", "reader", "folded", "lepers", "cranky", "bosses", "ledges", "player", "yellow", "lunged", "mattes", "confer", "malign", "shared", "brandy", "filmed", "rhinos", "pulsed", "rouses", "stones", "mixers", "cooped", "joiner", "papped", "liston", "capote", "salvos", "wicker", "ciders", "hoofed", "wefted", "locket", "picker", "nougat", "limpid", "hooter", "jailer", "peaces", "mashes", "custer", "wallis", "purees", "trends", "irater", "honied", "wavers", "tanner", "change", "hinges", "tatted", "cookie", "catnap", "carton", "crimed", "betted", "veined", "surges", "rumped", "merlin", "convey", "placid", "harped", "dianna", "hookey", "nobles", "carted", "elided", "whined", "glover", "bleats", "stales", "husker", "hearer", "tartan", "weaker", "skewer", "lumbar", "temper", "gigged", "gawked", "mayors", "pigged", "gather", "valves", "mitten", "largos", "boreas", "judges", "cozens", "censor", "frilly", "dumbed", "downer", "jogger", "scolds", "danced", "floras", "funded", "lumped", "dashes", "azores", "quites", "chunks", "washed", "duller", "bilges", "cruels", "brooks", "fishes", "smoked", "leaped", "hotter", "trials", "heaves", "rouges", "kissed", "sleety", "manses", "spites", "starts", "banded", "clings", "titted", "vetoed", "mister", "mildew", "wailed", "sheets", "peeked", "passer", "felted", "broken", "lieges", "ruffed", "bracts", "buster", "muffed", "lanker", "breaks", "coffey", "sighed", "charms", "balded", "kisser", "booths", "leaven", "cheeps", "billed", "lauder", "bumped", "career", "stocks", "airier", "limped", "jeanie", "roamed", "carves", "lilted", "router", "bonnie", "denver", "briggs", "steeps", "nerves", "oinked", "bucked", "hooves", "dancer", "burris", "parked", "swells", "collie", "perked", "cooler", "fopped", "wedder", "malted", "sabers", "lidded", "conner", "rogues", "fought", "dapper", "purled", "crowds", "barnes", "bonner", "globed", "goners", "yankee", "probes", "trains", "sayers", "jersey", "valley", "vatted", "tauter", "dulled", "mucked", "jotted", "border", "genres", "banked", "filter", "hitler", "dipper", "dollie", "sieves", "joliet", "tilted", "checks", "sports", "soughs", "ported", "causes", "gelded", "mooter", "grills", "parred", "tipped", "placer", "slayer", "glided", "basked", "rinses", "tamper", "bunged", "nabbed", "climbs", "faeces", "hanson", "brainy", "wicket", "crowns", "calmed", "tarred", "spires", "deanne", "gravel", "messes", "snides", "tugged", "denier", "moslem", "erased", "mutter", "blahed", "hunker", "fasten", "garbed", "cracks", "braked", "rasped", "ravens", "mutton", "tester", "tories", "pinker", "titled", "arisen", "softer", "woolen", "disses", "likest", "dicier", "nagged", "lipton", "plumbs", "manged", "faulty", "sacred", "whiter", "erases", "padres", "haired", "captor", "metals", "cardin", "yowled", "trusts", "revels", "boxers", "toured", "spouts", "sodded", "judged", "holley", "figged", "pricey", "lapses", "harper", "beaned", "sewers", "caused", "willie", "farmer", "pissed", "bevies", "bolled", "bugler", "votive", "person", "linton", "senses", "supped", "mashed", "pincer", "wetter", "tangos", "sticky", "lodger", "loader", "daunts", "peaked", "moused", "sleeps", "lasted", "tasked", "awards", "lovely", "gushed", "spurts", "canter", "mantis", "coaled", "groans", "dannie", "oopses", "sneaky", "vogues", "mobile", "plumes", "chides", "theses", "marcia", "parser", "flexed", "stayed", "fouler", "tusked", "quartz", "daubed", "clancy", "rouged", "flaked", "norton", "dunner", "corded", "shelly", "hester", "fucker", "polled", "rodger", "yeager", "zinced", "livens", "browne", "gonged", "pubbed", "sapped", "thrive", "placed", "jensen", "moises", "scopes", "stumpy", "stocky", "heller", "levers", "morals", "wheres", "gasped", "jobber", "leaved", "champs", "rosier", "pallet", "shooed", "parses", "bender", "closet", "pureed", "routes", "verges", "bulled", "foster", "rummer", "molten", "condos", "better", "cotter", "lassos", "grafts", "vendor", "thrace", "codded", "tinker", "bullet", "beaker", "garden", "spiels", "popper", "skills", "plated", "farrow", "flexes", "esters", "brains", "handel", "puller", "dickey", "creeks", "ballot", "singer", "sicker", "spayed", "spoils", "rubier", "missed", "framed", "bonnet", "molder", "mugger", "waived", "taster", "robles", "tracks", "nearer", "lister", "horsed", "drakes", "lopped", "lubber", "busied", "button", "eluded", "ceases", "sought", "realer", "lasers", "pollen", "crisps", "binned", "darrel", "crafty", "gleams", "lonely", "gordon", "harley", "damian", "whiles", "wilton", "lesser", "mallow", "kenyon", "wimped", "scened", "risked", "hunter", "rooter", "ramses", "inches", "goaded", "ferber", "freaky", "nerved", "spoken", "lovers", "letter", "marrow", "bulbed", "braver", "sloped", "breads", "cannes", "bassos", "orated", "clever", "darren", "bredes", "gouger", "servos", "trites", "troths", "flunky", "jammed", "bugged", "watter", "motive", "humped", "writer", "pestle", "rilled", "packer", "foists", "croats", "floury", "napier", "floors", "scotty", "sevens", "harrow", "welter", "quacks", "daybed", "lorded", "pulses", "pokier", "fatten", "midges", "joints", "snoopy", "looter", "monies", "canted", "riffed", "misses", "bunker", "piston", "yessed", "earner", "hawked", "wedged", "brewer", "nested", "graver", "hoaxes", "slaves", "pricks", "magpie", "bernie", "rapier", "roster", "poohed", "corner", "trysts", "rogers", "whirls", "bathed", "teasel", "opener", "minced", "sister", "dreamy", "worker", "rinked", "panted", "triton", "mervin", "snowed", "leafed", "thinks", "lesson", "millet", "larson", "lagged", "likely", "stormy", "fortes", "hordes", "wovens", "kinked", "mettle", "seated", "shirts", "solver", "giants", "jilted", "leaded", "mendez", "lowers", "bidder", "greats", "pepped", "flours", "versus", "canton", "weller", "cowper", "tapped", "dueled", "mussed", "rubies", "bonged", "steals", "formed", "smalls", "sculls", "docket", "ouster", "gunned", "thumps", "curred", "withes", "putted", "buttes", "bloats", "parsed", "galley", "preses", "tagged", "hanger", "planes", "chords", "shafts", "carson", "posits", "zinger", "solves", "tensed", "tastes", "rinsed", "kenned", "bitten", "leslie", "chanty", "candor", "daises", "baggie", "wedded", "paints", "moored", "haloed", "hornet", "lifted", "fender", "guiles", "swifts", "flicks", "lancer", "spares", "pellet", "passed", "finked", "joanna", "bidden", "swamps", "lapped", "leered", "served", "shirrs", "choker", "limper", "marker", "nudged", "triter", "thanks", "peered", "bruins", "loaves", "fabled", "lathes", "pipers", "hooped", "orates", "burned", "swines", "sprats", "warder", "colder", "crazes", "reined", "prized", "majors", "darrow", "waifed", "rooked", "rickey", "patter", "shrive", "gropes", "gassed", "throve", "region", "weaken", "hettie", "walton", "galled", "convoy", "wesson", "exudes", "tinted", "clanks", "blinks", "slacks", "stilts", "franny", "socket", "wished", "kidded", "knotty", "turves", "cashes", "geared", "sunned", "glowed", "sadden", "harlem", "testes", "sweets", "becket", "blazes", "batter", "fellow", "clovis", "copier", "shaped", "husked", "gimlet", "rooney", "taints", "sashes", "bossed", "cootie", "franck", "probed", "bagged", "smocks", "batten", "spared", "chills", "relics", "meyers", "grader", "tromps", "dimmer", "pasted", "pepper", "capped", "played", "junket", "easier", "palmed", "pander", "vaguer", "bulged", "dissed", "borges", "raises", "wallow", "jigged", "bogged", "burped", "neater", "rammed", "fibers", "castor", "skirts", "cancer", "tilled", "spored", "dander", "denims", "budges", "trucks", "sowers", "yapped", "cadges", "wrists", "hacker", "graved", "vipers", "noshes", "minted", "lessor", "cassia", "wrecks", "hidden", "brando", "honeys", "chilli", "ragged", "breded", "punier", "stacey", "sisses", "jocked", "croaks", "dinned", "walker", "heston", "flares", "coined", "cannot", "chocks", "leases", "wander", "balder", "warmer", "bawled", "donnie", "damson", "header", "chilly", "models", "simper", "watery", "milked", "poises", "combed", "toilet", "gallop", "sonnet", "loosed", "yawned", "splays", "pauses", "bother", "graphs", "shrews", "scones", "manuel", "milers", "hotels", "bennie", "flores", "spells", "grimed", "tenses", "staged", "puffer", "posies", "motion", "fudged", "fainer", "tatter", "seraph", "nansen", "months", "muppet", "tamera", "shaman", "falser", "becker", "lisbon", "clefts", "weeper", "mendel", "girder", "takers", "torsos", "forked", "dances", "stated", "yelled", "scants", "frothy", "rolled", "yodels", "listen", "craned", "brooms", "suffer", "easter", "shills", "craves", "bleeps", "belled", "dished", "bordon", "zither", "jacket", "lammer", "kirked", "shaved", "atoned", "frumpy", "nosier", "vender", "graced", "clingy", "chants", "wrests", "cursed", "prunes", "tarter", "stripe", "coffee", "veiled", "tweeds", "shrine", "spines", "kegged", "melvin", "gasser", "market", "marten", "peeped", "sanger", "somber", "spider", "netted", "radium", "slings", "scarfs", "mended", "creels", "shaves", "payers", "bunked", "movers", "beings", "conked", "cozies", "benton", "codger", "prints", "gusset", "longed", "burner", "jambed", "mullet", "fogged", "scores", "carbon", "sleeks", "helped", "waxier", "gilded", "harlot", "winces", "tenser", "lowell", "ramsey", "kennan", "booted", "beaver", "rested", "shouts", "hickey", "looped", "swings", "wonted", "dilled", "defers", "lolled", "pupped", "cruets", "solved", "romper", "defter", "chokes", "kithed", "garnet", "bookie", "stared", "stares", "latter", "lazies", "fanned", "wagged", "dunces", "corked", "cloned", "prided", "baxter", "pusses", "boomed", "masses", "warren", "weaves", "delves", "handed", "merton", "lusher", "hepper", "gibber", "sender", "parsec", "snares", "masher", "seamed", "sweats", "welles", "gagged", "curter", "mother", "beeped", "vealed", "shoved", "slaver", "hacked", "gutted", "ranged", "bashed", "closer", "storks", "meshed", "cortex", "copper", "severn", "gripes", "carlos", "scares", "crates", "boiled", "ginned", "mouses", "raided", "greyed", "verier", "slopes", "fenced", "sniper", "priced", "flawed", "buffed", "spacey", "favors", "platen", "miller", "walled", "cutter", "skated", "holier", "beamed", "waiter", "drowns", "clomps", "quarks", "bested", "frisks", "purged", "scalds", "marian", "flower", "howled", "plover", "bikers", "trails", "hagged", "smirks", "sitter", "carmen", "lanced", "plants", "nobler", "yakked", "thesis", "lassen", "margin", "wagner", "sifter", "houses", "screws", "booker", "dormer", "meters", "padded", "loaded", "cartel", "sutton", "willis", "chatty", "dunked", "dreamt", "dalton", "fables", "coveys", "muller", "shanty", "adders", "tailor", "helper", "liters", "butted", "maiman", "hollie", "gallon", "xavier", "shrank", "mickey", "rather", "powers", "keened", "doused", "kisses", "flanks", "dotted", "phased", "dumped", "linger", "kramer", "spaced", "soften", "strife", "rowers", "hovers", "crimes", "crooks", "carrel", "braces", "lander", "shrove", "skulks", "banker", "itches", "dropsy", "misted", "pulped", "cloche", "fawned", "states", "teared", "beeper", "raider", "groves", "livery", "aerier", "keenan", "severe", "sabres", "bogies", "coated", "harlow", "tanked", "mellow", "cozier", "shanks", "spooky", "blamed", "tricks", "sleets", "punted", "jumped", "caxton", "warped", "halley", "frisky", "shines", "skater", "lumber", "truces", "sliced", "gibbet", "narked", "chives", "graves", "gummed", "holler", "glazes", "nieves", "hushed", "nought", "prated", "chored", "cloudy", "kidder", "huston", "straws", "twined", "gifted", "rodney", "haloes", "france", "wirier", "mercia", "rubbed", "coaxed", "sumner", "snipes", "nipper", "leiden", "madman", "margie", "footed", "firmed", "budded", "froths", "senior", "hoover", "tailed", "glider", "straps", "stalks", "billow", "racked", "javier", "zoomed", "shades", "whores", "braids", "roused", "sudden", "dogies", "fencer", "snaked", "flings", "traded", "gunner", "snider", "staten", "levees", "lathed", "sailor", "waited", "muster", "clothe", "lulled", "cargos", "revved", "sooths", "flamed", "borers", "feller", "bladed", "oliver", "collin", "wusses", "murder", "parted", "jailed", "frayed", "doored", "cheeks", "misled", "belted", "winter", "merges", "shaven", "fudges", "tabbed", "forget", "sloths", "cachet", "mealed", "sassed", "salter", "haunts", "ranger", "rivets", "deeded", "reaped", "damped", "crated", "youths", "whacks", "tamers", "misery", "seeped", "eerier", "tiller", "busses", "gloved", "hushes", "cronus", "pruned", "casket", "direst", "guilds", "motley", "spools", "fevers", "snores", "greece", "elides", "waists", "rattle", "trader", "juster", "rashes", "stoney", "pipped", "solder", "sinner", "prides", "rugged", "steers", "gnarly", "titter", "cities", "walter", "stolen", "steaks", "hawker", "weaned", "jobbed", "jacked", "pikers", "hipper", "spoilt", "beeves", "craved", "gotten", "balked", "sherry", "looney", "crisis", "callie", "swiped", "fished", "rooted", "bopped", "bowler", "escher", "chumps", "jerrod", "lefter", "snooty", "fillet", "scales", "comets", "lisped", "decked", "clowns", "horned", "robber", "bottle", "reeled", "crapes", "banter", "martel", "dowels", "brandt", "sweeps", "heeled", "tabled", "manors", "danger", "dionne", "prayer", "decker", "millie", "boated", "damned", "horses", "globes", "failed", "lammed", "nigher", "joyner", "sobers", "chided", "tipper", "parcel", "flakes", "fugger", "elated", "hinder", "hopper", "crafts", "wipers", "badder", "jessie", "matted", "wafted", "pealed", "cheats", "elites", "torres", "bushed", "sneaks", "tidies", "brings", "stalls", "payees", "zonked", "danker", "poshes", "smelts", "stoops", "warden", "chicks", "ramsay", "budged", "firmer", "glazed", "heated", "slices", "hovels", "belied", "shifts", "pauper", "tinges", "weston", "casted", "titles", "droves", "roomer", "modals", "seamen", "wearer", "blonde", "berlin", "libbed", "tensor", "hokier", "lambed", "graped", "headed", "copped", "eroses", "fagged", "filler", "keener", "stages", "civets", "spills", "tithed", "sullen", "sucked", "briton", "whaler", "hooded", "tittle", "bucket", "furled", "darned", "planet", "clucks", "batted", "dagger", "brides", "severs", "pathos", "grainy", "relied", "carpel", "makers", "lancet", "slowed", "messed", "ravels", "faster", "gabbed", "chance", "grayed", "santos", "spends", "chinos", "saints", "swirly", "dories", "wilson", "milton", "clangs", "manual", "nodded", "signer", "stript", "etched", "vaster", "wastes", "stored", "minces", "purred", "marvin", "pinned", "skulls", "heaved", "wadded", "fowled", "hashed", "mullen", "relief", "hatted", "primed", "chaffs", "canned", "lackey", "showed", "shandy", "chases", "maggie", "deafen", "bussed", "differ", "worked", "marted", "ducked", "socked", "fussed", "greyer", "herder", "trusty", "follow", "samson", "babies", "whorls", "stanks", "manson", "cranes", "murrow", "shrink", "genius", "holder", "lenses", "yucked", "termed", "ruined", "junker", "belies", "joshed", "cooled", "basted", "greeks", "fuller", "healer", "carver", "havens", "drunks", "sucker", "lotion", "glared", "healed", "pocked", "rifles", "weaved", "canoed", "punter", "hinton", "settle", "boobed", "hinted", "scored", "harder", "status", "sloven", "hayden", "golfed", "scoots", "bloods", "slaked", "jugged", "louses", "cassie", "shaded", "rushed", "pitied", "barked", "honked", "rasher", "forced", "shaver", "vowels", "holden", "pelvis", "blades", "chests", "preyer", "floods", "deanna", "cation", "mapper", "falter", "dabbed", "mocker", "nestle", "shucks", "heeded", "ticker", "binges", "summer", "slumps", "lusted", "scampi", "crofts", "gorges", "pardon", "torses", "smokes", "lashed", "bailey", "jabbed", "calmer", "preset", "forbes", "hasted", "wormed", "winged", "minors", "banner", "grazed", "hewers", "kernel", "jolted", "sniped", "clunky", "ratios", "blinds", "ganges", "misers", "spikes", "riders", "hallow", "grumpy", "barren", "summed", "infers", "places", "jarred", "killer", "plaint", "goofed", "subbed", "prudes", "sipped", "kookie", "whines", "droopy", "palled", "cherry", "proves", "mobbed", "spaded", "cheese", "pluses", "bathes", "motels", "spewed", "soaked", "howler", "puffed", "malled", "shrike", "slided", "fulled", "pouted", "shames", "lessen", "ringed", "teemed", "grands", "linked", "wooten", "feuded", "deaden", "scents", "flutes", "salton"};
        //            Set<String> dict = new HashSet<String>(Arrays.asList(strs));
        //            System.out.println("? => " + ladderLength(start, end, dict));
        //        }
        //        {
        //            String start = "nape";
        //            String end = "mild";
        //            String[] strs = {"dose", "ends", "dine", "jars", "prow", "soap", "guns", "hops", "cray", "hove", "ella", "hour", "lens", "jive", "wiry", "earl", "mara", "part", "flue", "putt", "rory", "bull", "york", "ruts", "lily", "vamp", "bask", "peer", "boat", "dens", "lyre", "jets", "wide", "rile", "boos", "down", "path", "onyx", "mows", "toke", "soto", "dork", "nape", "mans", "loin", "jots", "male", "sits", "minn", "sale", "pets", "hugo", "woke", "suds", "rugs", "vole", "warp", "mite", "pews", "lips", "pals", "nigh", "sulk", "vice", "clod", "iowa", "gibe", "shad", "carl", "huns", "coot", "sera", "mils", "rose", "orly", "ford", "void", "time", "eloy", "risk", "veep", "reps", "dolt", "hens", "tray", "melt", "rung", "rich", "saga", "lust", "yews", "rode", "many", "cods", "rape", "last", "tile", "nosy", "take", "nope", "toni", "bank", "jock", "jody", "diss", "nips", "bake", "lima", "wore", "kins", "cult", "hart", "wuss", "tale", "sing", "lake", "bogy", "wigs", "kari", "magi", "bass", "pent", "tost", "fops", "bags", "duns", "will", "tart", "drug", "gale", "mold", "disk", "spay", "hows", "naps", "puss", "gina", "kara", "zorn", "boll", "cams", "boas", "rave", "sets", "lego", "hays", "judy", "chap", "live", "bahs", "ohio", "nibs", "cuts", "pups", "data", "kate", "rump", "hews", "mary", "stow", "fang", "bolt", "rues", "mesh", "mice", "rise", "rant", "dune", "jell", "laws", "jove", "bode", "sung", "nils", "vila", "mode", "hued", "cell", "fies", "swat", "wags", "nate", "wist", "honk", "goth", "told", "oise", "wail", "tels", "sore", "hunk", "mate", "luke", "tore", "bond", "bast", "vows", "ripe", "fond", "benz", "firs", "zeds", "wary", "baas", "wins", "pair", "tags", "cost", "woes", "buns", "lend", "bops", "code", "eddy", "siva", "oops", "toed", "bale", "hutu", "jolt", "rife", "darn", "tape", "bold", "cope", "cake", "wisp", "vats", "wave", "hems", "bill", "cord", "pert", "type", "kroc", "ucla", "albs", "yoko", "silt", "pock", "drub", "puny", "fads", "mull", "pray", "mole", "talc", "east", "slay", "jamb", "mill", "dung", "jack", "lynx", "nome", "leos", "lade", "sana", "tike", "cali", "toge", "pled", "mile", "mass", "leon", "sloe", "lube", "kans", "cory", "burs", "race", "toss", "mild", "tops", "maze", "city", "sadr", "bays", "poet", "volt", "laze", "gold", "zuni", "shea", "gags", "fist", "ping", "pope", "cora", "yaks", "cosy", "foci", "plan", "colo", "hume", "yowl", "craw", "pied", "toga", "lobs", "love", "lode", "duds", "bled", "juts", "gabs", "fink", "rock", "pant", "wipe", "pele", "suez", "nina", "ring", "okra", "warm", "lyle", "gape", "bead", "lead", "jane", "oink", "ware", "zibo", "inns", "mope", "hang", "made", "fobs", "gamy", "fort", "peak", "gill", "dino", "dina", "tier"};
        //            Set<String> dict = new HashSet<String>(Arrays.asList(strs));
        //            System.out.println("? => " + ladderLength(start, end, dict));
        //        }

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
        //int[] num = {6, -18, -20, -7, -15, 9, 18, 10, 1, -20, -17, -19, -3, -5, -19, 10, 6, -11, 1, -17, -15, 6,
        // 17, -18, -3, 16, 19, -20, -3, -17, -15, -3, 12, 1, -9, 4, 1, 12, -2, 14, 4, -4, 19, -20, 6, 0, -19, 18,
        // 14, 1, -15, -5, 14, 12, -4, 0, -10, 6, 6, -6, 20, -8, -6, 5, 0, 3, 10, 7, -2, 17, 20, 12, 19, -13, -1, 10,
        // -1, 14, 0, 7, -3, 10, 14, 14, 11, 0, -4, -15, -8, 3, 2, -5, 9, 10, 16, -4, -3, -9, -8, -14, 10, 6, 2, -12,
        // -7, -16, -6, 10};
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
