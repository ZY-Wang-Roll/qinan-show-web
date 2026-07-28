package com.luyingqinhua.controller;

import com.luyingqinhua.common.Result;
import com.luyingqinhua.entity.Message;
import com.luyingqinhua.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /** 获取所有留言（最新在前） */
    @GetMapping("/messages")
    public Result<List<Message>> getAll() {
        return Result.ok(messageService.getAll());
    }

    /** 提交新留言 */
    @PostMapping("/messages")
    public Result<Message> create(@RequestBody Message message) {
        if (message.getNickname() == null || message.getNickname().isBlank()) {
            return Result.fail(400, "昵称不能为空");
        }
        if (message.getContent() == null || message.getContent().isBlank()) {
            return Result.fail(400, "留言内容不能为空");
        }
        Message saved = messageService.create(message);
        return Result.ok(saved);
    }

    /** 编辑留言（管理后台用） */
    @PutMapping("/messages/{id}")
    public Result<Message> update(@PathVariable Long id, @RequestBody Message message) {
        return Result.ok(messageService.update(id, message));
    }

    /** 删除留言（管理后台用） */
    @DeleteMapping("/messages/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        messageService.delete(id);
        return Result.ok();
    }
}
