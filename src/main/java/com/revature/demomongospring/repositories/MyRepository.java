package com.revature.demomongospring.repositories;

import com.revature.demomongospring.models.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends JpaRepository<Associate, Integer> {
    //nope
}
