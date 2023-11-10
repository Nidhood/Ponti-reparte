import { EvaluarIngresoDeSesion,asignarFoto,implementarBuscar} from './helpers/general.js';
import {generateProductListTiendas,getElem, show, hide,funcionalidadBotonesProductoCarrito,inicializarEventosProducto} from './helpers/infoProducto.js';
window.getElem = getElem;
window.show = show;
window.hide = hide;

//LAS SIGUIENTES FUNCIONES SE INVOCAN FUERA DEL MAIN
implementarBuscar();

funcionalidadBotonesProductoCarrito();

//OBTENER LOS PRODUCTOS
// peticion
//se manda la cant de productos que se quiere leer
async function getProductosTienda() {
  return await fetch("http://localhost:8080/tiendas/"+sessionStorage.getItem("idTienda"), {
    headers: {
      "Content-Type": "application/json",
    },
  });
}
//se llama la peticion y se guarda en promesaProductos
const promesaProductos = getProductosTienda();
promesaProductos
  .then((res) => {
    console.log(res.ok);
    return res.json();
  })
  .then((data) => {
    //ingresarlo al local storage la lista de ids de productos
    //y hacer el proceso de ingresar las fotos
    generateProductListTiendas(data.productos);
  })
  .catch(() => {
    console.log("error");
  });

//todo lo relacionado con la modificacion de la cantidad de producto
//tambien hara la labor de main
$(document).ready(function () {
  EvaluarIngresoDeSesion();
 // InicializarPedido();

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
