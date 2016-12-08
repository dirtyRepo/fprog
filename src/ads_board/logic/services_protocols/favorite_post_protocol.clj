(ns ads-board.logic.services-protocols.favorite-post-protocol)

(defprotocol favorite-post-service-protocol
	(get-by-user-id [this user_id])
	(get-by-post-id [this post_id])
	(insert-item [this newItem])
	(delete-item [this itemToDelete]))