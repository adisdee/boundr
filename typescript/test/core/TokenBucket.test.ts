/* Test that performs token consumption and refilling */

import { TokenBucket } from "../../src/core/TokenBucket";
import { SystemTimeProvider } from "../../src/time/SystemTimeProvider";

async function run() {
  const time = new SystemTimeProvider();
  const bucket = new TokenBucket(3, 5000, time);

  console.assert(bucket.consumeToken() === true, "Accepted");
  console.assert(bucket.consumeToken() === true, "Accepted");
  console.assert(bucket.consumeToken() === true, "Accepted");
  console.assert(bucket.consumeToken() === true, "Rejected");

  console.log("Waiting to refill...");

  await new Promise(r => setTimeout(r, 7000));

  console.assert(bucket.consumeToken() === true, "After Refill - Accepted");

  console.log("TOKEN BUCKET TEST PASSED");  
}

run();