import { EvaluarIngresoDeSesion,asignarFoto,implementarBuscar} from './helpers/general.js';
import { generateTiendaList,getElem, show, hide,funcionalidadBotonesProductoCarrito,generateProductListBuscador,inicializarEventosProducto} from './helpers/infoProducto.js';
window.getElem = getElem;
window.show = show;
window.hide = hide;


//OBTENER LOS PRODUCTOS
// peticion
//se manda la cant de productos que se quiere leer

async function getProductos(){
  return await fetch('http://localhost:8080/buscar/productos/'+sessionStorage.getItem('palabrasClave'), {
    headers: {
          'Content-Type': 'application/json'
      }
  })
}

//se llama la peticion y se guarda en promesaProductos
var promesaProductos = getProductos()

promesaProductos
  .then((res) => {
    return res.json(); 
  })
  .then((data) => {
    console.log(data);
    //ingresarlo al local storage la lista de ids de productos
    //y hacer el proceso de ingresar las fotos
    generateProductListBuscador(data);
  })
  .catch(() => {
    console.log("error");
  });


  //PROCESO PARA OBTENER TIENDAS MINIS
async function getTiendas(){
  
  var inicial='http://localhost:8080/buscar/tiendas';
  
  if(sessionStorage.getItem('palabrasClave')!="tiendas")
  {
    inicial=inicial+"/"+sessionStorage.getItem('palabrasClave');
  }

  return await fetch(inicial, {
  
      headers: 
      {
          'Content-Type': 'application/json'
      }
  })
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

   funcionalidadBotonesProductoCarrito();

  $(document).ready(function() {
    
    EvaluarIngresoDeSesion();
    asignarFoto();
    implementarBuscar();
    inicializarEventosProducto();
  
  });
