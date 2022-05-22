public interface Sequential<T> {
    public void setNext(T next);
    public T getNext();
    public void setPrevious(T previous);
    public T getPrevious();
}