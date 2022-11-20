package edu.kmaooad.services;

import edu.kmaooad.models.Expert;
import edu.kmaooad.repository.ExpertsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertsService {
    private ExpertsRepository expertsRepository;

    @Autowired
    public ExpertsService(ExpertsRepository expertsRepository) {
        this.expertsRepository = expertsRepository;
    }

    public Expert createExpert(Expert expert){
        return expertsRepository.save(expert);
    }

    public void deleteExpert(Expert expert){
        expertsRepository.delete(expert);
    }
}
