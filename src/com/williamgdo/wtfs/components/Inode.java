package com.williamgdo.wtfs.components;
import com.williamgdo.wtfs.utils.MathFunctions;

import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;

// INode = index node
public class Inode {
//    short fatEntry; // First data block
//    LinkedList<Short> fatEntries = new LinkedList<>();
    short[] fatEntries;
    long size;  // Size of the file in bytes

    public short getBlocksCount() {
        return blocksCount;
    }

    short blocksCount; // how many blocks allocated to this file
    short id;

    public void setId(short id) {
        this.id = id;
    }

    public short[] getFatEntries() {
        return fatEntries;
    }

    public short getId() {
        return id;
    }

    long time; // what time was this file last accessed?
    long createTime; // what time was this file created?

    /*
        short linksCount; // how many links are there to this file
        String type; // dir, link, file
        short indirectPointer; // multi-level index
        char mode; // read/written/executed
        char fileAccessControlList; // contains rules that grant or deny access to certain digital
    */

    public Inode(short[] fatEntries, long size, short blocksCount, short id, long time, long createTime) {
        this.fatEntries = fatEntries;
        this.size = size;
        this.blocksCount = blocksCount;
        this.id = id;
        this.time = time;
        this.createTime = createTime;
    }

    public Inode(short[] fatEntries, long size, short blocksCount, short id) {
        long epochTime = Instant.now().toEpochMilli();

        this.fatEntries = fatEntries;
        this.size = size;
        this.blocksCount = blocksCount;
        this.id = id;
        this.time = epochTime;
        this.createTime = epochTime;
    }

    public Inode() {
        long epochTime = Instant.now().toEpochMilli();
        this.time = epochTime;
        this.createTime = epochTime;
    }

    @Override
    public String toString() {
        return "Inode{" +
                "fatEntries=" + Arrays.toString(fatEntries) +
                ", size=" + size +
                ", blocksCount=" + blocksCount +
                ", id=" + id +
                ", time=" + time +
                ", createTime=" + createTime +
                "}\n";
    }

    public void setBlocksFromInputFile(BlockBitmap blockBitmap, short sectorLength, long fileLength) {
        short blocksCount = MathFunctions.roundUpLongDivision(fileLength, sectorLength);

        this.fatEntries = blockBitmap.getIndexForNumberFreeBlocks(blocksCount);
        this.size = fileLength;
        this.blocksCount = blocksCount;
    }
}
