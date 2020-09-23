
CREATE TABLE IF NOT EXISTS messageSimpleByRecipient (
    id uuid,
    recipientId uuid,
    topicId uuid,
    senderName text,
    content text,
    createDate timestamp,
    senderId uuid,
    recipientName text,
    topicName text,
    messageStatus text,
    messageId uuid,
    PRIMARY KEY(id)
);
