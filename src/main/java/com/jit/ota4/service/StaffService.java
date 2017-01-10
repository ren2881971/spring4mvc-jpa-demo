package com.jit.ota4.service;

import org.springframework.stereotype.Service;

import com.jit.ota4.entity.Staff;


public interface StaffService {
	public Staff saveStaff(Staff staff);
	public Staff getStaff(String keyId);
}
