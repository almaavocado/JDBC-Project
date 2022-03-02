-- Gets the total expenses for each house
SELECT House_Num, SUM(AMOUNT) 'Expenses' FROM
-- Outer Join
(SELECT *
 FROM TINYHOME t1
          LEFT JOIN COMMUNITY c ON t1.COMMUNITY_Community_ID = c.Community_ID
 UNION
 SELECT *
 FROM TINYHOME t2
          RIGHT JOIN COMMUNITY c ON t2.COMMUNITY_Community_ID = c.Community_ID
) t
    INNER JOIN EXPENSE GROUP BY House_Num;

-- provides the name(s) of funders that have financially supported communities with 50% or greater occupied homes
SELECT funder_name FROM FUNDER
EXCEPT
SELECT funder_name FROM
FUNDER INNER JOIN CONTRIBUTION C on FUNDER.PHONE_NUMBER = C.FUNDER_PHONE_NUMBER
INNER JOIN COMMUNITY C2 on C2.COMMUNITY_ID = C.COMMUNITY_COMMUNITY_ID
WHERE c2.HOMES_FILLED / c2.TOTAL_CAPACITY < 0.5;

-- provides the name(s) of the funder who donated to the largest sized community
SELECT DISTINCT funder_name FROM
    (SELECT funder_name, total_capacity FROM
        FUNDER INNER JOIN CONTRIBUTION C on FUNDER.PHONE_NUMBER = C.FUNDER_PHONE_NUMBER
        INNER JOIN COMMUNITY C2 on C.COMMUNITY_COMMUNITY_ID = C2.COMMUNITY_ID
        ) a
WHERE total_capacity =
      (SELECT MAX(TOTAL_CAPACITY) FROM COMMUNITY);
