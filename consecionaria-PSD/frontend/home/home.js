const reservarBtns = document.querySelectorAll('.reservar-btn');
const popup = document.getElementById('login-popup');

reservarBtns.forEach(btn => {
  btn.addEventListener('click', () => {
    const estaLogueado = localStorage.getItem('usuarioLogueado');

    if (estaLogueado) {
      // Redirigir al detalle del vehículo
      window.location.href = '../detalleVehiculo/detalleVehiculo.html';
    } else {
      // Mostrar el popup
      popup.style.display = 'flex'; // Cambié a flex para centrar bien el popup
    }
  });
});

//boton user (redirige a login-register o perfilConfig)

const userButton = document.getElementById('user-button');

userButton.addEventListener('click', () => {
  const estaLogueado = localStorage.getItem('usuarioLogueado');
  if (estaLogueado) {
    const tipoUsuario = localStorage.getItem('tipoUsuario');
    if (tipoUsuario === 'comprador') {
      window.location.href = '../configuracion/comprador.html';
    } else if (tipoUsuario === 'vendedor') {
      window.location.href = '../configuracion/vendedor.html';
    } else if (tipoUsuario === 'admin') {
      window.location.href = '../configuracion/admin.html';
    } else {
      // fallback en caso de que no esté definido el tipo
      window.location.href = '../configuracion/configuracion.html';
    }
  } else {
    window.location.href = '../login-register/login-register.html';
  }
});