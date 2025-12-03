package restaurant_management;

/**
 * Lop DoUong: Dai dien cho cac loai do uong (Nuoc suoi, Coca, Pepsi).
 */
public class DoUong extends SanPham {
    // Thuoc tinh mo ta loai do uong
    private String loai;

    public DoUong(String ten, double gia, String loai) {
        super(ten, gia);
        this.loai = loai;
    }

    @Override
    public void hienThiThongTin() {
        System.out.printf("[Do Uong] %s (%s) - Gia: %.0f VND\n", ten, loai, gia);
    }
}