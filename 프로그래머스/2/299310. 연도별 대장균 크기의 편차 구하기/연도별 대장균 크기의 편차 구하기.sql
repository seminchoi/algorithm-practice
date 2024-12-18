-- 코드를 작성해주세요
#가장 큰 대장균을 찾는다.
SELECT YEAR(ED.DIFFERENTIATION_DATE) as YEAR, (B.MAX_SIZE - ED.SIZE_OF_COLONY) as YEAR_DEV, ID
FROM ECOLI_DATA as ED
JOIN (SELECT MAX(SIZE_OF_COLONY) as MAX_SIZE, YEAR(DIFFERENTIATION_DATE) as YEAR
            FROM ECOLI_DATA as I
            GROUP BY YEAR(DIFFERENTIATION_DATE))
            as B
            ON B.YEAR = YEAR(ED.DIFFERENTIATION_DATE)
ORDER BY YEAR, YEAR_DEV
;


# SELECT A.ID, A.YEAR,
# FROM ECOLI_DATA as A
# JOIN (SELECT 
#       FROM ECOLI_DATA as B
#       WHERE
#      )