select
    empno, ename, mgr, deptno, (LEVEL - 1) LVL
from
    emp
START WITH
    mgr is null
CONNECT BY
    PRIOR empno = mgr
ORDER SIBLINGS BY
    hiredate DESC
;


SELECT
    e.empno 사원번호, e.ename 사원이름, e.mgr 상사번호, ee.ename 상사이름
FROM
    emp e, emp ee
WHERE
    e.mgr = ee.empno(+)
;