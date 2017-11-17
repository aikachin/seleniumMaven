package com.aikachin.selenium;// import org.eclipse.jetty.server.Server;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import javax.servlet.Servlet;
import java.io.File;

// import org.eclipse.jetty.server.*;
// import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @Author: AIkachin
 * @Description:
 * @Date: Created in 16:24 2017/10/18 0018.
 * @Modified by :
 */
public class jettyEmbeddedSeleniumServer {
    public static void main(String args[]) throws Exception{
        Server server = new Server();

        WebAppContext context = new WebAppContext();
        context.setContextPath("");
        File st = new File(".");

        context.setWar(st.getPath());
        server.addHandler(context);

        context.addServlet(Servlet.class, "/wd/*");

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(3002);
        server.addConnector(connector);
        server.start();

    }
}
