package com.java5.slide6.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java5.slide6.entity.Clazz;

public interface ClazzDAO extends JpaRepository<Clazz, Long>{
	List<Clazz> findByNumberOfStudentsBetween(Integer min, Integer max);
	
	@Query("SELECT c FROM Clazz c WHERE c.name=:Name")
	List<Clazz> FindByName(@Param("Name") String name);
	
	@Query("SELECT c FROM Clazz c WHERE c.numberOfStudents BETWEEN :min AND :max")
	List<Clazz> FindBetwenMinAndMax(@Param("min")int min,@Param("max")int max);
	
	
	@Query(value="SELECT * FROM Clazz WHERE semester=:sem",nativeQuery=true)
	List<Clazz> GetClassListBySemester(@Param("sem") String sem);
	
	@Query(name="FindByNameAndBySemester")
	List<Clazz> FindByNameAndBySemester(@Param("name") String name,@Param("sem") String sem);
}
