package com.taxah.jj.dz4;


import com.taxah.jj.dz4.entity.Course;

/*
Создайте базу данных (например, SchoolDB).
В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
Настройте Hibernate для работы с вашей базой данных.
Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */

/**
 * This class demonstrates the usage of Hibernate with a database (e.g., SchoolDB). It includes
 * the creation of a Courses table with fields id (primary key), title, and duration. Hibernate is
 * configured to interact with the database. The Java class Course corresponds to the Courses table
 * with the necessary Hibernate annotations.
 * <p>
 * Using Hibernate, the code performs operations such as insertion, retrieval, update, and deletion
 * of data in the Courses table. Each operation is executed within a separate transaction to ensure
 * data integrity.
 * <p>
 * The main method showcases these operations by creating a new Course, adding it to the database,
 * updating its information, retrieving it, and finally, deleting it from the Courses table. The
 * results are printed to the console for demonstration purposes.
 */
public class Main {
    /**
     * The entry point of the application, demonstrating Hibernate operations on the Courses table.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        try (DB dataBase = new DB()) {
            // Display all existing courses in the Courses table
            System.out.println("=================================================================================" +
                    "====================================================");
            dataBase.getAllCourses().forEach(System.out::println);

            // Create a new course and add it to the Courses table
            Course newCourse = new Course("Java Junior", 1);
            dataBase.addCourseRow(newCourse);

            // Update the information of the new course using different parameters
            dataBase.updateCourse(newCourse, "Please Wait");
            dataBase.updateCourse(newCourse, 2);
            dataBase.updateCourse(newCourse, "Spring soon", 1);

            // Retrieve and display the updated information of the new course
            Course course = dataBase.getCourseRow(newCourse.getId());
            System.out.println("For demonstrate: " + course);

            // Delete the new course from the Courses table
            dataBase.deleteCourseRow(newCourse);

            // Display all remaining courses in the Courses table after deletion
            dataBase.getAllCourses().forEach(System.out::println);

            System.out.println("Select by title = " + dataBase.getCourseListByTitle("IoT"));
            System.out.println("Select first by title = " + dataBase.getFirstCourseByTitle("Java"));
            System.out.println("=================================================================================" +
                    "====================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
