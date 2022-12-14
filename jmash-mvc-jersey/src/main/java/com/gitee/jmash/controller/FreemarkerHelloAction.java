
package com.gitee.jmash.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("freemarker/hello")
@Controller
@RequestScoped
public class FreemarkerHelloAction {

  @GET
  public String execute() {
    return "hello.ftl";
  }

}
