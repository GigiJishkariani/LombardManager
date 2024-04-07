package dev.boot.repository;

import dev.boot.domain.Jewelry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JewelryRepository extends CrudRepository<Jewelry, Long> {
}