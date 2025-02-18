// frontend/js/todos.js

async function getTodos(username) {
    const response = await fetch(`http://localhost:8080/todos/${username}`);
    return response.json();
}

async function addTodo(username, title, description) {
    const response = await fetch("http://localhost:8080/todos/add", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `username=${username}&title=${title}&description=${description}`
    });
    return response.text();
}

async function deleteTodo(todoId) {
    const response = await fetch(`http://localhost:8080/todos/delete/${todoId}`, {
        method: "DELETE"
    });
    return response.text();
}