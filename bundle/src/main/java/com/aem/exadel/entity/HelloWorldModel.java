package com.aem.exadel.entity;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Interface.class},
        resourceType = {HelloWorldModel.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HelloWorldModel implements Interface {
    static final String RESOURCE_TYPE = "testprojekt/components/content/helloWorldModel";

    @Self
    @Required
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String resourceType;

    @OSGiService
    private ModelFactory modelFactory;

//    @Getter
//    @Setter
    @ValueMapValue
    private String message;

    @PostConstruct
    protected void init() {
        message = "\tHello World!\n";
        message += "\tResource type is: " + resourceType + "\n";
    }

    public String getMessage() {
        return message;
    }
}