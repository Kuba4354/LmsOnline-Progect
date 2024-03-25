import database.DataBase;
import group.GeneredID;
import group.Group;
import group.Lessons;
import group.Student;
import role.Admin;
import service.AdminService;
import service.GroupService;
import service.Impl.AdminIml;
import service.Impl.GroupServiceImpl;
import service.Impl.LessonServiceImpl;
import service.Impl.StudentServiceImpl;
import service.StudentService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        if (time.isAfter(LocalTime.of(5, 0, 0)) && time.isBefore(LocalTime.of(12, 0, 0))) {
            System.out.println("Кутман тан саат! -> " + time.format(formatter));
        } else if (time.isAfter(LocalTime.of(12, 0, 0)) && time.isBefore(LocalTime.of(18, 0, 0))) {
            System.out.println("Кутман кун!->" + time.format(formatter));
        } else
            System.out.println("Кутман кеч! Саат->" + time.format(formatter));

        Scanner scanner = new Scanner(System.in);
        Scanner scanForStr = new Scanner(System.in);

        DataBase dataBase = new DataBase();
        GroupService group = new GroupServiceImpl(dataBase);
        LessonServiceImpl lesson = new LessonServiceImpl(dataBase);
        StudentService student = new StudentServiceImpl(dataBase);
        AdminService adminService = new AdminIml();
        Admin admin = new Admin();

        boolean isTrue = true;
        while (isTrue) {
            try {

                System.out.println("Катталган болсонуз 1 басыныз, пароль унутуп калып, озгортуу учун 2 басыныз, 3 чыгуу!");
                int buttonLog = scanner.nextInt();
                if (buttonLog == 1) {
                    Admin login = adminService.login(admin);
                    if (login == null) {
                        System.out.println("туура эмес жаздыныз!!!");
                    } else {
                        System.out.println("Кош келиниз");
                        innerLoop:
                        while (true) {
                            try {
                                mainMenu();
                                switch (scanner.nextInt()) {
                                    case 0 -> {
                                        break innerLoop;
                                    }
                                    case 1 -> System.out.println(group.addGroup());
                                    case 2 -> {
                                        System.out.print("Группанын атын жазыныз: ");
                                        String groupName = scanForStr.nextLine();
                                        Group groupByName = group.getGroupByName(groupName);
                                        if (groupByName == null) {
                                            System.out.println("туура эмес жаздыныз!!!");
                                        } else System.out.println(groupByName);
                                    }
                                    case 3 -> {
                                        System.out.print("Группанын атын жазыныз: ");
                                        String groupName = scanForStr.nextLine();
                                        System.out.println(group.upDateGroup(groupName));
                                    }
                                    case 4 -> System.out.println(group.getAllGroups());
                                    case 5 -> {
                                        System.out.print("Группанын атын жазыныз: ");
                                        String groupName = scanForStr.nextLine();
                                        List<Student> students = student.addStudent(groupName);
                                        if (students == null) {
                                            System.out.println("туура эмес жаздыныз!");
                                        } else System.out.println(students);
                                    }
                                    case 6 -> {
                                        System.out.print("Студенттин email жазыныз: ");
                                        String email = scanForStr.nextLine();
                                        System.out.println(student.upDateStudentName(email));
                                    }
                                    case 7 -> {
                                        System.out.print("Студенттин атын жазыныз: ");
                                        String nameStudent = scanForStr.nextLine();
                                        Student list = student.findStudent(nameStudent);
                                        if (list == null) {
                                            System.out.println("туура эмес жаздыныз!");
                                        } else {
                                            System.out.println(list);
                                        }
                                    }
                                    case 8 -> {
                                        System.out.print("Группанын атын жазыныз: ");
                                        String groupName = scanForStr.nextLine();
                                        List<Student> studentList = student.getAllStudentsByGroup(groupName);
                                        if (studentList == null) {
                                            System.out.println("туура эмес жаздыныз!");
                                        } else {
                                            System.out.println(studentList);
                                        }
                                    }
                                    case 9 -> {
                                        System.out.print("Студенттин id жазыныз: ");
                                        long id = scanner.nextLong();
                                        List<Lessons> allLessonsStudent = student.getAllLessonsStudent(id);
                                        if (allLessonsStudent == null) {
                                            System.out.println("туура эмес жаздыныз!");
                                        } else {
                                            System.out.println(allLessonsStudent);
                                        }
                                    }
                                    case 10 -> {
                                        System.out.print("Студенттин email жазыныз: ");
                                        String email = scanForStr.nextLine();
                                        System.out.println(student.deleteStudent(email));
                                    }
                                    case 11 -> {
                                        System.out.print("Группанын атын жазыныз: ");
                                        String groupName = scanForStr.nextLine();
                                        List<Lessons> addLesson = lesson.addLesson(groupName);
                                        if (addLesson == null) {
                                            System.out.println("туура эмес жаздыныз!");
                                        } else {
                                            System.out.println(addLesson);
                                        }
                                    }
                                    case 12 -> {
                                        System.out.print("Сабактын атын жазыныз: ");
                                        String lessonName = scanForStr.nextLine();
                                        Lessons lessonLesson = lesson.getLesson(lessonName);
                                        if (lessonLesson == null) {
                                            System.out.println("туура эмес жаздыныз!");
                                        } else {
                                            System.out.println(lessonLesson);
                                        }
                                    }
                                    case 13 -> {
                                        System.out.print("Группанын атын жазыныз: ");
                                        String groupName = scanForStr.nextLine();
                                        List<Lessons> lessonList = lesson.getAllLessonByGroupName(groupName);
                                        if (lessonList == null) {
                                            System.out.println("туура эмес жаздыныз!");
                                        } else {
                                            System.out.println(lessonList);
                                        }
                                    }
                                    case 14 -> {
                                        System.out.print("Сабактын ID жазыныз: ");
                                        long id = scanner.nextLong();
                                        System.out.println(lesson.deleteLessonById(id));
                                    }
                                    case 15 -> {
                                        System.out.print("Группанын атын жазыныз: ");
                                        String groupName = scanForStr.nextLine();
                                        System.out.println(group.deleteGroup(groupName));
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.err.println("Enter valid Integer");
                                scanner.nextLine();
                            }
                        }
                    }
                } else if (buttonLog == 2) {
                    Admin changed = adminService.changePassword(admin);
                    if (changed == null) {
                        System.out.println("туура эмес жаздыныз!");
                    } else {
                        System.out.println(changed);
                    }
                } else if (buttonLog == 3) {
                    isTrue = false;
                }
            } catch (InputMismatchException e) {
                System.err.println("Enter valid Integer");
                scanner.nextLine();
            }
        }
    }


    private static void mainMenu() {
        System.out.println("""
                ╔════════════════════════════════════════════╗
                ║            Добро пожаловать!               ║
                ╠════════════════════════════════════════════╣
                ║ 0  -> Выйти.                               ║
                ║ 1  -> Добавить новую группу.               ║
                ║ 2  -> Получить группу по названию.         ║
                ║ 3  -> Обновить название группы.            ║
                ║ 4  -> Получить список всех групп.          ║
                ║ 5  -> Добавить нового студента в группу.   ║
                ║ 6  -> Обновить информацию о студенте.      ║
                ║ 7  -> Найти студента по имени.             ║
                ║ 8->Получить список всех студентов в группе.║
                ║ 9 -> Получить список всех уроков студента. ║
                ║ 10-> Удалить студента по электронной почте.║
                ║ 11 -> Добавить новый урок в группу.        ║
                ║ 12 -> Получить урок по названию.           ║
                ║ 13 -> Получить список всех уроков в группе.║
                ║ 14 -> Удалить урок по идентификатору.      ║
                ║ 15 -> Удалить группу по названию.          ║
                ╚════════════════════════════════════════════╝
                                
                """);


        System.out.print("Команда: ");
    }
}
