package com.brianxia.dockerdeploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DockerDeployApplication {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
    public static void main(String[] args) {
        SpringApplication.run(DockerDeployApplication.class, args);
    }

}
