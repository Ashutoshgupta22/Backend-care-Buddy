package com.aspark.carebuddy.model.user;

import com.aspark.carebuddy.security.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class User implements UserDetails {


	@SequenceGenerator(
			name="user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1)
//	@Id
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "user_sequence"
//	)
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	@Column(name = "first_name")
	private String firstName;
	@NonNull
	@Column(name = "last_name")
	private String lastName;
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

		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(simpleGrantedAuthority);
	}

	@Override
	public String getUsername() {
		return email;
	}

	public boolean isLocked() {
		return locked;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + firstName + '\'' +
				", age=" + age +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", userRole=" + userRole +
				", locked=" + locked +
				", enabled=" + enabled +
				'}';
	}
}
