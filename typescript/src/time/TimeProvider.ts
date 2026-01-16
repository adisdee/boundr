/* Abstraction for system time access */

export interface TimeProvider {
  /* @return current time in milliseconds */

  now(): number;
}
