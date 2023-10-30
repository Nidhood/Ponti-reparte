package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.dto.ProductoTiendasDTO;
import javeriana.pontireparte.project.dto.TiendaDTO;
import javeriana.pontireparte.project.entities.*;
import javeriana.pontireparte.project.repositories.IngredienteProductoRepository;
import javeriana.pontireparte.project.repositories.ProductoRepository;
import javeriana.pontireparte.project.repositories.TiendaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final IngredienteProductoRepository ingredienteProductoRepository;
    private final TiendaProductoRepository tiendaProductoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository,  IngredienteProductoRepository ingredienteProductoRepository, TiendaProductoRepository tiendaProductoRepository) {

        this.productoRepository = productoRepository;
        this.ingredienteProductoRepository = ingredienteProductoRepository;
        this.tiendaProductoRepository = tiendaProductoRepository;
    }

    public List<Producto> getProducto(){
        return productoRepository.findAll();
    }

    public List<Producto> getTodosLosProductosConIngredientes() {
        List<Producto> todosLosProductos = productoRepository.findAll();
        todosLosProductos.forEach(producto -> {
            producto.setIngredientes(mapToIngredientesConCantidad(ingredienteProductoRepository.findIngredientesByProductoId(producto.getId())));
        });
        return todosLosProductos;
    }

    public Producto infoWithProducto(UUID productoId) {
        Producto producto = productoRepository.findProductoById(productoId);
        producto.setIngredientes(ProductoService.mapToIngredientesConCantidad(ingredienteProductoRepository.findIngredientesByProductoId(productoId)));
        return producto;
    }

    public static List<IngredienteProducto> mapToIngredientesConCantidad(List<Object[]> ingredientesConCantidad) {
        return ingredientesConCantidad.stream()
                .map(obj -> {
                    IngredienteProducto ingredienteProducto = new IngredienteProducto();
                    Ingrediente ingrediente = new Ingrediente();
                    ingrediente.setNombreingrediente((String) obj[0]);
                    ingredienteProducto.setIngrediente(ingrediente);
                    ingredienteProducto.setCantidad((int) obj[1]);
                    return ingredienteProducto;
                })
                .collect(Collectors.toList());
    }

    public List<Producto> buscarProductosPorPalabraClave(String palabraclave) {
        if(palabraclave.equalsIgnoreCase("promociones")){
            System.out.println("entro a promociones");
            List<Producto> productosEncontrados = productoRepository.findByKeywordByPromocion(palabraclave);
            productosEncontrados.forEach(producto -> {
                producto.setIngredientes(mapToIngredientesConCantidad(ingredienteProductoRepository.findIngredientesByProductoId(producto.getId())));
            });
            return productosEncontrados;
        } else {
            List<Producto> productosEncontrados = productoRepository.findByKeyword(palabraclave);
            productosEncontrados.forEach(producto -> {
                producto.setIngredientes(mapToIngredientesConCantidad(ingredienteProductoRepository.findIngredientesByProductoId(producto.getId())));
            });
            return productosEncontrados;
        }
    }

    // Método para mapear a TiendaDTO
    public List<TiendaDTO> mapToProductosTiendas(List<Object[]> tiendasConCantidad) {
        return tiendasConCantidad.stream()
                .map(tiendas -> {
                    TiendaDTO tiendaDTO = new TiendaDTO();
                    tiendaDTO.setId((UUID) tiendas[0]);
                    tiendaDTO.setNombreTienda((String) tiendas[1]);
                    tiendaDTO.setFoto((String) tiendas[2]);
                    tiendaDTO.setCantidad((int) tiendas[3]);
                    return tiendaDTO;
                })
                .collect(Collectors.toList());
    }


    public ProductoTiendasDTO getTiendasPorProducto(UUID productoId) {
        Producto producto = productoRepository.findProductoById(productoId);
        ProductoTiendasDTO productoTiendasDTO = new ProductoTiendasDTO();
        productoTiendasDTO.setId(producto.getId());
        productoTiendasDTO.setNombreproducto(producto.getNombreproducto());
        productoTiendasDTO.setFoto(producto.getFoto().getFoto());
        productoTiendasDTO.setPreciodinero(producto.getPreciodinero());
        productoTiendasDTO.setPreciopuntos(producto.getPreciopuntos());
        productoTiendasDTO.setDescripcion(producto.getDescripcion());
        productoTiendasDTO.setPromocion(producto.getPromocion());
        productoTiendasDTO.setTiendas(mapToProductosTiendas(tiendaProductoRepository.findTiendasByProductoId(productoId)));
        return productoTiendasDTO;
    }
}
