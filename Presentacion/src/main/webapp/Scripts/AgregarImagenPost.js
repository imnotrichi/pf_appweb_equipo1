class AgregarImagenPost {
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
        
        const archivoInput = document.getElementById('archivo');
        const formData = new FormData();
        
        if (archivoInput.files.length === 0) {
            alert('Por favor, selecciona una imagen.');
            return;
        }
        
        formData.append('file', archivoInput.files[0]);
        
        try {
            const response = await fetch(this.apiUrl, {
                method: 'POST',
                body: formData
            });
            
            if (!response.ok) {
                throw new Error('Error en la solicitud');
            }
            
            const resultado = await response.json();
            console.log('Respuesta del servidor:', resultado);
            
        } catch (error) {
            console.error('Error:', error);
            alert('Hubo un problema al enviar la imagen del post');
        }
    }
}


