package com.jit.ota4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jit.ota4.entity.Staff;
import com.jit.ota4.repository.StaffRepo;
import com.jit.ota4.service.StaffService;
@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffRepo staffRepo;
	@Override
	public Staff saveStaff(Staff staff) {
		return staffRepo.save(staff);
	}
	@Override
	public Staff getStaff(String keyId) {
		return staffRepo.findOne(keyId);
	}

}
