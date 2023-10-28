package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.entities.Ingrediente;
import javeriana.pontireparte.project.entities.IngredienteProducto;
import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.repositories.IngredienteProductoRepository;
import javeriana.pontireparte.project.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final IngredienteProductoRepository ingredienteProductoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository,  IngredienteProductoRepository ingredienteProductoRepository) {

        this.productoRepository = productoRepository;
        this.ingredienteProductoRepository = ingredienteProductoRepository;
    }

    public List<Producto> getProducto(){

        return productoRepository.findAll();
    }

    public Producto infoWithProducto(UUID productoId) {
        Producto producto = productoRepository.findById(productoId);
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
}
