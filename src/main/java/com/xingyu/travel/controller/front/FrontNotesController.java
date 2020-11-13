package com.xingyu.travel.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.Comment;
import com.xingyu.travel.model.Notes;
import com.xingyu.travel.model.Notestype;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.CollectService;
import com.xingyu.travel.services.CommentService;
import com.xingyu.travel.services.NotesService;
import com.xingyu.travel.services.UserService;
import com.xingyu.travel.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller.front
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/front/notes")
public class FrontNotesController {
	@Autowired
	private NotesService notesService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private CollectService collectService;

	/**
	 * @Description: 查询共享游记详情以及评论
	 * @Author: Admin
	 * @Param: [notesId, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/notesInfo")
	public String notesInfo(Integer notesId, HttpServletRequest request) {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		Notes notes = notesService.findNotesById(notesId);
		Users userServiceById = userService.findById(notes.getUserId());
		notes.setPetName(userServiceById.getPetName());
		List<Comment> notesComment = commentService.findNotesComment(notesId);
		ArrayList<Users> usersCom = new ArrayList<>();
		for (Comment comment : notesComment) {
			//返回用户信息
			Users commUsers = userService.findById(comment.getUsersId());
			usersCom.add(commUsers);
		}
		if (null != users) {
			//根据用户id、notesid查询收藏表
			boolean collectByUIdAndNotesId = collectService.findCollectByUIdAndNotesId(users.getUserId(), notesId);
			request.setAttribute("collectByUIdAndNotesId", collectByUIdAndNotesId);
		}
		Notestype notestype = notesService.findNotesTypeById(notes.getNotestypeId());
		request.setAttribute("notes", notes);
		request.setAttribute("notesComment", notesComment);
		//comment的评论人信息
		request.setAttribute("usersCom", usersCom);
		request.setAttribute("notestype", notestype);
		return "/front/notes/notesInfo";
	}


	/**
	 * @Description: 查询所有游记信息（分页展示）
	 * @Author: Admin
	 * @Param: [pageNo, pageSize, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("notesList")
	public String findAllStrategy(Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 8 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Notes> NotesList = notesService.findAllNotes();
		List<Notestype> notestypeList = notesService.findAllNotesType();
		PageInfo<Notes> pageInfo = new PageInfo(NotesList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("NotesList", NotesList);
		request.setAttribute("notestypeList", notestypeList);
		return "/front/notes/notesList";
	}


	/**
	 * @Description: 根据游记类型查询游记
	 * @Author: Admin
	 * @Param: [pageNo, pageSize, notestypeId, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/notesType")
	public String notesType(Integer pageNo, Integer pageSize, Integer notestypeId, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 8 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Notes> notesByTypeIdList = notesService.findNotesByTypeId(notestypeId);
		Notestype notestype = notesService.findNotesTypeById(notestypeId);
		PageInfo<Notes> pageInfo = new PageInfo(notesByTypeIdList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("notesByTypeIdList", notesByTypeIdList);
		request.setAttribute("notestype", notestype);
		return "/front/notes/notesType";
	}

	/**
	 * @Description: 根据名字模糊搜索（分页展示）
	 * @Author: Admin
	 * @Param: [notesName]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/seachByName")
	public String seachByName(Integer pageNo, Integer pageSize, String notesName, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 6 : pageSize;
		PageHelper.startPage(pageNo, pageSize);
		//根据名字模糊查询 游记
		List<Notes> notesList = notesService.findNotesByName(notesName);
		List<Notes> notesReferList = notesService.findNotesRefer();
		PageInfo pageInfo = new PageInfo(notesList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("notesReferList", notesReferList);
		request.setAttribute("notesList", notesList);
		return "/front/notes/seachNotesByName";
	}

	/**
	 * @Description: 跳转添加游记界面
	 * @Author: Admin
	 * @Param: [request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toInsertNotes")
	public String toInsertNotes(HttpServletRequest request) {
		List<Notestype> allNotesType = notesService.findAllNotesType();
		request.setAttribute("allNotesType", allNotesType);
		return "/front/notes/insertNotes";
	}

	/**
	 * @Description: 编写旅游游记
	 * @Author: Admin
	 * @Param: []
	 * @Return java.lang.String
	 **/
	@RequestMapping("/insertNotes")
	public String insertNotes(Notes notes, @RequestParam(value = "file", required = false) MultipartFile[] files, HttpServletRequest request) throws Exception {
		if (null != files) {
			String fileNameStr = UploadFileUtil.uploadFileByFiles(files);
			notes.setNotesPic(fileNameStr);
		}
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		notes.setUserId(users.getUserId());
		int i = notesService.insertNotes(notes);
		if (i > 0) {
			return "/front/notes/insertNotes";
		} else {
			return "/front/error/error";
		}
	}

	/**
	 * @Description: 查询用户的notes
	 * @Author: Admin
	 * @Param: [request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/notesListByUserId")
	public String notesListByUserId(HttpServletRequest request) {
		Users users = (Users) redisTemplate.boundValueOps(request.getRemoteAddr()).get();
		List<Notes> notesByUsersId = notesService.findNotesByUsersId(users.getUserId());
		request.setAttribute("notesByUsersId", notesByUsersId);
		return "/front/notes/myNotes";
	}

	/**
	 * @Description: 跳转NotesDail页面
	 * @Author: Admin
	 * @Param: [scenery, request]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/toNotesDail")
	public String toNotesDail(Notes notes, HttpServletRequest request) {
		Notes notesById = notesService.findNotesById(notes.getNotesId());
		Users users = userService.findById(notesById.getUserId());
		notesById.setPetName(users.getPetName());
		Notestype notesTypeById = notesService.findNotesTypeById(notesById.getNotestypeId());
		notesById.setNotestypeName(notesTypeById.getNotestypeName());
		request.setAttribute("notesById", notesById);
		return "/front/notes/notesDail";
	}
}
