package hr.redzicleon.library.services;

import hr.redzicleon.library.domain.Book;

public interface BooksService {
    public Book getBook(String isbn);
}