/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function () {
    const enlacesEliminar = document.querySelectorAll(".eliminar-post");

    enlacesEliminar.forEach((enlace) => {
        enlace.addEventListener("click", function (event) {
            const confirmado = confirm("¿Estás seguro de que deseas eliminar este post? Esta acción no se puede deshacer.");
            if (!confirmado) {
                event.preventDefault();
            }
        });
    });
});