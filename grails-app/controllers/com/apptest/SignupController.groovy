package com.apptest

import org.springframework.security.access.annotation.Secured

@Secured(["isAnonymous()"])
class SignupController {

    def springSecurityService
    def userService


    def create() {}

    def save(SignupForm formInstance){
        if (formInstance == null) {
            flash.message = "Bad request"
            redirect(action: 'index')
            return
        }


    }
}
class SignupForm {
    String login
    String password
    String passwordRepeat
}
