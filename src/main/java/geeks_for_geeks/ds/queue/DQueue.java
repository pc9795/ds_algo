package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.queue.adt.Queue;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 05:20
 **/
public class DQueue extends QueueUsingArray implements Queue {

    public DQueue() {
        super();
    }

    public DQueue(int size) {
        super(size);
    }

    /**
     * t=O(1)
     *
     * @param value data to add
     * @return calling instance
     */
    public DQueue insertFront(int value) {
        assert !isFull() : "Queue is full";
        if (front == -1) {
            front = values.length - 1;
        } else {
            front--;
        }
        values[front] = value;
        return this;
    }

    /**
     * t=O(1)
     *
     * @return data at the rear
     */
    public int deleteLast() {
        assert !isEmpty() : "Queue is empty";
        rear--;
        if (rear == -1) {
            rear = values.length - 1;
        }
        int value = values[rear];
        if (front == rear) {
            front = -1;
            rear = 0;
        }
        return value;
    }
}
