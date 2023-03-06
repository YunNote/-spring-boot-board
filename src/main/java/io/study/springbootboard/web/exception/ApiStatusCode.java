package io.study.springbootboard.web.exception;

public enum ApiStatusCode {

   OK(200, "Success Api Call"),
   CREATED(201, "Created Success"),

   BAD_REQUEST(400, "Bad Request"),
   X_API_VERSION_NOT_FOUND(404, "x-api-version Not Found"),

   // JWT Prefix 1xxx
   JWT_UNKNOWN_ERROR(1400, "Unknown Jwt Error"),
   INVALID_JWT_SIGNATURE(1401, "Invalid JWT Signature "),
   EXPIRED_JWT_TOKEN(1402, "Expired Jwt Token"),
   UNSUPPORTED_JWT_TOKEN(1403, "Unsupported Jwt Token"),
   INVALID_JWT_TOKEN(1404, "Invalid Jwt Token"),
   JWT_AUTHORITIES_NOT_FOUND(1405, "Authorities not found Exception"),

   // User Prefix 2xxx

   USER_UNAUTHORIZED(2401, "User Unauthorized"),
   USER_LOGIN_NOT_MATCHED(2404, "User Email or Password Not Match"),
   USER_EMAIL_VALIDATION_FAILED(2410, "이메일 형식이 잘못되었습니다."),
   USER_PASSWORD_VALIDATION_FAILED(2411, "비밀번호 형식이 잘못되었습니다."),
   USER_EMAIL_IS_EXIST(2412, "이미 존재하는 이메일입니다.")
   ;

   private int code;
   private String description;

   ApiStatusCode(int code, String description) {
      this.code = code;
      this.description = description;
   }

   public int getCode() {
      return this.code;
   }

   public String getDescription() {
      return this.description;
   }
}
