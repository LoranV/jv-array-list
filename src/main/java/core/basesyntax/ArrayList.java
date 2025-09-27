package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CONFIRMED_SIZE = 10;
    private int size = 0;
    private Object[] array;

    public ArrayList() {
        array = new Object[CONFIRMED_SIZE];
    }

    private void grow(int size) {
        int newSize = size + (size >> 1);
        array = Arrays.copyOf(array, newSize);
    }

    @Override
    public void add(T value) {
        if (size < array.length) {
            array[size++] = value;
        } else {
            grow(size);
            array[size++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size >= array.length) {
                grow(size);
            }
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("List size: " + size);
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void set(T value, int index) {
        try {
            if (index >= 0 && index < size) {
                array[index] = value;
            } else {
                throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            final T tempDel = (T) array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = null;
            size--;
            return tempDel;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public T remove(T element) {
        int elementIndex = -1;
        for (int i = 0; i < size; i++) {
            if (array[i] == null ? array[i] == element : array[i].equals(element)) {
                elementIndex = i;
            }
        }
        if (elementIndex >= 0 && elementIndex < size) {
            final T tempDel = (T) array[elementIndex];
            for (int i = elementIndex; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[size - 1] = null;
            size--;
            return tempDel;
        } else {
            throw new NoSuchElementException("Index: " + elementIndex + ", Size: " + size);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
}
