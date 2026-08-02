/*=========================================================
 ETL VALIDATION QUERIES
 Project : ETL Automation Framework
 Source  : CUSTOMER_SOURCE
 Target  : CUSTOMER_TARGET
=========================================================*/

----------------------------------------------------------
-- 1. Source Row Count
----------------------------------------------------------
SELECT COUNT(*) AS SOURCE_COUNT
FROM CUSTOMER_SOURCE;

----------------------------------------------------------
-- 2. Target Row Count
----------------------------------------------------------
SELECT COUNT(*) AS TARGET_COUNT
FROM CUSTOMER_TARGET;

----------------------------------------------------------
-- 3. Missing Records (Present in Source, Missing in Target)
----------------------------------------------------------
SELECT *
FROM CUSTOMER_SOURCE
MINUS
SELECT *
FROM CUSTOMER_TARGET;

----------------------------------------------------------
-- 4. Extra Records (Present in Target, Not in Source)
----------------------------------------------------------
SELECT *
FROM CUSTOMER_TARGET
MINUS
SELECT *
FROM CUSTOMER_SOURCE;

----------------------------------------------------------
-- 5. Salary Mismatch
----------------------------------------------------------
SELECT
    S.CUSTOMER_ID,
    S.SALARY AS SOURCE_SALARY,
    T.SALARY AS TARGET_SALARY
FROM CUSTOMER_SOURCE S
JOIN CUSTOMER_TARGET T
ON S.CUSTOMER_ID = T.CUSTOMER_ID
WHERE S.SALARY <> T.SALARY;