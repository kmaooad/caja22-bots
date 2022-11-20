package edu.kmaooad.repository;

import edu.kmaooad.models.Expert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertsRepository extends MongoRepository<Expert, String> {
}
