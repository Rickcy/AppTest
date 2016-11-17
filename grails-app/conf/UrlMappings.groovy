class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/registration"(controller: 'signup',view: 'create')
        "/"(controller: 'front',view:'front/index')
        "500"(view:'/error')
        "404"(view: '/notFound')
	}
}
