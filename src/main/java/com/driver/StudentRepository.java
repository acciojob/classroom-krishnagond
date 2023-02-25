package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentHashMap = new HashMap<>();
    HashMap<String, Teacher> teacherHashMap = new HashMap<>();
    HashMap<String, List<String>> teacherStudentHashMap = new HashMap<>();

    public void addStudent(Student student){
        String studentName = student.getName();
        studentHashMap.put(studentName, student);
    }
    public void addTeacher(Teacher teacher){
        String teacherName = teacher.getName();
        teacherHashMap.put(teacherName, teacher);
    }
    public void addStudentTeacherPair(String teacher, String student){
        if (teacherStudentHashMap.containsKey(teacher)){
            List<String> teacher_list = teacherStudentHashMap.get(teacher);
            teacher_list.add(student);
            teacherStudentHashMap.put(teacher,teacher_list);
        }else {
            List<String> teacher_list = new ArrayList<>();
            teacher_list.add(student);
            teacherStudentHashMap.put(teacher,teacher_list);
        }
    }
    public Student getStudentByName(String studentName){
        for(String sName : studentHashMap.keySet()){
            if(sName.equals(studentName)){
                return studentHashMap.get(sName);
            }
        }
        return null;
    }
    public Teacher getTeacherByName(String teacherName){
        for(String tName : studentHashMap.keySet()){
            if(tName.equals(teacherName)){
                return teacherHashMap.get(tName);
            }
        }
        return null;
    }
    public List<String> getStudentsByTeacherName(String teacherName){
        List<String> studentList = new ArrayList<>();
        if(teacherStudentHashMap.containsKey(teacherName)){
            return teacherStudentHashMap.get(teacherName);
        }
        return studentList;
    }
    public List<String> getAllStudents(){
        List<String> studentList = new ArrayList<>();
        for(String sName: studentHashMap.keySet()){
            studentList.add(sName);
        }
        return studentList;
    }
    public void deleteTeacherByName(String teacher){
        List<String> student_list = teacherStudentHashMap.get(teacher);
        for (String s: student_list){
            studentHashMap.remove(s);
        }
        teacherHashMap.remove(teacher);
        teacherStudentHashMap.remove(teacher);
    }
    public void deleteAllTeachers(){
        for (List<String> student_list: teacherStudentHashMap.values()){
            for (String s: student_list){
                studentHashMap.remove(s);
            }
        }
        teacherHashMap.clear();
        teacherStudentHashMap.clear();
    }
}
