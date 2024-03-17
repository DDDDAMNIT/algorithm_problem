package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[][] arr=new int[10][];
        int[] arr1=new int[10];
        Solution solution=new Solution();
        int[] strs={-1,0,1,2,-1,-4};
        String s1="sadbutsad";
        String s2="sad";
        TreeNode root=createTreeNode(null);
        solution.averageOfLevels637(root);
    }

    private static TreeNode createTreeNode(String list){
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(9);
        root.right=new TreeNode(20);
        root.right.left=new TreeNode(15);
        root.right.right=new TreeNode(7);
        return root;
    }
}