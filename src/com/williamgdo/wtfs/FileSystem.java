package com.williamgdo.wtfs;

import java.io.*;
import java.time.Instant;
import java.util.LinkedList;

import static com.williamgdo.wtfs.DefaultValues.DEFAULT_MAX_FILENAME_LENGTH;

public class FileSystem {
    private Superblock superblock;
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

    // transfer file from some file system into this one
    public void uploadFileToFileSystem(String filepath, String filename) {
        File file = new File(filepath);

        if (!isValidFile(file))
            return;

        if (!areResourcesAvailableForNewFile(file.length()))
            return;

        createFile(file, filename);
    }

    public void uploadFileToFileSystem(String filepath) {
        File file = new File(filepath);

        if (file.exists()) {
            uploadFileToFileSystem(filepath, file.getName());
        }
    }

    private static boolean isValidFile(File file) {
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado.");
            return false;
        }

        // TODO: remove when directories are implemented
        if (!file.isFile() && file.isDirectory()) {
            System.out.println("Pastas ainda não foram implementadas.");
            return false;
        }

        if (!file.isFile()) {
            System.out.println("Tipo de arquivo inválido.");
            return false;
        }

        if (!file.canRead()) {
            System.out.println("File is not readable.");
            return false;
        }

        return true;
    }
    private boolean areResourcesAvailableForNewFile(long fileLength) {
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

    private void createFile(File file, String filename) {
        // update inodes
        short index = inodeTable.getIndexForFirstFreeInode();
        long epochTime = Instant.now().toEpochMilli();
        short blocksCount = roundUpLongDivision(file.length(), superblock.getBytesPerSector());
        short[] entries = blockBitmap.getIndexForNumberFreeBlocks(blocksCount);

        Inode newInode = new Inode (
                entries,
                file.length(),
                blocksCount,
                index,
                epochTime,
                epochTime
        );

        inodeTable.insertInodeInIndex(newInode, index);

        // update filenameTable
        filenameTable.setFilenameInId(filename, index);

        // store bytes to data blocks
        try {
            FileInputStream fileStream = new FileInputStream(file.getPath());

            byte[] buffer = new byte[superblock.getBytesPerSector()];
            int bytesRead;
            for (short entry : entries) {
                bytesRead = fileStream.read(buffer);
                dataBlocks.writeByteArrayToSector(buffer, entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // update superblock usedInodes (+1)
        superblock.incrementUsedInodeEntriesBy((short) 1);
        // update superblock usedSector (+N)
        superblock.incrementUsedSectorsBy(blocksCount);

    }

    private short roundUpLongDivision(long a, long b) {
        return (short) (a / b + ((a % b == 0) ? 0 : 1));
    }

    public boolean downloadFileFromFileSystem(String filepathInFs, String filepathToDownload) {
        // get file entries
        Inode inode = inodeTable.inodes[0];

        short[] entries = inode.fatEntries;

        // transfer every byte to a byte stream
        short bufferSize = superblock.getBytesPerSector();
        try (FileOutputStream fos = new FileOutputStream(filepathToDownload)) {
            int offset = 0;
            for (short entry : entries) {
                byte[] data = dataBlocks.readByteArrayFromSector(entry, bufferSize);

                int length = (int) Math.min(bufferSize, inode.size - offset);
                fos.write(data, offset, length);
                offset += length;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // save bytestream into filepathToDownload
        return true;
    }

    public void report() {
        System.out.println("Superblock:    ###########################");
        System.out.println("Inodes: " + superblock.getUsedInodeEntries() + "/" + superblock.getTotalInodeEntries());
        System.out.println("Sectors: " + superblock.getUsedInodeEntries() + "/" + superblock.getTotalSectors());
        System.out.println("Disk Size: " + superblock.getUsedDiskSize() + "/" + superblock.getDiskSize() + " (" + superblock.getAvailableDiskSizePercentage()+ "%)");
    }
}
