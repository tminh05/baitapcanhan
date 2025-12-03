package restaurant_management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import lớp Scanner để nhập liệu

/**
 * Lop QuanLyNhaHang: Thuc thi cac interfaces IThucDon va ITinhTien.
 */
public class QuanLyNhaHang implements IThucDon, ITinhTien {
    private List<SanPham> menu;
    private static Scanner scanner = new Scanner(System.in); // Khai bao Scanner

    public QuanLyNhaHang() {
        this.menu = new ArrayList<>();
    }

    // --- PHUONG THUC TU INTERFACE IThucDon/ITinhTien (Giu nguyen) ---
    
    @Override
    public void themSanPham(SanPham sp) {
        menu.add(sp);
    }

    @Override
    public void hienThiMenu() {
        System.out.println("\n====== MENU HIEN TAI ======");
        if (menu.isEmpty()) {
            System.out.println("Menu hien tai dang trong.");
            return;
        }
        for (int i = 0; i < menu.size(); i++) {
            SanPham sp = menu.get(i);
            // Hien thi STT cho de chon
            System.out.print((i + 1) + ". ");
            sp.hienThiThongTin();
        }
        System.out.println("===========================");
    }

    @Override
    public double tinhTongTien(List<SanPham> danhSachGoiMon) {
        double tong = 0;
        for (SanPham sp : danhSachGoiMon) {
            tong += sp.getGia();
        }
        return tong;
    }

    @Override
    public double apDungGiamGia(double tongTien, double phanTramGiamGia) {
        if (phanTramGiamGia < 0 || phanTramGiamGia > 100) {
            return tongTien; 
        }
        return tongTien * (1 - phanTramGiamGia / 100);
    }
    
    // --- PHUONG THUC XU LY NHAP LIEU TU BAN PHIM ---

    /**
     * Cho phep nguoi dung nhap thong tin de them san pham vao menu.
     */
    public void themSanPhamTuBanPhim() {
        System.out.println("\n--- Them San Pham Moi ---");
        System.out.print("Nhap ten san pham: ");
        String ten = scanner.nextLine();

        double gia = 0;
        System.out.print("Nhap gia san pham (VND): ");
        try {
            gia = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Gia khong hop le. Dat mac dinh la 0.");
        }
        
        System.out.print("San pham la (1: Thuc An / 2: Do Uong): ");
        String loaiSP = scanner.nextLine();

        if (loaiSP.equals("1")) {
            System.out.print("Nhap loai Thuc An (Khai vi/Mon chinh/Trang mieng): ");
            String loaiThucAn = scanner.nextLine();
            themSanPham(new ThucAn(ten, gia, loaiThucAn));
            
        } else if (loaiSP.equals("2")) {
            System.out.print("Nhap loai Do Uong (Nuoc suoi/Coca/Pepsi): ");
            String loaiDoUong = scanner.nextLine();
            themSanPham(new DoUong(ten, gia, loaiDoUong));
            
        } else {
            System.out.println("Lua chon khong hop le. Khong the them san pham.");
            return;
        }
        System.out.println(">>> Da them '" + ten + "' vao menu thanh cong.");
    }
    
    /**
     * Cho phep nguoi dung goi mon bang cach nhap STT san pham.
     */
    public void goiMonTuBanPhim() {
        if (menu.isEmpty()) {
            System.out.println("Khong the goi mon, menu hien tai dang trong.");
            return;
        }
        
        List<SanPham> hoaDon = new ArrayList<>();
        String luaChon = "";
        
        do {
            hienThiMenu();
            System.out.print("Nhap STT san pham muon goi (Nhap '0' de ket thuc goi mon): ");
            luaChon = scanner.nextLine();
            
            try {
                int stt = Integer.parseInt(luaChon);
                if (stt == 0) {
                    break; // Thoat vong lap khi nhap 0
                }
                
                if (stt > 0 && stt <= menu.size()) {
                    SanPham spChon = menu.get(stt - 1);
                    hoaDon.add(spChon);
                    System.out.println("-> Da them: " + spChon.getTen() + " vao hoa don.");
                } else {
                    System.err.println("STT khong hop le. Vui long chon lai.");
                }
            } catch (NumberFormatException e) {
                if (!luaChon.equals("0")) {
                    System.err.println("Nhap lieu khong hop le. Vui long nhap so STT.");
                }
            }
        } while (true);
        
        // Hien thi hoa don sau khi ket thuc goi mon
        xuLyHoaDon(hoaDon);
    }
    
    /**
     * Xu ly va in ra hoa don cuoi cung.
     */
    private void xuLyHoaDon(List<SanPham> hoaDon) {
        if (hoaDon.isEmpty()) {
            System.out.println("\nKhong co mon nao duoc chon. Ket thuc.");
            return;
        }
        
        System.out.println("\n--- HOA DON CUA BAN ---");
        double tongTienChuaGiam = tinhTongTien(hoaDon);
        
        System.out.println("Cac mon da goi:");
        for (SanPham item : hoaDon) {
            System.out.printf("- %s (%.0f VND)\n", item.getTen(), item.getGia());
        }
        
        System.out.printf("\nTong tien (Chua giam gia): %.0f VND\n", tongTienChuaGiam);
        
        // Them chuc nang giam gia tuy chon
        System.out.print("Nhap phan tram giam gia (Nhap 0 neu khong co): ");
        double phanTramGiam = 0;
        try {
            phanTramGiam = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            phanTramGiam = 0;
        }
        
        double tongTienSauGiam = apDungGiamGia(tongTienChuaGiam, phanTramGiam);
        System.out.printf("Thanh tien (Giam %.0f%%): %.0f VND\n", phanTramGiam, tongTienSauGiam);
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        QuanLyNhaHang quanLy = new QuanLyNhaHang();
        int choice;

        // Them mot so san pham mac dinh de test nhanh
        quanLy.themSanPham(new ThucAn("Salad Dua leo kem chua", 40000, "Khai vi"));
        quanLy.themSanPham(new ThucAn("Goi cuon tom dac biet", 70000, "Khai vi"));
        quanLy.themSanPham(new ThucAn("Bo Sot Tieu Xanh", 180000, "Mon chinh"));
        quanLy.themSanPham(new ThucAn("Com chien dac biet", 80000, "Mon chinh"));
        quanLy.themSanPham(new DoUong("Nuoc suoi Aquafina 500ml", 20000, "Do uong"));
        quanLy.themSanPham(new DoUong("Coca Zero ly", 30000, "Do uong"));
        
        do {
            System.out.println("\n===== CHUONG TRINH QUAN LY NHA HANG =====");
            System.out.println("1. Hien thi Menu");
            System.out.println("2. Them San Pham Moi vao Menu");
            System.out.println("3. Goi Mon");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Nhap lua chon cua ban: ");
            
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1; // Neu nhap chu, dat choice khac 0 de tiep tuc loop
            }

            switch (choice) {
                case 1:
                    quanLy.hienThiMenu();
                    break;
                case 2:
                    quanLy.themSanPhamTuBanPhim();
                    break;
                case 3:
                    quanLy.goiMonTuBanPhim();
                    break;
                case 0:
                    System.out.println("Da thoat chuong trinh. Tam biet!");
                    break;
                default:
                    System.err.println("Lua chon khong hop le. Vui long thu lai.");
                    break;
            }
        } while (choice != 0);
        
        scanner.close(); // Dong Scanner khi chuong trinh ket thuc
    }
}