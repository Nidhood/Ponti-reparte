package javeriana.pontireparte.project.services;

import javeriana.pontireparte.project.dto.UbicacionDTO;
import javeriana.pontireparte.project.entities.Ubicacion;
import javeriana.pontireparte.project.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    @Autowired
    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<UbicacionDTO> getAllUbicaciones() {
        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();
        return ubicaciones.stream()
                .map(this::mapToUbicacionDTO)
                .collect(Collectors.toList());
    }

    private UbicacionDTO mapToUbicacionDTO(Ubicacion ubicacion) {
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setId(ubicacion.getId());
        ubicacionDTO.setNombre(ubicacion.getEdificio()); // Ajusta según la estructura de tu Ubicacion
        return ubicacionDTO;
    }
}