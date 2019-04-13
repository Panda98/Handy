package com.handy.support.utils.status;

/**
 * Created by joanie on 2019/4/13.
 */
public class ReturnStatus<K,V> {
    private K key;
    private V value;

    public ReturnStatus(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ReturnStatus{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
