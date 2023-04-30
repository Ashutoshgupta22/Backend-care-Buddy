package org.mitraz.MITRAz.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mitraz.MITRAz.security.UserRole;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
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
	private String name;
	@NonNull
	private int age;

	private double latitude;
	private double longitude;
	private String pincode;

	@NonNull
	private String email;
	@NonNull
	private String password;

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
				", name='" + name + '\'' +
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
