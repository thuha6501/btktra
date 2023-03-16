import DAO.StudentDAO;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    private static StudentDAO studentDAO = new StudentDAO();

    private static void mainMenu() {
        System.out.println("--- QUẢN LÝ SẢN PHẨM ---");
        System.out.println("1. Danh sách sinh viên");
        System.out.println("2. Thêm một sinh viên mới");
        System.out.println("3. Cập nhập thông tin sinh viên");
        System.out.println("4. Xóa một sinh viên theo mã");
        System.out.println("5. Tìm một sinh viên theo họ và têm hoặc mẫ gần đúng");
        System.out.println("5. Sắp xếp sinh viên theo điểm số GPA tng dần");
        System.out.println("7. in ra tất cả sinh viên nữ ở Hà Nội có GPA trên 2.5");
        System.out.println("8. Xắp xếp sinh viên theo họ tên, bảng chữ cái");
    }

    private static void option1() {
        List<Student> studentList = studentDAO.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s", "MÃ sinh viên", "Họ tên", "Giới tính", "Địa chỉ");
        System.out.println();
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            System.out.printf("%-20d %-20s %-20d %-20s\n", (i+1), s.getFull_name(), s.getGender(),s.getAddress());
        };
    }

    private static void option2(Scanner in) {
        Student s = new Student();
        System.out.print("\tNhập id:");
        s.setId(in.nextLine());
        System.out.print("\tNhập tên: ");
        s.setFull_name(in.nextLine());
        System.out.print("\tNhập giới tính: ");
        s.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("\tNhập ngày tháng năm sinh: ");
        s.setDate(in.nextLine());
        System.out.print("\tNhập địa chỉ: ");
        s.setAddress(in.nextLine());
        System.out.print("\tNhập số điện thoại: ");
        s.setPhone(in.nextLine());
        System.out.print("\tNhập email:");
        s.setEmail(in.nextLine());
        System.out.print("\tNhâp điểm: ");
        s.setMark(in.nextDouble());
        StudentDAO.insert(s);
        System.out.println("\tthêm thành công");
         in.nextLine();
    }
    private static void option3(Scanner in){
        List<Student> studentsList=new ArrayList<>();
        System.out.println("nhap ma nhan vien muon xoa");
        String id=in.nextLine();
        studentDAO.delete(id);
        List<Student> studentList = studentDAO.getAll();
        System.out.println(studentList);
    }
    private static void option4(Scanner in){
       StudentDAO updateStudent= new StudentDAO();
        Student s= new Student();
        System.out.println("Nhap vao ma sinh vien");
        String ma=in.nextLine();
        System.out.print("Ten sinh vien \t");

        s.setFull_name(in.nextLine());
        System.out.print("Nhập gioi tinh \t");
        s.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("Nhap vao ngay thang nam sinh \t");
        s.setDate(in.nextLine());
        System.out.print("Nhap vao sdt \t");
        s.setPhone(in.nextLine());
        System.out.print("Nhap email\t");
        s.setEmail(in.nextLine());
        System.out.print("Nhap dia chi\t");
        s.setAddress(in.nextLine());
        System.out.print("Nhap vao diem \t");
        s.setMark(in.nextDouble());
        updateStudent.update(s,ma);
        in.nextLine();

    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int option = -1;
        do {
            mainMenu();
            System.out.print("Nhập lựa chọn: ");
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng!");
                continue;
            }
            if (option < 1 || option > 8) {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    option3(in);
                    break;
                case 4:
                    option4(in);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }


        }
        while (option != 0);
        in.close();

    }

}
