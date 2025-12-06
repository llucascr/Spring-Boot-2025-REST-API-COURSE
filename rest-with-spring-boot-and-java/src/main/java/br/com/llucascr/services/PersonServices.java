package br.com.llucascr.services;

import br.com.llucascr.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/*
* @Service = Cria um bean dessa classe e deixa disponivel na memoria*/
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person) {
        logger.info("Creating one Person");

        // Mock

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one Person");

        // Mock

        return person;
    }

    public List<Person> findAll() {
        logger.info("Fiding all People!");
        //Mock
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }


    public void delete(String id) {
        logger.info("Deleting one Person!");

    }

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

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName" + i);
        person.setLastName("LastName" + i);
        person.setAddress("Some Address in Brasil");
        person.setGender("Male");
        return person;
    }

}
