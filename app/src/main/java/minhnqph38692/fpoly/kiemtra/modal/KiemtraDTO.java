package minhnqph38692.fpoly.kiemtra.modal;

import com.google.gson.annotations.SerializedName;

public class KiemtraDTO {

    @SerializedName("_id")
     String id;
    String name, maSV;
    int diem;
    private String createdAt;
    private String updatedAt;

    public KiemtraDTO() {
    }

    public KiemtraDTO(String id, String name, String maSV, int diem, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.maSV = maSV;
        this.diem = diem;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
