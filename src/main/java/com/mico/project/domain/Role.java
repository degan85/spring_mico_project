package com.mico.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="role")
@Data
@Getter @Setter @NoArgsConstructor
public class Role {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  public Role(String name){
    this.name = name;
  }  
  
}