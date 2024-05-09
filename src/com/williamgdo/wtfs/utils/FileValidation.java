package com.williamgdo.wtfs.utils;

import java.io.File;

public class FileValidation {
    public static boolean isInvalidFile(File file) {
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado.");
            return true;
        }

        // TODO: remove when directories are implemented
        if (!file.isFile() && file.isDirectory()) {
            System.out.println("Pastas ainda não foram implementadas.");
            return true;
        }

        if (!file.isFile()) {
            System.out.println("Tipo de arquivo inválido.");
            return true;
        }

        if (!file.canRead()) {
            System.out.println("File is not readable.");
            return true;
        }

        return false;
    }
}
