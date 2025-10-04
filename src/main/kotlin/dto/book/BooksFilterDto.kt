package dto.book

data class BooksFilterDto(
    val title: String? = null,
    val author: String? = null,
    val genre: String? = null,
    val language: String? = null,
    val yearFrom: Int? = null,
    val yearTo: Int? = null,
    val hasCover: Boolean? = null
)