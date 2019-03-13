package kr.go.ngii.edu.main.board.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.controller.rest.ResponseData;
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

		try{
			List<BbsNotice> result = boardMapper.getNoticeList();
			return result;

		}catch(Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}

	}
	public List<BbsNotice> getNoticeList(int offset, int limit) {

		try {
			return boardMapper.getNoticeList(offset, limit);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsNotice getNoticeListbyId(int idx) {

		try {
			BbsNotice notice = new BbsNotice();
			notice.setIdx(idx);
			return boardMapper.getNoticeListbyId(notice);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsNotice insertNotice(String title, String description) {

		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
			BbsNotice param = new BbsNotice();
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setCreateDate(new Date());
			boardMapper.insertNotice(param);

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			BbsNotice param = new BbsNotice();
			param.setIdx(idx);
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setModifyDate(new Date());
			boardMapper.modifyNotice(param);

			if (param.getIdx()!=null) {
				param = getNoticeListbyId(idx);
			}

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			if (getNoticeListbyId(noticeId)!=null) {
				boardMapper.deleteNotice(noticeId);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getNoticeCnt() {
		try {
			return boardMapper.getNoticeCnt();
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}


	public List<BbsQuestion> getQnaList() {

		try{
			List<BbsQuestion> result = boardMapper.getQnaList();
			return result;

		}catch(Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public List<BbsQuestion> getQnaList(int offset, int limit) {

		try {
			return boardMapper.getQnaList(offset, limit);

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsQuestion getQnaListbyId(int idx) {

		try {
			BbsQuestion question = new BbsQuestion();
			question.setIdx(idx);
			return boardMapper.getQnaListbyId(question);

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsQuestion insertQna(String title, String description) {

		User user = null;
		try {
			user = (User) getHttpSession().getAttribute("USER_INFO");
			if (user == null) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
			BbsQuestion param = new BbsQuestion();
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setUserId(user.getIdx());
			param.setCreateDate(new Date());

			boardMapper.insertQna(param);

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsQuestion modifyQna(int idx, String title, String description) {

		User user = null;
		try {
			user = (User) getHttpSession().getAttribute("USER_INFO");
			if (user == null) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

			if (user.getIdx() != getQnaListbyId(idx).getUserId() && !"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
			BbsQuestion param = new BbsQuestion();
			param.setIdx(idx);
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setModifyDate(new Date());

			boardMapper.modifyQna(param);

			if (param.getIdx()!=null) {
				param = getQnaListbyId(idx);
			}

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public boolean deleteQna(int qnaId) {

		User user = null;
		try {
			user = (User) getHttpSession().getAttribute("USER_INFO");
			if (user == null) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

			if (user.getIdx() != getQnaListbyId(qnaId).getUserId() 
					&& !"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
			if (getQnaListbyId(qnaId) != null) {
				boardMapper.deleteQna(qnaId);
				return true;

			} else {
				return false;
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public int getQnaCnt() {

		try {
			return boardMapper.getQnaCnt();

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}


	public List<BbsFAQuestion> getFaqList() {

		try{
			List<BbsFAQuestion> result = boardMapper.getFaqList();
			return result;

		}catch(Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public List<BbsFAQuestion> getFaqList(int offset, int limit) {

		try {
			return boardMapper.getFaqList(offset, limit);

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsFAQuestion getFaqListbyId(int idx) {

		try {
			BbsFAQuestion question = new BbsFAQuestion();
			question.setIdx(idx);
			return boardMapper.getFaqListbyId(question);

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsFAQuestion insertFaq(String title, String description) {

		User user = null;

		try {
			user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
			BbsFAQuestion param = new BbsFAQuestion();
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setUserId(user.getIdx());
			param.setCreateDate(new Date());

			boardMapper.insertFaq(param);

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			BbsFAQuestion param = new BbsFAQuestion();
			param.setIdx(idx);
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setModifyDate(new Date());

			boardMapper.modifyFaq(param);

			if (param.getIdx()!=null) {
				param = getFaqListbyId(idx);
			}

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			if (getFaqListbyId(faqId)!=null) {
				boardMapper.deleteFaq(faqId);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public int getFaqCnt() {

		try {
			return boardMapper.getFaqCnt();

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}


	public List<BbsReply> getReListbyQnaId(int qnaId) {

		try {
			BbsReply reply = new BbsReply();
			reply.setQnaId(qnaId);
			return boardMapper.getReListbyQnaId(reply);

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}

	}

	public BbsReply getReListbyIdx(int idx) {

		try {
			BbsReply reply = new BbsReply();
			reply.setIdx(idx);
			return boardMapper.getRebyIdx(reply);

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}

	}

	public BbsReply insertRe(int qnaId, String description) {

		User user = null;
		try {
			user = (User) getHttpSession().getAttribute("USER_INFO");
			if (user == null) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
			BbsReply param = new BbsReply();
			param.setQnaId(qnaId);
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setUserId(user.getIdx());
			param.setCreateDate(new Date());
			boardMapper.insertRe(param);

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsReply modifyRe(int idx, String description) {

		User user = null;
		try {
			user = (User) getHttpSession().getAttribute("USER_INFO");
			if (user == null) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

			if (user.getIdx() != getReListbyIdx(idx).getUserId() 
					&& !"3".equals(user.getUserDivision().trim())) {

				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
			BbsReply param = new BbsReply();
			param.setIdx(idx);
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setModifyDate(new Date());

			boardMapper.modifyRe(param);

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			if (getReListbyIdx(idx)!=null) {
				boardMapper.deleteRe(idx);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public List<BbsPds> getPdsList() {
		try {
			List<BbsPds> result = boardMapper.getPdsList();
			return result;

		} catch(Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public List<BbsPds> getPdsList(int offset, int limit) {

		try {
			return boardMapper.getPdsList(offset, limit);

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsPds getPdsById(int idx) {

		try {
			BbsPds param = new BbsPds();
			param.setIdx(idx);
			return boardMapper.getPdsbyId(param);

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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


		try {
			BbsPds param = new BbsPds();
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setCreateDate(new Date());

			boardMapper.insertPds(param);

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}


	public BbsPds insertPds(String title, String description, MultipartFile attach) {

		User user = null;
		try {
			user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
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
			bbsPds.setTitle( StringEscapeUtils.escapeHtml4(title) );
			bbsPds.setDescription( StringEscapeUtils.escapeHtml4(description) );
			bbsPds.setUserId(user.getIdx());
			bbsPds.setCreateDate(new Date());
			boardMapper.insertPds(bbsPds);

			BbsPdsFile bbsPdsFile = new BbsPdsFile();
			bbsPdsFile.setPdsId(bbsPds.getIdx());
			bbsPdsFile.setFileName(fileName);
			bbsPdsFile.setFilePath(fileid);
			bbsPdsFile.setExt(ext);
			bbsPdsFile.setCreateDate(new Date());
			boardMapper.insertPdsFile(bbsPdsFile);
			bbsPds.setBbsPdsFile(bbsPdsFile);

			return bbsPds;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			BbsPds param = new BbsPds();
			param.setIdx(idx);
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setModifyDate(new Date());

			boardMapper.modifyPds(param);

			if (param.getIdx()!=null) {
				param = getPdsById(idx);
			}

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsPds modifyPds(int idx, String title, String description, MultipartFile attach) {

		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			if (!"3".equals(user.getUserDivision().trim())) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}

		try {
			BbsPds param = new BbsPds();
			param.setIdx(idx);
			param.setTitle( StringEscapeUtils.escapeHtml4(title) );
			param.setDescription( StringEscapeUtils.escapeHtml4(description) );
			param.setModifyDate(new Date());

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

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			if (getPdsById(idx)!=null) {
				boardMapper.deletePds(idx);
				this.deletePdsFileByPdsId(idx);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public int getPdsCnt() {
		try {
			return boardMapper.getPdsCnt();
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public List<BbsPdsFile> getPdsFileList(int pdsId) {
		try {
			return boardMapper.getPdsFileList(pdsId);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsPdsFile getPdsFileByIdx(int idx) {
		try {
			BbsPdsFile bbsPdsFile = new BbsPdsFile();
			bbsPdsFile.setIdx(idx);
			return boardMapper.getPdsFilebyId(bbsPdsFile);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsPdsFile getPdsFileByUUID(String filePath) {
		try {
			BbsPdsFile bbsPdsFile = new BbsPdsFile();
			bbsPdsFile.setFilePath(filePath);
			return boardMapper.getPdsFilebyUUID(bbsPdsFile);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

	public BbsPdsFile getPdsFileByPdsId(int pdsId) {
		try {
			BbsPdsFile bbsPdsFile = new BbsPdsFile();
			bbsPdsFile.setPdsId(pdsId);
			return boardMapper.getPdsFilebyId(bbsPdsFile);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}

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

		try {
			BbsPdsFile bbsPdsFile = new BbsPdsFile();
			bbsPdsFile.setPdsId(pdsId);
			bbsPdsFile.setFilePath(filePath);
			bbsPdsFile.setFileName(fileName);
			bbsPdsFile.setExt(ext);
			bbsPdsFile.setCreateDate(new Date());
			boardMapper.insertPdsFile(bbsPdsFile);

			return bbsPdsFile;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			BbsPdsFile param = new BbsPdsFile();
			param.setIdx(idx);
			param.setFilePath(filePath);
			param.setFileName(fileName);
			param.setExt(ext);
			param.setModifyDate(new Date());
			boardMapper.modifyPdsFile(param);

			return param;

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
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

		try {
			if (getPdsFileList(pdsId)!=null) {
				boardMapper.deletePdsFile(pdsId);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}


	public void deletePdsFileByPdsId(int pdsId) {

		try {
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

		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}

}
