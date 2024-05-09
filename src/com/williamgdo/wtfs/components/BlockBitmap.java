package com.williamgdo.wtfs.components;

public class BlockBitmap {
    boolean[] table;

    public BlockBitmap(int length) {
        table = new boolean[length];
    }

    public short getIndexForFirstFreeBlock() {
        for (int i = 0; i < table.length; i++) {
            if(!table[i])
                return (short) i;
        }
        return -1;
    }

    public short[] getIndexForNumberFreeBlocks(int numberOfFreeBlocks) {
        short[] freeBlocks = new short[numberOfFreeBlocks];
        short cont = 0;

        for (short i = 0; i < table.length && cont < numberOfFreeBlocks; i++) {
            if(!table[i]) {
                freeBlocks[cont] = i;
                cont++;
            }
        }

        if (cont == numberOfFreeBlocks)
            return freeBlocks;
        else
            return null;
    }
}
