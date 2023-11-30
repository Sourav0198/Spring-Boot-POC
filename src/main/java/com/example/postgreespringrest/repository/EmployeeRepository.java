package com.example.postgreespringrest.repository;

import com.example.postgreespringrest.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @Modifying
    @Query("Delete from EmployeeEntity e where e.id between  1000 and 3000")
    void deleteByCustumQuery();

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM EmployeeEntity e WHERE e.id = :Id")


    boolean isEmployeeExistById(@Param("Id") Integer id);
}

