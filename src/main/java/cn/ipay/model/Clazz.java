package cn.ipay.model;

import java.util.HashSet;
import java.util.Set;

public class Clazz {

	private long id;
	private String name;
	private Set<Student> students = new HashSet<Student>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Clazz{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
