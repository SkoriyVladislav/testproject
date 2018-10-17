package com.aem.exadel.servlet;

import com.aem.exadel.entity.News;
import com.aem.exadel.service.RSSReader;
import com.aem.exadel.service.impl.RSSReaderImpl;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import javax.jcr.Repository;
import java.io.IOException;
import java.util.List;


@SlingServlet(paths = "/bin/company/repo", methods = HttpConstants.METHOD_GET, metatype = true)
public class Controller extends SlingAllMethodsServlet {

    @Reference
    private Repository repository;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json");
        //response.getWriter().print(“{\”coming\” : \”soon\”}”);
        //String[] keys = repository.getDescriptorKeys();
        JSONObject jsonObject = new JSONObject();
        RSSReader rssReader = new RSSReaderImpl();
        List<News> newsList = rssReader.readFeed();
        try {
            response.setStatus(200);
            jsonObject.put("News", newsList);
            //jsonObject.put(keys[i], repository.getDescriptor(keys[i]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.getWriter().print(jsonObject.toString());
    }
}

