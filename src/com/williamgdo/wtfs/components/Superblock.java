package com.williamgdo.wtfs.components;

import static com.williamgdo.wtfs.utils.DefaultValues.*;

public class Superblock {
//    @Serial
//    private static final long serialVersionUID = 0L;

    private final short totalSectors;        // Total number of sectors
    private short usedSectors;    // Number of used sectors
    private final short bytesPerSector;     // Number of bytes in each sector, ex: 512, 4096
    private final short totalInodeEntries;     // Total number of inode entries
    private short usedInodeEntries; // Number of available inode entries
    public short getTotalSectors() {
        return totalSectors;
    }

    public short getBytesPerSector() {
        return bytesPerSector;
    }

    public short getTotalInodeEntries() {
        return totalInodeEntries;
    }
//    short sectors_per_cluster;  // Number of sectors in each block

    public Superblock() {
        this.totalSectors = DEFAULT_SUPERBLOCK_SECTORS;
        this.usedSectors = 0;
        this.bytesPerSector = DEFAULT_SUPERBLOCK_BYTES_PER_SECTOR;
        this.totalInodeEntries = DEFAULT_SUPERBLOCK_TOTAL_INODE_ENTRIES;
        this.usedInodeEntries = 0;
    }
    public Superblock(short totalSectors, short bytesPerSector, short totalInodeEntries) {
        this.totalSectors = totalSectors;
        this.usedSectors = 0;
        this.bytesPerSector = bytesPerSector;
        this.totalInodeEntries = totalInodeEntries;
        this.usedInodeEntries = 0;
    }

    public short getUsedSectors() {
        return usedSectors;
    }

    public short getUsedInodeEntries() {
        return usedInodeEntries;
    }

    public short getAvailableInodes() {
        return (short) (totalInodeEntries - usedInodeEntries);
    }

    public short getAvailableSectors() {
        return (short) (totalSectors - usedSectors);
    }

    public int getAvailableDiskSize() {
        return getAvailableSectors() * bytesPerSector;
    }
    public int getUsedDiskSize() {
        return usedSectors * bytesPerSector;
    }

    public int getDiskSize() {
        return totalSectors * bytesPerSector;
    }

    public Double getAvailableDiskSizePercentage() {
        return (double) ((usedSectors * bytesPerSector) / getDiskSize());
    }

    public void incrementUsedSectorsBy(short amount) {
        usedSectors += amount;
    }

    public void decrementUsedSectorsBy(short amount) {
        usedSectors -= amount;
    }

    public void incrementUsedInodeEntriesBy(short amount) {
        usedInodeEntries += amount;
    }

    public void decrementUsedInodeEntriesBy(short amount) {
        usedInodeEntries -= amount;
    }
}
