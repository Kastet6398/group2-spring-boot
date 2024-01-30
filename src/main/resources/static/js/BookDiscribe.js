// Получение списка книг
fetch('/api/books')
    .then(response => response.json())
    .then(books => {

    });

// Получение информации о конкретной книге
fetch('/api/books/bookId')
    .then(response => response.json())
    .then(book => {
    });
