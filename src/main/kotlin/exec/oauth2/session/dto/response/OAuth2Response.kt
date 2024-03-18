package exec.oauth2.session.dto.response

interface OAuth2Response {
    //제공자 (Ex. naver, google, ...)
    fun getProvider(): String?

    //제공자에서 발급해주는 아이디(번호)
    fun getProviderId(): String?

    //이메일
    fun getEmail(): String?

    //사용자 실명 (설정한 이름)
    fun getName(): String?

}