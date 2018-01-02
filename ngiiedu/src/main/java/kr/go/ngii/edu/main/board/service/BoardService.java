package kr.go.ngii.edu.main.board.service;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.Module;

import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.main.board.mapper.BoardMapper;
import kr.go.ngii.edu.main.board.model.BbsPds;
import kr.go.ngii.edu.main.board.model.BbsPdsFile;
import kr.go.ngii.edu.main.board.model.BbsFAQuestion;
import kr.go.ngii.edu.main.board.model.BbsNotice;
import kr.go.ngii.edu.main.board.model.BbsQuestion;
import kr.go.ngii.edu.main.board.model.BbsReply;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.users.model.User;

@Service
public class BoardService extends BaseService {

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
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
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
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
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
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
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
	
	public BbsFAQuestion modifyFaq(int idx, String title, String description) {
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
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
		
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		
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
		
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		
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
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		
		BbsReply param = new BbsReply();
		param.setIdx(idx);
		//param.setTitle(title);
		param.setDescription(description);
		//param.setAttach(attach);
		//param.setModiDate(new Date());
		
		boardMapper.modifyRe(param);

		return param;
	}
	
	public boolean deleteRe(int idx) {
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
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
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		BbsPds param = new BbsPds();
		param.setTitle(title);
		param.setDescription(description);;
//		param.setCreateDate(new Date());
//		param.setModifyDate(new Date());
		boardMapper.insertPds(param);
		return param;
	}
	
	
	
	public BbsPds insertPds(String title, String description, MultipartFile attach) {
		
		int userId;
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			userId = user.getIdx();
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		
		String fileRepoPath = LocalResourceBundle.FILE_SAVE_REPOSITORY;

		File dir = new File(fileRepoPath);
	    if(!dir.isDirectory()){
	        dir.mkdir();
	    }
		
//		String newFileName = ; // 업로드 되는 파일명
		String fileName = attach.getOriginalFilename();
		
		String fileid = UUID.randomUUID().toString().replaceAll("-", "");
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		
		StringBuffer newFileName = new StringBuffer()
				.append(fileid).append(".").append(ext);
        
		try {
        	attach.transferTo(new File(fileRepoPath+newFileName.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		BbsPds bbsPds = new BbsPds();
		bbsPds.setTitle(title);
		bbsPds.setDescription(description);
		bbsPds.setUserId(userId);
//		param.setCreateDate(new Date());
//		param.setModifyDate(new Date());
		boardMapper.insertPds(bbsPds);
        
        BbsPdsFile bbsPdsFile = new BbsPdsFile();
        bbsPdsFile.setPdsId(bbsPds.getIdx());
        bbsPdsFile.setFileName(fileName);
        bbsPdsFile.setFilePath(fileid);
        bbsPdsFile.setExt(ext);
//      bbsPdsFile.setCreateDate(createDate);
//		bbsPdsFile.setModifyDate(modifyDate);
        boardMapper.insertPdsFile(bbsPdsFile);
        bbsPds.setBbsPdsFile(bbsPdsFile);
		return bbsPds;
	}
	
	public BbsPds modifyPds(int idx, String title, String description) {
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
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
	
	public BbsPds modifyPds(int idx, String title, String description, MultipartFile attach) {
		int userId;
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			userId = user.getIdx();
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		
		BbsPds param = new BbsPds();
		param.setIdx(idx);
		param.setTitle(title);
		param.setDescription(description);
//		param.setModifyDate(new Date());
		boardMapper.modifyPds(param);
		if (param.getIdx()!=null) {
			param = getPdsById(idx);
		}
		
		if (attach != null) {
			// 기존파일 삭제 
			deletePdsFileByPdsId(idx);
			
			// 새파일 작성
			String fileRepoPath = LocalResourceBundle.FILE_SAVE_REPOSITORY;

			File dir = new File(fileRepoPath);
		    if(!dir.isDirectory()){
		        dir.mkdir();
		    }
		    
		    String fileName = attach.getOriginalFilename();
			
			String fileid = UUID.randomUUID().toString().replaceAll("-", "");
			String ext = fileName.substring(fileName.lastIndexOf(".")+1);
			
			StringBuffer newFileName = new StringBuffer()
					.append(fileid).append(".").append(ext);
			
			BbsPdsFile bbsPdsFile = new BbsPdsFile();
	        bbsPdsFile.setPdsId(idx);
	        bbsPdsFile.setFileName(fileName);
	        bbsPdsFile.setFilePath(fileid);
	        bbsPdsFile.setExt(ext);
	        boardMapper.insertPdsFile(bbsPdsFile);
	        param.setBbsPdsFile(bbsPdsFile);
			try {
	        	attach.transferTo(new File(fileRepoPath+newFileName.toString()));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		return param;
	}

	public boolean deletePds(int idx) {
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		if (getPdsById(idx)!=null) {
			boardMapper.deletePds(idx);
			this.deletePdsFileByPdsId(idx);
			return true;
		} else {
			return false;
		}
	}
	
	public int getPdsCnt() {
		return boardMapper.getPdsCnt();
	}
	
	public List<BbsPdsFile> getPdsFileList(int pdsId) {
		return boardMapper.getPdsFileList(pdsId);
	}
	
	public BbsPdsFile getPdsFileByIdx(int idx) {
		BbsPdsFile bbsPdsFile = new BbsPdsFile();
		bbsPdsFile.setIdx(idx);
		return boardMapper.getPdsFilebyId(bbsPdsFile);
	}
	
	public BbsPdsFile getPdsFileByUUID(String filePath) {
		BbsPdsFile bbsPdsFile = new BbsPdsFile();
		bbsPdsFile.setFilePath(filePath);
		return boardMapper.getPdsFilebyUUID(bbsPdsFile);
	}
	
	public BbsPdsFile getPdsFileByPdsId(int pdsId) {
		BbsPdsFile bbsPdsFile = new BbsPdsFile();
		bbsPdsFile.setPdsId(pdsId);
		return boardMapper.getPdsFilebyId(bbsPdsFile);
	}
	
	public BbsPdsFile insertPdsFile(int pdsId, String filePath, String fileName, String ext) {
		
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		
		BbsPdsFile bbsPdsFile = new BbsPdsFile();
		bbsPdsFile.setPdsId(pdsId);
		bbsPdsFile.setFilePath(filePath);
		bbsPdsFile.setFileName(fileName);
		bbsPdsFile.setExt(ext);
		//bbsPdsFile.setCreateDate(new Date());
		//bbsPdsFile.setModifyDate(new Date());
		boardMapper.insertPdsFile(bbsPdsFile);

		return bbsPdsFile;
	}
	
	public BbsPdsFile modifyPdsFile(int idx, String filePath, String fileName, String ext) {
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		
		BbsPdsFile param = new BbsPdsFile();
		param.setIdx(idx);
		param.setFilePath(filePath);
		param.setFileName(fileName);
		param.setExt(ext);
		//param.setAttach(attach);
		//param.setModiDate(new Date());
		boardMapper.modifyPdsFile(param);
		return param;
	}
	
	public boolean deletePdsFile(int pdsId) {
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		if (getPdsFileList(pdsId)!=null) {
			boardMapper.deletePdsFile(pdsId);
			return true;
		} else {
			return false;
		}
	}
	
	
	public void deletePdsFileByPdsId(int pdsId) {
		
		List<BbsPdsFile> bbsPdsFileList = boardMapper.getPdsFileList(pdsId);
		String fileRepoPath = LocalResourceBundle.FILE_SAVE_REPOSITORY;
		
		boardMapper.deletePdsFile(pdsId);
		for (BbsPdsFile item:bbsPdsFileList) {
			StringBuffer sb = new StringBuffer()
					.append(fileRepoPath).append(item.getFilePath())
					.append(".").append(item.getExt());

			File f = new File(sb.toString());
			f.delete();
		}
	}
	
}
