package ar.gob.sigep.AWSService.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

	void uploadFile(MultipartFile multipartFile);
	
	byte[] downloadFile(String keyName);
	
	
}
