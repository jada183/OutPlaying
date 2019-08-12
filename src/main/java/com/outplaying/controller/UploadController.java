package com.outplaying.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.outplaying.service.IStorageService;

@RestController
@RequestMapping(value = "file")
public class UploadController {

	@Autowired
	private IStorageService storageService;

	List<String> files = new ArrayList<String>();

	@PostMapping("profile-img/temp")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = "";
		String message = "";
		try {
			fileName = storageService.storeTemporaryProfileImage(file);
			return ResponseEntity.status(HttpStatus.OK).body(fileName);
			
		} catch (Exception e) {
			message = "FAIL to upload" + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	@PostMapping("post-img/temp")
	public ResponseEntity<String> handleFileUploadPost(@RequestParam("file") MultipartFile file) {
		String fileName = "";
		String message = "";
		try {
			fileName = storageService.storeTemporaryPostImage(file);
			return ResponseEntity.status(HttpStatus.OK).body(fileName);
			
		} catch (Exception e) {
			message = "FAIL to upload" + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}


	@GetMapping("profile-img/{filename}")
	public ResponseEntity<Resource> getFileProfile(@PathVariable String filename) {

		Resource file = storageService.loadFileProfileImg(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	@GetMapping("post-img/{filename}")
	public ResponseEntity<Resource> getFilePost(@PathVariable String filename) {
		Resource file = storageService.loadFilePostImg(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

}
