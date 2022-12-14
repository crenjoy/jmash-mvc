
package com.gitee.jmash;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mvc.security.Csrf;
import jakarta.mvc.security.Csrf.CsrfOptions;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.Map;

/**
 * Default JAX-RS application listening on /mvc
 */
@ApplicationPath("/mvc")
@ApplicationScoped
public class App extends Application {

  @Override
  public Map<String, Object> getProperties() {
    return Map.of(Csrf.CSRF_PROTECTION, CsrfOptions.IMPLICIT);
  }

  public static void main(String[] args) {
    JmashApplication.run(App.class, args);
  }
}
