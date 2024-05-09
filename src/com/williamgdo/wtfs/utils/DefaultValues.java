package com.williamgdo.wtfs.utils;

public final class DefaultValues {
    // this class purpose is to provide constants used during the application execution
    private DefaultValues() {
        // No need to instantiate the class, we can hide its constructor
    }
    public static final short DEFAULT_SUPERBLOCK_SECTORS = 512;
    public static final short DEFAULT_SUPERBLOCK_BYTES_PER_SECTOR = 4096;
    public static final short DEFAULT_SUPERBLOCK_TOTAL_INODE_ENTRIES = 4096;
    public static final short DEFAULT_MAX_FILENAME_LENGTH = 255;
}
