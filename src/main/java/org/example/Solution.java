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
        int[] res = {-1, -1};
        if (nums.length == 0) {
            return res;
        }
        int left = 0;
        int right = nums.length - 1;
        int l1 = left;
        int r2 = right;
        int i = bisearch(nums, target, left, right);
        if (i == -1) {
            return res;
        }
        res[0] = i;
        res[1] = i;

        int l2 = i - 1;
        int r1 = i + 1;
        //查询l
        while (l1 <= l2) {
            int ii = (l1 + l2) / 2;
            if (nums[ii] < target) {
                l1 = ii + 1;
            } else if (nums[ii] > target) {
                l2 = ii - 1;
            } else {
                res[0] = ii;
                l2 = ii - 1;
            }
        }
        //查询r
        while (r1 <= r2) {
            int ii = (r1 + r2) / 2;
            if (nums[ii] < target) {
                r1 = ii + 1;
            } else if (nums[ii] > target) {
                r2 = ii - 1;
            } else {
                res[1] = ii;
                r1 = ii + 1;
            }
        }
        return res;
    }

    public int mySqrt69(int x) {
        if(x<2){
            return x;
        }
        int left = 1;
        int right = x;
        while (left+1 < right) {
            int i = (left + right) >>1;
            if(x/i==i){
                return i;
            }else if(x/i<i){
                right=i;
            }else{ //<x
                left=i;
            }
        }
        return left;
    }

    public boolean isPerfectSquare367(int num) {
        if(num<2){
            return true;
        }
        int left = 1;
        int right = num;
        while (left+1 < right) {
            int i = (left + right) >>1;
            if(num/i==i){
                return i * i == num;
            }else if(num/i<i){
                right=i;
            }else{ //<x
                left=i;
            }
        }
        return false;
    }

    public int removeElement27_1(int[] nums, int val) {
        int l=nums.length;
        for(int i=0;i<l;i++){
            if(val==nums[i]){
                //后面所有都往前移一位
                for(int j=i+1;j<l;j++){
                    nums[j-1]=nums[j];
                }
                i--;
                l--;
            }
        }
        return l;
    }
    public int removeElement27_2(int[] nums, int val) {
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            while (left<=right&&nums[left]!=val){
                left++;
            }
            while(left<=right&&nums[right]==val){
                right--;
            }
            if(left<right) {
                nums[left] = nums[right];
                left++;
                right--;
            }
        }
        return left;
    }

    public int removeDuplicates26_1(int[] nums) {
        int i=0;
        int num=0;
        int l=nums.length;
        int tmp=-10001;
        for(;i<l;i++){
            if(tmp!=nums[i]){
                tmp=nums[i];
                num++;
            }else{
                for(int j=i+1;j<l;j++){
                    nums[j-1]=nums[j];
                }
                l--;
                i--;
            }
        }
        return num;
    }

    public int removeDuplicates26_2(int[] nums) {
        int l=nums.length;
        if (l==1){
            return 1;
        }
        int i0=0;
        int i1=1;
        while(i1<l){
            while(i1<l && nums[i1]==nums[i0]){
                i1++;
            }
            if(i1<l && nums[i0]!=nums[i1]){
                nums[i0+1]=nums[i1];
                i0++;
                i1++;
            }
        }
        return i0+1;
    }

    public void moveZeroes283(int[] nums) {
        int l=nums.length;
        if(l<2){
            return;
        }
        int i0=0;
        int i1=-1;
        int cnt=0;
        for(;i0<l;i0++){
            if(nums[i0]==0){
                cnt++;
                if(i1==-1){
                    i1=i0;
                }
            }else{
                if(i1!=-1){
                    nums[i1]=nums[i0];
                    i1++;
                }
            }
        }
        for(int i=0;i<cnt;i++){
            nums[l-i-1]=0;
        }
    }

    public boolean backspaceCompare844(String s, String t) {
        int i=s.length()-1;
        int j=t.length()-1;
        int skipS=0;
        int skipT=0;

        while(i>=0 || j>=0){
            char cs=' ';
            char cT=' ';
            while(i>=0){
                if(s.charAt(i)=='#'){
                    skipS++;
                    i--;
                }else{
                    if(skipS>0){
                        skipS--;
                        i--;
                    }else{
                        cs=s.charAt(i);
                        break;
                    }
                }
            }
            while(j>=0){
                if(t.charAt(j)=='#'){
                    skipT++;
                    j--;
                }else{
                    if(skipT>0){
                        skipT--;
                        j--;
                    }else{
                        cT=t.charAt(j);
                        break;
                    }
                }
            }
            if(cs==cT){
                i--;
                j--;
            }else{
                return false;
            }
        }
        return true;

    }

    public int[] sortedSquares977(int[] nums) {
        int l=nums.length;
        int[] res=new int[l];
        int i=0;
        int j=nums.length-1;
        if(nums[i]>=0&&nums[j]>=0){
            for(int ii=i;ii<=j;ii++){
                res[ii]=nums[ii]*nums[ii];
            }
            return res;
        }else if(nums[i]<0&&nums[j]<0){
            for(int ii=i;ii<=j;ii++){
                res[ii]=nums[i+j-ii]*nums[i+j-ii];
            }
            return res;
        }
        while(i<=j){
            l--;
            if(nums[i]+nums[j]>0){
                res[l]=nums[j]*nums[j];
                j--;
            }else{
                res[l]=nums[i]*nums[i];
                i++;
            }
        }
        return res;
    }

    public int minSubArrayLen209(int target, int[] nums) {
        int res=nums.length+1;
        int left=0;//记录需要减掉的内容
        int right=0;
        int sum=0;
        while(right<nums.length){
            if(sum<target) {
                sum += nums[right];
            }
            if(sum>=target){//这里可以用while减少循环次数
                sum-=nums[left];
//                sum-=nums[right];
                res=Math.min(res,right-left+1);
                left++;
                if(left>right){
                    right++;
                }
            }else{
                right++;
            }
        }
        if(res<nums.length+1) {
            return res;
        }else {
            return 0;
        }
    }

    public int totalFruit904(int[] fruits) {
        int res=0;
        int l=fruits.length;
        int[] fruitRecords=new int[l];//默认0为未选中
        int getType=0;//选中的种类，超过2就要操作了
        int left=0;//窗口左边界
        int right=0;//窗口右边界
        int changePoint=0;//变动点(用于重定向窗口左边界)
        int tmp=-1;//用于判断是否发生变动
        for(;right<l;right++){
            int fruit=fruits[right];
            if(fruitRecords[fruit]==0){
                fruitRecords[fruit]=1;
                getType++;
                if (getType>2){//要踢掉最前面的
                    res=Math.max(res,right-left);//解算上一窗口数量
                    fruitRecords[fruits[changePoint-1]]=0;
                    getType--;
                    left=changePoint;
                }
            }
            if(fruit!=tmp){//发生变动
                tmp=fruit;
                changePoint=right;
            }
        }
        if(right==l){
            res=Math.max(res,right-left);
        }
        return res;
    }



    public void replaceArr(int[] nums,int i,int j){
        if(i==j){
            return;
        }
        if(j>=nums.length||i>=nums.length){
            return;
        }
        int tmp=nums[j];
        nums[j]=nums[i];
        nums[i]=tmp;
    }

    /**
     * 折半查找有序数组
     * 返回target在nums中从left到right元素中存在的位置，没有则返回-1
     *
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
            } else if (nums[i] > target) {
                right = i - 1;
            } else {
                return i;
            }
        }
        return -1;
    }
}
