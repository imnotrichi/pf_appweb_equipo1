/* 
 * ResponderComentario.js
 */

class RespuestaComentario {
    constructor() {
        this.apiUrl = './ResponderComentario';
        this.enviarEvento();
    }

    enviarEvento() {
        const form = document.getElementById('comentarioForm');
        if (form) {
            form.addEventListener('submit', (event) => this.enviarComentario(event));
        }
    } 
}
