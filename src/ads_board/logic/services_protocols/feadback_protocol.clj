(ns ads-board.logic.services-protocols.feadback-protocol)

(defprotocol feadback-service-protocol
	(get-by-post-id [this post_id]))