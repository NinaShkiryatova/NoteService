class Note(
    var title: String,
    var text: String,
    var privacy: Int,
    var commentPrivacy: Int,
    var privacyView: Privacy,
    var privacyComment: Privacy,
    var noteId: Int = 0,
    var noteDeleted: Boolean = false
) {
}

enum class Privacy(val value: Int) {
    ALL(0), FRIENDS(1), FRIENDS_OF_FRIENDS(2), ONLY_ME(3)
}
