package ru.netology

internal class CommentService : CrudService<Comment> {
    private var nextId = 1
    private val comments: MutableList<Comment> = mutableListOf()
    private val blackList: MutableList<Comment> = mutableListOf()


    override fun add(element: Comment): Comment {
        element.copy (id = nextId)
        nextId++
        comments += element
        return comments.last()
    }

    override fun delete(id: Int): Boolean {
        for (element in comments) {
            if (element.id == id) {
                blackList += element
                comments.remove(element)
                return true
            }
        }
        return false
    }

    override fun edit(element: Comment): Boolean {
        for ((index, newElement) in comments.withIndex()) {
            if (element.id == newElement.id) {
                comments[index] = element.copy(message = newElement.message)
                return true
            }
        }
        return false
    }

    override fun read(ids: String): MutableList<Comment> {
        val altComment: MutableList<Comment> = mutableListOf()
        val parts = ids.split(",")
        for (part in parts) {
            for ((index, element) in comments.withIndex()) {
                val currentId = Integer.parseInt(parts[index])
                if (currentId == element.noteId) {
                    altComment += element
                }
            }
        }
        return altComment
    }

    override fun getById(id: Int): Comment? {
        for (element in comments) {
            if (element.id == id) {
                return element
            }
        }
        return null
    }

    fun restoreComment(deletedCommentId: Int) {
        for (element in blackList) {
            if (deletedCommentId == element.id) {
                comments += element
                return
            }
        }
        throw DeletedCommentNotFoundException("Такой комментарий не найден!!")
    }
}