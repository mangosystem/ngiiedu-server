package kr.go.ngii.edu.main.board.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.Module;

import kr.go.ngii.edu.main.board.mapper.BoardMapper;
import kr.go.ngii.edu.main.board.model.BbsNotice;
import kr.go.ngii.edu.main.board.model.BbsQuestion;
import kr.go.ngii.edu.main.board.model.BbsReply;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	public List<BbsNotice> getNoticeList() {
		List<BbsNotice> result = null;
		try{
			result = boardMapper.getNoticeList();
			
		}catch(Exception e) {
			
		}
		return result;
	}
	public List<BbsNotice> getNoticeList(int offset, int limit) {
		return boardMapper.getNoticeList(offset, limit);
	}

	public BbsNotice getNoticeListbyId(int idx) {
		BbsNotice notice = new BbsNotice();
		notice.setIdx(idx);
		return boardMapper.getNoticeListbyId(notice);
	}

	public BbsNotice insertNotice(String title, String content) {
		
		BbsNotice param = new BbsNotice();
		param.setTitle(title);
		param.setContent(content);
		//param.setCreateDate(new Date());
		//param.setModifyDate(new Date());
		boardMapper.insertNotice(param);

		return param;
		//Module result = false;

	}
	
	public BbsNotice modifyNotice(int idx, String title, String content) {
		BbsNotice param = new BbsNotice();
		param.setIdx(idx);
		param.setTitle(title);
		param.setContent(content);
		//param.setModiDate(new Date());
		
		boardMapper.modifyNotice(param);

		if (param.getIdx()!=null) {
			param = getNoticeListbyId(idx);
		}

		return param;
	}

	public boolean deleteNotice(int noticeId) {
		if (getNoticeListbyId(noticeId)!=null) {
			boardMapper.deleteNotice(noticeId);
			return true;
		} else {
			return false;
		}
	}
	
	public List<BbsQuestion> getQnaList() {
		List<BbsQuestion> result = null;
		try{
			result = boardMapper.getQnaList();
			
		}catch(Exception e) {
			
		}
		return result;
	}
	
	public List<BbsQuestion> getQnaList(int offset, int limit) {
		return boardMapper.getQnaList(offset, limit);
	}

	public BbsQuestion getQnaListbyId(int idx) {
		BbsQuestion question = new BbsQuestion();
		question.setIdx(idx);
		return boardMapper.getQnaListbyId(question);
	}

	public BbsQuestion insertQna(String title, String content, String writer, String attach) {
		
		BbsQuestion param = new BbsQuestion();
		param.setTitle(title);
		param.setContent(content);
		param.setWriter(writer);
		param.setAttach(attach);
		//param.setCreateDate(new Date());
		//param.setModifyDate(new Date());
		boardMapper.insertQna(param);

		return param;
		//Module result = false;

	}
	
	public BbsQuestion modifyQna(int idx, String title, String content, String attach) {
		BbsQuestion param = new BbsQuestion();
		param.setIdx(idx);
		param.setTitle(title);
		param.setContent(content);
		param.setAttach(attach);
		//param.setModiDate(new Date());
		
		boardMapper.modifyQna(param);

		if (param.getIdx()!=null) {
			param = getQnaListbyId(idx);
		}

		return param;
	}

	public boolean deleteQna(int qnaId) {
		if (getQnaListbyId(qnaId)!=null) {
			boardMapper.deleteQna(qnaId);
			return true;
		} else {
			return false;
		}
	}
	
	public List<BbsReply> getReListbyId(int qnaId) {
		BbsReply reply = new BbsReply();
		reply.setQnaId(qnaId);
		return boardMapper.getReListbyIdx(reply);
	}
	
	public BbsReply getReListbyIdx(int idx) {
		BbsReply reply = new BbsReply();
		reply.setIdx(idx);
		return boardMapper.getRebyIdx(reply);
	}
	
	public BbsReply insertRe(int qnaId, String content, String writer) {
		BbsReply param = new BbsReply();
		param.setQnaId(qnaId);
		param.setContent(content);
		param.setWriter(writer);
		//param.setCreateDate(new Date());
		//param.setModifyDate(new Date());
		boardMapper.insertRe(param);

		return param;
	}
	
	public BbsReply modifyRe(int idx, String content) {
		BbsReply param = new BbsReply();
		param.setIdx(idx);
		//param.setTitle(title);
		param.setContent(content);
		//param.setAttach(attach);
		//param.setModiDate(new Date());
		
		boardMapper.modifyRe(param);

		return param;
	}
	
	public boolean deleteRe(Integer idx) {
		// TODO Auto-generated method stub
		if (getReListbyIdx(idx)!=null) {
			boardMapper.deleteRe(idx);
			return true;
		} else {
			return false;
		}
	}
	
}
