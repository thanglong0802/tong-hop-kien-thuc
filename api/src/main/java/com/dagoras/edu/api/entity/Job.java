package com.dagoras.edu.api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    private String id;
    private Integer countStudent;
    private Integer countTeacher;
    private Integer numberOfPeopleOfTheSameAge;
    private Integer studyTheSameSubject;
}
