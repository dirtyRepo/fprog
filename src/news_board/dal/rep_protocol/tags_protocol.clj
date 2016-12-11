(ns news_board.dal.rep-protocol.tags-protocol)

(defprotocol tags-rep-protocol
  (get-users-subsribe-tags-id [this id]))