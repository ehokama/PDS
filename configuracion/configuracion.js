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

  // 🔁 Redirigir al home después de éxito
  window.location.href = "http://127.0.0.1:5500/home/home.html";

} catch (error) {
  console.error("Error:", error);
  alert("Hubo un error: " + error.message);
}
