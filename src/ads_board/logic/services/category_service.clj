(ns ads-board.logic.services.category-service
	(:require [ads-board.logic.services-protocols.base-protocol :as base-protocol]
			  [ads-board.dal.rep.category-rep :as category-repo]))

(deftype category-service [category-repo]

	base-protocol/base-service-protocol

	(get-items [this]
		(.get-items category-repo))

	(get-item [this id]
		(first (.get-item category-repo id)))

	(insert-item [this newItem]
		(.insert-item category-repo newItem))

	(update-item [this updatedItem]
		(.update-item category-repo updatedItem))

	(delete-item [this id]
		(.delete-item category-repo id))
)
