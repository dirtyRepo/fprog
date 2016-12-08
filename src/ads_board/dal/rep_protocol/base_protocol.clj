(ns ads-board.dal.rep-protocol.base-protocol)

(defprotocol base-rep-protocol
	(get-items [this])
	(get-item [this id])
	(insert-item [this newItem])
	(update-item [this updatedItem])
	(delete-item [this id]))