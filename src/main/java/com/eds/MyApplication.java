package com.eds;

import com.eds.controller.MainController;
import com.eds.controller.api.AdditionalApiController;
import com.eds.controller.api.ApiController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> h = new HashSet<>();
        h.add( MainController.class );
        h.add( ApiController.class );
        h.add( AdditionalApiController.class );
        return h;
    }
}