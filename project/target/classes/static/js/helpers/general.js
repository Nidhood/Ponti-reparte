export function EvaluarIngresoDeSesion() {
    if (!sessionStorage.getItem("idUsuario")) {
      window.location.href = "../index.html";
    }
  }

export function asignarFoto() {
  if (sessionStorage.getItem("fotoPerfil")) {
    document.getElementById("userimg").src =
      sessionStorage.getItem("fotoPerfil");
  } else {
    document.getElementById("userimg").src =
      "https://us.123rf.com/450wm/thesomeday123/thesomeday1231709/thesomeday123170900021/85622928-icono-de-perfil-de-avatar-predeterminado-marcador-de-posición-de-foto-gris-vectores-de.jpg";
  }
}

export function implementarBuscar()
{
  const input = document.getElementById("barrabusqueda");
  input.addEventListener("keydown", function (event) {
    if (event.key === "Enter" && input.value.trim() !== "") {
      // Redireccionar a la nueva pantalla
      sessionStorage.setItem("palabrasClave", input.value);
      window.location.href = "../html/Busqueda.html";
    }
  });
}