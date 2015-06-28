package com.xafero.vee.env.storage;

public interface IStorage<K, V> {

	V getItem(K key);

	V setItem(K key, V value);

	V removeItem(K key);

}