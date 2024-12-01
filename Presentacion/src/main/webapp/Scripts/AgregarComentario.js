class ComentarioManager {
    constructor() {
        this.apiUrl = './AgregarComentario';
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
            console.log(contenido);

            // Verificar si la respuesta es un JSON válido
            const contentType = response.headers.get('content-type');
            if (!contentType || !contentType.includes('application/json')) {
                throw new Error('Respuesta del servidor no es un JSON válido');
            }

            const result = await response.json();

            // Manejar respuesta del servidor
            if (result.status === 'success') {
                alert('Comentario registrado exitosamente.');
                contenidoInput.value = '';
                location.reload(true);
//
//                // Crear nuevo elemento de comentario y agregarlo a la lista
//                const nuevoComentario = document.createElement('div');
//                nuevoComentario.classList.add('comentario');
//                nuevoComentario.innerHTML = `
//                <p>
//                    <span class="usuario"></span>
//                    <br>
//                    ${comentarioData.contenido}
//                </p>`;
//
//                const contenedorComentarios = document.querySelector('.contenedor-comentario');
//                contenedorComentarios.appendChild(nuevoComentario);

            } else {
                alert('Error: ' + result.message);
            }
        } catch (error) {
            console.error('Error completo:', error);
            alert('Ocurrió un error al registrar el comentario. ' + error.toString());
        }
    }
}

// Inicializar el manejador de comentarios al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    new ComentarioManager();
});