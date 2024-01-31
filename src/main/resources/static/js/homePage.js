function deleteBook(id) {
    fetch('/api/delete-book/' + id, {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            alert('Deletion failed');
        }
        return response.json();
    })
    .then(data => {
        if (data.success) {
            location.reload();
        } else {
            alert('Deletion failed');
        }
    })
    .catch(error => {
        alert('Deletion failed');
    });
    event.preventDefault();
}
