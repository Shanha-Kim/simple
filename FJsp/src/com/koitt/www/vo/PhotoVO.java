package com.koitt.www.vo;

/**
 * 	이 클래스는 한장의 프로필 사진 데이터를 기억하고
 * 	그 데이터를 전달할 목적으로 제작된 클래스
 * 
 * @author	전은석
 * @since	2019.12.04
 * @version	v.1.0
 * @see
 * 			변경이력
 * 				2019.12.04		- 	클래스 제작		- 담당자 : 황태은
 *
 */

import java.sql.*;
import java.text.*;

public class PhotoVO {
	private int pno;
	private int mno;
	private String oriname;
	private String savename;
	private long len;
	private String dir;
	private Date pDate;
	private String sDate;
	private Time pTime;
	private String sTime;
	private String isshow;
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
		setsDate();
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy년 MM월 dd일");
		this.sDate = form.format(pDate);
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public Time getpTime() {
		return pTime;
	}
	public void setpTime(Time pTime) {
		this.pTime = pTime;
		setsTime();
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
		this.sTime = form.format(pTime);
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
}
