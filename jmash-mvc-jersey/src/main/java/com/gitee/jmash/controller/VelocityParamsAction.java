
package com.gitee.jmash.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("velocity/params")
@Controller
@RequestScoped
public class VelocityParamsAction {

  private Models models;

  public VelocityParamsAction() {
    // no-arg constructor required by CDI
  }

  @Inject
  public VelocityParamsAction(Models models) {
    this.models = models;
  }

  @GET
  public String execute(@QueryParam("name") String name) {
    models.put("visitor", name);
    return "params.vm";
  }

}
