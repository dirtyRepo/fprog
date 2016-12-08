(ns ads-board.logic.services.favorite-category-service
	(:require [ads-board.logic.services-protocols.favorite-category-protocol :as favorite-category-protocol]
			  [ads-board.dal.rep.favorite-category-rep :as favorite-category-repo]))

(deftype favorite-category-service [favorite-category-repo]

	favorite-category-protocol/favorite-category-service-protocol

	(get-by-user-id [this user_id]
		(.get-by-user-id favorite-category-repo user_id))

	(get-by-category-id [this category_id]
		(.get-by-category-id favorite-category-repo category_id))

	(insert-item [this newItem]
		(.insert-item favorite-category-repo newItem))

	(delete-item [this itemToDelete]
		(.delete-item favorite-category-repo itemToDelete))
)
