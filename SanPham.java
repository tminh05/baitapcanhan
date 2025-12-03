package restaurant_management;

/**
 * Lop tru tuong SanPham: Co so cho ThucAn va DoUong.
 */
public abstract class SanPham {
    protected String ten;
    protected double gia;

    public SanPham(String ten, double gia) {
        this.ten = ten;
        this.gia = gia;
    }

    // Phuong thuc tru tuong de hien thi thong tin chi tiet
    public abstract void hienThiThongTin();

    // Getters
    public String getTen() {
        return ten;
    }

    public double getGia() {
        return gia;
    }
}