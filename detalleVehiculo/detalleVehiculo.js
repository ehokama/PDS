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
    const response = await fetch(`http://localhost:8080/vehiculos/${patente}`);
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
















document.getElementById("confirmarBtn").addEventListener("click", confirmarOrden);

async function enviarOrden(nuevaOrden) {
  const response = await fetch("http://localhost:8080/ordenes", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(nuevaOrden)
  });

  if (!response.ok) {
    const errorText = await response.text(); // para ver el mensaje desde el backend
    throw new Error("Error al crear la orden: " + errorText);
  }

  return await response.json();
}

async function confirmarOrden() {
  const usuarioLogueado = JSON.parse(localStorage.getItem('usuario'));
  if (!usuarioLogueado || !usuarioLogueado.dni) {
    alert("No hay usuario logueado. Iniciá sesión para continuar.");
    return;
  }

  const garantias = {
    sin: { nombre: "Sin Garantía", descripcion: "0 meses", precio: 0 },
    plata: { nombre: "Garantía Plata", descripcion: "6 meses", precio: 300000 },
    oro: { nombre: "Garantía Oro", descripcion: "9 meses", precio: 405000 },
    platino: { nombre: "Garantía Platino", descripcion: "12 meses", precio: 540000 }
  };

  const selectGarantia = document.getElementById("miSelectGarantia");
  const opcionSeleccionada = selectGarantia.value;
  const garantiaSeleccionada = garantias[opcionSeleccionada];

  const patenteVehiculo = new URLSearchParams(window.location.search).get('patente');

  const nuevaOrden = {
    numeroDeOrden: 0, // opcional si el backend lo ignora o lo autogenera
    compradorDni: usuarioLogueado.dni,
    vendedorDni: "45123456", // valor temporal de prueba
    patenteVehiculo: patenteVehiculo,
    metodoDePagoId: document.getElementById("miSelectMetodoDePago").value,
    nombreCompleto: document.getElementById("nombreCompleto").value,
    cuit: document.getElementById("cuit").value,
    direccion: document.getElementById("direccion").value,
    // fechaCreacion se puede omitir si se autogenera en el backend
  };

  console.log("Enviando orden:", JSON.stringify(nuevaOrden, null, 2));

  try {
    await enviarOrden(nuevaOrden);

    const responseVehiculo = await fetch(`http://localhost:8080/vehiculos/${patenteVehiculo}`);
    if (!responseVehiculo.ok) throw new Error("Vehículo no encontrado");

    const vehiculo = await responseVehiculo.json();

    vehiculo.adicionales.push({
      tipo: "GarantiaExtendida",
      nombre: garantiaSeleccionada.nombre,
      descripcion: garantiaSeleccionada.descripcion,
      precio: garantiaSeleccionada.precio
    });

    vehiculo.tipoEstado = "Vendido";

    const responseUpdate = await fetch(`http://localhost:8080/vehiculos/${patenteVehiculo}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(vehiculo)
    });

    if (!responseUpdate.ok) throw new Error("Error al actualizar el vehículo");

    const data = await responseUpdate.json();
    console.log("Vehículo actualizado correctamente:", data);
    alert("Orden confirmada y garantía registrada.");
  } catch (error) {
    console.error("Error:", error);
    alert("Hubo un error: " + error.message);
  }
}



