package edu.kmaooad.services;

import edu.kmaooad.models.Expert;
import edu.kmaooad.repository.ExpertsRepository;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ExpertServiceTest {

    private final ExpertsRepository expertsRepository = mock(ExpertsRepository.class);
    private final ExpertsService expertsService = new ExpertsServiceImpl(expertsRepository);

    @Test
    public void shouldCreateExpert() {
        when(expertsRepository.save(any()))
                .then(invocationOnMock -> invocationOnMock.getArgument(0));

        Expert expert = new Expert("email", "type", "name");
        Expert result = expertsService.createExpert(expert);

        assertEquals(expert, result);
        verify(expertsRepository, times(1))
                .save(expert);
    }

    @Test
    public void shouldDeleteExpert() {
        expertsService.deleteExpert(any());
        verify(expertsRepository, times(1))
                .delete(any());
    }


}
