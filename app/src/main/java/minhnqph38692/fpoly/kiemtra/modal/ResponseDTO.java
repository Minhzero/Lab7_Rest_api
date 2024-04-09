package minhnqph38692.fpoly.kiemtra.modal;

public class ResponseDTO<T> {
    int status;
    String messager;
    T data;

    public ResponseDTO() {
    }

    public ResponseDTO(int status, String messager, T data) {
        this.status = status;
        this.messager = messager;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessager() {
        return messager;
    }

    public void setMessager(String messager) {
        this.messager = messager;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
