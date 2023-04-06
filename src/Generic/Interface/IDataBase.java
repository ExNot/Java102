package Generic.Interface;

public interface IDataBase <T> {

    boolean insert(T data);
    boolean delete(T data);
    boolean update(T data);
    T select();

}
