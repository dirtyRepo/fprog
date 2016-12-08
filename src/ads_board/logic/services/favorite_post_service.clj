(ns ads-board.logic.services.favorite-post-service
	(:require [ads-board.logic.services-protocols.favorite-post-protocol :as favorite-post-protocol]
			  [ads-board.dal.rep.favorite-post-rep :as favorite-post-repo]))

(deftype favorite-post-service [favorite-post-repo]

	favorite-post-protocol/favorite-post-service-protocol

	(get-by-user-id [this user_id]
		(.get-by-user-id favorite-post-repo user_id))

	(get-by-post-id [this post_id]
		(.get-by-post-id favorite-post-repo post_id))

	(insert-item [this newItem]
		(.insert-item favorite-post-repo newItem))

	(delete-item [this itemToDelete]
		(.delete-item favorite-post-repo itemToDelete))
)
