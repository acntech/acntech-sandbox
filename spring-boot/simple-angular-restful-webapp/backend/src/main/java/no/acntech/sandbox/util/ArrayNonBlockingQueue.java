package no.acntech.sandbox.util;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayNonBlockingQueue<E> extends ArrayBlockingQueue<E> {

    private int size;

    public ArrayNonBlockingQueue(int capacity) {
        super(capacity);
        this.size = capacity;
    }

    @Override
    public boolean add(E e) {
        if (super.size() == size) {
            this.remove();
        }
        return super.add(e);
    }
}
