class ProductosPedidos {
  constructor(idProducto, cantidad, precio, link, nombre) {
    this.idProducto = idProducto;
    this.cantidad = cantidad;
    this.precio = precio;
    this.link=link;
    this.nombre=nombre;
  }
}
let pedido;

export function InicializarPedido() {
  // Intenta obtener el pedido del sessionStorage
  const pedidoGuardado = sessionStorage.getItem("pedido");

  // Si no hay un pedido guardado...
  if (!pedidoGuardado) {
    pedido = {
      total: 0,
      id: null,
      idTienda: null,
      productos: [],
    };
    // Convierte el objeto 'pedido' a una cadena JSON y almacénalo en el sessionStorage
    sessionStorage.setItem("pedido", JSON.stringify(pedido));
  }
}

let pedidoOpcional = JSON.parse(sessionStorage.getItem("pedido"));

if (pedidoOpcional && pedidoOpcional.productos && pedidoOpcional.productos.length > 0) {
  InicializarPedido();
  var pedidoActual = pedidoOpcional || pedido;
  anadirProductosCarrito(pedidoActual);
}




export function  AnadirProductoAPedido(cantidad)
{

    // Obtener el pedido actual del sessionStorage
    var pedidoActual = JSON.parse(sessionStorage.getItem("pedido")) || pedido;

    /* constructor(idProducto, idTienda, cantidad, precio)*/
    var idproducto = sessionStorage.getItem("idproducto");
    var idtienda = $('input[name="TiendaSeleccion"]:checked').val();
    var precio = parseInt($("#precioText").text(), 10);
    var link=document.getElementById('fotopRODUCTO').getAttribute('src');
    var nombre=$("#tituloProducto").text()

    // Crear un nuevo producto
    let nuevoProducto = new ProductosPedidos(
      idproducto,
      parseInt(cantidad),
      precio,
      link,
      nombre
    );

    // Añadir el nuevo producto a la lista de productos
    pedidoActual.productos.push(nuevoProducto);

    // Actualizar el total (por ejemplo, sumando la cantidad * precio del nuevo producto)
    pedidoActual.total += nuevoProducto.cantidad * nuevoProducto.precio;
    
    //OJO ESTO SE CAMBIA
    pedidoActual.idTienda=idtienda;

    // Guardar el pedido actualizado de vuelta en el sessionStorage
    sessionStorage.setItem("pedido", JSON.stringify(pedidoActual));

    anadirProductosCarrito(pedidoActual);
}














//////////////////////////////////////////////////REVISAR PROPUESTA////////////////////////////////////////////
export function anadirProductosCarrito(productos)
{

//se vacia el contenedor y se vuelven a anadir los productos
vaciarContenedor("contenedorproductosCarrito");

var container = $("#contenedorproductosCarrito");

document.getElementById('contenedorproductosCarrito').innerHTML = JSON.parse(sessionStorage.getItem("pedido"));
console.log(JSON.parse(sessionStorage.getItem("pedido")))

let guarda = JSON.parse(sessionStorage.getItem("pedido"));
console.log(guarda.productos)

let muestra = "";

document.getElementById('count').innerHTML = guarda.productos.length;

//se vuelve a llenar
for(let i=0; i<guarda.productos.length; i++)
{
  muestra = `
      <div class="producto" id="${guarda.productos[i].idProducto}">
          <div class="circuloproducto">
                  <img class="fotominipRODUCTO" src="${guarda.productos[i].link}" alt="foto Producto">
          </div>
          <p class="nombreminiProducto">
              ${guarda.productos[i].nombre}
          </p>
          <p class="preciominiProducto">
              ${guarda.productos[i].precio}
          </p>
          <p class="cantminiProducto">
              ${guarda.productos[i].cantidad}
          </p>
          <div id="miniCantidades">
            <button class="minibasura" id="basura-${guarda.productos[i].idProducto}">
              <img class="sumatoria" src="../imagenes/trash.png" alt="basura">
            </button>
          </div>
      </div>
  `;
  
  container.append(muestra);
  
  //container.append(productBlock);
}

muestra = `

<a id="comienza_compra" class="boto" href="../pago.html/">
  <span class="botonfunci">Ir a pagar</span>
</a>

`;

container.append(muestra);

document.getElementById("caneca").style.display = "block";

document.getElementById("carrito_contiene").style.display = "none";
document.getElementById("contenedorproductosCarrito").style.display = "flex";


// Asignar el evento click al contenedor padre que existe en el DOM
container.on("click", ".minibasura", function (event) {
  event.preventDefault(); // Prevenir la acción predeterminada del enlace

  // Aquí puedes usar el ID del producto que está en el atributo 'id' del botón
  const productId = event.currentTarget.id.replace("basura-", "");
  console.log(productId);

  // Seleccionar el div del producto usando el ID y eliminarlo del contenedor
  container.find(`.producto[id="${productId}"]`).remove();

  //eliminar producto de la memoria
  // Obtener los productos actuales del sessionStorage
  const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));


  // Buscar el producto que se va a eliminar por su ID
  const productoAEliminar = pedidoActual.productos.find(producto => producto.idProducto === productId);

  if (productoAEliminar) {
    // Restar el precio del producto por la cantidad que se había pedido del total
    pedidoActual.total -= (productoAEliminar.precio * productoAEliminar.cantidad);

    // Filtrar la lista de productos para excluir el producto con el ID específico
    pedidoActual.productos = pedidoActual.productos.filter(producto => producto.idProducto !== productId);

    // Actualizar el sessionStorage con la nueva lista de productos y el total modificado
    sessionStorage.setItem("pedido", JSON.stringify(pedidoActual));
  }

});


}





function vaciarContenedor() {
const container = $("#contenedorproductosCarrito"); // Cambia 'ID_DEL_CONTENEDOR' por el ID real del contenedor
container.empty();

}


////////////////////////////////////////////AQUI TERMINA////////////////////////////////////








// Define la función eliminarTodo
export function eliminarTodo() {

var contenedor = $("#contenedorproductosCarrito");

  // Supongamos que quieres eliminar un elemento del sessionStorage con la clave "miElemento"
contenedor.empty();

var pedidoVacio= {
  total: 0,
  id:null,
  idTienda:null,
  productos: [],
};

document.getElementById('count').innerHTML = 0;
// Supongamos que quieres eliminar un elemento del sessionStorage con la clave "miElemento"
sessionStorage.setItem("pedido", JSON.stringify(pedidoVacio));


}

















export function getElem(id) {
  return document.getElementById(id);
}

export function show(id) {
  getElem(id).style.display = "block";
  document.body.classList.add("overlayActive");
}

export function hide(id) {
  getElem(id).style.display = "none";
  document.body.classList.remove("overlayActive");
}

export function funcionalidadBotonesProductoCarrito()
{
  document.addEventListener("DOMContentLoaded", function () {

    
    
      // Obtiene una referencia al elemento con el ID "caneca"
      var caneca = document.getElementById("caneca");
    
      // Agrega un event listener de clic al elemento "caneca"
      caneca.addEventListener("click", function () {  

        document.getElementById("caneca").style.display = "none";
        document.getElementById("carrito_contiene").style.display = "flex";
        document.getElementById("contenedorproductosCarrito").style.display = "none";

        eliminarTodo();
      });
    



      // Asegurarte de que el DOM esté cargado
      const BAgregarEirPagar = document.getElementById("BAgregarEirPagar"); // Selecciona el botón por su ID
      const BAgregarSeguirComprando = document.getElementById("BAgregarSeguirComprando"); // Selecciona el botón por su ID
      
      
      const cantidadInput = document.getElementById("cantidadProducto"); // Selecciona el input por su ID
      //si selecciona este
      //se debe anadir la info a la memoria e ir a la pantalla de realizar compra
      BAgregarEirPagar.addEventListener("click", function (event) {
        var cantidad = cantidadInput.value;
        evaluarItemsAnadir(cantidad, "BAgregarEirPagar",event);
      });
    
      //si selecciona este
      //se anade a la memoria de pedido
      //se cierra el pop up
      BAgregarSeguirComprando.addEventListener("click", function (event) {
        var cantidad = cantidadInput.value;
    
        evaluarItemsAnadir(cantidad, "BAgregarSeguirComprando",event);
    
      });
    
    });
}

function  evaluarItemsAnadir(cantidad, tipo, event)
{
if (isNaN(cantidad) || cantidad.trim() === "" || cantidad==0) {
  alert("Por favor, introduce un número válido en la cantidad.");
  event.preventDefault(); // Evita que se ejecute cualquier otro comportamiento del botón.
} 
else if($('input[name="TiendaSeleccion"]:checked').length==0)
{
  //console.log("Número de botones de radio seleccionados:", $('input[name="TiendaSeleccion"]:checked').length);
  alert("Por favor, selecciona una tienda");
  event.preventDefault(); // Evita que se ejecute cualquier otro comportamiento del botón. 
}
else if(evalCantidad(cantidad))
{
  //se hace el proceso de guardar el info del pedido
  //se anade el producto con su informacion de id cantidad y precio
  //se suma guarda el valor total

  const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));

  // Supongamos que quieres cambiar la cantidad del producto con un ID específico
  const idProductoEspecifico = sessionStorage.getItem("idproducto");

  // Busca el producto en la lista de productos
  const producto = pedidoActual.productos.find(p => p.idProducto === idProductoEspecifico);

  console.log( producto);

  if(producto)
  {
     pedidoActual.total-=producto.precio*parseInt(producto.cantidad);
     producto.cantidad = parseInt(cantidad);
     pedidoActual.total+=producto.precio*parseInt(producto.cantidad);
     sessionStorage.setItem("pedido", JSON.stringify(pedidoActual));
  }
  else
  {
    AnadirProductoAPedido(cantidad);
  }


  var container = $("#contenedorproductosCarrito");

document.getElementById('contenedorproductosCarrito').innerHTML = JSON.parse(sessionStorage.getItem("pedido"));
console.log(JSON.parse(sessionStorage.getItem("pedido")))

let guarda = JSON.parse(sessionStorage.getItem("pedido"));
console.log(guarda.productos)

let muestra = "";

document.getElementById('count').innerHTML = guarda.productos.length;

//se vuelve a llenar
for(let i=0; i<guarda.productos.length; i++)
{
  muestra = `
      <div class="producto" id="${guarda.productos[i].idProducto}">
          <div class="circuloproducto">
                  <img class="fotominipRODUCTO" src="${guarda.productos[i].link}" alt="foto Producto">
          </div>
          <p class="nombreminiProducto">
              ${guarda.productos[i].nombre}
          </p>
          <p class="preciominiProducto">
              ${guarda.productos[i].precio}
          </p>
          <p class="cantminiProducto">
              ${guarda.productos[i].cantidad}
          </p>
          <div id="miniCantidades">
            <button class="minibasura" id="basura-${guarda.productos[i].idProducto}">
              <img class="sumatoria" id="minibasura" src="../imagenes/trash.png" alt="basura">
            </button>
          </div>
      </div>
  `;

  container.append(muestra);
  
  //container.append(productBlock);
}

muestra = `

<a id="comienza_compra" class="boto" href="../pago.html/">
  <span class="botonfunci">Ir a pagar</span>
</a>

`;
container.append(muestra);

  if(tipo=="BAgregarSeguirComprando")
  {
    hide("popup");
  }
  else
  {
    window.location.href = "../html/pago.html";
  }
}
else
{
  alert("La tienda no tiene la cantidad de productos que pide");
  event.preventDefault(); // Evita que se ejecute cualquier otro comportamiento del botón. 
}
}


function evalCantidad(cantUsuario)
{
var nameValue = $('input[name="TiendaSeleccion"]:checked').attr('id');
// Separar el valor obtenido usando el caracter '-' y obtener la cantidad
var cantidadTienda = nameValue.split('-')[1];

if(cantUsuario<=parseInt(cantidadTienda))
{
  return true;
}

return false;
}


async function getInfoProducto(idproducto) {
  return await fetch("http://localhost:8080/productos/" + idproducto+"/tiendas", {
    headers: {
      "Content-Type": "application/json",
    },
  });
}

export function generateProductList(data) {
      var container = $(".scrollBoxProducto");
    
      for (var i = 0; i < 8; i++) {
        var productBlock = `
          <a href="#" class="productLink" id="${data[i].id}">
            <div class="producto">
                <div class="circuloproducto">
                        <img class="fotominipRODUCTO" src="${data[i].foto.foto}" alt="foto Producto">
                </div>
                <p class="nombreminiProducto">
                    ${data[i].nombreproducto}
                </p>
            </div>
            </a>
        `;
    
        container.append(productBlock);
      }
      // Es importante hacer el evento click DESPUÉS de agregar los productos al contenedor
      container.find(".productLink").on("click", function (event) {
          
            event.preventDefault(); // Prevenir la acción predeterminada del enlace
            console.log(event.currentTarget.id);
        
            //SE MANDA EL ID DEL PRODUCTO PARA QUE ME MANDEN LA INFO DE ESE PRODUCTO
            const promesaInformacionProducto = getInfoProducto(event.currentTarget.id);
        
            //guardar el id del producto para usarlo despues//
            sessionStorage.setItem("idproducto", event.currentTarget.id);
        
            promesaInformacionProducto
              .then((res) => {
                console.log(res.ok);
                return res.json();
              })
              .then((data) => {
                console.log(data);
                generateInfoProducto(data);
                show("popup");
              })
              .catch(() => {
                console.log("errorrrr");
              });
    
        });
      
    }


export function getCantidadPorProductoId(pedido, idProductoBuscado) {
      // Busca el producto en el pedido
      const producto = pedido.productos.find(producto => producto.idProducto === idProductoBuscado);
      
      // Si se encontró el producto, retorna su cantidad
      if (producto) {
        return producto.cantidad;
      } 
      
      // Si no se encontró el producto, retorna null o cualquier valor indicativo
      return null;
    }
    
    function AgregarRadioButtons(tiendasList,pedidoActual)
    {

      var container = $("#RadioOptions");
      container.empty();

    
      for (var i = 0; i < tiendasList.length; i++) {
        //mirar como saber cual tienda es
        //en este caso se mandan las tiendas donde esta disponible el producto
        //en radio se pone en value el valor que se enviara al servidor (que es el id de la tienda)
    
        //si todavia no ha pedido otros productos y por ende no ha seleccionado la tienda
        if(pedidoActual.idTienda==null && tiendasList[i].cantidad>0)
        {
          var tiendaBlock = `
          <div id="RadioOptions1">
              <input type="radio" class="tienda" id="tienda-${tiendasList[i].cantidad}" name="TiendaSeleccion" value="${tiendasList[i].id}">
              <label id="labelt" for="tienda${tiendasList[i].id}">${tiendasList[i].nombreTienda}</label>
          </div>
          `;
        }
        else
        {
          //si ya selecciono un producto de una tienda, solo puede pedir productos de dicha tienda
          if(tiendasList[i].id==pedidoActual.idTienda && tiendasList[i].cantidad>0)
          {
            var tiendaBlock = `
            <div id="RadioOptions1">
                  <input type="radio" class="tienda" id="tienda-${tiendasList[i].cantidad}" name="TiendaSeleccion" value="${tiendasList[i].id}">
                  <label id="labelt" for="tienda${tiendasList[i].id}">${tiendasList[i].nombreTienda}</label>
            </div>
            `;
          }
          else if(tiendasList[i].cantidad>0)
          {
            var tiendaBlock = `
            <div id="RadioOptions1">
                  <input type="radio" class="tienda" id="tienda-${tiendasList[i].cantidad}" name="TiendaSeleccion" value="${tiendasList[i].id}" disabled>
                  <label id="labelt" for="tienda${tiendasList[i].id}">${tiendasList[i].nombreTienda}</label>
            </div>
            `;
          }
        }
        container.append(tiendaBlock);
      }
    }
    
export function AgregarCantidadYTotal(pedidoActual,precioTot)
    {
        //se evalua si el producto ya habia sido agregado en el carrito
      var idProductoBuscado=sessionStorage.getItem("idproducto");
      var cantidad = getCantidadPorProductoId(pedidoActual, idProductoBuscado);
    
      var totalPropuesto=0;
      //si ya fue agregado se debe asignar la cantidad que anteriormente fue asignada
    
      if(cantidad!=null)
      {
        $("#cantidadProducto").val(cantidad);
        //si ya lo pidio no toca sumar el valor al totoal porque ya habia sido sumado antes
        totalPropuesto = pedidoActual.total;
    
        //comentar arriba que el producto ya fue anadido a la 
        //se muestra el boton el total propuesto si compre una und del producto mas lo que ya ha
      //anadido antes al carrito
          $("#BAgregarSeguirComprando").text(
            "Editar y seguir comprando"
          );
          $("#BAgregarEirPagar").text(
            "Editar e ir a pagar " + totalPropuesto.toFixed(2)
          ); // toFixed(2) asegura que se muestren solo dos decimales
    
         
      }
      else
      {//si no se ha obtenido ningun resultado cantidad es nulo y por eso se inicializa en 1
        $("#cantidadProducto").val(1);
        cantidad =1;
        totalPropuesto = pedidoActual.total + cantidad * precioTot;
          $("#BAgregarEirPagar").text(
            "Agregar e ir a pagar " + totalPropuesto.toFixed(2)
          ); // toFixed(2) asegura que se muestren solo dos decimales
          $("#BAgregarSeguirComprando").text(
            "Agregar y seguir comprando"
          );
      }
    
    
    }
    
    //genera la informacion del producto en el pop up conforme a la info mandada
export function generateInfoProducto(data) {
    
      const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));
    
      console.log(pedidoActual);
    
      //titulo grande del producto
      $("#tituloProducto").text(data.nombreproducto);
    
      //foto del producto
      $("#fotopRODUCTO").attr("src", data.foto);
    
      //calcula el precio actual con base en si tiene promocion o no
      const precioTot = data.preciodinero - data.preciodinero * data.promocion/100;
      $("#precioText").text(precioTot.toFixed(2));
    
      AgregarCantidadYTotal(pedidoActual,precioTot);
    
      //si no tiene descuento no se muestra la info de promocion
      if (data.promocion == 0) {
        $("#DescText").hide();
        $("#porcentajePopUp").hide();
        $("#DescText2").hide();
      } else {
        //si si hay
        //se muestra la promocion
        $("#DescText2").text(data.promocion + "%");
        //se muestra el precio anterior tachado
        $("#DescText").text(data.preciodinero);
        $("#DescText").css("text-decoration", "line-through");
        $("#DescText").show();
        $("#porcentajePopUp").show();
        $("#DescText2").show();
      }

      var totalCantidad = data.tiendas.reduce((sum, tiend) => sum + tiend.cantidad, 0);
      if (totalCantidad == 0 || data.tiendas.length==0) {
        $("#disponible").text("Agotado");
        $("#disponible").css("background-color", "#f17e7e"); // Cambia el color a rojo
      }
    
      //descripcion del producto
      $("#textoDescrip").text(data.descripcion);
      
      //ingredientes del producto
      const ingredientesList = data.ingredientes;
    
     
      //como es en una lista se concatenen con comas
     // Extraer solo el atributo nombreIngrediente de cada objeto y luego hacer join
     var ingredientesText = ingredientesList.map(ing => ing.nombreIngrediente).join(", ");
    
      $("#textoIngred").text(ingredientesText);
    
      //una lista de tiendas
      var listaTiendas=data.tiendas;
    
      AgregarRadioButtons(listaTiendas,pedidoActual);
    }

export function generateTiendaList(data) {
  var container = $(".scrollBoxTienda");
  for (var i = 0; i < data.length; i++) {
    var tiendaBlock = `
    <a href="#" class="tiendaLink" id="${data[i].id}">
      <div class="minitienda">
          <div class="circuloTIENDA">
            
                <img class="fotominiTIENDA" src=${data[i].foto.foto} alt="foto Tienda">
      
          </div>
          
          <p class="nombreminiTIENDA">
            ${data[i].nombretienda}
          </p>
      </div>
      </a>
      `;

    container.append(tiendaBlock);
  }

  container.find(".tiendaLink").on("click", function (event) {
      event.preventDefault(); // Prevenir la acción predeterminada del enlace
      sessionStorage.setItem("idTienda", event.currentTarget.id); // Guardar el ID de la tienda en sessionStorage
      //ESTO SE DEBE EDITAR
      window.location.href = "../html/plantilla.html"; // Redirigir a la nueva página
    });
}

export function inicializarEventosProducto() {
 
$("#basura").on("click", function () {
  $("#cantidadProducto").val("1");
  //se obtiene la cantidad del producto
  //se obtiene el precio actual del prodcuto
  var totalPropuesto=0;
  var precioTot = parseInt($("#precioText").text(), 10);

  var pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));

  // Supongamos que quieres cambiar la cantidad del producto con un ID específico
  var idProductoEspecifico = sessionStorage.getItem("idproducto");

  // Busca el producto en la lista de productos
 var producto = pedidoActual.productos.find(p => p.idProducto === idProductoEspecifico);

  if(producto)
  {
    pedidoActual.total-=producto.precio*producto.cantidad;
  }

    totalPropuesto = pedidoActual.total + 1 * precioTot;


  $("#BAgregarEirPagar").text(
    "Agregar e ir a pagar " + totalPropuesto.toFixed(0)
  );
});

$("#mas").on("click", function () {
  //se actualiza la cantidad
  var currentValue = parseInt($("#cantidadProducto").val(), 10) || 0; // Convertir el valor a entero
  $("#cantidadProducto").val(currentValue + 1);

  var pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));

  // Supongamos que quieres cambiar la cantidad del producto con un ID específico
  var idProductoEspecifico = sessionStorage.getItem("idproducto");

  // Busca el producto en la lista de productos
 var producto = pedidoActual.productos.find(p => p.idProducto === idProductoEspecifico);

  if(producto)
  {
    pedidoActual.total-=producto.precio*producto.cantidad;
  }
  //se obtiene la cantidad del producto
  //se obtiene el precio actual del prodcuto
  var precioTot = parseInt($("#precioText").text(), 10);
  const totalPropuesto = pedidoActual.total + (currentValue + 1) * precioTot;
  $("#BAgregarEirPagar").text(
    "Agregar e ir a pagar " + totalPropuesto.toFixed(0)
  );
});

$("#cantidadProducto").on("blur", function () {
  //Detectar cambios en el input
  var valor = $(this).val().trim(); // Eliminar espacios en blanco

  // Comprobar si el valor es un número y no está vacío
  if ($.isNumeric(valor) && valor !== "" && valor!="0") {
    const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));
    //se obtiene la cantidad del producto
    //se obtiene el precio actual del prodcuto
    var precioTot = parseInt($("#precioText").text(), 10);
    const totalPropuesto = pedidoActual.total + valor * precioTot;
    $("#BAgregarEirPagar").text(
      "Agregar e ir a pagar " + totalPropuesto.toFixed(0)
    );
  } else {
    $(this).val("1");
    alert("Por favor, introduce un número válido.");
    const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));
    var precioTot = parseInt($("#precioText").text(), 10);
    const totalPropuesto = pedidoActual.total + 1 * precioTot;
    $("#BAgregarEirPagar").text(
      "Agregar e ir a pagar " + totalPropuesto.toFixed(0)
    );
  }
});

}

export function generateProductListBuscador(data) {
var container = $(".scrollBoxProducto");

for (var i = 0; i < data.length; i++) {
  var productBlock = `
    <a href="#" class="productLink" id="${data[i].id}">
      <div class="producto">
          <div class="circuloproducto">
                  <img class="fotominipRODUCTO" src="${data[i].foto.foto}" alt="foto Producto">
          </div>
          <p class="nombreminiProducto">
              ${data[i].nombreproducto}
          </p>
      </div>
      </a>
  `;
  
  container.append(productBlock);
}
// Es importante hacer el evento click DESPUÉS de agregar los productos al contenedor
container.find(".productLink").on("click", function (event) {
    
      event.preventDefault(); // Prevenir la acción predeterminada del enlace
      console.log(event.currentTarget.id);
      show("popup");//ESTO SE QUITA
  
      //SE MANDA EL ID DEL PRODUCTO PARA QUE ME MANDEN LA INFO DE ESE PRODUCTO
      const promesaInformacionProducto = getInfoProducto(event.currentTarget.id);
  
      //guardar el id del producto para usarlo despues//
      sessionStorage.setItem("idproducto", event.currentTarget.id);
  
      promesaInformacionProducto
        .then((res) => {
          console.log(res.ok);
          return res.json();
        })
        .then((data) => {

          
            console.log(data);
            show("popup");
            generateInfoProducto(data);
  
        })
        .catch(() => {
          console.log("errorrrr");
        });

  });

}