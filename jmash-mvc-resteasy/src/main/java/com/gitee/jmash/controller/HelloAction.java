
package com.gitee.jmash.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("hello")
@Controller
@RequestScoped
public class HelloAction {

  @GET
  public String execute() {
    return "hello.jsp";
  }
  
}
