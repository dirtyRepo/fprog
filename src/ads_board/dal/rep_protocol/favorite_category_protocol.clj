(ns ads-board.dal.rep-protocol.favorite-category-protocol)

(defprotocol favorite-category-rep-protocol
	(get-by-user-id [this user_id])
	(get-by-category-id [this category_id])
	(insert-item [this newItem])
	(delete-item [this itemToDelete]))