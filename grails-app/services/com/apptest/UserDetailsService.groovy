package com.apptest

import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.transaction.Transactional
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Transactional
class UserDetailsService implements GrailsUserDetailsService {


    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        return loadUserByUsername(username)
    }

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User.withTransaction { status ->
            log.debug('loadUserByUsername: '+username)
            User user = User.findByUsername(username)
            if (!user) {
                throw new UsernameNotFoundException('Пользователь не найден', username)
            }

            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(Role.get(user.roleId).authority));

            return new GrailsUser(user.username,user.password,true,true,true,true,authorities,user.id)
        }
    }
}
