package edu.northeastern.cs5200.controllers.hello;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<HelloObject, Integer> {
}

