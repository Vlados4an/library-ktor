package ru.clevertec.di.modules

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import repository.author.AuthorRepository
import repository.genre.GenreRepository
import ru.clevertec.repository.author.AuthorRepositoryImpl
import ru.clevertec.repository.book.BookRepository
import ru.clevertec.repository.book.BookRepositoryImpl
import ru.clevertec.repository.genre.GenreRepositoryImpl

val repositoryModule = DI.Module("repositoryModule") {
    bind<BookRepository>() with singleton { BookRepositoryImpl() }
    bind<AuthorRepository>() with singleton { AuthorRepositoryImpl() }
    bind<GenreRepository>() with singleton { GenreRepositoryImpl() }
}