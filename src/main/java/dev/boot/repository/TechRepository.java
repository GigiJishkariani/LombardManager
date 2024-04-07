package dev.boot.repository;

import dev.boot.domain.Tech;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechRepository extends CrudRepository<Tech, Long> {
}
