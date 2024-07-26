package com.sast.astbenchmark.model.alias;

import java.util.Random;

public class Invoke {

    public static void copy(DataClass dc1, DataClass dc2) {
        dc2.data = dc1.data;
    }

    public static void copy(A b, String string) {
        A c = b;
        c.b = string;
    }

    public static A newA(String data) {
        A a = new A();
        A b = a;
        b.b = data;
        return a;
    }

    public static void taintMe(DataClass dc, String taint) {
        String tainted = taint;
        dc.data = tainted;
    }

    public static A alias(B b) {
        return b.attr;
    }

    public static A alias(A a) {
        A r = a;
        return r;
    }

    public static String alias(String val) {
        String r = val;
        return r;
    }

    public static void alias(B b1, B b2) {
        b2.setAttr(b1.attr);
    }

    public static void alias(B b, A a) {
        b.setAttr(a);
    }

    public static void doUnalias(B b) {
        b.setAttr(new A());
    }

    private static void overwriteParameter(B b) {
        b = new B();
    }

    public static <T> SimpleLinkedList<T> newSimpleLinkedList(T data) {
        SimpleLinkedList<T> link = new SimpleLinkedList<>();
        SimpleLinkedList<T> link1 = new SimpleLinkedList<>();
        SimpleLinkedList<T> link2 = new SimpleLinkedList<>();
        SimpleLinkedList<T> link3 = new SimpleLinkedList<>();
        SimpleLinkedList<T> link4 = new SimpleLinkedList<>();
        SimpleLinkedList<T> link5 = new SimpleLinkedList<>();
        SimpleLinkedList<T> link6 = new SimpleLinkedList<>();
        link.next = link1;
        link1.next = link2;
        link2.next = link3;
        link3.next = link4;
        link4.next = link5;
        link5.next = link6;
        link6.data = data;
        return link;
    }

    public static String taintRecursively(A a, String v) {
        if (new Random().nextBoolean()) {
            a.b = v;
            return "";
        } else {
            A a2 = new A();
            taintRecursively(a2, v + "foo");
            return a2.b;
        }
    }

    public static String id(String data) {
        String ret = data;
        return ret;
    }

    public static String id1(String data) {
        return id(data + "bar");
    }

    public static String id2(String data) {
        return id1(data + "bar");
    }

    public static String id3(String data) {
        return id2(data + "bar");
    }

    public static String id4(String data) {
        return id3(data + "bar");
    }

    public static String id5(String data) {
        return id4(data + "bar");
    }

    public static String id6(String data) {
        return id5(data + "bar");
    }

    public static String id7(String data) {
        return id6(data + "bar");
    }

    public static String id8(String data) {
        return id7(data + "bar");
    }

    static X newX(Y y) {
        X r = new X(); // allocation-site
        r.y = y;
        return r;
    }

    private static X newX(X x) {
        X r = new X(); // allocation-site
        r.y = x.y;
        return r;
    }

    static X newX1(Y y) {
        X x = new X(); // allocation-site
        x.y = y;
        return newX(x);
    }

    public static X assign(X x, Y y) {
        x.y = y;
        return x;
    }

    private static void assign(final Container2 base, final String string) {
        base.f.g = string;
    }
}