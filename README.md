# Boundr - Rate Limiter

Boundr is a lightweight, in-memory rate limiting library based on the token bucket algorithm.

## About

Boundr controls how often an action can be performed over time using token refilling.
It tracks requests per user, IP, or API key and rejects requests when configured limits are exceeded.

## When to Use

- Application-level rate limiting for APIs or services
- No external dependencies such as Redis
- Predictable and testable request limits

## How It Works

- Each key (user, IP, or API key) is assigned a token bucket
- Tokens are refilled over time
- Each request consumes one token
- When no tokens are available, the request is rejected


## Examples

```java
// With Java

BoundrConfig config = new BoundrConfig(10, 5 * 60 * 1000);
Boundr boundr = new Boundr(config);

if (boundr.allow("user12")) {
    // process request
}
```

```typescript
// With TypeScript

const boundr = new Boundr({ capacity: 10, refillMs: 60000 });

if (boundr.allow("user12")) {
    // process request
}
```

## Repo Structure

`/docs` - Include Boundr Documentation

`/java` - Java source code

`/typescript` - TypeScript source code

`/javascript` - Compiled JavaScript output


