package com.pccw.platform.connector.api;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pccw.platform.connector.config.HeartbeatConfig.Heartbeat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-01-06T04:08:16.794Z[GMT]")
@RestController
@RequestMapping(
    value = "${server.rest.base:}/heartbeat",
    produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "General")
public class HeartbeatApiController {

  @Autowired private Heartbeat heartbeat;

  @Operation(summary = "Get heartbeat info")
  @GetMapping
  public Heartbeat heartbeat() {
    heartbeat.setCurrent(Instant.now().toString());
    return heartbeat;
  }
}
