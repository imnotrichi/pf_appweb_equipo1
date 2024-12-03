class AgregarPost {
    constructor() {
        this.apiUrl = './CrearPost';
        this.enviarEvento();
    }

    enviarEvento() {
        const form = document.getElementById('nuevoPost');
        if (form) {
            form.addEventListener('submit', (event) => this.enviarPost(event));
        }
    }

    async enviarPost(event) {
        event.preventDefault();

        const tituloInput = document.getElementById('titulo');
        const subtituloInput = document.getElementById('subtitulo');
        const tipoPostInput = document.getElementById('tipo-post');
        const cuerpoInput = document.getElementById('cuerpo');
        const archivoInput = document.getElementById('archivo');


        const titulo = tituloInput.value.trim();
        const subtitulo = subtituloInput.value.trim();
        const tipoPost = tipoPostInput.value;
        const contenido = cuerpoInput.value.trim();

        if (!titulo) {
            alert('Por favor, ingresa un titulo.');
            return;
        }

        if (!subtitulo) {
            alert('Por favor, ingresa un subtitulo.');
            return;
        }

        if (!contenido) {
            alert('Por favor, escribe el contenido del cuerpo del post.');
            return;
        }

        if (archivoInput.files.length === 0) {
            alert('Por favor, selecciona una imagen.');
            return;
        }

        const imagen = archivoInput.files[0].name;

        const postNuevo = {
            titulo: titulo,
            subtitulo: subtitulo,
            tipoPost: tipoPost,
            contenido: contenido,
            imagen: imagen
        };

        try {
            const response = await fetch(this.apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(postNuevo)
            });

            if (!response.ok) {
                throw new Error('Error al subir el post');
            } else {
                // Redirige a la página principal o a la lista de posts
//                window.location.href = './General.jsp';
            }

        } catch (error) {
            console.error('Error:', error);
            alert('Hubo un problema al subir el post');
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new AgregarPost();
});


