package vn.edu.hcmut.core.model.hibernate.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "privileges")
public class Privilege implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long privilegeId;
    private String privilegeName;
    private String note;
// 
//    @ManyToMany(mappedBy = "privileges")
//    private Collection<Role> roles;
}
