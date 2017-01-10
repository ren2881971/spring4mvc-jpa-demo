package test.com.jit.ota4.service.impl; 

import com.jit.ota4.configuration.WebAppConfig;
import com.jit.ota4.entity.Department;
import com.jit.ota4.entity.Staff;
import com.jit.ota4.repository.BaseSpecification;
import com.jit.ota4.repository.DepartmentRepo;
import com.jit.ota4.repository.SearchCriteria;
import com.jit.ota4.repository.StaffRepo;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/** 
* DepartmentServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 8, 2017</pre> 
* @version 1.0 
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebAppConfig.class)
@WebAppConfiguration
@Transactional
public class DepartmentServiceImplTest {

    @Autowired
    DepartmentRepo repo;
    @Autowired
    StaffRepo staffRepo;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: saveDep(Department dep) 
*
*/


    public void testSpecification() throws Exception {
        BaseSpecification sp1 = new BaseSpecification<Department>(new SearchCriteria("depName",":","住建厅"));
        List<Department> dep = repo.findAll(Specifications.where(sp1));
        for(Department d:dep){
            System.out.println(d.getDepNo());
        }
    }

    @Test
    public void testStaff() throws Exception{
        String sql = "select s from Staff s ,Department d where d.keyId = s.depId and d.keyId = ?1";
        List<Object> param = new ArrayList<Object>();
        param.add("402881e8598399fa0159839cf4bc0002");
        List<Staff> list = staffRepo.findByHqlWithPage(sql, param, Staff.class,1, 2);

        for(Staff s: list){
            System.out.println(s.getStaffName());
        }

    }



}
