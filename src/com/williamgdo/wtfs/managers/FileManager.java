package com.williamgdo.wtfs.managers;

import com.williamgdo.wtfs.FileSystem;
import com.williamgdo.wtfs.components.*;
import com.williamgdo.wtfs.utils.MathFunctions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;

public class FileManager {
    public static void createFile(
            File file,
            String filename,
            InodeTable inodeTable,
            Superblock superblock,
            BlockBitmap blockBitmap,
            FilenameTable filenameTable,
            DataBlocks dataBlocks
    ) {
        short sectorLength = superblock.getBytesPerSector();
        Inode inode = new Inode();
        inode.setBlocksFromInputFile(blockBitmap, sectorLength, file.length());
        inodeTable.insertIntoInodeTable(inode);
        filenameTable.setFilenameInId(filename, inode.getId());
        dataBlocks.saveFileOnDataBlocks(file, inode.getFatEntries(), sectorLength);
        superblock.incrementUsedInodeEntriesBy((short) 1);
        superblock.incrementUsedSectorsBy(inode.getBlocksCount());
    }


}

