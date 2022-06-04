package com.practice.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.practice.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// File Name
		String name = file.getOriginalFilename();

		// Random Name Generate File
		String randomId = UUID.randomUUID().toString();
		String randomFileName = randomId.concat(name.substring(name.lastIndexOf(".")));

		// Full Path
		String filePath = path + File.separator + randomFileName;

		// Create Folder If Not Created
		File f = new File(path);
		if (!f.exists())
			f.mkdir();

		Files.copy(file.getInputStream(), Paths.get(filePath));

		return randomFileName;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}
}
