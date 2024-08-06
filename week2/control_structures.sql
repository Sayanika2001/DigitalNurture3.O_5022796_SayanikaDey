--Scenario 1: The bank wants to apply a discount to loan interest rates for customers above 60 years old 
-- Create table
CREATE TABLE Customers (
  CustomerID INT PRIMARY KEY,
  Name VARCHAR(50),
  DateOfBirth DATE
);

CREATE TABLE Loans (
  LoanID INT PRIMARY KEY,
  CustomerID INT,
  InterestRate DECIMAL(4, 2),
  FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- PL/SQL block to apply discount
DELIMITER //
CREATE PROCEDURE ApplyDiscount()
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE customerID INT;
  DECLARE dateOfBirth DATE;
  DECLARE interestRate DECIMAL(4, 2);
  DECLARE cur1 CURSOR FOR
    SELECT C.CustomerID, C.DateOfBirth, L.InterestRate
    FROM Customers C
    JOIN Loans L ON C.CustomerID = L.CustomerID;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN cur1;

  read_loop: LOOP
    FETCH cur1 INTO customerID, dateOfBirth, interestRate;
    IF done THEN
      LEAVE read_loop;
    END IF;

    IF TIMESTAMPDIFF(YEAR, dateOfBirth, CURDATE()) > 60 THEN
      UPDATE Loans
      SET InterestRate = InterestRate - 0.01
      WHERE CustomerID = customerID;
    END IF;
  END LOOP;

  CLOSE cur1;
END;//
DELIMITER ; 
--Scenario 2: A customer can be promoted to VIP status based on their balance
-- Create table
CREATE TABLE Customers (
  CustomerID INT PRIMARY KEY,
  Name VARCHAR(50),
  Balance DECIMAL(10, 2),
  IsVIP BOOLEAN DEFAULT FALSE
);

-- PL/SQL block to promote customers to VIP
DELIMITER //
CREATE PROCEDURE PromoteToVIP()
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE customerID INT;
  DECLARE balance DECIMAL(10, 2);
  DECLARE cur1 CURSOR FOR
    SELECT CustomerID, Balance
    FROM Customers;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN cur1;

  read_loop: LOOP
    FETCH cur1 INTO customerID, balance;
    IF done THEN
      LEAVE read_loop;
    END IF;

    IF balance > 10000 THEN
      UPDATE Customers
      SET IsVIP = TRUE
      WHERE CustomerID = customerID;
    END IF;
  END LOOP;

  CLOSE cur1;
END;//
DELIMITER ;
--Scenario 3: The bank wants to send reminders to customers whose loans are due within the next 30 days
-- Create table
CREATE TABLE Loans (
  LoanID INT PRIMARY KEY,
  CustomerID INT,
  DueDate DATE
);

-- PL/SQL block to send reminders
DELIMITER //
CREATE PROCEDURE SendReminders()
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE customerID INT;
  DECLARE dueDate DATE;
  DECLARE cur1 CURSOR FOR
    SELECT CustomerID, DueDate
    FROM Loans
    WHERE DueDate BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY);
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN cur1;

  read_loop: LOOP
    FETCH cur1 INTO customerID, dueDate;
    IF done THEN
      LEAVE read_loop;
    END IF;

    SELECT CONCAT('Reminder: Loan due on ', dueDate) AS Reminder;
  END LOOP;

  CLOSE cur1;
END;//
DELIMITER ;

