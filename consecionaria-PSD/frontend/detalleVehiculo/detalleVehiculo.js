//garantia y metododepago

const selectGarantia = document.getElementById('miSelectGarantia');
const mensajeGarantia = document.getElementById('mensajeGarantia');

const selectMetodoPago = document.getElementById('miSelectMetodoDePago');
const mensajeMetodoPago = document.getElementById('mensajeMetodoPago');

selectGarantia.addEventListener('change', () => {
    const opcion = selectGarantia.options[selectGarantia.selectedIndex].text;
    mensajeGarantia.textContent = `Elegiste garantía: ${opcion}`;
});

selectMetodoPago.addEventListener('change', () => {
    const opcion = selectMetodoPago.options[selectMetodoPago.selectedIndex].text;
    mensajeMetodoPago.textContent = `Elegiste método de pago: ${opcion}`;
});

//pop up confirmar

document.addEventListener('DOMContentLoaded', () => {
  const btnConfirmar = document.querySelector('.btn');
  const popup = document.getElementById('compraExitosa');
  btnConfirmar.addEventListener('click', () => {
    popup.style.display = 'block';

  });
});


