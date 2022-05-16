package ru.netology

internal class NoteService : CrudService<Note> {

    private val notes: MutableList<Note> = mutableListOf()
    private var nextId = 1


    override fun add(element: Note): Note {
        element.copy(id = nextId)
        nextId++
        notes += element
        return notes.last()
    }


    override fun delete(id: Int): Boolean {
        for (element in notes) {
            if (element.id == id) {
                notes.remove(element)
                return true
            }
        }
        return false
    }

    override fun edit(element: Note): Boolean {
        for ((index, newElement) in notes.withIndex()) {
            if (element.id == newElement.id) {
                notes[index] = element
                return true
            }
        }
        return false
    }

    override fun read(ids: String): MutableList<Note> {
        val newNote: MutableList<Note> = mutableListOf()
        val parts = ids.split(",")
        for (part in parts) {
            for ((index, element) in notes.withIndex()) {
                val currentId = Integer.parseInt(parts[index])
                if (currentId == element.id) {
                    newNote += element
                }
            }
        }
        return newNote
    }

    override fun getById(id: Int): Note? {
        for (element in notes) {
            if (element.id == id) {
                return element
            }
        }
        return null
    }
}