package com.williamgdo.wtfs;

public class Superblock {
    private static final long serialVersionUID = 0L;
    short totalSectors;        // Total number of sectors
    short usedSectors;    // Number of used sectors
    short bytesPerSector;     // Number of bytes in each sector, ex: 512, 4096
    short totalInodeEntries;     // Total number of inode entries
    short usedInodeEntries; // Number of available inode entries

    //    short sectors_per_cluster;  // Number of sectors in each block
}
