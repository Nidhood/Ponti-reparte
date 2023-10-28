// En tu archivo principal
class ProductosPedidos {
  constructor(idProducto, cantidad, precio, link, nombre) {
    this.idProducto = idProducto;
    this.cantidad = cantidad;
    this.precio = precio;
    this.link=link;
    this.nombre=nombre;
  }
}

function InicializarPedido() {
  // Intenta obtener el pedido del sessionStorage
  const pedidoGuardado = sessionStorage.getItem("pedido");

  // Si no hay un pedido guardado...
  if (!pedidoGuardado) {
    const pedido = {
      total: 0,
      id:null,
      idTienda:null,
      productos: [],
    };
    // Convierte el objeto 'pedido' a una cadena JSON y almacénalo en el sessionStorage
    sessionStorage.setItem("pedido", JSON.stringify(pedido));
  }
}

//evalua si el usuario puede acceder a esta pagina directaente porque
//ya habia ingresado sesion previamente
function EvaluarIngresoDeSesion() {
  if (!sessionStorage.getItem("idUsuario")) {
    window.location.href = "../index.html";
  }
}

//ESTO SE CAMBIA
//por ahora es asi porque no tengo el servidor
//pero siempre hay sessionStorage.getItem('fotoPerfil')
function asignarFoto() {
  if (sessionStorage.getItem("fotoPerfil")) {
    document.getElementById("userimg").src =
      sessionStorage.getItem("fotoPerfil");
  } else {
    document.getElementById("userimg").src =
      "https://us.123rf.com/450wm/thesomeday123/thesomeday1231709/thesomeday123170900021/85622928-icono-de-perfil-de-avatar-predeterminado-marcador-de-posición-de-foto-gris-vectores-de.jpg";
  }
}

//LAS SIGUIENTES FUNCIONES SE INVOCAN FUERA DEL MAIN

//esto es para la barra de busqueda
//si hay elementos escritos y se pone enter
//se busca conforme a lo escrito
const input = document.getElementById("barrabusqueda");
input.addEventListener("keydown", function (event) {
  if (event.key === "Enter" && input.value.trim() !== "") {
    // Redireccionar a la nueva pantalla
    sessionStorage.setItem("palabrasClave", input.value);
    window.location.href = "../html/Busqueda.html";
  }
});

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

function  AnadirProductoAPedido(cantidad)
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

      console.log(pedidoActual.total+"VER")
      
      //OJO ESTO SE CAMBIA
      //pedidoActual.idTienda=idtienda;
      pedidoActual.idTienda=1;

      // Guardar el pedido actualizado de vuelta en el sessionStorage
      sessionStorage.setItem("pedido", JSON.stringify(pedidoActual));
}

//ANADIR AL CARRITO
//aqui se hace el proceso de anadir lo pedido en memoria dependiendo del boton
document.addEventListener("DOMContentLoaded", function () {
  // Asegurarte de que el DOM esté cargado
  const BAgregarEirPagar = document.getElementById("BAgregarEirPagar"); // Selecciona el botón por su ID
  const BAgregarSeguirComprando = document.getElementById("BAgregarSeguirComprando"); // Selecciona el botón por su ID
  
  
  const cantidadInput = document.getElementById("cantidadProducto"); // Selecciona el input por su ID
  //si selecciona este
  //se debe anadir la info a la memoria e ir a la pantalla de realizar compra
  BAgregarEirPagar.addEventListener("click", function (event) {
    var cantidad = cantidadInput.value;

    if (isNaN(cantidad) || cantidad.trim() === "") {
      alert("Por favor, introduce un número válido en la cantidad.");
      event.preventDefault(); // Evita que se ejecute cualquier otro comportamiento del botón.
    } else {
      //se hace el proceso de guardar el info del pedido
      //se anade el producto con su informacion de id cantidad y precio
      //se suma guarda el valor total
      const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));

      // Supongamos que quieres cambiar la cantidad del producto con un ID específico
      const idProductoEspecifico = sessionStorage.getItem("idproducto");

      // Busca el producto en la lista de productos
      const producto = pedidoActual.productos.find(p => p.idProducto === idProductoEspecifico);

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

      window.location.href = "../html/pago.html";
    }
  });

  //si selecciona este
  //se anade a la memoria de pedido
  //se cierra el pop up
  BAgregarSeguirComprando.addEventListener("click", function (event) {
    var cantidad = cantidadInput.value;

    if (isNaN(cantidad) || cantidad.trim() === "") {
      alert("Por favor, introduce un número válido en la cantidad.");
      event.preventDefault(); // Evita que se ejecute cualquier otro comportamiento del botón.
    } else {
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
      
      hide("popup");
    }

  });

  //-------------------------------------------FUNCION DEL CARRITO----------------------------//

// Asegurarte de que el DOM esté completamente cargado
  const carritoBoton = document.getElementById("Bcarrito"); // Selecciona el elemento por su ID

  carritoBoton.addEventListener("click", function(event) {
      event.preventDefault(); // Evita que el enlace haga su comportamiento predeterminado (en este caso, no navegará a ningún lugar porque el href es "#")
      //anadir informacion al carrito
      const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));
     // const divElement = document.getElementById('CarritoVacio');

      if(pedidoActual.productos.length>0)
      {
          //no muestra la foto de que el carrito esta vacio
          //divElement.style.display = 'none';
          //anadirProductosCarrito(pedidoActual.productos);

          //SUBTOTAL
          //$("subtotal").text(pedidoActual.total);

          //mostrar el carrito
          //show('popUpCarrito');
      }
      else
      {
          //poner la imagen porque el carrito esta vacio
          //divElement.style.display = 'block';  // o 'inline', 'inline-block', etc., dependiendo de lo que necesites
      }
       
  });

   /* const VaciarCarrito=document.getElementById("vaciarCarrito");

  VaciarCarrito.addEventListener("click", function(event) {
    event.preventDefault();
    //eliminar todo 
    vaciarContenedor("contenedorproductosCarrito");

    //poner la imagen 
    const divElement = document.getElementById('CarritoVacio');
    divElement.style.display = 'block';  // o 'inline', 'inline-block', etc., dependiendo de lo que necesites
  });

  const IrApagar=document.getElementById("iraPagar");

  IrApagar.addEventListener("click", function(event) {
    event.preventDefault();
    window.location.href = "../html/plantilla.html"; // Redirigir a la nueva página
  });*/


});


//////////////////////////////////////////////////REVISAR PROPUESTA////////////////////////////////////////////
function anadirProductosCarrito(productos)
{
  //se vacia el contenedor y se vuelven a anadir los productos
  vaciarContenedor("contenedorproductosCarrito");

  var container = $(".contenedorproductosCarrito");

  //se vuelve a llenar
  for(i=0; i<productos.length; i++)
  {
      var productBlock = `
        <div class="producto" id="${productos[i].idProducto}">
            <div class="circuloproducto">
                    <img class="fotominipRODUCTO" src="${productos[i].link}" alt="foto Producto">
            </div>
            <p class="nombreminiProducto">
                ${productos[i].nombre}
            </p>
            <p class="preciominiProducto">
                ${productos[i].precio}
            </p>
            <p class="cantminiProducto">
                ${productos[i].cantidad}
            </p>
            <div id="miniCantidades">
              <button class="minibasura" id="basura-${productos[i].idProducto}">
                <img class="sumatoria" id="minibasura" src="../imagenes/trash.png" alt="basura">
              </button>
              <a class="minieditar" id="editar-${productos[i].idProducto}">
                'Editar'
              </a>
            </div>
        </div>
    `;
    
    container.append(productBlock);
  }


  //el onclick de si pone la mini basura
  //debe eliminarse ese div del carrito
  //eliminarse de memoria de la canasta
  container.find(".minibasura").on("click", function (event) {
      event.preventDefault(); // Prevenir la acción predeterminada del enlace

      // Aquí puedes usar el ID del producto que está en el atributo 'id' del enlace, pero recuerda quitar el prefijo "edit-"
      const productId = event.currentTarget.id.replace("basura-", "");
      console.log(productId);

      // Seleccionar el div del producto usando el ID y eliminarlo del contenedor
      container.find(`.producto[id="${productId}"]`).remove();

      //eliminar producto de la memoria
      // Obtener los productos actuales del sessionStorage
      const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));

      // Filtrar la lista de productos para excluir el producto con el ID específico
      pedidoActual.productos = pedidoActual.productos.filter(producto => producto.idProducto !== productId);

      // Actualizar el sessionStorage con la nueva lista de productos
      sessionStorage.setItem("pedido", JSON.stringify(pedidoActual));

  });

  //el onclick de si pone editar
  //en este caso solo debe mostrar la info del producto y ahi se edita la cantidad
  //toca primero enviar la peticion y todo eso
    // Es importante hacer el evento click DESPUÉS de agregar los productos al contenedor
    container.find(".minieditar").on("click", function (event) {
        event.preventDefault(); // Prevenir la acción predeterminada del enlace
    
        // Aquí puedes usar el ID del producto que está en el atributo 'id' del enlace, pero recuerda quitar el prefijo "edit-"
        const productId = event.currentTarget.id.replace("editar-", "");
        console.log(productId);

        const promesaInformacionProducto = getInfoProducto(event.currentTarget.id);
  
        //guardar el id del producto para usarlo despues//
        sessionStorage.setItem("idproducto", productId);
    
        promesaInformacionProducto
          .then((res) => {
            console.log(res.ok);
            return res.json();
          })
          .then((data) => {
            console.log(data);
            hide('popUpCarrito');
            show("popup");
            generateInfoProducto(data);
    
          })
          .catch(() => {
            console.log("errorrrr");
          });
    });

}

function vaciarContenedor(nombreContenedor) {
  const container = document.getElementById(nombreContenedor); // Cambia 'ID_DEL_CONTENEDOR' por el ID real del contenedor
  container.innerHTML = '';
}


////////////////////////////////////////////AQUI TERMINA////////////////////////////////////


//OBTENER LOS PRODUCTOS
// peticion
//se manda la cant de productos que se quiere leer
async function getProductos() {
  return await fetch("http://localhost:8080/productos", {
    headers: {
      "Content-Type": "application/json",
    },
  });
}
//se llama la peticion y se guarda en promesaProductos
const promesaProductos = getProductos();
promesaProductos
  .then((res) => {
    console.log(res+"hola");
    return res.json(); 
  })
  .then((data) => {
    console.log(data);
    //ingresarlo al local storage la lista de ids de productos
    //y hacer el proceso de ingresar las fotos
    generateProductList(data);
  })
  .catch(() => {
    console.log("error");
  });

//se generan los productos para poner
function generateProductList(data) {
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
        //show("popup");//ESTO SE QUITA
    
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

////////////////////////////////PONER LA INFO EN EL POPUP DEL PRODUCTO/////////////////////
//se pide la informacion del producto
async function getInfoProducto(idproducto) {
  return await fetch("http://localhost:8080/productos/" + idproducto, {
    headers: {
      "Content-Type": "application/json",
    },
  });
}

function getCantidadPorProductoId(pedido, idProductoBuscado) {
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

  for (var i = 0; i < tiendasList.length; i++) {
    //mirar como saber cual tienda es
    //en este caso se mandan las tiendas donde esta disponible el producto
    //en radio se pone en value el valor que se enviara al servidor (que es el id de la tienda)

    //si todavia no ha pedido otros productos y por ende no ha seleccionado la tienda
    if(pedidoActual.idTienda==null)
    {
      var tiendaBlock = `
      <div id="RadioOptions1">
            <input type="radio" class="tienda" id="tienda${tiendasList[i].id}" name="TiendaSeleccion" value="${tiendasList[i].id}">
            <label id="labelt" for="tienda${tiendasList[i].ID}">${tiendasList[i].nombretienda}</label>
      </div>
      `;
    }
    else
    {
      //si ya selecciono un producto de una tienda, solo puede pedir productos de dicha tienda
      if(tiendasList[i].id==pedidoActual.idTienda)
      {
        var tiendaBlock = `
        <div id="RadioOptions1">
              <input type="radio" class="tienda" id="tienda${tiendasList[i].id}" name="TiendaSeleccion" value="${tiendasList[i].id}">
              <label id="labelt" for="tienda${tiendasList[i].ID}">${tiendasList[i].nombretienda}</label>
        </div>
        `;
      }
      else
      {
        var tiendaBlock = `
        <div id="RadioOptions1">
              <input type="radio" class="tienda" id="tienda${tiendasList[i].id}" name="TiendaSeleccion" value="${tiendasList[i].id}" disabled>
              <label id="labelt" for="tienda${tiendasList[i].ID}">${tiendasList[i].nombretienda}</label>
        </div>
        `;
      }
    }
    //REVISAR
    container.append(tiendaBlock);
  }
}

function AgregarCantidadYTotal(pedidoActual,precioTot)
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
function generateInfoProducto(data) {

  const pedidoActual = JSON.parse(sessionStorage.getItem("pedido"));

  console.log(pedidoActual);

  //titulo grande del producto
  $("#tituloProducto").text(data.nombreproducto);

  //foto del producto
  $("#fotopRODUCTO").attr("src", data.foto.foto);

  //calcula el precio actual con base en si tiene promocion o no
  const precioTot = data.preciodinero - data.preciodinero * data.promocion;
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
    $("#DescText2").text(data.promocion * 100 + "%");
    //se muestra el precio anterior tachado
    $("#DescText").text(data.preciodinero);
    $("#DescText").css("text-decoration", "line-through");
  }

  /*
  //si la cantidad esta en cero es porque no hay
  if (data.CantidadDisponible == 0) {
    $("#disponible").text("Agotado");
    $("#disponible").css("background-color", "#f17e7e"); // Cambia el color a rojo
  }*/

  //descripcion del producto
  $("#textoDescrip").text(data.descripcion);
 
  
  //ingredientes del producto
  /*const ingredientesList = data.ingredientes;

  //como es en una lista se concatenen con comas
  var ingredientesText = ingredientesList.join(", ");

  $("#textoIngred").text(ingredientesText);*/

  //una lista de tiendas
  /*const tiendasList = data.tiendas;

  AgregarRadioButtons(tiendasList, pedidoActual);*/
}

//PROCESO PARA OBTENER TIENDAS MINIS
async function getTiendas() {
  return await fetch("http://localhost:8080/tiendas/limit/8", {
    headers: {
      "Content-Type": "application/json",
    },
  });
}
//SE CREA LA PETICION
const promesaTiendas = getTiendas();
promesaTiendas
  .then((res) => {
    console.log(res.ok);
    return res.json(); 
  })
  .then((data) => {
    console.log(data);
    //ingresarlo al local storage la lista de ids de tiendas
    //y hacer el proceso de ingresar las fotos
    generateTiendaList(data);
  })
  .catch(() => {
    console.log("error");
  });
//generar cada tienda
function generateTiendaList(data) {
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

  // Es importante hacer el evento click DESPUÉS de agregar los productos al contenedor
  //se guarda
  container.find(".tiendaLink").on("click", function (event) {
    event.preventDefault(); // Prevenir la acción predeterminada del enlace
    sessionStorage.setItem("idTienda", event.currentTarget.id); // Guardar el ID de la tienda en sessionStorage
    //ESTO SE DEBE EDITAR
    window.location.href = "../html/plantilla.html"; // Redirigir a la nueva página
  });
}

//todo lo relacionado con la modificacion de la cantidad de producto
//tambien hara la labor de main
$(document).ready(function () {
  //EvaluarIngresoDeSesion();
  InicializarPedido();

  //asignar la foto del usuario
  asignarFoto();

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
    if ($.isNumeric(valor) && valor !== "") {
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

  //ESTO ES PARA LA BUSQUEDA RAPIDA
  $("#promo").on("click", function () {
    sessionStorage.setItem("palabrasClave", "promociones");
  });

  $("#groceries").on("click", function () {
    sessionStorage.setItem("palabrasClave", "tiendas");
  });

});
