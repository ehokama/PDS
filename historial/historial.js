window.onload = function () {
  listarOrdenes();
};

let listarOrdenes = async () => {
  try {
    const usuario = JSON.parse(localStorage.getItem("usuario"));
    if (!usuario || !usuario.dni) {
      document.querySelector(".orden").innerHTML = "<p>Usuario no logueado.</p>";
      return;
    }

    const peticion = await fetch("http://localhost:8080/ordenes", {
      method: 'GET',
      headers: {
        'Accept': 'application/json'
      }
    });

    const ordenes = await peticion.json();

    // Filtramos las órdenes del usuario actual
    const ordenesUsuario = ordenes.filter(orden => orden.comprador.dni === usuario.dni);

    let listadoOrdenes = "";
    for (let orden of ordenesUsuario) {
      const fechaOrden = orden.fechaCreacion.split("T")[0];
      const estadoOrden = orden.vehiculo.tipoEstado;

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
          <button onclick='exportarOrden(${JSON.stringify(orden).replace(/'/g, "\\'")})'>Exportar en TXT</button>
        </article>
      `;

      listadoOrdenes += contenidoPost;
    }

    document.querySelector(".orden").innerHTML = listadoOrdenes || "<p>No se encontraron órdenes para este usuario.</p>";
  } catch (error) {
    console.error("Error al listar ordenes:", error);
  }
};

function exportarOrden(orden) {
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
