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
    public void addStudentTeacherPair(String teacherName, String studentName){
        List<String> studentList = new ArrayList<>();
        if(teacherStudentHashMap.containsKey(teacherName)){
            studentList = teacherStudentHashMap.get(teacherName);
            studentList.add(studentName);
            teacherStudentHashMap.put(teacherName, studentList);
        }
        else{
            studentList.add(studentName);
            teacherStudentHashMap.put(teacherName, studentList);
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
    public void deleteTeacherByName(String teacherName){
        List<String> studentList = new ArrayList<>();
        if(teacherStudentHashMap.containsKey(teacherName)){
            studentList = teacherStudentHashMap.get(teacherName);
            for (String student : studentList){
                if(studentHashMap.containsKey(student)){
                    studentHashMap.remove(student);
                }
            }
            if(teacherHashMap.containsKey(teacherName)){
                teacherHashMap.remove(teacherName);
            }
            teacherStudentHashMap.remove(teacherName);
        }
    }
    public void deleteAllTeachers(){
        teacherHashMap = new HashMap<>();
        HashSet<String> studentSet = new HashSet<>();
        for(String tName : teacherStudentHashMap.keySet()){
            for(String sName : teacherStudentHashMap.get(tName)){
                studentSet.add(sName);
            }
        }
        for(String sName : studentSet){
            if(studentHashMap.containsKey(sName)){
                studentHashMap.remove(sName);
            }
        }
        teacherStudentHashMap = new HashMap<>();
    }
}
