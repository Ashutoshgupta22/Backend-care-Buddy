package com.aspark.carebuddy.model.nurse;

import lombok.Getter;
import lombok.Setter;
import com.aspark.carebuddy.security.UserRole;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;


@Getter
@Setter
@Entity
public class Nurse implements UserDetails {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	private String name;
	private int age;

	private double latitude;
	private double longitude;
	private String pincode;

	@NonNull
	private String email;
	@NonNull
	private String password;

	@NonNull
	@Column(name = "firebase_token")
	private String firebaseToken;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	private boolean locked=false;
	private boolean enabled=false;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
