package com.stt.hashMap;

import java.io.Serializable;
import java.util.Map;

public class MyHashMap<K,V> implements Serializable {

    private static final long serialVersionUID = 1L;

    //默认初始化容量
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    //最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;

    //负载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    //链表转树阀值
    static final int TREEIFY_THRESHOLD = 8;

    //树转链表阀值
    static final int UNTREEIFY_THRESHOLD = 6;

    static final int MIN_TREEIFY_CAPACITY = 64;

    static class Node<K,V> implements Map.Entry<K,V>{
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash,K key,V value,Node<K,V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(MAXIMUM_CAPACITY);
    }

}
