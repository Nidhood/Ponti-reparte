package javeriana.pontireparte.project.controllers;

import javeriana.pontireparte.project.dto.UbicacionDTO;
import javeriana.pontireparte.project.entities.Ubicacion;
import javeriana.pontireparte.project.services.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Allow all origins
@RequestMapping(path = "/ubicaciones")
public class UbicacionController {
    private final UbicacionService ubicacionService;

    @Autowired
    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @GetMapping
    public List<UbicacionDTO> getAllUbicaciones() {
        return ubicacionService.getAllUbicaciones();
    }
}
