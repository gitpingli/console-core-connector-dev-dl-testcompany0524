package com.pccw.platform.connector.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pccw.platform.connector.config.HeartbeatConfig.Heartbeat;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@ConfigurationProperties(prefix = "springdoc")
public class SwaggerConfig {

  private List<Server> servers;

  public List<Server> getServers() {
    return servers;
  }

  public void setServers(List<Server> servers) {
    this.servers = servers;
  }

  // https://swagger.io/docs/specification/authentication/
  @Bean
  public OpenAPI customOpenAPI(@Autowired Heartbeat heartbeat) {
    OpenAPI api =
        new OpenAPI()
            .info(new Info().title(heartbeat.getDescription()).version(heartbeat.getVersion()));
    api.setServers(servers);
    return api;
  }
}
