
package com.gitee.jmash;

import java.io.File;
import java.net.URISyntaxException;
import javax.naming.NamingException;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class JmashApplication {

  private static Log log = LogFactory.getLog(JmashApplication.class);

  public static void run(Class<?> callingClass, String[] args) {
    try {
      runTomcat(callingClass, args);
    } catch (Exception ex) {
      log.error(ex);
    }
  }

  protected static File getRootFolder() {
    try {
      File root;
      String runningJarPath = JmashApplication.class.getProtectionDomain().getCodeSource()
          .getLocation().toURI().getPath().replaceAll("\\\\", "/");
      int lastIndexOf = runningJarPath.lastIndexOf("/target/");
      if (lastIndexOf < 0) {
        root = new File("");
      } else {
        root = new File(runningJarPath.substring(0, lastIndexOf));
      }
      log.info("Application Root Folder: " + root.getAbsolutePath());
      return root;
    } catch (URISyntaxException ex) {
      throw new RuntimeException(ex);
    }
  }

  protected static void runTomcat(Class<?> callingClass, String[] args)
      throws LifecycleException, NamingException {
    System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");

    File root = getRootFolder();

    Tomcat tomcat = new Tomcat();

    File base = new File(root, "target/tomcat");
    if (!base.exists()) {
      base.mkdirs();
    }
    log.info("Tomcat Work Path:" + base.getAbsolutePath());
    tomcat.setBaseDir(base.getAbsolutePath());

    int port = 8080;
    Connector connector = tomcat.getConnector();
    connector.setPort(port);

    log.info("Tomcat running on port : " + port);

    File app = new File(root.getAbsolutePath(), "src/main/webapp");
    if (!app.exists()) {
      app = new File(base.getAbsolutePath(), "webapp");
    }
    if (!app.exists()) {
      app.mkdirs();
    }

    log.info("Configuring Webapp Path: " + app.getAbsolutePath());
    StandardContext ctx = (StandardContext) tomcat.addWebapp("", app.getAbsolutePath());

    // Set execution independent of current thread context classloader
    // (compatibility with exec:java mojo)
    ctx.setParentClassLoader(callingClass.getClassLoader());

    // Declare an alternative location for your "WEB-INF/classes" dir
    // Servlet 3.0 annotation will work
    File webInfClassesFolder = new File(root.getAbsolutePath(), "target/classes");
    WebResourceRoot resources = new StandardRoot(ctx);

    WebResourceSet resourceSet;
    if (webInfClassesFolder.exists()) {
      resourceSet = new DirResourceSet(resources, "/WEB-INF/classes",
          webInfClassesFolder.getAbsolutePath(), "/");
      log.info("Loading WEB-INF Resources Path : '" + webInfClassesFolder.getAbsolutePath() + "'");
    } else {
      resourceSet = new EmptyResourceSet(resources);
    }
    resources.addPreResources(resourceSet);
    ctx.setResources(resources);

    // JNDI Name.
    tomcat.enableNaming();

    tomcat.start();

    tomcat.getServer().await();
  }

}
