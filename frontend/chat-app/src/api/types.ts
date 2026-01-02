export interface LoginRequest {
  username: string;
  password: string;
}

export interface NewPersonRequest {
  firstname: string;
  lastname: string;
  username: string;
  password: string;
}

export interface Person {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
}

export interface CreateMessageRequest {
  conversationId?: number;
  participantIds?: number[];
  senderId: number;
  text: string;
}

export interface Message {
  conversationId: number;
  senderId: number;
  message: string;
  messageId: number;
  creationDate: Date;
}

export interface ParticipantInfo {
  id: number;
  firstName: string;
  lastName: string;
}

export interface Conversation {
  conversationId: number;
  conversationName: string;
  latestMessage: Message;
  participants: ParticipantInfo[];
}
