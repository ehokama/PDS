window.onload = function () {
  listarVehiculos();
};

// Función para manejar reserva de vehículo
function handleReservar(event) {
  const estaLogueado = localStorage.getItem('usuarioLogueado');
  if (estaLogueado) {
    const patenteVehiculo = event.target.getAttribute('data-patente');
    window.location.href = `../detalleVehiculo/detalleVehiculo.html?patente=${patenteVehiculo}`;
  } else {
    document.getElementById('login-popup').style.display = 'flex';
  }
}


// Botón de usuario (redirige según tipo de usuario o a login)
const userButton = document.getElementById('user-button');

userButton.addEventListener('click', () => {
  const estaLogueado = localStorage.getItem('usuarioLogueado');
  if (estaLogueado) {
    const tipoUsuario = localStorage.getItem('tipoUsuario');
    if (tipoUsuario === 'comprador') {
      window.location.href = '../perfilCliente/perfilCliente.html';
    } else if (tipoUsuario === 'vendedor') {
      window.location.href = '../perfilVendedor/perfilVendedor.html';
    } else if (tipoUsuario === 'admin') {
      window.location.href = '../perfilAdministrador/perfilAdministrador.html';
    } else {
      window.location.href = '../perfilCliente/perfilCliente.html';
    }
  } else {
    window.location.href = '../login-register/login-register.html';
  }
});

// Función para listar vehículos desde la API
let listarVehiculos = async () => {
  try {
    const peticion = await fetch("http://localhost:8080/api/vehiculos", {
      method: 'GET',
      headers: {
        'Accept': 'application/json'
      }
    });

    const vehiculos = await peticion.json();

    let catalogoVehiculos = "";

    for (let vehiculo of vehiculos) {
      let contenidoPost = `
        <article class="post">
          <h2>${vehiculo.marca} ${vehiculo.modelo}</h2>
          <p>${vehiculo.año} - ${vehiculo.kilometraje} km</p>
          <img src="${vehiculo.imagenUrl}" alt="Imagen del vehículo" />
          <p>Precio: $${vehiculo.precio}</p>
          <p>Ubicación: Buenos Aires</p>
          <button class="reservar-btn" data-patente="${vehiculo.patente}">Reservar</button>
        </article>
      `;
      catalogoVehiculos += contenidoPost;
    }

    // Insertar HTML en el contenedor
    document.querySelector(".container").innerHTML = catalogoVehiculos;

    // Agregar eventos a los botones generados
    const reservarBtns = document.querySelectorAll('.reservar-btn');
    reservarBtns.forEach(btn => {
      btn.addEventListener('click', handleReservar);
    });

  } catch (error) {
    console.error("Error al listar vehículos:", error);
  }
};
