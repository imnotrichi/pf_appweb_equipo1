class AgregarImagenPost {
    constructor() {
        this.apiUrl = './GuardarImagenPost';
        this.enviarEvento();
    }

    enviarEvento() {
        const form = document.getElementById('nuevoPost');
        if (form) {
            form.addEventListener('submit', (event) => this.enviarImagenPost(event));
        }
    }

    async enviarImagenPost(event) {
        event.preventDefault();

        const archivoInput = document.getElementById('archivo');
        const formData = new FormData();

        if (archivoInput.files.length === 0) {
            alert('Por favor, selecciona una imagen.');
            return;
        }

        formData.append('imagen', archivoInput.files[0]);

        try {
            const response = await fetch(this.apiUrl, {
                method: 'POST',
                body: formData
            });

            if (!response.ok) {
                const error = await response.json();
                alert(`Error: ${error.message}`);
                return;
            }

            const resultado = await response.json();
            console.log('Respuesta del servidor:', resultado);

            if (resultado.status === 'success') {
                alert('Post creado exitosamente');
                // Redirige a la página principal o a la lista de posts
                window.location.href = './Inicio.jsp';
            } else {
                alert(`Error: ${resultado.message}`);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Hubo un problema al enviar la imagen del post');
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new AgregarImagenPost();
});


