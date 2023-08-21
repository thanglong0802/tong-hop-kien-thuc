package com.dagoras.edu.api.repository;

import com.dagoras.edu.api.entity.Job;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends ElasticsearchRepository<Job, String> {
}
