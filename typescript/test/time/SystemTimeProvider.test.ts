/* Test that system time provider returns moving forward time */

import { SystemTimeProvider } from "../../src/time/SystemTimeProvider";

const time = new SystemTimeProvider();

const t1 = time.now();

setTimeout(() => {
  const t2 = time.now();

  console.assert(t2 > t1, "Time should move forward");
  console.log("SYSTEM TIME TEST PASSED");
}, 1000);
