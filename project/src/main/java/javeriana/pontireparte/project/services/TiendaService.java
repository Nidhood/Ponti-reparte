package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.entities.Producto;
import javeriana.pontireparte.project.entities.Tienda;
import javeriana.pontireparte.project.repositories.ProductoRepository;
import javeriana.pontireparte.project.repositories.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TiendaService {
    private final TiendaRepository tiendaRepository;
    @Autowired
    public TiendaService(TiendaRepository tiendaRepository) {

        this.tiendaRepository = tiendaRepository;
    }

    public List<Tienda> getTienda(){

        return tiendaRepository.findAll();
    }

}
