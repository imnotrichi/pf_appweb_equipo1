/* 
 * ResponderComentario.js
 */
class RespuestaComentario {
    constructor() {
        this.apiUrl = './ResponderComentario';
        this.enviarEvento();
    }

    enviarEvento() {
        const forms = document.querySelectorAll('#respuestaForm');
        forms.forEach(form => {
            form.addEventListener('submit', (event) => this.enviarComentario(event));
        });
    } 
    
    async enviarComentario(event) {
        
        event.preventDefault();
        
        const form = event.target;
        const contenidoInput = form.querySelector('#contenidoRespuesta');
        const idComentarioInput = form.querySelector('#postComentario');
        
        const contenido = contenidoInput.value.trim();
        const idComentario = idComentarioInput.value;
        
        console.log('Contenido:', contenido);
        console.log('ID Comentario:', idComentario);
        console.log("*****************************")

        // Validar campos
        if (!contenido) {
            alert('Por favor escribe una respuesta.');
            return;
        }

        // Preparar datos para enviar
        const respuestaData = {
            contenido: contenido,
            respuesta: { 
                id: parseInt(idComentario) 
            }
        };
        
        console.log(respuestaData);

        try {
            const response = await fetch(this.apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(respuestaData)
            });

            // Verificar si la respuesta es un JSON válido
            const contentType = response.headers.get('content-type');
            if (!contentType || !contentType.includes('application/json')) {
                throw new Error('Respuesta del servidor no es un JSON válido');
            }

            const result = await response.json();

            // Manejar respuesta del servidor
            if (result.status === 'success') {
                alert('Respuesta registrado exitosamente.');
                contenidoInput.value = ''; 
                // Recargar página para mostrar nueva respuesta
                location.reload(true); 
            } else {
                alert('Error: ' + result.message);
            }
        } catch (error) {
            console.error('Error completo:', error);
            alert('Ocurrió un error al registrar la respuesta. ' + error.toString());
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new RespuestaComentario();
});
