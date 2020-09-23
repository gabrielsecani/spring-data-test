package br.com.fiap.fiapstock;

import br.com.fiap.fiapstock.dto.UserCreateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.data.url=jdbc:h2:mem:database"})
class FiapstockApplicationTests {

    //	@Autowired
//	private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser() throws Exception {
        //Preparação
        UserCreateDTO user = new UserCreateDTO();
        user.setUsername("Gabriel");
        user.setPassword("12345678");
        String json = objectMapper.writeValueAsString(user);

        // Execução/Verificação
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("Gabriel")));

    }

}
