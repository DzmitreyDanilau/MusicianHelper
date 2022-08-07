package com.musicianhelper.core.common

interface State
interface Action
interface Event
interface Result
interface Navigation
interface ErrorResult : Result {
  val error: Throwable
}
