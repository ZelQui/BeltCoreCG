document.addEventListener('DOMContentLoaded', function() {
    const togglePassword = document.getElementById('togglePassword');
    const passwordField = document.getElementById('passwordField');
    const eyeIcon = document.getElementById('eyeIcon');

    togglePassword.addEventListener('click', function() {
        // Cambiar el tipo del campo de contraseña
        if (passwordField.type === 'text') {  // Si la contraseña está visible
            passwordField.type = 'password';   // Ocultar la contraseña
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        } else {  // Si la contraseña está oculta
            passwordField.type = 'text';       // Mostrar la contraseña
            eyeIcon.classList.remove('fa-eye');
            eyeIcon.classList.add('fa-eye-slash');
        }
    });
});