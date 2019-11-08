package vo;

import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;

public class EmpVO {
	private int eno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate; // jdbc 는 날짜와 시간을 별도로 처리를 한다.
	private Time hireTime;
	private String sDate; // 입사일을 문자열로 저장할 변수
	private int sal;
	private int comm;
	private int dno;
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Time getHireTime() {
		return hireTime;
	}
	public void setHireTime(Time hireTime) {
		this.hireTime = hireTime;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public void setsDate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm:ss");
		this.sDate = form1.format(hiredate);
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	
	public String toString() {
		return "사원번호 - " + eno + 
				"\n사원이름 - " + ename + 
				"\n사원직급 - " + job + 
				"\n상사번호 - " + mgr + 
				"\n입 사 일 - " + sDate +
				"\n급    여 - " + sal + 
				"\n보 너 스 - " + comm + 
				"\n부서번호 - " + dno + 
				"\n-------------------------------";
	}
}
