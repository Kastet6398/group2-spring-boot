function signup(event) {
    if (document.getElementById("password").value !== document.getElementById("passwordConfirm").value) {
        alert("Passwords did not match");
    } else {
        fetch('/api/sign-up', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'username': document.getElementById('username').value,
                'firstName': document.getElementById('firstName').value,
                'lastName': document.getElementById('lastName').value,
                'email': document.getElementById('email').value,
                'password': document.getElementById('password').value
            })
        })
        .then(response => {
            if (!response.ok) {
                alert('Sign up failed');
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                location.href = '/';
            } else {
                alert('Sign up failed');
            }
        })
        .catch(error => {
            alert('Sign up failed');
        });
    }
    event.preventDefault();
}
