package com.williamgdo.wtfs.managers;

import com.williamgdo.wtfs.components.Superblock;

public class ResourceManager {
    public static boolean areResourcesAvailableForNewFile(long fileLength, Superblock superblock) {
        if (superblock.getAvailableInodes() < 1) {
            System.out.println("The file system has no available INodes to create the file.");
            return false;
        }

        if (superblock.getAvailableDiskSize() < fileLength) {
            System.out.println("The file system has no free space to create the file.");
            return false;
        }

        return true;
    }
}
