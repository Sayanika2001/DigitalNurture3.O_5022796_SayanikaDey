--Scenario 1: The bank needs to process monthly interest for all savings accounts
-- Create table
CREATE TABLE Accounts (
  AccountID INT PRIMARY KEY,
  CustomerID INT,
  Balance DECIMAL(10, 2),
  AccountType VARCHAR(50)
);

-- Stored procedure to process monthly interest
DELIMITER //
CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
  UPDATE Accounts
  SET Balance = Balance * 1.01
  WHERE AccountType = 'Savings';
END;//
DELIMITER ;
--Scenario 2: The bank wants to implement a bonus scheme for employees based on their performance
-- Create table
CREATE TABLE Employees (
  EmployeeID INT PRIMARY KEY,
  Name VARCHAR(50),
  Department VARCHAR(50),
  Salary DECIMAL(10, 2)
);

-- Stored procedure to update employee bonus
DELIMITER //
CREATE PROCEDURE UpdateEmployeeBonus(IN department VARCHAR(50), IN bonusPercentage DECIMAL(4, 2))
BEGIN
  UPDATE Employees
  SET Salary = Salary * (1 + bonusPercentage / 100)
  WHERE Department = department;
END;//
DELIMITER ;
--Scenario 3: Customers should be able to transfer funds between their accounts  
-- Create table
CREATE TABLE Accounts (
  AccountID INT PRIMARY KEY,
  CustomerID INT,
  Balance DECIMAL(10, 2)
);

-- Stored procedure to transfer funds
DELIMITER //
CREATE PROCEDURE TransferFunds(IN sourceAccountID INT, IN targetAccountID INT, IN amount DECIMAL(10, 2))
BEGIN
  DECLARE sourceBalance DECIMAL(10, 2);
  SELECT Balance INTO sourceBalance
  FROM Accounts
  WHERE AccountID = sourceAccountID;

  IF sourceBalance >= amount THEN
    UPDATE Accounts
    SET Balance = Balance - amount
    WHERE AccountID = sourceAccountID;

    UPDATE Accounts
    SET Balance = Balance + amount
    WHERE AccountID = targetAccountID;
  ELSE
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance';
  END IF;
END;//
DELIMITER ;
