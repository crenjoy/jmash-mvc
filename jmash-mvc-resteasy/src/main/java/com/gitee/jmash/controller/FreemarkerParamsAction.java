
package com.gitee.jmash.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("freemarker/params")
@Controller
@RequestScoped
public class FreemarkerParamsAction {

  private Models models;

  public FreemarkerParamsAction() {
    // no-arg constructor required by CDI
  }

  @Inject
  public FreemarkerParamsAction(Models models) {
    this.models = models;
  }

  @GET
  public String execute(@QueryParam("name") String name) {
    models.put("visitor", name);
    return "params.ftl";
  }

}
