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

    //todo 过段时间再看看
    public int fourSumCount454(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res=0;
        Map<Integer,Integer> map1=new HashMap<>();
        for(int x1:nums1){
            for(int x2:nums2){
                map1.put(x1+x2,map1.getOrDefault(x1+x2,0)+1);
            }
        }
        for(int x3:nums3){
            for(int x4:nums4){
                res+=map1.getOrDefault(-x3-x4,0);
            }
        }
        return res;
    }

    public boolean canConstruct383(String ransomNote, String magazine) {
        char[] arrS=ransomNote.toCharArray();
        int[] arr=new int[26];
        for(int i=0;i<arrS.length;i++){
            arr[arrS[i]-'a']++;
        }
        char[] arrM=magazine.toCharArray();
        for(int i=0;i<arrM.length;i++){
            arr[arrM[i]-'a']--;
        }
        for(int i1=0;i1<26;i1++){
            if(arr[i1]>0){
                return false;
            }
        }
        return true;
    }

    //todo 此题需要重做
    public List<List<Integer>> threeSum15(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left=i+1;
            int right=nums.length-1;
            while(left<right){
                if(nums[i]+nums[right]+nums[left]==0){
                    //记录结果
                    List<Integer> l=new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[left]);
                    l.add(nums[right]);
                    res.add(l);
                    while(left<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(nums[i]+nums[right]+nums[left]<0){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum18(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        int len=nums.length;
        for(int i=0;i<len-3;i++){
            if(nums[i]>target&&nums[i]>0){
                break;
            }
            if((long)nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target){
                break;
            }
            if((long)nums[i]+nums[len-1]+nums[len-2]+nums[len-3]<target){
                continue;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<len-2;j++){
                if(nums[i]+nums[j]>target&&nums[i]+nums[j]>0){
                    break;
                }
                if((long)nums[i]+nums[j]+nums[j+1]+nums[j+2]>target){
                    break;
                }
                if((long)nums[i]+nums[j]+nums[len-1]+nums[len-2]<target){
                    continue;
                }
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                int l=j+1;
                int r=len-1;
                while(l<r){
                    if(nums[i]+nums[j]+nums[l]+nums[r]==target){
                        res.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        while(l<r&&nums[l]==nums[l+1]){
                            l++;
                        }
                        while(l<r&&nums[r]==nums[r-1]){
                            r--;
                        }
                        l++;
                        r--;
                    }else if(nums[i]+nums[j]+nums[l]+nums[r]<target){
                        l++;
                    }else{
                        r--;
                    }
                }

            }
        }
        return res;
    }

    public void reverseString344(char[] s) {
        char tmp;
        int l=s.length;
        for(int i=0;i<s.length/2;i++){
            tmp=s[i];
            s[i]=s[l-i-1];
            s[l-i-1]=tmp;
        }
    }

    public String reverseStr541(String s, int k) {
        char[] sArr=s.toCharArray();
        int len=sArr.length;
        int time=len/k;
        int i=0;
        for (;i<time;i++){
            if(i%2==0){
                reverseCharArr(sArr,i*k,i*k+k);
            }else{
                continue;
            }
        }
        if(i%2==0){
            reverseCharArr(sArr,i*k,len);
        }
        return new String(sArr);
    }

    public String reverseWords151(String s) {
        //这题的标准做法是先去除多余空格、再翻转每个单词、再翻转整个字符串
        String[] sArr=s.split(" ");
        StringBuilder sb=new StringBuilder();
        int len=sArr.length;
        for(int i=0;i<len;i++){
            if(sArr[len-i-1].isEmpty()){
                continue;
            }
            sb.append(sArr[len-i-1]).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /**
     * KMP算法实现字符串匹配（KMP貌似也是动态规划的一种？）
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr28(String haystack, String needle) {
        char[] arrH=haystack.toCharArray();
        char[] arrN=needle.toCharArray();
        int len=arrN.length;
        if(len==0){
            return 0;
        }
        int[] next=getNext(arrN);
        int j=0;
        int res=-1;
        for (int i=0;i<arrH.length;i++){
            while(j>0&&arrN[j]!=arrH[i]){
                j=next[j-1];
            }
            if(arrN[j]==arrH[i]){
                j++;
            }
            if(j==len){
                res=i-j+1;
                return res;
            }
        }
        return res;
    }

    //todo 这个题可以以后再做一遍加深理解
    public boolean repeatedSubstringPattern459(String s) {
        char[] arr=s.toCharArray();;
        int len=arr.length;
        if(len<=1){
            return false;
        }
        int[] next=new int[len];
        int j=0;
        next[0]=j;
        for(int i=1;i<len;i++){
            while(j>0&&arr[i]!=arr[j]){
                j=next[j-1];
            }
            if(arr[i]==arr[j]){
                j++;
            }
            next[i]=j;
        }
        if(next[len-1]>0&&len%(len-next[len-1])==0){
            return true;
        }else {
            return false;
        }
    }

    public boolean isValid20(String s) {
        char[] arr=s.toCharArray();
        Stack<Character> stack=new Stack<Character>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]=='('){
                stack.push('(');
            }
            else if(arr[i]=='{'){
                stack.push('{');
            }
            else if(arr[i]=='['){
                stack.push('[');
            }
            else if(arr[i]==')'){
                if(stack.empty()){
                    return false;
                }
                if(stack.pop()!='('){
                    return false;
                }
            }
            else if(arr[i]=='}'){
                if(stack.empty()){
                    return false;
                }
                if(stack.pop()!='{'){
                    return false;
                }
            }
            else if(arr[i]==']'){
                if(stack.empty()){
                    return false;
                }
                if(stack.pop()!='['){
                    return false;
                }
            }
        }
        return stack.empty();
    }

    //todo 这个题可以用双指针法更快解决（效率4倍
    public String removeDuplicates1047(String s) {
        if(s.length()<2){
            return s;
        }
        StringBuilder sb=new StringBuilder();
        char[] arr=s.toCharArray();
        LinkedList<Character> queue=new LinkedList();
        for(int i=0;i<arr.length;i++){
            if(queue.peekLast()==null){
                queue.offer(arr[i]);
            }else {
                if (queue.peekLast() == arr[i]) {
                    queue.pollLast();
                } else {
                    queue.offer(arr[i]);
                }
            }
        }

        while(!queue.isEmpty()){
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    //todo 这个题可以用递归做，节约非常多时间，听说stack相关内容都可以用递归做，很神奇
    public int evalRPN150(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<tokens.length;i++){
            if("+".equals(tokens[i])){
                int x=stack.pop()+stack.pop();
                stack.push(x);
            }else if("-".equals(tokens[i])){
                int x=stack.pop()-stack.pop();
                stack.push(-x);
            }else if("*".equals(tokens[i])){
                int x=stack.pop()*stack.pop();
                stack.push(x);
            }else if("/".equals(tokens[i])){
                int x2=stack.pop();
                int x1=stack.pop();
                int x=x1/x2;
                stack.push(x);
            }else{
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

    //todo 这道题基本是抄答案的，还没有完全理解单调队列的操作方式
    public int[] maxSlidingWindow239(int[] nums, int k) {
        int l=nums.length;
        int[] res=new int[l-k+1];
        Deque<Integer> queue=new LinkedList<>();
        for(int i=0;i<l;i++){
            //in
            while(!queue.isEmpty() && nums[i]>nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.addLast(i);
            //out，窗口范围不再包含队列头上的元素
            if(i-queue.peekFirst()>=k){
                queue.pollFirst();
            }
            //ans
            if(i>=k-1){
                res[i-k+1]=nums[queue.peekFirst()];
            }
            queue.
        }
        return res;
    }
    //todo 这道题基本是抄答案的，还没有完全理解单调队列的操作方式
    public int longestSubarray1438(int[] nums, int limit) {
        int len=nums.length;
        int j=0;
        int res=0;
        Deque<Integer> dequeMax=new LinkedList<>();
        Deque<Integer> dequeMin=new LinkedList<>();
        for(int i=0;i<len;i++){
            while(!dequeMax.isEmpty()&&nums[dequeMax.peekLast()]<nums[i]){
                dequeMax.pollLast();
            }
            dequeMax.addLast(i);
            while(!dequeMin.isEmpty()&&nums[dequeMin.peekLast()]>nums[i]){
                dequeMin.pollLast();
            }
            dequeMin.addLast(i);
            while(nums[dequeMax.peekFirst()]-nums[dequeMin.peekFirst()]>limit){
                if(j==dequeMax.peekFirst()){
                    dequeMax.pollFirst();
                }
                if(j==dequeMin.peekFirst()){
                    dequeMin.pollFirst();
                }
                j++;
            }
            res=Math.max(res,i-j+1);
        }
        return res;
    }

    public int[] topKFrequent347(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2)->pair2[1]-pair1[1]);
        for(Map.Entry<Integer, Integer> entry:map.entrySet()){
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = pq.poll()[0];
        }
        return res;
    }

    public void quickSort(int[] arr, int start, int end) {
        // 递归结束条件:start大于或等于end时
        if (start < end) {
            // 得到基准元素位置
            int pivotIndex = partition(arr, start, end);
            // 根据基准元素，分成两部分进行递归排序
            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);
        }
    }
    private int partition(int[] arr, int start, int end) {
        // 取第1个位置的元素作为基准元素
        int pivot = arr[start];
        int left = start;
        int right = end;

        while (left < right) {
            //right指针左移
            while (left < right && arr[right] >= pivot) right--;
            //left指针右移
            while (left < right && arr[left] <= pivot) left++;
            if (left >= right) break;
            //交换left和right 指针所指向的元素
            arr[left] = arr[left] + arr[right] - (arr[right] = arr[left]);
        }
        // 将基准元素与指针重合点所指的元素进行交换
        arr[start] = arr[left];
        arr[left] = pivot;

        return left;
    }

    private int[] getNext(char[] arrN){
        int len=arrN.length;
        int[] next=new int[len];
        int j=0;
        next[0]=j;
        for(int i=1;i<len;i++){
            while(j>0&&arrN[i]!=arrN[j]){
                j=next[j-1];
            }
            if(arrN[j]==arrN[i]){
                j++;
            }
            next[i]=j;
        }
        return next;
    }

    public String num2Number(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(c>'9'||c<'0'){
                sb.append(c);
            }else{
                sb.append("number");
            }
        }
        return sb.toString();
    }

    public void reverseCharArr(char[] s,int left,int right) {
        //包括left，不包括right
        char tmp;
        for(int i=left;i<(left+right)/2;i++){
            tmp=s[i];
            s[i]=s[left+right-i-1];
            s[left+right-i-1]=tmp;
        }
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
