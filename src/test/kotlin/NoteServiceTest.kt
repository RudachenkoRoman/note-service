import org.junit.Test

import org.junit.Assert.*
import ru.netology.*

internal class NoteServiceTest {
    private val noteService = NoteService()
    private val commentService = CommentService()

    @Test
    fun add() {
        val add = Note(
            id = 7,
            title = "Affirmation",
            text = "Love will save the world",
            privacy = 0,
            commentPrivacy = 0,
            date = 10_04_2022
        )
        val addedNote1 = noteService.add(add)

        assertNotNull(addedNote1)
    }

    @Test
    fun createComment() {
        val comment = Comment(
            noteId = 1,
            replyTo = 2,
            message = "Make love not war",
            id = 5,
            date = 10_04_2022
        )

        val addedComment1 = commentService.add(comment)

        assertNotNull(addedComment1)
    }

    @Test
    fun successfulDelete() {
        val addedNote2 = noteService.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        commentService.add(
            Comment(
                noteId = addedNote2.id,
                replyTo = 2,
                message = "And Oscar goes to...",
                id = 5,
                date = 10_04_2022
            )
        )
        val result = noteService.delete(id = addedNote2.id)
        assertTrue(result)

    }

    @Test
    fun unsuccessfulDelete() {
        val addedNote3 = noteService.add(
            Note(
                id = 3,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        commentService.add(
            Comment(
                noteId = addedNote3.id,
                replyTo = 2,
                message = "And Oscar goes to...",
                id = 5,
                date = 10_04_2022
            )
        )
        val result = noteService.delete(id = 5)
        assertFalse(result)
    }

    @Test
    fun successfulDeleteComment() {
        val addedNote4 = noteService.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val addedComment2 = commentService.add(
            Comment(
                noteId = addedNote4.id,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 2,
                date = 10_04_2022
            )
        )
        val result = commentService.delete(id = addedComment2.id)
        assertTrue(result)
    }

    @Test
    fun unsuccessfulDeleteComment() {
        val addedComment3 = commentService.add(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 5,
                date = 10_04_2022
            )
        )
        val oneResult = commentService.delete(id = 3)
        assertFalse(oneResult)

        val twoResult = commentService.delete(id = addedComment3.id)
        assertTrue(twoResult)
    }

    @Test
    fun successfulEdit() {
        val addedNote6 = noteService.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )

        val updatedNote1 = noteService.edit(
            Note(
                id = addedNote6.id,
                title = "Affirmation",
                text = "Love will save the world",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )

        assertTrue(updatedNote1)
    }

    @Test
    fun unsuccessfulEdit() {
        val addedNote7 = noteService.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )

        val updatedNote2 = noteService.edit(
            Note(
                id = 10,
                title = "Affirmation",
                text = "Love will save the world",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        assertFalse(updatedNote2)
    }

    @Test
    fun successfulEditComment() {
        val addedNote8 = noteService.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val addedComment4 = commentService.add(
            Comment(
                noteId = addedNote8.id,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 2,
                date = 10_04_2022
            )
        )
        val updatedComment1 = commentService.edit(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "And Oscar goes to...",
                id = addedComment4.id,
                date = 10_04_2022
            )
        )
        assertTrue(updatedComment1)
    }

    @Test
    fun failedEditComment() {

        commentService.add(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 20,
                date = 10_04_2022
            )
        )
        val updatedComment2 = commentService.edit(
            Comment(
                noteId = 2,
                replyTo = 2,
                message = "And Oscar goes to...",
                id = 7,
                date = 10_04_2022
            )
        )
        assertFalse(updatedComment2)
    }

    @Test
    fun get() {
        noteService.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        noteService.add(
            Note(
                id = 2,
                title = "Affirmation",
                text = "You should be proud of your life",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        val result = noteService.read(ids = "1,2")
        assertNotNull(result)
    }

    @Test
    fun getById() {
        val addedNote10 = noteService.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        noteService.add(addedNote10)
        val result = noteService.getById(addedNote10.id)
        assertNotNull(result)
    }

    @Test
    fun failedGetById() {
        val addedNote11 = noteService.add(
            Note(
                id = 10,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )

        val result = noteService.getById(20)
        assertNull(result)
    }

    @Test
    fun getCommentById() {
        val addedComment5 = commentService.add(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 20,
                date = 10_04_2022
            )
        )
        val result = commentService.getById(addedComment5.id)
        assertNotNull(result)
    }

    @Test
    fun failedGetCommentById() {
        val addedComment6 = commentService.add(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 20,
                date = 10_04_2022
            )
        )
        val result = commentService.getById(3)
        assertNull(result)
    }

    @Test
    fun read() {
        val addedNote12 = noteService.add(
            Note(
                id = 1,
                title = "Affirmation",
                text = "You can win if you want",
                privacy = 0,
                commentPrivacy = 0,
                date = 10_04_2022
            )
        )
        commentService.add(
            Comment(
                noteId = addedNote12.id,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 1,
                date = 10_04_2022
            )
        )
        commentService.add(
            Comment(
                noteId = addedNote12.id,
                replyTo = 2,
                message = "What a wonderful world!",
                id = 2,
                date = 10_04_2022
            )
        )
        val result = commentService.read(ids = "1,2")
        assertNotNull(result)
    }

    @Test
    fun successfulRestoreComment() {

        val addedComment6 = commentService.add(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "Hakuna Matata!",
                id = 1,
                date = 10_04_2022
            )
        )
        commentService.delete(addedComment6.id)
        commentService.restoreComment(addedComment6.id)
    }

    @Test(expected = DeletedCommentNotFoundException::class)
    fun shouldThrow() {

        val addedComment7 = commentService.add(
            Comment(
                noteId = 1,
                replyTo = 2,
                message = "Love saves the world!",
                id = 5,
                date = 10_04_2022
            )
        )
        commentService.delete(addedComment7.id)
        commentService.restoreComment(20)
    }
}