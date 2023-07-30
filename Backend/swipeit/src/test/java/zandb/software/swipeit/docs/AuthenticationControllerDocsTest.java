package zandb.software.swipeit.docs;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zandb.software.swipeit.data.user.service.SwipeItUserService;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthenticationControllerDocsTest {

  private MockMvc mockMvc;

  @Autowired
  private SwipeItUserService swipeItUserService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @BeforeEach
  public void setUp(WebApplicationContext webApplicationContext,
      RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation)).alwaysDo(document("{method-name}",
            preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
        .build();
  }

  @Test
  public void registerUserExample() throws Exception {
    this.mockMvc.perform(post("/register").header("Authorization", "Basic VGVzdDp0ZXN0"))
        .andExpect(status().isOk())
        .andDo(document("register-user",
            requestHeaders(headerWithName("Authorization").description(
                "The authorization header using the basic auth method"))));
  }

  @Test
  public void authenticateUserExample() throws Exception {
    this.swipeItUserService.createUserWithUsernameAndPassword("Test",
        passwordEncoder.encode("test"));

    this.mockMvc.perform(post("/authenticate").header("Authorization", "Basic VGVzdDp0ZXN0"))
        .andExpect(status().isOk())
        .andDo(document("authenticate-user",
            requestHeaders(headerWithName("Authorization").description(
                "The authorization header using the basic auth method")),
            responseFields(subsectionWithPath("jwtToken").description(
                "The JWT Token which is used for the authentication in all further requests"))));
  }
}
