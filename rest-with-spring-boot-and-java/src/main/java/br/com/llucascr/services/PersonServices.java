package br.com.llucascr.services;

import br.com.llucascr.exception.ResourceNotFoundException;
import br.com.llucascr.model.Person;
import br.com.llucascr.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/*
* @Service = Cria um bean dessa classe e deixa disponivel na memoria*/
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class);

    @Autowired
    private PersonRepository repository;

    public Person create(Person person) {
        logger.info("Creating one Person");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one Person");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName() != null ? person.getFirstName() : entity.getFirstName());
        entity.setLastName(person.getLastName() != null ? person.getLastName() : entity.getLastName());
        entity.setAddress(person.getAddress() != null ? person.getAddress() : entity.getAddress());
        entity.setGender(person.getGender() != null ? person.getGender() : entity.getGender());

        return repository.save(entity);
    }

    public List<Person> findAll() {
        logger.info("Fiding all People!");
        return repository.findAll();
    }


    public void delete(Long id) {
        logger.info("Deleting one Person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(entity);
    }

    public Person findById(Long id) {
        logger.info("Finding one Person!");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

}
