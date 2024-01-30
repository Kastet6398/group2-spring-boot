function showBookDetails(bookId) {
    const modal = document.getElementById('bookDetailsModal');
    const titleElement = document.getElementById('bookDetailsTitle');
    const descriptionElement = document.getElementById('bookDetailsDescription');

    // Временные данные для примера:
    const bookDetails = {
        book1: { title: 'Название книги 1', description: 'Описание первой книги.' },
        book2: { title: 'Название книги 2', description: 'Описание второй книги.' }

    };

    const selectedBook = bookDetails[bookId];

    if (selectedBook) {
        titleElement.innerText = selectedBook.title;
        descriptionElement.innerText = selectedBook.description;
        modal.style.display = 'flex';
    }
}

function closeBookDetailsModal() {
    const modal = document.getElementById('bookDetailsModal');
    modal.style.display = 'none';
}
