--Scenario 1: Calculate the age of customers for eligibility checks
-- Create table
CREATE TABLE Customers (
  CustomerID INT PRIMARY KEY,
  Name VARCHAR(50),
  DateOfBirth DATE
);

-- Function to calculate age
DELIMITER //
CREATE FUNCTION CalculateAge(dob DATE) RETURNS INT
BEGIN
  DECLARE age INT;
  SET age = FLOOR(DATEDIFF(CURDATE(), dob) / 365.25);
  RETURN age;
END;//
DELIMITER ;
--Scenario 2: The bank needs to compute the monthly installment for a loan
-- Create table
CREATE TABLE Loans (
  LoanID INT PRIMARY KEY,
  CustomerID INT,
  LoanAmount DECIMAL(10, 2),
  InterestRate DECIMAL(4, 2),
  LoanDuration INT
);

-- Function to calculate monthly installment
DELIMITER //
CREATE FUNCTION CalculateMonthlyInstallment(loanAmount DECIMAL(10, 2), interestRate DECIMAL(4, 2), loanDuration INT) RETURNS DECIMAL(10, 2)
BEGIN
  DECLARE monthlyInstallment DECIMAL(10, 2);
  SET monthlyInstallment = loanAmount * (interestRate / 1200) * (1 + interestRate / 1200) ^ (loanDuration * 12) / ((1 + interestRate / 1200) ^ (loanDuration * 12) - 1);
  RETURN monthlyInstallment;
END;//
DELIMITER ;
--Scenario 3: Check if a customer has sufficient balance before making a transaction
-- Create table
CREATE TABLE Accounts (
  AccountID INT PRIMARY KEY,
  CustomerID INT,
  Balance DECIMAL(10, 2)
);

-- Function to check sufficient balance
DELIMITER //
CREATE FUNCTION HasSufficientBalance(accountID INT, amount DECIMAL(10, 2)) RETURNS BOOLEAN
BEGIN
  DECLARE sufficientBalance BOOLEAN;
  DECLARE currentBalance DECIMAL(10, 2);
  SELECT Balance INTO currentBalance
  FROM Accounts
  WHERE AccountID = accountID;
  IF currentBalance >= amount THEN
    SET sufficientBalance = TRUE;
  ELSE
    SET sufficientBalance = FALSE;
  END IF;
  RETURN sufficientBalance;
END;//
DELIMITER ;
