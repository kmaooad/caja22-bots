package edu.kmaooad.repository;

import edu.kmaooad.models.Expert;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpertsRepository extends MongoRepository<Expert, String> {
}
