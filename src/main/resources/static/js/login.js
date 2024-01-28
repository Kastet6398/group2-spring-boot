function login(event) {
    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            'username': document.getElementById('username').value,
            'password': document.getElementById('password').value
        })
    })
    .then(response => {
        if (!response.ok) {
            alert('Login failed');
        }
        return response.json();
    })
    .then(data => {
        if (data.success) {
            location.href = '/';
        } else {
            alert('Login failed');
        }
    })
    .catch(error => {
        alert('Login failed');
    });
    event.preventDefault();
}
