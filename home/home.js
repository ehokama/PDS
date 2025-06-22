window.onload = function () {
  listarVehiculos();
};

// Función para listar vehículos desde la API
let listarVehiculos = async () => {
  try {
    const peticion = await fetch("http://localhost:8080/vehiculos", {
      method: 'GET',
      headers: {
        'Accept': 'application/json'
      }
    });

    const vehiculos = await peticion.json();

    let catalogoVehiculos = "";

    // Obtener el rol del usuario desde localStorage
    const usuarioJSON = localStorage.getItem('usuario');
    let rolUsuario = null;

    if (usuarioJSON) {
      const usuario = JSON.parse(usuarioJSON);
      rolUsuario = usuario.rol_usuario; // <- adaptado a tu estructura
    }

    // Filtrar vehículos si el rol no es "Administrador"
    const vehiculosAMostrar = (rolUsuario === "Administrador")
      ? vehiculos
      : vehiculos.filter(v => v.tipoEstado === "Disponible");

    for (let vehiculo of vehiculosAMostrar) {
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

    document.querySelector(".vehiculo").innerHTML = catalogoVehiculos;

    // si le das a "reservar" te redirige al detalle del vehiculo
    const reservarBtns = document.querySelectorAll('.reservar-btn');
    reservarBtns.forEach(btn => {
      btn.addEventListener('click', handleReservar);
    });

  } catch (error) {
    console.error("Error al listar vehículos:", error);
  }
};

// Función para manejar reserva de vehículo
function handleReservar(event) {
  console.log('Botón reservar clickeado');
  const estaLogueado = localStorage.getItem('usuario');
  if (estaLogueado) {
    const patenteVehiculo = event.target.getAttribute('data-patente');
    window.location.href = `../detalleVehiculo/detalleVehiculo.html?patente=${patenteVehiculo}`;
  } else {
    alert('Debes iniciar sesión para reservar un vehículo.');
  }
}
