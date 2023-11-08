
main();

function EvaluarIngresoDeSesion()
{
  if(!sessionStorage.getItem('idUsuario'))
  {
    window.location.href = "../index.html";
  }
}

//PARA HACER EL POP UP
// Definición de funciones y variables
var getElem = function (id) {
  return document.getElementById(id);
};
var show = function (id) {
  getElem(id).style.display = "block";
  document.body.classList.add("overlayActive");
};
var hide = function (id) {
  getElem(id).style.display = "none";
  document.body.classList.remove("overlayActive");
};


function asignarFoto() {
  if (sessionStorage.getItem("fotoPerfil")) {
    document.getElementById("userimg").src =
      sessionStorage.getItem("fotoPerfil");
  } else {
    document.getElementById("userimg").src =
      "https://us.123rf.com/450wm/thesomeday123/thesomeday1231709/thesomeday123170900021/85622928-icono-de-perfil-de-avatar-predeterminado-marcador-de-posición-de-foto-gris-vectores-de.jpg";
  }
}

async function getInfoPedido() {
  return await fetch("http://localhost:8080/pedido/"+ sessionStorage.getItem("IDpedido")+"/domiciliario", {
    headers: {
      "Content-Type": "application/json",
    },
  });
}
const promesaInformacionPedido = getInfoPedido()

promesaInformacionPedido
.then((res) => {
  console.log(res.ok);
  res.json();
})
.then((data) => {
  console.log(data);

  generateInfoPedido(data);

  show("popup");
})
.catch(() => {
  console.log("error");
});

function generateInfoPedido(data)
{
  if(data.tipo=="domicilio")
  {
    $("#NombreDomiciliario").text("El domiciliario se llama: "+data.nombre);
    $("#NumeroTel").text("El domiciliario se llama: "+data.telefono);
  }
  //si es cogio recoger en el establecimiento
  else
  {
    $("#NombreDomiciliario").text("Trámite finalizado");
    $("#NumeroTel").text("En 30 minutos vaya al establecimiento");
  }
}

async function main()
{
    EvaluarIngresoDeSesion();
  asignarFoto();
}

window.addEventListener('DOMContentLoaded', (event) => {

  setTimeout(() => {
      // Cambia el texto después de 4 segundos
      document.getElementById('cargaText').textContent = 'Terminando términos del domicilio';
  }, 3000);

  setTimeout(() => {
      // Oculta y muestra los elementos después de 9 segundos
      document.getElementById('Carga').style.display = 'none';
      document.getElementById('Resultado').style.display = 'block';
  }, 9000);

});

