package com.aem.exadel.service;

import com.adobe.cq.sightly.WCMUsePojo;
import com.aem.exadel.entity.ManualCard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.jcr.Node;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class HeroRestComponent extends WCMUsePojo {

    /** The hero text bean that stores values returned by the RestFul Web Service. */
    private ManualCard manualCard = null;

    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public void activate() throws Exception {
        Node currentNode = getResource().adaptTo(Node.class);

        manualCard = new ManualCard();

        String link = null;
        if(currentNode.hasProperty("jcr:link")){
            link = currentNode.getProperty("./jcr:link").getString();
        }

        StringReader ss = new StringReader(getCardJSON(link));
    }



    public ManualCard getManualCard() {
        return this.manualCard;
    }


    //Invokes a third party Restful Web Service and returns the results in a JSON String
    private static String getCardJSON(String link) {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet(link);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            String myJSON="" ;
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                myJSON = myJSON + output;
            }


            httpClient.getConnectionManager().shutdown();
            return myJSON ;
        }

        catch (Exception e) {
            e.printStackTrace() ;
        }

        return null;
    }

}
