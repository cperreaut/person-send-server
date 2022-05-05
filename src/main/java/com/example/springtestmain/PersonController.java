package com.example.springtestmain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping("/persons")
    public String persons(Model model){
        model.addAttribute("persons",repository.findAll());
        return "personList";
    }

    @GetMapping("/new_person")
    public String newPerson(Model model){
        RestTemplate restTemplate = new RestTemplate();
        Person newPerson = restTemplate.getForObject("http://server-app:8080/person", Person.class);
        repository.save(newPerson);
        return persons(model);
    }


}
