package com.doje.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.doje.service.DojeService;
import com.doje.vo.BoardVo;
import com.doje.vo.Editor;
import com.doje.vo.FreeBoardVo;
import com.doje.vo.GuestBoardVo;
import com.doje.vo.ImageBoardVo;
import com.doje.vo.ImageListVo;
import com.doje.vo.NoticeBoardVo;
import com.doje.vo.UserVo;

@Controller
public class DojeController {
	@Autowired
	DojeService dojeService;

	@RequestMapping("/userJoin")
	public String userJoin() {
		return "/userJoin";
	}

	@RequestMapping("/userEdit")
	public String userEdit() {
		return "/userEdit";
	}

	@RequestMapping(value = "/checkId", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String checkId(HttpServletRequest req) {
		return dojeService.checkId(req);
	}

	@RequestMapping("/main")
	public String main(HttpServletRequest req, UserVo userVo, BoardVo boardVo, FreeBoardVo freeBoardVo,
			NoticeBoardVo noticeBoardVo, GuestBoardVo guestBoardVo, ImageBoardVo imageBoardVo,
			ImageListVo imageListVo) {
		HttpSession session = req.getSession();
		String isPage = req.getParameter("page");
		String writer = req.getParameter("writer");
		String isType = req.getParameter("type");

		// 작성자 없을때
		if (writer == null) {
			userVo.setHobby(Arrays.toString(req.getParameterValues("hobby"))); // 자동주입
			// 회원가입
			if (isPage.equals("join")) {
				dojeService.insertUser(userVo);
				// 유저정보 세션에 저장
				session.setAttribute("user", userVo);
			}
			// 정보수정
			else if (isPage.equals("edit")) {
				dojeService.updateUser(userVo);
				session.removeAttribute("user");
				// 유저정보 세션에 저장
				session.setAttribute("user", userVo);

			}
			// 로그인
			else if (isPage.equals("login")) {
				userVo = dojeService.selectUser(req);
				return dojeService.login(req, userVo);
			}
		}
		// 작성자 있을때
		else if (writer != null) {
			boardVo.setWriter(writer);
			// 글 수정 페이지
			if (isPage.equals("boardEdit")) {
				String board_pk = req.getParameter("no");
				dojeService.updateBoard(boardVo);
				System.out.println(boardVo.toString());
				session.setAttribute("board", boardVo);
				// 자유게시판
				if (isType.equals("free")) {
					freeBoardVo.setBoard_pk(board_pk);
					dojeService.updateFreeBoard(freeBoardVo);
					System.out.println(freeBoardVo.toString() + "\n자유 게시판 수정 완료");
					session.setAttribute("free", freeBoardVo);
				}
				// 공지사항
				else if (isType.equals("notice")) {
					noticeBoardVo.setBoard_pk(board_pk);
					noticeBoardVo.setEdit_id(writer);
					dojeService.updateNoticeBoard(noticeBoardVo);
					System.out.println(noticeBoardVo.toString() + "\n공지사항 수정 완료");
					session.setAttribute("notice", noticeBoardVo);
				}
				// 이미지 게시판
				else if (isType.equals("image")) {
					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
					List<MultipartFile> imgList = multipartRequest.getFiles(req.getParameter("images_name")); // List

					imageBoardVo = dojeService.selectImageBoard(board_pk);
					imageBoardVo.setTotal(req.getParameter("total"));
					dojeService.updateImageBoard(imageBoardVo);
					System.out.println(imageBoardVo.toString() + "\n이미지 게시판 수정 완료");

					String image_pk = imageBoardVo.getNo();

					List<ImageListVo> list = dojeService.selectImageList(image_pk);
					for (ImageListVo imagelist : list) {
						DeleteFile(req, imagelist);
						dojeService.deleteImageList(imagelist.getNo());
					}

					imageListVo.setImage_pk(image_pk);
					for (MultipartFile image : imgList) {
						CreateFile(image, req, imageListVo);
						dojeService.insertImageList(imageListVo);
					}
					list = dojeService.selectImageList(image_pk);
					session.setAttribute("image", imageBoardVo);
					session.setAttribute("list", list);
				}
			}
			// 글 수정 아닐 때
			else {
				String board_pk = dojeService.insertBoard(boardVo);
				boardVo = dojeService.selectBoard(board_pk);
				System.out.println(boardVo.toString());
				session.setAttribute("board", boardVo);
				// 자유게시판
				if (isPage.equals("free")) {
					freeBoardVo = dojeService.freeBoardQuery(board_pk, freeBoardVo);
					session.setAttribute("free", freeBoardVo);
				}
				// 공지사항
				else if (isPage.equals("notice")) {
					noticeBoardVo.setEdit_id(writer);
					noticeBoardVo = dojeService.noticeBoardQuery(board_pk, noticeBoardVo);
					session.setAttribute("notice", noticeBoardVo);
				}
				// 이미지 게시판
				else if (isPage.equals("image")) {
					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
					List<MultipartFile> imageList = multipartRequest.getFiles(req.getParameter("images_name")); // List
					imageBoardVo = dojeService.imageBoardQuery(board_pk, imageBoardVo);

					String image_pk = imageBoardVo.getNo();
					imageListVo.setImage_pk(image_pk);
					for (MultipartFile image : imageList) {
						CreateFile(image, req, imageListVo);
						dojeService.insertImageList(imageListVo);
					}
					List<ImageListVo> list = dojeService.selectImageList(image_pk);
					session.setAttribute("image", imageBoardVo);
					session.setAttribute("list", list);
				}
				// 방명록
				else if (isPage.equals("guest")) {
					dojeService.guestBoardQuery(board_pk, guestBoardVo);
				}
			}
		}
		return "/main";
	}

	// 상위 경로 얻는 메소드
	public static String getRootPath(HttpServletRequest req) {
		String exactOs = System.getProperty("os.name"); // key = os.name, value = 현재 컴퓨터의 OS 이름
		String os = exactOs.split(" ")[0]; // 넘어온 이름값을 공백으로 잘랐을때 0번째 방의 값
		if (os.equals("Windows")) {
			return req.getSession().getServletContext().getRealPath("/"); // C:\Users\GoTaeYoung\eclipse-workspace\Test1234\src\main\webapp\
		} else {
			return "";
		}
	}

	// 폴더 생성 메소드
	private void mkdir(String dir_path) {
		File dir = new File(dir_path);
		if (!dir.exists()) // 폴더 존재 여부
			dir.mkdirs(); // 현재 폴더와 상위 폴더까지 생성
	}

	FileOutputStream fos;

	// 파일 생성 메소드
	public void CreateFile(MultipartFile file, HttpServletRequest req, ImageListVo imageListVo) {
		try {
			// 원본 파일명
			String original_name = file.getOriginalFilename();
			String ext = original_name.substring(original_name.lastIndexOf(".") + 1);

			// 서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
			Date date = new Date();
			String time = format.format(date);
			String realname = time + "_" + (int) ((Math.random() * 1000) + 1) + "." + ext;

			String root_path = getRootPath(req); // C:\Users\GoTaeYoung\eclipse-workspace\Test1234\src\main\webapp\
			mkdir(root_path + "temp"); // 현재 폴더와 상위 폴더까지 생성
			String full_path = root_path + "temp" + File.separator + realname; // C:\Users\GoTaeYoung\eclipse-workspace\Test1234\src\main\webapp\temp\imgCnt.jpg
			byte fileData[] = file.getBytes(); // 파일 정보를 바이트 단위로 변경
			fos = new FileOutputStream(full_path); // 입력한 경로대로 생성
			fos.write(fileData); // 입력한 경로에 있는 파일에 바이트 단위의 데이터 출력.

			imageListVo.setUrl("/temp/" + realname);
			imageListVo.setOrigin_name(original_name);
			imageListVo.setServer_name(realname);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e2) {
				}
			}
		}
	}

	public void DeleteFile(HttpServletRequest req, ImageListVo imageListVo) {
		String root_path = getRootPath(req); // C:\Users\GoTaeYoung\eclipse-workspace\Test1234\src\main\webapp\
		mkdir(root_path + "temp"); // 현재 폴더와 상위 폴더까지 생성
		String full_path = root_path + "temp" + File.separator + imageListVo.getServer_name(); // C:\Users\GoTaeYoung\eclipse-workspace\Test1234\src\main\webapp\temp\imgCnt.jpg
		File file = new File(full_path);
		file.delete();
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("user") != null)
			session.invalidate();
		return "/login";
	}

	@RequestMapping("/freeBoard")
	public String freeBoard() {
		return "/freeBoard";
	}

	@RequestMapping("/guestBoard")
	public String guestBoard() {
		return "/guestBoard";
	}

	@RequestMapping("/noticeBoard")
	public String noticeBoard() {
		return "/noticeBoard";
	}

	@RequestMapping("/imageBoard")
	public String imageBoard() {
		return "/imageBoard";
	}

	@RequestMapping("/boardEdit")
	public String boardEdit() {
		return "/boardEdit";
	}

	// 단일파일업로드
	@RequestMapping("/photoUpload")
	public String photoUpload(HttpServletRequest req, Editor vo) {
		String callback = vo.getCallback();
		String callback_func = vo.getCallback_func();
		String file_result = "";
		try {
			if (vo.getFiledata() != null && vo.getFiledata().getOriginalFilename() != null
					&& !vo.getFiledata().getOriginalFilename().equals("")) {

				String original_name = vo.getFiledata().getOriginalFilename();
				String ext = original_name.substring(original_name.lastIndexOf(".") + 1);

				String rootPath = getRootPath(req);

				String path = rootPath + "temp" + File.separator;
				mkdir(path);

				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
				Date date = new Date();
				String time = format.format(date);
				String realname = time + "_" + (int) ((Math.random() * 1000) + 1) + "." + ext;

				vo.getFiledata().transferTo(new File(path + realname));
				file_result += "&bNewLine=true&sFileName=" + original_name + "&sFileURL=/temp/" + realname;
			} else {
				file_result += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + callback + "?callback_func=" + callback_func + file_result;
	}
}
