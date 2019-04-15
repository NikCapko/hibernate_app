package code;

import java.util.List;

public class StudentService {

  private StudentDao studentDao = new StudentDao();

  public StudentService() {}

  public Student findStudent(int id) {
    return studentDao.findById(id);
  }

  public void saveStudent(Student student) {
    studentDao.save(student);
  }

  public void deleteStudent(Student student) {
    studentDao.delete(student);
  }

  public void updateStudent(Student student) {
    studentDao.update(student);
  }

  public List<Student> findAllStudent() {
    return studentDao.findAll();
  }
}
