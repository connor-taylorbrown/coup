package coup.restful;

import coup.provider.JsonMoxyConfigurationContextResolver;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.xml.bind.JAXBException;

/**
 * Registry for Jersey service handlers.
 */
public class ResourceConfiguration extends ResourceConfig {
    public ResourceConfiguration() throws JAXBException {
        packages("coup.restful");
        register(NetworkedGame.class);
        register(MoxyJsonFeature.class);
        register(JsonMoxyConfigurationContextResolver.class);
    }
}
