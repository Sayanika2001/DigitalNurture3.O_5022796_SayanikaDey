-- Create customers table
CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    customer_name VARCHAR2(100),
    balance NUMBER,
    IsVIP VARCHAR2(5) DEFAULT 'FALSE'
);

-- Insert sample data into customers table
INSERT INTO customers (customer_id, customer_name, balance)
VALUES (1, 'John Doe', 15000);

INSERT INTO customers (customer_id, customer_name, balance)
VALUES (2, 'Jane Smith', 8000);

INSERT INTO customers (customer_id, customer_name, balance)
VALUES (3, 'Mike Johnson', 12000);

INSERT INTO customers (customer_id, customer_name, balance)
VALUES (4, 'Emily Davis', 5000);

COMMIT;

-- Create transactions table
CREATE TABLE transactions (
    transaction_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    transaction_date DATE,
    amount NUMBER,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Insert sample data into transactions table
INSERT INTO transactions (transaction_id, customer_id, transaction_date, amount)
VALUES (1, 1, SYSDATE - 15, 1000);

INSERT INTO transactions (transaction_id, customer_id, transaction_date, amount)
VALUES (2, 2, SYSDATE - 10, 2000);

INSERT INTO transactions (transaction_id, customer_id, transaction_date, amount)
VALUES (3, 3, SYSDATE - 5, 1500);

INSERT INTO transactions (transaction_id, customer_id, transaction_date, amount)
VALUES (4, 4, SYSDATE - 2, 500);

COMMIT;

-- Create loans table
CREATE TABLE loans (
    loan_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    interest_rate NUMBER,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Insert sample data into loans table
INSERT INTO loans (loan_id, customer_id, interest_rate)
VALUES (1, 1, 5.00);

INSERT INTO loans (loan_id, customer_id, interest_rate)
VALUES (2, 2, 4.50);

INSERT INTO loans (loan_id, customer_id, interest_rate)
VALUES (3, 3, 6.00);

INSERT INTO loans (loan_id, customer_id, interest_rate)
VALUES (4, 4, 3.75);

COMMIT;

-- Scenario 1: Generate Monthly Statements
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT t.customer_id, c.customer_name, t.transaction_date, t.amount
        FROM transactions t
        JOIN customers c ON t.customer_id = c.customer_id
        WHERE EXTRACT(MONTH FROM t.transaction_date) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.transaction_date) = EXTRACT(YEAR FROM SYSDATE);
    transaction_record GenerateMonthlyStatements%ROWTYPE;
BEGIN
    OPEN GenerateMonthlyStatements;
    LOOP
        FETCH GenerateMonthlyStatements INTO transaction_record;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || transaction_record.customer_id ||
                             ', Name: ' || transaction_record.customer_name ||
                             ', Transaction Date: ' || TO_CHAR(transaction_record.transaction_date, 'DD-MON-YYYY') ||
                             ', Amount: ' || transaction_record.amount);
    END LOOP;
    CLOSE GenerateMonthlyStatements;
END;
/
-- End of Scenario 1

-- Scenario 2: Apply Annual Fee
DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT customer_id, balance
        FROM customers;
    customer_record ApplyAnnualFee%ROWTYPE;
    annual_fee CONSTANT NUMBER := 100;
BEGIN
    OPEN ApplyAnnualFee;
    LOOP
        FETCH ApplyAnnualFee INTO customer_record;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;
        UPDATE customers
        SET balance = balance - annual_fee
        WHERE customer_id = customer_record.customer_id;
        DBMS_OUTPUT.PUT_LINE('Annual fee applied to Customer ID: ' || customer_record.customer_id ||
                             ', New Balance: ' || (customer_record.balance - annual_fee));
    END LOOP;
    CLOSE ApplyAnnualFee;
    COMMIT;
END;
/
-- End of Scenario 2

-- Scenario 3: Update Loan Interest Rates
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT loan_id, customer_id, interest_rate
        FROM loans;
    loan_record UpdateLoanInterestRates%ROWTYPE;
    new_interest_rate CONSTANT NUMBER := 4.00;  -- Example new interest rate policy
BEGIN
    OPEN UpdateLoanInterestRates;
    LOOP
        FETCH UpdateLoanInterestRates INTO loan_record;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;
        UPDATE loans
        SET interest_rate = new_interest_rate
        WHERE loan_id = loan_record.loan_id;
        DBMS_OUTPUT.PUT_LINE('Updated interest rate for Loan ID: ' || loan_record.loan_id ||
                             ', Customer ID: ' || loan_record.customer_id ||
                             ', New Interest Rate: ' || new_interest_rate);
    END LOOP;
    CLOSE UpdateLoanInterestRates;
    COMMIT;
END;
/
-- End of Scenario 3
