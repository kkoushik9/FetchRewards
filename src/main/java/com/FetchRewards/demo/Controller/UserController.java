package com.FetchRewards.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FetchRewards.demo.Entity.Balance;
import com.FetchRewards.demo.Entity.DeductedBalance;
import com.FetchRewards.demo.Entity.Points;
import com.FetchRewards.demo.Entity.Transaction;
import com.FetchRewards.demo.Service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

   
   @Autowired
  	private UserService userService;
	
	
	
   	 @PostMapping(value = "/deductPoints")	
   	public ResponseEntity<List<DeductedBalance>> deductPoints(@RequestBody Points points){
   		 
		 return userService.deductPoints(points.getPoints());
	 }
	 
   	@PostMapping(value = "/addTransaction")	 
   	public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction){
   		return userService.addTransaction(transaction);
   	}
   	
   	@GetMapping(value = "/getAllPayersBalance")	
   	public ResponseEntity<List<Balance>> getAllPayersBalance(){
   		return userService.getAllPayersBalance();
   	}
	
}
