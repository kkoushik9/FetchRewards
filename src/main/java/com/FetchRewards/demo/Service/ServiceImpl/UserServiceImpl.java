package com.FetchRewards.demo.Service.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.FetchRewards.demo.Entity.Balance;
import com.FetchRewards.demo.Entity.DeductedBalance;
import com.FetchRewards.demo.Entity.Transaction;
import com.FetchRewards.demo.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	Map<String, List<Integer>> userTransactionMap = new HashMap<>();
	Map<Integer, Transaction> transactionIdMap = new LinkedHashMap<>();
	
	static int availableBalance;
	static int id=0;	

public ResponseEntity<String> addTransaction(Transaction transaction) {
		
		if(userTransactionMap.containsKey(transaction.getName())) {
			
			List<Integer> userIdList= userTransactionMap.get(transaction.getName());
			if(transaction.getPoints()<0) {
				
				int amount = -1*transaction.getPoints();
				
				int balance  =  balance(transaction.getName()).getBody();
				
				if(balance<amount) return new ResponseEntity<String>("Transaction Unsucessful", HttpStatus.BAD_REQUEST);

				
				List<Integer> idToRemove= new ArrayList<>();
				
				for(int i : userIdList) {
					Transaction  trans  = transactionIdMap.get(i);
					if(trans.getPoints()>=amount) {
						trans.setPoints(trans.getPoints()-amount);
						transactionIdMap.put(i, trans);
						break;
					}
					else {
						amount-=trans.getPoints();
						trans.setPoints(0);
						transactionIdMap.put(i, trans);
					}
					
					if(trans.getPoints()==0) {
						transactionIdMap.remove(i);
						idToRemove.add(i);
					}
				}
				
				for(int i : idToRemove) {
					userIdList.remove(new Integer(i));
				}
				
				userTransactionMap.put(transaction.getName(), userIdList);
				
				availableBalance+=transaction.getPoints();
			}
			else {
				userIdList.add(++id);
				userTransactionMap.put(transaction.getName(), userIdList);
				transactionIdMap.put(id, transaction);
				availableBalance+=transaction.getPoints();
				
			}			
		}
		else {
			if(transaction.getPoints()<=0) return new ResponseEntity<String>("Transaction Unsucessful", HttpStatus.BAD_REQUEST);
			
			List<Integer> userIdList= new ArrayList<>();
			userIdList.add(++id);
			userTransactionMap.put(transaction.getName(), userIdList);
			transactionIdMap.put(id, transaction);
			availableBalance+=transaction.getPoints();
		}
		
		
		return new ResponseEntity<String>("Transaction Sucessful", HttpStatus.OK);

	}

public ResponseEntity<List<DeductedBalance>> deductPoints(int points) {
	
	List<DeductedBalance> deductedBalanceList = new ArrayList<DeductedBalance>();
	if(points>availableBalance) return new ResponseEntity<List<DeductedBalance>>(deductedBalanceList, HttpStatus.BAD_REQUEST);
	
	List<Integer> removeIds = new ArrayList<>();
	for(int i : transactionIdMap.keySet()) {
		
		if(transactionIdMap.get(i).getPoints()>=points) {
			
			deductedBalanceList.add(new DeductedBalance(transactionIdMap.get(i).getName(), -1*points, LocalDateTime.now())); 
			
			System.out.println(transactionIdMap.get(i).getName() + ", -" + points+ ", "+LocalDateTime.now());
			
			transactionIdMap.get(i).setPoints(transactionIdMap.get(i).getPoints()-points);
			if(transactionIdMap.get(i).getPoints()==0) {
				userTransactionMap.get(transactionIdMap.get(i).getName()).remove(new Integer(i));
				removeIds.add(i);
			}
			break;
		}
		else {
			if(transactionIdMap.get(i).getPoints()>0) {
				deductedBalanceList.add(new DeductedBalance(transactionIdMap.get(i).getName(), -1*transactionIdMap.get(i).getPoints(), LocalDateTime.now())); 
				System.out.println(transactionIdMap.get(i).getName() + ", -" + transactionIdMap.get(i).getPoints()+ ", "+LocalDateTime.now());
				points-=transactionIdMap.get(i).getPoints();
				transactionIdMap.get(i).setPoints(0);
				userTransactionMap.get(transactionIdMap.get(i).getName()).remove(new Integer(i));
				removeIds.add(i);	
			}				
		}	
		
	}
	
	for(int i : removeIds) {
		transactionIdMap.remove(i);
	}
	availableBalance-=points;
	
	return new ResponseEntity<List<DeductedBalance>>(deductedBalanceList, HttpStatus.OK);
}

public ResponseEntity<List<Balance>> getAllPayersBalance() {
	
	List<Balance> allUsersbalance = new ArrayList<Balance>();
	for(String payee : userTransactionMap.keySet()) {
		int amount=0;
		List<Integer> ids = userTransactionMap.get(payee);
		
		for(int id : ids) {
			amount+= transactionIdMap.get(id).getPoints();
		}
		
		allUsersbalance.add(new Balance(payee, amount));
		
		System.out.println(payee +" : " + amount);
	}
	
	return new ResponseEntity<List<Balance>>(allUsersbalance,HttpStatus.OK);
}



private ResponseEntity<Integer> balance(String user) {
	List<Integer> ids = userTransactionMap.get(user);
	
	int amount=0;
	for(int id : ids) {
		amount+= transactionIdMap.get(id).getPoints();
	}
	
	return new ResponseEntity<Integer>(amount, HttpStatus.OK);
}
	
	

}
