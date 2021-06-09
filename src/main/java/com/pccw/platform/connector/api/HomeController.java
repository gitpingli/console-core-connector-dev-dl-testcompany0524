/** */
package com.pccw.platform.connector.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/** @author dxiong */
@RestController
@RequestMapping(value = "${server.rest.base:}", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "General")
public class HomeController {

  @GetMapping
  public RedirectView index() {
    return new RedirectView("/swagger-ui.html");
  }
}
