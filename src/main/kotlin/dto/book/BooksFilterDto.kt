package dto.book

data class BooksFilterDto(
    val title: String?,
    val authorId: Int?,
    val genreId: Int?,
    val language: String?,
    val yearFrom: Int?,
    val yearTo: Int?,
    val hasCover: Boolean?
)