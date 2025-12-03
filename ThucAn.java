package restaurant_management;

/**
 * Lop ThucAn: Dai dien cho cac mon an (Khai vi, Mon chinh, Trang mieng).
 */
public class ThucAn extends SanPham {
    // Thuoc tinh mo ta loai mon an
    private String loai; 

    public ThucAn(String ten, double gia, String loai) {
        super(ten, gia);
        this.loai = loai;
    }

    @Override
    public void hienThiThongTin() {
        System.out.printf("[Thuc An] %s (%s) - Gia: %.0f VND\n", ten, loai, gia);
    }
}