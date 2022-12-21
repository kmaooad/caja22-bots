package edu.kmaooad.services;

import edu.kmaooad.models.Expert;
import edu.kmaooad.repository.ExpertsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertsServiceImpl implements ExpertsService {
    private final ExpertsRepository expertsRepository;

    @Autowired
    public ExpertsServiceImpl(ExpertsRepository expertsRepository) {
        this.expertsRepository = expertsRepository;
    }
    @Override
    public Expert createExpert(Expert expert){
        return expertsRepository.save(expert);
    }

    @Override
    public void deleteExpert(Expert expert){
        expertsRepository.delete(expert);
    }
}
