package com.FetchRewards.demo.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.FetchRewards.demo.Entity.Balance;
import com.FetchRewards.demo.Entity.DeductedBalance;
import com.FetchRewards.demo.Entity.Transaction;

public interface UserService {

	public ResponseEntity<String> addTransaction(Transaction transaction);
	
	public ResponseEntity<List<DeductedBalance>> deductPoints(int points);
	
	public ResponseEntity<List<Balance>> getAllPayersBalance();
	
}
