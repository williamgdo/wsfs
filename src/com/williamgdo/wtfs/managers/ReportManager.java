package com.williamgdo.wtfs.managers;

import com.williamgdo.wtfs.components.Superblock;

public class ReportManager {
    public static void printSuperblockReport(Superblock superblock) {
        System.out.println("Superblock:    ###########################");
        System.out.println("Inodes: " + superblock.getUsedInodeEntries() + "/" + superblock.getTotalInodeEntries());
        System.out.println("Sectors: " + superblock.getUsedInodeEntries() + "/" + superblock.getTotalSectors());
        System.out.println("Disk Size: " + superblock.getUsedDiskSize() + "/" + superblock.getDiskSize() + " (" + superblock.getAvailableDiskSizePercentage()+ "%)");
    }
}
