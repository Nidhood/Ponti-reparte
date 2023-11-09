import { EvaluarIngresoDeSesion,asignarFoto,implementarBuscar} from './helpers/general.js';
import { generateTiendaList,getElem, show, hide,funcionalidadBotonesProductoCarrito,generateProductList,inicializarEventosProducto} from './helpers/infoProducto.js';
window.getElem = getElem;
window.show = show;
window.hide = hide;

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

//LAS SIGUIENTES FUNCIONES SE INVOCAN FUERA DEL MAIN
implementarBuscar();


funcionalidadBotonesProductoCarrito();


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

//todo lo relacionado con la modificacion de la cantidad de producto
//tambien hara la labor de main
$(document).ready(function () {
  //EvaluarIngresoDeSesion();
  InicializarPedido();

  //asignar la foto del usuario
  asignarFoto();

  inicializarEventosProducto();

  //ESTO ES PARA LA BUSQUEDA RAPIDA
  $("#promo").on("click", function () {
    sessionStorage.setItem("palabrasClave", "promociones");
  });

  $("#groceries").on("click", function () {
    sessionStorage.setItem("palabrasClave", "tiendas");
  });

});
