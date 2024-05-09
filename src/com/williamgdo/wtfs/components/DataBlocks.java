package com.williamgdo.wtfs.components;

import java.io.*;

public class DataBlocks {
//    where data is actually stored
    short totalSectors, bytesPerSector;
//    ByteArrayOutputStream dataBlocks;
    byte[] dataBlocks;
    public DataBlocks(short totalSectors, short bytesPerSector) {
        this.totalSectors = totalSectors;
        this.bytesPerSector = bytesPerSector;
//        dataBlocks = new ByteArrayOutputStream(totalSectors * bytesPerSector);
        dataBlocks = new byte[totalSectors * bytesPerSector];
    }
    public void writeByteArrayToSector(byte[] byteArray, short sector) {
        write(byteArray, (short) (sector * bytesPerSector), bytesPerSector);
    }

    private void write(byte[] byteArray, short offset, short length) {
        /*
        // manual array copying
        for (int i = 0; i < length; i++) {
            dataBlocks[offset + i] = byteArray[i];
        }
        */

        if (length >= 0) System.arraycopy(byteArray, 0, dataBlocks, offset, length);
    }
    public byte[] readByteArrayFromSector(short sector, short length) {
        byte[] byteArray = new byte[length];
        System.arraycopy(dataBlocks, sector, byteArray, 0, length);
        return byteArray;
    }

    public void saveFileOnDataBlocks(File file, short[] blocks, short bufferLength) {
        try (FileInputStream fileStream = new FileInputStream(file.getPath())) {
            byte[] buffer = new byte[bufferLength];
            int bytesRead;
            for (short block : blocks) {
                bytesRead = fileStream.read(buffer);
                writeByteArrayToSector(buffer, block);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createFileFromInode(Inode inode, String filepathToDownload, short bufferLength) {
        try (FileOutputStream fos = new FileOutputStream(filepathToDownload)) {
            int offset = 0;
            for (short entry : inode.getFatEntries()) {
                byte[] data = readByteArrayFromSector(entry, bufferLength);

                int length = (int) Math.min(bufferLength, inode.size - offset);
                fos.write(data, offset, length);
                offset += length;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
