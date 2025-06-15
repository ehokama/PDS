function actualizarDatos(vehiculo) {
  // Setear texto
  document.getElementById("tituloVehiculo").textContent = `${vehiculo.marca} ${vehiculo.modelo}`;
  document.getElementById("tituloPestaña").textContent = `${vehiculo.marca} ${vehiculo.modelo} - Detalle`;
  document.getElementById("marcaVehiculo").textContent = vehiculo.marca;
  document.getElementById("modeloVehiculo").textContent = vehiculo.modelo;
  document.getElementById("kilometrajeVehiculo").textContent = vehiculo.kilometraje;
  document.getElementById("precioVehiculo").textContent = vehiculo.precio;
  document.getElementById("precioContado").textContent = vehiculo.precio;

  // Setear imagen (si viene en la propiedad 'imagenUrl' o 'imagen', ajustá según tu JSON)
  if (vehiculo.imagenUrl) {
    document.getElementById("imagenUrl").src = vehiculo.imagenUrl;
  } else {
    // poner una imagen por defecto si no existe
    document.getElementById("imagenUrl").src = "img/default-car.png";
  }

  // Detalle ubicación si viene, por ejemplo:
  if (vehiculo.ubicacion) {
    document.getElementById("detalleUbicacion").textContent = `Ubicación: ${vehiculo.ubicacion}`;
  } else {
    document.getElementById("detalleUbicacion").textContent = "";
  }
}

window.onload = async function () {
  const params = new URLSearchParams(window.location.search);
  const patente = params.get('patente');

  if (!patente) {
    mostrarError404();
    return;
  }

  try {
    const response = await fetch(`http://127.0.0.1:8080/vehiculos/${patente}`);

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

















// datos de la orden a enviar


const nuevaOrden = {
    tipo_vehiculo: document.getElementById("tipoVehiculo").value,
    patente: document.getElementById("patente").value,
    marca: document.getElementById("marca").value,
    modelo: document.getElementById("modelo").value,
    año: document.getElementById("año").value,
    color: document.getElementById("color").value,
    kilometraje: document.getElementById("kilometraje").value,
    numeroChasis: document.getElementById("numeroChasis").value,
    numeroMotor: document.getElementById("numeroMotor").value,
    precio: document.getElementById("precio").value,
    imagenUrl: document.getElementById("imagenUrl").value,
    adicionales: [],
    tipoEstado : "Disponible",
};


// Función para enviar la orden
function enviarOrden(orden) {
  fetch('http://localhost:8080/ordenes', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(orden)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status}`);
    }
    return response.json(); // parsea el JSON de respuesta
  })
  .then(data => {
    console.log('Orden creada:', data);
    alert('Orden creada con éxito, ID: ' + data.id);
  })
  .catch(error => {
    console.error('Error al crear la orden:', error);
    alert('Error al crear la orden: ' + error.message);
  });
}

// Ejemplo de llamada
enviarOrden(nuevaOrden);
