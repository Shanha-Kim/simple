package com.koitt.www.vo;

import java.text.*;
import java.sql.*;

/**
 * 		이 클래스는 파일업로드게시판의 
 * 		정보를 저장하고 전달할 목적의 클래스
 * 
 * @author	전은석
 * @since	2019.11.15
 * @version	v.1.0
 * @see		
 * 			변경이력
 * 				2019.11.15	-	클래스 제작	-	담당자 : 전은석
 *
 */
public class FileBoardVO {
	private int bno;
	private int writer;
	private String id;
	private String title;
	private String body;
	private int fno;
	private Date wdate;
	private String sdate;
	private Time wtime;
	private String stime;
	private String isshow;
	private int cnt;
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
		setSdate();
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy년 MM월 dd일");
		this.sdate = form.format(wdate);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public Time getWtime() {
		return wtime;
	}
	public void setWtime(Time wtime) {
		this.wtime = wtime;
		setStime();
	}
	public String getStime() {
		return stime;
	}
	public void setStime() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
		this.stime = form.format(wtime);
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public String toString() {
		String str = "********************" +
						bno + " - " + id +
						"\n\t ###  " + title +
						"--------------------" +
						"\n\t" + sdate +" " + stime +
						"\n\t" + body +
					"********************"
				;
		
		return str;
	}
}
