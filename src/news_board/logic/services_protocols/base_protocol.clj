(ns news_board.logic.services-protocols.base-protocol)

(defprotocol base-service-protocol
	(get-items [this])
	(get-item [this id])
	(insert-item [this newItem])
	(update-item [this updatedItem])
	(delete-item [this id]))