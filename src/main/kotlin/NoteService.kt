class NoteService {
    val noteService = mutableListOf<Note>()
    val comments = mutableListOf<Comment>()

    fun addNote(
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
        privacyView: Privacy,
        privacyComment: Privacy
    ): Int {
        val noteId = if (noteService.isEmpty()) 0 else {
            noteService.last().noteId + 1
        }
        noteService.add(Note(title, text, privacy, commentPrivacy, privacyView, privacyComment, noteId))
        return noteId
    }

    fun deleteNote(noteId: Int): Int {
        if (noteService[noteId].noteDeleted) {
            throw DeletedObjectException ("This note has been deleted already")
        } else {
            noteService[noteId].noteDeleted = true
            for (comment in comments) {
                if (comment.noteId == noteId)
                    comment.commentDeleted = true
            }
        }
        return 1
    }

    fun editNote(
        noteId: Int,
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int,
        privacyView: Privacy,
        privacyComment: Privacy
    ): Int {
        if (noteService[noteId].noteDeleted) {
            throw DeletedObjectException ("This note cannot be edited because it has been deleted already")
        } else {
            noteService[noteId].title = title
            noteService[noteId].text = text
            noteService[noteId].privacy = privacy
            noteService[noteId].commentPrivacy = commentPrivacy
            noteService[noteId].privacyView = privacyView
            noteService[noteId].privacyComment = privacyComment
        }
        return 1
    }

    fun getNotes(
        notesIds: String,
        sort: Int
    ): List<Note> {
        val strIndexes = notesIds.split(", ")
        var indexes = mutableListOf<Int>()
        for (strIndex in strIndexes) {
            indexes.add(strIndex.toInt())
        }
        when (sort) {
            0 -> indexes = indexes.sortedDescending().toMutableList()
            1 -> indexes = indexes.sorted().toMutableList()
            else -> throw java.lang.IllegalArgumentException("Requested value for SORT is 0 or 1")
        }
        val requestedNotes = mutableListOf<Note>()
        for (index in indexes) {
            requestedNotes.add(noteService[index])
        }
        return requestedNotes.toList()
    }

    fun getById(noteId: Int): Note {
        return noteService[noteId]
    }

    fun createComment(
        noteId: Int,
        message: String,
        replyTo: Int? = null
    ): Int {
        if (noteService.contains(noteService[noteId])) {
            val commentId = if (comments.isEmpty()) 0 else (comments.last().commentId + 1)
            comments.add(Comment(commentId, noteId, message, replyTo))
            return commentId
        } else {
            throw IndexOutOfBoundsException("Note with this id does not exist ")
        }
    }

    fun deleteComment(commentId: Int): Int {
        if (comments[commentId].commentDeleted) {
            throw DeletedObjectException ("This comment has been deleted already")
        } else {
        comments[commentId].commentDeleted = true
        }
        return 1
    }

    fun editComment(commentId: Int, message: String): Int {
        if (comments[commentId].commentDeleted) {
            throw DeletedObjectException ("This comment has been deleted already")
        } else {
            comments[commentId].message = message
        }
        return 1
    }

    fun getComments(noteId: Int, sort: Int): List<Comment> {
        val requestedComments = mutableListOf<Comment>()
        var sorters = mutableListOf<Int>()
        for (comment in comments) {
            if (comment.noteId == noteId) {
                sorters.add(comment.commentId)
            }
        }
        when (sort) {
            0 -> sorters = sorters.sortedDescending().toMutableList()
            1 -> sorters = sorters.sorted().toMutableList()
            else -> throw java.lang.IllegalArgumentException("Requested value for SORT is 0 or 1")
        }
        for (sorter in sorters) {
            if (comments[sorter].commentDeleted) {
                requestedComments.add(
                    Comment(
                        comments[sorter].commentId,
                        comments[sorter].commentId,
                        "Данный комментарий был удален.",
                        comments[sorter].replyTo,
                        true
                    )
                )
            } else {
                requestedComments.add(comments[sorter])
            }
        }
        return requestedComments.toList()
    }

    fun restoreComment(commentId: Int): Int {
        if (!comments[commentId].commentDeleted) {
            throw DeletedObjectException("This comment cannot be restored because it has not been deleted")
        } else {
            comments[commentId].commentDeleted = false
        }
        return 1
    }
}

