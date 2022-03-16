package com.qa.census.entity;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity

public class Saiyan {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@Column
		@Min(13)
		@Max(65)
		private int age;
		
		@Column(nullable = false)
		private String name;
		
		@Column
		private String sex;
		
		@Column
		private int powerLevel;
		
		@Column
		private Boolean isSSj;
		
		// Default Constructor
		public Saiyan() {}
		
		// Saiyan Generating Constructor		
		public Saiyan (@Min(13) @Min(65) int age, String name, String sex, int powerLevel, Boolean isSSj) {
			super();
			this.age = age;
			this.name = name;
			this.sex = sex;
			this.powerLevel = powerLevel;
			this.isSSj = isSSj;
			}
		
		// Saiyan Test
		public Saiyan(long id, @Min(13) @Max(65) int age, String name, String sex, int powerLevel, Boolean isSSj) {
			super();
			this.id = id;
			this.age = age;
			this.name = name;
			this.sex = sex;
			this.powerLevel = powerLevel;
			this.isSSj = isSSj;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public int getPowerLevel() {
			return powerLevel;
		}

		public void setPowerLevel(int powerLevel) {
			this.powerLevel = powerLevel;
		}

		public Boolean getIsSSj() {
			return isSSj;
		}

		public void setIsSSj(Boolean isSSj) {
			this.isSSj = isSSj;
		}

		@Override
		public String toString() {
			return "This a " + age + " year-old " + sex + " Saiyan named " + name + ". Their Power Level is: " + powerLevel;
			
		}
		
		// Used to verify objects

		@Override
		public int hashCode() {
			return Objects.hash(age, id, isSSj, name, powerLevel, sex);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Saiyan other = (Saiyan) obj;
			return age == other.age && id == other.id && Objects.equals(isSSj, other.isSSj)
					&& Objects.equals(name, other.name) && powerLevel == other.powerLevel
					&& Objects.equals(sex, other.sex);
		}

		
		
		
		
		
		
}