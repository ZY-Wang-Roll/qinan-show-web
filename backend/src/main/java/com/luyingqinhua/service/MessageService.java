package com.luyingqinhua.service;

import com.luyingqinhua.entity.Message;
import com.luyingqinhua.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public List<Message> getAll() {
        return repo.findAllByOrderByCreatedAtDesc();
    }

    public Message getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("留言不存在: " + id));
    }

    @Transactional
    public Message create(Message message) {
        if (message.getEnabled() == null) message.setEnabled(true);
        return repo.save(message);
    }

    @Transactional
    public Message update(Long id, Message updated) {
        Message msg = getById(id);
        if (updated.getNickname() != null) msg.setNickname(updated.getNickname());
        if (updated.getEmail() != null) msg.setEmail(updated.getEmail());
        if (updated.getContent() != null) msg.setContent(updated.getContent());
        if (updated.getAvatarUrl() != null) msg.setAvatarUrl(updated.getAvatarUrl());
        if (updated.getEnabled() != null) msg.setEnabled(updated.getEnabled());
        if (updated.getCreatedAt() != null) msg.setCreatedAt(updated.getCreatedAt());
        return repo.save(msg);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
