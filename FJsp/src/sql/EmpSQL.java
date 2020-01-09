package sql;

public class EmpSQL {
	public static final int TOTAL_CNT = 1001;
	public static final int SEL_1500_UPPER = 1002;
	public static final int SEL_ENAME = 1003;
	public static final int SEL_DNO = 1004;
	public static final int SEL_ALL = 1005;
	
	// 코드를 입력하면 질의명령을 반환해주는 함수
	public static String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case TOTAL_CNT:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	emp");
			break;
		case SEL_ALL:
			buff.append("SELECT ");
			buff.append("	empno, ename, job, nvl(mgr, -999) mgr, hiredate, sal, nvl(comm, -999) comm, deptno ");
			buff.append("FROM ");
			buff.append("	emp");
			break;
		case SEL_1500_UPPER:
			buff.append("SELECT ");
			buff.append("	empno eno, ename, job, mgr, hiredate, sal, comm, deptno dno ");
			buff.append("FROM ");
			buff.append("	emp ");
			buff.append("WHERE ");
			buff.append("	sal >= 1500");
			break;
		case SEL_ENAME:
			buff.append("SELECT ");
			buff.append("	empno eno, ename, job, mgr, hiredate, sal, comm, deptno dno ");
			buff.append("FROM ");
			buff.append("	emp ");
			buff.append("WHERE ");
			buff.append("	ename = ?");
			break;
		case SEL_DNO:
			buff.append("SELECT ");
			buff.append("	empno eno, ename, job, mgr, hiredate, sal, comm, deptno dno ");
			buff.append("FROM ");
			buff.append("	emp ");
			buff.append("WHERE ");
			buff.append("	deptno = ?");
			break;
		}
		
		return buff.toString();
	}
}
