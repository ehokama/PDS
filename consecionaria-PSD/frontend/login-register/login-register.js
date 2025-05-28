
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
// Simular inicio de sesión exitoso
loginForm.addEventListener('submit', (e) => {
  e.preventDefault(); // Prevenir envío real del formulario

  const email = loginForm['login-email'].value;
  const password = loginForm['login-password'].value;

  // Aca podrías validar que el usuario existe, etc. Por ahora simulamos que está todo bien
  if (email && password) {
    localStorage.setItem('usuarioLogueado', 'true');
    alert('¡Inicio de sesión exitoso!');
    window.location.href = '../home/homeUser.html'; // Redireccioná a la página del usuario logueado
  } else {
    alert('Por favor completá todos los campos');
  }
});