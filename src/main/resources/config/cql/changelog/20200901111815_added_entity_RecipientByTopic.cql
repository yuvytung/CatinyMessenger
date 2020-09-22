
CREATE TABLE IF NOT EXISTS recipientByTopic (
    topicId uuid,
    recipientId uuid,
    role text,
    createDate timestamp,
    recipientName text,
    topicName text,
    adderId uuid,
    creatorId uuid,
    PRIMARY KEY(topicId,recipientId)
);
