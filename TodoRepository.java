/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dto.InsertTodoDTO;
import java.util.List;

/**
 *
 * @author M S I
 */
public abstract class TodoRepository {
    public abstract List<TodoTask> getAll();
    
    public abstract TodoTask getById(int id);
    
    public abstract Boolean insert(InsertTodoDTO insertTodoDTO);
    
    public abstract Boolean update(TodoTask todoTask);
    
    public abstract Boolean deleteById(int id);
}
