(ns ads-board.dal.rep-protocol.feadback-protocol)

(defprotocol feadback-rep-protocol
	(get-by-post-id [this post_id]))