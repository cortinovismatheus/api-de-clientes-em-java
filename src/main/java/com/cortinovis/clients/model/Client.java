package com.cortinovis.clients.model;


import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String phone;

  @Column(name = "social_media" ,nullable = false)
  private String socialMedia;

  @Column(nullable = false)
  private Boolean active;

  public  Client() {}

  public Client (Long id, String name, String email, String phone, String socialMedia, Boolean active) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.socialMedia = socialMedia;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSocialMedia() {
    return socialMedia;
  }

  public void setSocialMedia(String socialMedia) {
    this.socialMedia = socialMedia;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}