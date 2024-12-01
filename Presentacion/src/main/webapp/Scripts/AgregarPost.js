class AgregarPost {
    constructor() {
        this.apiUrl = './AgregarPost';
        this.enviarEvento();
    }
    
    enviarEvento() {
        const form = document.getElementById("nuevoPost");
        if(form){
            form.addEventListener('submit', (event) => this.enviarPost(event));
        }
    }
    
    async enviarPost(evento) {
        evento.preventDefault();
        
        const data = new FormData();
        
        const tituloInput = document.getElementById('titulo');
        const subtituloInput = document.getElementById('subtitulo');
        const tipoPostInput = document.getElementById('tipo-post');
        const cuerpoInput = document.getElementById('cuerpo');
        const archivoInput = document.getElementById('archivo');
        
        
        const titulo = tituloInput.value.trim();
        const subtitulo = subtituloInput.value.trim();
        const tipoPost = tipoPostInput.value;
        const cuerpo = cuerpoInput.value.trim();
        const archivo = archivoInput.files[0];

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
        
        data.append('titulo', titulo);
        data.append('subtitulo', subtitulo);
        data.append('tipoPost', tipoPost);
        data.append('cuerpo', cuerpo);
        data.append('archivo', archivo);

        try {
            const response = await fetch(this.apiUrl, {
                method: 'POST',
                body: data
            });

            const result = await response.json();

            if (response.ok) {
                alert('Post publicado exitosamente.');
            } else {
                alert('Error al crear el post');
            }
        } catch (error) {
            console.error('Error completo:', error);
            alert('Ocurrió un error al registrar el comentario. ' + error.toString());
        }
    }
}


