package com.apptest

class User {

    def userService

    String username
    String password
    Date dateCreated
    Role role

    static constraints = {
        username blank: false
        password blank: false
        dateCreated()
        role nullable: false
    }

    static mapping = {
        table("users")
        role(fetch: 'join')
    }

    Set<Role> getAuthorities() {
        [role]
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        log.debug("User.beforeUpdate()")
        if (isDirty('password')) {
            log.debug("User.beforeUpdate() dirty password")
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = userService.encodePassword(password)
    }

    @Override
    String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", version=" + version +
                ", role=" + roleId +
                '}';
    }
}
