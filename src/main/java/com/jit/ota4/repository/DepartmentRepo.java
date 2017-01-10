package com.jit.ota4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jit.ota4.entity.Department;
@Repository
public interface DepartmentRepo extends BaseRepository<Department,String>{
	public Department findDepByDepNO(String depno);
	
	public Department findByDepNo(String depno);
	
	@Query("select d from Department d where d.depName like %?1%")
	public Department findByDepName(String depname);
	@Query(value= "select * from department d where d.depname like %?1%",nativeQuery=true)
	public List<Department> findByDepNameSQL(String depname);
}
