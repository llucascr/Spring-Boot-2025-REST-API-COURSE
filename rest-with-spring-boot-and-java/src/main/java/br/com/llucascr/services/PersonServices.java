package br.com.llucascr.services;

import br.com.llucascr.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/*
* @Service = Cria um bean dessa classe e deixa disponivel na memoria*/
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        logger.info("Finding one Person!");

        // Mock
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Lucas");
        person.setLastName("De Campos");
        person.setAddress("Cosmpolis - SP - Brasil");
        person.setGender("Male");

        return person;
    }

}
