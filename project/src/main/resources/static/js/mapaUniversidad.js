// FunciÃ³n para mostrar el pop-up
function mostrarPopup(id) {
  var popup = document.getElementById(id);
  popup.style.display = "block";
}

// FunciÃ³n para cerrar el pop-up
function cerrarPopup(id) {
  var popup = document.getElementById(id);
  popup.style.display = "none";
}

// Obtener todos los elementos con la clase 'btnMidlle' y asignar eventos de clic a ellos
var botones = document.querySelectorAll('.btnMidlle');

botones.forEach(function(boton) {
  boton.addEventListener('click', function() {
      var id = 'popup-' + boton.id;
      mostrarPopup(id);
  });
});


// ObtÃ©n todos los botones con la clase 'cerrar-popup'
const botonesPopUp = document.querySelectorAll('.cerrar-popup');

// Itera sobre los botones y asigna eventos
botonesPopUp.forEach(boton => {
  boton.addEventListener('click', function() {
    // Obtiene el nÃºmero del botÃ³n desde el atributo 'data-boton'
    const numeroBoton = this.getAttribute('data-boton');
    cerrarPopup(`popup-boton${numeroBoton}`);
  });
});

function EvaluarIngresoDeSesion() {
  if (!sessionStorage.getItem("idUsuario")) {
    window.location.href = "../index.html";
  }
}

function asignarFoto() {
if (sessionStorage.getItem("fotoPerfil")) {
  document.getElementById("userimg").src =
    sessionStorage.getItem("fotoPerfil");
} else {
  document.getElementById("userimg").src =
    "https://us.123rf.com/450wm/thesomeday123/thesomeday1231709/thesomeday123170900021/85622928-icono-de-perfil-de-avatar-predeterminado-marcador-de-posición-de-foto-gris-vectores-de.jpg";
}
}

main();

function main()
{
   asignarFoto();
   EvaluarIngresoDeSesion();
}


