package com.callor.todo.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.todo.config.QualifierConfig;
import com.callor.todo.service.FileServiceABS;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(QualifierConfig.SERVICE.FILE_SERVICE_V1)
public class FileServiceImplV1 extends FileServiceABS{

	@Override
	public String fileUp(MultipartFile file) {
		// TODO Auto-generated method stub

		log.debug("파일업로드 path {}", this.fileUpPath);
		
//		파일정보가 null이면 더이상 진행하지 말기
		if(file == null) {
			return null;
		}
		
//		업로드 폴더를 검사하기
		File dir = new File(fileUpPath);
		
//		업로드할 폴더가 없으면
		if(!dir.exists()) {
			dir.mkdirs();	// 폴더생성하기
		}
		
		String strUUID = UUID.randomUUID().toString();
		
//		원본파일이름 추출
		String originalFileName = file.getOriginalFilename();
		String saveFileName = String.format("%s-%s", strUUID, originalFileName);
		
//		파일을 저장하기 위하여 File 객체 생성
		File upLoadFile = new File(fileUpPath, saveFileName);
		try {
			file.transferTo(upLoadFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saveFileName;
		
	}

	@Override
	public List<String> filesUp(MultipartHttpServletRequest files) {
		// TODO Auto-generated method stub
		return null;
	}

}
