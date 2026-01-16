import { TimeProvider } from "./TimeProvider";

/* Provides system current time */

export class SystemTimeProvider implements TimeProvider {
  now(): number {
    return Date.now();
  }
}
