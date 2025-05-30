document.addEventListener('DOMContentLoaded', () => {
  const accesorioContainer = document.getElementById('accesorioContainer');
  const addAccesorioBtn = document.getElementById('add-accesorio');

  function createAccesorioItem() {
    const div = document.createElement('div');
    div.classList.add('accesorio-item');

    const inputNombre = document.createElement('input');
    inputNombre.type = 'text';
    inputNombre.name = 'accesorioNombre[]';
    inputNombre.placeholder = 'Nombre del accesorio';

    const inputDescripcion = document.createElement('input');
    inputDescripcion.type = 'text';
    inputDescripcion.name = 'accesorioDescripcion[]';
    inputDescripcion.placeholder = 'Descripción';

    const inputPrecio = document.createElement('input');
    inputPrecio.type = 'number';
    inputPrecio.name = 'accesorioPrecio[]';
    inputPrecio.placeholder = 'Precio';

    const removeBtn = document.createElement('button');
    removeBtn.type = 'button';
    removeBtn.textContent = 'Eliminar';
    removeBtn.addEventListener('click', () => {
      div.remove();
    });

    div.appendChild(inputNombre);
    div.appendChild(inputDescripcion);
    div.appendChild(inputPrecio);
    div.appendChild(removeBtn);

    return div;
  }

  addAccesorioBtn.addEventListener('click', () => {
    const item = createAccesorioItem();
    accesorioContainer.appendChild(item);
  });
});

let boton = document.getElementById('submit');

boton.addEventListener("click", evento => {
  evento.preventDefault();
  registrarVehiculo();
});

let registrarVehiculo = async () => {
  let campos = {};

  campos.patente = document.getElementById("patente").value;
  campos.marca = document.getElementById("marca").value;
  campos.modelo = document.getElementById("modelo").value;
  campos.año = parseInt(document.getElementById("año").value);
  campos.color = document.getElementById("color").value;
  campos.numeroChasis = parseInt(document.getElementById("numeroDeChasis").value);
  campos.numeroMotor = parseInt(document.getElementById("numeroMotor").value);
  campos.asientos = parseInt(document.getElementById("asientos").value);
  campos.precio = parseFloat(document.getElementById("precio").value);
  campos.disponible = campos.estado === "DISPONIBLE";
  campos.kilometraje = parseInt(document.getElementById("kilometraje").value);
  campos.estado = document.getElementById("estado").value;
  campos.tipo = document.getElementById("tipoVehiculo").value;
  campos.imagenUrl = document.getElementById("imagenUrl").value;

  // Crear lista de objetos JSON con nombre, descripción y precio
  const nombres = document.querySelectorAll('input[name="accesorioNombre[]"]');
  const descripciones = document.querySelectorAll('input[name="accesorioDescripcion[]"]');
  const precios = document.querySelectorAll('input[name="accesorioPrecio[]"]');

  campos.adicionales = [];
  for (let i = 0; i < nombres.length; i++) {
    const nombre = nombres[i].value.trim();
    const descripcion = descripciones[i].value.trim();
    const precio = parseFloat(precios[i].value);

    if (nombre && descripcion && !isNaN(precio)) {
      campos.adicionales.push({
        tipo: "Accesorio",
        nombre: nombre,
        descripcion: descripcion,
        precio: precio
      });
    }
  }

  const peticion = await fetch("http://localhost:8080/api/vehiculos", {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(campos)
  });

  if (peticion.ok) {
    const popup = document.getElementById('ingresoExitoso');
    popup.style.display = 'block';

    const cerrarBtn = document.getElementById('cerrarPopup');
    cerrarBtn.addEventListener('click', () => {
      popup.style.display = 'none';
      document.getElementById("vehiculoForm").reset();
    });
  } else {
    alert("Error al registrar vehículo");
  }
};
