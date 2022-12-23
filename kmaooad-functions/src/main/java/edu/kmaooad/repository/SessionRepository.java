package edu.kmaooad.repository;


import edu.kmaooad.models.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, Long> {
}
