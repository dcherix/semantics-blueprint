package config;

import org.openrdf.repository.Repository;
import org.openrdf.repository.sparql.SPARQLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import services.ConceptProvider;

/**
 * Created by didier on 04.02.16.
 */
@SpringBootApplication
public class Configuration {

    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    @Bean
    public Repository repository() {
        SPARQLRepository sparqlRepository = new SPARQLRepository("http://dbpedia.org/sparql");
        sparqlRepository.initialize();
        return sparqlRepository;
    }

    @Bean
    public ConceptProvider conceptProvider() {
        return new ConceptProvider(repository());
    }

    public static void main(String... args) {
        ApplicationContext context = SpringApplication.run(Configuration.class, args);
        context.getBean(ConceptProvider.class).dbpediaConcepts().stream().forEach(LOG::info);
    }
}
