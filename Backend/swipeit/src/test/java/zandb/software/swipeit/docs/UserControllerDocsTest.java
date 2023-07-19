package zandb.software.swipeit.docs;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zandb.software.swipeit.data.user.Client;
import zandb.software.swipeit.data.user.Supplier;
import zandb.software.swipeit.data.user.dto.ClientDTO;
import zandb.software.swipeit.data.user.repository.ClientRepository;
import zandb.software.swipeit.data.user.repository.SupplierRepository;
import zandb.software.swipeit.data.user.service.SwipeItUserDetailsService;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerDocsTest {

    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SwipeItUserDetailsService swipeItUserDetailsService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void getUserClientTest() throws Exception {
        Client client = new Client();
        client.setUserId(1);
        client.setUsername("Test");
        client.setPassword(passwordEncoder.encode("test"));

        clientRepository.save(client);

        UserDetails swipeItUserDetails = swipeItUserDetailsService.loadUserByUsername("Test");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                swipeItUserDetails, null, swipeItUserDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        this.mockMvc.perform(get("/user").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0IiwiaWF0IjoxNjg5NzkzNTUwLCJleHAiOjE3MjEzMjk1NTB9.DbxoXgPgcR1E5MsTqrallAX17yJd_m_I9v6ZH8j2hFYej2A_40syPjAO_UBj5GqaQYIt6zWsdkblLe6jxRXzbA")).andExpect(status().isOk())
                .andDo(document("get-user-client",
                        requestHeaders(headerWithName("Authorization").description("The authorization header using the basic auth method")),
                        responseFields(
                                subsectionWithPath("userId").description("The unique id of the user"),
                                subsectionWithPath("username").description("The unique username of the user"),
                                subsectionWithPath("firstName").description("The firstname of the user"),
                                subsectionWithPath("lastName").description("The lastt name of the user"),
                                subsectionWithPath("telephoneNumber").description("The telephone number of the user"),
                                subsectionWithPath("userType").description("The type of user. Is either CLIENT or SUPPLIER"),
                                subsectionWithPath("country").description("The country that the user lives in"),
                                subsectionWithPath("city").description("The city that the user lives in"),
                                subsectionWithPath("biography").description("A short description of the user"),
                                subsectionWithPath("height").description("The height of the user"),
                                subsectionWithPath("age").description("The age of the user")
                        )));
    }

    @Test
    public void getUserSupplierTest() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setUserId(1);
        supplier.setUsername("Test");
        supplier.setPassword(passwordEncoder.encode("test"));

        supplierRepository.save(supplier);

        UserDetails swipeItUserDetails = swipeItUserDetailsService.loadUserByUsername("Test");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                swipeItUserDetails, null, swipeItUserDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        this.mockMvc.perform(get("/user").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0IiwiaWF0IjoxNjg5NzkzNTUwLCJleHAiOjE3MjEzMjk1NTB9.DbxoXgPgcR1E5MsTqrallAX17yJd_m_I9v6ZH8j2hFYej2A_40syPjAO_UBj5GqaQYIt6zWsdkblLe6jxRXzbA")).andExpect(status().isOk())
                .andDo(document("get-user-supplier",
                        requestHeaders(headerWithName("Authorization").description("The authorization header using the basic auth method")),
                        responseFields(
                                subsectionWithPath("userId").description("The unique id of the user"),
                                subsectionWithPath("username").description("The unique username of the user"),
                                subsectionWithPath("firstName").description("The firstname of the user"),
                                subsectionWithPath("lastName").description("The lastt name of the user"),
                                subsectionWithPath("telephoneNumber").description("The telephone number of the user"),
                                subsectionWithPath("userType").description("The type of user. Is either CLIENT or SUPPLIER"),
                                subsectionWithPath("properties").description("The list of properties, that this supplier offers")
                        )));
    }

    @Test
    public void updateClientTest() throws Exception {
        Client client = new Client();
        client.setUserId(1);
        client.setUsername("Test");
        client.setPassword(passwordEncoder.encode("test"));

        clientRepository.save(client);

        UserDetails swipeItUserDetails = swipeItUserDetailsService.loadUserByUsername("Test");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                swipeItUserDetails, null, swipeItUserDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        this.mockMvc.perform(put("/client")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0IiwiaWF0IjoxNjg5NzkzNTUwLCJleHAiOjE3MjEzMjk1NTB9.DbxoXgPgcR1E5MsTqrallAX17yJd_m_I9v6ZH8j2hFYej2A_40syPjAO_UBj5GqaQYIt6zWsdkblLe6jxRXzbA")
                        .content("{\"userId\":1,\"username\":\"Test\",\"firstName\":null,\"lastName\":\"New last name\",\"telephoneNumber\":null,\"userType\":\"CLIENT\",\"country\":null,\"city\":null,\"biography\":null,\"height\":0,\"age\":0}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("update-user-client",
                        requestHeaders(headerWithName("Authorization").description("The authorization header using the basic auth method"))));
    }

    @Test
    public void updateSupplierTest() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setUserId(1);
        supplier.setUsername("Test");
        supplier.setPassword(passwordEncoder.encode("test"));

        supplierRepository.save(supplier);

        UserDetails swipeItUserDetails = swipeItUserDetailsService.loadUserByUsername("Test");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                swipeItUserDetails, null, swipeItUserDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        this.mockMvc.perform(put("/supplier")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0IiwiaWF0IjoxNjg5NzkzNTUwLCJleHAiOjE3MjEzMjk1NTB9.DbxoXgPgcR1E5MsTqrallAX17yJd_m_I9v6ZH8j2hFYej2A_40syPjAO_UBj5GqaQYIt6zWsdkblLe6jxRXzbA")
                        .content("{\"userId\":1,\"username\":\"Test\",\"firstName\":null,\"lastName\":\"New last name\",\"telephoneNumber\":null,\"userType\":\"SUPPLIER\",\"properties\":[]}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("update-user-supplier",
                        requestHeaders(headerWithName("Authorization").description("The authorization header using the basic auth method"))));
    }

}
