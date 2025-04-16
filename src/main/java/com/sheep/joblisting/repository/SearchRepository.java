package com.sheep.joblisting.repository;

import com.sheep.joblisting.controller.model.Post;

import java.util.List;

public interface SearchRepository {

    List<Post> findByText(String text);
}
