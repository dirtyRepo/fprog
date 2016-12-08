(ns ads-board.logic.services-protocols.favorite-category-protocol)

(defprotocol favorite-category-service-protocol
	(get-by-user-id [this user_id])
	(get-by-category-id [this category_id])
	(insert-item [this newItem])
	(delete-item [this itemToDelete]))