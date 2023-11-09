  
function mostrarTotal() {
// Obtener el pedido actual del sessionStorage
var pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));

// Obtener el elemento del DOM donde deseas mostrar el total
var totalDiv = document.querySelector(".costos > div:last-child");

// Verificar si el pedidoActual existe y tiene productos
if (pedidoActual && pedidoActual.productos.length > 0) {
    // Obtener el total del pedido y mostrarlo en el div
    totalDiv.textContent = "$" + pedidoActual.total.toFixed(2);
} else {
    // Si no hay productos en el pedido, mostrar un mensaje o un total de 0
    totalDiv.textContent = "$0.00";
}


// Obtén la variable con el valor de 2000
var valorVariable = 2000 + pedidoActual.total;

// Obtén el elemento que contiene "$" y donde deseas insertar la variable
var totalDetodoElement = document.querySelector('.totaldetodo');

// Actualiza el contenido de totaldetodo con el símbolo de dólar y la variable
totalDetodoElement.innerHTML = '$' + valorVariable.toFixed(2);

// Obtén el elemento que contiene "$" y donde deseas insertar la variable
var totalDetodoElement = document.querySelector('.totaldetodo');

// Actualiza el contenido de totaldetodo con el símbolo de dólar y la variable
totalDetodoElement.innerHTML = `$${valorVariable}`;

}



async function getUbicaciones(){
  return await fetch('http://localhost:8080/ubicaciones', {
    headers: {
          'Content-Type': 'application/json'
      }
  })
}

function generarDesplegable(data)
{
  var selectElement = document.getElementById("Nombre-tiendas");
  for(let i=0; i<data.length; i++)
  {
    var option = document.createElement("option");
      option.value = data[i].id;
      option.text = data[i].nombre;
      selectElement.appendChild(option);
  }
}

var promesaUbicaciones = getUbicaciones()

promesaUbicaciones
  .then((res) => {
    return res.json(); 
  })
  .then((data) => {
    console.log(data)

    generarDesplegable(data)
  })
  .catch(() => {
    console.log("error");
  });



  async function mandado(tiendaid,tipopedido,valortotal,aclaraciones,propina,tipopago,tipotarjeta,productos,ubicacionid){
    return await fetch('http://localhost:8080/pedidos/crear', {
      method:"POST",
      headers: {
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({
          compradorid : "6dfda066-af0c-495a-b243-6ff13e936424", 
          tiendaid:"04411fa5-8adc-4341-a93f-51db9724eb76",
          tipopedido: "Domicilio",
          valortotal: 7000,
          aclaraciones: null,
          propina: 0,
          tipopago: "Efectivo",
          tipotarjeta: null,
          productos: [
              {
                      "id" : "2796978b-293a-4065-9af5-26ea1cddf676",
                      "cantidad": 3
                  },
                  {
                      "id" : "3c1f0f42-0514-45c2-995f-277916eefbd4",
                      "cantidad": 4
                  }
          ],
          ubicacionid: "0b1436d4-6da0-4452-b957-a44af9edf8c1"
      }),
    })
  }


  var miBoton = document.getElementById("hacepedido");

// Agrega un controlador de eventos para el clic
  miBoton.addEventListener("click", function(event) {

   var pedido = sessionStorage.getItem("pedido")

   var tiendaid = pedido.idTienda;
   var tipopedido = $('input[name="ModDomi"]:checked').val();
   var valortotal = pedido.total;
   var aclaraciones = $("#inp2").val();
   var tipopago = null;
   var tipotarjeta = null;
   var productos = pedido.productos;
   
   // Obtén el elemento <select> por su ID
    var selectElement = document.getElementById("Nombre-tiendas");

    // Obtén el valor del elemento <option> seleccionado
    var selectedValue = selectElement.value;

    // Luego puedes utilizar la variable selectedValue para acceder al valor seleccionado
    console.log("El valor seleccionado es: " + selectedValue);

    var ubicacionid = selectedValue;

    //MIRAR QUE TODO ESTE CORRECTO ANTES DE HACER LA EXCEPCION

    var informa =  mandado(tiendaid,tipopedido,valortotal,aclaraciones,2000,tipopago,tipotarjeta,productos,ubicacionid)

    informa
      .then((res) => {
        return res.json(); 
      })
      .then((data) => {
        console.log(data)
        console.log("hola")
        sessionStorage.setItem("IDpedido", data)
        window.location.href = "../html/pedidoAceptado.html"; 

      })
      .catch(() => {
        console.log("error");
      });

  });

main();


function main()
{
// Luego, llama a la función mostrarTotal() para actualizar el div del total
mostrarTotal();
}




//Css mejor, Carrito object object, terminar excepciones de tipo de pago y demas, y que funcione hacer pedido, y tiendas meter
//productos