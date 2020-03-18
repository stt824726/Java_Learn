package com.stt.hashMap;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

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
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        //更新旧值
        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this){
                return true;
            }
            if(obj instanceof Map.Entry){
                Map.Entry<K,V> entity = (Map.Entry)obj;
                if(Objects.equals(key,((Map.Entry) obj).getKey()) && Objects.equals(value,((Map.Entry) obj).getValue())){
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return this.key + " : "+ this.value;
        }
    }

    /**
     * 确定哈希桶数组索引位置
     *
     * @param key
     * @return
     */
    static final int hash(Object key){
        int h;
        // 1. h = key.hashCode() 为第一步 取hashCode值
        // 2. (h >>> 16)  为第二步 高位参与运算
        // 3. 取模运算
        return (key == null)? 0 : (h = key.hashCode()) ^ ( h >>> 16);
    }

    public static void main(String[] args) {
        System.out.println(MAXIMUM_CAPACITY);
    }

}
