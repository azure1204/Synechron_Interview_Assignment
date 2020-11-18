package com.assignment.assignmentproj.repository;

import com.assignment.assignmentproj.model.CurrencyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyRate,String> {

}
