package org.acme;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.RouteDefinitionHelper;
import org.apache.camel.model.RoutesDefinition;
import org.apache.camel.quarkus.main.CamelMainApplication;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

@QuarkusMain
public class MyMain {

    private static final Logger LOG = Logger.getLogger(MyMain.class);
    private static String greeting;

    public static List<String> applicationArguments = null;

    public static void main(String... args) {
        /* Any custom logic can be implemented here
         * Here, we pass the value of the first argument to Quarkus CDI container so that it can be injected using
         * @Inject @Named("greeting")
         * And we pass the second argument as -durationMaxMessages which is the number of messages that the application
         * will process before terminating */
        List<String> filteredArgs = new ArrayList<>(args.length);

        //filteredArgs.add("\\home\\projects\\camelnodejs\\camel-quarkus-binary\\route.yaml");

        System.out.println("");
        System.out.println("");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            filteredArgs.add(args[i++]);
        }
        System.out.println("");
        System.out.println("");

        applicationArguments = filteredArgs;

        /*
        DefaultCamelContext context = new DefaultCamelContext();
        // append the routes to the context
        String myString = "- route:\n" +
                "    from: \"timer:yaml?period=3s\"\n" +
                "    steps:\n" +
                "      - set-body:\n" +
                "          simple: \"Timer fired ${header.CamelTimerCounter} times\"\n" +
                "      - to:\n" +
                "          uri: \"log:yaml\"\n" +
                "          parameters:\n" +
                "            show-body-type: false\n" +
                "            show-exchange-pattern: false\n" +
                "\n";
        ;

        try {
            //InputStream is = new ByteArrayInputStream(myString.getBytes());
            //RouteDefinition d = null;
            context.addRoutes(new RouteBuilder() {
                public void configure() throws Exception {
                    from("timer:yaml?period=3s").to("file://test");
                }
            });
            System.out.println("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Quarkus.run(CamelMainApplication.class, filteredArgs.toArray(new String[filteredArgs.size()]));
    }
}
