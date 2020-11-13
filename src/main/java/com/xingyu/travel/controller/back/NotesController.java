package com.xingyu.travel.controller.back;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyu.travel.model.Notes;
import com.xingyu.travel.model.Notestype;
import com.xingyu.travel.model.Users;
import com.xingyu.travel.services.NotesService;
import com.xingyu.travel.services.NotestypeService;
import com.xingyu.travel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.controller
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/notes")
public class NotesController {
	@Autowired
	private NotesService notesService;
	@Autowired
	private NotestypeService notestypeService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private UserService userService;

	/**
	 * @Description: 查询所有的游记（分页展示）
	 * @Author: Admin
	 * @Param: []
	 * @Return java.util.List<com.xingyu.travel.model.Notes>
	 **/
	@RequestMapping("/notesList")
	public String findAllNotes(Notes notes, Integer pageNo, Integer pageSize, HttpServletRequest request) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 100 : pageSize;
		notes = notes == null ? new Notes() : notes;

		PageHelper.startPage(pageNo, pageSize);
		List<Notes> notesList = notesService.findAllNotes();
		for (Notes notes1 : notesList) {
			Users users = userService.findById(notes1.getUserId());
			notes1.setPetName(users.getPetName());
		}
		PageInfo<Notes> pageInfo = new PageInfo(notesList);
		request.setAttribute("page", pageInfo);
		request.setAttribute("notesList", notesList);
		return "/back/notes/notesList";
	}

	/**
	 * @Description: 改变游记状态
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/updateNotesStatus")
	public String updateNotesStatus(Notes notes, HttpServletRequest request) {
		int i = notesService.updateNotesStatus(notes);
		request.setAttribute("i", i);
		return "/back/notes/notesList";
	}

	/**
	 * @Description: 根据游记Id查询游记详情（展示游记具体内容）
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return java.lang.String
	 **/
	@RequestMapping("/findNotesByNotesId")
	public String findNotesByNotesId(Notes notes, HttpServletRequest request) {
		//根据notesID查询notes
		Notes notes1 = notesService.findNotesByNotesId(notes.getNotesId());
		//查询游记类别
		Notestype notestype = notestypeService.findNotesTypeByTypeId(notes.getNotestypeId());
		request.setAttribute("notes", notes1);
		request.setAttribute("notestype", notestype);
		return "/back/notes/notesDetail";
	}

	/**
	 * @Description: 修改notes的refer状态（推荐游记）
	 * @Author: Admin
	 * @Param: [notes]
	 * @Return void
	 **/
	@RequestMapping("/referNotes")
	public String findNotesReferByNotesId(Notes notes, HttpServletRequest request) {
		int i = notesService.findNotesReferByNotesId(notes);
		request.setAttribute("i", i);
		return "/back/notes/notesList";
	}
}
