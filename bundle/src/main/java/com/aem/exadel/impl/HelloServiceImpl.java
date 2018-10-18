package com.aem.exadel.impl;

import javax.jcr.Repository;

import org.apache.sling.jcr.api.SlingRepository;

import com.aem.exadel.HelloService;
import org.osgi.service.component.annotations.Reference;

/**
 * One implementation of the {@link HelloService}. Note that
 * the repository is injected, not retrieved.
 */

public class HelloServiceImpl implements HelloService {
    
    @Reference
    private SlingRepository repository;

    public String getRepositoryName() {
        return repository.getDescriptor(Repository.REP_NAME_DESC);
    }

    public String echo(String str) {
        return str.toUpperCase();
    }
}
