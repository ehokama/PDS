//ACCESORIOS
// Obtenemos el container donde iran todos los accesorios y el boton para agregarlos
const accesorioContainer = document.getElementById("accesorioContainer");
const agregarAccesorioBtn = document.getElementById("agregarAccesorio");

// Cuando se hace clic en el boton
agregarAccesorioBtn.addEventListener("click", () => {

    // Creamos un div que contenga los inputs y el boton de eliminar
    const fila = document.createElement("div");
    fila.className = "accesorio-fila";

    //Creamos los inputs del accesorio
    const inputNombre = document.createElement("input");
    inputNombre.type = "text";
    inputNombre.placeholder = "Nombre del accesorio";

    const inputDescripcion = document.createElement("input");
    inputDescripcion.type = "text";
    inputDescripcion.placeholder = "Descripción";

    const inputPrecio = document.createElement("input");
    inputPrecio.type = "number";
    inputPrecio.placeholder = "Precio";

    //creamos el boton de eliminar el accesorio en especifico
    const botonEliminar = document.createElement("button");
    botonEliminar.textContent = "Eliminar";
    botonEliminar.type = "button";

    botonEliminar.addEventListener("click", () => {
      accesorioContainer.removeChild(fila);
    });

    fila.appendChild(inputNombre);
    fila.appendChild(inputDescripcion);
    fila.appendChild(inputPrecio);
    fila.appendChild(botonEliminar);

    accesorioContainer.appendChild(fila);
  })




//EQUIPAMIENTO
// Obtenemos el container donde iran todos los equipamientos y el boton para agregarlos
const equipamientoExtraContainer = document.getElementById("equipamientoExtraContainer");
const agregarEquipamientoExtraBtn = document.getElementById("agregarEquipamientoExtra");

// Cuando se hace clic en el boton
agregarEquipamientoExtraBtn.addEventListener("click", () => {

    // Creamos un div que contenga los inputs y el boton de eliminar
    const fila = document.createElement("div");
    fila.className = "equipamientoExtra-fila";

    //Creamos los inputs del accesorio
    const inputNombre = document.createElement("input");
    inputNombre.type = "text";
    inputNombre.placeholder = "Nombre del equipamiento";

    const inputDescripcion = document.createElement("input");
    inputDescripcion.type = "text";
    inputDescripcion.placeholder = "Descripción";

    //creamos el boton de eliminar el accesorio en especifico
    const botonEliminar = document.createElement("button");
    botonEliminar.textContent = "Eliminar";
    botonEliminar.type = "button";

    botonEliminar.addEventListener("click", () => {
      equipamientoExtraContainer.removeChild(fila);
    });

    fila.appendChild(inputNombre);
    fila.appendChild(inputDescripcion);
    fila.appendChild(botonEliminar);

    equipamientoExtraContainer.appendChild(fila);
})






//POST DEL VEHICULO 
const subirVehiculoBtn = document.getElementById("submit");

subirVehiculoBtn.addEventListener("click",() => {
    event.preventDefault();  // Evita recarga de página
    const datosVehiculo = {
        tipo_vehiculo: document.getElementById("tipoVehiculo").value,
        patente: document.getElementById("patente").value,
        marca: document.getElementById("marca").value,
        modelo: document.getElementById("modelo").value,
        año: document.getElementById("año").value,
        color: document.getElementById("color").value,
        kilometraje: document.getElementById("kilometraje").value,
        numeroDeChasis: document.getElementById("numeroDeChasis").value,
        numeroMotor: document.getElementById("numeroMotor").value,
        precio: document.getElementById("precio").value,
        imagenUrl: document.getElementById("imagenUrl").value,
        adicionales: [],
        tipoEstado : "Disponible",
    };


    const filasAccesorio = document.querySelectorAll(".accesorio-fila");
    filasAccesorio.forEach(fila => {
        const [nombre, descripcion, precio] = fila.querySelectorAll("input");
        datosVehiculo.adicionales.push({
            tipo: "Accesorio",
            nombre: nombre.value,
            descripcion: descripcion.value,
            precio: precio.value
        });
    });    

    const filasEquipamientoExtra = document.querySelectorAll(".equipamientoExtra-fila");
    filasEquipamientoExtra.forEach(fila => {
        const [nombre, descripcion] = fila.querySelectorAll("input");
        datosVehiculo.adicionales.push({
            tipo: "EquipamientoExtra",
            nombre: nombre.value,
            descripcion: descripcion.value,
            precio: 0
        });
    });
    console.log(JSON.stringify(datosVehiculo, null, 2));
    fetch("http://localhost:8080/vehiculos", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(datosVehiculo)
    }).then(res => {
        if (!res.ok) throw new Error("Error al enviar los datos");
        return res.json();
    }).then(data => {
        console.log("Vehículo subido correctamente:", data);
        alert("Vehículo subido correctamente");
    }).catch(error => {
        console.error("Error:", error);
        alert("Hubo un error al subir el vehículo");
    });

})

