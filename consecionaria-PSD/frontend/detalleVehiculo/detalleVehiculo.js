
const select = document.getElementById('miSelect');
const mensaje = document.getElementById('mensajeSeleccion');

select.addEventListener('change', () => {
    const opcion = select.options[select.selectedIndex].text;
    mensaje.textContent = `Elegiste: ${opcion}`;
});

