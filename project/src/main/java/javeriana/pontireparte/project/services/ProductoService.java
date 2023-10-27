package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.entities.Foto;
import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.repositories.ProductoRepository;
import javeriana.pontireparte.project.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class ProductoService {
    private final ProductoRepository productoRepository;
    @Autowired
    public ProductoService(ProductoRepository productoRepository) {

        this.productoRepository = productoRepository;
    }

    public List<Producto> getProducto(){

        return productoRepository.findAll();
    }

}
