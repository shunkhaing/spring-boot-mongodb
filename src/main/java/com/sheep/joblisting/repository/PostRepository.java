package com.sheep.joblisting.repository;

import com.sheep.joblisting.controller.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
