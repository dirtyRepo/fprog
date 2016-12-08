(ns ads-board.dal.rep-protocol.favorite-post-protocol)

(defprotocol favorite-post-rep-protocol
	(get-by-user-id [this user_id])
	(get-by-post-id [this post_is])
	(insert-item [this newItem])
	(delete-item [this itemToDelete]))