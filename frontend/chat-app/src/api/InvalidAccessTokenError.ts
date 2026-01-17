export class InvalidAccessTokenError extends Error {
  constructor() {
    super("Session ended, login again to continue");
  }
}
