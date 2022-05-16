package ru.netology

internal data class Comment(
    val noteId: Int,
    val replyTo: Int,
    val message: String,
    val id: Int,
    val date: Int
)