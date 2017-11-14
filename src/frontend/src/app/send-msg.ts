export class SendMessageRequest {
  constructor(recipient: number, message: string) {
    this.recipient = recipient;
    this.message = message;
  }

  public recipient: number;
  public message: string;
}
