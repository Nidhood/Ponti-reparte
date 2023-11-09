package javeriana.pontireparte.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import javeriana.pontireparte.project.controllers.UsuarioController;
import javeriana.pontireparte.project.entities.Usuario;
import javeriana.pontireparte.project.services.UsuarioService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    public void  testLoginUsuario() throws Exception {
        // Crea un usuario de prueba
        Usuario usuario = new Usuario();

        // Realiza la prueba del controlador usando MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/usuario/ingreso")
                        .content(asJsonString(usuario))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    // Utilidad para convertir un objeto a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
