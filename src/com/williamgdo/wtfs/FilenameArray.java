package com.williamgdo.wtfs;

public class FilenameArray {
    private final String[] filenames; // Adjust the size as needed

    public FilenameArray(int size) {
        filenames = new String[size];
        // Initialize the array with null
        for (int i = 0; i < size; i++) {
            filenames[i] = null;
        }
    }

    public String getFilenameFromId(int inodeId) {
        return filenames[inodeId];
    }
    public void setFilenameInId(String name, int inodeId) {
        filenames[inodeId] = name;
    }

    // TODO: Change String to array of char
    // char[][] charArrayArray = new char[5][256];

}
