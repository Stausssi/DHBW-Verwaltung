package dhbw.verwaltung.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {

    Result(T data) {
        this.data = data;
    }

    private T data;

    @Override
    public String toString() {
        return String.format("%s[data=%s]", getClass().getSimpleName(), data);
    }

    public T getData() {
        return data;
    }

    // Success sub-class
    public final static class Success<T> extends Result<T> {
        public Success(T data) {
            super(data);
        }
    }

    // Error sub-class
    public final static class Error extends Result<Exception> {
        public Error(Exception error) {
            super(error);
        }
    }
}
