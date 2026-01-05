package br.com.llucascr.services;

import br.com.llucascr.config.FileStorageConfig;
import br.com.llucascr.exception.FileNotFoundException;
import br.com.llucascr.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where files will be stored", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains a invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + fileName + " . Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filaPath = this.fileStorageLocation.resolve(fileName);
            Resource resource = new UrlResource(filaPath.toUri());

            if (resource.exists()) {
                return resource;
            } else  {
                throw new FileNotFoundException("File not found " + fileName);
            }

        } catch (Exception e) {
            throw new FileNotFoundException("File not found " + fileName, e);
        }
    }

}
