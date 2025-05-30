document.addEventListener('DOMContentLoaded', () => {
  // Redirigir si no hay sesión activa
  if (!localStorage.getItem('usuarioLogueado')) {
    window.location.href = '../login-register/login-register.html';
    return; // cortar acá
  }

  // El resto del código...
  const cerrarSesionBtn = document.getElementById('btnCerrarSesion');
  if (cerrarSesionBtn) {
    cerrarSesionBtn.addEventListener('click', () => {
      localStorage.removeItem('usuarioLogueado');
      window.location.href = '../login-register/login-register.html';
    });
  }
});
