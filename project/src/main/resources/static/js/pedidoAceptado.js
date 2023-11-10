
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


async function getInfoPedido() {

  //"http://localhost:8080/pedidos/"+ sessionStorage.getItem("IDpedido")
  return await fetch("http://localhost:8080/pedidos/"+ sessionStorage.getItem("IDpedido"), {
    headers: {
      "Content-Type": "application/json",
    },
  });
}

const promesaInformacionPedido = getInfoPedido()

promesaInformacionPedido
.then((res) => {
  console.log(res.ok +" info pedido correcta");
  console.log(sessionStorage.getItem("IDpedido"))
  return res.json();
})
.then((data) => {
  console.log(data);
  console.log(sessionStorage.getItem("IDpedido"))
  generateInfoPedido(data);
})
.catch(() => {
  console.log(sessionStorage.getItem("IDpedido"))
  console.log("error");
});

async function getInfoDomiciliario() {

  //"http://localhost:8080/pedidos/"+ sessionStorage.getItem("IDpedido")+"/domiciliario"
  return await fetch("http://localhost:8080/pedidos/"+ sessionStorage.getItem("IDpedido")+"/domiciliario", {
    headers: {
      "Content-Type": "application/json",
    },
  });
}

function generateInfoPedido(data)
{
  if(data.tipopedido=="Domicilio")
  {
    const promesaInformacionDomiciliario=getInfoDomiciliario();

    promesaInformacionDomiciliario
    .then((res) => {
      console.log(res.ok);
      return res.json();
    })
    .then((data) => {
      console.log(data);
      $("#NombreDomiciliario").text("El domiciliario se llama: "+data.nombre);
      $("#NumeroTel").text("El domiciliario se llama: "+data.telefono);
    })
    .catch(() => {
      console.log("error");
    });

  }
  //si escogio recoger en el establecimiento
  else
  {
    $("#NombreDomiciliario").text("Trámite finalizado");
    $("#NumeroTel").text("En 30 minutos vaya al establecimiento");
  }
}

async function main()
{
  EvaluarIngresoDeSesion();
}

window.addEventListener('DOMContentLoaded', (event) => {

  // Inicializa un contador para rastrear el tiempo
  let segundosTranscurridos = 0;

  // Función para cambiar el texto cada 3 segundos
  function cambiarTexto() {
    const textos = ['Buscando domiciliario', 'Enviando información a la tienda', 'Finalizando terminos del domicilio']; // Agrega los textos que desees

    document.getElementById('cargaText').textContent = textos[segundosTranscurridos];
    segundosTranscurridos += 1;

    // Verifica si han pasado 9 segundos y muestra/oculta elementos
    if (segundosTranscurridos >= 4) {
      document.getElementById('Carga').style.display = 'none';
      document.getElementById('Resultado').style.display = 'block';
    }
  }

  // Llama a la función para cambiar el texto cada 3 segundos
  setInterval(cambiarTexto, 3000);
});

// Agrega un controlador de eventos para el evento "popstate"
window.addEventListener('popstate', function(event) {
  // Verifica si la URL actual coincide con la URL de destino
  if (window.location.href === '../html/MenuUsuario.html') {
    // Redirige a la pantalla de destino
    window.location.href = '../html/MenuUsuario.html';
  }
});

// Crea un nuevo estado en el historial del navegador con la URL actual
var currentState = window.location.href;
window.history.pushState({ url: currentState }, '', currentState);


