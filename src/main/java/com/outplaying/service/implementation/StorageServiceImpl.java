package com.outplaying.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	private final Path rootLocationTem = Paths.get("temp-storage");
	private final Path rootLocationProfileImg = Paths.get("profile-img-storage");
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private ICredentialRepository credentialRepository;

	@Override
	public void storeTemporaryProfileImage(MultipartFile file) {
		try {
			if(Validator.isAuthenticated()) { 
				// tras comprobar si se esta autentificado, saco el valor de las credenciales obteniendo primero el usuario con el id de usuario
				//  que almace la clase authentication y a partir de este  saco las credenciales para guardar el nombre del archivo con el userName
				// que es unique.asi guardo una imagen de usuario para cada uno.
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				User user = userRepository.getOne(Long.parseLong(authentication.getName()));
				Credential credential = credentialRepository.credentialByIdUSer(user);
				// para obtener la extension de la imagen.
				String [] extension = file.getOriginalFilename().split("\\.");
				String imageName = credential.getUsername()+  "." +  extension[1];
				// metodo que elimina la imagen actual de perfil si se encuentra.
				this.deleteImgWithSameName(credential.getUsername(), "./temp-storage/");
				Files.copy(file.getInputStream(), this.rootLocationTem.resolve(imageName));
			} else {
				throw new HttpMessageNotReadableException("you cant add  this image",
						new Throwable("you cant add  this image"));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}

	}

	@Override
	public Resource loadFile(String filename) {
		try {
			Path file =  rootLocationProfileImg.resolve(filename);
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
	public void saveTempImg(String name) throws FileNotFoundException  {
		File f =  new File("./temp-storage/" +  name + ".png");
		File f2 =  new File("./temp-storage/" +  name +  ".jpg");
		
		if(f.exists()) { 
			InputStream in = new FileInputStream(f);
			try {
				this.deleteImgWithSameName(name, "./profile-img-storage/");
				Files.copy(in , this.rootLocationProfileImg.resolve(f.getName()));
				in.close();
				f.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(f2.exists()) { 
			InputStream in = new FileInputStream(f2);
			try {
				this.deleteImgWithSameName(name, "./profile-img-storage/");
				Files.copy(in , this.rootLocationProfileImg.resolve(f2.getName()));
				in.close();
				f2.delete();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void deleteImgWithSameName(String name, String rootDirectory) { 
		File f =  new File(rootDirectory +  name + ".png");
		File f2 =  new File(rootDirectory +  name +  ".jpg");
		if(f.exists()) { 
			f.delete();
		}
		if(f2.exists()) { 
			f2.delete();
		}
	}
	
	private void deleteFile(String fullName, String rootDirectory) {
		File f =  new File(rootDirectory +  fullName);
		if(f.exists()) { 
			f.delete();
		}
	}

}
