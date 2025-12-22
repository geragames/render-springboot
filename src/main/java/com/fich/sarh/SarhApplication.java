	package com.fich.sarh;

import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.PermissionEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.UserRepository;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.repository.MovementRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

	@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class SarhApplication {//implements WebMvcConfigurer {

	private final MovementRepository retrieveServicePort;

	public SarhApplication(MovementRepository retrieveServicePort) {
		this.retrieveServicePort = retrieveServicePort;
	}


	public static void main(String[] args) {
		SpringApplication.run(SarhApplication.class, args);
	}

	/*@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for(HttpMessageConverter<?> converter:converters) {
			if(converter instanceof MappingJackson2HttpMessageConverter){
				ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
				mapper.registerModule(new Hibernate5Module());
			}
		}

	}*/

/*	@Bean
	public ApplicationRunner init(UserRepository userRepository) {
		return args -> {

			//
			// CompletableFuture.supplyAsync()

			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE").build();

			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ").build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE").build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE").build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR").build();

			RoleEntity roleAdmin = RoleEntity.builder().roleEnum(RoleEnum.ADMIN)
					.permissionSet(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleEntity roleUser = RoleEntity.builder().roleEnum(RoleEnum.USER)
					.permissionSet(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder().roleEnum(RoleEnum.INVITED)
					.permissionSet(Set.of(readPermission))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder().roleEnum(RoleEnum.DEVELOPER)
					.permissionSet(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			// CREAR USUARIO

			UserEntity userSantiago = UserEntity.builder()
					.username("santiago")
					.email("santiago@gmail.com")
					.password("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialNonExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.email("daniel@gmail.com")
					.password("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialNonExpired(true)
					.roles(Set.of(roleUser))
					.build();


			UserEntity userAndrea = UserEntity.builder()
					.username("andrea")
					.email("andrea@gmail.com")
					.password("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialNonExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userAnyi = UserEntity.builder()
					.username("anyi")
					.email("anyi@gmail.com")
					.password("1234")
					.isEnabled(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialNonExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();


            userRepository.saveAll(List.of(userDaniel, userSantiago, userAndrea, userAnyi));


		}
	}; */
}
