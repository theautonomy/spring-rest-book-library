package org.wei.spring.rest.dao;

import java.util.ArrayList;

import org.wei.spring.rest.domain.Book;

public class InMemoryBookDatabase {
	
	static ArrayList<Book> books = new ArrayList<Book>();
	static int id = 1;
	
	static {
		Book book = new Book();
		book.setId(id++);
		book.setTitle("What a book!");
		book.setAuthor("Mark Smith");
		books.add(book);
		
		book = new Book();
		book.setId(id++);
		book.setTitle("What a new book!");
		book.setAuthor("Smith Mark");
		books.add(book);
	}
	
	public static ArrayList<Book> getBooks() {
		return books;
	}

	public static int getNextIndex() {
		return id++;
	}

}
