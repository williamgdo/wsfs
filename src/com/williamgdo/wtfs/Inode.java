package com.williamgdo.wtfs;

import java.time.LocalDateTime;

public class Inode {
    short fatEntry; // First data block
    int size;        // Size of the file in bytes
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
}
