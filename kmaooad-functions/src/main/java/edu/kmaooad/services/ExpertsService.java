package edu.kmaooad.services;

import edu.kmaooad.models.Expert;

public interface ExpertsService {

    Expert createExpert(Expert expert);

    void deleteExpert(Expert expert);
}
