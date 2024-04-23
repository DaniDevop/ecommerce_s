package dn.daniel.stock.top.office.store.GestionOfficeStore.Client;

import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.ClientRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Gerer le cycle de vie de nos test
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Ordonner les test
@AutoConfigureMockMvc // Injecter un mock
@Tag("Test ClientControllerBack") // Permet de taguer notre classe
@DisplayName("Test controller Client") // Donner une sorte de description
public class ClientTestController {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MockMvc mvc;

    private JSONObject json;

    @BeforeAll
    @AfterAll
    public  void deleteDatabase(){

        json=null;
    }

    @Test
    @Order(1)
    @DisplayName("Test de creation d un client")
    public void testWhenCreateClient()throws Exception{
      String jsonBody = "{\n" +
                "  \"nom\": \"Daniel Levy\",\n" +
                "  \"email\": \"daniel@gmail.com\",\n" +
                "  \"tel\": \"782529002\",\n" +
                "  \"password\": \"1234\",\n" +
                "  \"id\": 1\n" +
                "}";
        MvcResult mvcResult= this.mvc.perform(
                MockMvcRequestBuilders.post("/api/client/addClient")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom", is("Daniel Levy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is("daniel@gmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tel", is("782529002")))
                .andReturn();
        json =new JSONObject(mvcResult.getResponse().getContentAsString());


    }
    @Test
    @Order(2)
    @DisplayName("Test de lecture d un client")
    public void testThatWeCanReadClient()throws Exception{

        this.mvc.perform(
                MockMvcRequestBuilders.get("/api/client/getClientBy/"+json.getInt("id"))
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom", is("Daniel Levy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is("daniel@gmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tel", is("782529002")))
                .andReturn();
    }


    @Test
    @Order(3)
    @DisplayName("Test de lecture d un client")
    public void testThatWeCanReadClientList()throws Exception{

        this.mvc.perform(
                        MockMvcRequestBuilders.get("/api/client/clientAll")
         )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nom", is("Daniel Levy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email", is("daniel@gmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].tel", is("782529002")))
                .andReturn();
    }


    @Test
    @Order(4)
    @DisplayName("Test de creation d un client")
    public void testWhenUpdateClient()throws Exception{
        String jsonBody = "{\n" +
                "  \"nom\": \"Daniel Levys\",\n" +
                "  \"email\": \"danielLevy@gmail.com\",\n" +
                "  \"tel\": \"782529002\",\n" +
                "  \"password\": \"1234\",\n" +
                "  \"id\": 1\n" +
                "}";
        MvcResult mvcResult= this.mvc.perform(
                        MockMvcRequestBuilders.put("/api/client/updateClient")
                                .content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom", is("Daniel Levys")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is("danielLevy@gmail.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tel", is("782529002")))
                .andReturn();
        json =new JSONObject(mvcResult.getResponse().getContentAsString());


    }

}
