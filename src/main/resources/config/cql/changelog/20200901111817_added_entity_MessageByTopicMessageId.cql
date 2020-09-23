
CREATE TABLE IF NOT EXISTS messageByTopicMessageId (
    id uuid,
    topicId uuid,
    messageId uuid,
    senderName text,
    messageContent text,
    createDate timestamp,
    modifyDate timestamp,
    topicName text,
    senderId uuid,
    deletedStatus boolean,
    PRIMARY KEY(id)
);
