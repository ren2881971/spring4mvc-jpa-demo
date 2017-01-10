package com.jit.ota4.service.impl;

import com.jit.ota4.entity.Department;
import com.jit.ota4.repository.DepartmentRepo;
import com.jit.ota4.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/8.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo depRepo;

    @Override
    public Department saveDep(Department dep) {
        return depRepo.save(dep);
    }
}
