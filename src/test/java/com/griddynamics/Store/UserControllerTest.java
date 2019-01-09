package com.griddynamics.Store;

import com.griddynamics.Store.model.User;
import com.griddynamics.Store.security.EncryptedPasswordUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StoreApplication.class)
@WebAppConfiguration
public class UserControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void whenFindUserByEmailReturnOk() throws Exception {
        User newUser = new User();
        newUser.setEmail("www@mail.ru");
        newUser.setPassword(EncryptedPasswordUtils.encryptePassword("1234"));
        String inputJson = super.mapToJson(newUser);
         mvc.perform(post("/newUser")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isOk());
    }

    @Test
    public void whenLoginStatusIsOk() throws Exception {
//        String plainClientCredentials="fake@mail.ru:9876";
//        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));

        mvc.perform(post("/login").header("Authorization", "Basic bmV3QGZha2VtYWlsLmNvbTo5ODc2"))
                .andExpect(status().isOk());
    }

}
