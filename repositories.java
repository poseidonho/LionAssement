package com.assement.featureswtiches;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositories extends CrudRepository<features, Long> {
    features findByEmail(String email);

}