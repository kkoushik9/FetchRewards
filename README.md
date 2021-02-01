# Fetch Rewards Coding Exercise

## Venkata Sai Koushik Koritala

This is for the Fetch Rewards Coding Exercise. The Application was developed in spring boot.

<br/>

**MY ASSUMPTION FOR THE EXERCISE :**

- All the points given to a player are in the Integer Format.
- Date should be in the format LocalDateTime.
- Name should be in the format of STRING.
- None of the points given by a payer will go negative.
- When a payer say, Payer1 has an existing transaction with +300 points and then Later If the same payer Payer1 wants to take away some points from his already existing transactions, he can do it but without his Transactions going Negative. That is, Now Payer1 already has a transaction with +300. Now he can make a point take away for until -300 but not more. And Only he can take away the points that he has given.
- When a user decides to use his points then he can only use the points that are available to him.
- When a user uses his points then the points are reduced from the transactions in a cronological order. That is, Even when a payer having two different transactions, First the points will be deducted from the first transaction then we check if we have any other transactions that we can point, which came after thr first transactrion, Then only we proceed for the other transaction of the same payer.

<br/>

**Running the Application:**

- Clone the project in a local repository, Import the project as maven project into eclipse, Right click on the project and run it as spring boot application.

<br/>
<br/>

**This Routes Created for:**

1. Add points to user account for specific payer and date.
2. Deduct points from the user account using above constraints and return a list of [payer, points deducted] for each call to spend points.
3. Return point balance per user that would list all positive points per payer.

<br/>

## **Using the API with `POSTMAN`**

---

## 1. Add Points to the User

<br/>

### Endpoint: `/addTransaction`

- This is a `POST` request. <br/>
  We can use the following Request Body:
  <br/><br/>

 ```Java
    {
        "name"   : << Payer_Name_As_STRING >>,
        "points" : << Points_As_INTEGER >>,
        "date"   : << Date_As_LocalDateTime >>
    }
 ```

  <br/>

- Give all the details of the transaction that you want to add in the format mentioned above.
- The name of the payer is to be given as a STRING
- The points of the payer are given as INTEGER.
- If the transaction is successful them the result will be returned as "Transaction Sucessful", else "Transaction Unsucessful."
- We can have transactions with negative points for a particular user which will deduct the points given by the same user.
  <br/>
  <br/>

---

## 2. View Point Balance

<br/>

### Endpoint: `/getAllPayersBalance`

- This is a `GET` request. <br/>
- This API cal gives all the POINTS of the user along with the payer name.
- Then out put will be in a JSON format

<br/>

```Java
    [
     {
        "payerName"   : << Payer_Name_As_STRING >>,
        "balance"     : << Balance_As_INTEGER >>
     }
    ]
```

<br/>

- This API call will only give the payer name and the total number of points that the payer has offered.

<br/>
<br/>

---

## 3. Deduct points from the User

<br/>

### Endpoint: `/deductPoints`

- This is a `POST` request. <br/>
  We can use the folloeing Request Body:
  <br/><br/>

 ```Java
    {
        "points" : << Points_As_INTEGER >>
    }
 ```

  <br/>

- We deduct the points from each transaction in a cronological order.
- The output will be in the following format.

 ```Java
      [
      {
          "payerName"   : << Payer_Name_As_STRING >>,
          "balance"     : << Balance_Deducted_As_INTEGER >>,
          "time"        : << Time_As_LocalDateTime  >>
      }
      ]
 ```

  <br/>
  <br/>
