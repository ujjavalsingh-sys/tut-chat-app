export interface LoginForm {
  username: string;
  password: string;
}

export interface RegisterForm {
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

export interface MessageRequest {
  conversationId?: number;
  participantIds?: PersonId[];
  senderId: number;
  text: string;
}

export interface Message {
  conversationId: number;
  senderId: number;
  message: string;
  messageId: number;
}

interface PersonId {
  personId: number;
}

export interface ParticipantInfo {
  id: number;
  firstName: string;
  lastName: string;
}

export interface Conversation {
  conversationId: number;
  messages: Message[];
  participants: ParticipantInfo[];
}

export interface ConversationSummary {
  name: string;
  conversationId: number;
  messageCount: number;
  participants: PersonId[];
}
