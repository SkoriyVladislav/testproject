package com.aem.exadel.servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;


import javax.servlet.Servlet;
import java.io.IOException;

@Component(service=Servlet.class,
        property={
                Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                    "sling.servlet.resourceTypes="+ "AemTraining64/components/structure/main-page",
                "sling.servlet.extensions=" + "txt"
        })
public class Controller extends SlingAllMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.getWriter().print("STR");
        /*response.setHeader("Content-Type", "application/json");
        //response.getWriter().print(“{\”coming\” : \”soon\”}”);
        //String[] keys = repository.getDescriptorKeys();
        JSONObject jsonObject = new JSONObject();
        RSSReader rssReader = new RSSReaderImpl();
        List<DynamicCard> newsList = rssReader.readFeed();
        try {
            response.setStatus(200);
            jsonObject.put("DynamicCard", newsList);
            //jsonObject.put(keys[i], repository.getDescriptor(keys[i]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.getWriter().print(jsonObject.toString());*/
    }
}

