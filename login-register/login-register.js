document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('register-form');

    registerForm.addEventListener('submit', function (e) {
      e.preventDefault(); // prevenir el comportamiento defeault de enviar el formulario
        
      const nombre = document.getElementById('nombre').value;
      const apellido = document.getElementById('apellido').value;
      const dni = document.getElementById('dni').value;
      const correo = document.getElementById('correo').value;
      const telefono = document.getElementById('telefono').value;
      const password = document.getElementById('password').value;
      const confirmarPassword = document.getElementById('regPasswordConf').value;
      // Validar que las contraseñas coincidan
      if (password !== confirmarPassword) {
          alert("Las contraseñas no coinciden");
          return;
      }
      // Crear el objeto usuario  
      const usuario = {
        rol_usuario: 'Cliente',
        nombre: nombre,
        apellido: apellido,
        dni: dni,
        correo: correo,
        telefono: telefono,
        password: password
      };

        // Enviar la solicitud POST al backend
        fetch('http://localhost:8080/usuarios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuario)
        })
        .then(response => {
            if (response.ok) {
                alert('Usuario registrado correctamente');
                registerForm.reset();
            } else {
                alert('Error al registrar el usuario');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al conectar con el servidor');
        });
    });
});


document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');

    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const correo = document.getElementById('login-email').value;
        const password = document.getElementById('login-password').value;

        const loginData = {
            correo: correo,
            password: password
        };

        fetch('http://localhost:8080/usuarios/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Credenciales inválidas');
            }
            return response.json();
        })
        .then(usuario => {
            alert('Login exitoso');
            // Guardar usuario en localStorage
            localStorage.setItem('usuario', JSON.stringify(usuario));
            window.location.href = '../home/home.html';
        })
        .catch(error => {
            alert(error.message);
        });
    });
});

document.getElementById("tab-login").addEventListener("click", () => {
  document.getElementById("login-form").classList.add("active");
  document.getElementById("register-form").classList.remove("active");
  document.getElementById("tab-login").classList.add("active");
  document.getElementById("tab-register").classList.remove("active");
});

document.getElementById("tab-register").addEventListener("click", () => {
  document.getElementById("register-form").classList.add("active");
  document.getElementById("login-form").classList.remove("active");
  document.getElementById("tab-register").classList.add("active");
  document.getElementById("tab-login").classList.remove("active");
});