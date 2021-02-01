package com.FetchRewards.demo.Service.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Service;

import com.FetchRewards.demo.Entity.Transaction;
import com.FetchRewards.demo.Service.InitializerService;
import com.FetchRewards.demo.Service.UserService;


@Service
public class InitializerServiceImpl implements InitializerService{

	
	@Autowired
	UserService userService;
	
	@Override
	public void populateInitialData() {
		// TODO Auto-generated method stub
		userService.addTransaction(new Transaction("dannon", 300, LocalDateTime.parse("2020-11-02T14:15:30")));
		userService.addTransaction(new Transaction("unilever", 200, LocalDateTime.parse("2020-11-02T14:15:30")));
		userService.addTransaction(new Transaction("dannon", -200, LocalDateTime.parse("2020-11-02T14:15:30")));
		userService.addTransaction(new Transaction("miller coors", 10000, LocalDateTime.parse("2020-11-02T14:15:30")));
		userService.addTransaction(new Transaction("dannon", 1000, LocalDateTime.parse("2020-11-02T14:15:30")));
	}
	
	

}
