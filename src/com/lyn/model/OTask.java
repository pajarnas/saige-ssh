package com.lyn.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author    Yaning Liu
 *
 * @filename  PTask.java
 *
 * @date      2019-02-16
 *
 */

//other tasks extends to Task
@Entity
@PrimaryKeyJoinColumn(name = "taskid")
@Table(name="_otask")
@DiscriminatorValue("其他")
@Polymorphism(type= PolymorphismType.EXPLICIT)
@Getter @Setter @NoArgsConstructor 
public class OTask extends Task{
	
	@ManyToOne
    @JoinColumn(name="ptask_id", nullable=false)
    private PTask ptask;
	
}