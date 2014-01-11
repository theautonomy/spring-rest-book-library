package org.wei.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wei.spring.rest.dao.IBookDAO;
import org.wei.spring.rest.domain.Book;

@Controller
@RequestMapping(value = "/restapi/books")
public class BooksController {
	
	@Autowired
	@Qualifier("bookRepo")
	private IBookDAO dao; 
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Book> getBooks() {
		return dao.findAllBooks(); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Book findBook(@PathVariable("id") int index) {
		return dao.findBook(index);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteBook(@PathVariable("id") int index) {
		dao.removeBook(index);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes="application/json")
	@ResponseBody
	public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
		return dao.updateBook(book);
	}

	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Book addBook(@RequestBody Book book) {
		dao.addBook(book);
		return book;
	}

}
