package ru.netology

internal data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val privacy: Int,
    val commentPrivacy: Int,
    val date: Int,
    val comments: MutableList<Comment> = mutableListOf()
)