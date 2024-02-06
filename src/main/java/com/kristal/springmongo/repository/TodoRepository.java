package com.kristal.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kristal.springmongo.model.TodoModel;

@Repository
public interface TodoRepository extends MongoRepository<TodoModel, String> {

}