package org.abratuhi.dbrider;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class PostgresTestResource implements QuarkusTestResourceLifecycleManager {

  private PostgreSQLContainer postgres;

  @Override
  public Map<String, String> start() {

    postgres = new PostgreSQLContainer()
      .withDatabaseName("postgres")
      .withUsername("postgres")
      .withPassword("postgres");

    postgres.start();

    return Map.of(
      "%test.quarkus.datasource.username", "postgres",
      "%test.quarkus.datasource.password", "postgres",
      "%test.quarkus.datasource.jdbc.url", "jdbc:postgresql://localhost:" + postgres.getMappedPort(5432) + "/postgres"
    );
  }

  @Override
  public void stop() {
    postgres.stop();
  }
}
