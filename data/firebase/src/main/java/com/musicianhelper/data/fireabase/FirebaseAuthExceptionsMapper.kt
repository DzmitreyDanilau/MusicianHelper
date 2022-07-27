package com.musicianhelper.data.fireabase

import com.google.firebase.auth.FirebaseAuthException
import com.musicianhelper.data.AuthThrowable
import com.musicianhelper.data.ExceptionMapper
import javax.inject.Inject

class FirebaseAuthExceptionsMapper @Inject constructor() : ExceptionMapper<FirebaseAuthException> {

  override fun map(exception: FirebaseAuthException): Throwable {
    val error = when (exception.errorCode) {
      "ERROR_INVALID_EMAIL" -> {
        FirebaseAuthErrors.ERROR_INVALID_EMAIL
      }
      "ERROR_CREDENTIAL_ALREADY_IN_USE" -> {
        FirebaseAuthErrors.ERROR_CREDENTIAL_ALREADY_IN_USE
      }
      "ERROR_USER_NOT_FOUND" -> {
        FirebaseAuthErrors.ERROR_USER_NOT_FOUND
      }
      "ERROR_WEAK_PASSWORD" -> {
        FirebaseAuthErrors.ERROR_WEAK_PASSWORD
      }
      "ERROR_MISSING_EMAIL" -> {
        FirebaseAuthErrors.ERROR_MISSING_EMAIL
      }
      "ERROR_EMAIL_ALREADY_IN_USE" -> {
        FirebaseAuthErrors.ERROR_EMAIL_ALREADY_IN_USE
      }
      else -> FirebaseAuthErrors.ERROR_UNKNOWN
    }

    return AuthThrowable(error.text, error.code)
  }

  internal enum class FirebaseAuthErrors(
    val code: Int,
    val text: String
  ) {
    ERROR_UNKNOWN(999, "Sorry, something happened. Try again later"),
    ERROR_INVALID_EMAIL(
      901,
      "The email address is badly formatted."
    ),
    ERROR_CREDENTIAL_ALREADY_IN_USE(
      902,
      "This credential is already associated with a different user account."
    ),
    ERROR_USER_NOT_FOUND(
      903,
      "There is no user record corresponding to this identifier. The user may have been deleted."
    ),
    ERROR_WEAK_PASSWORD(904, "The given password is invalid."),
    ERROR_MISSING_EMAIL(905, "An email address must be provided."),
    ERROR_EMAIL_ALREADY_IN_USE(
      906,
      "The email address is already in use by another account."
    )
  }

//    ("ERROR_INVALID_CUSTOM_TOKEN", "The custom token format is incorrect. Please check the documentation."));
//    ("ERROR_CUSTOM_TOKEN_MISMATCH", "The custom token corresponds to a different audience."));
//    ("ERROR_INVALID_CREDENTIAL", "The supplied auth credential is malformed or has expired."));
//    ("ERROR_WRONG_PASSWORD", "The password is invalid or the user does not have a password."));
//    ("ERROR_USER_MISMATCH", "The supplied credentials do not correspond to the previously signed in user."));
//    ("ERROR_REQUIRES_RECENT_LOGIN", "This operation is sensitive and requires recent authentication. Log in again before retrying this request."));
//    ("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL", "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."));
//    ("ERROR_USER_DISABLED", "The user account has been disabled by an administrator."));
//    ("ERROR_USER_TOKEN_EXPIRED", "The user\'s credential is no longer valid. The user must sign in again."));
//    ("ERROR_INVALID_USER_TOKEN", "The user\'s credential is no longer valid. The user must sign in again."));
//    ("ERROR_OPERATION_NOT_ALLOWED", "This operation is not allowed. You must enable this service in the console."));
}