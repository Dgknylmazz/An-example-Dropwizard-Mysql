package com.alkimsoft.sandbox;

import com.alkimsoft.sandbox.dao.dao.UserDAO;
import com.alkimsoft.sandbox.representation.entities.User;
import com.alkimsoft.sandbox.resource.AuthResource;
import com.alkimsoft.sandbox.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by gunerkaanalkim on 14/03/16.
 */
public class App extends Application<ProjectConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private final HibernateBundle<ProjectConfiguration> hibernateBundle = new HibernateBundle<ProjectConfiguration>(
            User.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(ProjectConfiguration projectConfiguration) {
            return projectConfiguration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(io.dropwizard.setup.Bootstrap<ProjectConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(ProjectConfiguration yollandoConfiguration, Environment environment) throws Exception {
        LOGGER.info("Services are run.");

        //  CORS Settings
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        //  CORS Settings

        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new UserResource(userDAO));
        environment.jersey().register(new AuthResource(userDAO));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
