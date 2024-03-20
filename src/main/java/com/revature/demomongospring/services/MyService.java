package com.revature.demomongospring.services;

import com.revature.demomongospring.exceptions.ItemNotFoundException;
import com.revature.demomongospring.models.Associate;
import com.revature.demomongospring.repositories.AssociateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private AssociateRepository repository;

    @Autowired
    public MyService(AssociateRepository repository) {
        System.out.println("Service Constructor");
        this.repository = repository;
    }

    public Associate findById(ObjectId id) throws ItemNotFoundException {
        /*
        This returns our result of one exists, otherwise it throws ItemNotFound
         */
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("Query returned no result."));
    }


    public Associate findByFirstName(String firstName) throws ItemNotFoundException{
        return repository.findByFirstName(firstName).orElseThrow(() -> new ItemNotFoundException("Query returned no result."));
    }


}
