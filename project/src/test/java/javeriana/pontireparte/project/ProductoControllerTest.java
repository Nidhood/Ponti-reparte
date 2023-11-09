package javeriana.pontireparte.project;

import javeriana.pontireparte.project.controllers.ProductoController;
import javeriana.pontireparte.project.dto.ProductoTiendasDTO;
import javeriana.pontireparte.project.dto.TiendaDTO;
import javeriana.pontireparte.project.repositories.TiendaProductoRepository;
import javeriana.pontireparte.project.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static javeriana.pontireparte.project.PontireparteApplication.logger;
import static org.mockito.Mockito.*;


@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @MockBean
    private ProductoService productoService;

    @MockBean
    private TiendaProductoRepository tiendaProductoRepository; // Agregado el repositorio simulado

    @Test
    public void testGetTiendasPorProducto() throws Exception {
        // Configura el objeto de prueba
        UUID productoId = UUID.fromString("3c1f0f42-0514-45c2-995f-277916eefbd4"); // Chocolate
        ProductoTiendasDTO productoTiendasDTO = new ProductoTiendasDTO();

        // Configura los valores esperados en productoTiendasDTO
        productoTiendasDTO.setId(productoId);
        productoTiendasDTO.setTiendas(mapToProductosTiendas(tiendaProductoRepository.findTiendasByProductoId(productoId)));

        // Configura el comportamiento del servicio simulado
        when(productoService.getTiendasPorProducto(productoId)).thenReturn(productoTiendasDTO);

        // Crea MockMvc manualmente
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProductoController(productoService)).build();

        // Realiza la solicitud MockMvc y verifica los resultados esperados
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/productos/" + productoId + "/tiendas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productoTiendasDTO.getId().toString()))
                // Asegúrate de verificar otras propiedades según sea necesario
                .andExpect(MockMvcResultMatchers.jsonPath("$.tiendas").isArray());

        // Verifica que el método del servicio haya sido invocado con el ID correcto
        verify(productoService, times(1)).getTiendasPorProducto(productoId);

        // Imprime información para análisis
        logger.info("Resultado de la prueba:");
        logger.info("Valor enviado (ID): {}", productoId);
        logger.info("Valor esperado (ProductoTiendasDTO): {}", productoTiendasDTO);
        logger.info("Valor devuelto: {}", productoService.getTiendasPorProducto(productoId));
    }

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
}
