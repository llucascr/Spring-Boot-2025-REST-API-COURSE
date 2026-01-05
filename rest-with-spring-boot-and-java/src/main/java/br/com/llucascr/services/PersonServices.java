package br.com.llucascr.services;

import br.com.llucascr.data.dto.PersonDTO;
import br.com.llucascr.exception.RequiredObjectIsNullException;
import br.com.llucascr.exception.ResourceNotFoundException;
import br.com.llucascr.model.Person;
import br.com.llucascr.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.llucascr.mapper.ObjectMapper.parseListObjects;
import static br.com.llucascr.mapper.ObjectMapper.parseObject;

/*
* @Service = Cria um bean dessa classe e deixa disponivel na memoria*/
@Service
public class PersonServices {

    private Logger logger = LoggerFactory.getLogger(PersonServices.class);

    @Autowired
    private PersonRepository repository;

    public Page<PersonDTO> findByName(String firstName, Pageable pageable) {
        Page<Person> personPage = repository.findPeopleByName(firstName, pageable);

        return personPage.map(person -> parseObject(person, PersonDTO.class));
    }

    public Page<PersonDTO> findAll(Pageable pageable) {
        logger.info("Fiding all People!");

        var personPage = repository.findAll(pageable);

        return personPage.map(person -> {
            return parseObject(person, PersonDTO.class);
        });
    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");

        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        return parseObject(person, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Creating one Person");

        if (personDTO == null) throw new RequiredObjectIsNullException();

        Person person = parseObject(personDTO, Person.class);
        return parseObject(repository.save(person), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Updating one Person");

        if (personDTO == null) throw new RequiredObjectIsNullException();

        Person entity = repository.findById(personDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(personDTO.getFirstName() != null ? personDTO.getFirstName() : entity.getFirstName());
        entity.setLastName(personDTO.getLastName() != null ? personDTO.getLastName() : entity.getLastName());
        entity.setAddress(personDTO.getAddress() != null ? personDTO.getAddress() : entity.getAddress());
        entity.setGender(personDTO.getGender() != null ? personDTO.getGender() : entity.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(entity);
    }

    @Transactional
    public PersonDTO disablePerson(Long id) {
        logger.info("Disable one Person!");

        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        repository.disablePerson(id);

        Person person = repository.findById(id).get();
        return parseObject(person, PersonDTO.class);
    }

}
