package com.outplaying.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface IStorageService {
	public void store(MultipartFile file);
	
	public Resource loadFile(String filename);
	
}
