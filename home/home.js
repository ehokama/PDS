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

    // Insertar HTML en el "<section class="vehiculo">" dentro delcatalogo
    document.querySelector(".vehiculo").innerHTML = catalogoVehiculos;

    // si le das a "reservar" te redirige al detalle del vehiculo
    const reservarBtns = document.querySelectorAll('.reservar-btn');
    reservarBtns.forEach(btn => {
        btn.addEventListener('click', handleReservar);
    });

    } catch (error) {
        console.error("Error al listar vehículos:", error);
    }


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

};