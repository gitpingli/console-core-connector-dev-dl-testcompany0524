package com.pccw.platform.connector.api.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IndexHomeControllerIT {

  @Autowired private MockMvc mvc;

  @Test
  void testIndexPage() throws Exception {
    mvc.perform(get("/").contentType(MediaType.ALL))
        .andDo(print())
        .andExpect(status().is3xxRedirection());
  }
}
