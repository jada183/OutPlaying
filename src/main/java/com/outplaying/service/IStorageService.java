package com.outplaying.service;

import java.io.FileNotFoundException;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface IStorageService {
	public String storeTemporaryProfileImage(MultipartFile file);
	
	public Resource loadFileProfileImg(String filename);
	
	public String saveTempImgProfileImg (String lastpictureName ,String newPictureName) throws FileNotFoundException;
	
	public String saveTempImgPostImg (String lastpictureName ,String newPictureName, String idPost) throws FileNotFoundException;
	
	public String storeTemporaryPostImage(MultipartFile file);
	
	public Resource loadFilePostImg(String filename);
	
	public void  deleteFile(String name, String rootDirectory);
	
}
