package com.zyka.spring.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.zyka.spring.test.model.Operation;
import com.zyka.spring.test.model.Task;
import com.zyka.spring.test.repositories.OperationRepository;
import com.zyka.spring.test.repositories.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JsonPatchTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RepositoryEntityLinks entityLinks;

    @Test
    public void testJsonPatch() throws Exception {

        Task task1 = taskRepository.save(new Task("task1", 1.5));
        Task task2 = taskRepository.save(new Task("task2", 2.5));

        Operation operation = operationRepository.save(new Operation(task1, "operation1"));

        mockMvc.perform(
                    patch(entityLinks.linkToSingleResource(Operation.class, operation.getId()).getHref())
                        .content("[{ \"op\": \"replace\", \"path\": \"task\", \"value\" : \"" + entityLinks.linkToSingleResource(Task.class, task2.getId()).getHref() + "\"}]")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType("application/json-patch+json"))
                .andDo(print())
                .andExpect(
                    status().isOk());

        operation = operationRepository.findOne(operation.getId());

        Assert.assertEquals(task2.getId(), operation.getTask().getId());
    }

}
