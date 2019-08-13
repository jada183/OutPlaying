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
	private final Path rootLocationPostImg = Paths.get("post-img-storage");

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ICredentialRepository credentialRepository;

	@Override
	public String storeTemporaryProfileImage(MultipartFile file) {
		try {
			if (Validator.isAuthenticated()) {
				// tras comprobar si se esta autentificado, saco el valor de las credenciales
				// obteniendo primero el usuario con el id de usuario
				// que almace la clase authentication y a partir de este saco las credenciales
				// para guardar el nombre del archivo con el userName
				// que es unique.asi guardo una imagen de usuario para cada uno.
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				User user = userRepository.getOne(Long.parseLong(authentication.getName()));
				Credential credential = credentialRepository.credentialByIdUSer(user);
				// para obtener la extension de la imagen.
				String[] extension = file.getOriginalFilename().split("\\.");
				String imageName = credential.getUsername() + "." + extension[1];
				// metodo que elimina la imagen actual de perfil si se encuentra.
				this.deleteImgWithSameName(credential.getUsername(), "./temp-storage/");
				Files.copy(file.getInputStream(), this.rootLocationTem.resolve(imageName));
				return imageName;
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
	public void saveTempImgProfileImg(String name) throws FileNotFoundException {
		File f = new File("./temp-storage/" + name);

		if (f.exists()) {
			InputStream in = new FileInputStream(f);
			try {
				String[] splitName = name.split("\\.");
				this.deleteImgWithSameName(splitName[0], "./profile-img-storage/");
				Files.copy(in, this.rootLocationProfileImg.resolve(f.getName()));
				in.close();
				f.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void saveTempImgPostImg(String name, String idPost) throws FileNotFoundException {
		File f = new File("./temp-storage/" + name);

		if (f.exists()) {
			InputStream in = new FileInputStream(f);
			try {
				String[] splitName = name.split("\\.");
				this.deleteImgWithSameName(splitName[0] + idPost , "./post-img-storage/");
				String [] fileNameSpliting = f.getName().split("\\.");
				String nameFilePersist =  fileNameSpliting[0] + idPost + "." + fileNameSpliting[1];
				Files.copy(in, this.rootLocationPostImg.resolve(nameFilePersist));
				in.close();
				f.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String storeTemporaryPostImage(MultipartFile file) {
		try {
			if (Validator.isAuthenticated()) {
				// tras comprobar si se esta autentificado, saco el valor de las credenciales
				// obteniendo primero el usuario con el id de usuario
				// que almace la clase authentication y a partir de este saco las credenciales
				// para guardar el nombre del archivo con el userName
				// que es unique.asi guardo una imagen de usuario para cada uno.
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				User user = userRepository.getOne(Long.parseLong(authentication.getName()));
				Credential credential = credentialRepository.credentialByIdUSer(user);
				// para obtener la extension de la imagen.
				String[] extension = file.getOriginalFilename().split("\\.");
				String imageName = credential.getUsername() + "Post." + extension[1];
				// metodo que elimina la imagen actual de perfil si se encuentra.
				this.deleteImgWithSameName(credential.getUsername(), "./temp-storage/");
				Files.copy(file.getInputStream(), this.rootLocationTem.resolve(imageName));
				return imageName;
			} else {
				throw new HttpMessageNotReadableException("you cant add  this image",
						new Throwable("you cant add  this image"));
			}
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}

	}

	private void deleteImgWithSameName(String name, String rootDirectory) {
		File f = new File(rootDirectory + name + ".png");
		File f2 = new File(rootDirectory + name + ".jpg");
		if (f.exists()) {
			f.delete();
		}
		if (f2.exists()) {
			f2.delete();
		}
	}

	

}
