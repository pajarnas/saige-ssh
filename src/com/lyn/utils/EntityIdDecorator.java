package com.lyn.utils;

import com.lyn.model.*;
import java.util.Random;
/**
 * @author    Yaning Liu
 *
 * @filename  EntityIdGenerator.java
 *
 * @date      2019-03-24
 *
 */
public class EntityIdDecorator {
	public EntityIdDecorator(Task task) {
		
		task.setId(id);
		Random r = Random();
		r.nextInt(99);
		
	}
}
