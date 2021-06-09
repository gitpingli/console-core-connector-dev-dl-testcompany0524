/** */
package com.pccw.platform.connector.config;

import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author dxiong */
@Configuration
public class HeartbeatConfig {

  private String startUpAt = Instant.now().toString();

  @Bean
  public Heartbeat getHeartBeat(@Autowired InfoEndpoint info) {

    return Optional.ofNullable(info.info())
        .map(x -> x.get("build"))
        .map(
            x -> {
              @SuppressWarnings("rawtypes")
              HashMap build = (HashMap) x;
              Heartbeat data = new Heartbeat();
              data.name = (String) build.get("artifact");
              data.version = (String) build.get("version");
              data.description = (String) build.get("name");
              data.status = "UP";
              Instant instant = (Instant) build.get("time");
              data.releasedAt = instant.toString();
              data.startUpAt = startUpAt;
              data.current = Instant.now().toString();
              return data;
            })
        .orElse(new Heartbeat());
  }

  @Data
  public static class Heartbeat {

    private String name;
    private String description;
    private String version;
    private String status;
    private String startUpAt;
    private String releasedAt;
    private String current;
  }
}
