class ComentarioManager {
    constructor() {
        this.apiUrl = './AgregarComentario';
        this.postServletUrl = './Post';
        this.enviarEvento();
    }

    enviarEvento() {
        const form = document.getElementById('comentarioForm');
        if (form) {
            form.addEventListener('submit', (event) => this.enviarComentario(event));
        }
    }

    async enviarComentario(event) {
        event.preventDefault();

        const contenidoInput = document.getElementById('contenidoComentario');
        const idPostInput = document.getElementById('postId');

        const contenido = contenidoInput.value.trim();
        const idPost = idPostInput.value;

        // Validar campos
        if (!contenido) {
            alert('Por favor escribe un comentario.');
            return;
        }

        // Preparar datos para enviar
        const comentarioData = {
            contenido: contenido,
            post: {
                id: parseInt(idPost)
            }
        };

        try {
            const response = await fetch(this.apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(comentarioData)
            });

            // Verificar si la respuesta es un JSON válido
            const contentType = response.headers.get('content-type');
            if (!contentType || !contentType.includes('application/json')) {
                throw new Error('Respuesta del servidor no es un JSON válido');
            }

            const result = await response.json();

            // Manejar respuesta del servidor
            if (result.status === 'success') {
                contenidoInput.value = '';
                // Llamar al servlet para actualizar los comentarios
                await this.actualizarComentarios(idPost);
            } else {
                alert('Error: ' + result.message);
            }
        } catch (error) {
            console.error('Error completo:', error);
            alert('Ocurrió un error al registrar el comentario. ' + error.toString());
        }
    }

    async actualizarComentarios(idPost) {
        try {
            const response = await fetch(`${this.postServletUrl}?id=${idPost}`, {
                method: 'GET'
            });

            if (!response.ok) {
                throw new Error('Error al obtener los comentarios del servidor.');
            }

            const html = await response.text();

            // Actualizar la sección de comentarios en el DOM
            const comentariosSection = document.getElementById('comentarios');
            if (comentariosSection) {
                comentariosSection.innerHTML = html;
            }
        } catch (error) {
            console.error('Error al actualizar los comentarios:', error);
            alert('No se pudo actualizar la lista de comentarios.');
        }
    }
}

// Inicializar el manejador de comentarios al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    new ComentarioManager();
});
