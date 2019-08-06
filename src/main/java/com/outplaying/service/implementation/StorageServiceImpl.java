package com.outplaying.service.implementation;

import java.io.File;
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

	private final Path rootLocation = Paths.get("temp-storage");
	
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
				File f =  new File("./upload-dir/" + imageName);
				if(f.exists()) { 
					f.delete();
				}
				Files.copy(file.getInputStream(), this.rootLocation.resolve(imageName));
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
			Path file = rootLocation.resolve(filename);
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

}
