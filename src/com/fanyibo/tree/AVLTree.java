/*
 *    Copyright   2014
 *     Filename : AVLTree
 *      Project : TestProject
 *   Created by : fanyibo on 12/23/14 2:01 PM
 */
package com.fanyibo.tree;

public class AVLTree {

    public TreeNode root;

    public AVLTree() {
        this.root = null;

    }

    public void insert(int v) {

        TreeNode newNode = new TreeNode(v);
        if (root == null) {
            root = newNode;
            return;
        }
        if (root.val <= v) {
            if (root.right == null) {
                root.right = newNode;
            } else {
                _insert(root.right, newNode);
                selfBalance();
            }
        } else {
            if (root.left == null) {
                root.left = newNode;
            } else {
                _insert(root.left, newNode);
                selfBalance();
            }
        }
    }

    private void _insert(TreeNode node, TreeNode target) {

        if (node.val <= target.val) {
            if (node.right == null) {
                node.right = target;
            } else {
                _insert(node.right, target);
            }
        } else {
            if (node.left == null) {
                node.left = target;
            } else {
                _insert(node.left, target);
            }
        }
    }

    public void updateBalance(TreeNode node) {

    }

    public void selfBalance() {

    }

    public int balanceFactor(TreeNode node) {

        int maxLeft = 0;
        int currLeft = 0;
        maxLeft = dfsHeight(node.left, currLeft);

        int maxRight = 0;
        int currRight = 0;
        maxRight = dfsHeight(node.right, currRight);

        return maxLeft - maxRight;
    }

    public int dfsHeight(TreeNode node, int curr) {

        if (node == null) {
            return curr;
        }

        curr++;
        int max;
        if (node.left == null && node.right == null) {
            max = curr;
        } else {
            max = Math.max(dfsHeight(node.left, curr), dfsHeight(node.right, curr));
        }

        curr--;
        return max;
    }

    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);

        n6.left = n2;
        n6.right = n8;
        n2.left = n1;
        n2.right = n4;
        n4.left = n3;
        n8.left = n7;
        n8.right = n9;

        AVLTree avlTree = new AVLTree();

        System.out.println(avlTree.balanceFactor(n6));

    }
}
