package apartment.ejar.config;

import apartment.ejar.entities.*;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class DataRestConfig extends RepositoryRestMvcConfiguration {

    public DataRestConfig(ApplicationContext context, ObjectFactory<ConversionService> conversionService) {
        super(context, conversionService);
    }

    @Override
    public RepositoryRestConfiguration repositoryRestConfiguration() {
        return super.repositoryRestConfiguration().exposeIdsFor(Location.class).exposeIdsFor(Broker.class).exposeIdsFor(Role.class)
                .exposeIdsFor(Image.class).exposeIdsFor(Apartment.class).setReturnBodyOnCreate(true).setReturnBodyOnUpdate(true);
    }
//     @Bean
//    protected LinkCollector linkCollector() {
//        return new LinkCollector(persistentEntities(), selfLinkProvider(), associationLinks()) {
//            public Links getLinksFor(Object object, List<Link> existingLinks) {
//                return new Links();
//            }
//        };
//    }
}
