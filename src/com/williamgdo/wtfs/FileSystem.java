package com.williamgdo.wtfs;

import com.williamgdo.wtfs.components.*;
import com.williamgdo.wtfs.managers.FileManager;
import com.williamgdo.wtfs.managers.ReportManager;
import com.williamgdo.wtfs.managers.ResourceManager;

import java.io.*;

import static com.williamgdo.wtfs.utils.DefaultValues.DEFAULT_MAX_FILENAME_LENGTH;

public class FileSystem {
    private final Superblock superblock;
    private FilenameTable filenameTable;
    private InodeTable inodeTable;
    private BlockBitmap blockBitmap;
    private DataBlocks dataBlocks;

    private void initializeParametersUsingSuperblock() {
        filenameTable = new FilenameTable(DEFAULT_MAX_FILENAME_LENGTH);
        inodeTable = new InodeTable(superblock.getTotalInodeEntries());
        dataBlocks = new DataBlocks(superblock.getTotalSectors(), superblock.getBytesPerSector());
        blockBitmap = new BlockBitmap(superblock.getTotalSectors());
    }
    public FileSystem() {
        superblock = new Superblock(); // default values
        initializeParametersUsingSuperblock();
    }
    public FileSystem(Superblock superblock) {
        this.superblock = superblock;
        initializeParametersUsingSuperblock();
    }

    public FileSystem(short totalSectors, short bytesPerSector, short totalInodeEntries) {
        superblock = new Superblock(totalSectors, bytesPerSector, totalInodeEntries);
        initializeParametersUsingSuperblock();
    }

    public void createFile(File file, String filename) {
        if (!ResourceManager.areResourcesAvailableForNewFile(file.length(), superblock))
            return;

        FileManager.createFile(
                file,
                filename,
                inodeTable,
                superblock,
                blockBitmap,
                filenameTable,
                dataBlocks);
    }

    public boolean downloadFile(String filepathInFs, String filepathToDownload) {
        // get file entries
        // TODO: search for file in table? Make function receive another parameter?
        // idea: pass inode index and make another function to get inode index from the name
        // as subtrees not possible currently, could make a list too
        // as a mockup for testing, I'll pass the first file only (inode at index 0):
        Inode inode = inodeTable.inodes[0];
        short bufferSize = superblock.getBytesPerSector();
        dataBlocks.createFileFromInode(inode, filepathToDownload, bufferSize);
        return true;
    }

    public void printReport() {
        ReportManager.printSuperblockReport(superblock);
    }

//    public void deleteFile()

//    public void renameFile()

//    public void makeDirectory()
}
