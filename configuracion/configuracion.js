document.addEventListener("DOMContentLoaded", () => {
  const estaLogueado = localStorage.getItem('usuario');

  if (!estaLogueado) {
    // Si NO está logueado, redirige al login
    window.location.href = "../login-register/login-register.html";
    return; // para evitar que se ejecute lo demás
  }

  // Si está logueado, mostrar botones (ya están en el HTML, solo queda el listener para cerrar sesión)
  const btnCerrar = document.querySelector(".btn-cerrar");

  btnCerrar.addEventListener("click", (e) => {
    e.preventDefault(); // evitar la navegación automática

    localStorage.clear();
    sessionStorage.clear();

    window.location.href = "../login-register/login-register.html";
  });
});

document.addEventListener("DOMContentLoaded", () => {
  const btnCerrar = document.querySelector(".btn-cerrar");

  btnCerrar.addEventListener("click", (e) => {
    e.preventDefault(); // evitar el link automático

    // Limpiar almacenamiento local o sessionStorage según uses
    localStorage.clear();      // borra todo lo almacenado localmente
    sessionStorage.clear();    // borra todo lo almacenado en sesión


    // Luego redirigir a página de login o inicio
    window.location.href = "http://127.0.0.1:5500/login-register/login-register.html"; 
    // Cambia esta URL a la de tu página de login real
  });
});