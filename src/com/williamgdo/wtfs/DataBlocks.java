package com.williamgdo.wtfs;

import java.io.ByteArrayOutputStream;

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

}
