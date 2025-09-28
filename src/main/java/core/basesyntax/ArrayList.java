package core.basesyntax;

public class ArrayList<T> implements List<T> {
    private static final int CONFIRMED_SIZE = 10;
    private int size = 0;
    private Object[] listArray;

    public ArrayList() {
        listArray = new Object[CONFIRMED_SIZE];
    }

    private void grow(int size) {
        int newSize = size + (size >> 1);
        Object[] tempArray = listArray;
        listArray = new Object[newSize];
        System.arraycopy(tempArray, 0, listArray, 0, size);
    }

    private boolean validateElementIndex(int index) {
        return index >= 0 && index <= size;
    }

    @Override
    public void add(T value) {
        if (size == listArray.length) {
            grow(size);
        }
        listArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size >= listArray.length) {
                grow(size);
            }
            int numMoved = size - index;
            if (numMoved > 0) {
                System.arraycopy(listArray, index, listArray, index + 1, numMoved);
            }
            listArray[index] = value;
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
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) listArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            listArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            final T tempDel = (T) listArray[index];
            int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(listArray, index + 1, listArray, index, numMoved);
            }
            listArray[--size] = null;
            return tempDel;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public T remove(T element) {
        int elementIndex = -1;
        for (int i = 0; i < size; i++) {
            if (listArray[i] == null ? listArray[i] == element : listArray[i].equals(element)) {
                elementIndex = i;
                break;
            }
        }
        if (elementIndex >= 0 && elementIndex < size) {
            final T tempDel = (T) listArray[elementIndex];
            for (int i = elementIndex; i < size - 1; i++) {
                listArray[i] = listArray[i + 1];
            }
            listArray[size - 1] = null;
            size--;
            return tempDel;
        } else {
            throw new java.util.NoSuchElementException("Element not found: " + element);
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
