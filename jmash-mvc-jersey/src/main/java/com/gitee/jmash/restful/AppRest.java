
package com.gitee.jmash.restful;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.HashMap;
import java.util.Map;

@Path("app")
@Produces("application/json")
public class AppRest {

  @GET
  public Map<String, String> getMap() {
    Map<String, String> map = new HashMap<>();
    map.put("name", "Jakarta Restful Service");
    map.put("version", "3.1");
    return map;
  }

}
