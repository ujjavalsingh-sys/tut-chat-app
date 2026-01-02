package com.example.chat.chat.conversation;

import com.example.chat.chat.message.Message;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Conversation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  public Long getId() {
    return id;
  }

  // 1 Conversation has many messages
  // mappedBy: bi-directional association with Messages entity
  // cascade: deleting a conversation deletes all messages associated to it
  @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Message> messages = new ArrayList<>();

  @ElementCollection
  @CollectionTable(
      name = "conversation_participants",
      joinColumns = @JoinColumn(name = "conversation_id"))
  @Column(name = "person_id")
  private Set<Long> participantsIds = new HashSet<>();

  public Set<Long> getParticipantsIds() {
    return participantsIds;
  }

  public void addParticipantId(Long id) {
    participantsIds.add(id);
  }

  public Conversation() {}

  public Conversation(Set<Long> participantsIds) {
    this.participantsIds = participantsIds;
  }
}
