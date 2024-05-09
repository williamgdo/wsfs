package com.williamgdo.wtfs;

import com.williamgdo.wtfs.components.Inode;
import com.williamgdo.wtfs.utils.FileValidation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSystemFacade {
    FileSystem fileSystem;

    public FileSystemFacade(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    // transfer file from some file system into this one
    public void uploadFileToFileSystem(String filepath, String filename) {
        File file = new File(filepath);

        if (FileValidation.isInvalidFile(file))
            return;

        fileSystem.createFile(file, filename);
    }

    public void uploadFileToFileSystem(String filepath) {
        File file = new File(filepath);

        if (FileValidation.isInvalidFile(file))
            return;

        fileSystem.createFile(file, file.getName());
    }

    // TODO: check if returns can return errors alongside exceptions
    public boolean downloadFileFromFileSystem(String filepathInFs, String filepathToDownload) {
        return fileSystem.downloadFile(filepathInFs, filepathToDownload);
    }
}
