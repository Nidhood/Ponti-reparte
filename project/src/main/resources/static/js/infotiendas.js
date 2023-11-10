import { EvaluarIngresoDeSesion,asignarFoto,implementarBuscar} from './helpers/general.js';
import {generateTiendaList,getElem, show, hide,funcionalidadBotonesProductoCarrito,generateProductList,inicializarEventosProducto} from './helpers/infoProducto.js';
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
    console.log(data);
    console.log(sessionStorage.getItem("idTienda"))
    //ingresarlo al local storage la lista de ids de productos
    //y hacer el proceso de ingresar las fotos
    generateProductListTiendas(data.productos);
  })
  .catch(() => {
    console.log("error");
  });

  function generateProductListTiendas(data) {
        var container = $(".scrollBoxProducto");
      
        for (var i = 0; i < 8; i++) {
          var productBlock = `
            <a href="#" class="productLink" id="${data[i].id}">
              <div class="producto">
                  <div class="circuloproducto">
                          <img class="fotominipRODUCTO" src="${data[i].foto}" alt="foto Producto">
                  </div>
                  <p class="nombreminiProducto">
                      ${data[i].nombreProducto}
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
//todo lo relacionado con la modificacion de la cantidad de producto
//tambien hara la labor de main
$(document).ready(function () {
  //EvaluarIngresoDeSesion();
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
