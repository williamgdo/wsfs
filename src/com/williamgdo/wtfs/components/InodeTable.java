package com.williamgdo.wtfs.components;

public class InodeTable {
    public Inode[] inodes;

    public InodeTable(short length) {
        this.inodes = new Inode[length];
    }

    public short getIndexForFirstFreeInode() {
        for (int i = 0; i < inodes.length; i++) {
            if(inodes[i] == null)
                return (short) i;
        }
        return -1;
    }

    public void insertIntoInodeTable(Inode inode) {
        short index = getIndexForFirstFreeInode();
        inode.setId(index);
        inodes[index] = inode;
//        insertInodeInIndex(inode, index);
    }

//    public void insertInodeInIndex(Inode inode, int index) {
//        inodes[index] = inode;
//    }
}
