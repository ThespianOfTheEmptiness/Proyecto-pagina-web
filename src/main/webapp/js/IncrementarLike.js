/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function incrementLikes(id) {
    console.log(id);
    fetch('incrementarLike', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al incrementar likes');
        }
        return response.json();
    })
    .then(data => {
        // Actualizar el contador de likes en el frontend
        const likeCountElement = document.getElementById('likeCount-${id}');
        likeCountElement.textContent = data.likes;
    })
    .catch(error => {
        console.error('Hubo un error:', error);
    });
}


