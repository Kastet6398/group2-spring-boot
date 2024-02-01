var prev = undefined;
const objectsEqual = (o1, o2) =>
    Object.keys(o1).length === Object.keys(o2).length
        && Object.keys(o1).every(p => o1[p].name == o2[p].name);
async function fetchUserBooks() {
    try {
        const response = await fetch('/api/list-books');
        const userBooks2 = await response.json();
        console.log(userBooks2.books);
        console.log(prev);
        console.log(userBooks2.books != prev);
        if (prev == undefined || !objectsEqual(userBooks2.books, prev)) {
        prev = userBooks2.books;

        const userBooksContainer = document.getElementById('userBooks');
        let htmlContent = '';

        userBooks2.books.forEach(book => {
            htmlContent += `
                <div class="book">
                    <img style="width:90%" src='${book.coverSheet}'>
                    <p>${book.name} by ${book.author}</p>
                    <p>${book.description}</p>
            `;

            if (book.publisher == user.id) {
                htmlContent += `
                    <button class="deleteBtn" onclick="deleteBook(${book.id})">Delete</button>
                `;
            }

            htmlContent += `
                    <hr>
                </div>
            `;
        });

        userBooksContainer.innerHTML = htmlContent;
        }

    } catch (error) {
        console.error('Error fetching user books:', error);
    }
}


async function deleteBook(bookId) {
    if (confirm("Are you sure you want to delete this book?")) {
        try {
            const response = await fetch(`/api/delete-book/${bookId}`, {
                method: 'GET',
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
}

fetchUserBooks();
setInterval(fetchUserBooks, 500);
