package org.wei.spring.rest.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.wei.spring.rest.domain.Book;

@Repository("bookRepo")
public class InMemoryBookDAO implements IBookDAO {

	@Override
	public ArrayList<Book> findAllBooks() {
		return InMemoryBookDatabase.getBooks(); 
	}

	@Override
	public Book findBook(int index) {
		ArrayList<Book> books = findAllBooks();
		Book temp = null;
		for (Book book : books) {
			if (index == book.getId()) {
				temp = book;
			}
		}
		return temp;
	}

	@Override
	public void addBook(Book book) {
		ArrayList<Book> books = findAllBooks();
		book.setId(InMemoryBookDatabase.getNextIndex());
		books.add(book);
	}

	@Override
	public Book updateBook(Book book) {
		ArrayList<Book> books = findAllBooks();
		Book temp = null;
		for (Book b : books) {
			if (b.getId() == book.getId()) {
				books.remove(b);
				books.add(book);
				temp = book;
				break;
			}
		}
		return temp;
	}

	@Override
	public void removeBook(int index) {
		ArrayList<Book> books = findAllBooks();
		for (Book book : books) {
			if (index == book.getId()) {
				books.remove(book);
				break;
			}
		}
	}

}
