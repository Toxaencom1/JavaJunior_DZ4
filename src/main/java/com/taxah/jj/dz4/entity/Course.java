package com.taxah.jj.dz4.entity;


import javax.persistence.*;

/**
 * The Course class represents a course entity in the database with the corresponding Hibernate annotations.
 * It is mapped to the "Courses" table, where each course has an ID (primary key), title, and duration.
 */
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "duration")
    private int duration; // in years

    /**
     * Default constructor for Hibernate usage.
     */
    public Course() {
    }

    /**
     * Parameterized constructor to create a Course instance with specified title and duration.
     *
     * @param title    The title of the course.
     * @param duration The duration of the course in years.
     */
    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    /**
     * Retrieves the title of the course.
     *
     * @return The title of the course.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the course.
     *
     * @param title The new title of the course.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the duration of the course in years.
     *
     * @return The duration of the course.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the course in years.
     *
     * @param duration The new duration of the course.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Retrieves the ID (primary key) of the course.
     *
     * @return The ID of the course.
     */
    public int getId() {
        return id;
    }

    /**
     * Generates a string representation of the Course object, including ID, title, and duration.
     *
     * @return A string representation of the Course object.
     */
    @Override
    public String toString() {
        return "Course{" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", duration = " + duration +
                " year(s)}";
    }
}
