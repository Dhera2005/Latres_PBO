/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import db.Koneksi;
import dto.InsertTodoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M S I
 */
public class DatabaseTodoRepository extends TodoRepository {
    
    @Override
    public List<TodoTask> getAll() {
        List<TodoTask> list = new ArrayList<>();
        
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM todos";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TodoTask task = new TodoTask(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("status")
                );
                list.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public Boolean insert(InsertTodoDTO dto) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO todos(title, status) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Boolean update(TodoTask task) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE todos SET title=?, status=? WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus());
            ps.setInt(3, task.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Boolean deleteById(int id) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "DELETE FROM todos WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public TodoTask getById(int id) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM todos WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TodoTask(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}