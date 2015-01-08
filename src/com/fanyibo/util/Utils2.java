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
    public static List<List<Integer>> threeSum(int[] num, int sum) {
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

    public static HashMap<List<Integer>, Integer> _combination(int[] num, int start, int count) {
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

    public static Set<List<Integer>> _threeSum(int[] num, int startIndex, int target, int count) {

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
    public static int threeSumClosest(int[] num, int target) {
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
    public static List<List<Integer>> fourSum(int[] num, int target) {
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
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

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

    public static void ReAdjustCandy(int[] ratings, int[] candy, int startIndex) {
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
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

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

    public static Set<List<Integer>> getCombinationSum(int[] candidates, int sum, int start) {

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
    public static List<List<Integer>> combinationSum2(int[] num, int target) {

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

    public static Set<List<Integer>> getUnqiueCombinationSum(int[] candidates, int sum, int start) {

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
    public static List<List<Integer>> combine(int n, int k) {


        if (n <= 0 || k <= 0) {
            return new ArrayList<List<Integer>>();
        }
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 1; i <= n - k + 1; i++) {
            set.addAll(_combinations(n, i, k));
        }
        return new ArrayList<List<Integer>>(set);
    }

    public static List<List<Integer>> _combinations(int n, int start, int k) {

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
    public static int uniquePaths(int m, int n) {

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

        Interval(int s, int e) {
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

    public static void insertInterval(List<Interval> intervals, Interval newInterval) {
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
    public static int numDistinct(String S, String T) {

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
    public static int divide(int dividend, int divisor) {

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
    public static boolean isInterleave(String s1, String s2, String s3) {

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
                interLeave[i][j] = (interLeave[i - 1][j] && (s1.charAt(i - 1) == s3
                        .charAt(i + j - 1))) || (interLeave[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1)));
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
    public static int minDistance(String word1, String word2) {

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
    public static boolean isNeighbor(String word1, String word2) {
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

    public static int ladderLength(String start, String end, Set<String> dict) {

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
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            LadderNode that = (LadderNode) o;

            if (val != null ? !val.equals(that.val) : that.val != null) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = val != null ? val.hashCode() : 0;
            return result;
        }
    }

    public static List<List<String>> findLadders(String start, String end, Set<String> dict) {

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
    public static int calcLongestConsecutive(HashMap<Integer, Boolean> visited, HashMap<Integer, Integer> lengths,
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
     * 1234
     * 1243
     * 1324
     * 1342
     * 1423
     * 1432
     * 2134
     * 2143
     * Given n and k, return the kth permutation sequence.
     * Note: Given n will be between 1 and 9 inclusive.
     */
    public static String getPermutation(int n, int k) {

        if (n == 0) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        StringBuilder builder = new StringBuilder();
        int[] cache = new int[n + 1];
        int size = n;
        int temp = n;
        int numPerHead = 1;
        while (temp > 0) {
            numPerHead *= temp;
            temp--;
        }
        if (k > numPerHead) {
            return "";
        }
        while (builder.length() < size && n > 0) {
            numPerHead = numPerHead / n;
            int times = k / numPerHead;
            k = k % numPerHead;
            if (times == 0 || k != 0) {
                times++;
            }
            if (k == 0) {
                k = numPerHead;
            }
            int i;
            for (i = 1; i < cache.length; i++) {
                if (cache[i] != 1) {
                    times--;
                }
                if (times == 0) {
                    cache[i] = 1;
                    break;
                }
            }
            builder.append(i);
            n--;
        }
        return builder.toString();
    }

    /**
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of
     * numbers.
     * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in
     * ascending order).
     * The replacement must be in-place, do not allocate extra memory.
     * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
     * column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     */
    public static void nextPermutation(int[] num) {

        int i = num.length - 1;
        for (; i > 0; i--) {
            if (num[i] > num[i - 1]) {
                int index = -1;
                for (int j = i + 1; j < num.length; j++) {
                    if (num[j] > num[i - 1] && num[j] < num[i]) {
                        if (index == -1) {
                            index = j;
                        } else if (num[index] > num[j]) {
                            index = j;
                        }
                    }
                }
                if (index == -1) {
                    index = i;
                }
                int temp = num[i - 1];
                num[i - 1] = num[index];
                num[index] = temp;
                break;
            }
        }
        // sort [i, length-1]
        Arrays.sort(num, i, num.length);
    }


    /**
     * Given a collection of numbers, return all possible permutations.
     * For example,
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    public static List<List<Integer>> permute(int[] num) {
        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < num.length; i++) {
            indexes.add(i);
        }
        return permute(num, indexes);
    }

    public static List<List<Integer>> permute(int[] num, List<Integer> indexes) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (indexes.size() == 0) {
        } else if (indexes.size() == 1) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(num[indexes.get(0)]);
            result.add(list);
        } else {
            for (int i = 0; i < indexes.size(); ++i) {
                int headIndex = indexes.get(i);
                List<Integer> newIndexes = new ArrayList<Integer>();
                for (int j = 0; j < indexes.size(); j++) {
                    if (j != i) {
                        newIndexes.add(indexes.get(j));
                    }
                }
                List<List<Integer>> others = permute(num, newIndexes);
                for (List<Integer> list : others) {
                    list.add(0, num[headIndex]);
                    result.add(list);
                }
            }
        }
        return result;
    }

    /**
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     * For example,
     * [1,1,2] have the following unique permutations:
     * [1,1,2], [1,2,1], and [2,1,1].
     */
    public static List<List<Integer>> permuteUnique(int[] num) {

        HashMap<Integer, HashMap<Integer, Boolean>> visited = new HashMap<Integer, HashMap<Integer, Boolean>>();
        for (int i = 0; i < num.length; i++) {
            HashMap<Integer, Boolean> level = new HashMap<Integer, Boolean>();
            for (int j = 1; j <= num.length; j++) {
                level.put(j, false);
            }
            visited.put(num[i], level);
        }

        List<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < num.length; i++) {
            indexes.add(i);
        }
        return permuteUnique(num, indexes, visited);
    }

    public static List<List<Integer>> permuteUnique(int[] num, List<Integer> indexes,
                                                    HashMap<Integer, HashMap<Integer, Boolean>> visited) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (indexes.size() == 0) {
        } else {
            int level = indexes.size();

            if (level == 1) {

                int target = num[indexes.get(0)];
                HashMap<Integer, Boolean> targetMap = visited.get(target);
                if (!targetMap.get(level)) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(target);
                    result.add(list);
                    targetMap.put(level, true);
                }
            } else {
                for (int i = 0; i < level; ++i) {
                    for (Integer key : visited.keySet()) {
                        HashMap<Integer, Boolean> map = visited.get(key);
                        for (Integer _level : map.keySet()) {
                            if (_level < level) {
                                map.put(_level, false);
                            }
                        }
                    }
                    int headIndex = indexes.get(i);
                    int target = num[headIndex];
                    HashMap<Integer, Boolean> targetMap = visited.get(target);
                    if (!targetMap.get(level)) {
                        List<Integer> newIndexes = new ArrayList<Integer>();
                        for (int j = 0; j < level; j++) {
                            if (j != i) {
                                newIndexes.add(indexes.get(j));
                            }
                        }
                        List<List<Integer>> others = permuteUnique(num, newIndexes, visited);
                        for (List<Integer> list : others) {
                            list.add(0, num[headIndex]);
                            result.add(list);
                        }
                        targetMap.put(level, true);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
     * Try to solve it in linear time/space.
     * Return 0 if the array contains less than 2 elements.
     * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
     */
    public static int maximumGap(int[] num) {
        int length = num.length;
        if (length <= 2) {
            return 0;
        }
        int min = num[0];
        int max = num[0];
        for (int i = 0; i < length; i++) {
            int current = num[i];
            if (max < current) {
                max = current;
            }
            if (min > current) {
                min = current;
            }
        }

        int numBuckets = (max - min) / length + 1;
        int[][] buckets = new int[numBuckets][2];

        for (int i = 0; i < length; i++) {
            int current = num[i];
            int iBucket = (int) Math.floor((current - min) / length);
            int[] bucket = buckets[iBucket];
            if (bucket[0] == 0) {
                bucket[0] = current;
            } else if (bucket[0] > current) {
                bucket[0] = current;
            }
            if (bucket[1] == 0) {
                bucket[1] = current;
            } else if (bucket[1] < current) {
                bucket[1] = current;
            }
        }

        int gap = 0;
        int prev = 0;
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i][0] == 0) {
                continue;
            }
            gap = Math.max(gap, buckets[i][0] - buckets[prev][1]);
            prev = i;
        }

        return gap;
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * For example, given s = "aab",
     * Return
     * [
     * ["aa","b"],
     * ["a","a","b"]
     * ]
     */
    // TODO
    public static List<List<String>> partition(String s) {
        return null;
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return the minimum cuts needed for a palindrome partitioning of s.
     * For example, given s = "aab",
     * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
     * d[i,j]
     * if i==j:  d[i,j] = true
     * otherwise d[i,j] = A[i]==A[j] (d[i+1],[j-1])
     */
    public static int minCut(String s) {

        int length = s.length();
        if (length < 2) {
            return 0;
        }
        int[] d = new int[length];
        boolean[][] v = new boolean[length][length];
        d[length - 1] = length - 1;
        for (int i = length - 1; i >= 0; i--) {
            d[i] = i;
            for (int j = i; j < length; j++) {
                if (i == j) {
                    v[i][j] = true;
                } else if (i + 1 == j) {
                    v[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    v[i][j] = s.charAt(i) == s.charAt(j) && v[i + 1][j - 1];
                }
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = i; j >= 0; j--) {
                if (v[j][i]) {
                    if (j == 0) {
                        d[i] = 0;
                    } else {
                        d[i] = Math.min(d[i], d[j - 1] + 1);
                    }
                }
            }
        }
        return d[length - 1];
    }

    /**
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated
     * sequence of one or more dictionary words.
     * For example, given
     * s = "leetcode",
     * dict = ["leet", "code"].
     * Return true because "leetcode" can be segmented as "leet code".
     */
    public static boolean wordBreak1(String s, Set<String> dict) {

        int length = s.length();
        int dictSize = dict.size();
        if (length == 0 && dictSize == 0) {
            return true;
        } else if (length == 0 || dictSize == 0) {
            return false;
        }
        boolean[] d = new boolean[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                String current = s.substring(j, i + 1);
                d[i] = dict.contains(current) && (j == 0 || d[j - 1]);
                if (d[i]) {
                    break;
                }
            }
        }
        return d[length - 1];
    }

    /**
     * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a
     * valid dictionary word.
     * Return all such possible sentences.
     * For example, given
     * s = "catsanddog",
     * dict = ["cat", "cats", "and", "sand", "dog"].
     * A solution is ["cats and dog", "cat sand dog"].
     */
    public static List<String> wordBreak2(String s, Set<String> dict) {

        int length = s.length();
        int dictSize = dict.size();
        if (length == 0 || dictSize == 0) {
            return new ArrayList<String>();
        }
        HashMap<Integer, List<String>> combinations = new HashMap<Integer, List<String>>();
        boolean[] b = new boolean[length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = length - 1; j >= i; j--) {
                String current = s.substring(i, j + 1);
                boolean leftValid = dict.contains(current);
                boolean rightValid = (j == length - 1) || b[j + 1];
                if (leftValid && rightValid) {
                    b[i] = true;
                    break;
                }
            }
        }
        for (int i = length - 1; i >= 0; i--) {
            List<String> _combinations = new ArrayList<String>();
            combinations.put(i, _combinations);
            if (!b[i]) {
                continue;
            }
            for (int j = length - 1; j >= i; j--) {
                String current = s.substring(i, j + 1);
                if (!dict.contains(current)) {
                    continue;
                }
                if (j == length - 1) {
                    _combinations.add(current);
                } else {
                    List<String> list = combinations.get(j + 1);
                    for (String str : list) {
                        _combinations.add(current + " " + str);
                    }
                }
            }
        }
        return combinations.get(0);

        /*
         int length = s.length();
         int dictSize = dict.size();
         if (length == 0 || dictSize == 0) {
         return new ArrayList<String>();
         }
         HashMap<Integer, List<String>> combinations = new HashMap<Integer, List<String>>();
         boolean[] b = new boolean[length];
         for (int i = length - 1; i >= 0; i--) {
         List<String> _combinations = new ArrayList<String>();
         for (int j = length - 1; j >= i; j--) {
         String current = s.substring(i, j + 1);
         boolean leftValid = dict.contains(current);
         boolean rightValid = (j == length - 1) || b[j + 1];
         if (leftValid && rightValid) {
         b[i] = true;
         if (j == length - 1) {
         _combinations.add(current);
         } else {
         List<String> list = combinations.get(j + 1);
         for (String str : list) {
         _combinations.add(current + " " + str);
         }
         }
         }
         }
         combinations.put(i, _combinations);
         }
         return combinations.get(0);
         */
    }

    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     * For example,
     * Given 1->2->3->4->5->6, you should return the list as 2->1->4->3->6->5.
     * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself
     * can be changed.
     */
    public static ListNode swapPairs(ListNode head) {

        ListNode newHead = head;
        ListNode oLeftFather = null;
        ListNode oLeft = head;
        ListNode oRight = oLeft == null ? null : oLeft.next;
        ListNode oRightChild = oRight == null ? null : oRight.next;

        while (oLeft != null && oRight != null) {
            if (oLeftFather != null) {
                oLeftFather.next = oRight;
            } else {
                newHead = oRight;
            }
            oRight.next = oLeft;
            oLeft.next = oRightChild;

            oRight = oLeft;
            oLeftFather = oRight;
            oLeft = oLeftFather.next;
            if (oLeft == null) {
                break;
            } else {
                oRight = oLeft.next;
                if (oRight != null) {
                    oRightChild = oRight.next;
                }
            }
        }
        return newHead;
    }

    /**
     * Search Insert Position
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index
     * where it would be if it were inserted in order.
     * You may assume no duplicates in the array.
     * Here are few examples.
     * [1,3,5,6], 5 → 2
     * [1,3,5,6], 2 → 1
     * [1,3,5,6], 7 → 4
     * [1,3,5,6], 0 → 0
     */
    public static int searchInsert(int[] A, int target) {
        int length = A.length;
        if (length == 0) {
            return 0;
        } else if (length == 1) {
            return A[0] >= target ? 0 : 1;
        }
        return searchInsert(A, target, 0, length - 1);
    }

    public static int searchInsert(int[] A, int target, int start, int end) {

        int length = end - start + 1;
        if (start > end) {
            return end + 1;
        }
        if (length <= 2) {
            if (target <= A[start]) {
                return start;
            } else {
                if (target <= A[end]) {
                    return end;
                } else {
                    return end + 1;
                }
            }
        }
        int mid = start + (length / 2);
        if (target <= A[mid]) {
            return searchInsert(A, target, start, mid);
        } else {
            return searchInsert(A, target, mid + 1, end);
        }
    }

    /**
     * Substring with Concatenation of All Words
     * You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices
     * of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening
     * characters.
     * For example, given:
     * S: "barfoothefoobarman"
     * L: ["foo", "bar"]
     * You should return the indices: [0,9].
     * (order does not matter).
     */
    public static List<Integer> findSubstring(String S, String[] L) {

        List<Integer> result = new ArrayList<Integer>();
        int strLen = S.length();
        int dicLen = L.length;
        if (dicLen == 0 || strLen == 0) {
            return result;
        }
        int singleLen = L[0].length();
        int totalLen = singleLen * dicLen;
        if (totalLen > strLen) {
            return result;
        }

        HashMap<String, Integer> words = new HashMap<String, Integer>();
        for (String aL : L) {
            if (words.containsKey(aL)) {
                words.put(aL, words.get(aL) + 1);
            } else {
                words.put(aL, 1);
            }
        }

        for (int i = 0; i <= strLen - totalLen; ++i) {
            HashMap<String, Integer> wordUsed = new HashMap<String, Integer>();
            boolean valid = true;
            for (int j = 0; j < dicLen; j++) {
                String curWord = S.substring(i + j * singleLen, i + j * singleLen + singleLen);
                if (!words.containsKey(curWord)) {
                    valid = false;
                    break;
                }
                if (wordUsed.containsKey(curWord)) {
                    wordUsed.put(curWord, wordUsed.get(curWord) + 1);
                } else {
                    wordUsed.put(curWord, 1);
                }
                if (wordUsed.get(curWord) > words.get(curWord)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                result.add(i);
            }
        }
        return result;
    }


    /**
     * Sort Colors
     * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are
     * adjacent, with the colors in the order red, white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * Note:
     * You are not suppose to use the library's sort function for this problem.
     * click to show follow up.
     * Follow up:
     * A rather straight forward solution is a two-pass algorithm using counting sort.
     * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
     * then 1's and followed by 2's.
     * Could you come up with an one-pass algorithm using only constant space?
     */
    public static void sortColors(int[] A) {

        if (A.length < 2) {
            return;
        }
        int index1 = 0;
        int index2 = A.length - 1;
        for (int i = 0; index1 <= index2 && i <= index2; ) {
            if (A[i] == 0) {
                int temp = A[index1];
                A[index1] = A[i];
                A[i] = temp;
                index1++;
                i++;
            } else if (A[i] == 1) {
                i++;
            } else if (A[i] == 2) {
                int temp = A[index2];
                A[index2] = A[i];
                A[i] = temp;
                index2--;
            } else {
                System.out.println("Invalid Number!");
                return;
            }
        }
    }

    /**
     * Sort List
     * Sort a linked list in O(n log n) time using constant space complexity.
     */
    public static ListNode sortList(ListNode head) {

        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        if (size == 1) {
            return head;
        }
        int half = size / 2 - 1;
        ListNode leftLast = head;
        while (half > 0) {
            leftLast = leftLast.next;
            half--;
        }
        ListNode rightHead = leftLast.next;
        leftLast.next = null;

        ListNode head1 = sortList(head);
        ListNode head2 = sortList(rightHead);

        return mergeLinkedList(head1, head2);
    }

    public static ListNode mergeLinkedList(ListNode head1, ListNode head2) {

        ListNode temp = null;
        ListNode newHead = null;
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while (temp1 != null && temp2 != null) {
            int leftval = temp1.val;
            int rightval = temp2.val;
            if (leftval <= rightval) {
                if (temp == null) {
                    newHead = temp1;
                    temp = newHead;
                } else {
                    temp.next = temp1;
                    temp = temp.next;
                }
                temp1 = temp1.next;
            } else {
                if (temp == null) {
                    newHead = temp2;
                    temp = newHead;
                } else {
                    temp.next = temp2;
                    temp = temp.next;
                }
                temp2 = temp2.next;
            }
        }
        if (temp1 == null && temp2 != null) {
            temp.next = temp2;
        } else if (temp1 != null && temp2 == null) {
            temp.next = temp1;
        }
        return newHead;
    }

    /**
     * Spiral Matrix
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * For example,
     * Given the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * You should return [1,2,3,6,9,8,7,4,5].
     */
    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<Integer>();
        int m = matrix.length;
        if (m == 0) {
            return result;
        }
        int n = matrix[0].length;
        if (n == 0) {
            return result;
        }
        boolean[][] used = new boolean[m][n];
        int i = 0;
        int j = 0;
        while (!used[i][j]) {
            used[i][j] = true;
            result.add(matrix[i][j]);

            boolean up = i > 0 && !used[i - 1][j];
            boolean down = i < m - 1 && !used[i + 1][j];
            boolean left = j > 0 && !used[i][j - 1];
            boolean right = j < n - 1 && !used[i][j + 1];

            if (!up && !down && !left && !right) {
                break;
            } else if (!up && down && !left && right) {
                j++;
            } else if (!up && down && left && !right) {
                i++;
            } else if (up && !down && left && !right) {
                j--;
            } else if (up && !down && !left && right) {
                i--;
            } else if (!up && !down && !left && right) {
                j++;
            } else if (!up && !down && left && !right) {
                j--;
            } else if (!up && down && !left && !right) {
                i++;
            } else if (up && !down && !left && !right) {
                i--;
            }
        }
        return result;
    }

    /**
     * Spiral Matrix II
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * For example,
     * Given n = 3,
     * You should return the following matrix:
     * [
     * [ 1, 2, 3 ],
     * [ 8, 9, 4 ],
     * [ 7, 6, 5 ]
     * ]
     */
    public static int[][] generateMatrix(int n) {

        if (n == 0) {
            return new int[0][0];
        }
        int[][] result = new int[n][n];
        int i = 0;
        int j = 0;
        int val = 1;
        while (result[i][j] == 0) {
            result[i][j] = val++;
            boolean up = i > 0 && result[i - 1][j] == 0;
            boolean down = i < n - 1 && result[i + 1][j] == 0;
            boolean left = j > 0 && result[i][j - 1] == 0;
            boolean right = j < n - 1 && result[i][j + 1] == 0;
            if (!up && !down && !left && !right) {
                break;
            } else if (!up && down && !left && right) {
                j++;
            } else if (!up && down && left && !right) {
                i++;
            } else if (up && !down && left && !right) {
                j--;
            } else if (up && !down && !left && right) {
                i--;
            } else if (!up && !down && !left && right) {
                j++;
            } else if (!up && !down && left && !right) {
                j--;
            } else if (!up && down && !left && !right) {
                i++;
            } else if (up && !down && !left && !right) {
                i--;
            }
        }
        return result;
    }

    /**
     * Search for a Range
     * Given a sorted array of integers, find the starting and ending position of a given target value.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * If the target is not found in the array, return [-1, -1].
     * For example,
     * Given [5, 7, 7, 8, 8, 10] and target value 8,
     * return [3, 4].
     */
    public static int[] searchRange(int[] A, int target) {

        int[] result = {-1, -1};
        int size = A.length;
        if (size == 0) {
            return result;
        } else



        return null;
    }


    public static void PRINT(Object obj) {
        System.out.println(obj.toString());
    }

    public static void main(String[] args) {

        int[][] A = {{1, 2, 3, 4, 5, 6}, {18, 19, 20, 21, 22, 7}, {17, 28, 29, 30, 23, 8}, {16, 27, 26, 25, 24, 9},
                     {15, 14, 13, 12, 11, 10}};
        int[][] result = generateMatrix(5);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
