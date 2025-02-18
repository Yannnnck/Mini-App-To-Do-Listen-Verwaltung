// frontend/js/auth.js

async function registerUser(username, email, password) {
    const response = await fetch("http://localhost:8080/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `username=${username}&email=${email}&password=${password}`
    });
    return response.text();
}

async function loginUser(username, password) {
    const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `username=${username}&password=${password}`
    });
    return response.text();
}
