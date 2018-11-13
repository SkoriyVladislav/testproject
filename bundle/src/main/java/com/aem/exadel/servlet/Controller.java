package com.aem.exadel.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.jcr.Repository;
import java.io.IOException;


@SlingServlet(paths="/bin/mySearchServlet", methods = "GET", metatype=true)
public class Controller extends SlingAllMethodsServlet {

    private Repository repository;

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

