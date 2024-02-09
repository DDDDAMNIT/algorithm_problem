package org.example;

/**
 * LeetCode
 */
class Solution {
    public int search704(int[] nums, int target) {
        int l = nums.length;
        if (l == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = l - 1;
        while (left <= right) {
            int i = (left + right) / 2;
            if (nums[i] == target) {
                return i;
            } else if (nums[i] < target) {
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return -1;
    }

    public int searchInsert35(int[] nums, int target) {
        int l = nums.length;
        int left = 0;
        int right = l - 1;
        int i = 0;
        int j = 0;
        while (left <= right) {
            i = (left + right) / 2;
            if (nums[i] == target) {
                return i;
            } else if (nums[i] < target) {
                left = i + 1;
                j = left;
            } else {
                right = i - 1;
                j = i;
            }
        }
        return Math.max(j, 0);
    }

    public int[] searchRange34(int[] nums, int target) {
        int[] res= {-1,-1};
        if (nums.length==0){
            return res;
        }
        int left=0;
        int right=nums.length-1;
        int l1=left;
        int r2=right;
        int i=bisearch(nums,target,left,right);
        if(i==-1){
            return res;
        }
        res[0]=i;
        res[1]=i;

        int l2=i-1;
        int r1=i+1;
        //查询l
        while (l1 <= l2) {
            int ii = (l1 + l2) / 2;
            if (nums[ii] < target) {
                l1 = ii + 1;
            } else if (nums[ii] > target){
                l2 = ii - 1;
            } else {
                res[0]=ii;
                l2 = ii - 1;
            }
        }
        //查询r
        while (r1 <= r2) {
            int ii = (r1 + r2) / 2;
            if (nums[ii] < target) {
                r1 = ii + 1;
            } else if (nums[ii] > target){
                r2 = ii - 1;
            } else {
                res[1]=ii;
                r1=ii+1;
            }
        }
        return res;
    }

    /**
     * 折半查找有序数组
     * 返回target在nums中从left到right元素中存在的位置，没有则返回-1
     * @param nums
     * @param target
     * @param left
     * @param right
     * @return
     */
    public int bisearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int i = (left + right) / 2;
            if (nums[i] < target) {
                left = i + 1;
            } else if (nums[i] > target){
                right = i - 1;
            } else {
                return i;
            }
        }
        return -1;
    }
}
