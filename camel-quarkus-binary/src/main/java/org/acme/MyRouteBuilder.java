package org.acme;

import org.apache.camel.CamelContext;
import org.apache.camel.ExtendedCamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Resource;
import org.apache.camel.spi.RoutesLoader;
import org.apache.camel.support.ResourceHelper;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MyRouteBuilder extends RouteBuilder {

    private static final Logger LOG = Logger.getLogger(MyMain.class);

    @Override
    public void configure() {
        CamelContext context = getContext();
        // Load file route.yaml as a

        List<String> args = MyMain.applicationArguments;

        String first = "route.yaml";
        if (args.size() > 0)
            first = args.get(0);

        Path fileName = Path.of(first);
        try {
            String myRoute = Files.readString(fileName);
            ExtendedCamelContext extendedCamelContext = context.adapt(ExtendedCamelContext.class);
            RoutesLoader loader = extendedCamelContext.getRoutesLoader();
            Resource resource = ResourceHelper.fromString("test.yaml", myRoute);
            loader.loadRoutes(resource);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

