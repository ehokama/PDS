window.onload = function () {
  listarOrdenes();
};

let listarOrdenes = async () => {
  try {
    const peticion = await fetch("http://localhost:8080/ordenes", {
      method: 'GET',
      headers: {
        'Accept': 'application/json'
      }
    });

    const ordenes = await peticion.json();

    // Obtener filtros del DOM
    const fechaFiltro = document.getElementById("filtroFecha").value;
    const estadoFiltro = document.getElementById("filtroEstado").value;

    let listadoOrdenes = "";
for (let orden of ordenes) {
  const fechaOrden = orden.fechaCreacion.split("T")[0];
  const estadoOrden = orden.vehiculo.tipoEstado;

  if (fechaFiltro && fechaOrden !== fechaFiltro) continue;
  if (estadoFiltro && estadoOrden !== estadoFiltro) continue;

  let contenidoPost = `
    <article class="post">
      <p><strong>Número de orden:</strong> ${orden.numeroDeOrden}</p>
      <p><strong>Comprador:</strong> ${orden.comprador.nombre} ${orden.comprador.apellido}</p>
      <p><strong>Vendedor:</strong> ${orden.vendedor.nombre} ${orden.vendedor.apellido}</p>
      <p><strong>Fecha:</strong> ${fechaOrden}</p>
      <p><strong>Vehículo:</strong> ${orden.vehiculo.marca} ${orden.vehiculo.modelo} (${orden.vehiculo.año})</p>
      <p><strong>Estado:</strong> ${estadoOrden}</p>
      <p><strong>Método de Pago:</strong> ${orden.metodoDePago ? orden.metodoDePago.nombre : "No especificado"}</p>
      <p><strong>Total:</strong> $${orden.costoTotal.toLocaleString()}</p>
      <img src="${orden.vehiculo.imagenUrl}" alt="Imagen del vehículo">
      <br><br>
      <button onclick='exportarOrdenTXT(${JSON.stringify(orden).replace(/'/g, "\\'")})'>Exportar en TXT</button>
      <button onclick='exportarOrdenCSV(${JSON.stringify(orden).replace(/'/g, "\\'")})'>Exportar en CSV</button>
    </article>
  `;

  listadoOrdenes += contenidoPost;
}

    document.querySelector(".orden").innerHTML = listadoOrdenes || "<p>No se encontraron órdenes con los filtros seleccionados.</p>";

  } catch (error) {
    console.error("Error al listar ordenes:", error);
  }
};
function exportarOrdenTXT(orden) {
  const contenido = JSON.stringify(orden, null, 2); // formateado
  const blob = new Blob([contenido], { type: "text/plain" });
  const url = URL.createObjectURL(blob);

  const link = document.createElement("a");
  link.href = url;
  link.download = `orden_${orden.numeroDeOrden}.txt`;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  URL.revokeObjectURL(url);
}

function exportarOrdenCSV(orden) {
  // Encabezados y datos
  const encabezados = [
    "Número de Orden", "Comprador", "Vendedor", "Fecha", "Marca", "Modelo", "Año",
    "Estado", "Método de Pago", "Costo Total"
  ];

  const fila = [
    orden.numeroDeOrden,
    `${orden.comprador.nombre} ${orden.comprador.apellido}`,
    `${orden.vendedor.nombre} ${orden.vendedor.apellido}`,
    orden.fechaCreacion.split("T")[0],
    orden.vehiculo.marca,
    orden.vehiculo.modelo,
    orden.vehiculo.año,
    orden.vehiculo.tipoEstado,
    orden.metodoDePago ? orden.metodoDePago.nombre : "No especificado",
    orden.costoTotal
  ];

  const contenido = `${encabezados.join(",")}\n${fila.map(valor => `"${valor}"`).join(",")}`;
  const blob = new Blob([contenido], { type: "text/csv" });
  const url = URL.createObjectURL(blob);

  const link = document.createElement("a");
  link.href = url;
  link.download = `orden_${orden.numeroDeOrden}.csv`;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  URL.revokeObjectURL(url);
}
