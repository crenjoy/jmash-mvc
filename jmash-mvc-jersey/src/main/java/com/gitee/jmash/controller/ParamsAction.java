
package com.gitee.jmash.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("params")
@Controller
@RequestScoped
public class ParamsAction {

  private Models models;

  public ParamsAction() {
    // no-arg constructor required by CDI
  }

  @Inject
  public ParamsAction(Models models) {
    this.models = models;
  }

  @GET
  public String execute(@QueryParam("name") String name) {
    models.put("visitor", name);
    return "params.jsp";
  }
}
