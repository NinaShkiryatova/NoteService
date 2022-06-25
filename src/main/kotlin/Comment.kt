class Comment(
    var commentId: Int = 0,
    val noteId: Int,
    var message: String,
    val replyTo: Int?,
    var commentDeleted: Boolean = false
) {
}