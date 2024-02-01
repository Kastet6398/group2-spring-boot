
        function submitForm(event) {
            var formData = {
                name: document.getElementById('name').value,
                urlOfContent: document.getElementById('urlOfContent').value,
                author: document.getElementById('author').value,
                coverSheet: document.getElementById('coverSheet').value,
                category: document.getElementById('category').value,
                releaseYear: parseInt(document.getElementById('releaseYear').value),
                pagesAmount: parseInt(document.getElementById('pagesAmount').value),
                genre: document.getElementById('genre').value,
                description: document.getElementById('description').value
            };

            console.log(formData);

            fetch('/api/create-book', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.success || data.id) {
                    alert('Book created successfully!');
                } else {
                    alert('Error creating book: ' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        event.preventDefault();
        }
