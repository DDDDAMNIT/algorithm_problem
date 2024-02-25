package org.example;

import java.util.*;

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
        if (x < 2) {
            return x;
        }
        int left = 1;
        int right = x;
        while (left + 1 < right) {
            int i = (left + right) >> 1;
            if (x / i == i) {
                return i;
            } else if (x / i < i) {
                right = i;
            } else { //<x
                left = i;
            }
        }
        return left;
    }

    public boolean isPerfectSquare367(int num) {
        if (num < 2) {
            return true;
        }
        int left = 1;
        int right = num;
        while (left + 1 < right) {
            int i = (left + right) >> 1;
            if (num / i == i) {
                return i * i == num;
            } else if (num / i < i) {
                right = i;
            } else { //<x
                left = i;
            }
        }
        return false;
    }

    public int removeElement27_1(int[] nums, int val) {
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            if (val == nums[i]) {
                //后面所有都往前移一位
                for (int j = i + 1; j < l; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;
                l--;
            }
        }
        return l;
    }

    public int removeElement27_2(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] != val) {
                left++;
            }
            while (left <= right && nums[right] == val) {
                right--;
            }
            if (left < right) {
                nums[left] = nums[right];
                left++;
                right--;
            }
        }
        return left;
    }

    public int removeDuplicates26_1(int[] nums) {
        int i = 0;
        int num = 0;
        int l = nums.length;
        int tmp = -10001;
        for (; i < l; i++) {
            if (tmp != nums[i]) {
                tmp = nums[i];
                num++;
            } else {
                for (int j = i + 1; j < l; j++) {
                    nums[j - 1] = nums[j];
                }
                l--;
                i--;
            }
        }
        return num;
    }

    public int removeDuplicates26_2(int[] nums) {
        int l = nums.length;
        if (l == 1) {
            return 1;
        }
        int i0 = 0;
        int i1 = 1;
        while (i1 < l) {
            while (i1 < l && nums[i1] == nums[i0]) {
                i1++;
            }
            if (i1 < l && nums[i0] != nums[i1]) {
                nums[i0 + 1] = nums[i1];
                i0++;
                i1++;
            }
        }
        return i0 + 1;
    }

    public void moveZeroes283(int[] nums) {
        int l = nums.length;
        if (l < 2) {
            return;
        }
        int i0 = 0;
        int i1 = -1;
        int cnt = 0;
        for (; i0 < l; i0++) {
            if (nums[i0] == 0) {
                cnt++;
                if (i1 == -1) {
                    i1 = i0;
                }
            } else {
                if (i1 != -1) {
                    nums[i1] = nums[i0];
                    i1++;
                }
            }
        }
        for (int i = 0; i < cnt; i++) {
            nums[l - i - 1] = 0;
        }
    }

    public boolean backspaceCompare844(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            char cs = ' ';
            char cT = ' ';
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else {
                    if (skipS > 0) {
                        skipS--;
                        i--;
                    } else {
                        cs = s.charAt(i);
                        break;
                    }
                }
            }
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else {
                    if (skipT > 0) {
                        skipT--;
                        j--;
                    } else {
                        cT = t.charAt(j);
                        break;
                    }
                }
            }
            if (cs == cT) {
                i--;
                j--;
            } else {
                return false;
            }
        }
        return true;

    }

    public int[] sortedSquares977(int[] nums) {
        int l = nums.length;
        int[] res = new int[l];
        int i = 0;
        int j = nums.length - 1;
        if (nums[i] >= 0 && nums[j] >= 0) {
            for (int ii = i; ii <= j; ii++) {
                res[ii] = nums[ii] * nums[ii];
            }
            return res;
        } else if (nums[i] < 0 && nums[j] < 0) {
            for (int ii = i; ii <= j; ii++) {
                res[ii] = nums[i + j - ii] * nums[i + j - ii];
            }
            return res;
        }
        while (i <= j) {
            l--;
            if (nums[i] + nums[j] > 0) {
                res[l] = nums[j] * nums[j];
                j--;
            } else {
                res[l] = nums[i] * nums[i];
                i++;
            }
        }
        return res;
    }

    public int minSubArrayLen209(int target, int[] nums) {
        int res = nums.length + 1;
        int left = 0;//记录需要减掉的内容
        int right = 0;
        int sum = 0;
        while (right < nums.length) {
            if (sum < target) {
                sum += nums[right];
            }
            if (sum >= target) {//这里可以用while减少循环次数
                sum -= nums[left];
//                sum-=nums[right];
                res = Math.min(res, right - left + 1);
                left++;
                if (left > right) {
                    right++;
                }
            } else {
                right++;
            }
        }
        if (res < nums.length + 1) {
            return res;
        } else {
            return 0;
        }
    }

    public int totalFruit904(int[] fruits) {
        int res = 0;
        int l = fruits.length;
        int[] fruitRecords = new int[l];//默认0为未选中
        int getType = 0;//选中的种类，超过2就要操作了
        int left = 0;//窗口左边界
        int right = 0;//窗口右边界
        int changePoint = 0;//变动点(用于重定向窗口左边界)
        int tmp = -1;//用于判断是否发生变动
        for (; right < l; right++) {
            int fruit = fruits[right];
            if (fruitRecords[fruit] == 0) {
                fruitRecords[fruit] = 1;
                getType++;
                if (getType > 2) {//要踢掉最前面的
                    res = Math.max(res, right - left);//解算上一窗口数量
                    fruitRecords[fruits[changePoint - 1]] = 0;
                    getType--;
                    left = changePoint;
                }
            }
            if (fruit != tmp) {//发生变动
                tmp = fruit;
                changePoint = right;
            }
        }
        if (right == l) {
            res = Math.max(res, right - left);
        }
        return res;
    }

    //todo 这个题做的不好，耗时太多了，后面可以修改修改
    public String minWindow76(String s, String t) {
        Map<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (mapT.containsKey(c)) {
                mapT.put(c, mapT.get(c) + 1);
            } else {
                mapT.put(c, 1);
            }
        }
        int left = 0;
        int right = 0;
        int res = s.length() + 1;
        int ansLeft = 0;
        int ansRight = 0;
        for (; right < s.length(); right++) {
            char cR = s.charAt(right);
            if (mapT.containsKey(cR)) {
                mapT.put(cR, mapT.get(cR) - 1);
                //并判断是否达标
                if (check76(mapT)) {
                    while (left <= right) {
                        char cL = s.charAt(left);
                        if (mapT.containsKey(cL)) {
                            mapT.put(cL, mapT.get(cL) + 1);
                            if (!check76(mapT)) {
                                int ans = right - left + 1;
                                if (res >= ans) {
                                    res = ans;
                                    ansLeft = left;
                                    ansRight = right;
                                }
                                mapT.put(cL, mapT.get(cL) - 1);
                                break;
                            } else {
                                int ans = right - left + 1;
                                res = res < ans ? res : ans;
                            }
                        }
                        left++;
                    }
                } else {
                    continue;
                }
            }
        }
        return res == s.length() + 1 ? "" : s.substring(ansLeft, ansRight + 1);
    }

    public boolean check76(Map<Character, Integer> mapT) {
        for (Map.Entry<Character, Integer> entry : mapT.entrySet()) {
            int val = entry.getValue();
            if (val > 0) {
                return false;
            }
        }
        return true;
    }

    public int[][] generateMatrix59(int n) {
        if (n == 1) {
            return new int[][]{{1}};
        }
        int[][] res = new int[n][n];
        int circle = n / 2 + n % 2;
        int stepLength = n - 1;
        int direction = 0 % 4;
        int i = 0;
        int j = 0;
        int in = 1;
        //0 i不变j每次+1
        //1 j不变i每次+1
        //2 i不变j每次-1
        //3 j不变i每次-1
        while (stepLength >= 0) {
            if (stepLength == 0) {
                res[i][j] = in;
                break;
            }
            for (int s = 0; s < stepLength && in <= n * n; s++) {
                res[i][j] = in;
                in++;
                if (direction == 0) {
                    j++;
                } else if (direction == 1) {
                    i++;
                } else if (direction == 2) {
                    j--;
                } else if (direction == 3) {
                    i--;
                }
            }
            direction++;
            if (direction >= 4) {
                j++;
                i++;
                direction = 0;
                stepLength -= 2;
            }
        }
        return res;
    }

    public List<Integer> spiralOrder54(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int down = matrix.length - 1;
        int right = matrix[0].length - 1;
        int sum = (right + 1) * (down + 1);
        int i = 0, j = 0;
        int in = 1;
        while (down > 0 || right > 0) {
            if (right > 0) {
                for (int s = 0; s < right && in <= sum; s++) {
                    list.add(matrix[i][j]);
                    j++;
                    in++;
                }

            }
            if (down > 0) {
                for (int s = 0; s < down && in <= sum; s++) {
                    list.add(matrix[i][j]);
                    i++;
                    in++;
                }

            }
            if (right > 0) {
                for (int s = 0; s < right && in <= sum; s++) {
                    list.add(matrix[i][j]);
                    j--;
                    in++;
                }

            }
            if (down > 0) {
                for (int s = 0; s < down && in <= sum; s++) {
                    list.add(matrix[i][j]);
                    i--;
                    in++;
                }
            }
            i++;
            j++;
            down -= 2;
            right -= 2;
        }
        if (down == 0 && right == 0) {
            list.add(matrix[i][j]);
        }
        return list;
    }

    public int[] spiralArrayLCR146(int[][] array) {
        if (array.length == 0) {
            return new int[0];
        }
        int down = array.length - 1;
        int right = array[0].length - 1;
        int sum = (right + 1) * (down + 1);
        int[] res = new int[sum];
        int i = 0, j = 0;
        int in = 0;
        while (down > 0 || right > 0) {
            if (right > 0) {
                for (int s = 0; s < right && in < sum; s++) {
                    res[in] = array[i][j];
                    j++;
                    in++;
                }

            }
            if (down > 0) {
                for (int s = 0; s < down && in < sum; s++) {
                    res[in] = array[i][j];
                    i++;
                    in++;
                }

            }
            if (right > 0) {
                for (int s = 0; s < right && in < sum; s++) {
                    res[in] = array[i][j];
                    j--;
                    in++;
                }

            }
            if (down > 0) {
                for (int s = 0; s < down && in < sum; s++) {
                    res[in] = array[i][j];
                    i--;
                    in++;
                }
            }
            i++;
            j++;
            down -= 2;
            right -= 2;
        }
        if (down == 0 && right == 0) {
            res[sum - 1] = array[i][j];
        }
        return res;
    }

    public ListNode removeElements203_1(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return dummy.next;
    }

    public ListNode removeElements203_2(ListNode head, int val) {
        //判断原本的头节点是否符合条件
        if (head == null) {
            return head;
        }
        while (head != null && head.val == val) {
            head = head.next;
        }
        //遍历后面德节点
        ListNode now = head;
        while (now != null) {
            while (now.next != null && now.next.val == val) {
                now.next = now.next.next;
            }
            now = now.next;
        }
        return head;
    }

    public ListNode reverseList206(ListNode head) {
        //双指针，每次后面的指向前面的
        ListNode tmp=null;
        ListNode dn1=null;
        ListNode dn2=head;
        while(dn2!=null){
            tmp=dn2.next;
            dn2.next=dn1;
            dn1=dn2;
            dn2=tmp;
        }
        return dn1;
    }

    public ListNode swapPairs24(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode dummy=new ListNode(-1,head);
        ListNode tmp=null;
        ListNode dn1=dummy;
        ListNode dn2=head;
        while(dn2!=null&&dn2.next!=null){
            dn1.next=dn2.next;
            tmp=dn2.next.next;
            dn2.next.next=dn2;
            dn2.next=tmp;
            dn1=dn2;
            dn2=dn2.next;
        }
        return dummy.next;
    }

    public ListNode removeNthFromEnd19(ListNode head, int n) {
        ListNode dummy=new ListNode(-1,head);
        ListNode dn1=dummy;
        ListNode dn2=dummy;
        while(n>0){
            dn1=dn1.next;
            n--;
        }
        while(dn1.next!=null){
            dn1=dn1.next;
            dn2=dn2.next;
        }
        dn2.next=dn2.next.next;
        return dummy.next;
    }

    public ListNode getIntersectionNode160(ListNode headA, ListNode headB) {
        ListNode dn1=headA;
        ListNode dn2=headB;
        int l1=0,l2=0;
        while(dn1!=null){
            dn1=dn1.next;
            l1++;
        }
        while(dn2!=null){
            dn2=dn2.next;
            l2++;
        }
        if(dn1!=dn2){
            return null;
        }
        int diff=0;
        if(l1>=l2){
            diff=l1-l2;//1先走
            dn1=headA;
            dn2=headB;
            while(diff-->0){
                dn1=dn1.next;
            }
            while(l2-->0){
                if(dn1==dn2){
                    return dn1;
                }else{
                    dn1=dn1.next;
                    dn2=dn2.next;
                }
            }
        }else{
            diff=l2-l1;//2先走
            dn1=headA;
            dn2=headB;
            while(diff-->0){
                dn2=dn2.next;
            }
            while(l1-->0){
                if(dn1==dn2){
                    return dn1;
                }else{
                    dn1=dn1.next;
                    dn2=dn2.next;
                }
            }
        }
        return null;
    }

    public ListNode detectCycle142(ListNode head) {
        ListNode dn1=head;
        ListNode dn2=head;
        while(dn2!=null&&dn2.next!=null){
            dn2=dn2.next.next;
            dn1=dn1.next;
            if(dn2==dn1){
                //有环
                while(head!=dn1){
                    head=head.next;
                    dn1=dn1.next;
                }
                return head;
            }
        }
        return null;
    }

    public boolean isAnagram242(String s, String t) {
        char[] arrS=s.toCharArray();
        char[] arrT=t.toCharArray();
        int[] arr=new int[26];
        for(int i=0;i<arrS.length;i++){
            arr[arrS[i]-'a']++;
        }
        for(int i=0;i<arrT.length;i++){
            arr[arrT[i]-'a']--;
        }
        for(int i=0;i<26;i++){
            if(arr[i]!=0){
                return false;
            }
        }
        return true;
    }

    public List<List<String>> groupAnagrams49_1(String[] strs) {
        List<List<String>> ans=new ArrayList<>();
        int l=strs.length;
        int iArr=0;
        int[][] arr=new int[l][];
        for(int i=0;i<l;i++){
            char[] arrT=strs[i].toCharArray();
            boolean b=false;
            for(int j=0;j<iArr;j++){
                if(arr[j]==null){
                    break;
                }
                if(isAnagramArr(arr[j],arrT)){
                    //有就装到对应的List里
                    ans.get(j).add(strs[i]);
                    b=true;
                    break;
                }
            }
            //遍历完都没有就新建List
            if(!b){
                List<String > list=new ArrayList<>();
                list.add(strs[i]);
                ans.add(list);
                int[] arr0=new int[26];
                for(int i0=0;i0<arrT.length;i0++){
                    arr0[arrT[i0]-'a']++;
                }
                arr[iArr]=arr0;
                iArr++;
            }
        }
        return ans;
    }

    public List<List<String>> groupAnagrams49_2(String[] strs) {
        List<List<String>> ans=new ArrayList<>();
        int l=strs.length;
        int iArr=0;
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<l;i++){
            char[] arrT=strs[i].toCharArray();
            Arrays.sort(arrT);
            String arrS=new String(arrT);
            if(!map.containsKey(arrS)){
                List<String > list=new ArrayList<>();
                list.add(strs[i]);
                ans.add(list);
                map.put(arrS,iArr++);
            }else{
                ans.get(map.get(arrS)).add(strs[i]);
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams438(String s, String p) {
        List<Integer> ans=new ArrayList<>();
        int lp=p.length();
        int i=0;
        int[] arrp=toAnagramArr(p);
        char[] arrs=s.toCharArray();
        int last=0;
        for(;i+lp<=s.length();i++){
            if(last==0){
                last=1;
                boolean b=true;
                for(int j=0;j<lp;j++){
                    arrp[arrs[j]-'a']--;
                }
                for(int i1=0;i1<26;i1++){
                    if(arrp[i1]!=0){
                        b=false;
                    }
                }
                if(b){
                    ans.add(i);
                }
            }
            else{
                boolean b=true;
                arrp[arrs[i-1]-'a']++;
                arrp[arrs[i+lp-1]-'a']--;
                for(int i1=0;i1<26;i1++){
                    if(arrp[i1]!=0){
                        b=false;
                    }
                }
                if(b){
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    public int[] intersection349(int[] nums1, int[] nums2) {
        Set<Integer> list=new HashSet<>();
        Set<Integer> list1=new HashSet<>();
        for(int i:nums1){
            list1.add(i);
        }
        for(int j:nums2){
            if(list1.contains(j)){
                list.add(j);
            }
        }
        int[] res=new int[list.size()];
        int index=0;
        for(Integer ii:list){
            res[index]=ii;
            index++;
        }
        return res;
    }

    public int[] intersect350(int[] nums1, int[] nums2) {
        List<Integer> list=new ArrayList<>();
        int[] arr1=new int[1001];
        int[] arr2=new int[1001];
        for(int i:nums1){
            arr1[i]++;
        }
        for(int i:nums2){
            arr2[i]++;
        }
        for(int i=0;i<1001;i++){
            if(arr1[i]>0&&arr2[i]>0){
                for(int j=0;j<Math.min(arr1[i], arr2[i]);j++){
                    list.add(i);
                }
            }
        }
        int[] res=new int[list.size()];
        for(int i=0;i<list.size();i++){
            res[i]=list.get(i);
        }
        return res;
    }

    public boolean isHappy202(int n) {
        Set<Integer> set=new HashSet<>();
        while (n!=1){
            if(set.contains(n)){
                break;
            }else{
                set.add(n);
            }
            n=happyStep1(n);
        }
        if(n==1){
            return true;
        }
        return false;
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        int[] res=new int[2];
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                res[0]=i;
                res[1]=map.get(nums[i]);
                return res;
            }else{
                map.put(target-nums[i],i);
            }
        }
        return res;
    }

    private int happyStep1(int n){
        int res=0;
        while(n>0){
            int c=n%10;
            n=n/10;
            res+=c*c;
        }
        return res;
    }
    private int[] toAnagramArr(String s){
        char[] arrS=s.toCharArray();
        int[] arr=new int[26];
        for(int i=0;i<arrS.length;i++){
            arr[arrS[i]-'a']++;
        }
        return arr;
    }
    private boolean isAnagramArr(int[] arr,char arrT[]){
        int[] arrTime=Arrays.copyOf(arr,26);
        for(int i0=0;i0<arrT.length;i0++){
            arrTime[arrT[i0]-'a']--;
        }
        for(int i1=0;i1<26;i1++){
            if(arrTime[i1]!=0){
                return false;
            }
        }
        return true;
    }
    public void replaceArr(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        if (j >= nums.length || i >= nums.length) {
            return;
        }
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
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
