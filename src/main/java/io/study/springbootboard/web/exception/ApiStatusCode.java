package io.study.springbootboard.web.exception;

public enum ApiStatusCode {

   BAD_REQUEST(400, "Bad Request"),
   X_API_VERSION_NOT_FOUND(404, "x-api-version Not Found"),

   // JWT Prefix 1xxx
   JWT_UNKNOWN_ERROR(1400, "Unknown Jwt Error"),
   INVALID_JWT_SIGNATURE(1401, "Invalid JWT Signature "),
   EXPIRED_JWT_TOKEN(1402, "Expired Jwt Token"),
   UNSUPPORTED_JWT_TOKEN(1403, "Unsupported Jwt Token"),
   INVALID_JWT_TOKEN(1404, "Invalid Jwt Token"),
   JWT_AUTHORITIES_NOT_FOUND(1405, "Authorities not found Exception")
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
