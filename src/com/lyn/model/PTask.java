package com.lyn.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Getter @Setter @NoArgsConstructor 
public class PTask extends Task{
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ptask_children")
	private List<Task> child_putasks;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ptask_children")
	private List<Task> fk_stask;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ptask_children")
	private List<Task> child_prtasks;
    
}