package com.Sinema.demo.tokens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationTokenRepository extends JpaRepository<ValidationToken, Long> {
}
