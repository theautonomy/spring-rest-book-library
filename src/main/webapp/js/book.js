
var rootURL = "restapi/books";

findAllBooks();

$('#btnDelete').hide();

$('#btnSearch').click(function() {
	searchBook($('#searchId').val());
	return false;
});

$('#btnAdd').click(function() {
	newBook();
	return false;
});

$('#btnSave').click(function() {
	if ($('#id').val() == '')
		addBook();
	else
		updateBook();
	return false;
});

$('#btnDelete').click(function() {
	deleteBook();
	return false;
});

$('#bookList a').live('click', function() {
	findBookById($(this).data('identity'));
});

$("img").error(function() {
	$(this).attr("src", "pics/generic.jpg");

});

function searchBook(searchKey) {
	if (searchKey == '')
		findAllBooks();
	else
		findBookById(searchKey);
}

function newBook() {
	$('#btnDelete').hide();
	currentBook = {};
	renderBookDetails(currentBook); 
}

function findAllBooks() {
	console.log('findAllBooks');
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json", 
		success : renderBookList
	});
}

function findBookById(id) {
	console.log('findBookById: ' + id);
	$.ajax({
		type : 'GET',
		url : rootURL + '/' + id,
		dataType : "json",
		success : function(data) {
			$('#btnDelete').show();
			console.log('findBookById success: ' + data.name);
			book = data;
			renderBookDetails(book);
			renderBookList(data);
		}
	});
}

function addBook() {
	console.log('add book');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL,
		dataType : "json",
		data : convertFormDataToJSON(),
		success : function(data, textStatus, jqXHR) {
			alert('Book created successfully at ' + jqXHR.getResponseHeader('Location'));
			$('#btnDelete').show();
			$('#id').val(data.id);
			$('#bookList').append(
					'<li><a href="#" data-identity="' + data.id + '">'
							+ data.author + '</a></li>');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Add book error: ' + textStatus);
		}
	});
}

function updateBook() {
	console.log('Update book');
	$.ajax({
		type : 'PUT',
		contentType : 'application/json',
		url : rootURL + '/' + $('#id').val(),
		dataType : "json",
		data : convertFormDataToJSON(),
		success : function(data, textStatus, jqXHR) {
			alert('Book updated successfully');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Update book error: ' + textStatus);
		}
	});
}

function deleteBook() {
	console.log('Delete book');
	$.ajax({
		type : 'DELETE',
		url : rootURL + '/' + $('#id').val(),
		success : function(data, textStatus, jqXHR) {
			alert('Book deleted successfully');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Delete book error');
		}
	});
}

function renderBookList(data) {
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$('#bookList li').remove();
	$.each(list, function(index, book) {
		$('#bookList').append(
				'<li><a href="#" data-identity="' + book.id + '">'
						+ book.author + '</a></li>');
	});
}

function renderBookDetails(book) {
	$('#id').val(book.id);
	$('#author').val(book.author);
	$('#title').val(book.title);

}

function convertFormDataToJSON() {
	var id = $('#id').val();
	return JSON.stringify({
		"id" : id == "" ? null : id,
		"author" : $('#author').val(),
		"title" : $('#title').val()
	});
}
