class AgregarPost {
    constructor() {
        this.apiUrl = './AgregarPost';
        this.enviarEvento();
    }
    
    enviarEvento() {
        const form = document.getElementById('nuevoPost');
        if (form){
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
        const cuerpo = cuerpoInput.value.trim();

        if (!titulo) {
            alert('Por favor, ingresa un titulo.');
            return;
        }

        if (!subtitulo) {
            alert('Por favor, ingresa un subtitulo.');
            return;
        }

        if (!cuerpo) {
            alert('Por favor, escribe el contenido del cuerpo del post.');
            return;
        }
        
        if (archivoInput.files.length === 0) {
            alert('Por favor, selecciona una imagen.');
            return;
        }

        const nombreImagen = archivoInput.files[0].name;
        
        const postNuevo = {
            titulo: titulo,
            subtitulo: subtitulo,
            tipoPost: tipoPost,
            cuerpo: cuerpo,
            nombreImagen: nombreImagen
        };
        
        try {
        const response = await fetch(this.apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(postNuevo)
        });

        if (!response.ok) {
            throw new Error('Error al subir el post');
        }

        const resultado = await response.json();
        console.log('Post subido exitosamente', resultado);
        
        } catch (error) {
            console.error('Error:', error);
            alert('Hubo un problema al subir el post');
        }
    }
}


