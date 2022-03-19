package com.musicianhelper.data.fireabase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.musicianhelper.data.User
import com.musicianhelper.domain.AuthenticationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthenticationService @Inject constructor(
  private val firebaseAuth: FirebaseAuth
) : AuthenticationService {

  override fun login(
    name: String,
    password: String
  ): Flow<Result<User>> {
    return flow<Result<User>> {
      try {
        val authResult = firebaseAuth.signInWithEmailAndPassword(name, password).await()
        emit(Result.success(mapToUser(authResult)))
      } catch (e: Exception) {
        emit(Result.failure(e))
      }
    }
  }

  override fun logOut() {
    TODO("Not yet implemented")
  }

  private fun mapToUser(authResult: AuthResult): User {
    val user = authResult.user
    return User(id = user?.uid ?: "", name = user?.email ?: "")
  }
}

// data class AuthException(
//   val error: Throwable?,
//   val code: Int
// ) : Throwable()

// class FirebaseErrorMapper {
//
// fun map(authException: FirebaseAuthException): Throwable {
//   return when (authException.errorCode) {
//     "ERROR_INVALID_EMAIL" -> {
//       AuthException(authException.cause, authException)
//     }
//     "ERROR_CREDENTIAL_ALREADY_IN_USE" -> {
//     }
//     "ERROR_USER_NOT_FOUND" -> {
//     }
//     "ERROR_WEAK_PASSWORD" -> {
//     }
//     "ERROR_MISSING_EMAIL" -> {
//     }
//     else -> {}
//   }
// }

// ("ERROR_INVALID_CUSTOM_TOKEN", "The custom token format is incorrect. Please check the documentation."));
// ("ERROR_CUSTOM_TOKEN_MISMATCH", "The custom token corresponds to a different audience."));
// ("ERROR_INVALID_CREDENTIAL", "The supplied auth credential is malformed or has expired."));
// ("ERROR_INVALID_EMAIL", "The email address is badly formatted."));
// ("ERROR_WRONG_PASSWORD", "The password is invalid or the user does not have a password."));
// ("ERROR_USER_MISMATCH", "The supplied credentials do not correspond to the previously signed in user."));
// ("ERROR_REQUIRES_RECENT_LOGIN", "This operation is sensitive and requires recent authentication. Log in again before retrying this request."));
// ("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL", "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."));
// ("ERROR_EMAIL_ALREADY_IN_USE", "The email address is already in use by another account."));
// ("ERROR_CREDENTIAL_ALREADY_IN_USE", "This credential is already associated with a different user account."));
// ("ERROR_USER_DISABLED", "The user account has been disabled by an administrator."));
// ("ERROR_USER_TOKEN_EXPIRED", "The user\'s credential is no longer valid. The user must sign in again."));
// ("ERROR_USER_NOT_FOUND", "There is no user record corresponding to this identifier. The user may have been deleted."));
// ("ERROR_INVALID_USER_TOKEN", "The user\'s credential is no longer valid. The user must sign in again."));
// ("ERROR_OPERATION_NOT_ALLOWED", "This operation is not allowed. You must enable this service in the console."));
// ("ERROR_WEAK_PASSWORD", "The given password is invalid."));
// ("ERROR_MISSING_EMAIL", "An email address must be provided.";
// }