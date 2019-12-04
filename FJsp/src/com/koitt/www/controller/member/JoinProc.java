package com.koitt.www.controller.member;

import java.io.*;
import javax.servlet.http.*;
import com.oreilly.servlet.*;

import com.koitt.www.controller.*;
import com.koitt.www.dao.*;
import com.koitt.www.vo.*;
import com.koitt.www.util.*;

/**
 * 	이 클래스는 회원가입 처리 요청을 처리할 컨트롤러 클래스
 * @author	전은석
 * @since	2019.12.04
 * @version	v.1.0
 * @see
 * 			변경이력
 * 				2019.12.04		- 	클래스 제작  - 담당자 : 전은석
 *
 */

public class JoinProc implements MainController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/";
		req.setAttribute("isRD", true);
		
		int cnt = 0 ;
		// 할일
		// 1. 데이터 받고 <== 스트림 방식으로 넘어오는 데이터를 일단 서버의 파일로 저장하고
		MultipartRequest multi = null;
		String sPath = req.getSession().getServletContext().getRealPath("img");
		System.out.println("*** cont path : " + sPath);
		try {
			multi = new MultipartRequest(req, sPath + "/profile", 1024*1024*10, "UTF-8", new MyFileRenamePolicy());
			
			// 회원 정보를 입력하고...
			cnt = insertMemb(multi);
			if(cnt != 1) {
				return "/member/join.cls";
			}
			// 프로필사입 정보 입력을 해준다.
			
			cnt = insertPic(multi);
		} catch(Exception e) {
			System.out.println("### 업로드 처리 에러 ###");
			e.printStackTrace();
		}
		
		return view;
	}
	
	// 회원테이블 입력 전담 처리함수
	public int insertMemb(MultipartRequest multi) {
		int cnt = 0;
		// 1. 데이터 뽑아내고
		String sid = multi.getParameter("id");
		String spw = multi.getParameter("pw");
		String sname = multi.getParameter("name");
		String mail = multi.getParameter("mail");
		String tel = multi.getParameter("tel");
		String gen = multi.getParameter("gen");
		String savt = multi.getParameter("avt");
		int avt = Integer.parseInt(savt);
		
		MemberVO vo = new MemberVO();
		vo.setId(sid);
		vo.setPw(spw);
		vo.setName(sname);
		vo.setMail(mail);
		vo.setTel(tel);
		vo.setGen(gen);
		vo.setAvt(avt);
		
		MemberDAO mDAO = new MemberDAO();
		cnt = mDAO.addMember(vo);
		
		return cnt;
	}
	
	// 프로필 사진 정보 입력 전담 처리함수
	public int insertPic(MultipartRequest multi) {
		int cnt = 0 ;
		
		String oriname = multi.getOriginalFileName("prof");
		if(oriname == null) {
			return cnt;
		}
		String savename = multi.getFilesystemName("prof");
		
		File file = multi.getFile("prof");
		long len = file.length();
		String dir = "/img/profile";
		MemberDAO mDAO = new MemberDAO();
		String id = multi.getParameter("id");
		int mno = mDAO.getMno(id);
		// 한번에 처리하기로 했으니 VO 담아서 함수를 호출한다.
		PhotoVO vo = new PhotoVO();
		vo.setOriname(oriname);
		vo.setSavename(savename);
		vo.setLen(len);
		vo.setDir(dir);
		vo.setMno(mno);
		
		// 데이터베이스 처리함수 호출한다.
		cnt = mDAO.addPic(vo);
		
		return cnt;
	}

}
