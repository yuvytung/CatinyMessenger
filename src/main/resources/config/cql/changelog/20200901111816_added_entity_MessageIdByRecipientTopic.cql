
CREATE TABLE IF NOT EXISTS messageIdByRecipientTopic (
    id uuid,
    recipientId uuid,
    topicId uuid,
    createDate timestamp,
    messageId uuid,
    messageStatus text,
    deletedStatus boolean,
    PRIMARY KEY(id)
);
