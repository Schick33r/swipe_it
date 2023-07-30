package zandb.software.swipeit.docs;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import zandb.software.swipeit.data.tokenblacklist.BlacklistedJwtToken;
import zandb.software.swipeit.data.tokenblacklist.repository.BlacklistedJwtTokenRepository;
import zandb.software.swipeit.data.user.SwipeItUser;
import zandb.software.swipeit.data.user.repository.SwipeItUserRepository;
import zandb.software.swipeit.data.user.service.SwipeItUserDetailsService;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LogoutControllerDocsTest {

  private MockMvc mockMvc;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private SwipeItUserDetailsService swipeItUserDetailsService;

  @Autowired
  private BlacklistedJwtTokenRepository blacklistedJwtTokenRepository;

  @Autowired
  private SwipeItUserRepository swipeItUserRepository;

  @BeforeEach
  public void setUp(WebApplicationContext webApplicationContext,
      RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation)).alwaysDo(document("{method-name}",
            preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
        .build();
  }

  @Test
  public void logoutUserDocTest() throws Exception {
    SwipeItUser user = new SwipeItUser();
    user.setUserId(1);
    user.setUsername("Test");
    user.setPassword(passwordEncoder.encode("test"));

    swipeItUserRepository.save(user);

    UserDetails swipeItUserDetails = swipeItUserDetailsService.loadUserByUsername("Test");

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        swipeItUserDetails, null, swipeItUserDetails.getAuthorities());
    usernamePasswordAuthenticationToken
        .setDetails(new WebAuthenticationDetailsSource());

    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

    this.mockMvc.perform(post("/logoutUser").header("Authorization",
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0IiwiaWF0IjoxNjg5NzkzNTUwLCJleHAiOjE3MjEzMjk1NTB9.DbxoXgPgcR1E5MsTqrallAX17yJd_m_I9v6ZH8j2hFYej2A_40syPjAO_UBj5GqaQYIt6zWsdkblLe6jxRXzbA"))
        .andExpect(status().isOk())
        .andDo(document("logout-user",
            requestHeaders(headerWithName("Authorization").description(
                "The authorization header using the basic auth method"))));

    List<BlacklistedJwtToken> blacklistedJwtTokens = blacklistedJwtTokenRepository.findAll();

    Assertions.assertEquals(blacklistedJwtTokens.size(), 1);
    Assertions.assertEquals(blacklistedJwtTokens.get(0).getToken(),
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0IiwiaWF0IjoxNjg5NzkzNTUwLCJleHAiOjE3MjEzMjk1NTB9.DbxoXgPgcR1E5MsTqrallAX17yJd_m_I9v6ZH8j2hFYej2A_40syPjAO_UBj5GqaQYIt6zWsdkblLe6jxRXzbA");
  }
}
