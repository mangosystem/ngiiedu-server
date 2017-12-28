package kr.go.ngii.edu.main.board.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.Module;

import kr.go.ngii.edu.main.board.mapper.BoardMapper;
import kr.go.ngii.edu.main.board.model.BbsPds;
import kr.go.ngii.edu.main.board.model.BbsFAQuestion;
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

	public BbsNotice insertNotice(String title, String description) {
		
		BbsNotice param = new BbsNotice();
		param.setTitle(title);
		param.setDescription(description);;
//		param.setCreateDate(new Date());
//		param.setModifyDate(new Date());
		boardMapper.insertNotice(param);

		return param;
		//Module result = false;

	}
	
	public BbsNotice modifyNotice(int idx, String title, String description) {
		BbsNotice param = new BbsNotice();
		param.setIdx(idx);
		param.setTitle(title);
		param.setDescription(description);
//		param.setModifyDate(new Date());
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
	
	public int getNoticeCnt() {
		return boardMapper.getNoticeCnt();
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

	public BbsQuestion insertQna(String title, String description, int userId) {
		
		BbsQuestion param = new BbsQuestion();
		param.setTitle(title);
		param.setDescription(description);
		param.setUserId(userId);
//		param.setWriter(writer);
//		param.setAttach(attach);
		//param.setCreateDate(new Date());
		//param.setModifyDate(new Date());
		boardMapper.insertQna(param);

		return param;
		//Module result = false;

	}
	
	public BbsQuestion modifyQna(int idx, String title, String description) {
		BbsQuestion param = new BbsQuestion();
		param.setIdx(idx);
		param.setTitle(title);
		param.setDescription(description);
//		param.setAttach(attach);
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
	
	public int getQnaCnt() {
		return boardMapper.getQnaCnt();
	}

	
	public List<BbsFAQuestion> getFaqList() {
		List<BbsFAQuestion> result = null;
		try{
			result = boardMapper.getFaqList();
			
		}catch(Exception e) {
			
		}
		return result;
	}
	
	public List<BbsFAQuestion> getFaqList(int offset, int limit) {
		return boardMapper.getFaqList(offset, limit);
	}

	public BbsFAQuestion getFaqListbyId(int idx) {
		BbsFAQuestion question = new BbsFAQuestion();
		question.setIdx(idx);
		return boardMapper.getFaqListbyId(question);
	}

	public BbsFAQuestion insertFaq(String title, String description, int userId) {
		
		BbsFAQuestion param = new BbsFAQuestion();
		param.setTitle(title);
		param.setDescription(description);
		param.setUserId(userId);
//		param.setWriter(writer);
//		param.setAttach(attach);
		//param.setCreateDate(new Date());
		//param.setModifyDate(new Date());
		boardMapper.insertFaq(param);
		return param;
		//Module result = false;
	}
	
	public BbsFAQuestion modifyFaq(int idx, String title, String description, String attach) {
		BbsFAQuestion param = new BbsFAQuestion();
		param.setIdx(idx);
		param.setTitle(title);
		param.setDescription(description);
//		param.setAttach(attach);
		//param.setModiDate(new Date());
		
		boardMapper.modifyFaq(param);

		if (param.getIdx()!=null) {
			param = getFaqListbyId(idx);
		}

		return param;
	}
	
	public boolean deleteFaq(int faqId) {
		if (getFaqListbyId(faqId)!=null) {
			boardMapper.deleteFaq(faqId);
			return true;
		} else {
			return false;
		}
	}
	
	public int getFaqCnt() {
		return boardMapper.getFaqCnt();
	}

	
	public List<BbsReply> getReListbyQnaId(int qnaId) {
		BbsReply reply = new BbsReply();
		reply.setQnaId(qnaId);
		return boardMapper.getReListbyQnaId(reply);
	}
	
	public BbsReply getReListbyIdx(int idx) {
		BbsReply reply = new BbsReply();
		reply.setIdx(idx);
		return boardMapper.getRebyIdx(reply);
	}
	
	public BbsReply insertRe(int qnaId, String description, int userId) {
		BbsReply param = new BbsReply();
		param.setQnaId(qnaId);
		param.setDescription(description);
		param.setUserId(userId);
		//param.setCreateDate(new Date());
		//param.setModifyDate(new Date());
		boardMapper.insertRe(param);

		return param;
	}
	
	public BbsReply modifyRe(int idx, String description) {
		BbsReply param = new BbsReply();
		param.setIdx(idx);
		//param.setTitle(title);
		param.setDescription(description);
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
	
	public List<BbsPds> getPdsList() {
		List<BbsPds> result = null;
		try{
			result = boardMapper.getPdsList();
			
		}catch(Exception e) {
			
		}
		return result;
	}
	public List<BbsPds> getPdsList(int offset, int limit) {
		return boardMapper.getPdsList(offset, limit);
	}

	public BbsPds getPdsById(int idx) {
		BbsPds param = new BbsPds();
		param.setIdx(idx);
		return boardMapper.getPdsbyId(param);
	}

	public BbsPds insertPds(String title, String description) {
		
		BbsPds param = new BbsPds();
		param.setTitle(title);
		param.setDescription(description);;
//		param.setCreateDate(new Date());
//		param.setModifyDate(new Date());
		boardMapper.insertPds(param);

		return param;
		//Module result = false;

	}
	
	public BbsPds modifyPds(int idx, String title, String description) {
		BbsPds param = new BbsPds();
		param.setIdx(idx);
		param.setTitle(title);
		param.setDescription(description);
//		param.setModifyDate(new Date());
		boardMapper.modifyPds(param);

		if (param.getIdx()!=null) {
			param = getPdsById(idx);
		}

		return param;
	}

	public boolean deletePds(int idx) {
		if (getPdsById(idx)!=null) {
			boardMapper.deletePds(idx);
			return true;
		} else {
			return false;
		}
	}
	
	public int getPdsCnt() {
		return boardMapper.getPdsCnt();
	}
	
}
