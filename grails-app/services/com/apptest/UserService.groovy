package com.apptest

import grails.transaction.Transactional

@Transactional
class UserService {

    def springSecurityService


    @Transactional
    User createUser(String username, String password, Role role) {
        User user = new User(
                username: username,
                password: password,
                role: role,

        )
        log.debug("Saving")
        user.save(flush: true)
        return user
    }
    String encodePassword(String password) {
        springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

}
