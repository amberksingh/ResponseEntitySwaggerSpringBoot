package com.example.demo.controller;

import com.example.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    private ConcurrentHashMap<String, Person> persons = new ConcurrentHashMap<String, Person>();

    @GetMapping("")
    public List<Person> getPersons() {

        ArrayList<Person> list = new ArrayList<>(persons.values());

        log.info("Persons list : " + list);

        return list;
    }

    @PostMapping()
    public Person addPerson(@RequestBody Person person) {

        log.info("Person added : " + person);

        return persons.put(person.getPersonId(), person);

    }

    @GetMapping("{id}")
    public ResponseEntity<String> getPersonNameUsingId(@PathVariable String id) {

        //String personName = persons.get(id).getPersonName();

        if (persons.containsKey(id)) {
            String personName = persons.get(id).getPersonName();
            log.info("Person Name : " + personName);

            return new ResponseEntity<>("Person name is " +personName, HttpStatus.OK);
        } else {
            log.info("Person Name not found : ");
            return new ResponseEntity<>("Person name Not Found ", HttpStatus.BAD_REQUEST);
        }

    }
}
