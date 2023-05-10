package com.leetcode.study.algorithm.medium;

import com.leetcode.study.algorithm.bean.ListNode2;
import lombok.val;

import java.math.BigInteger;

/**
 * @author xuchangcheng
 * @Date 2023/5/9 17:03
 * @Description : 两数相加
 */
public class AddOfTwoNum {

    /**
     * 力扣 第2题
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
     */

    /**
     * 节点添加
     * 示例：  ListNode ln11 = new ListNode(2);
     *         ListNode ln12 = new ListNode(4);
     *         ListNode ln13 = new ListNode(9);
     *         ln11.next = ln12;
     *         ln12.next = ln13;
     *         ln13.next = null;
     * 注意：必须要用while才能到最深的节点，if不可以
     * @param o 初始节点
     * @param a 添加节点
     */
    public static void addNode(ListNode2 o,ListNode2 a){
        while(o.next != null){
            o = o.next;
        }
        o.next = a;
    }

    /**
     * 取出节点数据，并翻转
     * @param n
     * @return
     */
    public static StringBuilder getVal(ListNode2 n){
        StringBuilder sb = new StringBuilder();
        while (n.next != null){
            sb.append(n.val);
            n = n.next;
        }
        sb.append(n.val);
        return sb.reverse();
    }

    /**
     * 第一种方式，速度不是很快
     * (int)s.charAt(s.length()-1)-'0'  char 转 int
     * @param n1
     * @param n2
     * @return
     */
    public static ListNode2 addTwoNumbersFirst(ListNode2 n1,ListNode2 n2){
        StringBuilder v1 = getVal(n1);
        StringBuilder v2 = getVal(n2);
        BigInteger aa = new BigInteger(v1.toString()).add(new BigInteger(v2.toString()));
        String s = aa.toString();
        //新建链表
        ListNode2 head = new ListNode2((int)s.charAt(s.length()-1)-'0');
        ListNode2 l = null;
        //依次添加字符串数据
        for (int i = 0; i < (s.length()-1); i++) {
            l = new ListNode2((int)s.charAt(s.length() - (i + 2))-'0');
            addNode(head,l);
        }
        return head;
    }

    /**
     * 第二种方式，此种方式比较快
     * 利用除数以及取余完成 / 、 %
     * 这里需要改变下思维 比如 2,4,3 + 5,6,4 实际是 3,4,2 + 4,6,5
     * 这里的while 采用 2,4,3 + 5,6,4 因此需要往后加 cursor，比如 4 + 6的1值 应该加在 3 + 4 这里 变成 3 + 4 + 1；
     * @param n1
     * @param n2
     * @return
     */
    public static ListNode2 addTwoNumbersSecond(ListNode2 n1,ListNode2 n2){
        ListNode2 data = new ListNode2(0);
        ListNode2 temp = data;
        // 循环每个节点
        int cursor = 0; // 主要作用是进数操作，比如 4+6=10，那么后一位应该加上1
        while(n1 != null || n2 != null){
            int x = n1 == null ? 0 : n1.val;
            int y = n2 == null ? 0 : n2.val;
            int sum = x + y + cursor;
            cursor = sum / 10; // 判断sum是否溢出（比如结果为10，11）
            temp.next = new ListNode2(sum%10);
            temp = temp.next;
            if(n1 != null) n1 = n1.next;
            if(n2 != null)n2 = n2.next;
        }
        if(cursor > 0){
            temp.next = new ListNode2(cursor);
        }
        return data.next;
    }

    public static void main(String[] args) {

        ListNode2 n1 = new ListNode2(2);
        int [] aa = {4,3};
        for (int i : aa) {
            addNode(n1,new ListNode2(i));
        }

        ListNode2 n2 = new ListNode2(5);
        int [] aaa = {6,7};
        for (int i : aaa) {
            addNode(n2,new ListNode2(i));
        }

        ListNode2 listNode2 = addTwoNumbersFirst(n1, n2);
        StringBuilder val = getVal(listNode2);
        System.out.println(val.reverse().toString());

        ListNode2 listNode3 = addTwoNumbersSecond(n1, n2);
        StringBuilder val2 = getVal(listNode3);
        System.out.println(val2.reverse().toString());
    }
}
