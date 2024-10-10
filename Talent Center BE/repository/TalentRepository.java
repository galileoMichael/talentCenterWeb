package com.tujuhsembilan.talentcenter.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.talentcenter.model.Talent;

import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface TalentRepository extends JpaRepository<Talent, UUID>, JpaSpecificationExecutor<Talent> {

    long count(Specification<Talent> specification);
}
