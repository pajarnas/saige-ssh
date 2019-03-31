package com.lyn.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import com.lyn.model.enums.Role;
import com.lyn.model.enums.Stage;

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


@Entity
@PrimaryKeyJoinColumn(name = "taskid")
@Table(name="_ptask")
@DiscriminatorValue("生产")
@Polymorphism(type= PolymorphismType.EXPLICIT)
@Getter @Setter @NoArgsConstructor 
public class PTask extends Task{
	
	 @Enumerated(EnumType.STRING)
	    private Stage stage;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="ptask")
	@Column(nullable=true)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<OTask> sub_otasks;
	
	@OneToOne
	private User pur_user;
	
	@OneToOne
	private User s_user;
	
	@OneToOne
	private User pr_user;
	
	

	
    
}