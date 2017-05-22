public interface Filter<T>
{
    boolean accept(T element);
}