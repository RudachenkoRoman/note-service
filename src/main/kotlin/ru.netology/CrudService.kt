package ru.netology

interface CrudService<T> {

    fun add(element: T): T

    fun delete(id: Int): Boolean

    fun edit(element: T): Boolean

    fun read(ids: String): List<T>

    fun getById(id: Int): T?
}