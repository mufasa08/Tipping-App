package com.example.cache

class MemoryCache<T> {
    /*
    -> This is a rudimentary method of caching
    -> It is only useful if transitioning to a new activity.
    -> In practise we would prefer to use a proper DB like Room or SQL Lite
    */
    private val map: MutableMap<String, T> = mutableMapOf()

    fun load(key: String): T = map.getValue(key)

    fun save(key: String, anyObject: T): T =
        map.put(key, anyObject).run { anyObject }
}
