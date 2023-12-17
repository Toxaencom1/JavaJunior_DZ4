package com.taxah.jj.dz4;

import com.taxah.jj.dz4.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The DB class provides methods to interact with a database using Hibernate for CRUD operations
 * on the Courses table. It implements the AutoCloseable interface to ensure proper closure of
 * the Hibernate SessionFactory.
 */
public class DB implements AutoCloseable {
    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();

    /**
     * Adds a new Course record to the Courses table in the database.
     *
     * @param course The Course object to be added.
     */
    public void addCourseRow(Course course) {
        try (Session session = factory.getCurrentSession()) {
            // Add
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            System.out.println("    Created: " + course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a Course record from the Courses table based on the provided ID.
     *
     * @param id The ID of the Course to be retrieved.
     * @return The Course object retrieved from the database.
     */
    public Course getCourseRow(int id) {
        try (Session session = factory.getCurrentSession()) {
            //Get
            session.beginTransaction();
            Course workWithCourse = session.get(Course.class, id);
            System.out.println("    Get course: " + workWithCourse);
            session.close();
            return workWithCourse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a Course record from the Courses table based on the provided ID.
     *
     * @param title The title of the list Courses to be retrieved.
     * @return The Courses list object retrieved from the database by title.
     */
    public List<Course> getCourseListByTitle(String title) {
        try (Session session = factory.getCurrentSession()) {
            //Get
            session.beginTransaction();
            String hql = "FROM Course WHERE title = :courseTitle";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("courseTitle", title);

            // Получите результат в виде списка
            List<Course> courses = query.getResultList();
            session.getTransaction().commit();
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all Course records from the Courses table.
     *
     * @return A list of all Course objects in the database.
     */
    public List<Course> getAllCourses() {
        try (Session session = factory.getCurrentSession()) {
            //Get
            session.beginTransaction();
            List<Course> courses = session.createQuery("from Course", Course.class).getResultList();

            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the duration of a Course record in the Courses table.
     *
     * @param course   The Course object to be updated.
     * @param duration The new duration value.
     */
    public void updateCourse(Course course, int duration) {
        try (Session session = factory.getCurrentSession()) {
            // Update
            session.beginTransaction();
            Course workWithCourse = session.get(Course.class, course.getId());
            workWithCourse.setDuration(duration);
            session.update(workWithCourse);
            session.getTransaction().commit();
            System.out.println("    Changed: " + workWithCourse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the title of a Course record in the Courses table.
     *
     * @param course The Course object to be updated.
     * @param title  The new title value.
     */
    public void updateCourse(Course course, String title) {
        try (Session session = factory.getCurrentSession()) {
            // Update
            session.beginTransaction();
            course.setTitle(title);
            session.update(course);
            session.getTransaction().commit();
            System.out.println("    Changed: " + course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates both the title and duration of a Course record in the Courses table.
     *
     * @param course   The Course object to be updated.
     * @param title    The new title value.
     * @param duration The new duration value.
     */
    public void updateCourse(Course course, String title, int duration) {
        try (Session session = factory.getCurrentSession()) {
            // Update
            session.beginTransaction();
            course.setTitle(title);
            course.setDuration(duration);
            session.update(course);
            session.getTransaction().commit();
            System.out.println("    Changed: " + course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a Course record from the Courses table.
     *
     * @param course The Course object to be deleted.
     */
    public void deleteCourseRow(Course course) {
        try (Session session = factory.getCurrentSession()) {
            //Create and add
            session.beginTransaction();
            session.delete(course);
            session.getTransaction().commit();
            System.out.printf("    Course '%s' was deleted!!!\n", course.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the Hibernate SessionFactory when the DB object is closed.
     *
     * @throws Exception If an exception occurs during closure.
     */
    @Override
    public void close() throws Exception {
        factory.close();
    }
}
