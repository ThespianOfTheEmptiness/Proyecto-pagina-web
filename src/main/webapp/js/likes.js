// Función para actualizar el contador de likes al cargar la página
function cargarLikes(id) {
    fetch(`/obtenerLikes?id=${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al obtener los likes');
        }
        return response.json();
    })
    .then(data => {
        // Actualizar el contador de likes en el frontend
        const likeCountElement = document.getElementById(`likeCount-${id}`);
        likeCountElement.textContent = data.likes; // Actualiza el texto del contador
    })
    .catch(error => {
        console.error('Hubo un error al cargar los likes:', error);
    });
}

// Función para incrementar los likes al hacer click
function incrementLikes(id) {
    fetch('incrementarLike', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al incrementar likes');
        }
        return response.json();
    })
    .then(data => {
        // Actualizar el contador de likes en el frontend
        const likeCountElement = document.getElementById(`likeCount-${id}`);
        likeCountElement.textContent = data.likes; // Actualiza el contador
    })
    .catch(error => {
        console.error('Hubo un error:', error);
    });
}

// Cargar los likes al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    const likeButtons = document.querySelectorAll('.like-button');
    likeButtons.forEach(button => {
        const id = button.getAttribute('data-id');
        cargarLikes(id);  // Cargar los likes al cargar la página
    });
});