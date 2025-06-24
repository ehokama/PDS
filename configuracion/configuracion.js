document.addEventListener("DOMContentLoaded", () => {
  const usuario = JSON.parse(localStorage.getItem('usuario'));

  if (!usuario) {
    // redirige al login
    window.location.href = "../login-register/login-register.html";
    return;
  }

  const rol = usuario.rol_usuario.toLowerCase();

  if (rol !== "administrador") {
    const btnAgregar = document.querySelector(".btn-agregar");
    const btnReportes = document.querySelector(".btn-reportes");

    if (btnAgregar) btnAgregar.style.display = "none";
    if (btnReportes) btnReportes.style.display = "none";
  }

  if (rol !== "cliente") {
    const btnHistorial = document.querySelector(".btn-historial");
    if (btnHistorial) btnHistorial.style.display = "none";
  }

  const btnCerrar = document.querySelector(".btn-cerrar");
  if (btnCerrar) {
    btnCerrar.addEventListener("click", (e) => {
      e.preventDefault();
      localStorage.clear();
      sessionStorage.clear();
      window.location.href = "../login-register/login-register.html";
    });
  }
});
