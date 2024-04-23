package dn.daniel.stock.top.office.store.GestionOfficeStore.Commandes;


import dn.daniel.stock.top.office.store.GestionOfficeStore.Repository.CommandesRepository;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Tag("Test CommandesController")
public class CommandeControllerTest {




    @Autowired
    private CommandesRepository commandesRepository;
    @Autowired
    private MockMvc mvc;

    private JSONObject json;



    @BeforeAll
    @AfterAll
    public void deleteDatabase(){
        //this.commandesRepository.deleteAll();
        json=null;
    }


    @Test
    @Order(1)
    @DisplayName("Test when create an Commandes To client")
    public void testWhenCreateCommandes()throws Exception{

        String jsonBody = "{\n" +
                "  \"date_livraison\": \"10/02/2024\"\n" +
                "}";
        MvcResult mvcResult= this.mvc.perform(
                        MockMvcRequestBuilders.post("/api/commandes/addCommandes/"+11)
                                .content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date_livraison", is("10/02/2024")))
                .andReturn();
        json =new JSONObject(mvcResult.getResponse().getContentAsString());

    }

    @Test
    @Order(2)
    @DisplayName("Test when read Liste Commandes")
        public void testWhenWeWentReadListCommandes() throws Exception{
       MvcResult mvcResult= this.mvc.perform(
                        MockMvcRequestBuilders.get("/api/client/commandesBy/"+6)
                )

                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date_livraison", is("10/02/2024")))

                .andReturn();
        json =new JSONObject(mvcResult.getResponse().getContentAsString());
        }
}
