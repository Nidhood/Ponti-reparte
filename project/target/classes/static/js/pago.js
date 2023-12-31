export function EvaluarIngresoDeSesion() {
  if (!sessionStorage.getItem("idUsuario")) {
    window.location.href = "../index.html";
  }
} 

function mostrarTotal() {

  //sessionStorage.setItem("idUsuario","6713830e-9af4-4d4d-a190-e7a0899eb499");
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
    //alert(tiendaid+tipopedido+valortotal+aclaraciones+propina+tipopago+tipotarjeta+productos+ubicacionid)

    //NaN $6600 null 2000 Efectivo null undefined 18a193cd-6f4b-430b-bc32-11dcd313baf5
    
    return await fetch('http://localhost:8080/pedidos/crear', {
      method:"POST",
      headers: {
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({
          compradorid : sessionStorage.getItem("idUsuario"),
          tiendaid: tiendaid,
          tipopedido: tipopedido,
          valortotal: valortotal,
          aclaraciones: aclaraciones,
          propina: propina,
          tipopago: tipopago,
          tipotarjeta: tipotarjeta,
          productos: productos,
          ubicacionid: ubicacionid
      }),
    })
  }

  class producto {
    constructor(id, cantidad) {
      this.id = id;
      this.cantidad = cantidad;
    }
  }

  class ProductosPedidos {
    constructor(idProducto, cantidad, precio, link, nombre) {
      this.idProducto = idProducto;
      this.cantidad = cantidad;
      this.precio = precio;
      this.link=link;
      this.nombre=nombre;
    }
  }

  var miBoton = document.getElementById("hacepedido");

// Agrega un controlador de eventos para el clic
  miBoton.addEventListener("click", function(event) {

   var pedido = JSON.parse(sessionStorage.getItem("pedido"))
   //alert()

   var tiendaid = JSON.parse(sessionStorage.getItem("pedido")).idTienda; //no esta cogiendo el get item pedido
   var tipopedido = null; //creo que no se esta mostrando
   var valortotal = document.getElementById("megatotal").innerHTML.replace(/^./, "");//ya cpge, falta quitar el primer caracter que es el simbolo peso
   var aclaraciones = $("#inp2").val();
   var tipopago = null;
   var tipotarjeta = null;
   var correcto=true;

   //VOLVER A HACER LA LISTA 
   var productos = pedido.productos

   console.log(productos)
  
   var productosTransformados = productos.map(Productos => new producto(Productos.idProducto, Productos.cantidad));

   console.log(productosTransformados)

   if(document.getElementById("mod1").checked){
    tipopago = document.getElementById("mod1").value
  }
  else if(document.getElementById("mod2").checked){
    tipopago = document.getElementById("mod2").value
  }
  else if(document.getElementById("mod3").checked){
    tipopago = document.getElementById("mod3").value
  }
  else
  {
    correcto=false;
  }


  if(document.getElementById("mod4").checked){
    tipopedido = document.getElementById("mod4").value
  }
  else if(document.getElementById("mod5").checked){
    tipopedido = document.getElementById("mod5").value
  }
  else
  {
    correcto=false;
  }
   
  // Obtén el elemento <select> por su ID
  var selectElement = document.getElementById("Nombre-tiendas");

  // Obtén el valor del elemento <option> seleccionado
  var selectedValue = selectElement.value;

  // Luego puedes utilizar la variable selectedValue para acceder al valor seleccionado
  console.log("El valor seleccionado es: " + selectedValue);

  if (selectElement.value === "" || aclaraciones=== "") {
    correcto=false;
  }

  var ubicacionid = selectedValue;

  if(correcto)
  {
        //MIRAR QUE TODO ESTE CORRECTO ANTES DE HACER LA EXCEPCION
    var informa =  mandado(tiendaid,tipopedido,valortotal,aclaraciones,2000,tipopago,tipotarjeta,productosTransformados,ubicacionid)

    informa
      .then((res) => {
        return res.json(); 
      })
      .then((data) => {
        console.log(data)
        console.log("hola")
        sessionStorage.setItem("IDpedido", data.id)
        window.location.href = "../html/pedidoAceptado.html"; 

      })
      .catch(() => {
        console.log("error");
      });
    }
    else
    {
      alert("Ingrese los campos correctos")
    }

    

  });

main();


function main()
{
  EvaluarIngresoDeSesion();
// Luego, llama a la función mostrarTotal() para actualizar el div del total
   mostrarTotal();
}




//Css mejor, Carrito object object, terminar excepciones de tipo de pago y demas, y que funcione hacer pedido, y tiendas meter
//productos