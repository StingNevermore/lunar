package com.nevermore.lunar.server.controller;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author nevermore
 */
@Controller
@RequestMapping("camunda")
public class CamundaController {

    private final RuntimeService runtimeService;
    private final RepositoryService repositoryService;
    private final TaskService taskService;

    public CamundaController(RuntimeService runtimeService, RepositoryService repositoryService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
        this.taskService = taskService;
    }

    @RequestMapping("test")
    @ResponseBody
    public ResponseEntity<?> test() {
//        Deployment deploy = repositoryService.createDeployment().addClasspathResource("payment.bpmn").deploy();
        ProcessInstance process = runtimeService.startProcessInstanceByKey("payment-retrieval");
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(process.getProcessInstanceId()).active().list();
        return ok().build();
    }

}
