
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
  return await fetch("http://localhost:8080/pedidos/4177f0f6-83f8-41fe-ac73-796f6953eda5", {
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

  //show("popup");
})
.catch(() => {
  console.log("error");
});

async function getInfoDomiciliario() {

  //"http://localhost:8080/pedidos/"+ sessionStorage.getItem("IDpedido")+"/domiciliario"
  return await fetch("http://localhost:8080/pedidos/4177f0f6-83f8-41fe-ac73-796f6953eda5/domiciliario", {
    headers: {
      "Content-Type": "application/json",
    },
  });
}

function generateInfoPedido(data)
{
  if(data.tipopedido=="domicilio")
  {
    const promesaInformacionDomiciliario=getInfoDomiciliario();

    promesaInformacionDomiciliario
    .then((res) => {
      console.log(res.ok);
      res.json();
    })
    .then((data) => {
      console.log(data);
      $("#NombreDomiciliario").text("El domiciliario se llama: "+data.nombre);
      $("#NumeroTel").text("El domiciliario se llama: "+data.telefono);

      show("popup");
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
  //EvaluarIngresoDeSesion();
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



