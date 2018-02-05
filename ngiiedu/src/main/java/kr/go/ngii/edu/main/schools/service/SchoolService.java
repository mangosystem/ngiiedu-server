package kr.go.ngii.edu.main.schools.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.UIDUtil;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.schools.mapper.SchoolMapper;
import kr.go.ngii.edu.main.schools.model.School;

@Service
public class SchoolService extends BaseService{

	@Autowired
	private SchoolMapper schoolMapper;


	public Map<String, Object> list(int offset, int limit, String keyword, String schoolLevel) {

		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("list", schoolMapper.list(offset, limit, keyword, schoolLevel));
			result.put("count", schoolMapper.listCount(keyword, schoolLevel));

			return result;

		} catch (Exception e) {
			return null;
		}
	}

	public School get(School school) {
		return schoolMapper.get(school);
	}

	public School get(int idx) {
		School school = new School();
		school.setIdx(idx);
		return schoolMapper.get(school);
	}

	public School get(String schoolAuthkey) {
		School school = new School();
		school.setSchoolAuthkey(schoolAuthkey);
		return schoolMapper.get(school);
	}

	public boolean delete(int idx) {
		if (get(idx)!=null) {
			schoolMapper.delete(idx);
			return true;
		} else {
			return false;
		}
	}

	public School create(String schoolId, String schoolName, String schoolLevel, String schoolStatus, String schoolEduOfficeName, Integer schoolEduOfficeCode, String schoolSidoOfficeName, 
			Integer schoolSidoOfficeCode, String schoolAddr, String schoolBuildDate, String schoolEstablishType, String schoolLat, String schoolLon, String schoolBranchType, 
			String schoolAddrRoad, String schoolRefDate, String schoolCreateDate, String schoolEditDate) {
		try {

			School param = new School();

			param.setSchoolId(schoolId);
			param.setSchoolName(schoolName);
			param.setSchoolLevel(schoolLevel);
			param.setSchoolStatus(schoolStatus);
			param.setSchoolEduOfficeName(schoolEduOfficeName);
			param.setSchoolEduOfficeCode(schoolEduOfficeCode);
			param.setSchoolSidoOfficeName(schoolSidoOfficeName);
			param.setSchoolSidoOfficeCode(schoolSidoOfficeCode);
			param.setSchoolAddr(schoolAddr);
			param.setSchoolBuildDate(schoolBuildDate);
			param.setSchoolEstablishType(schoolEstablishType);
			param.setSchoolLat(schoolLat);
			param.setSchoolLon(schoolLon);
			param.setSchoolBranchType(schoolBranchType);
			param.setSchoolAddrRoad(schoolAddrRoad);
			param.setSchoolReferenceDate(schoolRefDate);
			param.setSchoolDataCreateDate(schoolCreateDate);
			param.setSchoolDateEditDate(schoolEditDate);
			param.setSchoolAuthkey(getAuthkey());

			schoolMapper.create(param);

			return param;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public School modify(Integer idx, String schoolName, String schoolLevel, String schoolStatus, String schoolEduOfficeName, Integer schoolEduOfficeCode, String schoolSidoOfficeName, 
			Integer schoolSidoOfficeCode, String schoolAddr, String schoolBuildDate, String schoolEstablishType, String schoolLat, String schoolLon, String schoolBranchType, 
			String schoolAddrRoad, String schoolReferenceDate, String schoolDataCreateDate, String schoolDateEditDate) {
		School param = new School();
		param.setIdx(idx);
		param.setSchoolName(schoolName);
		param.setSchoolLevel(schoolLevel);
		param.setSchoolStatus(schoolStatus);
		param.setSchoolEduOfficeName(schoolEduOfficeName);
		param.setSchoolEduOfficeCode(schoolEduOfficeCode);
		param.setSchoolSidoOfficeName(schoolSidoOfficeName);
		param.setSchoolSidoOfficeCode(schoolSidoOfficeCode);
		param.setSchoolAddr(schoolAddr);
		param.setSchoolBuildDate(schoolBuildDate);
		param.setSchoolEstablishType(schoolEstablishType);
		param.setSchoolLat(schoolLat);
		param.setSchoolLon(schoolLon);
		param.setSchoolBranchType(schoolBranchType);
		param.setSchoolAddrRoad(schoolAddrRoad);
		param.setSchoolReferenceDate(schoolReferenceDate);
		param.setSchoolDataCreateDate(schoolDataCreateDate);
		param.setSchoolDateEditDate(schoolDateEditDate);
		schoolMapper.modify(param);

		return param;
	}

	public School createAPI(String schoolId, String schoolName, String schoolLevel, String schoolStatus, String schoolEduOfficeName, Integer schoolEduOfficeCode, String schoolSidoOfficeName, 
			Integer schoolSidoOfficeCode, String schoolAddr, String schoolBuildDate, String schoolEstablishType, String schoolLat, String schoolLon, String schoolBranchType, 
			String schoolAddrRoad, String schoolRefDate, String schoolCreateDate, String schoolEditDate) {

		try {

			School param = new School();

			param.setSchoolId(schoolId);
			param.setSchoolName(schoolName);
			param.setSchoolLevel(schoolLevel);
			param.setSchoolStatus(schoolStatus);
			param.setSchoolEduOfficeName(schoolEduOfficeName);
			param.setSchoolEduOfficeCode(schoolEduOfficeCode);
			param.setSchoolSidoOfficeName(schoolSidoOfficeName);
			param.setSchoolSidoOfficeCode(schoolSidoOfficeCode);
			param.setSchoolAddr(schoolAddr);
			param.setSchoolBuildDate(schoolBuildDate);
			param.setSchoolEstablishType(schoolEstablishType);
			param.setSchoolLat(schoolLat);
			param.setSchoolLon(schoolLon);
			param.setSchoolBranchType(schoolBranchType);
			param.setSchoolAddrRoad(schoolAddrRoad);
			param.setSchoolReferenceDate(schoolRefDate);
			param.setSchoolDataCreateDate(schoolCreateDate);
			param.setSchoolDateEditDate(schoolEditDate);
			param.setSchoolAuthkey(getAuthkey());

			schoolMapper.createAPI(param);

			return param;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public int count() {
		return schoolMapper.count();
	}

	/**
	 * 중복되지 않는 학교코드를 자동으로 생성하여 리턴한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getAuthkey() throws Exception {

		String key = UIDUtil.randomKey36(10);

		if (schoolMapper.exists(key)) {
			return getAuthkey();
		} else {
			return key;
		}
	}

	public void modifyAuthkey(Integer idx) {
		try {
			School param = new School();

			String schoolAuthkey = getAuthkey();
			param.setIdx(idx);
			param.setSchoolAuthkey(schoolAuthkey);


			schoolMapper.modifyAuthkey(param);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}

	}

	public School getAuthkey(Integer idx) {

		return schoolMapper.getAuthkey(idx);

	}

}

