package de.othr.grn.ui.model;

import de.othr.grn.service.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class initModel {
    @Inject
    private InitService initService;

    public InitService getInitService() {
        return initService;
    }

    public void setInitService(InitService initService) {
        this.initService = initService;
    }
}
