(ns ads-board.logic.services-protocols.posts-protocol)

(defprotocol posts-service-protocol
	(get-by-user-id [this user_id]))