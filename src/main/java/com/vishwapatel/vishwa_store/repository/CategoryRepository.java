package com.vishwapatel.vishwa_store.repository;

import com.vishwapatel.vishwa_store.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
