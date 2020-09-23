
CREATE TABLE IF NOT EXISTS messageIdByRecipientTopic (
    recipientId uuid,
    topicId uuid,
    createDate timestamp,
    messageId uuid,
    messageStatus text,
    deletedStatus boolean,
    PRIMARY KEY((recipientId,topicId),createDate,messageId)
) WITH CLUSTERING ORDER BY (createDate DESC);
