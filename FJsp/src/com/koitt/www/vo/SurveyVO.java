package com.koitt.www.vo;

/**
 * 	이 클래스는 설문조사 관련 데이터를 저장할 클래스
 * 
 * @author	전은석
 * @since	2019.12.05
 * @version	v.1.0
 * @see
 * 			변경이력
 * 				2019.12.05		-		클래스 제작 	- 담당자 : 전은석
 *
 */

import java.sql.*;
import java.text.*;

public class SurveyVO {
	// 설문테이블 데이터
	private int sno;
	private String title;	// 설문 
	private String check;
	private Date startDate;	// 설문 시작 날짜
	private Date endDate;	// 설문 종료 날짜
	private String startStrDate;
	private String endStrDate;
	// 설문문항 테이블
	private int qno;
	private String body;
	// 설문보기 테이블
	private int seno;
	private String ebody;
	private int count;
	// 설문 참여 정보
	private int inno;
	private String id;
	private Date invDate;	// 설문 참여 일자
	private String sinvDate;
	private Time invTime;	// 설문 참여 시간
	private String sinvTime;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		setStartStrDate();
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		setEndStrDate();
	}
	public String getStartStrDate() {
		return startStrDate;
	}
	public void setStartStrDate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
		this.startStrDate = form.format(startDate);
	}
	public void setStartStrDate(String startStrDate) {
		this.startStrDate = startStrDate;
	}
	public String getEndStrDate() {
		return endStrDate;
	}
	public void setEndStrDate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
		this.endStrDate = form.format(endDate);
	}
	public void setEndStrDate(String endStrDate) {
		this.endStrDate = endStrDate;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getSeno() {
		return seno;
	}
	public void setSeno(int seno) {
		this.seno = seno;
	}
	public String getEbody() {
		return ebody;
	}
	public void setEbody(String ebody) {
		this.ebody = ebody;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getInno() {
		return inno;
	}
	public void setInno(int inno) {
		this.inno = inno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getInvDate() {
		return invDate;
	}
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
		setSinvDate();
	}
	public String getSinvDate() {
		return sinvDate;
	}
	public void setSinvDate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
		this.sinvDate = form.format(invDate);
	}
	public void setSinvDate(String sinvDate) {
		this.sinvDate = sinvDate;
	}
	public Time getInvTime() {
		return invTime;
	}
	public void setInvTime(Time invTime) {
		this.invTime = invTime;
		setSinvTime();
	}
	public String getSinvTime() {
		return sinvTime;
	}
	public void setSinvTime() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
		this.sinvTime = form.format(invTime);
	}
	public void setSinvTime(String sinvTime) {
		this.sinvTime = sinvTime;
	}
	
}
