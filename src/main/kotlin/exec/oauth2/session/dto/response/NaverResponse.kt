package exec.oauth2.session.dto.response

class NaverResponse(attribute: Map<String, Any>) : OAuth2Response {
    private val attribute = attribute["response"] as Map<*, *>

    val provider: String
        get() = "naver"

    val providerId: String
        get() = attribute["id"].toString()

    val email: String
        get() = attribute["email"].toString()

    val name: String
        get() = attribute["name"].toString()
}