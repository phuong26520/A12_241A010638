package vn.edu.vhu.ltdd.a12sqlite;
 
/** Một dòng trong bảng sinh_vien. */
public class SinhVien {
 
    private long id;            // khóa chính, do SQLite tự sinh
    private String maSv;
    private String hoTen;
    private String lop;
    private double diem;
 
    public SinhVien(long id, String maSv, String hoTen, String lop, double diem) {
        this.id = id;
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.lop = lop;
        this.diem = diem;
    }
 
    /** Dùng khi tạo mới: chưa có id vì SQLite sẽ cấp. */
    public SinhVien(String maSv, String hoTen, String lop, double diem) {
        this(-1, maSv, hoTen, lop, diem);
    }
 
    public long getId() { return id; }
    public String getMaSv() { return maSv; }
    public String getHoTen() { return hoTen; }
    public String getLop() { return lop; }
    public double getDiem() { return diem; }
 
    public void setId(long id) { this.id = id; }
}
