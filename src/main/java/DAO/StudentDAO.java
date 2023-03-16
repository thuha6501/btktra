package DAO;

import connection.MyConnection;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAll() {
        final String sql = "SELECT * FROM `Students`";

        List<Student> brandList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setFull_name(rs.getString("full_name"));
                s.setGender(rs.getInt("gender"));
                s.setDate(rs.getString("date"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));
                s.setMark(rs.getDouble("mark"));

                brandList.add(s);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return brandList;
    }
    public Student getById(String id) {
        final String sql = "SELECT * FROM `Students` WHERE  `id` = " + id;
        Student s = null;

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                s = new Student();
                s.setId(rs.getString("id"));
                s.setFull_name(rs.getString("full_name"));
                s.setGender(rs.getInt("gender"));
                s.setDate(rs.getString("date"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));
                s.setMark(rs.getDouble("mark"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void insert(Student s) {

            final String sql = String.format("INSERT  INTO `Students` VALUES ('%s','%s','%s','%s','%s','%s','%s','%f' ) ",
                    s.getId(), s.getFull_name(), s.getGender(), s.getDate(), s.getAddress(), s.getPhone(), s.getEmail(), s.getMark()
            );
            try {
                Connection conn = MyConnection.getConnection();
                Statement stmt = conn.createStatement();
                long rs = stmt.executeUpdate(sql);

                if (rs == 0) {
                    System.out.println("Thêm thất bại");
                }

                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    public void update(Student student, String id) {
        Student tmp = getById(id);
        if (tmp == null) {
            throw new RuntimeException("id không tồn tại!");
        }

        final String sql = String.format("UPDATE `students` SET `full_name`='%s',`gender`='%s',`date`='%s',`address`='%s',`phone`='%s',`email`='%s',`mark`='%f' WHERE `id` = '%s'",
                student.getFull_name(), student.getGender(), student.getDate(), student.getAddress(),student.getPhone(),student.getEmail(),student.getMark(), id
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void delete(String id) {
        Student product = getById(id);
        if (product == null) {
            throw new RuntimeException("id không tồn tại!");
        }

        final String sql = "DELETE FROM `students` WHERE  `id` = " + id;
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
