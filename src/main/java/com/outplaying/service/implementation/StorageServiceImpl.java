package com.outplaying.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.outplaying.model.Credential;
import com.outplaying.model.User;
import com.outplaying.repository.ICredentialRepository;
import com.outplaying.repository.IUserRepository;
import com.outplaying.service.IStorageService;
import com.outplaying.utils.Validator;

@Service
public class StorageServiceImpl implements IStorageService {

	private final Path rootLocationProfileImg = Paths.get("profile-img-storage");
	private final Path rootLocationPostImg = Paths.get("post-img-storage");

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ICredentialRepository credentialRepository;

	@Override
	public String storeTemporaryProfileImage(MultipartFile file) {
		try {
			if (Validator.isAuthenticated()) {
				// Saco el username a partir de la autenticacion de la cual puedo obtener el id de user y con este las credenciales.
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				User user = userRepository.getOne(Long.parseLong(authentication.getName()));
				Credential credential = credentialRepository.credentialByIdUSer(user);
				// para obtener la extension de la imagen.
				String[] extension = file.getOriginalFilename().split("\\.");
				// metodo que elimina la imagen actual de perfil si se encuentra.
				File tempFile = File.createTempFile(credential.getUsername(), "."+ extension[1] );
				tempFile.deleteOnExit();
				
				FileOutputStream fos = new FileOutputStream(tempFile); 
		        fos.write(file.getBytes());
		        fos.close();
				return tempFile.getName();
//				this.deleteImgWithSameName(credential.getUsername(), "./temp-storage/");
//				Files.copy(file.getInputStream(), this.rootLocationTem.resolve(imageName));
//				return imageName;
				
				
				
			} else {
				throw new HttpMessageNotReadableException("you cant add  this image",
						new Throwable("you cant add  this image"));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}

	}

	@Override
	public Resource loadFileProfileImg(String filename) {
		try {
			Path file = rootLocationProfileImg.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	@Override
	public Resource loadFilePostImg(String filename) {
		try {
			Path file = rootLocationPostImg.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	@Override
	public String saveTempImgProfileImg(String lastpictureName ,String newPictureName) throws FileNotFoundException {
		File f = new File(System.getProperty("java.io.tmpdir") + newPictureName);
		if (f.exists()) {
			InputStream in = new FileInputStream(f);
			try {
				String [] fileNameSpliting = f.getName().split("\\.");
				String nameFilePersist =  fileNameSpliting[0] +  "." + fileNameSpliting[1];
				Files.copy(in, this.rootLocationProfileImg.resolve(nameFilePersist));
				in.close();
				f.delete();
				if(lastpictureName != "") {
					this.deleteFile(lastpictureName , "./profile-img-storage/");
				}
				return nameFilePersist;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
		
	}
	@Override
	public String saveTempImgPostImg(String lastpictureName ,String newPictureName, String idPost) throws FileNotFoundException {
		File f = new File(System.getProperty("java.io.tmpdir") + newPictureName);
		if (f.exists()) {
			InputStream in = new FileInputStream(f);
			try {
				String [] fileNameSpliting = f.getName().split("\\.");
				String nameFilePersist =  fileNameSpliting[0] + idPost + "." + fileNameSpliting[1];
				Files.copy(in, this.rootLocationPostImg.resolve(nameFilePersist));
				in.close();
				f.delete();
				if(lastpictureName != "") {
					this.deleteFile(lastpictureName , "./post-img-storage/");
				}
				return nameFilePersist;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String storeTemporaryPostImage(MultipartFile file) {
		try {
			if (Validator.isAuthenticated()) {
				// uso los datos de las credenciales recuperados del token para dar nombre al fichero.
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				User user = userRepository.getOne(Long.parseLong(authentication.getName()));
				Credential credential = credentialRepository.credentialByIdUSer(user);
				// para obtener la extension de la imagen.
				String[] extension = file.getOriginalFilename().split("\\.");
				File tempFile = File.createTempFile(credential.getUsername() + "Post" , "."+ extension[1]);
				tempFile.deleteOnExit();
				
				FileOutputStream fos = new FileOutputStream(tempFile); 
		        fos.write(file.getBytes());
		        fos.close();
				return tempFile.getName();
			} else {
				throw new HttpMessageNotReadableException("you cant add  this image",
						new Throwable("you cant add  this image"));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}

	}
	
	public void deleteFile(String name, String rootDirectory) { 
		File f = new File(rootDirectory + name );
		if (f.exists()) {
			f.delete();
		}
	}


	

}
