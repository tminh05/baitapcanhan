package restaurant_management;

import java.util.List;

/**
 * Interface ITinhTien: Dinh nghia cac hanh dong lien quan den tinh toan.
 */
public interface ITinhTien {
    // Phuong thuc tinh tong tien cua mot danh sach goi mon
    double tinhTongTien(List<SanPham> danhSachGoiMon);

    // Phuong thuc ap dung giam gia (neu co)
    double apDungGiamGia(double tongTien, double phanTramGiamGia);
}