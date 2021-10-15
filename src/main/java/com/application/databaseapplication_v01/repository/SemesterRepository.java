package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    /*@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);*/

    @Query("SELECT sm FROM Semester sm WHERE sm.active = ?1")
    public Semester getActiveSemester(Integer num);

}
