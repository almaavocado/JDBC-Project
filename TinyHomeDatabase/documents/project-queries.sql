-- #1
-- Gets the total expenses for each house
SELECT House_Num, SUM(AMOUNT) 'Expenses' FROM
    (
        SELECT * FROM TINYHOME t1
            LEFT JOIN COMMUNITY c ON t1.COMMUNITY_Community_ID = c.Community_ID
        UNION
            SELECT * FROM TINYHOME t2
                RIGHT JOIN COMMUNITY c ON t2.COMMUNITY_Community_ID = c.Community_ID
    ) t
    INNER JOIN EXPENSE GROUP BY House_Num;

-- #2
-- Provides the name(s) of funders that have financially supported communities with 50% or greater occupied homes
SELECT funder_name FROM FUNDER
    EXCEPT
    SELECT funder_name FROM FUNDER

        INNER JOIN CONTRIBUTION C on FUNDER.PHONE_NUMBER = C.FUNDER_PHONE_NUMBER
        INNER JOIN COMMUNITY C2 on C2.COMMUNITY_ID = C.COMMUNITY_COMMUNITY_ID
        
        WHERE c2.HOMES_FILLED / c2.TOTAL_CAPACITY < 0.5;

-- #3
-- Provides the name(s) of the funder who donated to the largest sized community
SELECT DISTINCT funder_name FROM
    (SELECT funder_name, total_capacity FROM FUNDER
        INNER JOIN CONTRIBUTION C on FUNDER.PHONE_NUMBER = C.FUNDER_PHONE_NUMBER
        INNER JOIN COMMUNITY C2 on C.COMMUNITY_COMMUNITY_ID = C2.COMMUNITY_ID) a

WHERE total_capacity =
      (SELECT MAX(TOTAL_CAPACITY) FROM COMMUNITY);

-- #4
--returns the size of the a recoveryProgram's local community
SELECT COMMUNITY_STATE, CITY, PE.PROGRAM_PROGRAM_NAME as "Program_Name", COUNT(PERSON_APPLICANT_ID) as "Local_Program_Size"
FROM    TINYHOME TH INNER JOIN COMMUNITY C on TH.COMMUNITY_COMMUNITY_ID = C.COMMUNITY_ID
        INNER JOIN PROGRAMENROLLMENT PE on TH.RESIDENT_APPLICANT_ID = PE.PERSON_APPLICANT_ID
GROUP BY COMMUNITY_STATE, CITY, PROGRAM_PROGRAM_NAME;

-- #5
--returns the number of goals met by each community
SELECT COMMUNITY_STATE, CITY, COUNT(A.GOAL_MET) AS "GOALS_MET"
FROM    TINYHOME TC INNER JOIN COMMUNITY C on TC.COMMUNITY_COMMUNITY_ID = C.COMMUNITY_ID
        LEFT OUTER JOIN AFTERSTAY A ON TC.RESIDENT_APPLICANT_ID = A.PERSON_APPLICANT_ID
GROUP BY COMMUNITY_STATE, CITY;

-- #6
--returns communities who's expenses are greater than their incoming contributions
SELECT COMMUNITY_STATE, CITY, SUM(AMOUNT_FUNDED) as "Total_Contributions", Total_Expenses
FROM    CONTRIBUTION C1 INNER JOIN COMMUNITY C2 on C1.COMMUNITY_COMMUNITY_ID = C2.COMMUNITY_ID
        INNER JOIN (
            SELECT COMMUNITY_COMMUNITY_ID, SUM(AMOUNT) as Total_Expenses
            FROM EXPENSE
            GROUP BY COMMUNITY_COMMUNITY_ID) TE
        ON C2.COMMUNITY_ID = TE.COMMUNITY_COMMUNITY_ID
GROUP BY COMMUNITY_STATE, CITY, Total_Expenses
HAVING Total_Expenses > SUM(AMOUNT_FUNDED);
