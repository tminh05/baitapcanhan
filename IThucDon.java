package restaurant_management;

import java.util.List;

/**
 * Interface IThucDon: Dinh nghia cac hanh dong lien quan den Menu.
 */
public interface IThucDon {
    // Phuong thuc them san pham vao menu
    void themSanPham(SanPham sp);

    // Phuong thuc hien thi toan bo menu
    void hienThiMenu();
}