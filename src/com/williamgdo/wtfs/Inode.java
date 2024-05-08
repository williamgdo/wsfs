package com.williamgdo.wtfs;
import java.util.Arrays;
import java.util.LinkedList;

// INode = index node
public class Inode {
//    short fatEntry; // First data block
//    LinkedList<Short> fatEntries = new LinkedList<>();
    short[] fatEntries;
    long size;  // Size of the file in bytes
    short blocksCount; // how many blocks allocated to this file
    short id;
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
}
