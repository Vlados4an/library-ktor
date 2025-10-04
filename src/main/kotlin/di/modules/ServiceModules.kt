package ru.clevertec.di.modules

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.clevertec.service.author.AuthorServiceImpl
import ru.clevertec.service.book.BookService
import ru.clevertec.service.book.BookServiceImpl
import ru.clevertec.service.genre.GenreServiceImpl
import service.author.AuthorService
import service.genre.GenreService

val serviceModule = DI.Module("serviceModule") {
    bind<BookService>() with singleton { BookServiceImpl(instance(), instance(), instance()) }
    bind<AuthorService>() with singleton { AuthorServiceImpl(instance(), instance()) }
    bind<GenreService>() with singleton { GenreServiceImpl(instance(), instance()) }
}