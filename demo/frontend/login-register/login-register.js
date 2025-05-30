// declaracion de variables
  const tabLogin = document.getElementById('tab-login');
  const tabRegister = document.getElementById('tab-register');
  const loginForm = document.getElementById('login-form');
  const registerForm = document.getElementById('register-form');
  const showRegisterLink = document.getElementById('show-register');
  const showLoginLink = document.getElementById('show-login');
  function activateLogin() {
    tabLogin.classList.add('active');
    tabRegister.classList.remove('active');
    loginForm.classList.add('active');
    registerForm.classList.remove('active');
  }
  function activateRegister() {
    tabRegister.classList.add('active');
    tabLogin.classList.remove('active');
    registerForm.classList.add('active');
    loginForm.classList.remove('active');
  }
// clicks para cambiar entre pestañas
  tabLogin.addEventListener('click', activateLogin);
  tabRegister.addEventListener('click', activateRegister);

// Validar que las contraseñas coincidan
  registerForm.addEventListener('submit', (e) => {
    const pass = registerForm['reg-password'].value;
    const passConf = registerForm['reg-password-conf'].value;
    if(pass !== passConf) {
      e.preventDefault();
      alert('Las contraseñas no coinciden');
    }
  });

// registro (conexion a controller con guardado en mssql)

let registroBtn = document.getElementById("registerBtn");
registroBtn.addEventListener("click", evento=>{
    evento.preventDefault(); // <--- Esto detiene el envío del formulario tradicional
    registrarUsuario();
});

let registrarUsuario = async()=>{

let campos = {};

campos.dni = document.getElementById("dni").value;
campos.nombre = document.getElementById("nombre").value;
campos.apellido = document.getElementById("apellido").value;
campos.password = document.getElementById("password").value;
campos.telefono = document.getElementById("telefono").value;
campos.correo = document.getElementById("correo").value;
campos.tipo = "Cliente"; // o "Administrador" pero predet es cliente. Usuario desde administracion puede upgradear los roles

const registro = await fetch("http://localhost:8080/api/usuarios",
{
  method:'POST',
  headers: {
      'Accept':'application/json',
      'Content-Type': 'application/json',
  },
  body: JSON.stringify(campos)
});

if (registro.ok) {
  const popup = document.getElementById('registroExitoso');
  popup.style.display = 'block';

  const cerrarBtn = document.getElementById('cerrarPopup');
  cerrarBtn.addEventListener('click', () => {
    popup.style.display = 'none';
    document.getElementById("vehiculoForm").reset(); // Opcional: resetea el formulario
  });
} else {
  alert("Error al registrarse");
}

}

/* inicio de sesion*/

loginForm.addEventListener('submit', async (e) => {
  e.preventDefault();

  const email = loginForm['login-email'].value.trim();
  const password = loginForm['login-password'].value;

  if (!email || !password) {
    alert('Por favor completá todos los campos');
    return;
  }

  try {
    const response = await fetch('http://localhost:8080/api/usuarios/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ correo: email, password: password }),
    });

    if (response.ok) {
      const usuario = await response.json();
      // Guardamos algún dato del usuario para saber que está logueado
      localStorage.setItem('usuarioLogueado', JSON.stringify({
        correo: usuario.correo,
        nombre: usuario.nombre,
        // podés guardar más campos si querés
      }));

      alert('¡Inicio de sesión exitoso!');
      window.location.href = '../home/home.html'; // Redireccionar al home
    } else if (response.status === 401) {
      alert('Correo o contraseña incorrectos');
    } else {
      alert('Error en el servidor, intentá más tarde');
    }
  } catch (error) {
    alert('Error de conexión: ' + error.message);
  }
});
