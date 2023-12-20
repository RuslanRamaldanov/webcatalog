package com.Ramaldanov.webcatalog.service;
import com.Ramaldanov.webcatalog.entity.UsersFile;
import com.Ramaldanov.webcatalog.exceptions.FileNameNotFoundException;
import com.Ramaldanov.webcatalog.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    public UsersFile loadFileByFileName(String fileName) throws FileNameNotFoundException {
        UsersFile file = fileRepository.findByFileName(fileName);
        if(file == null)
            throw new FileNameNotFoundException();
        return file;
    }
    public boolean saveFile (UsersFile file) throws FileNameNotFoundException{
        UsersFile fileFromDB = fileRepository.findByFileName(file.getFileName());
        if(fileFromDB == null) {
            fileRepository.save(file);
            return true;
        }
        return false;
    }
    public boolean deleteFile(Long id) {
        if(fileRepository.findById(id).isPresent()) {
            fileRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<UsersFile> loadAll() {
        return fileRepository.findAll();
    }
    public boolean isExist(UsersFile file) throws FileNameNotFoundException {
        UsersFile fileFromDB = fileRepository.findByFileName(file.getFileName());
        return !(fileFromDB == null);
    }
    public UsersFile getFileById(Long id) {
        Optional<UsersFile> file = fileRepository.findById(id);
        return file.orElse(null);
    }
    public void updateData(Long id, String author, Integer year, List<String> keyWords, String articleName) {
        UsersFile file = getFileById(id);
        file.setAuthor(author);
        file.setYear(year);
        file.setKeyWords(keyWords);
        file.setArticleName(articleName);
        fileRepository.save(file);
    }

    public List<UsersFile> foundFiles(String author, Integer year, List<String> keyWords, String articleName) {
        List<UsersFile> files = loadAll();
        System.out.println(author.length()+" " + year+" " + articleName.length());
        files.removeIf(file ->
                !(
                (author.length() != 0 & file.getAuthor().equals(author)) &
                (year != null & file.getYear().equals(year)) &
                (articleName.length() != 0 & file.getArticleName().equals(articleName))
                ));

        if(files.size() == 0) {
            List<UsersFile> keyWordFiles = new ArrayList<>();
            for(UsersFile file : loadAll()) {
                boolean containsAllElements = true;
                for(String keyWord : keyWords) {
                    if(!file.getKeyWords().contains(keyWord)) {
                        containsAllElements = false;
                        break;
                    }
                }
                if (containsAllElements)
                    keyWordFiles.add(file);
            }
            return keyWordFiles;
        }
        //                file.getKeyWords().equals(keyWords) ||
        return files;
    }
}
