
CREATE TABLE IF NOT EXISTS messageByTopicMessageId (
    topicId uuid,
    createDate timestamp,
    messageId uuid,
    senderName text,
    messageContent text,
    modifyDate timestamp,
    topicName text,
    senderId uuid,
    deletedStatus boolean,
    PRIMARY KEY((topicId) , createDate ,messageId)
) WITH CLUSTERING ORDER BY (createDate DESC);
