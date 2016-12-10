(ns news_board.dal.rep-protocol.posts-protocol)

(defprotocol posts-rep-protocol
	(get-by-user-id [this user_id]))