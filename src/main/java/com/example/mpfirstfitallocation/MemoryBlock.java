package com.example.mpfirstfitallocation;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MemoryBlock {
    private final IntegerProperty id;
    private final IntegerProperty blockSize;
    private final IntegerProperty processSize;
    private final BooleanProperty allocated;

    public MemoryBlock(int id, int blockSize, int processSize, boolean allocated) {
        this.id = new SimpleIntegerProperty(id);
        this.blockSize = new SimpleIntegerProperty(blockSize);
        this.processSize = new SimpleIntegerProperty(processSize);
        this.allocated = new SimpleBooleanProperty(allocated);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public IntegerProperty blockSizeProperty() {
        return blockSize;
    }

    public IntegerProperty processSizeProperty() {
        return processSize;
    }

    public BooleanProperty allocatedProperty() {
        return allocated;
    }

    public int getId() {
        return id.get();
    }

    public int getBlockSize() {
        return blockSize.get();
    }

    public int getProcessSize() {
        return processSize.get();
    }

    public boolean isAllocated() {
        return allocated.get();
    }
}
