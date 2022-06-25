import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class NoteServiceTest {
    private val listOfNotes = NoteService()

    @org.junit.jupiter.api.Test
    fun addNote() {
        //Arrange
        val title = "Заметка1"
        val text = "Текст1"
        val privacy = 4
        val commentPrivacy = 4
        val privacyView = Privacy.ONLY_ME
        val privacyComment = Privacy.ONLY_ME

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)

        //Assert
        assertEquals(0, listOfNotes.noteService[0].noteId)
    }

    @org.junit.jupiter.api.Test
    fun deleteNote() {
        //Arrange
        val title = "Заметка2"
        val text = "Текст2"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL
        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.deleteNote(0)
        //Assert
        assertEquals(true, listOfNotes.noteService[0].noteDeleted)
    }

    @org.junit.jupiter.api.Test
    fun deleteNoteException() {
        assertThrows<DeletedObjectException>{
        //Arrange
        val title = "Заметка2"
        val text = "Текст2"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.deleteNote(0)
        listOfNotes.deleteNote(0)
    }
    }

    @org.junit.jupiter.api.Test
    fun editNote() {
        //Arrange
        val title = "Заметка3"
        val text = "Текст3"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL

        val titleToEdit = "Заметка4"
        val textToEdit = "Текст4"
        val privacyToEdit = 1
        val commentPrivacyToEdit = 1
        val privacyViewToEdit = Privacy.FRIENDS
        val privacyCommentToEdit = Privacy.FRIENDS

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.editNote(
            0,
            titleToEdit,
            textToEdit,
            privacyToEdit,
            commentPrivacyToEdit,
            privacyViewToEdit,
            privacyCommentToEdit
        )

        //Assert
        assertEquals(textToEdit, listOfNotes.noteService[0].text)
    }

    @org.junit.jupiter.api.Test
    fun editNoteException() {
        assertThrows<DeletedObjectException>{
            val title = "Заметка3"
            val text = "Текст3"
            val privacy = 3
            val commentPrivacy = 3
            val privacyView = Privacy.ALL
            val privacyComment = Privacy.ALL

            val titleToEdit = "Заметка4"
            val textToEdit = "Текст4"
            val privacyToEdit = 1
            val commentPrivacyToEdit = 1
            val privacyViewToEdit = Privacy.FRIENDS
            val privacyCommentToEdit = Privacy.FRIENDS

            //Act
            listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
            listOfNotes.deleteNote(0)
            listOfNotes.editNote(
                0,
                titleToEdit,
                textToEdit,
                privacyToEdit,
                commentPrivacyToEdit,
                privacyViewToEdit,
                privacyCommentToEdit
            )
        }
    }

    @org.junit.jupiter.api.Test
    fun getNotes() {
        //Arrange
        val title1 = "Заметка1"
        val text1 = "Текст1"
        val privacy1 = 4
        val commentPrivacy1 = 4
        val privacyView1 = Privacy.ONLY_ME
        val privacyComment1 = Privacy.ONLY_ME

        val title2 = "Заметка2"
        val text2 = "Текст2"
        val privacy2 = 3
        val commentPrivacy2 = 3
        val privacyView2 = Privacy.ALL
        val privacyComment2 = Privacy.ALL

        val title3 = "Заметка3"
        val text3 = "Текст3"
        val privacy3 = 3
        val commentPrivacy3 = 3
        val privacyView3 = Privacy.ALL
        val privacyComment3 = Privacy.ALL

        //Act
        listOfNotes.addNote(title1, text1, privacy1, commentPrivacy1, privacyView1, privacyComment1)
        listOfNotes.addNote(title2, text2, privacy2, commentPrivacy2, privacyView2, privacyComment2)
        listOfNotes.addNote(title3, text3, privacy3, commentPrivacy3, privacyView3, privacyComment3)

        //Assert
        assertEquals(listOfNotes.noteService, listOfNotes.getNotes("0, 1, 2", 1))
    }

    @org.junit.jupiter.api.Test
    fun getNotesBackList() {
        //Arrange
        val title1 = "Заметка1"
        val text1 = "Текст1"
        val privacy1 = 4
        val commentPrivacy1 = 4
        val privacyView1 = Privacy.ONLY_ME
        val privacyComment1 = Privacy.ONLY_ME

        val title2 = "Заметка2"
        val text2 = "Текст2"
        val privacy2 = 3
        val commentPrivacy2 = 3
        val privacyView2 = Privacy.ALL
        val privacyComment2 = Privacy.ALL

        val title3 = "Заметка3"
        val text3 = "Текст3"
        val privacy3 = 3
        val commentPrivacy3 = 3
        val privacyView3 = Privacy.ALL
        val privacyComment3 = Privacy.ALL

        //Act
        listOfNotes.addNote(title1, text1, privacy1, commentPrivacy1, privacyView1, privacyComment1)
        listOfNotes.addNote(title2, text2, privacy2, commentPrivacy2, privacyView2, privacyComment2)
        listOfNotes.addNote(title3, text3, privacy3, commentPrivacy3, privacyView3, privacyComment3)

        //Assert
        assertEquals(listOfNotes.noteService.reversed(), listOfNotes.getNotes("0, 1, 2", 0))
    }

    @org.junit.jupiter.api.Test
    fun getNotesException() {
        assertThrows<IllegalArgumentException> {
            //Arrange
            val title1 = "Заметка1"
            val text1 = "Текст1"
            val privacy1 = 4
            val commentPrivacy1 = 4
            val privacyView1 = Privacy.ONLY_ME
            val privacyComment1 = Privacy.ONLY_ME

            val title2 = "Заметка2"
            val text2 = "Текст2"
            val privacy2 = 3
            val commentPrivacy2 = 3
            val privacyView2 = Privacy.ALL
            val privacyComment2 = Privacy.ALL

            val title3 = "Заметка3"
            val text3 = "Текст3"
            val privacy3 = 3
            val commentPrivacy3 = 3
            val privacyView3 = Privacy.ALL
            val privacyComment3 = Privacy.ALL

            //Act
            listOfNotes.addNote(title1, text1, privacy1, commentPrivacy1, privacyView1, privacyComment1)
            listOfNotes.addNote(title2, text2, privacy2, commentPrivacy2, privacyView2, privacyComment2)
            listOfNotes.addNote(title3, text3, privacy3, commentPrivacy3, privacyView3, privacyComment3)
            listOfNotes.getNotes("0, 1, 2", 7)
        }
    }

    @org.junit.jupiter.api.Test
    fun getById() {
        //Arrange
        val title1 = "Заметка1"
        val text1 = "Текст1"
        val privacy1 = 4
        val commentPrivacy1 = 4
        val privacyView1 = Privacy.ONLY_ME
        val privacyComment1 = Privacy.ONLY_ME

        val title2 = "Заметка2"
        val text2 = "Текст2"
        val privacy2 = 3
        val commentPrivacy2 = 3
        val privacyView2 = Privacy.ALL
        val privacyComment2 = Privacy.ALL

        //Act
        listOfNotes.addNote(title1, text1, privacy1, commentPrivacy1, privacyView1, privacyComment1)
        listOfNotes.addNote(title2, text2, privacy2, commentPrivacy2, privacyView2, privacyComment2)

        //Assert
        assertEquals(listOfNotes.noteService[1], listOfNotes.getById(1))
    }

    @org.junit.jupiter.api.Test
    fun createComment() {
        //Arrange
        val title = "Заметка3"
        val text = "Текст3"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL

        val noteId = 0
        val message = "Comment Text"

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.createComment(noteId, message)

        //Assert
        assertEquals(0, listOfNotes.comments[0].commentId)
    }

    @org.junit.jupiter.api.Test
    fun createCommentException() {
        assertThrows<IndexOutOfBoundsException> {
            val title = "Заметка3"
            val text = "Текст3"
            val privacy = 3
            val commentPrivacy = 3
            val privacyView = Privacy.ALL
            val privacyComment = Privacy.ALL

            val noteId = 5
            val message = "Comment Text"

            //Act
            listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
            listOfNotes.createComment(noteId, message)
        }
    }

    @org.junit.jupiter.api.Test
    fun deleteComment() {
        //Arrange
        val title = "Заметка3"
        val text = "Текст3"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL

        val noteId = 0
        val message = "Comment Text"

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.createComment(noteId, message)
        listOfNotes.deleteComment(0)

        //Assert
        assertEquals(true, listOfNotes.comments[0].commentDeleted)
    }

    @org.junit.jupiter.api.Test
    fun deleteCommentException() {
        assertThrows<DeletedObjectException>{
            //Arrange
            val title = "Заметка3"
            val text = "Текст3"
            val privacy = 3
            val commentPrivacy = 3
            val privacyView = Privacy.ALL
            val privacyComment = Privacy.ALL

            val noteId = 0
            val message = "Comment Text"

            //Act
            listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
            listOfNotes.createComment(noteId, message)
            listOfNotes.deleteComment(0)
            listOfNotes.deleteComment(0)
        }
    }

    @org.junit.jupiter.api.Test
    fun editComment() {
        //Arrange
        val title = "Заметка3"
        val text = "Текст3"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL

        val noteId = 0
        val message = "Comment Text"
        val editedMessage = "Another Comment Text"

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.createComment(noteId, message)
        listOfNotes.editComment(0, editedMessage)

        //Assert
        assertEquals(editedMessage, listOfNotes.comments[0].message)
    }

    @org.junit.jupiter.api.Test
    fun editCommentException() {
        assertThrows<DeletedObjectException> {
            //Arrange
            val title = "Заметка3"
            val text = "Текст3"
            val privacy = 3
            val commentPrivacy = 3
            val privacyView = Privacy.ALL
            val privacyComment = Privacy.ALL

            val noteId = 0
            val message = "Comment Text"
            val editedMessage = "Another Comment Text"

            //Act
            listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
            listOfNotes.createComment(noteId, message)
            listOfNotes.deleteComment(0)
            listOfNotes.editComment(0, editedMessage)
        }
    }

    @org.junit.jupiter.api.Test
    fun getComments() {
        //Arrange
        val title = "Заметка3"
        val text = "Текст3"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL

        val noteId = 0
        val message1 = "Comment Text"
        val message2 = "Comment Text 2"
        val message3 = "Comment Text 3"

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.createComment(noteId, message1)
        listOfNotes.createComment(noteId, message2)
        listOfNotes.createComment(noteId, message3)

        //Assert
        assertEquals(listOfNotes.comments, listOfNotes.getComments(0, 1))
    }

    @org.junit.jupiter.api.Test
    fun getCommentsBackList() {
        //Arrange
        val title = "Заметка3"
        val text = "Текст3"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL

        val noteId = 0
        val message1 = "Comment Text"
        val message2 = "Comment Text 2"
        val message3 = "Comment Text 3"

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.createComment(noteId, message1)
        listOfNotes.createComment(noteId, message2)
        listOfNotes.createComment(noteId, message3)

        //Assert
        assertEquals(listOfNotes.comments.reversed(), listOfNotes.getComments(0, 0))
    }

    @org.junit.jupiter.api.Test
    fun getCommentsException() {
        assertThrows<IllegalArgumentException> {
            //Arrange
            val title = "Заметка3"
            val text = "Текст3"
            val privacy = 3
            val commentPrivacy = 3
            val privacyView = Privacy.ALL
            val privacyComment = Privacy.ALL

            val noteId = 0
            val message1 = "Comment Text"
            val message2 = "Comment Text 2"
            val message3 = "Comment Text 3"

            //Act
            listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
            listOfNotes.createComment(noteId, message1)
            listOfNotes.createComment(noteId, message2)
            listOfNotes.createComment(noteId, message3)
            listOfNotes.getComments(noteId, 5)
        }
    }

    @org.junit.jupiter.api.Test
    fun restoreComment() {
        //Arrange
        val title = "Заметка3"
        val text = "Текст3"
        val privacy = 3
        val commentPrivacy = 3
        val privacyView = Privacy.ALL
        val privacyComment = Privacy.ALL

        val noteId = 0
        val message = "Comment Text"

        //Act
        listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
        listOfNotes.createComment(noteId, message)
        listOfNotes.deleteComment(0)
        listOfNotes.restoreComment(0)

        //Assert
        assertEquals(false, listOfNotes.comments[0].commentDeleted)
    }

    @org.junit.jupiter.api.Test
    fun restoreCommentException() {
        assertThrows<DeletedObjectException>{
            //Arrange
            val title = "Заметка3"
            val text = "Текст3"
            val privacy = 3
            val commentPrivacy = 3
            val privacyView = Privacy.ALL
            val privacyComment = Privacy.ALL

            val noteId = 0
            val message = "Comment Text"

            //Act
            listOfNotes.addNote(title, text, privacy, commentPrivacy, privacyView, privacyComment)
            listOfNotes.createComment(noteId, message)
            listOfNotes.restoreComment(0)
        }
    }
}
