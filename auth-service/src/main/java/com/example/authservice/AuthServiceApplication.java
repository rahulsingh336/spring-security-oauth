package com.example.authservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class AuthServiceApplication {

	@Bean
	CommandLineRunner demo (AccountRepository accountRepository){
		return args -> {
			Stream.of("test,test","spring,boot")
					.map(tpl -> tpl.split(","))
					.forEach(tpl -> accountRepository.save(new Account(tpl[0], tpl[1], true)));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
@Configuration
@EnableAuthorizationServer
class AuthServiceConfiguration extends AuthorizationServerConfigurerAdapter{

	private final AuthenticationManager authenticationManager;

	AuthServiceConfiguration(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
	{
		oauthServer.checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
				.inMemory()
				.withClient("html5")
				.secret("password")
				.authorizedGrantTypes("authorization_code","refresh_token")//, "authorization_code", "refresh_token")
				.scopes("read", "write", "testApproval")
				.accessTokenValiditySeconds(6000);
				/*.authorizedGrantTypes("password")
				.authorizedGrantTypes.add("refresh_token");
        .authorizedGrantTypes.add("client_credentials");*/
        		//.authorizedGrantTypes("authorization_code")
				//.scopes("user_info");
				/*.scopes("openid");*/


	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       endpoints.authenticationManager(authenticationManager);
	}
}

@Service
class AccountUserDetailsService implements UserDetailsService{

	private final AccountRepository accountRepository;

	AccountUserDetailsService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return accountRepository.findByUserName(userName)
				.map(account -> new User(account.getUserName(),
						                 account.getPassword(),
						                 account.isActive(),
						                  account.isActive(),
						account.isActive(),
						account.isActive(),
						AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")
				)).orElseThrow(() -> new UsernameNotFoundException("Could not found user Name " + userName));
	}
}

interface AccountRepository  extends JpaRepository<Account, Long>{
	Optional<Account> findByUserName(String userName);
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Account{

	@Id
	@GeneratedValue
	private Long id;

	private String userName, password;

	private boolean active;

	public Account(String userName, String password, boolean active) {

		this.userName = userName;
		this.password = password;
		this.active = active;
	}
}
