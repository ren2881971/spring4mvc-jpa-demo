package com.jit.ota4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jit.ota4.entity.Staff;

@Repository
public interface StaffRepo extends BaseRepository<Staff,String>{
	@Query("select s.staffName,s.keyId from Staff s where s.depId = ?1")
	List<Object[]> getStaffNameByDepId(String depId);
	@Query("select s from Staff s,Department d where s.depId = d.keyId and d.keyId = ?1")
	List<Staff> getStaffByDepId(String depId);
}
