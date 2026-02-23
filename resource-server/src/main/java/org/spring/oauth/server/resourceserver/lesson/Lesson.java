package org.spring.oauth.server.resourceserver.lesson;

import java.time.LocalDateTime;

public class Lesson {
  private String title;
  private String description;
  private String instructor;
  private LocalDateTime schedule;

  public Lesson(String title, String description, String instructor, LocalDateTime schedule) {
    this.title = title;
    this.description = description;
    this.instructor = instructor;
    this.schedule = schedule;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getInstructor() {
    return instructor;
  }

  public LocalDateTime getSchedule() {
    return schedule;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  public void setSchedule(LocalDateTime schedule) {
    this.schedule = schedule;
  }
}
