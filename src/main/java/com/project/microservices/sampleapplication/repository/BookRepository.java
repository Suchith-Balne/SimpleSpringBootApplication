package com.project.microservices.sampleapplication.repository;

import com.project.microservices.sampleapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
