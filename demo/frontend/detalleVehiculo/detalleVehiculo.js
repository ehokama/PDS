window.onload = async function () {
  const params = new URLSearchParams(window.location.search);
  const patente = params.get('patente');

  if (!patente) {
    mostrarError404();
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/api/vehiculos/${patente}`);

    if (response.status === 404) {
      mostrarError404();
      return;
    }

    if (!response.ok) {
      throw new Error("Error en la petición");
    }

    const vehiculo = await response.json();
    actualizarDatos(vehiculo);
  } catch (error) {
    console.error("Error al cargar el vehículo:", error);
    mostrarError404();
  }
};

function mostrarError404() {
  // Borro contenido actual y muestro mensaje 404 grande y centrado
  document.body.innerHTML = `
    <div style="height: 100vh; display:flex; flex-direction: column; justify-content:center; align-items:center">
      <h1 style="font-size:6rem; margin:0; color:#d00;">404</h1>
      <p style="font-size:1.5rem; margin:10px 0 0;">Vehículo no encontrado</p>
      <a href="../home/home.html" style="margin-top:20px; font-size:1.2rem; text-decoration:none; color:#06c;">Volver al inicio</a>
    </div>
  `;
}

function actualizarDatos(vehiculo) {
  document.getElementById('imagenVehiculo').src = vehiculo.imagenUrl || '';
  document.getElementById('marcaVehiculo').textContent = vehiculo.marca || '';
  document.getElementById('modeloVehiculo').textContent = vehiculo.modelo || '';
  document.getElementById('kilometrajeVehiculo').textContent = vehiculo.kilometraje || '';
  document.getElementById('precioVehiculo').textContent = vehiculo.precio || '';
  document.getElementById('tituloVehiculo').textContent = `${vehiculo.marca || ''} ${vehiculo.modelo || ''} ${vehiculo.version || ''}`.trim();
  document.getElementById('detalleUbicacion').textContent = `${vehiculo.kilometraje || ''} km - Buenos Aires`;
  document.getElementById('precioContado').textContent = vehiculo.precio || '';
}



let confirmarBtn = document.getElementById("confirmarBtn");
confirmarBtn.addEventListener("click", evento => {
    evento.preventDefault(); // evita que recargue la página
    crearOrdenCompra();
});



let crearOrdenCompra = async () => {
  let campos = {
  "estadoOrden": "EN_PROCESO",
  "fecha": "",
  "total": 35000.0,
  "vehiculo": {
    "tipo": "Camioneta",
    "patente": "AG345LN"
  },
  "usuario": {
    "tipo": "Cliente",
    "dni":"465" 
  }
  };


  const peticion = await fetch("http://localhost:8080/api/ordenes", {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(campos)
  });

  //puedes hacer que salga un pop up que indice que la orden se creo exitosamente y si no se crea que aparezca error al crear la orden?
  try {
    if (!peticion.ok) {
      throw new Error(`Error en la creación de la orden. Código: ${peticion.status}`);
    }

    alert("¡Orden de compra creada exitosamente!");
    window.location.href = '../home/home.html';

  } catch (error) {
    console.error("Error al crear la orden de compra:", error);
    alert("Ocurrió un error al crear la orden. Intenta nuevamente.");
  }
};





