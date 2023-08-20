package com.dagoras.edu.api.repository;

import com.dagoras.edu.api.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    Student findByPhoneNumber(String phoneNumber);
    Page<Student> findAllByNameContaining(String textSearch, Pageable pageable);
    @Query(value = "select count(st.id) from tbl_student st where st.is_delete = 0", nativeQuery = true)
    Integer countStudentById();
    @Query(value = "select count(t.id) from tbl_teacher t where t.is_delete = 0", nativeQuery = true)
    Integer countTeacherById();
    Integer countStudentByAge(Integer age);
    @Query(value = "select count(st.id) from tbl_student st inner join tbl_student_subject ss on st.id = ss.student_id " +
            "inner join tbl_subject su on ss.subject_id = su.id " +
            "where su.name like concat('%', :subjectName, '%')", nativeQuery = true)
    Integer studyTheSameSubject(@Param("subjectName") String subjectName);
}
