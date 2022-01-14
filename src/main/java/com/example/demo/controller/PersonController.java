package com.example.demo.controller;

import com.example.demo.model.CreditCard;
import com.example.demo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    private ConcurrentHashMap<String, Person> persons = new ConcurrentHashMap<String, Person>();

//    @Autowired
//    private CreditCard creditCard;

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

    @PostMapping("/addMultiplePerson")
    public List<Person> addMultiplePerson(@RequestBody List<Person> persons) {

        persons.stream().forEach(p -> this.persons.put(p.getPersonId(), p));

        List<Person> list = new ArrayList<>();

        this.persons.forEach( (k,v) -> {
            list.add(v);
        });

        //Collection<Person> values = this.persons.values();

        log.info("Persons added : " + list);

        return list;

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

    @GetMapping("/credit-card/{id}")
    public ResponseEntity<CreditCard> getCreditCardDetailsUsingId(@PathVariable String id) {

        //String personName = persons.get(id).getPersonName();

        if (persons.containsKey(id)) {
            CreditCard creditCard = persons.get(id).getCreditCard();
            log.info("CreditCard Details : " + creditCard);

            return new ResponseEntity<>(creditCard, HttpStatus.OK);
        } else {
            log.info("Person/CreditCard Details Not found : ");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
