document.addEventListener('DOMContentLoaded', function () {
    const deleteUserBooksBtn = document.getElementById('deleteUserBooksBtn');

    deleteUserBooksBtn.addEventListener('click', function () {
        deleteUserBooks();
    });

    fetchUserBooks();
});

async function fetchUserBooks() {
    try {
        const response = await fetch('/api/books'); // Замените на ваш эндпоинт получения книг
        const userBooks = await response.json();

        const userBooksContainer = document.getElementById('userBooks');
        userBooksContainer.innerHTML = '';

        userBooks.forEach(book => {
            const bookDiv = document.createElement('div');
            bookDiv.innerHTML = `
                <p>${book.name} by ${book.author}</p>
                <button class="deleteBtn" onclick="deleteBook(${book.id})">Delete</button>
                <hr>
            `;
            userBooksContainer.appendChild(bookDiv);
        });
    } catch (error) {
        console.error('Error fetching user books:', error);
    }
}

async function deleteBook(bookId) {
    try {
        const response = await fetch(`/api/delete-book/${bookId}`, {
            method: 'DELETE',
        });
        const data = await response.json();

        if (data.success) {
            alert('Book deleted successfully!');
            fetchUserBooks();
        } else {
            alert('Error deleting book: ' + data.message);
        }
    } catch (error) {
        console.error('Error deleting book:', error);
    }
}

function deleteUserBooks() {
    try {
        fetch('/api/delete-all-user-books', {
            method: 'DELETE',
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('All user books deleted successfully!');
                fetchUserBooks();
            } else {
                alert('Error deleting user books: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    } catch (error) {
        console.error('Error deleting user books:', error);
    }
}
