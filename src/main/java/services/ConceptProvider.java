package services;

import org.openrdf.query.QueryResults;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.util.Repositories;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by didier on 04.02.16.
 */
public class ConceptProvider {
    private final Repository repository;

    public ConceptProvider(Repository repository) {
        this.repository = repository;
    }

    public List<String> dbpediaConcepts() {
        try (RepositoryConnection connection = repository.getConnection()) {
            TupleQueryResult result = connection.prepareTupleQuery("SELECT ?concept WHERE { [] a ?concept }").evaluate();
            return QueryResults.stream(result)
                    .map(bs -> bs.getValue("concept").stringValue())
                    .collect(Collectors.toList());
        }
    }
}
